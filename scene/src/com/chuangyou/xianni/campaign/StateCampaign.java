package com.chuangyou.xianni.campaign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.state.NotifyStateProcessProto.NotifyStateProcessMsg;
import com.chuangyou.common.protobuf.pb.state.NotifyStateQteProto.NotifyStateQteMsg;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.state.CampaignState;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.action.StateRevivalPlayerAction;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.state.QteTemp;
import com.chuangyou.xianni.state.StateEventFactory;
import com.chuangyou.xianni.state.action.EmptyStateDelayAction;
import com.chuangyou.xianni.state.action.QteDelayAction;
import com.chuangyou.xianni.state.logic.RandomLogic;
import com.chuangyou.xianni.state.stateEvent.BaseStateEvent;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class StateCampaign extends SingleCampaign {

	/**
	 * 当前进度
	 */
	private int						curProcess;
	/**
	 * 进度上限
	 */
	private int						maxProcess;

	/**
	 * 境界配置
	 */
	private StateConfig				config;

	private Map<Integer, QteTemp>	qteTempMap;

	private boolean					isReady		= false;

	private boolean					isEndEvent	= false;

	public StateCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, StateConfig config) {
		super(tempInfo, creater, 0);
		qteTempMap = new HashMap<>();
		this.config = config;
		this.curProcess = 0;
		this.maxProcess = config.getMaxProcess();
	}

	/**
	 * 执行随机事件 1：玩家不在当前副本，结束事件随机
	 * 
	 */
	public void execRandomEvent() {
		if (!checkIsInScene()) {
			return; // 不在地图中随机事件停止
		}
		if (this.state.getCode() != CampaignState.START) {
			return;
		}
		if (this.curProcess >= this.maxProcess) {
			if (!isEndEvent) {
				isEndEvent = true;
				doEndEvents(); // 执行结束事件
			}
			return;
		}

		// 随机池.随机一条事件
		int[] events = config.getEventPools();
		StateEventConfig event = RandomLogic.getRandomEvent(events);
		if (doEvent(event) == 1) {
			// todo 加进度值
			this.curProcess += event.getProcess();
			notifyProcess();
			// todo定时执行下一个事件
			ArmyProxy army = WorldMgr.getArmy(this.creater);
			if (army != null) {
				EmptyStateDelayAction action = new EmptyStateDelayAction(getActionQueue(), event.getCoolDown() * 1000, this, army);
				action.getActionQueue().enDelayQueue(action);
			}
		}
	}

	/**
	 * 失败结束
	 */
	public void failOver() {
		ArmyProxy army = WorldMgr.getArmy(this.creater);
		if (army != null) {
			StateRevivalPlayerAction revival = new StateRevivalPlayerAction(army);
			ThreadManager.actionExecutor.enDelayQueue(revival);
		}
		this.stop();
	}

	/**
	 * QTE完成执行指定的事件
	 * 
	 * @param event
	 */
	public void execOneEvent(StateEventConfig event) {
		if (!checkIsInScene())
			return; // 不在地图中随机事件停止
		if (this.state.getCode() != CampaignState.START)
			return;
		ArmyProxy army = WorldMgr.getArmy(this.creater);
		if (army == null)
			return;
		if (this.curProcess >= this.maxProcess) {
			doEndEvents(); // 执行结束事件
			return;
		}

		BaseStateEvent stateEvent = StateEventFactory.createEventById(event);
		if (stateEvent != null) {
			stateEvent.exec(this, army);

			// todo 加进度值
			this.curProcess += event.getProcess();
			notifyProcess();
			// todo定时执行下一个事件
			EmptyStateDelayAction action = new EmptyStateDelayAction(getActionQueue(), event.getCoolDown() * 1000, this, army);
			action.getActionQueue().enDelayQueue(action);
		}
	}

	/**
	 * 更新进度
	 * 
	 * @param value
	 */
	private void notifyProcess() {
		ArmyProxy army = WorldMgr.getArmy(this.creater);
		if (army != null) {
			NotifyStateProcessMsg.Builder resp = NotifyStateProcessMsg.newBuilder();
			resp.setProcess(this.curProcess);
			army.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_STATE_FB_PROCESS, resp));

		}
	}

	/**
	 * 执行境界结束事件
	 */
	private void doEndEvents() {
		int[] events = config.getEndEventPools();
		for (int i = 0; i < events.length; i++) {
			StateEventConfig stateEvent = StateTemplateMgr.getEvents().get(events[i]);
			if (stateEvent != null) {
				doEvent(stateEvent);
			}
		}
	}

	/**
	 * 执行境界事件
	 * 
	 * @param event
	 * @return -1:失败 1:事件成功 2：QTE
	 */
	private int doEvent(StateEventConfig event) {
		if (event != null) {
			ArmyProxy army = WorldMgr.getArmy(this.creater);
			if (army == null) {
				return -1;
			}
			if (event.getQteChance() != 0 && RandomLogic.randomStateQte(event.getQteChance())) { // 先执行QTE
				// todo发送QTE
				NotifyStateQteMsg.Builder msg = NotifyStateQteMsg.newBuilder();
				msg.setEventId(event.getId());
				msg.setQteId(event.getQteId());
				msg.setQteLimitTime(event.getQteLimitTime());
				army.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_STATE_QTE, msg));
				QteTemp temp = new QteTemp(event.getQteId(), event.getId(), System.currentTimeMillis(), this);
				qteTempMap.put(temp.getQteId(), temp);
				QteDelayAction action = new QteDelayAction(this.getActionQueue(), event.getQteLimitTime() * 1000, event.getQteId(), this);
				action.getActionQueue().enDelayQueue(action);
				return 2;
			} else {
				// todo 加进度
				BaseStateEvent stateEvent = StateEventFactory.createEventById(event);
				if (stateEvent != null) {
					stateEvent.exec(this, army);
					return 1;
				}
			}
		}
		return -1;
	}

	/**
	 * 检测玩家是不是在地图中
	 * 
	 * @return true：在 false :不在
	 */
	private boolean checkIsInScene() {
		ArmyProxy army = WorldMgr.getArmy(this.creater);
		if (army != null && army.getPlayer().getField().getCampaignId() == this.id) {
			return true;
		}
		return false;
	}

	@Override
	public void onPlayerEnter(ArmyProxy army) {
		// TODO Auto-generated method stub
		super.onPlayerEnter(army);
		this.doEnter(army);
	}

	private void doEnter(ArmyProxy army) {
		if (this.isReady == false) {
			this.isReady = true;
			notifyProcess();
		} else { // 有相应的处罚
			if (this.curProcess < this.maxProcess) {
				this.curProcess = (this.curProcess / 2);
			}
			notifyProcess();
			// 所有怪物回血
			for (Field field : allFields.values()) {
				if (field != null) {
					List<Living> lives = field.getMonsters();
					for (Living living : lives) {
						if (living instanceof Monster) {
							Monster m = (Monster) living;
							m.fullState();
						}
					}
				}
			}
			qteTempMap.clear();
		}
		EmptyStateDelayAction action = new EmptyStateDelayAction(getActionQueue(),config.getStartEvent()*1000 , this, army);
		action.getActionQueue().enDelayQueue(action);
	}

	public Map<Integer, QteTemp> getQteTempMap() {
		return qteTempMap;
	}

	@Override
	public void clearCampaignData() {
		super.clearCampaignData();
		if (qteTempMap != null) {
			this.qteTempMap.clear();
			qteTempMap = null;
		}
	}

	@Override
	public void onPlayerEnter(ArmyProxy army, int mapId, Vector3 v3) {
		// TODO Auto-generated method stub
		super.onPlayerEnter(army, mapId, v3);
		this.doEnter(army);
	}

}
