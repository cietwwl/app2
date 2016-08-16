package com.chuangyou.xianni.entity.item;

public interface ItemRemoveType {
	/**
	 * 减到为零删除
	 */
	public static final short	TOCLEAR			= 1001;

	/**
	 * 拆分减少
	 */
	public static final short	SPLIT			= 1002;

	/**
	 * 拆分删除
	 */
	public static final short	SPILTDELETE		= 1003;

	/**
	 * 手动删除
	 */
	public static final short	DELETE			= 1005;

	/**
	 * 失效删除
	 */
	public static final short	VALID_DELETE	= 1006;

	/**
	 * 使用
	 */
	public static final short	USE				= 1007;

	/**
	 * 用户删除
	 */
	public static final short	PLAYER_DELETE	= 1008;

	/**
	 * npc商店使用
	 */
	public static final short	NPC_SHOP		= 1009;

	/**
	 * 任务消耗
	 */
	public static final short TASK_COMMIT = 1010;
	
	/**
	 * 进入副本消耗
	 */
	public static final short	JOIN_CAMPAIGN	= 1011;

	/**
	 * 分解
	 */
	public static final short	RESOLVE			= 1012;
	/**
	 * 空间消耗
	 */
	public static final short SPACE = 1013;
	/**
	 * 魂幡
	 */
	public static final short SOUL = 1014;
	
	
}
