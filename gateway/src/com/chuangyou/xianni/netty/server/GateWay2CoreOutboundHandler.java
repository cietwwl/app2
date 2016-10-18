package com.chuangyou.xianni.netty.server;

import com.chuangyou.xianni.netty.handler.server.ServerOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

//与客户端连接处理类
public class GateWay2CoreOutboundHandler extends ServerOutboundHandler {
	
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		
	}
}
