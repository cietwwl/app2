package com.chuangyou.xianni.netty.handler.client;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ClientOutboundHandler extends ChannelOutboundHandlerAdapter {
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {

		// System.out.println("客户端监听服务器关闭连接");
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		// System.out.println("客户端监听连接关闭");
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		ctx.connect(remoteAddress, localAddress, promise);
		// System.out.println("客户端监听******************************");
	}

	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	}

}
