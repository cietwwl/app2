package com.chuangyou.xianni.entity.item;

/**
 * 
 * <pre>
 * 物品所属目标
 * </pre>
 */
public interface BagType {

	/**
	 * 新增来源
	 */
	public static final short	New				= -2;
	/**
	 * 回收站，已删除
	 */
	public static final short	Recycle			= -1;

	/**
	 * 领主背包
	 */
	public static final short	Play			= 1;

	/**
	 * 英雄装备背包
	 */
	public static final short	HeroEquipment	= 2;

	/** 数值背包 */
	public static final short	VirtualValue	= 3;

}
