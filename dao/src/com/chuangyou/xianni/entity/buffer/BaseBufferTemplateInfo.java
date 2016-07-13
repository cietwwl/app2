package com.chuangyou.xianni.entity.buffer;

/** 基础buffer模板类型 */
public class BaseBufferTemplateInfo {
	private int		templateId;		// 模板ID
	private String	templateName;	// bf名称
	private int		types;			// 类型
	private int		grades;			// bf等级
	private String	icon;			// 图标
	private int		addWay;			// 数据类型
	private int		canSuperpose;	// 是否允许叠加
	private int		dataType1;		// 数据类型
	private int		dataValue1;		// 数据值
	private int		dataType2;		// 数据类型
	private int		dataValue2;		// 数据值
	private int		dataType3;		// 数据类型
	private int		dataValue3;		// 数据值
	private int		isTips;			// 是否显示图标

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public int getGrades() {
		return grades;
	}

	public void setGrades(int grades) {
		this.grades = grades;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getAddWay() {
		return addWay;
	}

	public void setAddWay(int addWay) {
		this.addWay = addWay;
	}

	public int getCanSuperpose() {
		return canSuperpose;
	}

	public void setCanSuperpose(int canSuperpose) {
		this.canSuperpose = canSuperpose;
	}

	public int getDataType1() {
		return dataType1;
	}

	public void setDataType1(int dataType1) {
		this.dataType1 = dataType1;
	}

	public int getDataValue1() {
		return dataValue1;
	}

	public void setDataValue1(int dataValue1) {
		this.dataValue1 = dataValue1;
	}

	public int getDataType2() {
		return dataType2;
	}

	public void setDataType2(int dataType2) {
		this.dataType2 = dataType2;
	}

	public int getDataValue2() {
		return dataValue2;
	}

	public void setDataValue2(int dataValue2) {
		this.dataValue2 = dataValue2;
	}

	public int getDataType3() {
		return dataType3;
	}

	public void setDataType3(int dataType3) {
		this.dataType3 = dataType3;
	}

	public int getDataValue3() {
		return dataValue3;
	}

	public void setDataValue3(int dataValue3) {
		this.dataValue3 = dataValue3;
	}

	public int getIsTips() {
		return isTips;
	}

	public void setIsTips(int isTips) {
		this.isTips = isTips;
	}

}
