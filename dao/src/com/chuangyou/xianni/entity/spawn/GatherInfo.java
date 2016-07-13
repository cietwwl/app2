package com.chuangyou.xianni.entity.spawn;

/**
 * 采集物+触发点
 * @author laofan
 *
 */
public class GatherInfo {
	
	
	
	
	
	
	/**  采集物  */
	public static final int TYPE_GATHER = 4;
	/**  触发点   */
	public static final int TYPE_TRIGGER = 5;
	
	/////////////////////////////////////////////////////////
	/**
	 *  唯一ID 
	 *  */
	public int id;
	
	/**
	 * 类型
	 * 4:采集物 5:触发器
	 */
	public int type;
	
	/**
	 * 名称
	 */
	public String name;
	
	/**
	 * 读条时间  单位：秒
	 */
	public int time;
	
	
	/**
	 * 脚本
	 */
	public String scriptId;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public String getScriptId() {
		return scriptId;
	}


	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}
	
	
}
