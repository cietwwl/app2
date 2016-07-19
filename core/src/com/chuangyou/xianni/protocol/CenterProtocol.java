package com.chuangyou.xianni.protocol;

/**
 * 发往center_server，范围 10001 - 20000 10500---10600: 由范加伟使用 10100---10300：由郭小帆使用
 * 10300---10500 由hw使用 10601---10700z
 * 
 */
public interface CenterProtocol {

	public static final short	C_REGISTER						= 10001;	//
	public static final short	C_LOGIN_KEY						= 10002;	// 登录第二步，验证KEY，验证通过，则返回角色列表
	public static final short	C_PLAYER_LOGIN					= 10003;	// 登录第五步，加载基础信息
	public static final short	C_PLAYER_DATA					= 10004;	// 登陆第六步：加载玩家数据(客户端分状态多次加载)
	public static final short	C_PLAYER_OUT					= 10005;	// 退出
	public static final short	C_PLAYER_CREATE					= 10006;	// 创建角色
	public static final short	C_PLAYER_RELOAD_SCENCE_DATA		= 10007;	// 回写scene服务器数据
	public static final short	C_PLAYER_KILL_MONSTER			= 10008;	// 收到scene服杀怪通知
	public static final short	C_PLAYER_UPDATA_PRO				= 10009;	// 收到scene服属性修改
	// ===========================>邮件<==============================
	/** 获取邮件数量 */
	public static final short	C_REQ_GETEMAILS_NUM				= 10505;
	/** 获取指定邮件详细信息 */
	public static final short	C_REQ_GETEMAILINFOBYINDEX		= 10506;
	/** 设置邮件已读 */
	public static final short	C_REQ_SETEMAILREADER			= 10507;
	/** 提取附件 */
	public static final short	C_REQ_GETEMAILATTACKMENT		= 10508;
	/** 一键提取所有邮件附件 */
	public static final short	C_REQ_GETEMAILATTACHMENTBATCH	= 10509;
	/** 删除邮件 */
	public static final short	C_REQ_DELEMAIL					= 10510;
	/** 批量删除邮件 */
	public static final short	C_REQ_DELEMAIL_BATCH			= 10511;
	// ========================><=========================================

	// ==========================>好友<========================================
	/** 获取好友列表 */
	public static final short	C_REQ_GETFRIENDS				= 10512;
	/** 添加好友 */
	public static final short	C_REQ_ADDFRIEND					= 10513;
	/** 通过角色名查询角色信息 */
	public static final short	C_REQ_QUERYROLEBYNAME			= 10514;
	/** 获取最近联系人列表 */
	public static final short	C_REQ_GETRECENTLYLINKMANS		= 10515;
	/** 删除好友 */
	public static final short	C_REQ_DELFRIEND					= 10516;
	/** 获取推荐好友 */
	public static final short	C_REQ_GETRECOMMENDFRIENDS		= 10517;

	// ========================><=========================================

	// ==========================>地图<========================================
	/** 请求变更场景 */
	public static final short	C_CHANGE_MAP					= 10518;
	/** 申请场景入场结果 */
	public static final short	C_ENTER_SENCE_MAP_RESULT		= 10519;
	/** 客户端进入场景结果 */
	@Deprecated
	public static final short	C_ENTER_MAP_RESULT				= 10520;
	/** scene服务器创建地图 */
	public static final short	C_SCENE_CREATE_MAP				= 10524;
	/** scene服务器销毁地图 */
	public static final short	C_SCENE_CREATE_DESTORY			= 10525;

	// ========================><=========================================

	// =========================>副本<===================================
	/** 创建副本 */
	public static final short	C_CREATE_CAMPAIGN				= 10601;

	/** 副本创建结果 */
	public static final short	C_CREATE_CAM_RESULT				= 10602;

	/** 退出副本 */
	public static final short	C_QUIT_CAMPAIGN					= 10603;

	/** 进入副本结果 */
	public static final short	C_ENTER_SENCE_CAMPAIGN_RESULT	= 10604;

	/** 返回副本 */
	public static final short	C_BACK_CAMPAIGN					= 10605;

	/** 副本操作 */
	public static final short	C_CAMPAIGN_OPTION				= 10606;

	/** 玩家副本状态 */
	public static final short	C_CAMPAIGN_STATU				= 10607;

	// ========================><=========================================

	// =========================>NPC商店<===================================
	/** 获取NPC商店信息 */
	public static final short	C_REQ_GETNPCSHOPINFO			= 10521;
	/** 购买NPC商店物品 */
	public static final short	C_REQ_BUYGOODS					= 10522;
	/** 请求单个NPC商店商品信息 */
	public static final short	C_REQ_GET_INFO_BYID				= 10523;

	// ========================><=========================================

	// =========================>npc对话<===================================
	/** 打开NPC对话 */
	public static final short	C_REQ_OPENNPCDIALOG				= 10527;
	/** NPC对话选项 */
	public static final short	C_REQ_NPCDIALOGSELECT			= 10528;
	// ========================><=========================================

	// =============================>任务<======================================
	/** 获取任务列表 */
	public static final short	C_REQ_TASKLIST					= 10529;
	/**
	 * 任务操作
	 */
	public static final short	C_REQ_TASKOPERATE				= 10530;

	/** 设置任务失败回复 */
	public static final short	C_REQ_SETTASKFAIL				= 10531;
	/**
	 * 设置NPC对话任务完成
	 */
	public static final short	C_REQ_SETNPCDIALOG				= 10532;
	/** 通关副本 */
	public static final short	C_REQ_PASS_FB					= 10533;

	// ==================================================================
	// ==========================>采集+触发点<=====================================

	/** 触发点 */
	public static final short	C_REQ_TRIGGER					= 10534;

	// ==================================================================
	// =========================>组队<=====================================
	/** 创建队伍 */
	public static final short	C_REQ_CREATE_TEAM				= 10535;
	/** 申请入队 */
	public static final short	C_REQ_APPLY_TO_TEAM				= 10536;
	/** 队长获取申请队列 */
	public static final short	C_REQ_GET_APPLY_LIST			= 10537;
	/** 队长同意/拒绝入队 */
	public static final short	C_REQ_AGREE_TO_TEAM				= 10538;
	/** 队长邀请入队 */
	public static final short	C_REQ_INVITE_TO_TEAM			= 10539;
	/** 接受队长的邀请 */
	public static final short	C_REQ_AGREE_INVITE				= 10540;
	/** 队长主动移交自己队长的职位 */
	public static final short	C_REQ_TEAM_CHANGE_LEADER		= 10541;
	/** 离队 */
	public static final short	C_REQ_TEAM_LEAVE				= 10542;
	/** 踢人 */
	public static final short	C_REQ_TEAM_KICK					= 10543;
	/** 队长设置队伍目标 */
	public static final short	C_REQ_TEAM_SET_TARGET			= 10544;
	/** 按目标查找队伍列表 */
	public static final short	C_ERQ_GET_TEAM_LIST				= 10545;
	/** 匹配 */
	public static final short	C_ERQ_MATCH_TEAM_TARGET			= 10546;
	/**
	 * 队长清空申请列表
	 */
	public static final short	C_REQ_TEAM_CLEAR_APPLY_LIST		= 10547;
	/** 取消匹配 */
	public static final short	C_REQ_TEAM_CLEAR_MATCH			= 10548;
	/** 回应查找怪物结果 */
	public static final short	C_INNER_SEARCH_MONSTER			= 10549;
	// ==================================================================
	// =========================>坐骑<===================================
	/** 获取坐骑信息 */
	public static short			C_MOUNT_GETINFO					= 10101;
	/** 坐骑升级 */
	public static short			C_MOUNT_LEVEL_UP				= 10102;
	/** 清除坐骑升级CD */
	public static short			C_MOUNT_LEVELUPCD_CLEAR			= 10103;
	/** 坐骑升阶 */
	public static short			C_MOUNT_GRADE_UP				= 10104;
	/** 坐骑装备升级 */
	public static short			C_MOUNT_EQUIP_UP				= 10105;
	/** 使用坐骑属性丹 */
	public static short			C_MOUNT_DAN_USE					= 10106;
	/** 选择当前使用的坐骑出战 */
	public static short			C_MOUNT_FIGHT_CHOOSE			= 10107;
	/** 坐骑神兵升阶 */
	public static short			C_MOUNT_WEAPON_UP				= 10108;
	/** 已获得的特殊坐骑 */
	public static short			C_MOUNT_SPECIAL_GET				= 10109;
	// =========================><===================================

	// =========================>法宝<===================================
	/** 获取法宝信息 */
	public static short			C_MAGICWP_GETINFO				= 10110;
	/** 选择召唤法宝 */
	public static short			C_MAGICWP_FIGHT_USE				= 10111;
	/** 请求法宝升级 */
	public static short			C_MAGICWP_LEVEL_UP				= 10112;
	/** 获取禁制信息 */
	public static short			C_MAGICWP_BAN_GETINFO			= 10113;
	/** 请求激活禁制碎片 */
	public static short			C_MAGICWP_BAN_FRAGMENT_USE		= 10114;
	/** 禁制升级 */
	public static short			C_MAGICWP_BAN_LEVEL_UP			= 10115;
	/** 请求使用属性丹 */
	public static short			C_MAGICWP_DAN_USE				= 10116;
	/** 请求法宝洗炼信息 */
	public static short			C_MAGICWP_REFINE_GETINFO		= 10117;
	/** 请求激活,进阶法宝洗炼 */
	public static short			C_MAGICWP_REFINE_GRADE_UP		= 10118;
	/** 请求法宝洗炼 */
	public static short			C_MAGICWP_REFINE				= 10119;
	/** 保存洗炼属性 */
	public static short			C_MAGICWP_REFINE_SAVE			= 10120;
	/** 激活法宝 */
	public static short			C_MAGICWP_OPEN					= 10121;
	/** 设置禁制自动升级 */
	public static short			C_MAGICWP_BAN_LEVEL_SETAUTOUP	= 10122;
	/** 装备禁制 */
	public static short			C_MAGICWP_BAN_EQUIP				= 10123;
	/** 清除法宝升级CD */
	public static short			C_MAGICWP_LEVELUPCD_CLEAR		= 10124;
	/** 请求法宝系统总属性 */
	public static short			C_MAGICWP_GETTOTALATT			= 10125;
	// =========================><===================================

	// =========================>宠物<===================================
	/** 获取宠物信息 */
	public static short			C_PET_GETINFO					= 10126;
	/** 宠物激活 */
	public static short			C_PET_ACTIVATE					= 10127;
	/** 请求宠物出战 */
	public static short			C_PET_FIGHT						= 10128;
	/** 请求宠物提升资质 */
	public static short			C_PET_TALENT_UP					= 10129;
	/** 请求宠物升级 */
	public static short			C_PET_LEVEL_UP					= 10130;
	/** 请求宠物炼体 */
	public static short			C_PET_PHYSIQUE_UP				= 10131;
	/** 请求宠物品质提升 */
	public static short			C_PET_QUALITY_UP				= 10132;
	/** 请求宠物升阶 */
	public static short			C_PET_GRADE_UP					= 10133;
	/** 请求宠物总属性 */
	public static short			C_PET_GETTOTALATT				= 10134;
	/** 请求宠物炼魂 */
	public static short			C_PET_SOUL_UP					= 10135;
	/** 请求宠物技能激活 */
	public static short			C_PET_SKILL_ACTIVATE			= 10136;
	/** 请求宠物技能解封 */
	public static short			C_PET_SKILL_OPEN				= 10137;
	/** 请求宠物技能升级 */
	public static short			C_PET_SKILL_UP					= 10138;
	/** 请求宠物技能装备 */
	public static short			C_PET_SKILL_EQUIP				= 10139;
	/** 请求宠物技能更新 */
	public static short			C_PET_SKILL_UPDATE				= 10140;
	/** 请求宠物技能装备格解锁 */
	public static short			C_PET_SKILL_SLOT_UNLOCK			= 10141;
	// =========================><===================================

	// ========================>物品BEGIN<=================================
	/** 使用物品 */
	public static short			C_ITEM_USE						= 10142;
	/** 使用装备 */
	public static short			C_EQUIMPENT_OPTION				= 10143;
	/** 物品删除 */
	public static short			C_ITEM_DELETE					= 10144;
	/** 请求物品详细信息 */
	public static short			C_ITEM_FULL_INFO				= 10145;

	// =========================>物品END<===================================

	/** 掉落物拾取 */
	public static short			C_DROP_PICKUP					= 10146;
	
	/** 聊天发送消息 */
	public static short			C_CHAT_SEND						= 10147;
	/** 获取聊天记录 */
	public static short			C_CHAT_HISTORY_REQ				= 10148;

	// =========================>时装<===================================
	/** 获取时装信息 */
	public static short			C_FASHION_GET					= 10201;
	/** 时装穿上 */
	public static short			C_FASHION_EQUIP					= 10202;
	/** 时装脱下 */
	public static short			C_FASHION_UNEQUIP				= 10203;
	/** 时装进阶 */
	public static short			C_FASHION_GRADE_UP				= 10204;
	/** 时装激活 */
	public static short			C_FASHION_ACTIVATE				= 10205;
	// =========================>英雄基础技能<===================================
	/** 获取英雄技能信息 */
	public static short			C_HERO_GETSKILLINFO				= 10301;
	/** 英雄技能升级 */
	public static short			C_HERO_UPSKILL					= 10302;
	/** 获取技能总属性 */
	public static short			C_HERO_GETSKILLTOLPRO			= 10303;
	/** 升级技能阶段 */
	public static short			C_HERO_UPHEROSTAGECMD			= 10304;
	/** 一键英雄被动技能升级 */
	public static short			C_HERO_ONKEYUPSKILL				= 10305;

	// =========================><===================================
	/** 立即复活 */
	public static final short	C_PLAYER_REVIVAL				= 10401;
	// =========================>战斗模式<===============================
	/** 变更战斗模式 */
	public static final short	C_BATTLE_MODE   				= 10501;
	
	
	
}
