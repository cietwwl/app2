package com.chuangyou.xianni.state;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.state.GetStateInfoRespProto.GetStateInfoRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.state.logic.CalcStateAddAttLogic;
import com.chuangyou.xianni.state.logic.PassFbLogic;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.state.trigger.StateTrigger;

/**
 * 境界
 * @author laofan
 *
 */
public class StateInventory extends AbstractEvent implements IInventory {

	private GamePlayer player;
	
	public StateInventory(GamePlayer player) {
		this.player = player;
	}

	/**
	 * 条件集合
	 */
	private Map<Integer, StateConditionInfo> conditions;
	
	/**
	 * 触发器集合
	 */
	private Map<Integer, StateTrigger> stateTriggers;
	
	
	
	public StateConditionInfo getCondition(int stateId){
		StateConditionInfo info = conditions.get(stateId);
		if(info == null){
			info = new StateConditionInfo();
			info.setPlayerId(player.getPlayerId());
			info.setStateId(stateId);
			info.setProcess(0);
			Date now = new Date();
			info.setCreateTime(now);
			info.setUpdateTime(now);
			info.setOp(Option.Insert);
			conditions.put(stateId, info);
		}
		return info;
	}
	
	/**
	 * 重置
	 */
	public void resetStateTrigger(){
		if(stateTriggers!=null){
			Iterator<StateTrigger> it = stateTriggers.values().iterator();
			while(it.hasNext()){
				it.next().removeTrigger();
			}
			stateTriggers.clear();
		}
		stateTriggers = new HashMap<>();
		int lv = player.getBasePlayer().getPlayerInfo().getStateLv()+1;
		StateConfig config = StateTemplateMgr.getStates().get(lv);
		if(config!=null){
			int[] cons = config.getConditions();
			for (int i : cons) {
				StateConditionInfo info = getCondition(i);
				StateTrigger trigger = new StateTrigger(info, player);
				stateTriggers.put(info.getStateId(), trigger);
				trigger.addTrigger();
				if(trigger.getCondition()!=null){
					trigger.getCondition().initProcess();
				}
			}
		}else{
			Log.error("stateConfig配置表错误，找不到等级："+lv+" 对应该的配置信息");
		}
	}
	
	/**
	 *  通关副本
	 * @param campaignId
	 */
	public void passFb(int campaignId){
		new PassFbLogic().passFbLogic(player, campaignId);
	}
	
	
	/**
	 * 更新属性
	 */
	public void updateProperty() {
		if (player.getArmyInventory() != null) {
			
			BaseProperty stateData = new BaseProperty();
			BaseProperty statePer  = new BaseProperty();
			
			new CalcStateAddAttLogic().calc(player, stateData, statePer);
			player.getArmyInventory().getHero().addState(stateData, statePer);
			player.getArmyInventory().updateProperty();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		conditions = DBManager.getStateDao().getStateConditions(player.getPlayerId());
		resetStateTrigger();
		return true;
	}

	
	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		if(conditions!=null){
			conditions.clear();
			conditions = null;
		}
		if(stateTriggers!=null){
			stateTriggers.clear();
			stateTriggers = null;
		}
		return true;
	}

	
	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(conditions!=null){
			Iterator<StateConditionInfo> it = conditions.values().iterator();
			while(it.hasNext()){
				StateConditionInfo info =it.next();
				if(info.getOp() == Option.Insert){
					DBManager.getStateDao().addInfo(info);
				}else if(info.getOp() == Option.Update){
					DBManager.getStateDao().updateInfo(info);
				}
			}
		}
		
		return true;
	}

	/**
	 * 发送进度
	 */
	public void sendAllStateTriggers(){
		GetStateInfoRespMsg.Builder resp = GetStateInfoRespMsg.newBuilder();
		for(StateTrigger trigger:player.getStateInventory().getStateTriggers().values()){
			resp.addInfos(trigger.getInfo().getMsg());
		}
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_STATE_GET_INFO,resp));
	}
	
	public Map<Integer, StateTrigger> getStateTriggers() {
		return stateTriggers;
	}

}
