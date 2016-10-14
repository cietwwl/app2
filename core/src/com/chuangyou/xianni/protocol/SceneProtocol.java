package com.chuangyou.xianni.protocol;

/**
 * 发往场景服务器协议，20001-25000
 */

public interface SceneProtocol {
	public static final short	S_LOGIN_IN						= 20001;	// 登录场景服务器
	public static final short	S_LOGIN_OUT						= 20002;	// 退出
																			// 场景服务器

	public static final short	S_REGISTER						= 20003;	// 网关注册
	public static final short	S_ONLY_LOGIN_OUT				= 20004;	// 仅退出scene服务器

	public static final short	S_ENTERSCENE					= 20011;	// 进入场景
	public static final short	S_REQ_MOVE						= 20012;	// 请求移动
	public static final short	S_REQ_SYNC_P					= 20013;	// 同步位置

	public static final short	S_REQ_ACTION					= 20015;	// 请求Action

	public static final short	S_REQ_STOP						= 20018;	// 玩家停止移动

	public static final short	S_REQ_PLAYER_DETAIL				= 20021;	// 玩家详细数据
	public static final short	S_RELIEVE_PROTECTION			= 20022;	// 玩家进入场景成功,取消保护

	public static final short	S_TOUCH_POINT					= 20023;	// 玩家接触到地图上的节点(传送阵)

	// ===========================>battle<==============================
	public static final short	S_ARMY_ATTACK					= 20024;	// 玩家请求攻击
	// ===========================>end<==============================

	public static final short	S_PROPERTY_UPDATE				= 20025;	// 玩家属性更新

	public static final short	S_SCRIPT_RELOAD					= 20026;	// 重新加载脚本

	public static final short	S_TEMPLETE_RELOAD				= 20027;	// 重新加载模板配置

	public static final short	S_DROP_PICKUP					= 20028;	// 掉落物拾取

	public static final short	S_DROP_PICKUP_RESULT			= 20029;	// 掉落物品背包添加结果

	public static final short	S_PET_INFO_UPDATE				= 20030;	// 通知场景中宠物信息更新

	public static final short	S_PLAYER_CALL_REQ_MOVE			= 20031;	// 角色召唤物请求移动

	public static final short	S_ATTRIBUTE_SCENE_UPDATE		= 20032;	// 影响场景中外形的属性更新

	public static final short	S_PLAYER_MOUNT_STATE_REQ		= 20033;	// 请求上下坐骑
	public static final short	S_PLAYER_UPDATE					= 20034;	// 玩家数据更新

	public static final short	S_PLAYER_MANA_UPDATE			= 20035;	// 玩家灵力更新

	// ===========================>campaign<=========================
	public static final short	S_CREATE_CAMPAIGN				= 20050;	// 请求创建副本
	public static final short	S_CAMPAIGN_OPTION				= 20051;	// 副本操作
	// ===========================>end<==============================
	public static final short	S_PLAYER_REVIVAL				= 20101;	// 复活

	// ============================>采集<=====================================
	/** 采集 */
	public static final short	S_REQ_GATHER					= 20500;
	/** 队伍信息 --建队 */
	public static final short	S_TEAM_INFO						= 20501;
	/** 入队 */
	public static final short	S_TEAM_ADD						= 20502;
	/** 离队 */
	public static final short	S_TEAM_LEAVE					= 20503;
	/** 队长变更 */
	public static final short	S_TEAM_LEADER_CHANE				= 20504;
	/** 队消毁 */
	public static final short	S_TEAM_DESTROY					= 20506;
	/** 创建一个属于个人的私有怪物 */
	public static final short	S_CREATE_PIRVATE_MONSTER		= 20507;
	/** 查询是否存在私有怪物 */
	public static final short	S_SEARCH_PRIVATE_MONSTER		= 20508;
	/** 请求队伍信息 */
	public static final short	S_REQ_TEAM_INFO					= 20509;

	// ===================================================================
	/** 请求创建天逆珠秘境副本 **/
	public static final short	S_CREATE_INVERSE_BEAD_CAMPAIGN	= 20602;
	/** 怪物 **/
	public static final short	S_CREATE_INVERSE_SYNC_MONSTER	= 20603;
	// =========================>同步战斗模式<===============================
	/** 变更战斗模式 */
	public static final short	S_BATTLE_MODE					= 20601;

	/** 聊天消息内部通讯，通知场景服发给所在场景所有玩家 */
	public static final short	S_CHAT_INNER_SEND				= 20510;

	/** 怪物当前位置同步 */
	public static final short	S_MONSTER_POS_SYNC				= 20511;

	/** 玩家创建陷阱 */
	public static final short	S_CREATE_SNARE					= 20512;

	/** 陷阱操作 */
	public static final short	S_TOUCHU_SNARE_STATU			= 20513;
	// =============================>魂幡融合技能<=========================
	/**
	 * 魂幡融合技能更新同步
	 */
	public static final short	S_REQ_SOUL_FUSESKILL_UPDATE		= 20514;
	/** 魂幡数更新 */
	public static final short	S_REQ_SOUL_EXP					= 20515;
	// =============================>运镖系统<=========================
	/** 请求创建镖车 */
	public static final short	S_REQ_TRUCK_CREATE				= 20701;
	/** 请求镖车到底检查点 */
	public static final short	S_REQ_TRUCK_ARRIALCHECKPOINT	= 20702;
	/** 镖车相关Action */
	public static final short	S_REQ_TRUCK_ACTION				= 20703;
	/** 镖车请求移动 */
	public static final short	S_REQ_TRUCK_MOVE				= 20704;
	/** 镖车同步位置点 */
	public static final short	S_REQ_TRUCK_SYNCPOS				= 20705;
	/** 镖车停止移动 */
	public static final short	S_REQ_TRUCK_STOP				= 20706;
	/** 请求检查点 */
	public static final short	S_REQ_TRUCK_CHECKPOINT			= 20707;
	/** 请求添加物质 */
	public static final short	S_REQ_TRUCK_ADDMATERIAL			= 20708;
	/** 物资兑换完成，结算属性 */
	public static final short	S_RESP_TRUCK_MATCHANGED			= 20709;
	/** 镖车数据 */
	public static final short	S_RESP_TRUCK_INFO				= 20710;
	/** 请求护镖 */
	public static final short	S_REQ_TRUCK_PROTECTACTION		= 20711;
	/** 采集到物质 */
	public static final short	S_REQ_TRUCK_PICKMAT				= 20712;
	/** 镖师技能 */
	public static final short	S_REQ_TRUCK_SKILL				= 20713;
	/** 创建镖车价格 */
	public static final short	S_RESP_TRUCK_CREATEPRICE		= 20714;
	/** 回应镖车技能使用消耗 */
	public static final short	S_RESP_TRUCK_FUNCCONSUM			= 20715;
	// =============================>分身<=========================
	/** 副本操作 */
	public static final short	S_AVATAR_CAMPAIGN_OP			= 20737;
	/** 同步分身数据 */
	public static final short	S_SYNC_AVATAR_DATA				= 20738;
	/** 同步修改灵气 */
	public static final short	S_RE_WRITE_AVATAR_ENERGY		= 20739;

	// =============================>竞技场<=========================
	/** 战场挑战 */
	public static final short	S_CREATE_ARENA_BATTLE			= 20740;
	/** 客户端控制机器人攻击 */
	public static final short	S_ROBOT_ATTACK					= 20741;
	/** 创建1V1副本 */
	public static final short	S_CREATE_1V1_BATTLE				= 20744;

	// ==================================>境界<==========================
	/**
	 * 创建境界副本
	 */
	public static final short	S_CREATE_STATE_CAMPAIGN			= 20742;

	/**
	 * 添加境界副本BUFF
	 */
	public static final short	S_ADD_STATE_BUFF				= 20743;

	/**
	 * 帮派夺权挑战
	 */
	public static final short	S_GUILD_SEIZE_CHALLENGE			= 20745;

	/**
	 * 玩家帮派更新
	 */
	public static final short	S_PLAYER_GUILD_UPDATE			= 20746;
	/**
	 * 境界QTE结果返回
	 */
	public static final short	S_STATE_QTE_RES					= 20747;

}
