package com.chuangyou.xianni.command;

import com.chuangyou.common.protobuf.pb.LinkedLoadMsgProto.LinkedLoadMsg;
import com.chuangyou.common.util.ServerType;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.netty.LinkedClient;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

/**
 * 服务器注册CMD 
 * @author laofan
 *
 */
public abstract class ServerRegisterCmd implements Command {
	@Override
	public void execute(Channel channel, PBMessage message) throws Exception {
		LinkedLoadMsg msg = LinkedLoadMsg.parseFrom(message.getBytes());
		int serverId = msg.getServerId();
		String serverName = msg.getServerName();
		String address = msg.getAddress();
		int port = msg.getPort();
		int load = msg.getLoad();
		int index = msg.getIndex();

		LinkedClient client = new LinkedClient(ServerType.GATEWAY, serverId, serverName, address, port, index);
		client.setLoad(load);
		client.setChannel(channel);
		GatewayLinkedSet.addLinkedClient(client);
		execute(client);
	}

	public void execute(LinkedClient client) throws Exception {
			
	}

}
