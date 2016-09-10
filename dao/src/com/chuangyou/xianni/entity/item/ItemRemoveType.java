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

	/** 购买属性 */
	public static final short	BUY_PROPERTY			= 1022;

	/** 绑定仙玉购买 */
	public static final short	BIND_CASH_BUY			= 1023;

	/** 仙玉购买 */
	public static final short	CASH_BUY				= 1024;

	/** 收藏上限花费 */
	public static final short	SET_COLLECTION_LIMIT	= 1025;

	/** VIP礼包购买 */
	public static final short	BUY_VIP_GIFT			= 1026;

}
