package com.chuangyou.xianni.reload.cmd;

import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

@Cmd(code = Protocol.S_SCRIPT_RELOAD, desc = "重新加载脚本")
public class ReloadScriptCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ScriptManager.reLoad();
	}

}
