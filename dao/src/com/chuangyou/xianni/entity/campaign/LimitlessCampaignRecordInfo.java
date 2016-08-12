package com.chuangyou.xianni.entity.campaign;

import com.chuangyou.xianni.entity.DataObject;

/** 爬塔类副本记录 */
public class LimitlessCampaignRecordInfo extends DataObject{
	private long	playerId;
	private int		campaignId;	// 通过副本
	private long	updataTime;	// 更新时间

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public long getUpdataTime() {
		return updataTime;
	}

	public void setUpdataTime(long updataTime) {
		this.updataTime = updataTime;
	}

}
