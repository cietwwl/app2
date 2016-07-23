package com.chuangyou.xianni.conn;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.chuangyou.common.protobuf.pb.LinkedLoadMsgProto.LinkedLoadMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.NetConfigSet;
import com.chuangyou.common.util.NetConfigXml;
import com.chuangyou.common.util.ServerType;
import com.chuangyou.xianni.netty.LinkedClient;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.user.User;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

/**
 * <pre>
 * gateway连接其他服务器(center, sence, cross)的连接管理类
 * </pre>
 */
public class ClientSet {

	private static LinkedClient			self			= null;
	private static List<LinkedClient>	centerClients	= new Vector<LinkedClient>();	// 中心业务
	private static List<LinkedClient>	sceneClients	= new Vector<LinkedClient>();	// 副本服务器
	private static List<LinkedClient>	crossClients	= new Vector<LinkedClient>();	// 副本服务器

	private ClientSet() {

	}

	/**
	 * 初始化,负责连接到其他的服务器
	 */
	public static boolean init(int gatewayId) {
		try {
			initSelf(gatewayId);
			connect(ServerType.CENTER, "center");
			connect(ServerType.SCENE, "sence");
			connect(ServerType.CROSS, "cross");
			return true;
		} catch (Exception e) {
			Log.error("initialization connector has exception:", e);
		}
		return false;
	}

	private static void initSelf(int gatewayId) {
		NetConfigXml netConfigXml = NetConfigSet.getNetConfigXml(ServerType.GATEWAY, gatewayId);
		String name = netConfigXml.getName();
		String address = netConfigXml.getAddress();
		int port = netConfigXml.getPort();
		self = new GateWayClient(ServerType.GATEWAY, gatewayId, name, address, port, 0);
	}

	private static void connect(int serverType, String handName) {
		Collection<NetConfigXml> netConfigXmls = NetConfigSet.getNetConfigXml(serverType);
		for (NetConfigXml netConfigXml : netConfigXmls) {
			connect(serverType, netConfigXml, handName, 0);
		}
	}

	private static void connect(int serverType, NetConfigXml netConfigXml, String handName, int index) {
		if (netConfigXml == null) {
			Log.error("serverType : " + serverType + " , handName ：" + handName);
			return;
		}
		int serverId = netConfigXml.getId();
		String name = netConfigXml.getName();
		String address = netConfigXml.getAddress();
		int port = netConfigXml.getPort();

		LinkedClient client = new GateWayClient(serverType, serverId, name, address, port, index);
		addLinkedClient(serverType, client);
		if (!client.connect()) {
			ClientTryer.getInstance().ctry(client, 10, -1);
		} else {
			sendRegisterMsg(client);
		}
	}

	private static void addLinkedClient(int serverType, LinkedClient client) {
		if (serverType == ServerType.CENTER) {
			centerClients.add(client);
		} else if (serverType == ServerType.SCENE) {
			sceneClients.add(client);
		} else if (serverType == ServerType.CROSS) {
			crossClients.add(client);
		}

	}

	/**
	 * 获取当前管道所在的客户端连接
	 * 
	 * @param channel
	 */
	public static LinkedClient getLinkedClient(Channel channel) {
		for (LinkedClient client : centerClients) {
			if (client.getChannel() != null && client.getChannel().id() == channel.id()) {
				return client;
			}
		}
		for (LinkedClient client : sceneClients) {
			if (client.getChannel() != null && client.getChannel().id() == channel.id()) {
				return client;
			}
		}

		for (LinkedClient client : crossClients) {
			if (client.getChannel() != null && client.getChannel().id() == channel.id()) {
				return client;
			}
		}
		return null;
	}

	/**
	 * 删除连接
	 * 
	 * @param client
	 * @param type
	 */
	public static void removeServerClient(LinkedClient client) {
		switch (client.getType()) {
			case ServerType.CENTER:
				centerClients.remove(client);
				break;
			case ServerType.SCENE:
				sceneClients.remove(client);
				break;
			case ServerType.CROSS:
				crossClients.remove(client);
				break;

		}
	}

	public static void sendRegisterMsg(LinkedClient client) {
		short code = 0;
		int serverType = client.getType();
		switch (serverType) {
			case ServerType.CENTER:
				code = Protocol.C_REGISTER; // TODO
				break;
			case ServerType.SCENE:
				code = Protocol.S_REGISTER; // TODO
				break;
			case ServerType.CROSS:
				code = Protocol.CR_REGISTER; // TODO
				break;
			default:
				break;
		}
		if (code == 0) {
			return;
		}
		LinkedLoadMsg msg = self.writeProto(serverType);
		PBMessage pkg = MessageUtil.buildMessage(code, msg);
		client.send(pkg);
	}

	/**
	 * 转发数据包到客户端
	 * 
	 * @param packet
	 */
	public static void routeClient(PBMessage packet) {
		User user = UserMgr.getOnlineUser(packet.getPlayerId());
		if (user != null) {
			user.sendToUser(packet);
		} else {
			Log.debug("找不到对应的用户, userId: " + packet.getPlayerId() + ", code: " + packet.getCode());
		}
	}

	/**
	 * 转发数据包到center服务器
	 * 
	 * @param packet
	 */
	public static void routeCenter(PBMessage packet) {
		LinkedClient client = centerClients.get(0);
		route(client, packet);
	}

	/**
	 * 转发数据包到sence服务器
	 * 
	 * @param packet
	 */
	public static void routeSences(PBMessage packet) {
		LinkedClient client = null;
		if (client == null) {
			client = sceneClients.get(0);
		}
		route(client, packet);
	}

	/**
	 * 转发数据包到sence服务器
	 * 
	 * @param packet
	 */
	public static void routeCross(PBMessage packet) {
		LinkedClient client = null;
		if (client == null) {
			client = crossClients.get(0);
		}
		route(client, packet);
	}

	/**
	 * 发送数据包
	 * 
	 * @param client
	 * @param packet
	 */
	private static void route(LinkedClient client, PBMessage packet) {
		if (client != null) {
			client.send(packet);
		}
	}

	private static String genKey(LinkedClient client) {
		return client.getId() + "_" + client.getName() + "_" + client.getAddress() + "_" + client.getPort();
	}
}
