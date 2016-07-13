package com.chuangyou.xianni.entity.buffer;

/** 技能buffer模板数据 */
public class SkillBufferTemplateInfo {
	private int		templateId;		// 模板ID
	private String	bufferName;		// buffer名称
	private int		icon;			// buffer图标
	private int		isHelpful;		// 1 有益
	private int		type;			// 作用类型
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
	private int		isTips;			// 是否显示图标
	private int		isSave;			// 是否离线保存
	private int		valueType;		// 作用属性类型
	private int		value;			// 固定数值
	private int		valuePercent;	// 百分比

	private int		status;			// buff作用状态ID

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
