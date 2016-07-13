package com.chuangyou.xianni.protocol;

/**
 * 发往客户端协议号，范围 0 - 10000
 * 
 * 500---600: 由范加伟使用 100---300：由郭小帆使用 601 - 700(zhb) 700---900 由hw使用
 */
public interface ClientProtocol {

	public static final short U_G_LOGIN_OTHER = 1; // 账号在其他地方登录
	public static final short U_G_LOGIN_GATEWAY = 2; // 用户登录网关
	public static final short U_G_LOGIN_RESULT = 3; // 用户登录结果
	public static final short U_G_PLAYER_LIST = 4; // 获取角色列表
	public static final short U_G_PLAYERINFO = 5; // 发送角色信息
	public static final short U_G_BATTLEPLAYERINFO = 6; // 发送地图中其他玩家的信息
	public static final short U_G_DATA_LOAD_STATU = 7; // 通知客户端数据加载请求信息
	public static final short U_G_PLAYER_CREATE_RESULT = 8; // 通知客户端创建角色结果
	
	// ===================================================================

	// ===========================>battle<==============================
	public static final short U_G_ATTACK_SKILL = 20; // 通知附件人，玩家施放技能
	public static final short U_G_DAMAGE = 21; // 同步伤害给附近人
	public static final short U_G_BUFFER_OPTION = 22; // buffer操作
	public static final short U_LIVING_STATE_CHANGE = 23; // 生命体状态变更
	// ===========================>battle<==============================

	// ===========================>campaign<==============================
	public static final short U_CAMPAIGN_RESP = 601; // 副本返回状态
	public static final short U_CAMPAIGN_INFO = 602; // 副本当前信息
	public static final short U_CAMPAIGN_NODE_INFO = 603; // 副本节点
	// ===========================><==============================

	/** 错误码 */
	public static final short U_RESP_ERROR = 500;
	// ===========================>邮件<==============================
	/** 获取邮件数量 */
	public static final short U_RESP_GETEMAILS_NUM = 502;
	/** 获取指定邮件详细信息 */
	public static final short U_RESP_GETEMAILINFOBYINDEX = 503;
	/** 设置邮件已读 */
	public static final short U_RESP_SETEMAILREADER = 504;
	/** 邮件更新 */
	public static final short U_RESP_OPERATIONEMAIL = 505;
	/** 提取附件 */
	public static final short U_RESP_GETEMAILATTACKMENT = 506;
	/** 一键提取所有邮件附件 */
	public static final short U_RESP_GETEMAILATTACHMENTBATCH = 507;
	/** 删除邮件 */
	public static final short U_RESP_DELEMAIL = 508;
	/** 批量删除邮件 */
	public static final short U_RESP_DELEMAIL_BATCH = 509;

	
	// ========================><=========================================

	// ==========================>好友<====================================
	/** 获取好友列表 */
	public static final short U_RESP_GETFRIENDS = 510;
	/** 添加好友 */
	public static final short U_RESP_ADDFRIEND = 511;
	/** 通知玩家XXX加你为好友 */
	public static final short U_RESP_NOTIFYADDFRIEND = 512;
	/** 通过角色名查询角色信息 */
	public static final short U_RESP_QUERYROLEBYNAME = 513;
	/** 获取最近联系人列表 */
	public static final short U_RESP_GETRECENTLYLINKMANS = 514;
	/** 删除好友 */
	public static final short U_RESP_DELFRIEND = 515;
	/** 获取推荐好友 */
	public static final short U_REQ_GETRECOMMENDFRIENDS = 516;
	// ========================><=========================================

	// ==========================>地图<========================================
	/** 场景切换，通知客户端切换用户场景 */
	public static final short	U_CHANGE_MAP					= 1517;
	/** 客户端进请求进入场景结果 */
	public static final short U_ENTER_MAP_RESPOSE = 1518;
	/** 广播移动信息 */
	public static final short U_BC_MOVE = 1519; // 广播移动信息
	/** 玩家战斗属性变更 */
	public static final short U_RESP_ATT_CHG = 1520; // 玩家战斗属性变更
	/** 玩家战斗属性快照 */
	public static final short U_RESP_ATT_SNAP = 1521; // 玩家战斗属性快照
	/** 切换场景错误，返回结果给客户端 */
	public static final short U_ENTER_FAIL = 1522;
	/** 玩家离开九宫格区域 */
	public static final short U_LEAVE_GRID = 1523; //
	/** 玩家进入九宫格区域 */
	public static final short U_ENTER_GRID = 1524; //
	/** 玩家停止移动 */
	public static final short U_BC_STOP = 1525; //
	// ========================><=========================================
	// =========================>NPC商店<=========================================
	/** 获取NPC商店信息 */
	public static final short U_RESP_GETNPCSHOPINFO = 525;
	/** 购买NPC商店物品 */
	public static final short U_RESP_BUYGOODS = 526;

	/** 请求单个NPC商店商品信息 */
	public static final short U_RESP_GET_INFO_BYID = 527;

	// ========================><=========================================

	// =========================>npc对话<===================================
	/** 打开NPC对话 */
	public static final short U_RESP_OPENNPCDIALOG = 528;
	/** 发送HINT消息 */
	public static final short U_RESP_SENDHINT = 529;
	// ========================><=========================================
	/** 更新属性消息(单个) */

	public static final short	U_RESP_PLAYER_ATT_UPDATE		= 530;

	// =====================================================================
	// =============================>任务<======================================
	/** 获取任务列表 */
	public static final short U_RESP_TASKLIST = 531;
	/** 任务改变 */
	public static final short U_RESP_TASKUPDATE = 532;
	/**
	 * 任务操作回复
	 */
	public static final short U_RESP_TASKOPERATE = 533;
	/** 设置任务失败回复 */
	public static final short U_RESP_SETTASKFAIL = 534;

	/**
	 * 设置NPC对话任务完成
	 */
	public static final short U_RESP_SETNPCDIALOG = 535;

	// ==================================================================
	// ==========================>采集+触发点<=====================================
	/** 采集 */
	public static final short U_RESP_GATHER = 536;

	// ==================================================================

	// =========================>组队<=====================================
	/** 接收队伍信息 */
	public static final short U_RESP_TEAM_INFO = 537;
	/** 队伍消毁 */
	public static final short U_RESP_TEAM_DESTROY = 538;
	/** 队长变更 */
	public static final short U_RESP_TEAM_LEADER_CHANGE = 539;
	/** 队员在线状态变更 */
	public static final short U_RESP_TEAM_LINE_CHANGE = 540;
	/** 队员离队 */
	public static final short U_RESP_TEAM_LEAVE = 541;
	/** 队员入队 */
	public static final short U_RESP_TEAM_ADD = 542;
	/** 申请入队回复 */
	public static final short U_RESP_APPLY_TO_TEAM = 543;
	/** 通知队长有人申请入队 */
	public static final short U_RESP_TEAM_APPLY_LEADER = 544;
	/** 队长获取申请队列 */
	public static final short U_RESP_GET_APPLY_LIST = 545;
	/**通知申请人  队长同意你入队或者拒绝你入队 */
	public static final short U_RESP_NOTIFY_AGREE_REJECT = 546;
	/**收到队长的邀请通知  */
	public static final short U_RESP_NOTIFY_INVITE = 547;
	/** 队长设置队伍目标   */
	public static final short U_RESP_TEAM_SET_TARGET             = 548;
	
	/** 按目标查找队伍列表  */
	public static final short U_ERSP_GET_TEAM_LIST               = 549;
	
	/** 创建队伍 */
	public static final short	U_RESP_CREATE_TEAM				= 550;
	/**
	 * 队长清空申请列表
	 */
	public static final short   U_RESP_TEAM_CLEAR_APPLY_LIST     = 551;
	
	/**  取消匹配  */
	public static final short   U_RESP_TEAM_CLEAR_MATCH          = 552;
	
	/**  返回队伍蓝血量   */
	public static final short U_RESP_TEAM_HP_MP                  = 553;

	// ==================================================================

	/** 返回坐骑信息 */
	public static final short U_MOUNT_GETINFO = 101;
	/** 坐骑总属性有变更同步 */
	public static final short U_MOUNT_ATT_UPDATE = 102;
	/** 坐骑升级 */
	public static final short U_MOUNT_LEVEL_UP = 103;
	/** 清除坐骑升级CD */
	public static final short U_MOUNT_LEVELUPCD_CLEAR = 104;
	/** 坐骑升阶 */
	public static final short U_MOUNT_GRADE_UP = 105;
	/** 坐骑装备升级 */
	public static final short U_MOUNT_EQUIP_UP = 106;
	/** 使用坐骑属性丹 */
	public static final short U_MOUNT_DAN_USE = 107;
	/** 选择当前使用的坐骑出战 */
	public static final short U_MOUNT_FIGHT_CHOOSE = 108;
	/** 坐骑神兵升阶 */
	public static final short U_MOUNT_WEAPON_UP = 109;
	/** 已获得的特殊坐骑 */
	public static final short U_MOUNT_SPECIAL_GET = 110;

	/** 返回法宝信息 */
	public static final short U_MAGICWP_GETINFO = 111;
	/** 选择召唤法宝 */
	public static final short U_MAGICWP_FIGHT_USE = 112;
	/** 法宝升级 */
	public static final short U_MAGICWP_LEVEL_UP = 113;
	/** 禁制信息 */
	public static final short U_MAGICWP_BAN_GETINFO = 114;
	/** 激活禁制碎片 */
	public static final short U_MAGICWP_BAN_FRAGMENT_USE = 115;
	/** 禁制升级 */
	public static final short U_MAGICWP_BAN_LEVEL_UP = 116;
	/** 使用属性丹 */
	public static final short U_MAGICWP_DAN_USE = 117;
	/** 法宝洗炼信息 */
	public static final short U_MAGICWP_REFINE_GETINFO = 118;
	/** 激活,进阶法宝洗炼 */
	public static final short U_MAGICWP_REFINE_GRADE_UP = 119;
	/** 法宝洗炼 */
	public static final short U_MAGICWP_REFINE = 120;
	/** 保存洗炼属性 */
	public static final short U_MAGICWP_REFINE_SAVE = 121;
	/** 激活法宝 */
	public static final short U_MAGICWP_OPEN = 122;
	/** 设置禁制自动升级 */
	public static final short U_MAGICWP_BAN_LEVEL_SETAUTOUP = 123;
	/** 禁制位置改变 */
	public static final short U_MAGICWP_BAN_EQUIP = 124;
	/** 清除法宝升级CD */
	public static final short U_MAGICWP_LEVELUPCD_CLEAR = 125;
	/** 返回法宝系统总属性 */
	public static final short U_MAGICWP_GETTOTALATT = 126;

	/** 返回宠物信息 */
	public static final short U_PET_GETINFO = 127;
	/** 宠物激活 */
	public static final short U_PET_ACTIVATE = 128;
	/** 宠物出战 */
	public static final short U_PET_FIGHT = 129;
	/** 宠物提升资质 */
	public static final short U_PET_TALENT_UP = 130;
	/** 宠物升级 */
	public static final short U_PET_LEVEL_UP = 131;
	/** 宠物炼体 */
	public static final short U_PET_PHYSIQUE_UP = 132;
	/** 宠物品质提升 */
	public static final short U_PET_QUALITY_UP = 133;
	/** 宠物升阶 */
	public static final short U_PET_GRADE_UP = 134;
	/** 返回宠物总属性 */
	public static final short U_PET_GETTOTALATT = 135;
	/** 宠物炼魂 */
	public static final short U_PET_SOUL_UP = 136;
	/** 宠物技能激活 */
	public static final short U_PET_SKILL_ACTIVATE = 137;
	/** 宠物技能解封 */
	public static final short U_PET_SKILL_OPEN = 138;
	/** 宠物技能升级 */
	public static final short U_PET_SKILL_UP = 139;
	/** 宠物技能装备 */
	public static final short U_PET_SKILL_EQUIP = 140;
	/** 宠物技能更新 */
	public static final short U_PET_SKILL_UPDATE = 141;
	/** 宠物技能装备格解锁 */
	public static final short U_PET_SKILL_SLOT_UNLOCK = 142;

	// =========================>背包物品<===================================
	/** 物品外观信息列表 */
	public static final short U_ITEM_FACE_LIST = 143;

	/** 单个物品详细信息 */
	public static final short U_ITEM_FULL_INFO = 144;
	// ========================><=========================================

	/** 踩点触发的点状态更新 */
	public static final short U_TOUCH_POINT_UPDATE = 145;

	/** 掉落物品 */
	public static final short U_DROP_ITEM_PACKAGE = 146;

	/** 删除掉落物品 */
	public static final short U_DROP_ITEM_REMOVE = 147;
	
	public static final short U_PLAYER_MOUNT_STATE_RESP = 148;	// 同步坐骑状态

	/** 获取时装信息 */
	public static short U_FASHION_GET = 201;
	/** 时装穿上 */
	public static short U_FASHION_EQUIP = 202;
	/** 时装脱下 */
	public static short U_FASHION_UNEQUIP = 203;
	/** 时装进阶 */
	public static short U_FASHION_GRADE_UP = 204;
	/** 时装激活 */
	public static short U_FASHION_ACTIVATE = 205;
	/** 时装更新 */
	public static short U_FASHION_UPDATE = 206;

	// =========================>army<===================================
	/** 人物属性包 */
	public static final short U_ARMY_HERO_INFO = 207;
	// ========================>技能<=========================================
	/** 获取英雄技能信息 */
	public static final short U_HERO_GETSKILLINFO = 701;
	/** 英雄技能升级 */
	public static final short U_HERO_UpSkillOK = 702;
	/** 获取技能总属性 */
	public static final short U_HERO_GETSKILLTOLPRO = 703;
	/** 升级技能阶段 */
	public static final short U_HERO_UPHEROSTAGECMD = 704;
	
	/** 一键英雄技能升级 */
	public static final short U_HERO_ONKEYUPSKILLOK = 705;
	/** 返回技能列表 */
	public static final short U_HERO_SKILLLISTOK = 706;
	// ========================><=========================================
}
