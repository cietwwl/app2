package com.chuangyou.xianni.netty;

import java.util.concurrent.atomic.AtomicBoolean;

import com.chuangyou.common.protobuf.pb.LinkedLoadMsgProto.LinkedLoadMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.netty.codec.PBMessageDecoder;
import com.chuangyou.xianni.netty.codec.PBMessageEncoder;
import com.chuangyou.xianni.netty.handler.client.ClientInboundHandler;
import com.chuangyou.xianni.netty.handler.client.ClientOutboundHandler;
import com.chuangyou.xianni.proto.PBMessage;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 服务器和网关服务器之间的连接
 */
public class LinkedClient {

	protected int			id;								// 网关ID
	protected int			type;							// 连接类型
	protected String		name;
	protected String		address;
	protected int			port;
	protected int			load;
	protected long			lastUpdate;
	protected boolean		isActive;
	protected boolean		isTry;
	protected int			connTimes;						// 连接次数
	protected int			index;
	protected Channel		channel		= null;				// 连接会话
	protected int			onLineCount;
	protected AtomicBoolean	isConnect;
	public static final int	POLL_SIZE	= Short.MAX_VALUE;

	public LinkedClient(int type, int id, String name, String address, int port, int index) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.address = address;
		this.port = port;
		this.load = 0;
		this.lastUpdate = 0;
		this.isTry = true;
		this.connTimes = 0;
		this.index = index;
		this.isConnect = new AtomicBoolean(true);
	}

	public boolean isConnect() {
		return isConnect.get();
	}

	public void ping() {
		isConnect.set(false);
	}

	public void ok() {
		isConnect.set(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
		lastUpdate = System.currentTimeMillis();
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isTry() {
		return isTry;
	}

	public void setTry(boolean isTry) {
		this.isTry = isTry;
	}

	public int getConnTimes() {
		return connTimes;
	}

	public void setConnTimes(int connTimes) {
		this.connTimes = connTimes;
	}

	public void resetConnTimes() {
		connTimes = 0;
	}

	public int getIndex() {
		return index;
	}

	public int getOnLineCount() {
		return onLineCount;
	}

	public void setOnLineCount(int onLineCount) {
		this.onLineCount = onLineCount;
	}

	public boolean isLinkedClient(int serverId, String name, String address, int port) {
		return this.id == serverId && this.name.equals(name) && this.address.equals(address) && this.port == port;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id : ").append(id);
		sb.append(", name : ").append(name);
		sb.append(", index : ").append(index);
		sb.append(", address : ").append(address);
		sb.append(", port : ").append(port);
		sb.append(", load : ").append(load);
		sb.append(", isActive : ").append(isActive);
		sb.append(", connTimes : ").append(connTimes);
		return sb.toString();
	}

	/**
	 * 是否连接
	 * 
	 * @return
	 */
	public synchronized boolean isConnected() {
		if (channel != null && channel.isActive()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 连接
	 */
	public synchronized boolean connect() {
		EventLoopGroup workerGroup = new NioEventLoopGroup(5);
		try {
			load = 0;
			try {
				Bootstrap b = new Bootstrap().group(workerGroup).channel(NioSocketChannel.class)
						.option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
							@Override
							public void initChannel(SocketChannel ch) throws Exception {
								ChannelHandlerAdapter decoder = new PBMessageDecoder();
								ChannelHandlerAdapter encoder = new PBMessageEncoder();
								ChannelHandlerAdapter inboundHandler = new ClientInboundHandler();
								ChannelHandlerAdapter outboundHandler = new ClientOutboundHandler();

								ch.pipeline().addLast(decoder);
								ch.pipeline().addLast(inboundHandler);
								ch.pipeline().addLast(outboundHandler);
								ch.pipeline().addLast(encoder);
							}
						});

				// 启动客户端
				ChannelFuture f = b.connect(address, port).sync(); // (5)
				this.channel = f.channel();
				// 等待连接关闭
				// f.channel().closeFuture().sync();
			} finally {
				//workerGroup.shutdownGracefully();
			}
			connTimes = 0;
			return true;
		} catch (Exception e) {
			Log.error("connect to address " + address + ":" + port + " fail.", e);
			connTimes++;
			workerGroup.shutdownGracefully();
			return false;
		}
	}

	public synchronized void disConnect() {
		if (isConnected()) {
			channel.close();
			channel = null;
		}
	}

	/**
	 * 发送数据包
	 * 
	 * @param packet
	 */
	public void send(PBMessage packet) {
		if (channel == null || channel.isActive() == false) {
			return;
		}
		if (packet == null) {
			return;
		}
		try {
			channel.write(packet);
			channel.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedLoadMsg writeProto(int serverType) {
		LinkedLoadMsg.Builder msg = LinkedLoadMsg.newBuilder();
		msg.setAddress(getAddress());
		msg.setConnTimes(getConnTimes());
		msg.setIndex(getIndex());
		msg.setLoad(getLoad());
		msg.setPort(getPort());
		msg.setServerId(getId());
		msg.setServerName(getName());
		msg.setType(serverType);
		return msg.build();
	}
}
