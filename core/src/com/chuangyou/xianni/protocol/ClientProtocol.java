package com.chuangyou.xianni.protocol;

/**
 * 发往客户端协议号，范围 0 - 10000
 * 
 * 500---600 1000-1200: 由范加伟使用 100---300：由郭小帆使用 601 - 700(zhb) 700---900 由hw使用
 */
public interface ClientProtocol {

	public static final short	U_G_LOGIN_OTHER						= 1;	// 账号在其他地方登录
	public static final short	U_G_LOGIN_GATEWAY					= 2;	// 用户登录网关
	public static final short	U_G_LOGIN_RESULT					= 3;	// 用户登录结果
	public static final short	U_G_PLAYER_LIST						= 4;	// 获取角色列表
	public static final short	U_G_PLAYERINFO						= 5;	// 发送角色信息
	public static final short	U_G_BATTLEPLAYERINFO				= 6;	// 发送地图中其他玩家的信息
	public static final short	U_G_DATA_LOAD_STATU					= 7;	// 通知客户端数据加载请求信息
	public static final short	U_G_PLAYER_CREATE_RESULT			= 8;	// 通知客户端创建角色结果
	public static final short	U_G_PING_PACKET						= 9;	// 返回ping包
	public static final short	U_TIME_INFO							= 10;	// 用户重置信息包
	// ===================================================================

	// ===========================>battle<==============================
	public static final short	U_G_ATTACK_SKILL					= 20;	// 通知附件人，玩家施放技能
	public static final short	U_G_DAMAGE							= 21;	// 同步伤害给附近人
	public static final short	U_G_BUFFER_OPTION					= 22;	// buffer操作
	public static final short	U_LIVING_STATE_CHANGE				= 23;	// 生命体状态变更
	public static final short	U_SNARE_CREATE_RESULT				= 24;	// 陷阱创建结果
	public static final short	U_SNARE_TARGETS_INFO				= 25;	// 陷阱人数变化
	// ===========================>battle<==============================
	// ===========================>msgBegin<===========================
	public static final short	U_ALERT_MSG							= 26;	// 弹出消息
	// ===========================>msgEnd<==============================

	// ===========================>campaign<==============================
	public static final short	U_CAMPAIGN_RESP						= 601;	// 副本返回状态
	public static final short	U_CAMPAIGN_INFO						= 602;	// 副本当前信息
	public static final short	U_CAMPAIGN_NODE_INFO				= 603;	// 副本节点
	public static final short	U_CAMPAIGN_RECORD					= 604;	// 副本记录
	public static final short	U_CAMPAIGN_TASK_INFO				= 605;	// 副本任务记录
	public static final short	U_LIMITLESS_CAMPAIGN_RECORD			= 606;	// 挑战副本记录
	// ===========================><==============================
	// ===========================>拉取其他用户详细<===================
	public static final short	U_OTHER_EQUIPMENT_IFNO				= 607;	// 其他用户装备信息
	public static final short	U_OTHER_MAGICWP_IFNO				= 608;	// 其他用户法宝信息
	public static final short	U_OTHER_MOUNT_IFNO					= 609;	// 其他用户坐骑信息
	public static final short	U_OTHER_PET_IFNO					= 610;	// 其他用户宠物信息
	public static final short	U_OTHER_SOUL_IFNO					= 611;	// 其他用户魂幡信息
	// ===========================><==============================
	// ===========================>用户挑战信息<===================
	public static final short	U_ARENA_IFNO						= 622;	// 用户挑战信息
	public static final short	U_BATTLE_RESULT						= 623;	// 战斗结果
	// ===========================><==============================
	// ===========================>PvP1v1<=============
	public static final short	U_OTHER_BATTER_IFNO					= 624;	// 挑战对手信息
	public static final short	U_ME_RANK_INFO						= 625;	// 自己的信息
	public static final short	U_LEADER_BOARD						= 626;	// 排行榜信息
	// ===========================><===================
	// ===========================>Avartar分身<===================
	public static final short	U_SINGLE_AVARTAR_INFO				= 627;	// 单个分身信息
	public static final short	U_TOAL_AVARTAR_INFOS				= 628;	// 所有分身信息
	public static final short	U_CAMPAIGN_AVARTAR_REWARDS			= 629;	// 分身副本奖励
	// ===========================><===================

	/** 错误码 */
	public static final short	U_RESP_ERROR						= 500;
	// ===========================>邮件<==============================
	/** 获取邮件数量 */
	public static final short	U_RESP_GETEMAILS_NUM				= 502;
	/** 获取指定邮件详细信息 */
	public static final short	U_RESP_GETEMAILINFOBYINDEX			= 503;
	/** 设置邮件已读 */
	public static final short	U_RESP_SETEMAILREADER				= 504;
	/** 邮件更新 */
	public static final short	U_RESP_OPERATIONEMAIL				= 505;
	/** 提取附件 */
	public static final short	U_RESP_GETEMAILATTACKMENT			= 506;
	/** 一键提取所有邮件附件 */
	public static final short	U_RESP_GETEMAILATTACHMENTBATCH		= 507;
	/** 删除邮件 */
	public static final short	U_RESP_DELEMAIL						= 508;
	/** 批量删除邮件 */
	public static final short	U_RESP_DELEMAIL_BATCH				= 509;

	// ========================><=========================================

	// ==========================>好友<====================================
	/** 获取好友列表 */
	public static final short	U_RESP_GETFRIENDS					= 510;
	/** 添加好友 */
	public static final short	U_RESP_ADDFRIEND					= 511;
	/** 通知玩家XXX加你为好友 */
	public static final short	U_RESP_NOTIFYADDFRIEND				= 512;
	/** 通过角色名查询角色信息 */
	public static final short	U_RESP_QUERYROLEBYNAME				= 513;
	/** 获取最近联系人列表 */
	public static final short	U_RESP_GETRECENTLYLINKMANS			= 514;
	/** 删除好友 */
	public static final short	U_RESP_DELFRIEND					= 515;
	/** 获取推荐好友 */
	public static final short	U_REQ_GETRECOMMENDFRIENDS			= 516;
	// ========================><=========================================

	// ==========================>地图<========================================
	/** 场景切换，通知客户端切换用户场景 */
	public static final short	U_CHANGE_MAP						= 1517;
	/** 客户端进请求进入场景结果 */
	public static final short	U_ENTER_MAP_RESPOSE					= 1518;
	/** 广播移动信息 */
	public static final short	U_BC_MOVE							= 1519;	// 广播移动信息
	/** 玩家战斗属性变更 */
	public static final short	U_RESP_ATT_CHG						= 1520;	// 玩家战斗属性变更
	/** 玩家战斗属性快照 */
	public static final short	U_RESP_ATT_SNAP						= 1521;	// 玩家战斗属性快照
	/** 切换场景错误，返回结果给客户端 */
	public static final short	U_ENTER_FAIL						= 1522;
	/** 玩家离开九宫格区域 */
	public static final short	U_LEAVE_GRID						= 1523;	//
	/** 玩家进入九宫格区域 */
	public static final short	U_ENTER_GRID						= 1524;	//
	/** 玩家停止移动 */
	public static final short	U_BC_STOP							= 1525;	//
	// ========================><=========================================
	// =========================>NPC商店<=========================================
	/** 获取NPC商店信息 */
	public static final short	U_RESP_GETNPCSHOPINFO				= 525;
	/** 购买商店物品 */
	public static final short	U_RESP_BUYGOODS						= 526;

	/** 请求单个商店商品信息 */
	public static final short	U_RESP_GET_INFO_BYID				= 527;

	// ========================><=========================================

	// =========================>npc对话<===================================
	/** 打开NPC对话 */
	public static final short	U_RESP_OPENNPCDIALOG				= 528;
	/** 发送HINT消息 */
	public static final short	U_RESP_SENDHINT						= 529;
	// ========================><=========================================
	/** 更新属性消息(单个) */

	public static final short	U_RESP_PLAYER_ATT_UPDATE			= 530;

	// =====================================================================
	// =============================>任务<======================================
	/** 获取任务列表 */
	public static final short	U_RESP_TASKLIST						= 531;
	/** 任务改变 */
	public static final short	U_RESP_TASKUPDATE					= 532;
	/**
	 * 任务操作回复
	 */
	public static final short	U_RESP_TASKOPERATE					= 533;
	/** 设置任务失败回复 */
	public static final short	U_RESP_SETTASKFAIL					= 534;

	/**
	 * 设置NPC对话任务完成
	 */
	public static final short	U_RESP_SETNPCDIALOG					= 535;

	// ==================================================================
	// ==========================>采集+触发点<=====================================
	/** 采集 */
	public static final short	U_RESP_GATHER						= 536;

	// ==================================================================

	// =========================>组队<=====================================
	/** 接收队伍信息 */
	public static final short	U_RESP_TEAM_INFO					= 537;
	/** 队伍消毁 */
	public static final short	U_RESP_TEAM_DESTROY					= 538;
	/** 队长变更 */
	public static final short	U_RESP_TEAM_LEADER_CHANGE			= 539;
	/** 队员在线状态变更 */
	public static final short	U_RESP_TEAM_LINE_CHANGE				= 540;
	/** 队员离队 */
	public static final short	U_RESP_TEAM_LEAVE					= 541;
	/** 队员入队 */
	public static final short	U_RESP_TEAM_ADD						= 542;
	/** 申请入队回复 */
	public static final short	U_RESP_APPLY_TO_TEAM				= 543;
	/** 通知队长有人申请入队 */
	public static final short	U_RESP_TEAM_APPLY_LEADER			= 544;
	/** 队长获取申请队列 */
	public static final short	U_RESP_GET_APPLY_LIST				= 545;
	/** 通知申请人 队长同意你入队或者拒绝你入队 */
	public static final short	U_RESP_NOTIFY_AGREE_REJECT			= 546;
	/** 收到队长的邀请通知 */
	public static final short	U_RESP_NOTIFY_INVITE				= 547;
	/** 队长设置队伍目标 */
	public static final short	U_RESP_TEAM_SET_TARGET				= 548;

	/** 按目标查找队伍列表 */
	public static final short	U_ERSP_GET_TEAM_LIST				= 549;

	/** 创建队伍 */
	public static final short	U_RESP_CREATE_TEAM					= 550;
	/**
	 * 队长清空申请列表
	 */
	public static final short	U_RESP_TEAM_CLEAR_APPLY_LIST		= 551;

	/** 取消匹配 */
	public static final short	U_RESP_TEAM_CLEAR_MATCH				= 552;

	/** 返回队伍蓝血量 */
	public static final short	U_RESP_TEAM_HP_MP					= 553;

	// ==================================================================
	/** 商城信息请求返回 */
	public static final short	U_RESP_MALL_INFO					= 554;

	/** 请求简单快照信息 */
	public static final short	U_RESP_PLAYER_SIMPLE				= 555;
	// ===============================================================

	// ============================空间====================================
	/** 获取空间信息 */
	public static final short	U_RESP_GET_SPACE_INFO				= 556;

	/** 请求空间留言信息 */
	public static final short	U_RESP_GET_SPACE_MESSAGE			= 557;

	/** 操作日志 */
	public static final short	U_RESP_GET_SPACE_ACTION_LOG			= 558;

	/** 修改自己空间信息 */
	public static final short	U_RESP_ENDIT_INFO					= 559;
	/** 空间操作 */
	public static final short	U_RESP_SPACE_ACTION					= 1600;

	/** 添加留言 */
	public static final short	U_RESP_SPACE_ADD_MSG				= 1601;

	/** 删除留言 */
	public static final short	U_RESP_SPACE_DEL_MSG				= 1602;

	/** 收藏相关 */
	public static final short	U_RESP_SPACE_SET_COLLECTION			= 1603;

	/** 设置礼物 */
	public static final short	U_RESP_SPACE_SET_GIFT				= 1604;

	/** 通知属性改变 */
	public static final short	U_RESP_NOTIFY_SPACE_CHANGE			= 1065;
	// ============================>魂幡<====================================

	/**
	 * 请求信息
	 */
	public static final short	U_RESP_GET_SOUL_INFO				= 1605;

	/** 碎片合成 */
	public static final short	U_RESP_SOUL_PIECE_COMBO				= 1606;

	/** 增加经验值 */
	public static final short	U_RESP_SOUL_ADD_EXP					= 1607;

	/** 同步碎片数量 */
	public static final short	U_RESP_NOTIFY_CARDPIECE				= 1608;

	/**
	 * 材料制作
	 */
	public static final short	U_RESP_SOUL_MAKE					= 1609;

	/**
	 * 制作任务同步
	 */
	public static final short	U_RESP_SOUL_MAKE_TASK				= 1610;

	/**
	 * 融合
	 */
	public static final short	U_RESP_SOUL_FUSE					= 1611;

	// ==========================>日常活动<====================================
	/**
	 * 活动总数据
	 */
	public static final short	U_RESP_ACTIVITY_INFOS				= 1612;

	/**
	 * 个人活动信息同步
	 */
	public static final short	U_RESP_ACTIVITY_SYNC				= 1613;

	/**
	 * 系统活动信息同步
	 */
	public static final short	U_RESP_TEMP_ACTIVITY_SYNC			= 1621;

	/**
	 * 系统活动信息同步
	 */
	public static final short	U_RESP_SINGLE_TEMP_ACTIVITY_SYNC	= 1622;

	// ==========================>排行榜<======================================
	/**
	 * 排行榜
	 */
	public static final short	U_RESP_RANK_GET_TOTAL				= 1614;
	/**
	 * 排行榜
	 */
	public static final short	U_RESP_RANK_GET_INDEX				= 1615;
	/**
	 * 请求个人数据
	 */
	public static final short	U_RESP_RANK_MYRANK					= 1616;

	// ==========================>境界<======================================
	/**
	 * 获取信息
	 */
	public static final short	U_RESP_STATE_GET_INFO				= 1617;

	/**
	 * 更新
	 */
	public static final short	U_RESP_STATE_UPDATE					= 1618;

	/**
	 * 操作
	 */
	public static final short	U_RESP_STATE_OP						= 1619;

	/**
	 * 境界QTE结果
	 * 
	 */
	public static final short	U_RESP_STATE_QTE					= 1620;
	
	/**
	 * 境界副本进度通知
	 */
	public static final short U_RESP_STATE_FB_PROCESS				= 1623;
	
	/**
	 * 通知有事件触发
	 */
	public static final short U_RESP_STATE_NOTIFY_EVENT				= 1624;

	// =========================>坐骑<===================================
	/** 返回坐骑信息 */
	public static final short	U_MOUNT_GETINFO						= 101;
	/** 坐骑总属性有变更同步 */
	public static final short	U_MOUNT_ATT_UPDATE					= 102;
	/** 坐骑升级 */
	public static final short	U_MOUNT_LEVEL_UP					= 103;
	/** 清除坐骑升级CD */
	public static final short	U_MOUNT_LEVELUPCD_CLEAR				= 104;
	/** 坐骑升阶 */
	public static final short	U_MOUNT_GRADE_UP					= 105;
	/** 坐骑装备升级 */
	public static final short	U_MOUNT_EQUIP_UP					= 106;
	/** 使用坐骑属性丹 */
	public static final short	U_MOUNT_DAN_USE						= 107;
	/** 选择当前使用的坐骑出战 */
	public static final short	U_MOUNT_FIGHT_CHOOSE				= 108;
	/** 坐骑神兵升阶 */
	public static final short	U_MOUNT_WEAPON_UP					= 109;
	/** 已获得的特殊坐骑 */
	public static final short	U_MOUNT_SPECIAL_GET					= 110;

	/** 返回法宝信息 */
	public static final short	U_MAGICWP_GETINFO					= 111;
	/** 选择召唤法宝 */
	public static final short	U_MAGICWP_FIGHT_USE					= 112;
	/** 法宝升级 */
	public static final short	U_MAGICWP_LEVEL_UP					= 113;
	/** 禁制信息 */
	public static final short	U_MAGICWP_BAN_GETINFO				= 114;
	/** 激活禁制碎片 */
	public static final short	U_MAGICWP_BAN_FRAGMENT_USE			= 115;
	/** 禁制升级 */
	public static final short	U_MAGICWP_BAN_LEVEL_UP				= 116;
	/** 使用属性丹 */
	public static final short	U_MAGICWP_DAN_USE					= 117;
	/** 法宝洗炼信息 */
	public static final short	U_MAGICWP_REFINE_GETINFO			= 118;
	/** 激活,进阶法宝洗炼 */
	public static final short	U_MAGICWP_REFINE_GRADE_UP			= 119;
	/** 法宝洗炼 */
	public static final short	U_MAGICWP_REFINE					= 120;
	/** 保存洗炼属性 */
	public static final short	U_MAGICWP_REFINE_SAVE				= 121;
	/** 激活法宝 */
	public static final short	U_MAGICWP_OPEN						= 122;
	/** 设置禁制自动升级 */
	public static final short	U_MAGICWP_BAN_LEVEL_SETAUTOUP		= 123;
	/** 禁制位置改变 */
	public static final short	U_MAGICWP_BAN_EQUIP					= 124;
	/** 清除法宝升级CD */
	public static final short	U_MAGICWP_LEVELUPCD_CLEAR			= 125;
	/** 返回法宝系统总属性 */
	public static final short	U_MAGICWP_GETTOTALATT				= 126;

	/** 返回宠物信息 */
	public static final short	U_PET_GETINFO						= 127;
	/** 宠物激活 */
	public static final short	U_PET_ACTIVATE						= 128;
	/** 宠物出战 */
	public static final short	U_PET_FIGHT							= 129;
	/** 宠物提升资质 */
	public static final short	U_PET_TALENT_UP						= 130;
	/** 宠物升级 */
	public static final short	U_PET_LEVEL_UP						= 131;
	/** 宠物炼体 */
	public static final short	U_PET_PHYSIQUE_UP					= 132;
	/** 宠物品质提升 */
	public static final short	U_PET_QUALITY_UP					= 133;
	/** 宠物升阶 */
	public static final short	U_PET_GRADE_UP						= 134;
	/** 返回宠物总属性 */
	public static final short	U_PET_GETTOTALATT					= 135;
	/** 宠物炼魂 */
	public static final short	U_PET_SOUL_UP						= 136;
	/** 宠物技能激活 */
	public static final short	U_PET_SKILL_ACTIVATE				= 137;
	/** 宠物技能解封 */
	public static final short	U_PET_SKILL_OPEN					= 138;
	/** 宠物技能升级 */
	public static final short	U_PET_SKILL_UP						= 139;
	/** 宠物技能装备 */
	public static final short	U_PET_SKILL_EQUIP					= 140;
	/** 宠物技能更新 */
	public static final short	U_PET_SKILL_UPDATE					= 141;
	/** 宠物技能装备格解锁 */
	public static final short	U_PET_SKILL_SLOT_UNLOCK				= 142;

	// =========================>背包物品<===================================
	/** 物品外观信息列表 */
	public static final short	U_ITEM_FACE_LIST					= 143;

	/** 单个物品详细信息 */
	public static final short	U_ITEM_FULL_INFO					= 144;
	// ========================><=========================================

	/** 踩点触发的点状态更新 */
	public static final short	U_TOUCH_POINT_UPDATE				= 145;

	/** 掉落物品 */
	public static final short	U_DROP_ITEM_PACKAGE					= 146;

	/** 删除掉落物品 */
	public static final short	U_DROP_ITEM_REMOVE					= 147;

	/** 同步坐骑状态 */
	public static final short	U_PLAYER_MOUNT_STATE_RESP			= 148;

	/** 接收聊天消息 */
	public static final short	U_CHAT_RECEIVE						= 149;
	/** 发送给客户端聊天记录 */
	public static final short	U_CHAT_HISTORY_RESP					= 150;

	/** 背包格子解锁 */
	public static final short	U_BAG_GRID_UNLOCK					= 151;

	// ========================>装备<============================
	/** 装备栏位信息 */
	public static final short	U_EQUIPBAR_INFO						= 152;

	/** 装备信息 */
	public static final short	U_EQUIP_INFO						= 153;

	/** 装备一键分解 */
	public static final short	U_EQUIP_RESOLVE_ONEKEY				= 154;
	// ==========================================================

	/** 系统消息提示 */
	public static final short	U_SYSTEM_PROMPT						= 155;

	/** 神器数据 */
	public static final short	U_ARTIFACT_DATA						= 156;

	/** 神器请求返回 */
	public static final short	U_ARTIFACT_RESP						= 157;

	/** 帮派信息 */
	public static final short	U_GUILD_INFO						= 158;

	/** 帮派成员列表 */
	public static final short	U_GUILD_MEMBER_LIST					= 159;

	/** 创建帮派 */
	public static final short	U_GUILD_CREATE						= 160;

	/** 全服帮派列表 */
	public static final short	U_GUILD_LIST						= 161;

	/** 帮派操作返回 */
	public static final short	U_GUILD_ACTION_RESP					= 162;

	/** 成员信息 */
	public static final short	U_GUILD_MEMBER_INFO					= 163;

	/** 有玩家申请入帮 */
	public static final short	U_GUILD_APPLY_INFO					= 164;

	/** 申请列表 */
	public static final short	U_GUILD_APPLY_LIST					= 165;

	/** 批量玩家加入 */
	public static final short	U_GUILD_BAT_JOIN					= 166;

	/** 更新技能信息 */
	public static final short	U_GUILD_SKILL_UPDATE				= 167;

	/** 初始化全部帮派技能信息 */
	public static final short	U_GUILD_SKILL_ALL					= 168;

	/** 帮派仓库信息回复 */
	public static final short	U_GUILD_WAREHOUSE_RESP				= 169;

	/** 帮派信息列表 */
	public static final short	U_GUILD_LOG_LIST					= 170;

	/** 玩家帮派信息同步更新 */
	public static final short	U_PLAYER_GUILD_NOTIFY				= 171;
	
	/**
	 * 野外BOSS信息列表
	 */
	public static final short	U_FIELD_BOSS_LIST					= 172;
	
	/**
	 * 世界BOSS夺宝中玩家箱子数量
	 */
	public static final short	U_TREASURE_COUNT					= 173;
	
	/**
	 * 帮派快照信息
	 */
	public static final short	U_GUILD_SNAP						= 174;
	

	/** 获取时装信息 */
	public static short			U_FASHION_GET						= 201;
	/** 时装穿上 */
	public static short			U_FASHION_EQUIP						= 202;
	/** 时装脱下 */
	public static short			U_FASHION_UNEQUIP					= 203;
	/** 时装进阶 */
	public static short			U_FASHION_GRADE_UP					= 204;
	/** 时装激活 */
	public static short			U_FASHION_ACTIVATE					= 205;
	/** 时装更新 */
	public static short			U_FASHION_UPDATE					= 206;

	// =========================>army<===================================
	/** 人物属性包 */
	public static final short	U_ARMY_HERO_INFO					= 207;
	// ========================>技能<=========================================
	/** 获取英雄技能信息 */
	public static final short	U_HERO_GETSKILLINFO					= 701;
	/** 英雄技能升级 */
	public static final short	U_HERO_UpSkillOK					= 702;
	/** 获取技能总属性 */
	public static final short	U_HERO_GETSKILLTOLPRO				= 703;
	/** 升级技能阶段 */
	public static final short	U_HERO_UPHEROSTAGECMD				= 704;

	/** 一键英雄技能升级 */
	public static final short	U_HERO_ONKEYUPSKILLOK				= 705;
	/** 返回技能列表 */
	public static final short	U_HERO_SKILLLISTOK					= 706;
	// ========================>战斗模式<=========================
	/** 变更战斗模式 */
	public static final short	U_BATTLE_MODE						= 710;
	/** 脱战 **/
	public static final short	U_LEAVE_FIGHT						= 711;
	// ========================>天逆珠<=========================
	/** 天逆珠升级成功 **/
	public static final short	U_INVERSE_BEAD_UP					= 720;
	/** 天逆珠五行数据 **/
	public static final short	U_INVERSE_BEAD_DATE					= 721;
	/** 重置天逆珠数据 **/
	public static final short	U_RESET_INVERSE_MONSTER				= 722;
	/** 天逆珠数据变更 **/
	public static final short	U_REFRESH_INVERSE_BEAD				= 723;
	/** 请求领取灵气液 **/
	public static final short	U_INVERSE_RECEIVE_AURA				= 734;
	// ========================>vip<=========================
	/** vip 购买响应 **/
	public static final short	U_VIP_BUY							= 735;
	/** vip 购买礼包 **/
	public static final short	U_VIP_RECEIVE						= 736;
	/** 获取vip信息 **/
	public static final short	U_GET_VIP_INFO						= 737;
	/** 赠送vip **/
	public static final short	U_VIP_HANDSEL						= 738;
	// =============================>运镖系统<=========================
	/** 请求回应 */
	public static final short	U_RESP_TRUCK_ACTION					= 2612;
	/** 请求回应所有镖车的信息 */
	public static final short	U_RESP_TRUCK_ALLSTATUS				= 2613;
	/** 请求回应自己镖车的信息 */
	public static final short	U_RESP_TRUCK_MYSTATUS				= 2614;
	/** 检查点状态 */
	public static final short	U_RESP_TRUCK_CHECKPOINT				= 2615;
	/** 返回护镖列表 */
	public static final short	U_RESP_TRUCK_PROTECTORLST			= 2616;
	/** 回应护镖 */
	public static final short	U_RESP_TRUCK_INVITE					= 2617;
	/** 回应护镖反馈ACTION */
	public static final short	U_RESP_TRUCK_PROTECT_ACTION			= 2618;
	/** 回应镖车技能信息 */
	public static final short	U_RESP_TRUCK_DATAS					= 2619;
	/** 回应镖车技能加点 */
	public static final short	U_RESP_TRUCK_SKILLADDPOINT			= 2620;
	/** 回应使用镖车技能 */
	public static final short	U_RESP_TRUCK_USESKILL				= 2621;
	/** 镖车升级 */
	public static final short	U_RESP_TRUCK_LVLUP					= 2622;
	/** 镖车属性变更 */
	public static final short	U_RESP_TRUCK_ATTCHG					= 2623;
	/** 镖车领奖 */
	public static final short	U_RESP_TRUCK_REWARD					= 2624;
	/** 帮派成员护镖运镖时间 */
	public static final short	U_RESP_TRUCK_PROT_TIMER				= 2625;
	//福利相关协议
	/** 推送全部福利信息 */
	public static final short	U_ALL_WELFARE_INFO					= 2626;
	/** 更新单个福利信息 */
	public static final short	U_ONE_WELFARE_INFO				= 2627;

}
