package com.chuangyou.xianni.vip.cmd;

import com.chuangyou.common.protobuf.pb.vip.ReqBuyVipMsgProto.ReqBuyVipMsg;
import com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.vip.manager.VipManager;

@Cmd(code = Protocol.C_VIP_BUY, desc = "购买vip")
public class BuyVipCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqBuyVipMsg msg = ReqBuyVipMsg.parseFrom(packet.getBytes());
		int id = msg.getVipId();
		boolean res = VipManager.buyVip(player, id);
		if (res == true) {
			ResBuyVipMsg.Builder resMsg = ResBuyVipMsg.newBuilder();
			resMsg.setVipTimeLimit(player.getBasePlayer().getPlayerInfo().getVipTimeLimit().getTime());
			resMsg.setVipInterimTimeLimit(player.getBasePlayer().getPlayerInfo().getVipInterimTimeLimit().getTime());

			PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_SKILL_OPEN, msg);
			player.sendPbMessage(p);
		}
	}
}
