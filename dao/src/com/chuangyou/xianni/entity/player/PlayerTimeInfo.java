package com.chuangyou.xianni.entity.player;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

/**
 * 玩家时间、硽码相关信息
 * 
 * @author Gxf
 *
 */
public class PlayerTimeInfo extends DataObject {

	private long playerId;
	private int sigleCampCount;	// 每日单人副本数量
	private Date resetTime;		// 重置时间
	private int currRefreshId;  // 当前的id
	private String beadRefreshId;		// 天逆珠刷新后获得的id
	private Date beadRefreshDateTime;// 天逆珠刷新的时间

	public int getSigleCampCount() {
		return sigleCampCount;
	}

	public void setSigleCampCount(int sigleCampCount) {
		setOp(Option.Update);
		this.sigleCampCount = sigleCampCount;
	}

	public Date getResetTime() {
		return resetTime;
	}

	public void setResetTime(Date resetTime) {
		setOp(Option.Update);
		this.resetTime = resetTime;
	}

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

}
