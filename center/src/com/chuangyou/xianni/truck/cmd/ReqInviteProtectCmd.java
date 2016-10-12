package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.ReqInviteProtectProto.ReqInviteProtect;
import com.chuangyou.common.protobuf.pb.truck.RespInviteToPlayerProto.RespInviteToPlayer;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.helper.InvitePlayerHelper;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 向请求某人护镖
 * @author wkghost
 *
 */
@Cmd(code = Protocol.C_TRUCK_INVITEPROTECT, desc = "向请求某人护镖")
public class ReqInviteProtectCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqInviteProtect inviteProtectMsg = ReqInviteProtect.parseFrom(packet.getBytes());
		GamePlayer inviteProtectPlayer = WorldMgr.getPlayer(inviteProtectMsg.getProtectorId());
		if(InvitePlayerHelper.checkPlayerStatu(inviteProtectPlayer))
		{
			RespInviteToPlayer.Builder inviteToPlayer = RespInviteToPlayer.newBuilder();
			inviteToPlayer.setTruckId(inviteProtectMsg.getTruckId());
			inviteToPlayer.setBeprotectorId(player.getPlayerId());
			inviteToPlayer.setNickName(player.getNickName());
			inviteToPlayer.setTypetruck(inviteProtectMsg.getTrucktype());
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_INVITE, inviteToPlayer);
			inviteProtectPlayer.sendPbMessage(pkg);
		}
	}

}
