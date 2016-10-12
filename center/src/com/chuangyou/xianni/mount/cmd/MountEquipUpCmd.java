package com.chuangyou.xianni.mount.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.mount.MountAttUpdateRespProto.MountAttUpdateRespMsg;
import com.chuangyou.common.protobuf.pb.mount.MountEquipUpReqProto.MountEquipUpReqMsg;
import com.chuangyou.common.protobuf.pb.mount.MountEquipUpRespProto.MountEquipUpRespMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.mount.MountEquipCfg;
import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.state.event.MountStateEvent;

@Cmd(code = Protocol.C_MOUNT_EQUIP_UP, desc = "坐骑装备升级")
public class MountEquipUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		MountEquipUpReqMsg req = MountEquipUpReqMsg.parseFrom(packet.getBytes());
		//角色坐骑所有装备
		Map<Integer, MountEquipInfo> roleMountEquip = player.getMountInventory().getMountEquip();
		//请求升级的装备
		MountEquipInfo equip = roleMountEquip.get(req.getId());
		
		Map<Integer, MountEquipCfg> equipCfg = MountTemplateMgr.getEquipTemps().get(req.getId());
		if(equip == null || equipCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Equip_Not_Existed, packet.getCode());
			return;
		}
		//判断是否最高级
		MountEquipCfg targetCfg = equipCfg.get(equip.getEquipLevel() + 1);
		if(targetCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Equip_Level_Max, packet.getCode());
			return;
		}
		
		MountEquipCfg levelCfg = equipCfg.get(equip.getEquipLevel());
		//判断道具
		if(player.getBagInventory().getPlayerBagItemCount(levelCfg.getUpLevItem()) < levelCfg.getUpLevItemNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(levelCfg.getUpLevItem(), levelCfg.getUpLevItemNum(), ItemRemoveType.MOUNT_EQUIP_UP)) return;
		
		ThreadSafeRandom random = new ThreadSafeRandom();
		
		boolean isSuccess = random.isSuccessful(levelCfg.getRate(), 10000);
		if(isSuccess){
			//升级成功
			equip.setEquipLevel(equip.getEquipLevel() + 1);
			player.getMountInventory().updateMountEquip(equip);
			player.notifyListeners(new MountStateEvent(this,4, equip.getEquipId(),equip.getEquipLevel(),EventNameType.MOUNT));
		}
		
		//返回消息
		MountEquipUpRespMsg.Builder msg = MountEquipUpRespMsg.newBuilder();
		msg.setId(equip.getEquipId());
		msg.setLevel(equip.getEquipLevel());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_EQUIP_UP, msg);
		player.sendPbMessage(p);
		
		//属性变更
		MountAttUpdateRespMsg.Builder attMsg = MountAttUpdateRespMsg.newBuilder();
		Map<Integer, Integer> attMap = MountManager.computeMountAtt(player);
		for(Integer attType:attMap.keySet()){
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			bean.setAttType(attType);
			bean.setAttValue(attMap.get(attType));
			attMsg.addAtts(bean);
		}
		PBMessage pAtt = MessageUtil.buildMessage(Protocol.U_MOUNT_ATT_UPDATE, attMsg);
		player.sendPbMessage(pAtt);
		
		//影响人物属性变更
		player.getMountInventory().updataProperty();
	}

}
