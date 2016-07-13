package com.chuangyou.xianni.mount.cmd;

import java.util.Date;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.mount.MountEquipBeanProto.MountEquipBeanMsg;
import com.chuangyou.common.protobuf.pb.mount.MountGetInfoRespProto.MountGetInfoRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MOUNT_GETINFO, desc = "获取玩家坐骑信息")
public class MountGetInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
//		MountGetInfoReqMsg req = MountGetInfoReqMsg.parseFrom(packet.getBytes());
		
		MountInfo mount = player.getMountInventory().getMount();
		
		Map<Integer, MountEquipInfo> roleMountEquip = player.getMountInventory().getMountEquip();
		
		MountGetInfoRespMsg.Builder msg = MountGetInfoRespMsg.newBuilder();
		msg.setMountId(mount.getMountId());
		msg.setLevel(mount.getLevel());
		
		long leftCd = mount.getUpLevCd() - (new Date()).getTime();
		if(leftCd < 0){
			leftCd = 0;
		}
		msg.setUpLevCd(leftCd);
		msg.setGrade(mount.getGrade());
		msg.setBless(mount.getBless());
		
		
		for(MountEquipInfo equipInfo:roleMountEquip.values()){
			MountEquipBeanMsg.Builder bean = MountEquipBeanMsg.newBuilder();
			bean.setEquipId(equipInfo.getEquipId());
			bean.setEquipLevel(equipInfo.getEquipLevel());
			msg.addEquips(bean);
		}
		
		msg.setUseDanNum(mount.getUseDanNum());
		msg.setWeaponGrade(mount.getWeaponGrade());
		msg.setWeaponBless(mount.getWeaponBless());
		
		Map<Integer, Integer> attMap = MountManager.computeMountAtt(player);
		for(Integer attType:attMap.keySet()){
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			bean.setAttType(attType);
			bean.setAttValue(attMap.get(attType));
			msg.addAtts(bean);
		}
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_GETINFO, msg);
		player.sendPbMessage(p);
	}

}
