package com.chuangyou.xianni.battle.buffer;

public class BufferType {

	public static final int	COMMON_DAMANGE				= 1;	// 先扣气血，后扣元魂
	public static final int	ONLY_BLOOD					= 2;	// 只扣气血
	public static final int	ONLY_SOUL					= 3;	// 只扣元魂
	public static final int	COMMON_RESTORE				= 4;	// 普通恢复
																// 先恢复有元魂后恢复气血
	public static final int	ONLY_RESTORE_BLOOD			= 5;	// 只恢复气血
	public static final int	ONLY_RESTORE_SOUL			= 6;	// 只恢复 元魂

	public static final int	REAL_RESTORE_OR_DAMARGE		= 7;	// 真实回复/伤害的BUFF
	public static final int	PURIFY						= 8;	// 净化buffer
	
	public static final int REAL_RESTORE_OR_DAMAGE1		= 9;	// 真实回复/伤害的buff，按当前血量计算
	
	// public static final int FIXED_BODY = 100; // 定身
	public static final int	ATTR_BODY					= 200;	// 属性buffer
	public static final int	TRUCK_ATTR_BODY				= 201;	// 镖车属性buffer

	public static final int	CRIT_4_BLOOD				= 301;	// 对X%气血以下的怪物造成的气血伤害必定暴击
	public static final int	BUFFER_CREATER				= 302;	// 通用buffer产生器

	public static final int	BOMB_DAMAGE					= 303;	// 攻击时有一定几率爆炸，对前方180°1米半径所有敌人30%气血伤害
	public static final int	DEFENCE_BREAK				= 304;	// 攻击时有一定几率忽略对方x%的防御
	public static final int	SOUL_DEFECT_BREAK			= 305;	// 攻击时有一定几率忽略对方x%的魂防
	public static final int	CASTER_DAMAGE_EFFECT		= 306;	// 施法者伤害修改
	public static final int	BE_ATTACK_DAMAGE_EFFECT		= 307;	// 受击者伤害修改

	public static final int	ATTACK_COVENT_SOULATTACK	= 401;	// x%攻击转化为魂攻
	public static final int	SOULATTACK_COVENT_ATTACK	= 402;	// x%魂攻转化为物攻

	public static final int	TRUCE_HIDE_NAME				= 501;	// 劫镖buff，匿名劫镖
	
	public static final int DO_AND_CONTINUED_RESTORE	= 601;	// 恢复一次并且持续恢复
	public static final int BAN_RESTORE					= 602;	// 禁止治疗
	public static final int BUFF_OR_DEBUFF_IMMUNE		= 603;	// 免疫buff/debuff

	public static class FromType {
		public static final int	COMMON	= 0;	// 正常来源（技能）
		public static final int	WEAPON	= 1;	// 武器
		public static final int	FUSE	= 2;	// 魂幡
	}

}
