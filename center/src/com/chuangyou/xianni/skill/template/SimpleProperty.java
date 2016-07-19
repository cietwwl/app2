package com.chuangyou.xianni.skill.template;

public class SimpleProperty {
	private int				type;				// 属性ID
	private int				dataType;			// 数据类型 1 固定数值,2 提升万分比,3 负固定数值,4
												// 负万分比
	private int				value;				// 数值

	public static final int	NUMERICAL		= 1;
	public static final int	PERCENTAGE		= 2;
	public static final int	DECR_NUMERICAL	= 3;
	public static final int	DECR_PERCENTAGE	= 4;

	public boolean isPre() {
		return this.dataType == PERCENTAGE || this.dataType == DECR_PERCENTAGE;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

}
