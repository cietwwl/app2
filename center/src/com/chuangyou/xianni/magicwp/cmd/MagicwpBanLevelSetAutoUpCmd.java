package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanLevelSetAutoUpReqProto.MagicwpBanLevelSetAutoUpReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanLevelSetAutoUpRespProto.MagicwpBanLevelSetAutoUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_BAN_LEVEL_SETAUTOUP, desc = "设置禁制自动升级")
public class MagicwpBanLevelSetAutoUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpBanLevelSetAutoUpReqMsg req = MagicwpBanLevelSetAutoUpReqMsg.parseFrom(packet.getBytes());
		
		MagicwpBanInfo ban = player.getMagicwpInventory().getBan(req.getBanId());
		
		if(ban == null || ban.getLevel() < 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_UnGet, packet.getCode());
			return;
		}
		
		ban.setAutoUpLevel((byte)req.getIsAutoUp());
		player.getMagicwpInventory().updateBan(ban);
		player.getMagicwpInventory().refreshAutoUpBanList();
		
		MagicwpBanLevelSetAutoUpRespMsg.Builder msg = MagicwpBanLevelSetAutoUpRespMsg.newBuilder();
		msg.setBanId(ban.getBanId());
		msg.setIsAutoUp(ban.getAutoUpLevel());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_LEVEL_SETAUTOUP, msg);
		player.sendPbMessage(p);
	}

}
