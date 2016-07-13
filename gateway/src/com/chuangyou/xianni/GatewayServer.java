package com.chuangyou.xianni;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.conn.ConnMgr;
import com.chuangyou.xianni.user.UserMgr;

public class GatewayServer extends BaseServer {

	private static GatewayServer	gatewayServer;
	private ConnMgr					connMgr;

	public GatewayServer() {

	}

	public ConnMgr getConnMgr() {
		return connMgr;
	}

	@Override
	public boolean start() throws Exception {
		if (!super.start()) {
			return false;
		}

		if (!initComponent(UserMgr.init(), "启动user管理模块")) {
			return false;
		}

		if (!initConnector()) {
			return false;
		}
		// 设置状态
		setTerminate(false);
		return true;
	}

	private boolean initConnector() {
		connMgr = new ConnMgr();
		if (!initComponent(connMgr.init(), "启动gateway连接Server socket服务")) {
			return false;
		}
		return true;
	}

	public static GatewayServer getInstance() {
		if (gatewayServer == null) {
			gatewayServer = new GatewayServer();
		}
		return gatewayServer;
	}

	public static void main(String[] args) throws Exception {
		long time = System.currentTimeMillis();
		if (args.length <= 0) {
			System.err.println("请输入配置文件地址路径...启用默认配置文件路径");
			configPath = "config.properties";
		} else {
			configPath = args[0];
		}
		BaseServer gatewayServer = GatewayServer.getInstance();
		if (!gatewayServer.start()) {
			System.err.println("GatewayServer启动失败!");
			System.exit(1);
			return;
		}
		System.err.println("启动耗时: " + (System.currentTimeMillis() - time));
		Log.info("GatewayServer" + (gatewayServer.getId()) + "启动成功!");
	}

}
