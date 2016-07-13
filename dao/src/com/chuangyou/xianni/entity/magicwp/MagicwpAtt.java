package com.chuangyou.xianni.entity.magicwp;

import com.chuangyou.xianni.entity.DataObject;

public class MagicwpAtt extends DataObject {

	/** 角色ID */
	private long playerId;
	/** 当前选择出战的法宝ID */
	private int curMagicwpId;
	/** 属性丹使用数量 */
	private int useDanNum;
	
	public MagicwpAtt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MagicwpAtt(long playerId) {
		super();
		this.playerId = playerId;
		this.curMagicwpId = 0;
		this.useDanNum = 0;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getCurMagicwpId() {
		return curMagicwpId;
	}
	public void setCurMagicwpId(int curMagicwpId) {
		this.curMagicwpId = curMagicwpId;
	}
	public int getUseDanNum() {
		return useDanNum;
	}
	public void setUseDanNum(int useDanNum) {
		this.useDanNum = useDanNum;
	}
	@Override
	public String toString() {
		return "MagicwpAtt [roleId=" + playerId + ", curMagicwpId=" + curMagicwpId + ", useDanNum=" + useDanNum + "]";
	}
}
