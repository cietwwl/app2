package com.chuangyou.xianni.conn;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.netty.LinkedClient;
import com.chuangyou.xianni.netty.codec.PBMessageDecoder;
import com.chuangyou.xianni.netty.codec.PBMessageEncoder;
import com.chuangyou.xianni.netty.server.GateWay2CoreInboundHandler;
import com.chuangyou.xianni.netty.server.GateWay2CoreOutboundHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class GateWayClient extends LinkedClient {

	public GateWayClient(int type, int id, String name, String address, int port, int index) {
		super(type, id, name, address, port, index);
	}
	
	
	/**
	 * 连接
	 */
	public synchronized boolean connect() {
		try {
			load = 0;
			EventLoopGroup workerGroup = new NioEventLoopGroup(5);

			try {
				Bootstrap b = new Bootstrap().group(workerGroup).channel(NioSocketChannel.class)
						.option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
							@Override
							public void initChannel(SocketChannel ch) throws Exception {
								ChannelHandlerAdapter decoder = new PBMessageDecoder();
								ChannelHandlerAdapter encoder = new PBMessageEncoder();
								ChannelHandlerAdapter inboundHandler = new GateWay2CoreInboundHandler();
								ChannelHandlerAdapter outboundHandler = new GateWay2CoreOutboundHandler();

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
				// workerGroup.shutdownGracefully();
			}
			connTimes = 0;
			return true;
		} catch (Exception e) {
			Log.error("connect to address " + address + ":" + port + " fail.", e);
			connTimes++;
			return false;
		}
	}
}
