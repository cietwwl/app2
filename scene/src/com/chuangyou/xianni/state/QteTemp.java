package com.chuangyou.xianni.state;

import com.chuangyou.xianni.campaign.StateCampaign;

public class QteTemp {
	private int qteId;
	private int eventId;
	private long createTime;
	private StateCampaign campaign;
	
	public QteTemp(int qteId, int eventId, long createTime, StateCampaign campaign) {
		super();
		this.qteId = qteId;
		this.eventId = eventId;
		this.createTime = createTime;
		this.campaign = campaign;
	}

	
	public int getQteId() {
		return qteId;
	}

	public void setQteId(int qteId) {
		this.qteId = qteId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}



	public StateCampaign getCampaign() {
		return campaign;
	}



	public void setCampaign(StateCampaign campaign) {
		this.campaign = campaign;
	}
	
	
	
}
