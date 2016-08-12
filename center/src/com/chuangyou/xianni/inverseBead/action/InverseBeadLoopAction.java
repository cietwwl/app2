package com.chuangyou.xianni.inverseBead.action;

import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.inverseBead.RefreshInverseBeadMsgProto.RefreshInverseBeadMsg;
import com.chuangyou.common.protobuf.pb.inverseBead.SyncMonsterPoolMsgProto.SyncMonsterPoolMsg;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.exec.LoopAction;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.inverseBead.template.InverseBeadTemMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 怪物池管理
 * 
 * @author Administrator
 *
 */
public class InverseBeadLoopAction extends LoopAction {
	private GamePlayer player;
	private static final int count = Integer.MAX_VALUE;
	private static final int delay = 500;
	public static int intervalTime; // 间隔时间
	private List<Integer> list;

	public InverseBeadLoopAction(GamePlayer player, List<Integer> list) {
		super(player.getActionQueue(), delay, count);
		this.player = player;
		this.list = list;
	}

	@Override
	public void loopExecute() {
		boolean res = refreshSpawn();
		boolean res2 = refreshAura();
		if (res || res2) {
			// PlayerTimeInfo playerTimeInfo = player.getBasePlayer().getPlayerTimeInfo();
			PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();

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
			MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get(tem.getEntityId());
			msg.setMonsterLv(monsterInfo.getLevel());
			msg.setLastTime((int) Math.ceil(lastTime));

			PBMessage pbm = MessageUtil.buildMessage(Protocol.U_REFRESH_INVERSE_BEAD, msg);
			this.player.sendPbMessage(pbm);
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
		int t = Math.max(1, MathUtils.randomClamp(a - b * 0, a));
		return t * 60 * 1000;
	}

	private boolean refreshSpawn() {
		boolean need2s = false;
		if(player.getInverseBeadRefreshInventory()==null)
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
					System.err.println("配置丢失。。。。。。。");
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
			PBMessage c2s = MessageUtil.buildMessage(Protocol.S_CREATE_INVERSE_SYNC_MONSTER, msg);
			player.sendPbMessage(c2s);
		}

		return need2s;
	}

	private boolean refreshAura() {
		// PlayerTimeInfo playerTimeInfo = player.getBasePlayer().getPlayerTimeInfo();
		if(player.getInverseBeadRefreshInventory()==null)
			return false;
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		if (playerTimeInfo == null)
			return false;
		boolean need2s = false;
		while (true) {
			int auraNum = playerTimeInfo.getAuraNum();
			Date auraRefreshDateTime = playerTimeInfo.getAuraRefreshDateTime();
			if (auraRefreshDateTime == null) {
				playerTimeInfo.setAuraRefreshDateTime(new Date());
				auraRefreshDateTime = playerTimeInfo.getAuraRefreshDateTime();
			}
			if (System.currentTimeMillis() - auraRefreshDateTime.getTime() < 30 * 60 * 1000)
				break;
			auraNum++;
			playerTimeInfo.setAuraNum(auraNum);
			playerTimeInfo.setAuraRefreshDateTime(new Date(auraRefreshDateTime.getTime() + 30 * 60 * 1000));
			need2s = true;
		}
		return need2s;
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
