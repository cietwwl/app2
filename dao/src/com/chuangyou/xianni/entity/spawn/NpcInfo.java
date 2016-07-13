package com.chuangyou.xianni.entity.spawn;

public class NpcInfo {
	private int		npcId;		// int(11) NOT NULL,

	private String	name;		// ` varchar(255) DEFAULT NULL,

	private int		type;

	private int		redValue;	// ` int(11) DEFAULT NULL COMMENT '血',

	private int		level;		// ` int(11) DEFAULT '1' COMMENT '等级',

	private int		skin;

	private String	scriptId;
	
	/**
	 * 当type为4：代表CD时间
	 */
	private int intParam1;
	
	private int intParam2;
	
	private String strParam3;
	

	public NpcInfo() {
		super();
	}

	public int getNpcId() {
		return npcId;
	}

	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRedValue() {
		return redValue;
	}

	public void setRedValue(int redValue) {
		this.redValue = redValue;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSkin() {
		return skin;
	}

	public void setSkin(int skin) {
		this.skin = skin;
	}

	public String getScriptId() {
		return scriptId;
	}

	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIntParam1() {
		return intParam1;
	}

	public void setIntParam1(int intParam1) {
		this.intParam1 = intParam1;
	}

	public int getIntParam2() {
		return intParam2;
	}

	public void setIntParam2(int intParam2) {
		this.intParam2 = intParam2;
	}

	public String getStrParam3() {
		return strParam3;
	}

	public void setStrParam3(String strParam3) {
		this.strParam3 = strParam3;
	}
}
