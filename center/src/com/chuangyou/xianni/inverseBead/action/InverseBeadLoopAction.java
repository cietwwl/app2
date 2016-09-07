package com.chuangyou.xianni.inverseBead.action;

import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.inverseBead.RefreshInverseBeadMsgProto.RefreshInverseBeadMsg;
import com.chuangyou.common.protobuf.pb.inverseBead.SyncMonsterPoolMsgProto.SyncMonsterPoolMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.inverseBead.template.InverseBeadTemMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 怪物池管理
 * 
 * @author Administrator
 *
 */
public class InverseBeadLoopAction extends DelayAction {
	private GamePlayer		player;
	// private static final int count = Integer.MAX_VALUE;
	// private static final int delay = 50000;
	public static int		intervalTime	= 0;	// 下一次时间
	private List<Integer>	list;
	private boolean			isLoop			= true;

	public InverseBeadLoopAction(GamePlayer player, ActionQueue queue, List<Integer> list, boolean isLoop) {
		super(queue, intervalTime);
		this.player = player;
		this.list = list;
		this.isLoop = isLoop;
	}

	@Override
	public void execute() {
		if (player.getInverseBeadRefreshInventory() == null)
			return;
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		if (playerTimeInfo == null)
			return;

		boolean res = refreshSpawn();
		boolean res2 = refreshAura();

		if (res || res2) {
			// PlayerTimeInfo playerTimeInfo =
			// player.getBasePlayer().getPlayerTimeInfo();
			RefreshInverseBeadMsg.Builder msg = RefreshInverseBeadMsg.newBuilder();
			String beadRefreshId = playerTimeInfo.getBeadRefreshId();
			List<Integer> list = InverseBeadManager.getBeadRefreshId(beadRefreshId);
			msg.setMonsterNum(list.size());
			msg.setBeadRefreshDateTime(playerTimeInfo.getBeadRefreshDateTime().getTime());
			msg.setAuraNum(playerTimeInfo.getAuraNum());
			msg.setAuraRefreshDateTime(playerTimeInfo.getAuraRefreshDateTime().getTime());

			long lastTime = intervalTime - (System.currentTimeMillis() - playerTimeInfo.getBeadRefreshDateTime().getTime());
			if (lastTime < 0)
				lastTime = 0;
			SpawnInfo tem = InverseBeadTemMgr.getSpwanId(playerTimeInfo.getCurrRefreshId());
			if (tem == null) {
				return;
			}
			MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get(tem.getEntityId());

			if (monsterInfo == null) {
				// System.err.println("配置错误。。entityId:" + tem.getEntityId());
				return;
			}

			msg.setMonsterLv(monsterInfo.getLevel());
			msg.setLastTime((int) Math.ceil(lastTime));

			PBMessage pbm = MessageUtil.buildMessage(Protocol.U_REFRESH_INVERSE_BEAD, msg);
			this.player.sendPbMessage(pbm);
		}
		if (isLoop && playerTimeInfo != null && player.getPlayerState() == PlayerState.ONLINE) {
			this.execTime = System.currentTimeMillis() + intervalTime;
			getActionQueue().enDelayQueue(this);
		}
	}

	/**
	 * 获取间隔时间
	 * 
	 * @return
	 */
	private int getIntervalTime() {
		int a = SystemConfigTemplateMgr.getIntValue("fiveElements.a");
		int b = SystemConfigTemplateMgr.getIntValue("fiveElements.b");
		// T = Random( A - B*N , A )
		int t = Math.max(1, MathUtils.randomClamp(a - b * 1, a));
		return t * 60 * 1000;
	}

	private boolean refreshSpawn() {
		boolean need2s = false;
		if (player.getInverseBeadRefreshInventory() == null)
			return false;
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		if (playerTimeInfo == null)
			return false;
		synchronized (playerTimeInfo) {
			while (true) {
				String beadRefreshId = playerTimeInfo.getBeadRefreshId();
				Date beadRefreshDateTime = playerTimeInfo.getBeadRefreshDateTime();
				if (beadRefreshDateTime == null) {
					playerTimeInfo.setBeadRefreshDateTime(new Date());
					beadRefreshDateTime = playerTimeInfo.getBeadRefreshDateTime();
				}
				if (intervalTime <= 0) {// 第一次进来
					intervalTime = getIntervalTime();
				}
				if (System.currentTimeMillis() - beadRefreshDateTime.getTime() < intervalTime)
					break;
				this.list = InverseBeadManager.getBeadRefreshId(beadRefreshId);
				if (this.list.size() >= 30)
					break;

				// 刷新一个怪
				intervalTime = getIntervalTime();
				int curr = playerTimeInfo.getCurrRefreshId();
				SpawnInfo tem = InverseBeadTemMgr.getSpwanId(curr);
				if (tem == null) {
					// System.err.println("配置丢失。。。。。。。");
					Log.error("缺少配置。Spwan：" + curr);
					break;
				}
				int[] nextIds = tem.getNextSpawanIdAttr();
				int nextId;
				if (nextIds.length == 0 || nextIds[0] == 0) {// 最后一个
					nextId = curr;
				} else {
					nextId = nextIds[0];
				}

				if (this.list.contains(nextId)) {
					break;
				}

				this.list.add(nextId);
				String str = InverseBeadManager.getBeadRefreshIdStr(this.list);
				playerTimeInfo.setBeadRefreshId(str);
				playerTimeInfo.setCurrRefreshId(nextId);
				playerTimeInfo.setBeadRefreshDateTime(new Date(beadRefreshDateTime.getTime() + intervalTime));
				need2s = true;
			}
		}
		// 通知scence服务器
		if (need2s) {
			SyncMonsterPoolMsg.Builder msg = SyncMonsterPoolMsg.newBuilder();
			msg.addAllMonsterRefreshId(this.list);
			msg.setCurCampaign(player.getCurCampaign());
			PBMessage c2s = MessageUtil.buildMessage(Protocol.S_CREATE_INVERSE_SYNC_MONSTER, msg);
			player.sendPbMessage(c2s);
		}

		return need2s;
	}

	private boolean refreshAura() {
		// PlayerTimeInfo playerTimeInfo =
		// player.getBasePlayer().getPlayerTimeInfo();
		if (player.getInverseBeadRefreshInventory() == null)
			return false;
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		if (playerTimeInfo == null)
			return false;
		// boolean need2s = false;
		// while (true) {
		int auraNum = playerTimeInfo.getAuraNum();
		Date auraRefreshDateTime = playerTimeInfo.getAuraRefreshDateTime();
		if (auraRefreshDateTime == null) {
			playerTimeInfo.setAuraRefreshDateTime(new Date());
			auraRefreshDateTime = playerTimeInfo.getAuraRefreshDateTime();
		}

		long timeLen = System.currentTimeMillis() - auraRefreshDateTime.getTime();
		int addNum = (int) (timeLen / 1800000);// 30 分钟
		if (addNum <= 0)
			return false;

		auraNum += addNum;
		auraNum = Math.min(99, auraNum);
		playerTimeInfo.setAuraNum(auraNum);
		playerTimeInfo.setAuraRefreshDateTime(new Date(auraRefreshDateTime.getTime() + 30 * 60 * 1000 * addNum));

		// if (System.currentTimeMillis() - auraRefreshDateTime.getTime() < 30 *
		// 60 * 1000)
		// break;
		// auraNum++;
		// playerTimeInfo.setAuraNum(auraNum);
		// playerTimeInfo.setAuraRefreshDateTime(new
		// Date(auraRefreshDateTime.getTime() + 30 * 60 * 1000));
		// need2s = true;
		// }

		return true;
	}
	// public static void main(String[] args) {
	// List<Integer> list = new ArrayList<>();
	// list.add(1);
	// list.add(100);
	// list.add(1123);
	// list.add(456);
	// String str = getBeadRefreshIdStr(list);
	//
	// System.out.println(str);
	// List<Integer> list2 = getBeadRefreshId(str);
	// System.out.println(list2);
	// System.out.println(list2.remove(0));
	// System.out.println(list2);
	// }

}
