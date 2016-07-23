package com.chuangyou.xianni.user;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.conn.ClientSet;
import com.chuangyou.xianni.netty.AttributeKeySet;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

import io.netty.channel.Channel;

/**
 * <pre>
 * 玩家管理类
 * </pre>
 */
public class UserMgr {

	private static Map<Long, User>		onlineMap;		// 已登录用户
	private static Map<Long, Channel>	tempUserMap;	// 连接，尚未登录成功用户

	public static boolean init() {
		onlineMap = new ConcurrentHashMap<Long, User>();
		tempUserMap = new ConcurrentHashMap<Long, Channel>();
		return true;
	}

	/**
	 * 获取一个在线用户
	 */
	public static User getOnlineUser(long playerId) {
		return onlineMap.get(playerId);
	}

	/**
	 * 检测是否在线
	 */
	public static boolean checkOnline(long playerId) {
		if (getOnlineUser(playerId) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 加入在线列表
	 */
	public static void addOnline(Long playerId, Channel channel) {
		User user = new User(playerId, channel);
		onlineMap.put(playerId, user);
		channel.attr(AttributeKeySet.TEMP_USER_ID).set(0l);
		channel.attr(AttributeKeySet.PLAYER_ID).set(playerId);
	}

	/**
	 * 移除在线列表
	 * 
	 * @param userId
	 */
	public static void removeOnline(long playerId, Channel channel) {
		// 移除
		User user = getOnlineUser(playerId);
		if (user == null || channel != user.getChannel()) {
			Log.warn("客户端 当前用户已经从在线列表中清除了, userId : " + playerId);
			return;
		}
		onlineMap.remove(playerId);

		// 通知其他服务器
		PBMessage sencesReq = new PBMessage(Protocol.S_LOGIN_OUT);
		sencesReq.setPlayerId(playerId);
		ClientSet.routeSences(sencesReq);
		//



		// 关闭Socket
		if (user.getChannel() != null) {
			user.getChannel().close();
		}
	}

	/**
	 * 广播消息
	 * 
	 * @param message
	 */
	public static void broadcastMessage(PBMessage message) {
		
		ArrayList<User> users = new ArrayList<User>();
		synchronized(onlineMap){
			users.addAll(onlineMap.values());
		}

		for (User onlineUser : users) {
			if (onlineUser != null) {
				onlineUser.sendToUser(message);
			}
		}
	}

	/**
	 * <pre>
	 * 保存未经过验证的session对象
	 * </pre>
	 * 
	 * @param userId
	 *            玩家id
	 * @param key
	 *            玩家key,客户端的发送来的key值
	 * @param session
	 *            玩家session
	 */
	public static void addTempSession(long userId, String key, Channel channel) {
		channel.attr(AttributeKeySet.TEMP_USER_ID).set(userId);
		channel.attr(AttributeKeySet.LOGIN_KEY).set(key);
		tempUserMap.put(userId, channel);
	}

	/**
	 * <pre>
	 * 移除未经过验证的session对象
	 * </pre>
	 * 
	 * @param userId
	 *            玩家id
	 * @param key
	 *            玩家key,客户端发上来
	 */
	public static Channel removeTempSession(long userId) {
		Channel temp = tempUserMap.get(userId);
		if (temp != null && temp.attr(AttributeKeySet.TEMP_USER_ID).get() != 0) {
			return tempUserMap.remove(userId);
		}
		return null;
	}

	/**
	 * <pre>
	 * 移除未经过验证的session对象
	 * </pre>
	 * 
	 * @param userId
	 *            玩家id
	 * @param session
	 *            玩家session
	 */
	public static Channel removeTempSession(long userId, Channel channel) {
		Channel temp = tempUserMap.get(userId);
		if (temp == channel) {
			return tempUserMap.remove(userId);
		}
		return null;
	}

	/**
	 * 关闭gateway,将客户端连接全部断开
	 */
	public static void stop() {
		try {
			for (Channel temp : tempUserMap.values()) {
				temp.close();
			}

			for (Entry<Long, User> entry : onlineMap.entrySet()) {
				User utemp = entry.getValue();
				utemp.getChannel().close();
			}
		} catch (Exception e) {
			Log.error("Client set close client session exception.");
		}
	}
}
