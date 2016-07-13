package com.chuangyou.xianni.mount.cmd;

import com.chuangyou.common.protobuf.pb.mount.MountFightChooseReqProto.MountFightChooseReqMsg;
import com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.entity.mount.MountSpecialGet;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MOUNT_FIGHT_CHOOSE, desc = "选择出战坐骑")
public class MountFightChooseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MountFightChooseReqMsg req = MountFightChooseReqMsg.parseFrom(packet.getBytes());
		
		MountInfo mount = player.getMountInventory().getMount();
		if(req.getMountId() == mount.getMountId()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.MOUNT_FIGHT_ALREADY, packet.getCode());
			return;
		}
		
		MountGradeCfg mountGrade = MountTemplateMgr.getMountTemps().get(req.getMountId());
		
		int specialGrade = SystemConfigTemplateMgr.getIntValue("mount.grade.specialMount");
		
		if(specialGrade == mountGrade.getGrade()){
			MountSpecialGet mountGet = player.getMountInventory().getMountSpecialMap().get(req.getMountId());
			if(mountGet == null){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Special_UnGet, packet.getCode());
				return;
			}
		}
		
		if(specialGrade != mountGrade.getGrade() && mountGrade.getGrade() > mount.getGrade()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Grade_UnGet, packet.getCode());
			return;
		}
		
		mount.setMountId(req.getMountId());
		player.getMountInventory().updateMount(mount);
		
		MountFightChooseRespMsg.Builder msg = MountFightChooseRespMsg.newBuilder();
		msg.setMountId(mount.getMountId());
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_FIGHT_CHOOSE, msg);
		player.sendPbMessage(p);
		
		player.getBasePlayer().updateMountId(mount.getMountId());
	}

}
