package com.chuangyou.xianni.entity.spawn;

import com.chuangyou.common.util.Vector3;

public class SpawnInfo {
	private int		id;								// ID

	private int		mapid;							// 地图id

	private int		bound_x;						// x轴

	private int		bound_y;						// y轴

	private int		bound_z;						// z轴

	private Vector3	position	= new Vector3();	// 出生点

	private int		initStatu;						// 初始节点状态

	private int		tagId;							// 唯一标记ID
	private int[]	preSpawanIdAttr;				// 前置节点集

	private int[]	nextSpawanIdAttr;				// 后置节点集

	private int		wakeType;						// 激活下一个节点类型 0 默认为
													// 节点结束激活下一个，1 节点开始即调用

	private int		wakeDelay;						// 延迟多长时间唤醒下一个节点

	private int		restType;						// 刷新模式\r\n1：普通模式，死亡后计算刷新时间刷新\r\n2：boss模式，刷新后计算刷新时间重新刷新\r\n3：副本模式，死亡后不刷新\r\n其他特殊模式待有需求时添加

	private int		restSecs;						// 死亡刷新间隔时间

	private int		maxCount;						// 刷怪数量\r\n1：定点刷\r\n＞1：在定点周围1米范围刷多个怪

	private int		toalCount;						// 怪物总数(刷完节点销毁,0为永远刷新)

	private int		campaignFeatures;				// 节点在副本中类型 0 普通，1 副本出生点 2
													// 副本复活点 3 副本结束点

	private int		entityType;						// 实体类型(由于历史原因，采用此字段，作为节点说明字段)

	private int		entityId;						// 实体ID

	private int		timerType;						// ` int(11) DEFAULT NULL
													// COMMENT
													// '1:每天定时刷\r\n2：utc时间范围内刷',

	private String	timerBegin;						// ` varchar(11) DEFAULT
													// NULL COMMENT
													// '开始刷新时间\r\n配0为不限时\r\n时间格式hhmmss，开始时间刷新怪物，结束时间删除所有怪物',

	private String	timerEnd;						// ` varchar(11) DEFAULT
													// NULL COMMENT
													// '结束刷新时间',

	private int		initSecs;						// ` int(11) DEFAULT NULL
													// COMMENT
													// '开服刷怪延迟时间\r\n避免开服瞬间刷新太多怪物导致卡死',

	private int		param1;							// 节点参数1

	private int		param2;							// 节点参数2

	private int		param3;							// 节点参数3

	private int		param4;							// 节点参数4

	private String	strParam1;						// 节点参数4

	private String	strParam2;						// 节点参数4

	private String	strParam3;						// 节点参数4

	public int[] getPreSpawanIdAttr() {
		return preSpawanIdAttr;
	}

	public void setPreSpawanIdAttr(int[] preSpawanIdAttr) {
		this.preSpawanIdAttr = preSpawanIdAttr;
	}

	public int[] getNextSpawanIdAttr() {
		return nextSpawanIdAttr;
	}

	public void setNextSpawanIdAttr(int[] nextSpawanIdAttr) {
		this.nextSpawanIdAttr = nextSpawanIdAttr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMapid() {
		return mapid;
	}

	public void setMapid(int mapid) {
		this.mapid = mapid;
	}

	public int getBound_x() {
		return bound_x;
	}

	public void setBound_x(int bound_x) {
		this.bound_x = bound_x;
	}

	public int getBound_y() {
		return bound_y;
	}

	public void setBound_y(int bound_y) {
		this.bound_y = bound_y;
	}

	public int getBound_z() {
		return bound_z;
	}

	public void setBound_z(int bound_z) {
		this.bound_z = bound_z;
	}

	public int getInitStatu() {
		return initStatu;
	}

	public void setInitStatu(int initStatu) {
		this.initStatu = initStatu;
	}

	public void setPreSpawanId(String preSpawanId) {
		// this.preSpawanId = preSpawanId;
		if (preSpawanId != null) {
			String[] ids = preSpawanId.split(",");
			if (ids != null && ids.length > 0) {
				preSpawanIdAttr = new int[ids.length];
				for (int i = 0; i < ids.length; i++) {
					if (!ids[i].trim().equals("")) {
						preSpawanIdAttr[i] = Integer.valueOf(ids[i]);
					}
				}
			}
		}
	}

	public void setNextSpawanId(String nextSpawanId) {
		// this.nextSpawanId = nextSpawanId;
		if (nextSpawanId != null) {
			String[] ids = nextSpawanId.split(",");
			if (ids != null && ids.length > 0) {
				nextSpawanIdAttr = new int[ids.length];
				for (int i = 0; i < ids.length; i++) {
					if (!ids[i].trim().equals("")) {
						nextSpawanIdAttr[i] = Integer.valueOf(ids[i]);
					}
				}
			}
		}

	}

	public int getRestType() {
		return restType;
	}

	public void setRestType(int restType) {
		this.restType = restType;
	}

	public int getRestSecs() {
		return restSecs;
	}

	public void setRestSecs(int restSecs) {
		this.restSecs = restSecs;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getToalCount() {
		return toalCount;
	}

	public void setToalCount(int toalCount) {
		this.toalCount = toalCount;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getTimerType() {
		return timerType;
	}

	public void setTimerType(int timerType) {
		this.timerType = timerType;
	}

	public String getTimerBegin() {
		return timerBegin;
	}

	public void setTimerBegin(String timerBegin) {
		this.timerBegin = timerBegin;
	}

	public String getTimerEnd() {
		return timerEnd;
	}

	public void setTimerEnd(String timerEnd) {
		this.timerEnd = timerEnd;
	}

	public int getInitSecs() {
		return initSecs;
	}

	public void setInitSecs(int initSecs) {
		this.initSecs = initSecs;
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

	public String getStrParam1() {
		return strParam1;
	}

	public void setStrParam1(String strParam1) {
		this.strParam1 = strParam1;
	}

	public String getStrParam2() {
		return strParam2;
	}

	public void setStrParam2(String strParam2) {
		this.strParam2 = strParam2;
	}

	public String getStrParam3() {
		return strParam3;
	}

	public void setStrParam3(String strParam3) {
		this.strParam3 = strParam3;
	}

	public int getCampaignFeatures() {
		return campaignFeatures;
	}

	public void setCampaignFeatures(int campaignFeatures) {
		this.campaignFeatures = campaignFeatures;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getWakeDelay() {
		return wakeDelay;
	}

	public void setWakeDelay(int wakeDelay) {
		this.wakeDelay = wakeDelay;
	}

	public int getWakeType() {
		return wakeType;
	}

	public void setWakeType(int wakeType) {
		this.wakeType = wakeType;
	}

}
