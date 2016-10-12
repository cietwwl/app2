package com.chuangyou.xianni.entity.field;

import com.chuangyou.common.util.Vector3;

public class FieldInfo {
	private int		mapKey;							// 模板ID
	private String	name;							// 地图名
	private int		campaignId;						// 所属副本ID
	private int		campaignIndex;					// 所属副本序号
	private byte	type;							// 1公共地图 2副本地图
	private String	desc;							// 描述
	private Vector3	position	= new Vector3();	// 出生点
	private int		initScriptId;					// 初始化脚本ID
	private String	resName;						// 资源名称
	private int		battleType;						// 是否pk地图
	private String	startBattleTime;				// pk保护开始时间
	private String	endBattleTime;					// pk保护结束时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public int getCampaignIndex() {
		return campaignIndex;
	}

	public void setCampaignIndex(int campaignIndex) {
		this.campaignIndex = campaignIndex;
	}

	public int getMapKey() {
		return mapKey;
	}

	public void setMapKey(int mapKey) {
		this.mapKey = mapKey;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public int getInitScriptId() {
		return initScriptId;
	}

	public void setInitScriptId(int initScriptId) {
		this.initScriptId = initScriptId;
	}

	public int getBattleType() {
		return battleType;
	}

	public void setBattleType(int battleType) {
		this.battleType = battleType;
	}

	public String getStartBattleTime() {
		return startBattleTime;
	}

	public void setStartBattleTime(String startBattleTime) {
		this.startBattleTime = startBattleTime;
	}

	public String getEndBattleTime() {
		return endBattleTime;
	}

	public void setEndBattleTime(String endBattleTime) {
		this.endBattleTime = endBattleTime;
	}

}
