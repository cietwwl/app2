package com.chuangyou.xianni.entity.item;

import com.chuangyou.xianni.entity.property.BaseProperty;

public class ItemTemplateInfo extends BaseProperty {
	private int id;

	private String name;
	private int masterType; // 主类型
	private int sonType; // 子类型

	private byte profession; // 1战士，2法师，3弓箭手

	private int mod; // 作者:模型id

	private String icon; // 作者:图标id

	private int level; // 作者:初始等级

	private byte sex; // 作者:0 无性别，1 男，2女

	private byte itemcolor; // 作者:1 白，2 绿，3 蓝，4 紫，5 黄，6红

	private int exp; // 作者:分解经验系数

	private int buy; // 作者:购买价格

	private int sell; // 作者:出售价格

	private int amount; // 作者: 叠加数量

	private int init_life_time; // 作者:存在时间，单位毫秒

	private int itemBase; // 作者:基础属性

	private int qualityCoefficient;// 品质系数

	private int grow; // 作者:成长系数

	private int attspeed; // 作者:武器攻击速度，单位毫秒

	private byte bind; // 作者:1 不绑定，2 绑定，3 装备绑定，4时间解绑

	private int bindtime; // 作者:解绑时间，毫秒

	private String id_action; // 作者:脚本

	private int jiachi1; // 作者:加持激活级别

	private int statistics1; // 作者: 加持激活属性

	private int jiachi2; // 作者: 加持激活级别

	private int statistics2; // 作者: 加持激活属性

	private int jiachi3; // 作者: 加持激活级别

	private int statistics3; // 作者: 加持激活属性

	private int jiachi4; // 作者: 加持激活级别

	private int statistics4; // 作者: 加持激活属性

	private int awakeskill1; // 作者:1级觉醒技能

	private int awakenum1; // 作者: 激活次数

	private int addmoneytype1; // 作者:恢复次数资金类型

	private int addmoneynum1; // 作者: 资金数值

	private int awakeskill2; // 作者:2 级觉醒技能

	private int awakenum2; // 作者: 激活次数

	private int addmoneytype2; // 作者: 恢复次数资金类型

	private int addmoneynum2; // 作者: 资金数值

	private int awakeskill3; // 作者:3 级觉醒技能

	private int awakenum3; // 作者: 激活次数

	private int addmoneytype3; // 作者: 恢复次数资金类型

	private int addmoneynum3; // 作者: 资金数值

	private int suit_id; // 作者:套装id

	private String des; // 作者:描述

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public byte getItemcolor() {
		return itemcolor;
	}

	public void setItemcolor(byte itemcolor) {
		this.itemcolor = itemcolor;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public int getSell() {
		return sell;
	}

	public void setSell(int sell) {
		this.sell = sell;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getInit_life_time() {
		return init_life_time;
	}

	public void setInit_life_time(int init_life_time) {
		this.init_life_time = init_life_time;
	}

	public int getItemBase() {
		return itemBase;
	}

	public void setItemBase(int itemBase) {
		this.itemBase = itemBase;
	}

	public int getQualityCoefficient() {
		return qualityCoefficient;
	}

	public void setQualityCoefficient(int qualityCoefficient) {
		this.qualityCoefficient = qualityCoefficient;
	}

	public int getGrow() {
		return grow;
	}

	public void setGrow(int grow) {
		this.grow = grow;
	}

	public int getAttspeed() {
		return attspeed;
	}

	public void setAttspeed(int attspeed) {
		this.attspeed = attspeed;
	}

	public byte getBind() {
		return bind;
	}

	public void setBind(byte bind) {
		this.bind = bind;
	}

	public int getBindtime() {
		return bindtime;
	}

	public void setBindtime(int bindtime) {
		this.bindtime = bindtime;
	}

	public String getId_action() {
		return id_action;
	}

	public void setId_action(String id_action) {
		this.id_action = id_action;
	}

	public int getJiachi1() {
		return jiachi1;
	}

	public void setJiachi1(int jiachi1) {
		this.jiachi1 = jiachi1;
	}

	public int getStatistics1() {
		return statistics1;
	}

	public void setStatistics1(int statistics1) {
		this.statistics1 = statistics1;
	}

	public int getJiachi2() {
		return jiachi2;
	}

	public void setJiachi2(int jiachi2) {
		this.jiachi2 = jiachi2;
	}

	public int getStatistics2() {
		return statistics2;
	}

	public void setStatistics2(int statistics2) {
		this.statistics2 = statistics2;
	}

	public int getJiachi3() {
		return jiachi3;
	}

	public void setJiachi3(int jiachi3) {
		this.jiachi3 = jiachi3;
	}

	public int getStatistics3() {
		return statistics3;
	}

	public void setStatistics3(int statistics3) {
		this.statistics3 = statistics3;
	}

	public int getJiachi4() {
		return jiachi4;
	}

	public void setJiachi4(int jiachi4) {
		this.jiachi4 = jiachi4;
	}

	public int getStatistics4() {
		return statistics4;
	}

	public void setStatistics4(int statistics4) {
		this.statistics4 = statistics4;
	}

	public int getAwakeskill1() {
		return awakeskill1;
	}

	public void setAwakeskill1(int awakeskill1) {
		this.awakeskill1 = awakeskill1;
	}

	public int getAwakenum1() {
		return awakenum1;
	}

	public void setAwakenum1(int awakenum1) {
		this.awakenum1 = awakenum1;
	}

	public int getAddmoneytype1() {
		return addmoneytype1;
	}

	public void setAddmoneytype1(int addmoneytype1) {
		this.addmoneytype1 = addmoneytype1;
	}

	public int getAddmoneynum1() {
		return addmoneynum1;
	}

	public void setAddmoneynum1(int addmoneynum1) {
		this.addmoneynum1 = addmoneynum1;
	}

	public int getAwakeskill2() {
		return awakeskill2;
	}

	public void setAwakeskill2(int awakeskill2) {
		this.awakeskill2 = awakeskill2;
	}

	public int getAwakenum2() {
		return awakenum2;
	}

	public void setAwakenum2(int awakenum2) {
		this.awakenum2 = awakenum2;
	}

	public int getAddmoneytype2() {
		return addmoneytype2;
	}

	public void setAddmoneytype2(int addmoneytype2) {
		this.addmoneytype2 = addmoneytype2;
	}

	public int getAddmoneynum2() {
		return addmoneynum2;
	}

	public void setAddmoneynum2(int addmoneynum2) {
		this.addmoneynum2 = addmoneynum2;
	}

	public int getAwakeskill3() {
		return awakeskill3;
	}

	public void setAwakeskill3(int awakeskill3) {
		this.awakeskill3 = awakeskill3;
	}

	public int getAwakenum3() {
		return awakenum3;
	}

	public void setAwakenum3(int awakenum3) {
		this.awakenum3 = awakenum3;
	}

	public int getAddmoneytype3() {
		return addmoneytype3;
	}

	public void setAddmoneytype3(int addmoneytype3) {
		this.addmoneytype3 = addmoneytype3;
	}

	public int getAddmoneynum3() {
		return addmoneynum3;
	}

	public void setAddmoneynum3(int addmoneynum3) {
		this.addmoneynum3 = addmoneynum3;
	}

	public int getSuit_id() {
		return suit_id;
	}

	public void setSuit_id(int suit_id) {
		this.suit_id = suit_id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getMasterType() {
		return masterType;
	}

	public void setMasterType(int masterType) {
		this.masterType = masterType;
	}

	public int getSonType() {
		return sonType;
	}

	public void setSonType(int sonType) {
		this.sonType = sonType;
	}

}
