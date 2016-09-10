package com.chuangyou.xianni.entity.item;

/**
 * <pre>
 * 物品添加类型 [2999,+N]
 * </pre>
 */
public interface ItemAddType {
	/** 测试添加 */
	public static final short	TEST_ADD		= 2999;

	/** 叠加增加 */
	public static final short	OVERLAY			= 3001;

	/** 拆分新增 */
	public static final short	SPILTCREATE		= 3002;
	/**
	 * 邮件添加物品到背包
	 */
	public static final short	EMAIL_ADD		= 3003;

	/** npc商店加物品 */
	public static final short	NPC_SHOP_ADD	= 3004;

	/**
	 * 任务获得物品
	 */
	public static final short	TASK_ADD		= 3005;

	/**
	 * 拾取掉落物
	 */
	public static final short	DROP_PICKUP		= 3006;

	/** 脚本获得物品 */
	public static final short	SCRIPT_ADD		= 3007;
	/**
	 * 空间获得
	 */
	public static final short	SPACE_ADD		= 3008;

	/**
	 * 装备分解获得
	 */
	public static final short	EQUIP_RESOLVE	= 3009;

	/**
	 * 魂幡
	 */
	public static final short	SOUL			= 3010;

	/**
	 * VIP领取
	 */
	public static final short	VIP_GET			= 3011;

}
