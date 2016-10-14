package com.chuangyou.xianni.state.stateEvent;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 跟BUFF相关的事件
 * @author laofan
 *
 */
public class BuffStateEvent extends BaseStateEvent {

	public BuffStateEvent(StateEventConfig event) {
		super(event);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(StateCampaign campaign, ArmyProxy army) {
		// TODO Auto-generated method stub
		super.exec(campaign, army);
		
		if(event.getParam1()==0)return;
		SkillBufferTemplateInfo bufferTemp = BattleTempMgr.getBufferInfo(event.getParam1());
		if (bufferTemp != null) {
			Buffer buffer = BufferFactory.createBuffer(army.getPlayer(), army.getPlayer(), bufferTemp);
			army.getPlayer().addCampaignBuff(buffer);
			System.out.println("境界事件BUFF"+buffer.getBufferId());
		}else{
			Log.error("获取不到BUFF模板数据： playerId: " + army.getPlayerId() + "buffId:" +event.getParam1());
			return;
		}
		
	}

	
}
