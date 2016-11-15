package com.chuangyou.xianni.entity.mount;

import com.chuangyou.xianni.entity.DataObject;

public class MountSpecialGet extends DataObject {
	
	/** 激活结果-激活成功 */
	public static final int ACTIVATE_SUCCESS = 0;
	/** 激活结果-失败，坐骑不是特殊坐骑不可激活 */
	public static final int NOT_SPECIAL = 1;
	/** 激活结果-失败，坐骑已经激活 */
	public static final int ALREADY_ACTIVATED = 2;

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
