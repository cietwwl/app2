package com.chuangyou.xianni.entity.vip;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;

/**
 * 玩家vip记录
 * 
 * @author Administrator
 */

public class PlayerVipReceive extends DataObject {
	private int		id;
	private long	playerId;
	private int		vipType;
	private int		vipId;
	private Date	receiveTime;

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

	public int getVipType() {
		return vipType;
	}

	public void setVipType(int vipType) {
		this.vipType = vipType;
	}

	public int getVipId() {
		return vipId;
	}

	public void setVipId(int vipId) {
		this.vipId = vipId;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

}
