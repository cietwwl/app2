package com.chuangyou.xianni.state.logic;

import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 通关渡节副本
 * @author laofan
 *
 */
public class PassFbLogic extends BaseStateLogic{
	
	public void passFbLogic(GamePlayer player,int campaignId){
		StateConfig stateConfig = this.getNextStateConfig(player);
		if(stateConfig == null){
			return;
		}
		
		if(campaignId!=stateConfig.getCampaignID()){
			return;
		}
		
		int stateLv = player.getBasePlayer().getPlayerInfo().getStateLv() + 1;
		//升级
		if(player.getBasePlayer().updateStateLv(stateLv)){
			player.getStateInventory().updateProperty();			
		}
		//换任务
		player.getStateInventory().resetStateTrigger();
		
		//送物品
		player.getBagInventory().addItemInBagOrEmail(stateConfig.getAwardItem(), 1, ItemAddType.STATE, false);
		
		//同步给客户端
		player.getStateInventory().sendAllStateTriggers();
		
		
	}
}	
