package com.chuangyou.xianni.entity.campaign;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;

/**
 * 玩家完成副本记录表
 */
public class CampaignRecordInfo extends DataObject {
	private int		id;			// 自增ID
	private long	playerId;	// 玩家ID
	private int		campaignId;	// 副本ID
	private int		point;		// 挑战点
	private int		statu;		// 完成状态
	private int		assess;		// 最好成绩
	private Date	updataTime;	// 完成时间

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

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public int getAssess() {
		return assess;
	}

	public void setAssess(int assess) {
		this.assess = assess;
	}

	public Date getUpdataTime() {
		return updataTime;
	}

	public void setUpdataTime(Date updataTime) {
		this.updataTime = updataTime;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
