package com.chuangyou.xianni.state.stateEvent;

import com.chuangyou.common.protobuf.pb.state.NotifyStateEventProto.NotifyStateEventMsg;
import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.state.StateEventFactory;
import com.chuangyou.xianni.state.action.ComboStateEventAction;
import com.chuangyou.xianni.state.logic.RandomLogic;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 基本境界事件
 * @author laofan
 *
 */
public class BaseStateEvent {

	protected StateEventConfig event;


	public BaseStateEvent(StateEventConfig event) {
		this.event = event;
	}


	public void exec(StateCampaign campaign, ArmyProxy army){
		doComboEvent(campaign,army);
		notifyProduceEvent(army);
	}
	
	/**
	 * 通知产生事件
	 * @param army
	 */
	private void notifyProduceEvent(ArmyProxy army){
		NotifyStateEventMsg.Builder resp = NotifyStateEventMsg.newBuilder();
		resp.setEventId(event.getId());
		army.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_STATE_NOTIFY_EVENT,resp));
	}
	
	/**
	 * 执行combo事件
	 * @param campaign
	 * @param army
	 */
	protected void doComboEvent(StateCampaign campaign, ArmyProxy army){
		if(event.getComboChance()>0 && RandomLogic.randomStateCombo(event.getComboChance())){
			BaseStateEvent stateEvent = StateEventFactory.createEventById(StateTemplateMgr.getEvents().get(event.getComboEventID()));
			ComboStateEventAction action = new ComboStateEventAction(campaign.getActionQueue(),event.getComboCd(),campaign, army, stateEvent);
			campaign.getActionQueue().enDelayQueue(action);
		}
	}
	
	
}
