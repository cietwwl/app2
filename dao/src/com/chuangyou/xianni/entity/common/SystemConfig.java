package com.chuangyou.xianni.entity.common;

public class SystemConfig {
	private String key;//VARCHAR(255) NOT NULL
	private int value;//VARCHAR(255) NULL DEFAULT NULL
	private String _desc;//VARCHAR(255) NULL DEFAULT NULL
	public SystemConfig() {
		super();
	}
	public SystemConfig(String key, int value, String _desc) {
		super();
		this.key = key;
		this.value = value;
		this._desc = _desc;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String get_desc() {
		return _desc;
	}
	public void set_desc(String _desc) {
		this._desc = _desc;
	}
	@Override
	public String toString() {
		return "SystemConfig [key=" + key + ", value=" + value + ", _desc=" + _desc + "]";
	}
	
}
