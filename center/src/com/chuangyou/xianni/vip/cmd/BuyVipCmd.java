package com.chuangyou.xianni.vip.cmd;

import com.chuangyou.common.protobuf.pb.vip.ReqBuyVipMsgProto.ReqBuyVipMsg;
import com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg;
import com.chuangyou.common.protobuf.pb.vip.ResHandselVipMsgProto.ResHandselVipMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.vip.manager.VipManager;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code = Protocol.C_VIP_BUY, desc = "购买vip")
public class BuyVipCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqBuyVipMsg msg = ReqBuyVipMsg.parseFrom(packet.getBytes());
		int id = msg.getVipId();
		long playerId = msg.getPlayerId();
		long handselPlayerId = msg.getHandselPlayerId();
		boolean res = VipManager.buyVip(player, id, handselPlayerId);
		if (res == true) {
			ResBuyVipMsg.Builder resMsg = ResBuyVipMsg.newBuilder();
			resMsg.setVipTimeLimit(player.getBasePlayer().getPlayerInfo().getVipTimeLimit().getTime());
			resMsg.setVipInterimTimeLimit(player.getBasePlayer().getPlayerInfo().getVipInterimTimeLimit().getTime());

			PBMessage p = MessageUtil.buildMessage(Protocol.U_VIP_BUY, resMsg);
			player.sendPbMessage(p);
			System.out.println(p);
			if (handselPlayerId > 0) {
				GamePlayer handselPlayer = WorldMgr.getPlayerFromCache(handselPlayerId);
				if (handselPlayer != null) {
					ResHandselVipMsg.Builder resHandselVipMsg = ResHandselVipMsg.newBuilder();
					resHandselVipMsg.setName(handselPlayer.getBasePlayer().getPlayerInfo().getNickName());
					resHandselVipMsg.setVipId(id);
					resHandselVipMsg.setVipTimeLimit(handselPlayer.getBasePlayer().getPlayerInfo().getVipTimeLimit().getTime());
					resHandselVipMsg.setVipInterimTimeLimit(handselPlayer.getBasePlayer().getPlayerInfo().getVipInterimTimeLimit().getTime());
					PBMessage pb = MessageUtil.buildMessage(Protocol.U_VIP_HANDSEL, resHandselVipMsg);
					handselPlayer.sendPbMessage(pb);
				}
			}
		}
	}
}
