package com.chuangyou.xianni.entity.truck;

import com.chuangyou.common.util.Vector3;

public class TruckCheckPointConfig  {

	private int id;
	
	/**
	 * 路点类型1.开始点 2.中级的 3.结束点
	 */
	private int pointType;
	
	/**
	 * 对应的npcid
	 */
	private int npcId;
	
	/**
	 * 位置点
	 */
	private Vector3 point;
	
	/**
	 * 转场点下一个场景
	 */
	private int nextScene;
	
	/**
	 * 转场点下一个点的位置
	 */
	private Vector3 nextPoint;
	
	/**
	 * 个人运镖-购买物资
	 */
	private int individualNum;
	
	/**
	 * 帮派运镖-购买物资
	 */
	private int FactionNum;

	public int getIndividualNum() {
		return individualNum;
	}

	public void setIndividualNum(int individualNum) {
		this.individualNum = individualNum;
	}

	public int getFactionNum() {
		return FactionNum;
	}

	public void setFactionNum(int factionNum) {
		FactionNum = factionNum;
	}

	public int getNextScene() {
		return nextScene;
	}

	public void setNextScene(int nextScene) {
		this.nextScene = nextScene;
	}

	public Vector3 getNextPoint() {
		return nextPoint;
	}

	public void setNextPoint(Vector3 nextPoint) {
		this.nextPoint = nextPoint;
	}

	public Vector3 getPoint() {
		return point;
	}

	public void setPoint(Vector3 point) {
		this.point = point;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPointType() {
		return pointType;
	}

	public void setPointType(int pointType) {
		this.pointType = pointType;
	}

	public int getNpcId() {
		return npcId;
	}

	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}
}
