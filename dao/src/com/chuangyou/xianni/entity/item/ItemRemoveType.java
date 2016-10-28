package com.chuangyou.xianni.entity.item;

public interface ItemRemoveType {
	/**
	 * 减到为零删除
	 */
	public static final short	TOCLEAR					= 1001;

	/**
	 * 拆分减少
	 */
	public static final short	SPLIT					= 1002;

	/**
	 * 拆分删除
	 */
	public static final short	SPILTDELETE				= 1003;

	/**
	 * 手动删除
	 */
	public static final short	DELETE					= 1005;

	/**
	 * 失效删除
	 */
	public static final short	VALID_DELETE			= 1006;

	/**
	 * 使用
	 */
	public static final short	USE						= 1007;

	/**
	 * 用户删除
	 */
	public static final short	PLAYER_DELETE			= 1008;

	/**
	 * npc商店使用
	 */
	public static final short	NPC_SHOP				= 1009;

	/**
	 * 任务消耗
	 */
	public static final short	TASK_COMMIT				= 1010;

	/**
	 * 进入副本消耗
	 */
	public static final short	JOIN_CAMPAIGN			= 1011;

	/**
	 * 分解
	 */
	public static final short	RESOLVE					= 1012;
	/**
	 * 空间消耗
	 */
	public static final short	SPACE					= 1013;
	/**
	 * 魂幡
	 */
	public static final short	SOUL					= 1014;

	/**
	 * 宠物技能升级
	 */
	public static final short	PET_SKILL_UP			= 1015;

	/**
	 * 坐骑等级提升
	 */
	public static final short	MOUNT_LEVEL_UP			= 1016;

	/**
	 * 购买商品
	 */
	public static final short	PAY_BUY_GOODS			= 1017;

	/**
	 * 技能升级
	 */
	public static final short	UP_SKILL				= 1018;

	/**
	 * 法宝升级
	 */
	public static final short	UP_MAGICWP				= 1019;

	/**
	 * 法宝CD
	 */
	public static final short	MAGICWP_CLEAR_CD		= 1020;

	/**
	 * 坐骑CD
	 */
	public static final short	MOUNT_CLEAR_CD			= 1021;

	/** 宠物炼魂 */
	public static final short	PET_SOUL_UP				= 1022;

	/** 绑定仙玉购买 */
	public static final short	BIND_CASH_BUY			= 1023;

	/** 仙玉购买 */
	public static final short	CASH_BUY				= 1024;

	/** 收藏上限花费 */
	public static final short	SET_COLLECTION_LIMIT	= 1025;

	/** VIP礼包购买 */
	public static final short	BUY_VIP_GIFT			= 1026;

	/** 装备栏加持 */
	public static final short	EQUIPBAR_GRADEUP		= 1027;

	/** 装备觉醒 */
	public static final short	EQUIP_AWAKEN			= 1028;

	/** 装备注魂 */
	public static final short	EQUIP_STONE				= 1029;

	/** 时装激活 */
	public static final short	FASHION_ACTIVATE		= 1030;

	/** 时装进阶 */
	public static final short	FASHION_GRADEUP			= 1031;

	/** 法宝禁制碎片激活或使用 */
	public static final short	MAGICWP_BAN_FRAGMENTUSE	= 1032;

	/** 法宝使用属性丹 */
	public static final short	MAGICWP_PROPERTY_DAN	= 1033;

	/** 法宝激活 */
	public static final short	MAGICWP_ACTIVATE		= 1034;

	/** 法宝洗炼 */
	public static final short	MAGICWP_REFINE			= 1035;

	/** 法宝进阶 */
	public static final short	MAGICWP_GRADEUP			= 1036;

	/** 坐骑使用属性丹 */
	public static final short	MOUNT_PROPERTY_DAN		= 1037;

	/** 坐骑装备升级 */
	public static final short	MOUNT_EQUIP_UP			= 1038;

	/** 坐骑进阶 */
	public static final short	MOUNT_GRADEUP			= 1039;

	/** 坐骑神兵进阶 */
	public static final short	MOUNT_WEAPON_UP			= 1040;

	/** 宠物激活 */
	public static final short	PET_ACTIVATE			= 1041;

	/** 宠物进阶 */
	public static final short	PET_GRADEUP				= 1042;

	/** 宠物炼体 */
	public static final short	PET_PHYSIQUEUP			= 1043;

	/** 宠物品质提升 */
	public static final short	PET_QUALITY_UP			= 1044;

	/** 宠物资质提升 */
	public static final short	PET_TALENT_UP			= 1045;

	/** 宠物技能激活 */
	public static final short	PET_SKILL_ACTIVATE		= 1046;

	/** 宠物技能解封 */
	public static final short	PET_SKILL_OPEN			= 1047;

	/** 宠物技能槽解锁 */
	public static final short	PET_SKILLSLOT_UNLOCK	= 1048;

	/** 宠物升级 */
	public static final short	PET_LEVELUP				= 1049;

	/** 神器升级 */
	public static final short	ARTIFACT_LEVELUP		= 1050;

	/** 神器升星 */
	public static final short	ARTIFACT_GRADEUP		= 1051;

	/** 神器宝石激活 */
	public static final short	ARTIFACT_STONE_ACTIVATE	= 1052;

	/** 神器宝石升级 */
	public static final short	ARTIFACT_STONE_LEVELUP	= 1053;

	/** 背包格子解锁 */
	public static final short	BAG_GRID_UNLOCK			= 1054;
	/**
	 * 卡牌碎片删除
	 */
	public static final short	CARD_PIECE				= 1055;

	/**
	 * 运镖物质添加消耗
	 */
	public static final short	TRUCK_MAT				= 1056;

	/**
	 * 境界消耗
	 */
	public static final short	STATE					= 1057;

	/** 创建帮派 */
	public static final short	GUILD_CREATE			= 1058;

	/** 帮派捐献 */
	public static final short	GUILD_DONATE			= 1059;

	/** 存入帮派仓库 */
	public static final short	GUILD_WAREHOUSE_ADD		= 1060;

	/** 购买属性(灵力) */
	public static final short	BUY_PROPERTY			= 1061;

	/** 帮派夺权 */
	public static final short	GUILD_SEIZE				= 1062;

	/**
	 * 重置竞技场消耗
	 */
	public static final short	ARNE_RESET_COST			= 1063;

	/**
	 * 激活分身消耗
	 */
	public static final short	ACTIVE_AVATAR_COST		= 1064;

	/**
	 * 分身升星消耗
	 */
	public static final short	AVATAR_UP_STAR_COST		= 1065;

	/**
	 * 运镖物质创建消耗
	 */
	public static final short	TRUCK_CREATE			= 1066;
	/**
	 * 运镖技能消耗
	 */
	public static final short	TRUCK_SKILL				= 1067;

	/**
	 * 添加分身能量消耗
	 */
	public static final short	ADD_AVATAR_ENERGY		= 1068;

}
