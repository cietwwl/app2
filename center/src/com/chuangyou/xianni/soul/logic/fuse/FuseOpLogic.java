package com.chuangyou.xianni.soul.logic.fuse;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.chuangyou.common.protobuf.pb.soul.SoulFuseRespProto.SoulFuseRespMsg;
import com.chuangyou.common.protobuf.pb.soul.SyncSoulProto.SyncSoulLv;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.FuseItemConfig;
import com.chuangyou.xianni.entity.soul.SoulFuseSkillConfig;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.FuseSkillVo;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 开始融合
 * 
 * @author laofan
 *
 */
public class FuseOpLogic extends BaseFuseLogic implements IFuseLogic {

	protected int	pos;
	protected int	useItemId;
	protected FuseSkillVo tempSkill;
	protected int value;

	public FuseOpLogic(int op, GamePlayer player, int index, int pos, int useItemId) {
		super(op, player, index);
		this.pos = pos;
		this.useItemId = useItemId;
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub

		BaseItem item = player.getBagInventory().getPlayerBag().getItemByPos(pos);
//		if (item == null || item.getItemInfo().getId() != useItemId) {
		if (item == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE, "客户端传来的数据有误：" + useItemId + ":::" + pos);
			return;
		}
		value = item.getItemInfo().getPro();
		
		FuseItemConfig fuseItemConfig = SoulTemplateMgr.getFuseItemConfigMap().get(index);
		if(fuseItemConfig == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE, "FuseItemConfig配置表数据错误");
			return;
		}
		if(fuseItemConfig.getNeedItems().indexOf(item.getItemTempInfo().getId())==-1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE, "材料使用不对");
			return;
		}
		Map<Integer, FuseSkillVo> tempSkillMap = player.getSoulInventory().getTempMap();
		if (tempSkillMap.containsKey(this.index)) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE, "有未确定的技能。");
			return;
		}

		int oldSkillId = getOldSkillId();

		// 随机技能
		int newSkillID = getRandomSkillID(SoulTemplateMgr.getFuseSkillPoolMap().get(index), oldSkillId);
		if (newSkillID == 0) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE, "随机不出技能来。");
			return;
		}

		// 消耗材料
		if (!player.getBagInventory().getPlayerBag().removeByPos((short) pos, ItemRemoveType.SOUL)) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE, "消耗物品失败");
			return;
		}

		// 设置技能
		if (oldSkillId == 0) {
			this.setNewSkill(newSkillID,item.getItemTempInfo().getItemcolor());
		} else {
			tempSkill = new FuseSkillVo(newSkillID, item.getItemTempInfo().getItemcolor());
			this.player.getSoulInventory().getTempMap().put(index, tempSkill);
		}
		
		// 加魂魄值
		this.soulInfo.setExp(this.soulInfo.getExp() + value);
		this.soulInfo.setOp(Option.Update);

		player.getSoulInventory().updateProperty();
		sendResult(player);
		
		//经验值同步到scene服务器
		
		SoulInfo soulInfo = player.getSoulInventory().getSoulInfo();
		int lv = LevelUpTempleteMgr.getSoulLevel(soulInfo.getExp());
		SyncSoulLv.Builder sync = SyncSoulLv.newBuilder();
		sync.setSoulLv(lv);
		PBMessage pkg =  MessageUtil.buildMessage(Protocol.S_REQ_SOUL_EXP,sync);
		player.sendPbMessage(pkg);
	}

	/**
	 * 随机 -- 从技能池中随机出一条技能
	 * 
	 * @param list
	 * @param curSkillId
	 * @return
	 */
	protected int getRandomSkillID(List<SoulFuseSkillConfig> list, int curSkillId) {
		int size = list.size();
		if (size < 1) {
			Log.error("魂幡中技能数量不足===严重错误");
			return 0;
		}

		int count = 0;
		for (SoulFuseSkillConfig soulFuseSkillConfig : list) {
			count += soulFuseSkillConfig.getWeight();
		}
		int random = new Random().nextInt(count);
		int curCount = 0;
		for (SoulFuseSkillConfig soulFuseSkillConfig : list) {
			if (curCount + soulFuseSkillConfig.getWeight() > random) {
				return soulFuseSkillConfig.getId();
			} else {
				curCount +=soulFuseSkillConfig.getWeight();
			}
		}
		
		return 0;

	}

	@Override
	protected SoulFuseRespMsg.Builder getMsg(GamePlayer player) {
		// TODO Auto-generated method stub
		SoulFuseRespMsg.Builder msg = super.getMsg(player);
		if (tempSkill!=null){
			msg.setTempSkill(tempSkill.getSkillId());
			msg.setTempSkillColor(tempSkill.getColor());
			msg.setSoulExp(value);
		}
		return msg;
	}

	/**
	 * 获取旧技能ID
	 * 
	 * @return
	 */
	private int getOldSkillId() {
		int oldSkillId = 0;
		if (index == 1) {
			oldSkillId = this.soulInfo.getFuseSkillId1();
			if (isExpire(this.soulInfo.getFuseSkillCreateTime1(), oldSkillId)) {
				return 0;
			}
		} else if (index == 2) {
			oldSkillId = this.soulInfo.getFuseSkillId2();
			if (isExpire(this.soulInfo.getFuseSkillCreateTime2(), oldSkillId)) {
				return 0;
			}
		} else if (index == 3) {
			oldSkillId = this.soulInfo.getFuseSkillId3();
			if (isExpire(this.soulInfo.getFuseSkillCreateTime3(), oldSkillId)) {
				return 0;
			}
		} else if (index == 4) {
			oldSkillId = this.soulInfo.getFuseSkillId4();
			if (isExpire(this.soulInfo.getFuseSkillCreateTime4(), oldSkillId)) {
				return 0;
			}
		}
		return oldSkillId;
	}

	private boolean isExpire(Date date, int oldSkillId) {
		long t = System.currentTimeMillis();
		if (t - date.getTime() > SoulTemplateMgr.MAX_SOUL_SKILL_CD && oldSkillId > 0) {
			return true;
		}
		return false;
	}

	/////////////////////////////////////////////////////////////////////////////////
	//
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getUseItemId() {
		return useItemId;
	}

	public void setUseItemId(int useItemId) {
		this.useItemId = useItemId;
	}

}
