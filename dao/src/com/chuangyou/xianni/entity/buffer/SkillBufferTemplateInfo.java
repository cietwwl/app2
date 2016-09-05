package com.chuangyou.xianni.entity.buffer;

/** 技能buffer模板数据 */
public class SkillBufferTemplateInfo {
	private int		templateId;		// 模板ID
	private String	bufferName;		// buffer名称
	private int		icon;			// buffer图标
	private int		isHelpful;		// 1 有益
	private int		type;			// 作用类型
	private int		fromType;		// 源类型
	private int		targetType;		// 作用目标
	private int		targetLimit;	// 目标限定
	private int		exeWay;			// 生效时机
	private int		durableType;	// 耐久类型
	private int		exeTime;		// 生效时间
	private int		exeCount;		// 生效次数
	private int		cooldown;		// 生效间隔
	private int		overlayType;	// 叠加类型

	/*
	 * 0 不替换 1 新buff替换老buff 2 新buff与老buff保留时间最长的 3 新buff与老buff保留次数最多的 4
	 * 新buff的时间与次数增加到老buff上 5
	 */
	private int		overlayWay;		// 操作方式
	private int		level;			// 定义等级
	private int		isTips;			// 是否显示图标
	private int		isSave;			// 是否离线保存
	private int		valueType;		// 作用属性类型
	private int		value;			// 固定数值
	private int		valuePercent;	// 百分比

	private int		valueType1;		// 作用属性类型1
	private int		value1;			// 固定数值1
	private int		valuePercent1;	// 百分比1

	private int		status;			// buff作用状态ID
	private int		costCount;		// 消耗类型

	private int		param1;			// 配置参数1
	private int		param2;			// 配置参数2
	private int		param3;			// 配置参数3
	private int		param4;			// 配置参数4

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getBufferName() {
		return bufferName;
	}

	public void setBufferName(String bufferName) {
		this.bufferName = bufferName;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getIsHelpful() {
		return isHelpful;
	}

	public void setIsHelpful(int isHelpful) {
		this.isHelpful = isHelpful;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public int getTargetLimit() {
		return targetLimit;
	}

	public void setTargetLimit(int targetLimit) {
		this.targetLimit = targetLimit;
	}

	public int getExeWay() {
		return exeWay;
	}

	public void setExeWay(int exeWay) {
		this.exeWay = exeWay;
	}

	public int getDurableType() {
		return durableType;
	}

	public void setDurableType(int durableType) {
		this.durableType = durableType;
	}

	public int getExeTime() {
		return exeTime;
	}

	public void setExeTime(int exeTime) {
		this.exeTime = exeTime;
	}

	public int getExeCount() {
		return exeCount;
	}

	public void setExeCount(int exeCount) {
		this.exeCount = exeCount;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getIsTips() {
		return isTips;
	}

	public void setIsTips(int isTips) {
		this.isTips = isTips;
	}

	public int getValueType() {
		return valueType;
	}

	public void setValueType(int valueType) {
		this.valueType = valueType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValuePercent() {
		return valuePercent;
	}

	public void setValuePercent(int valuePercent) {
		this.valuePercent = valuePercent;
	}

	public int getIsSave() {
		return isSave;
	}

	public void setIsSave(int isSave) {
		this.isSave = isSave;
	}

	public int getOverlayType() {
		return overlayType;
	}

	public void setOverlayType(int overlayType) {
		this.overlayType = overlayType;
	}

	public int getOverlayWay() {
		return overlayWay;
	}

	public void setOverlayWay(int overlayWay) {
		this.overlayWay = overlayWay;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValuePercent1() {
		return valuePercent1;
	}

	public void setValuePercent1(int valuePercent1) {
		this.valuePercent1 = valuePercent1;
	}

	public int getValueType1() {
		return valueType1;
	}

	public void setValueType1(int valueType1) {
		this.valueType1 = valueType1;
	}

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public int getParam2() {
		return param2;
	}

	public void setParam2(int param2) {
		this.param2 = param2;
	}

	public int getParam3() {
		return param3;
	}

	public void setParam3(int param3) {
		this.param3 = param3;
	}

	public int getParam4() {
		return param4;
	}

	public void setParam4(int param4) {
		this.param4 = param4;
	}

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public int getCostCount() {
		return costCount;
	}

	public void setCostCount(int costCount) {
		this.costCount = costCount;
	}

}
