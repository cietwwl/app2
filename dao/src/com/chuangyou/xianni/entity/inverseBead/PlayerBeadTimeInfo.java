package com.chuangyou.xianni.entity.inverseBead;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class PlayerBeadTimeInfo extends DataObject {

	private long playerId;
	private int currRefreshId;  // 当前的id
	private String beadRefreshId;		// 天逆珠刷新后获得的id
	private Date beadRefreshDateTime;// 天逆珠刷新的时间
	private int auraNum;	// 灵气液个数
	private Date auraRefreshDateTime;// 灵气液刷新时间

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getCurrRefreshId() {
		return currRefreshId;
	}

	public void setCurrRefreshId(int currRefreshId) {
		setOp(Option.Update);
		this.currRefreshId = currRefreshId;
	}

	public String getBeadRefreshId() {
		return beadRefreshId;
	}

	public void setBeadRefreshId(String beadRefreshId) {
		setOp(Option.Update);
		this.beadRefreshId = beadRefreshId;
	}

	public Date getBeadRefreshDateTime() {
		return beadRefreshDateTime;
	}

	public void setBeadRefreshDateTime(Date beadRefreshDateTime) {
		setOp(Option.Update);
		this.beadRefreshDateTime = beadRefreshDateTime;
	}

	public int getAuraNum() {
		return auraNum;
	}

	public void setAuraNum(int auraNum) {
		setOp(Option.Update);
		this.auraNum = auraNum;
	}

	public Date getAuraRefreshDateTime() {
		return auraRefreshDateTime;
	}

	public void setAuraRefreshDateTime(Date auraRefreshDateTime) {
		setOp(Option.Update);
		this.auraRefreshDateTime = auraRefreshDateTime;
	}

}
