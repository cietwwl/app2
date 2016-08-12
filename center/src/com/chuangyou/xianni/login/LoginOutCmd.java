package com.chuangyou.xianni.login;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.ChangeLineAction;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code = Protocol.C_PLAYER_OUT, desc = "用户登出")
public class LoginOutCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO 用户退出后各种业务处理

		ChangeLineAction action = new ChangeLineAction(player, null, false);
		action.getActionQueue().enqueue(action);
		WorldMgr.logout(player);
		
	}

}
