package com.chuangyou.xianni.entity.inverseBead;

import com.chuangyou.xianni.entity.DataObject;

public class PlayerInverseBead extends DataObject {
	private int id;
	private long playerId;
	private int fiveElements;
	private int marking;
	private int stage;
	private int val;
	private int attVal;
	private int attVal2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getFiveElements() {
		return fiveElements;
	}

	public void setFiveElements(int fiveElements) {
		this.fiveElements = fiveElements;
	}

	public int getMarking() {
		return marking;
	}

	public void setMarking(int marking) {
		this.marking = marking;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getAttVal() {
		return attVal;
	}

	public void setAttVal(int attVal) {
		this.attVal = attVal;
	}

	public int getAttVal2() {
		return attVal2;
	}

	public void setAttVal2(int attVal2) {
		this.attVal2 = attVal2;
	}

}
