package com.chuangyou.xianni;

import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.NetConfigSet;
import com.chuangyou.common.util.NetConfigXml;
import com.chuangyou.common.util.ServerType;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.campaign.CampaignTaskTempMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.common.timer.TimerTaskMgr;
import com.chuangyou.xianni.drop.templete.DropTempleteMgr;
import com.chuangyou.xianni.inverseBead.template.InverseBeadMonsterTemMgr;
import com.chuangyou.xianni.mount.MountTempleteMgr;
import com.chuangyou.xianni.netty.codec.PBMessageDecoder;
import com.chuangyou.xianni.netty.codec.PBMessageEncoder;
import com.chuangyou.xianni.netty.server.SceneInboundHandler;
import com.chuangyou.xianni.netty.server.SceneOutboundHandler;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.role.template.AiConfigTemplateMgr;
import com.chuangyou.xianni.role.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.role.template.NpcInfoTemplateMgr;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.sql.db.pool.DBPoolMgr;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.navi.NavigationManager;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

public class CrossServer extends BaseServer {

	private static class InstanceHolder {
		private static final CrossServer INSTANCE = new CrossServer();
	}

	public static CrossServer getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public static void main(String[] args) throws Exception {
		long time = System.currentTimeMillis();
		if (args.length <= 0) {
			System.err.println("请输入配置文件地址路径...启用默认配置文件路径");
			configPath = "crossConfig.properties";
		} else {
			configPath = args[0];
		}
		CrossServer server = CrossServer.getInstance();
		if (server.start() == false) {
			throw new Exception("服务器启动失败");
		}

		ThreadServer socket = server.new ThreadServer();
		socket.start();
		System.err.println("启动耗时: " + (System.currentTimeMillis() - time));
		Log.error("server启动成功!");
	}

	public boolean start() throws Exception {
		if (!super.start()) {
			Log.error("BaseServer Start error");
			return false;
		}

		if (!initComponent(initDB(), "初始化数据库连接 ")) {
			return false;
		}
		if (!initComponent(BattleTempMgr.init(), "")) {
			return false;
		}

		if (!initComponent(FieldTemplateMgr.init(), "初始化地图模板数据")) {
			return false;
		}
		if (!initComponent(SpawnTemplateMgr.init(), "初始化地图对象生成模板数据")) {
			return false;
		}
		if (!initComponent(MonsterInfoTemplateMgr.init(), "初始化怪物模板数据")) {
			return false;
		}
		if (!initComponent(AiConfigTemplateMgr.init(), "初始化ai数据")) {
			return false;
		}
		if (!initComponent(InverseBeadMonsterTemMgr.init(), "初始化天逆珠")) {
			return false;
		}
		if (!initComponent(NpcInfoTemplateMgr.init(), "初始化NPC/转场点模板数据")) {
			return false;
		}

		if (!initComponent(initField(), "初始化场景配置数据")) {
			System.err.println("[ERROR]初始化场景失败！");
			return false;
		}

		if (!initComponent(DropTempleteMgr.init(), "掉落物模板数据")) {
			return false;
		}

		if (!initComponent(SystemConfigTemplateMgr.init(), "初始化公共字典配置表")) {
			return false;
		}

		if (!initComponent(ScriptManager.init(), "=====初始化脚本======")) {
			return false;
		}

		if (!initComponent(CampaignTaskTempMgr.init(), "初始化副本任务信息")) {
			return false;
		}

		if (!initComponent(CampaignTempMgr.init(), "初始化副本模板信息")) {
			return false;
		}

		if (!initComponent(NavigationManager.init(), "寻路组件初始化完成")) {
			return false;
		}

		if (!initComponent(TimerTaskMgr.init(), "初始化定时更新管理器")) {
			return false;
		}

		if (!initComponent(MountTempleteMgr.init(), "坐骑模板初始化")) {
			return false;
		}

		return true;
	}

	/**
	 * 初始化DB
	 * 
	 * @return
	 */
	public boolean initDB() {
		NetConfigXml mainXml = NetConfigSet.getNetConfigXml(ServerType.MAINDB, 401);
		NetConfigXml logXml = NetConfigSet.getNetConfigXml(ServerType.LOGDB, 402);
		boolean result = DBPoolMgr.init(mainXml, logXml, 30, 5, 30, 5);
		System.out.println("prepare mysql pool result:" + result);
		return result;
	}

	/**
	 * 初始化地图
	 * 
	 * @return
	 */
	public boolean initField() {
		return FieldMgr.getIns().initilize();
	}

	public class ThreadServer extends Thread {
		public void run() {
			try {
				initNetty();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// netty启动后会阻塞线程直至关闭，此处应在线程中启动
	public boolean initNetty() throws Exception {
		int size = Runtime.getRuntime().availableProcessors() * 2 + 1;
		NetConfigXml netConfig = NetConfigSet.getNetConfigXml(ServerType.CROSS, Config.getInt("server_id"));
		EventLoopGroup bossGroup = new NioEventLoopGroup(size);
		EventLoopGroup workerGroup = new NioEventLoopGroup(size);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {

					ByteToMessageDecoder decoder = new PBMessageDecoder();
					MessageToByteEncoder<PBMessage> encoder = new PBMessageEncoder();
					ChannelInboundHandlerAdapter inBoundHandler = new SceneInboundHandler();
					ChannelOutboundHandlerAdapter outBoundHandler = new SceneOutboundHandler();

					ch.pipeline().addLast(decoder);
					ch.pipeline().addLast(inBoundHandler);
					ch.pipeline().addLast(outBoundHandler);
					ch.pipeline().addLast(encoder);
				}
			});
			b.option(ChannelOption.SO_BACKLOG, 1280);
			b.childOption(ChannelOption.SO_KEEPALIVE, true);
			b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(netConfig.getPort()).sync();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		return false;
	}
}
