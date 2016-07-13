package com.chuangyou.xianni.warfield.spawn;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.node.CampaignNodeDecorator;
import com.chuangyou.xianni.constant.SpwanInfoType;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;

public class SpwanNode {
	protected int					nodeType;
	protected NodeState				state;
	protected SpawnInfo				spwanInfo;
	protected Field					field;
	protected int					campaignId;	// 所在副本ID
	protected CampaignNodeDecorator	decorator;	// 副本功能修饰器
	protected int					blood;		// 节点血量（适用于需要循环开闭的节点，如传送阵）

	public SpwanNode(SpawnInfo spwanInfo, Field field) {
		this.spwanInfo = spwanInfo;
		this.field = field;
		this.campaignId = field.getCampaignId();
		this.decorator = CampaignNodeDecorator.createDecorator(spwanInfo.getCampaignFeatures());
	}

	public void build() {
		// 副本分组节点添加进分组管理
		Campaign campaign = CampaignMgr.getCampagin(this.getCampaignId());
		if (campaign != null) {
			decorator.build(campaign, this);
		}
	}

	/** 激活 */
	public void active(ArmyProxy army) {
		if (getState().getCode() != NodeState.START) {
			return;
		}
		Campaign campaign = CampaignMgr.getCampagin(getCampaignId());
		if (campaign != null) {
			decorator.active(army, campaign, this);
		}

	}

	public void prepare() {

	}

	public void reset() {

	}

	public void start() {
		Campaign campaign = CampaignMgr.getCampagin(campaignId);
		if (campaign != null) {
			decorator.start(campaign, this);
		}
	}

	public void over() {
		// 当前节点结束，唤醒下一个节点
		int[] spwanIds = spwanInfo.getNextSpawanIdAttr();

		if (spwanIds == null || spwanIds.length == 0) {
			return;
		}

		Campaign campaign = CampaignMgr.getCampagin(campaignId);
		if (campaign != null) {
			decorator.over(campaign, this);
		}

		// 呼唤下一个节点，并且检测下一个节点的前置节点，是否均结束
		for (int nextTagId : spwanIds) {
			if (nextTagId == 0) {
				continue;
			}
			int nextNodeId = SpawnTemplateMgr.getSpwanId(nextTagId);
			SpwanNode node = field.getSpawnNode(nextNodeId);
			if (node == null) {
				return;
			}
			int[] preSpwanIds = node.getSpawnInfo().getPreSpawanIdAttr();
			if (preSpwanIds != null && preSpwanIds.length > 0) {
				for (int preTagId : preSpwanIds) {
					if (preTagId == 0) {
						continue;
					}
					int preNodeId = SpawnTemplateMgr.getSpwanId(preTagId);
					SpwanNode pre = field.getSpawnNode(preNodeId);
					if (node == null) {
						continue;
					}
					if (pre.getState().getCode() != NodeState.OVER && pre.getState().getCode() != NodeState.DELETE) {
						return;
					}
				}
			}

			// 如果下一个节点，不在激活状态，则激活下一个刷怪点
			if (!(node.getState() instanceof WorkingState)) {
				node.stateTransition(new WorkingState(node));
			}
		}
	}

	public void delete() {
		field.removeSpawnNode(this);
	}

	public int getSpwanId() {
		return spwanInfo.getId();
	}

	public int getNodeType() {
		return nodeType;
	}

	public SpawnInfo getSpawnInfo() {
		return spwanInfo;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public NodeState getState() {
		return state;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public void stateTransition(NodeState state) {
		this.state = state;
		state.work();
		// 通知副本，该节点发送改变
		Campaign campaign = CampaignMgr.getCampagin(campaignId);
		if (spwanInfo.getEntityType() != SpwanInfoType.MONSTER && spwanInfo.getEntityType() != SpwanInfoType.NPC && campaign != null) {
			campaign.updateSpawnInfo(this);
		}
	}

}
