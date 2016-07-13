package com.chuangyou.xianni.netty.server;

import java.net.SocketAddress;

import com.chuangyou.xianni.netty.handler.server.ServerOutboundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class CenterOutboundHandler extends ServerOutboundHandler {
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		ctx.close(promise);
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		ctx.connect(remoteAddress, localAddress, promise);
	}

	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	}

	/**
	 * Do nothing by default, sub-classes may override this method.
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().close();
		ctx.close();
	}

}
