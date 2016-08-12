package com.chuangyou.xianni.battle.buffer;

public class BufferType {

	public static final int	COMMON_DAMANGE		= 1;	// 先扣气血，后扣元魂
	public static final int	ONLY_BLOOD			= 2;	// 只扣气血
	public static final int	ONLY_SOUL			= 3;	// 只扣元魂
	public static final int	COMMON_RESTORE		= 4;	// 普通恢复 先恢复有元魂后恢复气血
	public static final int	ONLY_RESTORE_BLOOD	= 5;	// 只恢复气血
	public static final int	ONLY_RESTORE_SOUL	= 6;	// 只恢复 元魂
	public static final int	FIXED_BODY			= 100;	// 定身
	public static final int	ATTR_BODY			= 200;	// 属性buffer
}
