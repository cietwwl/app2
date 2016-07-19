package com.chuangyou.xianni.protocol;

/**
 * 发往场景服务器协议，20001-25000
 */

public interface SceneProtocol {
	public static final short	S_LOGIN_IN					= 20001;	// 登录场景服务器
	public static final short	S_LOGIN_OUT					= 20002;	// 退出
																		// 场景服务器

	public static final short	S_REGISTER					= 20003;	// 网关注册

	public static final short	S_ENTERSCENE				= 20011;	// 进入场景
	public static final short	S_REQ_MOVE					= 20012;	// 请求移动
	public static final short	S_REQ_SYNC_P				= 20013;	// 同步位置

	public static final short	S_REQ_ACTION				= 20015;	// 请求Action

	public static final short	S_REQ_STOP					= 20018;	// 玩家停止移动

	public static final short	S_REQ_PLAYER_DETAIL			= 20021;	// 玩家详细数据
	public static final short	S_RELIEVE_PROTECTION		= 20022;	// 玩家进入场景成功,取消保护

	public static final short	S_TOUCH_POINT				= 20023;	// 玩家接触到地图上的节点(传送阵)

	// ===========================>battle<==============================
	public static final short	S_ARMY_ATTACK				= 20024;	// 玩家请求攻击
	// ===========================>end<==============================

	public static final short	S_PROPERTY_UPDATE			= 20025;	// 玩家属性更新

	public static final short	S_SCRIPT_RELOAD				= 20026;	// 重新加载脚本

	public static final short	S_TEMPLETE_RELOAD			= 20027;	// 重新加载模板配置

	public static final short	S_DROP_PICKUP				= 20028;	// 掉落物拾取

	public static final short	S_DROP_PICKUP_RESULT		= 20029;	// 掉落物品背包添加结果

	public static final short	S_PET_INFO_UPDATE			= 20030;	// 通知场景中宠物信息更新

	public static final short	S_PLAYER_CALL_REQ_MOVE		= 20031;	// 角色召唤物请求移动

	public static final short	S_ATTRIBUTE_SCENE_UPDATE	= 20032;	// 影响场景中外形的属性更新

	public static final short	S_PLAYER_MOUNT_STATE_REQ	= 20033;	// 请求上下坐骑
	public static final short	S_PLAYER_UPDATE			    = 20034;	// 玩家数据更新
	
	// ===========================>campaign<=========================
	public static final short	S_CREATE_CAMPAIGN			= 20050;	// 请求创建副本
	public static final short	S_CAMPAIGN_OPTION			= 20051;	// 副本操作
	// ===========================>end<==============================
	public static final short	S_PLAYER_REVIVAL			= 20101;	// 复活

	// ============================>采集<=====================================
	/** 采集 */
	public static final short	S_REQ_GATHER				= 20500;
	/** 队伍信息 --建队 */
	public static final short	S_TEAM_INFO					= 20501;
	/** 入队 */
	public static final short	S_TEAM_ADD					= 20502;
	/** 离队 */
	public static final short	S_TEAM_LEAVE				= 20503;
	/** 队长变更 */
	public static final short	S_TEAM_LEADER_CHANE			= 20504;
	/** 队消毁 */
	public static final short	S_TEAM_DESTROY				= 20506;
	/** 创建一个属于个人的私有怪物 */
	public static final short	S_CREATE_PIRVATE_MONSTER	= 20507;
	/** 查询是否存在私有怪物 */
	public static final short	S_SEARCH_PRIVATE_MONSTER	= 20508;
	/** 请求队伍信息 */
	public static final short S_REQ_TEAM_INFO               = 20509;
	// =======================================================================
	// =========================>同步战斗模式<===============================
	/** 变更战斗模式 */
	public static final short	S_BATTLE_MODE   			= 20601;
	
	/** 聊天消息内部通讯，通知场景服发给所在场景所有玩家 */
	public static final short	S_CHAT_INNER_SEND			= 20510;
	
}
