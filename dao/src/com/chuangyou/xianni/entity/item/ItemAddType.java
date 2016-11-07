package com.chuangyou.xianni.entity.item;

/**
 * <pre>
 * 物品添加类型 [2999,11001]  不要超过 21001(奖励类型)
 * </pre>
 */
public interface ItemAddType {

	/** 奖励均应>12001 */
	public static class RewardType {
		/** 竞技场奖励 */
		public static final int	ARENA			= 10101;
		/** 竞技场排名奖励 */
		public static final int	PVP_1V1_RANK	= 10102;
		/** 竞技场连胜奖励 */
		public static final int	PVP_1V1_WIN		= 10103;
		/** 分身副本奖励 */
		public static final int	AVATAR_CAMPAIGN	= 10104;
	}

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

	/** 采集物 */
	public static final short	GATHER_ADD		= 3007;
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
	/**
	 * 境界
	 */
	public static final short	STATE			= 3012;

	/**
	 * 帮派商店购买
	 */
	public static final short	GUILD_SHOP_BUY	= 3013;

	/**
	 * 镖车物资掉落添加
	 */
	public static final short	TRUCK_DROP_MAT	= 3014;

	/**
	 * 镖车领奖
	 */
	public static final short	TRUCK_REWARD	= 3015;

	/**
	 * 任务掉落物品
	 */
	public static final short	TASK_DROP		= 3016;

	/**
	 * 消耗物品获取
	 */
	public static final short	COST_ITEM		= 3017;
	
	/**
	 * 开箱子
	 */
	public static final short	OPEN_BOX		= 3018;

	/**
	 * 福利领取物品
	 */
	public static final short	WELFARE			= 3019;
	
	/**
	 * 兑换
	 */
	public static final short   EXCHANGE                = 3020;
	
	/**
	 * 抽卡获取卡牌碎片
	 */
	public static final short LUCK_CARD = 3021;
}
