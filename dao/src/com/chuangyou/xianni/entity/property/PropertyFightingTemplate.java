package com.chuangyou.xianni.entity.property;

public class PropertyFightingTemplate {
	private int		property;	// 属性类型
	private String	pname;		// 属性名
	private int		fighting;	// 战斗力权重

	public int getProperty() {
		return property;
	}

	public void setProperty(int property) {
		this.property = property;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getFighting() {
		return fighting;
	}

	public void setFighting(int fighting) {
		this.fighting = fighting;
	}

}
