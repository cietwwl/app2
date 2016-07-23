package com.chuangyou.xianni.cross.command;

import com.chuangyou.xianni.command.ServerRegisterCmd;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.netty.LinkedClient;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.CR_REGISTER, desc = "注册连接到跨服服务器")
public class SceneRegisterCmd extends ServerRegisterCmd {
	@Override
	public void execute(LinkedClient client) throws Exception {
		System.err.println("注册到跨服务器服务器");
		GatewayLinkedSet.addLinkedClient(client);
	}
}