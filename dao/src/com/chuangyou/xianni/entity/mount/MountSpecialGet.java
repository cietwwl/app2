package com.chuangyou.xianni.entity.mount;

import com.chuangyou.xianni.entity.DataObject;

public class MountSpecialGet extends DataObject {

	private long playerId;
	private int mountId;
	
	public MountSpecialGet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MountSpecialGet(long playerId, int mountId) {
		super();
		this.playerId = playerId;
		this.mountId = mountId;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getMountId() {
		return mountId;
	}
	public void setMountId(int mountId) {
		this.mountId = mountId;
	}
	@Override
	public String toString() {
		return "MountSpecialGet [roleId=" + playerId + ", mountId=" + mountId + "]";
	}
}
