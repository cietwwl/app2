package com.chuangyou.xianni.entity.fieldBoss;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class FieldBossDieInfo extends DataObject {

	/** 怪物ID */
	private int monsterId;
	
	/** 死亡时间 */
	private Date deathTime;
	
	/** 下次刷新时间 */
	private Date nextTime;

	public int getMonsterId() {
		return monsterId;
	}

	public void setMonsterId(int monsterId) {
		this.monsterId = monsterId;
	}

	public Date getDeathTime() {
		return deathTime;
	}

	public void setDeathTime(Date deathTime) {
		setOp(Option.Update);
		this.deathTime = deathTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		setOp(Option.Update);
		this.nextTime = nextTime;
	}
	
}
