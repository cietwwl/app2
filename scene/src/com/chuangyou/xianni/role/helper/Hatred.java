package com.chuangyou.xianni.role.helper;

import com.chuangyou.xianni.pool.MemoryObject;

public class Hatred implements MemoryObject, Comparable<Hatred> {

	// 仇恨度
	private int		hatred;
	// 仇恨对象
	private long	target;
	// 最后一次攻击时间
	private long	lastAttack;

	public int getHatred() {
		return hatred;
	}

	public void setHatred(int hatred) {
		this.hatred = hatred;
	}

	public long getTarget() {
		return target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	public long getLastAttack() {
		return lastAttack;
	}

	public void setLastAttack(long lastAttack) {
		this.lastAttack = lastAttack;
	}

	@Override
	public int compareTo(Hatred o) {
		return o.getHatred() - getHatred();
	}

	@Override
	public void release() {
		this.hatred = 0;
		this.lastAttack = 0;
		this.target = 0;
	}

}
