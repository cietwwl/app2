package com.chuangyou.xianni.protocol;

/**
 * 发往网关服务器协议，范围25000-30000
 */
public interface GatewayProtocol {

	public final short	G_LOGIN_GATEWAY			= 25001;// 登录第一步： 登录网关

	public final short	G_USER_WAITE			= 25002;// 登录第三步：准备登录

	public final short	G_PLAYER_LOGIN			= 25003;// 登录第五步：角色登录

	public final short	G_PLAYER_LOGIN_OUT		= 25004;// ：角色退出

	public final short	G_PLAYER_CREATE			= 25005;// 请求创建角色

	public final short	G_PLAYER_CREATE_RESULT	= 25006;// 创建角色结果

	public final short	G_BROADCAST_PACKET		= 25007;// 广播包
}
