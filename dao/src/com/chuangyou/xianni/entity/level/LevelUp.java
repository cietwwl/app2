package com.chuangyou.xianni.entity.level;

public class LevelUp implements Comparable<LevelUp> {

	/**
	 * 升级类型1人物等级
	 */
	private int type;
	
	/**
	 * 等级
	 */
	private int level;
	
	/**
	 * 升级需要经验
	 */
	private long exp;
	
	/**
	 * 激活条件(备用字段)
	 */
	private int activeObject;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	@Override
	public int compareTo(LevelUp arg0) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getLevel(), arg0.getLevel());
	}

	public int getActiveObject() {
		return activeObject;
	}

	public void setActiveObject(int activeObject) {
		this.activeObject = activeObject;
	}
}
