package com.chuangyou.xianni.state.logic;

import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.StateTask;

/**
 * 进入渡节副本逻辑
 * @author laofan
 *
 */
public class EnterCampaignLogic extends BaseStateLogic{
		
	public void enter(GamePlayer player){
		if(!check(player)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR,Protocol.C_REQ_STATE_OP,"渡节有未完成的任务："+player.getPlayerId());		
			return;
		}
		
		StateConfig stateConfig = this.getNextStateConfig(player);
		if(stateConfig == null){
			return;
		}
		
		for (Entry<Integer, Integer> entry : stateConfig.getTicketsMap().entrySet()) {
			int tempId = entry.getKey();
			int count = entry.getValue();
			if(player.getBagInventory().isEnought(tempId, count) == false){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR,Protocol.C_REQ_STATE_OP,"渡节需要的物品不够：tempId"+tempId+"count:"+count);		
				return;
			}
		}
		
		//消耗物品
		for (Entry<Integer, Integer> entry : stateConfig.getTicketsMap().entrySet()) {
			int tempId = entry.getKey();
			int count = entry.getValue();
			if(player.getBagInventory().removeItem(tempId, count, ItemRemoveType.STATE) == false){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR,Protocol.C_REQ_STATE_OP,"渡节扣除物品出错：tempId"+tempId+"count:"+count);		
				return;
			}
		}
		
		//发送消息。进入副本
		CreateStateCampaignMsg.Builder msg = CreateStateCampaignMsg.newBuilder();
		msg.setCampaignId(stateConfig.getCampaignID());
		msg.setStateId(stateConfig.getId());
		
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.S_CREATE_STATE_CAMPAIGN,msg));
		
		this.sendResult(player, 1, 0);
	}
	
	
	/**
	 * 渡节任务是否全部完成
	 * @param player
	 * @return
	 */
	private boolean check(GamePlayer player){
		for (StateTask trigger : player.getStateInventory().getStateTasks().values()) {
			if(trigger.isFinish() == false){
				return false;
			}
		}
		return true;
	}
	
	
	
	
}
