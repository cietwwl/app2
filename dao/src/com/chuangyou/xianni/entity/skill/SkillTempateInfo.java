package com.chuangyou.xianni.entity.skill;

import com.chuangyou.xianni.entity.property.SkillPropertyTemplateInfo;

/** 基础技能模板 */
public class SkillTempateInfo {
	private int templateId; // 技能ID
	private String templateName; // 技能名
	private String icons; // 技能图标
	private int masterType; // 主类型
	private int sonType; // 字类型
	private int grandsonType; // 三级类型
	private int level; // 技能品质
	private String preTemplateId; // 学习该技能前置技能，使用 X,X隔开，无则不填
	private int nextTempId; // 技能升级后，后置技能
	private int needGrades; // 学习需要等级
	private int useWay; // 使用方式
	private int actionId; // 主动技能的执行ID，对应执行模板SkillActionInfo
	private int needStone; // 技能升级需要灵石
	private int needRepair; // 技能升级需要修为
	private int needJade; // 技能升级需要仙玉
	private String needGoods; // 技能升级需要物品 格式 物品id,数量#物品id,数量

	private String propertyIds; // 技能增加属性ID
	private String sysBufferIds; // 添加的系统BUFF
	private String fightBufferIds; // 添加的战斗buff
	private String description; // 描述
	//private SkillPropertyTemplateInfo proTemp;// 属性模板

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
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

	public int getGrandsonType() {
		return grandsonType;
	}

	public void setGrandsonType(int grandsonType) {
		this.grandsonType = grandsonType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPreTemplateId() {
		return preTemplateId;
	}

	public void setPreTemplateId(String preTemplateId) {
		this.preTemplateId = preTemplateId;
	}

	public int getNextTempId() {
		return nextTempId;
	}

	public void setNextTempId(int nextTempId) {
		this.nextTempId = nextTempId;
	}

	public int getNeedGrades() {
		return needGrades;
	}

	public void setNeedGrades(int needGrades) {
		this.needGrades = needGrades;
	}

	public int getUseWay() {
		return useWay;
	}

	public void setUseWay(int useWay) {
		this.useWay = useWay;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getNeedStone() {
		return needStone;
	}

	public void setNeedStone(int needStone) {
		this.needStone = needStone;
	}

	public int getNeedRepair() {
		return needRepair;
	}

	public void setNeedRepair(int needRepair) {
		this.needRepair = needRepair;
	}

	public int getNeedJade() {
		return needJade;
	}

	public void setNeedJade(int needJade) {
		this.needJade = needJade;
	}

	public String getNeedGoods() {
		return needGoods;
	}

	public void setNeedGoods(String needGoods) {
		this.needGoods = needGoods;
	}

	public String getSysBufferIds() {
		return sysBufferIds;
	}

	public void setSysBufferIds(String sysBufferIds) {
		this.sysBufferIds = sysBufferIds;
	}

	public String getFightBufferIds() {
		return fightBufferIds;
	}

	public void setFightBufferIds(String fightBufferIds) {
		this.fightBufferIds = fightBufferIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPropertyIds() {
		return propertyIds;
	}

	public void setPropertyIds(String propertyIds) {
		this.propertyIds = propertyIds;
	}

//	public SkillPropertyTemplateInfo getProTemp() {
//		return proTemp;
//	}
//
//	public void setProTemp(SkillPropertyTemplateInfo proTemp) {
//		this.proTemp = proTemp;
//	}

}
