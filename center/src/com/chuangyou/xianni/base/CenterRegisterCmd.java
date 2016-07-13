package com.chuangyou.xianni.base;

import com.chuangyou.xianni.command.ServerRegisterCmd;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.netty.LinkedClient;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REGISTER, desc = "注册连接到Center服务器")
public class CenterRegisterCmd extends ServerRegisterCmd {
	@Override
	public void execute(LinkedClient client) throws Exception {
		System.err.println("注册到center服务器");
		GatewayLinkedSet.addLinkedClient(client);
	}
}