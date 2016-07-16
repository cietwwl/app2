package com.chuangyou.xianni.campaign.node;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampaignNodeDecorator {
	public static CampaignNodeDecorator createDecorator(int campaignFeatures) {
		switch (campaignFeatures) {
			case Campaign.BORN_POINT:
				return new BornNode();
			case Campaign.REVIVAL_POINT:
				return new RevivalNode();
			case Campaign.END_POIN:
				return new EndNode();
			case Campaign.MONSTER_CALLER:
				return new MonsterCallerNode();
			case Campaign.GROUP_CREATER_NODE:
				return new GroupCreaterNode();
			case Campaign.TERMINATOR:
				return new TerminatorNode();
			default:
				break;
		}
		return new CampaignNodeDecorator();
	}

	public void build(Campaign campaign, SpwanNode node) {

	}

	/** 激活 */
	public void active(ArmyProxy army, Campaign campaign, SpwanNode node) {

	}

	public void prepare(Campaign campaign, SpwanNode node) {

	}

	public void reset(Campaign campaign, SpwanNode node) {

	}

	public void start(Campaign campaign, SpwanNode node) {

	}

	public void over(Campaign campaign, SpwanNode node) {

	}
}
