package com.chuangyou.xianni.battle.snare;

public class SnareConstant {
	// 生效时机
	public static class ExeWay {
		public static final int	IN				= 1;	// 进入时
		public static final int	TOUCH_PRE_TIME	= 2;	// 对陷阱对象定时执行
		public static final int	OUT				= 3;	// 对象离开时执行
	}

	// 作用对象
	public static class TargetType {
		public static final int	ENEMY			= 1;// 敌方
		public static final int	ENEMY_PLAYER	= 2;// 敌方，仅玩家
		public static final int	ENEMY_MONSTER	= 3;// 敌方，仅怪物
		public static final int	FRIENDLY		= 4;// 友方
		public static final int	ALL_OTHER		= 5;// 所有其他人
	}

}
