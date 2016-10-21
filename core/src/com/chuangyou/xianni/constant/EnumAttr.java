package com.chuangyou.xianni.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色属性枚举
 * 
 * @author laofan
 * 
 */
public enum EnumAttr {
	SOUL ( 1 , " 元魂上限" ) ,
	BLOOD ( 2 , "气血上限" ) ,
	ATTACK ( 3 , "攻击" ) ,
	DEFENCE ( 4 , "防御" ) ,
	SOUL_ATTACK ( 5 , " 魂攻" ) ,
	SOUL_DEFENCE ( 6 , "魂防" ) ,
	ACCURATE ( 7 , "命中" ) ,
	DODGE ( 8 , "闪避" ) ,
	CRIT ( 9 , "暴击" ) ,
	CRIT_DEFENCE ( 10 , "抗暴" ) ,
	CRIT_ADDTION ( 11 , "暴击伤害" ) ,
	CRIT_CUT ( 12 , "抗暴减伤" ) ,
	ATTACK_ADDTION ( 13 , "气血伤害增加" ) ,
	ATTACK_CUT ( 14 , "气血伤害减免" ) ,
	SOUL_ATTACK_ADDTION ( 15 , "元魂伤害增加" ) ,
	SOUL_ATTACK_CUT ( 16 , "元魂伤害减免" ) ,
	REGAIN_SOUL ( 17 , "每10秒回魂" ) ,
	REGAIN_BLOOD ( 18 , "每10秒回血" ) ,
	METAL ( 19 , "金" ) ,
	WOOD ( 20 , "木" ) ,
	WATER ( 21 , "水" ) ,
	FIRE ( 22 , "火" ) ,
	EARTH ( 23 , "土" ) ,
	METAL_DEFENCE ( 24 , "金 抗" ) ,
	WOOD_DEFENCE ( 25 , "木抗" ) ,
	WATER_DEFENCE ( 26 , "水抗" ) ,
	FIRE_DEFENCE ( 27 , "火抗" ) ,
	EARTH_DEFENCE ( 28 , "土抗" ) ,
	SPEED ( 29 , "速度" ) ,
	CUR_SOUL ( 30 , "当前元魂(生命值)" ) ,
	CUR_BLOOD ( 31 , "当前气血(蓝)" ) ,
	PROTECTION ( 32 , "无敌 1无敌，2非无敌" ) ,
	MAX_SOUL ( 33 , "最大元魂" ) ,
	MAX_BLOOD ( 34 , "最大气血" ) ,
	State ( 43 , "境界阶段" ) ,
	SkillStage ( 44 , "修炼阶段" ) ,
	Exp ( 45 , "经验" ) ,
	FightValue ( 46 , "战斗力" ) ,
	Level ( 47 , "等级" ) ,
	Mount ( 48 , "坐骑" ) ,
	Weapon ( 49 , "武器" ) ,
	VipLevel ( 50 , "vip等级" ) ,
	FaBao ( 51 , "法宝" ) ,
	BeiShi ( 52 , "背鉓" ) ,
	Clothes ( 53 , "时装" ) ,
	PetSoul ( 54 , " 炼魂(宠物炼魂)" ) ,
	PetPhysique ( 55 , "炼体(宠物炼体)" ) ,
	PetQuality ( 56 , "品质(宠物品质)" ) ,
	TOTALEXP ( 57 , "总经验" ) ,
	MAP_ID ( 58 , " 地图ID" ) ,
	MAP_KEY ( 59 , "地图KEY" ) ,
	TEAM_ID ( 60 , "组队" ) ,
	VIP_EXP ( 61 , "vip 经验" ) ,
	MANA ( 62 , "灵力" ) ,
	WEAPON_AWAKEN ( 63 , "武器觉醒" ) ,
	SOUL_EXP ( 64 , "魂幡经验" ) ,
	PK_VAL ( 65 , "PK 值" ) ,
	BATTLE_MODE ( 66 , "攻击模式" ) ,
	VIP_TEMPORARY ( 67 , "临时vip到期时间" ) ,
	AVTAR_CORRESPOND ( 68 , "分身默契等级" ) ,
	WORLDBOSS_TREASURE ( 69, "世界BOSS触发夺宝中玩家宝箱数量" ) ,
	MONEY ( 711 , " 灵石" ) ,
	CASH ( 712 , " 仙玉" ) ,
	CASH_BIND ( 713 , "绑定仙玉" ) ,
	EQUIPEXP ( 714 , "装备经验" ) ,
	REPAIR ( 715 , "修为" ) ,
	POINTS ( 716 , "积分 " ) ,
	AVATAR_ENERGY ( 725 , "仙力 " );

	private int		value;
	private String	description;

	private EnumAttr(int v, String desc) {
		this.value = v;
		this.description = desc;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return description;
	}

	public boolean compare(EnumAttr attr) {
		return this.value == attr.getValue();
	}

	private static Map<Integer, EnumAttr> map = new HashMap<>();

	static {
		EnumAttr[] attr = EnumAttr.values();
		for (EnumAttr enumAttr : attr) {
			map.put(enumAttr.getValue(), enumAttr);
		}
	}

	/**
	 * 获取枚举字典
	 * 
	 * @return
	 */
	public static Map<Integer, EnumAttr> getEnumAttrMap() {
		return map;
	}

	/**
	 * 通过值获取枚举
	 * 
	 * @param value
	 * @return
	 */
	public static EnumAttr getEnumAttrByValue(int value) {
		return map.get(value);
	}
}
