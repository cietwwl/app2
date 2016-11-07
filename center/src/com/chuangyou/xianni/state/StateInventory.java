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
import com.chuangyou.xianni.entity.task.ITaskCfg;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.StateTask;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.state.logic.CalcStateAddAttLogic;
import com.chuangyou.xianni.state.logic.PassFbLogic;
import com.chuangyou.xianni.state.template.StateTemplateMgr;

/**
 * 境界
 * @author laofan
 *
 */
public class StateInventory extends AbstractEvent implements IInventory {

	private final GamePlayer player;
	
	public StateInventory(GamePlayer player) {
		this.player = player;
	}


	/**
	 * 任务集合
	 */
	private Map<Integer, StateTask> stateTasks = new HashMap<>();
	
	
	/**
	 * 删除触发器
	 */
	public void removeStateTrigger(){
		if(stateTasks!=null){
			Iterator<StateTask> it = stateTasks.values().iterator();
			while(it.hasNext()){
				it.next().removeTrigger();
			}
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
		Map<Integer, StateConditionInfo> map = DBManager.getStateDao().getStateConditions(player.getPlayerId());
		if(map.size() == 0){
			this.resetStateTrigger();
		}else{			
			Iterator<StateConditionInfo> it = map.values().iterator();
			while(it.hasNext()){
				StateConditionInfo taskInfo = it.next();
				ITaskCfg cfg = StateTemplateMgr.getConditions().get(taskInfo.getStateId());
				if(cfg!=null){
					StateTask stateTask = new StateTask(cfg, taskInfo, player);
					stateTasks.put(taskInfo.getStateId(), stateTask);
					stateTask.addTrigger();
				}
			}
		}
		return true;
	}

	
	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		this.removeStateTrigger();
		if(stateTasks!=null){
			stateTasks.clear();
		}
		return true;
	}

	
	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(stateTasks!=null){
			Iterator<StateTask> it = stateTasks.values().iterator();
			while(it.hasNext()){
				StateConditionInfo info =(StateConditionInfo) it.next().getTaskInfo();
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
		for(StateTask stateTask:stateTasks.values()){
			resp.addInfos(((StateConditionInfo)stateTask.getTaskInfo()).getMsg());
		}
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_STATE_GET_INFO,resp));
	}
	

	public void addAllTrigger(){
		for (StateTask stateTask : stateTasks.values()) {
			stateTask.addTrigger();
		}
	}
	
	/**
	 * 重置
	 */
	public void resetStateTrigger() {
		// TODO Auto-generated method stub
		removeStateTrigger();
		if(this.stateTasks!=null){
			stateTasks.clear();
		}
		stateTasks = new HashMap<>();
		int lv = player.getBasePlayer().getPlayerInfo().getStateLv()+1;
		StateConfig config = StateTemplateMgr.getStates().get(lv);
		if(config!=null){
			int[] cons = config.getConditions();
			for (int stateId : cons) {
				StateTask stateTask = createStateTask(stateId);
				if(stateTask==null)continue;
			}
		}else{
			Log.error("stateConfig配置表错误，找不到等级："+lv+" 对应该的配置信息");
		}
	}

	
	private StateTask createStateTask(int stateId){
		StateTask stateTask = stateTasks.get(stateId);
		if(stateTask == null){
			ITaskCfg cfg = StateTemplateMgr.getConditions().get(stateId);
			if(cfg==null){
				Log.error("境界任务配置表错误：stateId："+stateId);
				return null;
			}
			StateConditionInfo taskInfo = new StateConditionInfo();
			taskInfo.setPlayerId(player.getPlayerId());
			taskInfo.setStateId(stateId);
			taskInfo.setProcess(0);
			Date now = new Date();
			taskInfo.setCreateTime(now);
			taskInfo.setUpdateTime(now);
			taskInfo.setOp(Option.Insert);
			stateTask = new StateTask(cfg, taskInfo, player);
			stateTasks.put(stateId, stateTask);
			stateTask.initTask();
			stateTask.addTrigger();
			DBManager.getStateDao().addInfo(taskInfo);
		}
		return stateTask;
	}
	
	public Map<Integer, StateTask> getStateTasks() {
		return stateTasks;
	}

}
