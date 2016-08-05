package com.chuangyou.xianni.entity.campaign;

import java.util.HashSet;
import java.util.Set;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

/**
 * 玩家完成副本记录表
 */
public class CampaignRecordInfo extends DataObject {
	private int				id;			// 自增ID
	private long			playerId;	// 玩家ID
	private int				campaignId;	// 副本ID
	private int				point;		// 挑战点
	private int				statu;		// 完成状态
	private long			updataTime;	// 完成时间
	private String			taskIds;	// 任务ID集
	private Set<Integer>	attrTaskIds;// 任务ID数组

	public CampaignRecordInfo(long playerId, int campaignId) {
		this.playerId = playerId;
		this.campaignId = campaignId;
		this.updataTime = System.currentTimeMillis();
	}

	public CampaignRecordInfo() {

	}

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

	public long getUpdataTime() {
		return updataTime;
	}

	public void setUpdataTime(long updataTime) {
		this.updataTime = updataTime;
	}

	public String getTaskIds() {
		if (getAttrTaskIds() == null || getAttrTaskIds().size() == 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (Integer id : attrTaskIds) {
			buffer.append(id + ",");
		}
		return buffer.substring(0, buffer.length() - 1);
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
		if (this.taskIds != null && !this.taskIds.equals("")) {
			String[] ids = this.taskIds.split(",");
			Set<Integer> set = new HashSet<>();
			for (String id : ids) {
				set.add(Integer.valueOf(id));
			}
			setAttrTaskIds(set);
		}
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		if (this.point != point) {
			setOp(Option.Update);
		}
		this.point = point;

	}

	public Set<Integer> getAttrTaskIds() {
		return attrTaskIds;
	}

	public void setAttrTaskIds(Set<Integer> attrTaskIds) {
		this.attrTaskIds = attrTaskIds;
	}

	public void recordTask(int taskId) {
		if(attrTaskIds == null){
			attrTaskIds = new HashSet<>();
		}
		attrTaskIds.add(taskId);
		setOp(Option.Update);
	}

}
