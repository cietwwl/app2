package com.chuangyou.xianni.constant;

public enum MagicwpBanConstant {
	// 金 杀死怪物掉落灵石增加10%
	// 嗜 攻击时有5%几率吸收5%的气血伤害回复自身气血（只有直接伤害技能（buff不触发）触发有效，群攻触发可以群吸）
	// 炼 分解装备时获得的装备经验增加5%
	// 定 暴击时有5%几率将目标定身，持续1秒
	// 血 受到致命气血攻击有1%几率回复10%气血（仅限气血）
	// 火 攻击时有3%几率召唤3只火朱雀随机攻击3米范围内<=3个敌人，50%伤害（元魂+气血）
	// 契 合体时属性加成率增加5%
	// 盾御 受到攻击时有3%几率免疫伤害（气魂都免疫）
	// 冷却 暴击时有10%几率减少所有技能CD1秒（）
	// 静默 攻击时有3%几率沉默目标1秒（添加一个沉默buff）
	// 破镜 对怪物伤害提升5%
	// 反噬 受到攻击时有3%几率免疫并完整反弹一次受到的伤害(反弹真实伤害)
	// 冰霜 攻击自己的敌人有2%几率被冰冻1秒(就是眩晕)
	// 兵解 攻击时有3%几率射出飞剑束攻击半径4米内所有敌人，伤害100%
	// 百胜 攻击有3%几率触发无敌1秒
	// 回魂 死亡时有2%几率满状态复活(致命伤害直接满血，合体状态不触发)
	ADD_GOLD ( 10001 , 0 , "禁制-金" ) ,
	SUCK_BLOOD ( 10002 , 6 , "禁制-嗜" ) ,
	DECOMPOSE ( 10003 , 0 , "禁制-炼" ) ,
	FIXED_BODY ( 10004 , 1 , "禁制-定" ) ,
	RESIST_DEATH ( 10005 , 60 , "禁制-血" ) ,
	BURNING ( 10006 , 5 , "禁制-火" ) ,
	AVATAR_CONTRACT ( 10007 , 0 , "禁制-契" ) ,
	IMMUNE_DAMAGE ( 10008 , 20 , "禁制-盾御" ) ,
	COOL_DOWN ( 10009 , 5 , "禁制-冷却" ) ,
	DISABLE_SKILL ( 10010 , 20 , "禁制-静默" ) ,
	MONSTER_DAMAGE_ADD ( 10011 , 0 , "禁制-破镜" ) ,
	BITE ( 10012 , 20 , "禁制-反噬" ) ,
	FROZEN ( 10013 , 5 , "禁制-冰霜" ) ,
	WEAPON ( 10014 , 5 , "禁制-兵解" ) ,
	INVINCIBLE ( 10015 , 20 , "禁制-百胜" ) ,
	RESURRECTION ( 10016 , 90 , "禁制-回魂" ),;

	private int		code;
	private int		cd;
	private String	desc;

	private MagicwpBanConstant(int code, int cd, String desc) {
		this.code = code;
		this.cd = cd;
		this.desc = desc;
	}

	public static MagicwpBanConstant getCode(int code) {
		for (MagicwpBanConstant chose : MagicwpBanConstant.values()) {
			if (chose.getCode() == code) {
				return chose;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public int getCd() {
		return cd;
	}

	public String toString() {
		return this.desc;
	}

}
