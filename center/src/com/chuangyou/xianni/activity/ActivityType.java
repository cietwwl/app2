package com.chuangyou.xianni.activity;

/**
 * 活动编号常量表
 * 
 * @author laofan
 *
 */
public enum ActivityType {
	PERSON_DART ( "个人运镖" , 1 ) ,
	UNITY_DART ( "帮会运镖" , 2 ) ,
	TIMING_DEMON_KING ( "定时妖王" , 3 ) ,
	INDIANA_ACTIVITIES ( "夺宝活动" , 4 ) ,
	DIGGING_UP_TREASURE ( "挖宝活动" , 5 ) ,
	RELIC_ADVENTURE ( "遗迹探险" , 6 ) ,
	ARENA ( "竞技场" , 7 ) ,
	PEAK_OF_THE_WAR ( " 巅峰之战" , 8 );
	private String	name;
	private int		value;

	public static ActivityType get(int code) {
		for (ActivityType type : ActivityType.values()) {
			if (type.getValue() == code) {
				return type;
			}
		}
		return null;
	}

	private ActivityType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
