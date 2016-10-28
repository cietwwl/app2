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

	SOUL ( 1 , NotifyType.NOTIFY_SCENE , " 元魂上限" ) ,
	BLOOD ( 2 , NotifyType.NOTIFY_SCENE , "气血上限" ) ,
	ATTACK ( 3 , NotifyType.NOTIFY_SCENE , "攻击" ) ,
	DEFENCE ( 4 , NotifyType.NOTIFY_SCENE , "防御" ) ,
	SOUL_ATTACK ( 5 , NotifyType.NOTIFY_SCENE , " 魂攻" ) ,
	SOUL_DEFENCE ( 6 , NotifyType.NOTIFY_SCENE , "魂防" ) ,
	ACCURATE ( 7 , NotifyType.NOTIFY_SCENE , "命中" ) ,
	DODGE ( 8 , NotifyType.NOTIFY_SCENE , "闪避" ) ,
	CRIT ( 9 , NotifyType.NOTIFY_SCENE , "暴击" ) ,
	CRIT_DEFENCE ( 10 , NotifyType.NOTIFY_SCENE , "抗暴" ) ,
	CRIT_ADDTION ( 11 , NotifyType.NOTIFY_SCENE , "暴击伤害" ) ,
	CRIT_CUT ( 12 , NotifyType.NOTIFY_SCENE , "抗暴减伤" ) ,
	ATTACK_ADDTION ( 13 , NotifyType.NOTIFY_SCENE , "气血伤害增加" ) ,
	ATTACK_CUT ( 14 , NotifyType.NOTIFY_SCENE , "气血伤害减免" ) ,
	SOUL_ATTACK_ADDTION ( 15 , NotifyType.NOTIFY_SCENE , "元魂伤害增加" ) ,
	SOUL_ATTACK_CUT ( 16 , NotifyType.NOTIFY_SCENE , "元魂伤害减免" ) ,
	REGAIN_SOUL ( 17 , NotifyType.NOTIFY_SCENE , "每10秒回魂" ) ,
	REGAIN_BLOOD ( 18 , NotifyType.NOTIFY_SCENE , "每10秒回血" ) ,
	METAL ( 19 , NotifyType.NOTIFY_SCENE , "金" ) ,
	WOOD ( 20 , NotifyType.NOTIFY_SCENE , "木" ) ,
	WATER ( 21 , NotifyType.NOTIFY_SCENE , "水" ) ,
	FIRE ( 22 , NotifyType.NOTIFY_SCENE , "火" ) ,
	EARTH ( 23 , NotifyType.NOTIFY_SCENE , "土" ) ,
	METAL_DEFENCE ( 24 , NotifyType.NOTIFY_SCENE , "金 抗" ) ,
	WOOD_DEFENCE ( 25 , NotifyType.NOTIFY_SCENE , "木抗" ) ,
	WATER_DEFENCE ( 26 , NotifyType.NOTIFY_SCENE , "水抗" ) ,
	FIRE_DEFENCE ( 27 , NotifyType.NOTIFY_SCENE , "火抗" ) ,
	EARTH_DEFENCE ( 28 , NotifyType.NOTIFY_SCENE , "土抗" ) ,
	SPEED ( 29 , NotifyType.NOTIFY_NEARS , "速度" ) ,
	CUR_SOUL ( 30 , NotifyType.NOTIFY_NEARS , "当前元魂(生命值)" ) ,
	CUR_BLOOD ( 31 , NotifyType.NOTIFY_NEARS , "当前气血(蓝)" ) ,
	PROTECTION ( 32 , NotifyType.NOTIFY_NEARS , "无敌 1无敌，2非无敌" ) ,
	MAX_SOUL ( 33 , NotifyType.NOTIFY_NEARS , "最大元魂" ) ,
	MAX_BLOOD ( 34 , NotifyType.NOTIFY_NEARS , "最大气血" ) ,
	State ( 43 , NotifyType.NOTIFY_SCENE , "境界阶段" ) ,
	SkillStage ( 44 , NotifyType.NOTIFY_USER , "修炼阶段" ) ,
	Exp ( 45 , NotifyType.NOTIFY_USER , "经验" ) ,
	FightValue ( 46 , NotifyType.NOTIFY_SCENE , "战斗力" ) ,
	Level ( 47 , NotifyType.NOTIFY_NEARS , "等级" ) ,
	Mount ( 48 , NotifyType.NOTIFY_NEARS , "坐骑" ) ,
	Weapon ( 49 , NotifyType.NOTIFY_NEARS , "武器" ) ,
	VipLevel ( 50 , NotifyType.NOTIFY_NEARS , "vip等级" ) ,
	FaBao ( 51 , NotifyType.NOTIFY_NEARS , "法宝" ) ,
	BeiShi ( 52 , NotifyType.NOTIFY_NEARS , "背鉓" ) ,
	Clothes ( 53 , NotifyType.NOTIFY_NEARS , "时装" ) ,
	PetSoul ( 54 , NotifyType.NOTIFY_USER , " 炼魂(宠物炼魂)" ) ,
	PetPhysique ( 55 , NotifyType.NOTIFY_USER , "炼体(宠物炼体)" ) ,
	PetQuality ( 56 , NotifyType.NOTIFY_USER , "品质(宠物品质)" ) ,
	TOTALEXP ( 57 , NotifyType.NOTIFY_USER , "总经验" ) ,
	MAP_ID ( 58 , NotifyType.NOTIFY_USER , " 地图ID" ) ,
	MAP_KEY ( 59 , NotifyType.NOTIFY_USER , "地图KEY" ) ,
	TEAM_ID ( 60 , NotifyType.NOTIFY_NEARS , "组队" ) ,
	VIP_EXP ( 61 , NotifyType.NOTIFY_USER , "vip 经验" ) ,
	MANA ( 62 , NotifyType.NOTIFY_NEARS , "灵力" ) ,
	WEAPON_AWAKEN ( 63 , NotifyType.NOTIFY_NEARS , "武器觉醒" ) ,
	SOUL_EXP ( 64 , NotifyType.NOTIFY_SCENE , "魂幡经验" ) ,
	PK_VAL ( 65 , NotifyType.NOTIFY_NEARS , "PK 值" ) ,
	BATTLE_MODE ( 66 , NotifyType.NOTIFY_NEARS , "攻击模式" ) ,
	VIP_TEMPORARY ( 67 , NotifyType.NOTIFY_USER , "临时vip到期时间" ) ,
	AVTAR_CORRESPOND ( 68 , NotifyType.NOTIFY_SCENE , "分身默契等级" ) ,
	WORLDBOSS_TREASURE ( 69 , NotifyType.NOTIFY_NEARS , "世界BOSS触发夺宝中玩家宝箱数量" ) ,
	MONEY ( 711 , NotifyType.NOTIFY_USER , " 灵石" ) ,
	CASH ( 712 , NotifyType.NOTIFY_USER , " 仙玉" ) ,
	CASH_BIND ( 713 , NotifyType.NOTIFY_USER , "绑定仙玉" ) ,
	EQUIPEXP ( 714 , NotifyType.NOTIFY_USER , "装备经验" ) ,
	REPAIR ( 715 , NotifyType.NOTIFY_USER , "修为" ) ,
	POINTS ( 716 , NotifyType.NOTIFY_USER , "积分 " ) ,
	AVATAR_ENERGY ( 725 , NotifyType.NOTIFY_NEARS , "仙力 " );

	private int		value;
	private byte	notifyType;
	private String	description;
	
	
	private EnumAttr(int v, byte notifyType, String desc) {
		this.value = v;
		this.notifyType = notifyType;
		this.description = desc;
	}

	public int getValue() {
		return value;
	}
	
	public byte getNotifyType(){
		return notifyType;
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
