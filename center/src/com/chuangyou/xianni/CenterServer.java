package com.chuangyou.xianni;

import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.FilterWordSet;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.NetConfigSet;
import com.chuangyou.common.util.NetConfigXml;
import com.chuangyou.common.util.ServerType;
import com.chuangyou.xianni.activity.template.ActivityTemplateMgr;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.artifact.template.ArtifactTemplateMgr;
import com.chuangyou.xianni.avatar.temlate.AvatarTempManager;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.campaign.CampaignTaskTempMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.common.template.PropertyFightingTemplateMgr;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.common.timer.TimerTaskMgr;
import com.chuangyou.xianni.drop.templete.DropTempleteMgr;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.fashion.template.FashionTemplateMgr;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.inverseBead.template.InverseBeadTemMgr;
import com.chuangyou.xianni.login.template.RoleConfigMgr;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.netty.codec.PBMessageDecoder;
import com.chuangyou.xianni.netty.codec.PBMessageEncoder;
import com.chuangyou.xianni.netty.server.CenterInboundHandler;
import com.chuangyou.xianni.netty.server.CenterOutboundHandler;
import com.chuangyou.xianni.netty.server.HttpServerInboundHandler;
import com.chuangyou.xianni.npcDialog.NpcInfoTemplateMgr;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.rank.template.RankTempMgr;
import com.chuangyou.xianni.reward.RewardManager;
import com.chuangyou.xianni.robot.RobotManager;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;
import com.chuangyou.xianni.sql.db.pool.DBPoolMgr;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;
import com.chuangyou.xianni.team.TeamTargetTempMgr;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.vip.templete.VipTemplateMgr;
import com.chuangyou.xianni.word.WorldMgr;

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
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class CenterServer extends BaseServer {

	private CenterServer() {

	}

	private static class InstanceHolder {
		private static final CenterServer INSTANCE = new CenterServer();
	}

	public static CenterServer getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public boolean start() throws Exception {
		if (!super.start()) {
			Log.error("BaseServer Start error");
			return false;
		}

		if (!initComponent(initDB(), "初始化数据库连接 ")) {
			return false;
		}
		if (!initComponent(WorldMgr.init(), "初始化世界管理")) {
			return false;
		}
		if (!initComponent(EntityIdBuilder.init(), "初始化服务器ID工厂")) {
			return false;
		}
		if (!initComponent(RoleConfigMgr.init(), "初始化角色模板数据")) {
			return false;
		}

		if (!initComponent(ItemManager.init(), "物品模板初始化")) {
			return false;
		}

		if (!initComponent(SystemConfigTemplateMgr.init(), "初始化公共字典配置表")) {
			return false;
		}

		if (!initComponent(LevelUpTempleteMgr.init(), "初始华升级配置表")) {
			return false;
		}

		if (!initComponent(MountTemplateMgr.init(), "初始化玩家坐骑模板数据")) {
			return false;
		}

		if (!initComponent(TimerTaskMgr.init(), "初始化定时更新管理器")) {
			return false;
		}
		if (!initComponent(MagicwpTemplateMgr.init(), "初始化法宝模板数据")) {
			return false;
		}
		if (!initComponent(PetTemplateMgr.init(), "初始化宠物模板数据")) {
			return false;
		}

		if (!initComponent(ShopTemplateMgr.init(), "初始化商店数据")) {
			return false;
		}
		if (!initComponent(MapProxyManager.init(), "初始化地图")) {
			return false;
		}
		if (!initComponent(NpcInfoTemplateMgr.init(), "初始化NPC模板数据")) {
			return false;
		}

		if (!initComponent(TaskTemplateMgr.init(), "初始化任务模板数据")) {
			return false;
		}

		if (!initComponent(FashionTemplateMgr.init(), "初始化时装模板数据")) {
			return false;
		}
		if (!initComponent(CampaignTaskTempMgr.init(), "初始化副本任务模板数据")) {
			return false;
		}

		if (!initComponent(CampaignTempMgr.init(), "初始化副本模板数据")) {
			return false;
		}
		
		if (!initComponent(DropTempleteMgr.init(), "初始化物品掉落模板数据")) {
			return false;
		}
		
		if (!initComponent(ScriptManager.init(), "=====初始化脚本======")) {
			return false;
		}
		if (!initComponent(SkillTempMgr.init(), "初始化技能模板数据")) {
			return false;
		}

		if (!initComponent(InverseBeadTemMgr.init(), "初始化天逆珠模板数据")) {
			return false;
		}
		if (!initComponent(MonsterInfoTemplateMgr.init(), "初始化怪物模板数据")) {
			return false;
		}

		if (!initComponent(PropertyFightingTemplateMgr.init(), "初始化属性战斗力配置")) {
			return false;
		}
		if (!initComponent(TeamTargetTempMgr.init(), "初始化组队目标表")) {
			return false;
		}

		if (!initComponent(EquipTemplateMgr.init(), "寝化装备模板数据")) {
			return false;
		}
		if (!initComponent(ArtifactTemplateMgr.init(), "神器模板数据")) {
			return false;
		}

		if (!initComponent(StateTemplateMgr.init(), "境界模板数据")) {
			return false;
		}

		if (!initComponent(FilterWordSet.loadFilterWord(Config.getValue("filter_word")), "初始化敏感字")) {
			return false;
		}

		if (!initComponent(SoulTemplateMgr.init(), "初始化魂幡模板数据")) {
			return false;
		}

		if (!initComponent(VipTemplateMgr.init(), "初始化vip模板数据")) {
			return false;
		}

		if (!initComponent(ActivityTemplateMgr.init(), "初始化日常活动模板数据")) {
			return false;
		}

		if(!initComponent(GuildTemplateMgr.init(), "帮派模板数据")){
			return false;
		}
		if (!initComponent(AvatarTempManager.init(), "加载分身模板数据")) {
			return false;
		}
		
		if (!initComponent(TruckTempMgr.init(), "初始化镖车模块")) {
			return false;
		}
			
		//管理器可能会用到模板数据，所以放在所有模板数据之后初始化
		if(!initComponent(RankServerManager.getInstance().init(),"初始化排行榜数据")){
			return false;
		}
		if (!initComponent(RankTempMgr.init(), "排行榜模板数据初始化")) {
			return false;
		}
		if (!initComponent(RobotManager.init(), "初始化机器模块")) {
			return false;
		}

		if (!initComponent(RewardManager.init(), "奖励模块")) {
			return false;
		}
		
		if(!initComponent(GuildManager.getIns().init(), "初始化帮派模块")){
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {

		long time = System.currentTimeMillis();
		if (args.length <= 0) {
			System.err.println("请输入配置文件地址路径...启用默认配置文件路径");
			configPath = "config.properties";
		} else {
			configPath = args[0];
		}
		CenterServer server = CenterServer.getInstance();
		if (server.start() == false) {
			throw new Exception("服务器启动失败");
		}

		ThreadServer scoket = server.new ThreadServer();
		scoket.start();
		HttpServer http = server.new HttpServer();
		http.start();
		System.err.println("启动耗时: " + (System.currentTimeMillis() - time));
		Log.error("center启动成功!");
	}

	public boolean initDB() {
		NetConfigXml mainXml = NetConfigSet.getNetConfigXml(ServerType.MAINDB, 401);
		NetConfigXml logXml = NetConfigSet.getNetConfigXml(ServerType.LOGDB, 402);
		boolean result = DBPoolMgr.init(mainXml, logXml, 30, 5, 30, 5);
		System.out.println("prepare mysql pool result:" + result);
		return result;
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
		NetConfigXml netConfig = NetConfigSet.getNetConfigXml(ServerType.CENTER, Config.getInt("server_id"));
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
					ChannelInboundHandlerAdapter inBoundHandler = new CenterInboundHandler();
					ChannelOutboundHandlerAdapter outBoundHandler = new CenterOutboundHandler();

					ch.pipeline().addLast(decoder);
					ch.pipeline().addLast(inBoundHandler);
					ch.pipeline().addLast(outBoundHandler);
					ch.pipeline().addLast(encoder);
				}
			});
			b.option(ChannelOption.SO_BACKLOG, 128);
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

	public class HttpServer extends Thread {
		public void run() {
			try {
				initHttpServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void initHttpServer() throws Exception {
		NetConfigXml netConfig = NetConfigSet.getNetConfigXml(ServerType.ADMIN_SERVER, 501);
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					// server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
					ch.pipeline().addLast(new HttpResponseEncoder());
					// server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
					ch.pipeline().addLast(new HttpRequestDecoder());
					ch.pipeline().addLast(new HttpServerInboundHandler());
				}
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture f = b.bind(netConfig.getPort()).sync();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
