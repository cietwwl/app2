package com.chuangyou.xianni.guild.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.guild.action.GuildCreateAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_GUILD_CREATE, desc = "门派创建")
public class GuildCreateCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		GuildCreateAction action = new GuildCreateAction(player, packet);
		action.getActionQueue().enqueue(action);
	}

}
