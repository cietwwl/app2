package com.chuangyou.xianni.state.condition;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;

public abstract class BaseStateCondition {
	
	protected StateConditionInfo info;
	
	protected StateConditionConfig config;
	
	protected GamePlayer player;
	
	protected ObjectListener listener;
	

	public BaseStateCondition(StateConditionInfo info, StateConditionConfig config,GamePlayer player) {
		super();
		this.info = info;
		this.config = config;
		this.player = player;
	}

	/**
	 *  添加监听
	 * @param player
	 */
	public abstract void addTrigger(GamePlayer player);
	
	/**
	 * 删除监听
	 * @param player
	 */
	public abstract void removeTrigger(GamePlayer player);
	
	
	/** 初始化进度 */
	public abstract void initProcess();
	
	/**
	 *  是否完成
	 * @return
	 */
	public boolean isComplete() {
		// TODO Auto-generated method stub
		if(this.info.getProcess()>=this.config.getTargetNum()){
			return true;
		}
		return false;
	}

	
	/**
	 * 提交处理
	 * @return
	 */
	public abstract boolean commitProcess();
	
	/**
	 * 通知更新
	 */
	public void doNotifyUpdate(){
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_STATE_UPDATE,this.info.getMsg()));
	}
	
}
