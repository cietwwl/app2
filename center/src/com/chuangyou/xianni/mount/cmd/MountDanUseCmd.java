package com.chuangyou.xianni.mount.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.mount.MountAttUpdateRespProto.MountAttUpdateRespMsg;
import com.chuangyou.common.protobuf.pb.mount.MountDanUseReqProto.MountDanUseReqMsg;
import com.chuangyou.common.protobuf.pb.mount.MountDanUseRespProto.MountDanUseRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MOUNT_DAN_USE, desc = "使用坐骑属性丹")
public class MountDanUseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MountDanUseReqMsg req = MountDanUseReqMsg.parseFrom(packet.getBytes());
		
		MountInfo mount = player.getMountInventory().getMount();
		int maxUseDan = SystemConfigTemplateMgr.getIntValue("mount.dan.maxUse");
		if(mount.getUseDanNum() + req.getUseNum() > maxUseDan){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_UseDan_max, packet.getCode());
			return;
		}
		
		int danItem = SystemConfigTemplateMgr.getIntValue("mount.dan.prop.Id");
		if(player.getBagInventory().getPlayerBagItemCount(danItem) < req.getUseNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(danItem, req.getUseNum(), BindType.ALL)) return;
		
		mount.setUseDanNum(mount.getUseDanNum() + req.getUseNum());
		player.getMountInventory().updateMount(mount);
		
		MountDanUseRespMsg.Builder msg = MountDanUseRespMsg.newBuilder();
		msg.setTotalUseNum(mount.getUseDanNum());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_DAN_USE, msg);
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
