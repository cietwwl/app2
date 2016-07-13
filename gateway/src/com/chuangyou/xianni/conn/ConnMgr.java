package com.chuangyou.xianni.conn;

import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.NetConfigSet;
import com.chuangyou.common.util.NetConfigXml;
import com.chuangyou.xianni.netty.client.GateWay2CilentInboundHandler;
import com.chuangyou.xianni.netty.codec.PBMessageDecoder;
import com.chuangyou.xianni.netty.codec.PBMessageEncoder;
import com.chuangyou.xianni.netty.server.GateWay2CoreOutboundHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 客户端连接管理
 **/
public class ConnMgr {

	private int id;

	public int getId() {
		return id;
	}

	public boolean init() {
		return reload();
	}

	public boolean reload() {
		id = Config.getInt("server_id");
		// 连接到服务器端
		if (!ClientSet.init(id)) {
			return false;
		}
		ThreadServer server = new ThreadServer();
		server.start();
		return true;
	}

	public class ThreadServer extends Thread {
		public void run() {
			loadClientConns();
		}
	}

	/**
	 * 初始化客户端连接监听
	 * 
	 * @return
	 */
	private boolean loadClientConns() {
		int size = Runtime.getRuntime().availableProcessors() * 2 + 1;
		NetConfigXml netConfig = NetConfigSet.getNetConfigXml(Config.getInt("server_type"), Config.getInt("server_id"));
		EventLoopGroup bossGroup = new NioEventLoopGroup(size);
		EventLoopGroup workerGroup = new NioEventLoopGroup(size);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {

					ChannelHandlerAdapter decoder = new PBMessageDecoder();
					ChannelHandlerAdapter encoder = new PBMessageEncoder();
					ChannelInboundHandlerAdapter inBoundHandler = new GateWay2CilentInboundHandler();
					ChannelOutboundHandlerAdapter outBoundHandler = new GateWay2CoreOutboundHandler();

					ch.pipeline().addLast(decoder);
					ch.pipeline().addLast(inBoundHandler);
					ch.pipeline().addLast(outBoundHandler);
					ch.pipeline().addLast(encoder);
				}
			});
			b.option(ChannelOption.SO_BACKLOG, 20000);
			b.childOption(ChannelOption.SO_KEEPALIVE, true);
			b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(netConfig.getPort()).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		return true;
	}
}