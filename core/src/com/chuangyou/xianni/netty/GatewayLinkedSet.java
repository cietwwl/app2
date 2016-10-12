package com.chuangyou.xianni.netty;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.BaseServer;
import com.chuangyou.xianni.proto.PBMessage;
import io.netty.channel.Channel;

/**
 * 管理网关服务器的MINA连接
 * 
 */
public class GatewayLinkedSet {

	private static Map<Integer, LinkedClient>	clients			= new ConcurrentHashMap<Integer, LinkedClient>();
	private static Map<String, LinkedClient>	crossClients	= new ConcurrentHashMap<String, LinkedClient>();

	private GatewayLinkedSet() {

	}

	private static String genKey(LinkedClient client) {
		return client.getName() + "_" + client.getId() + "_" + client.getIndex();
	}

	/**
	 * 添加到集合管理器
	 * 
	 * @param linkedClient
	 * @return
	 */
	public static LinkedClient addLinkedClient(LinkedClient client) {
		Log.info("连接到服务器, linkedclient : " + client.toString());
		if (BaseServer.isCross) {
			crossClients.put(genKey(client), client);
		}
		return clients.put(client.getId(), client);
	}

	/**
	 * 删除指定的连接
	 * 
	 * @param linkedClient
	 * @return
	 */
	public static LinkedClient removeLinkedClient(Channel channel) {
		LinkedClient client = getLinkedClient(channel);
		if (client == null) {
			return null;
		}
		if (BaseServer.isCross) {
			crossClients.remove((genKey(client)));
		}
		return clients.remove(client.getId());
	}

	public static LinkedClient getLinkedClient(Channel channel) {
		int id = channel.attr(AttributeKeySet.SERVER_ID).get();
		if (BaseServer.isCross) {
			return crossClients.get(id);
		} else {
			return clients.get(id);
		}
	}

	/**
	 * 发送到所有网关连接
	 * 
	 * @param packet
	 */
	public static void sendAll(PBMessage packet) {
		for (Entry<Integer, LinkedClient> linkedEntry : clients.entrySet()) {
			LinkedClient linked = linkedEntry.getValue();
			if (linked != null) {
				linked.send(packet);
			}
		}
	}

	/**
	 * 任选一个网关
	 */
	public static void send2Server(PBMessage packet) {
		for (Entry<Integer, LinkedClient> linkedEntry : clients.entrySet()) {
			LinkedClient linked = linkedEntry.getValue();
			if (linked != null) {
				linked.send(packet);
				return;
			}
		}
	}

	public static void sendCrossAll(PBMessage packet) {
		for (Entry<String, LinkedClient> linkedEntry : crossClients.entrySet()) {
			LinkedClient linked = linkedEntry.getValue();
			if (linked != null) {
				linked.send(packet);
			}
		}
	}
}
