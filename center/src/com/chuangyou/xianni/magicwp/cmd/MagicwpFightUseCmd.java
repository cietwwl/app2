package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpFightUseReqProto.MagicwpFightUseReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpFightUseRespProto.MagicwpFightUseRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_FIGHT_USE, desc = "法宝出战选择")
public class MagicwpFightUseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpFightUseReqMsg req = MagicwpFightUseReqMsg.parseFrom(packet.getBytes());
		MagicwpAtt magicwpAtt = player.getMagicwpInventory().getMagicwpAtt();
		MagicwpInfo magicwpInfo = player.getMagicwpInventory().getMagicwpInfo(req.getMagicwpId());
		if(magicwpInfo == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UnGet, packet.getCode());
			return;
		}
		
		magicwpAtt.setCurMagicwpId(req.getMagicwpId());
		player.getMagicwpInventory().updateMagicwpAtt(magicwpAtt);
		
		MagicwpFightUseRespMsg.Builder msg = MagicwpFightUseRespMsg.newBuilder();
		msg.setMagicwpId(magicwpAtt.getCurMagicwpId());
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_FIGHT_USE, msg);
		player.sendPbMessage(p);
		
		player.getBasePlayer().updateMagicwpId(magicwpAtt.getCurMagicwpId());
	}

}
