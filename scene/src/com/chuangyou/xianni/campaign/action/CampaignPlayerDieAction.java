package com.chuangyou.xianni.campaign.action;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;

public class CampaignPlayerDieAction extends Action {

	private Campaign campaign;
	private Player player;
	private Living source;
	
	public CampaignPlayerDieAction(Campaign campaign, Player player, Living source) {
		super(campaign);
		// TODO Auto-generated constructor stub
		this.campaign = campaign;
		this.player = player;
		this.source = source;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		campaign.playerDie(player, source);
	}

}
