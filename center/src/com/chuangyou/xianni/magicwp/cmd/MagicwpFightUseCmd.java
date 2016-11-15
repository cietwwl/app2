package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpFightUseReqProto.MagicwpFightUseReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_FIGHT_USE, desc = "法宝出战选择")
public class MagicwpFightUseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpFightUseReqMsg req = MagicwpFightUseReqMsg.parseFrom(packet.getBytes());
		int magicwpId = req.getMagicwpId();
		
		if(player.getMagicwpInventory() != null){
			player.getMagicwpInventory().magicwpFight(magicwpId, true);
		}
	}

}
