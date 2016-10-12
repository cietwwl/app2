package com.chuangyou.xianni.guild.cmd;

import com.chuangyou.xianni.guild.action.GuildJobSeizeResultAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_GUILD_SEIZE_RESULT, desc = "夺权挑战结果")
public class GuildSeizeResultCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		GamePlayer player = WorldMgr.getPlayer(packet.getPlayerId());
		if(player == null){
			return;
		}
		
		GuildJobSeizeResultAction action = new GuildJobSeizeResultAction(player, packet);
		action.getActionQueue().enqueue(action);
	}

}
