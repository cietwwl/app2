package com.chuangyou.xianni.user;

import com.chuangyou.xianni.netty.AttributeKeySet;
import com.chuangyou.xianni.netty.LinkedClient;
import com.chuangyou.xianni.proto.PBMessage;
import io.netty.channel.Channel;

/**
 * <pre>
 * 玩家对象
 * </pre>
 */
public class User {

	private final long		userId;
	private Channel			channel;
	private long			lastSyncTime;
	private int				acceleratCount;
	private LinkedClient	crossClient;
	private String			serverName;

	public User(final long userId, Channel channel) {
		this.userId = userId;
		this.channel = channel;
		if (channel != null) {
			this.channel.attr(AttributeKeySet.USER_ID).set(userId);
		}
		lastSyncTime = 0;
		acceleratCount = 0;
	}

	public long getUserId() {
		return userId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public long getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(long lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public int getAcceleratCount() {
		return acceleratCount;
	}

	public void setAcceleratCount(int acceleratCount) {
		this.acceleratCount = acceleratCount;
	}

	public void addAcceleratCount() {
		acceleratCount++;
	}

	public LinkedClient getCrossClient() {
		return crossClient;
	}

	public void setCrossClient(LinkedClient crossClient) {
		this.crossClient = crossClient;
	}

	public void sendToUser(PBMessage packet) {
		if (packet == null) {
			return;
		}
		if (channel == null || channel.isActive() == false) {
			System.out.println(
					"userId :" + this.userId + "  连接断开" + channel.id().toString() + "  code :" + packet.getCode());
			return;
		} 
		try {
			channel.write(packet);
			channel.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
}
