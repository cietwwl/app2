package com.chuangyou.xianni.constant;

public class BattleSettlementConstant {
	// 击杀
	public static final int	KILLER	= 1;
	// 参与
	public static final int	JOINER	= 2;

	public static interface RoleType {
		int	player	= 1;	// 玩家
		int	monster	= 2;	// 怪物
		int	pet		= 3;	// 宠物
		int	npc		= 4;	// NPC
		int	gather	= 5;	// 采集物
		int	snare	= 6;	// 陷阱
		int	truck	= 7;	// 镖车
		int	robot	= 8;	// 机器人
		int	matrial	= 9;	// 物资
		int	avatar	= 10;	// 分身
	}

	public static interface MonsterDropType {
		/** 仅击杀 */
		int	ONLY_KILLER		= 1;
		/** 所有参与者 */
		int	ERVERYBODY		= 2;
		/** 参与者仅获得经验 */
		int	JOINER_GET_EXP	= 3;
	}
}
