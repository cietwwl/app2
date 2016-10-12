package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqInviteGuildProtProto.InnerReqInviteGuildProt;
import com.chuangyou.common.protobuf.pb.truck.RespInviteToPlayerProto.RespInviteToPlayer;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckInventory;
import com.chuangyou.xianni.truck.helper.InvitePlayerHelper;
import com.chuangyou.xianni.word.WorldMgr;


@Cmd(code = Protocol.C_TRUCK_GUILD_PROT_INVITE, desc = "请求帮派成员护镖")
public class ReqGuildTruckProtInviteCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqInviteGuildProt msg = InnerReqInviteGuildProt.parseFrom(packet.getBytes());
		Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
		if(guild == null) return;
		for(long id : guild.getMemberIds())
		{
			if(id == player.getPlayerId()) continue;
			GamePlayer inviteProtectPlayer = WorldMgr.getPlayer(id);
			if(InvitePlayerHelper.checkPlayerStatu(inviteProtectPlayer))
			{
				RespInviteToPlayer.Builder inviteToPlayer = RespInviteToPlayer.newBuilder();
				inviteToPlayer.setTruckId(msg.getTruckId());
				inviteToPlayer.setBeprotectorId(player.getPlayerId());
				inviteToPlayer.setNickName(player.getNickName());
				inviteToPlayer.setTypetruck(TruckInventory.TYPE_G);	//帮派镖车
				PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_INVITE, inviteToPlayer);
				inviteProtectPlayer.sendPbMessage(pkg);
			}
		}
	}
}
