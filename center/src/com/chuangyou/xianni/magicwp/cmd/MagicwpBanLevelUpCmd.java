package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanLevelUpReqProto.MagicwpBanLevelUpReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanLevelUpRespProto.MagicwpBanLevelUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.magicwp.manager.MagicwpBanLevelManager;
import com.chuangyou.xianni.magicwp.manager.MagicwpManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_BAN_LEVEL_UP, desc = "禁制升级")
public class MagicwpBanLevelUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpBanLevelUpReqMsg req = MagicwpBanLevelUpReqMsg.parseFrom(packet.getBytes());
		MagicwpBanInfo ban = player.getMagicwpInventory().getBan(req.getBanId());
		
		if(!MagicwpBanLevelManager.useAllFragment(player, ban, packet.getCode())){
			return;
		}
		
		MagicwpBanLevelUpRespMsg.Builder msg = MagicwpBanLevelUpRespMsg.newBuilder();
		msg.setBanId(ban.getBanId());
		msg.setLevel(ban.getLevel());
		msg.setExp(ban.getExp());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_LEVEL_UP, msg);
		player.sendPbMessage(p);
		
		//法宝总属性改变
//		MagicwpManager.changeMagicwpAtt(roleId);
		//影响人物属性变更
		player.getMagicwpInventory().updataProperty();
	}

}
