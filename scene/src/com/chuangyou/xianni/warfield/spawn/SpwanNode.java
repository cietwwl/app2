package com.chuangyou.xianni.warfield.spawn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.node.CampaignNodeDecorator;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.entity.spawn.SpwanInfoRefreshType.SpwanInfoIntervalType;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;

public class SpwanNode {
	protected int					nodeType;
	protected NodeState				state;
	protected SpawnInfo				spwanInfo;
	protected Field					field;
	protected int					campaignId;			// 所在副本ID
	protected CampaignNodeDecorator	decorator;			// 副本功能修饰器
	protected int					blood;				// 节点血量（适用于需要循环开闭的节点，如传送阵）

	protected long					refreshTime;		// 刷新时间
	protected long					timeControlerTime;	// 定时刷新时间
	static final int				WAKE_OVER	= 0;	// 结束时唤醒下一个
	static final int				WAKE_START	= 1;	// 激活时唤醒下一个
	protected Map<Long, Living>		children;			// 子孙们

	public SpwanNode(SpawnInfo spwanInfo, Field field) {
		this.spwanInfo = spwanInfo;
		this.field = field;
		this.campaignId = field.getCampaignId();
		this.decorator = CampaignNodeDecorator.createDecorator(spwanInfo.getCampaignFeatures());
		this.children = new ConcurrentHashMap<>();
	}

	public void build() {
		// 副本分组节点添加进分组管理
		Campaign campaign = CampaignMgr.getCampagin(this.getCampaignId());
		if (campaign != null) {
			decorator.build(campaign, this);
		}
	}

	/** 激活 */
	public void active(ArmyProxy army) {
		if (getState().getCode() != NodeState.WORK) {
			return;
		}
		Campaign campaign = CampaignMgr.getCampagin(getCampaignId());
		if (campaign != null) {
			decorator.active(army, campaign, this);
		}
	}

	public void prepare() {

	}

	public void reset() {

	}

	public void start() {
		Campaign campaign = CampaignMgr.getCampagin(campaignId);
		if (campaign != null) {
			decorator.start(campaign, this);
		}
		if (spwanInfo.getWakeType() == WAKE_START) {

			if (spwanInfo.getWakeDelay() == 0) {
				wakeNext();
			} else {
				AbstractActionQueue queue = ThreadManager.getActionRandom();
				WakeNextDelayAction action = new WakeNextDelayAction(queue, spwanInfo.getWakeDelay() * 1000);
				queue.enDelayQueue(action);
			}
		}

		if (spwanInfo.getRestType() == SpwanInfoIntervalType.BRON_SIGN) {
			refreshTime = System.currentTimeMillis();
		}
	}

	public void over() {
		Campaign campaign = CampaignMgr.getCampagin(campaignId);
		if (campaign != null) {
			decorator.over(campaign, this);

			boolean updataProgress = true;
			for (SpwanNode node : campaign.getSpwanNodes().values()) {
				if (node.getSpawnInfo().getProgress() == spwanInfo.getProgress() && node.getState().getCode() != NodeState.OVER) {
					updataProgress = false;
					break;
				}
			}
			if (updataProgress) {
				campaign.updataProgress(spwanInfo.getProgress());
			}
		}
		if (spwanInfo.getWakeType() == WAKE_OVER) {

			if (spwanInfo.getWakeDelay() == 0) {
				wakeNext();
			} else {
				AbstractActionQueue queue = ThreadManager.getActionRandom();
				WakeNextDelayAction action = new WakeNextDelayAction(queue, spwanInfo.getWakeDelay() * 1000);
				queue.enDelayQueue(action);
			}
		}
		if (spwanInfo.getRestType() == SpwanInfoIntervalType.DIE_SIGN) {
			refreshTime = System.currentTimeMillis();
		}

		/*----------当有时间控制时，判断是否唤醒自己--------------*/
		if (spwanInfo.getRestType() != 0) {
			// 当副本结束后，节点不再复活自己
			if (campaignId != 0 && (campaign == null || campaign.isOver())) {
				return;
			}
			long relive = refreshTime + spwanInfo.getRestSecs() * 60l * 1000;
			long leftTime = relive - System.currentTimeMillis();
			if (leftTime <= 0) {
				this.revive();
			} else {
				AbstractActionQueue queue = ThreadManager.getActionRandom();
				WakeSelfDelayAction action = new WakeSelfDelayAction(queue, (int) leftTime, this);
				queue.enDelayQueue(action);
			}
		}
	}

	/** 强制关停节点 */
	public void forceStop() {
		state = new OverState(this);
		for (Living l : children.values()) {
			if (l.getField() != null) {
				l.getField().leaveField(l);
			}
			l.destory();
			l.clearData();
		}
		Campaign campaign = CampaignMgr.getCampagin(campaignId);
		// @atuo 2016-09-05 副本进度指引
		if (campaign != null) {
			campaign.updateSpawnInfo(this);
		}
	}

	public void delete() {
		field.removeSpawnNode(this);
	}

	public int getSpwanId() {
		return spwanInfo.getId();
	}

	public int getNodeType() {
		return nodeType;
	}

	public SpawnInfo getSpawnInfo() {
		return spwanInfo;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public NodeState getState() {
		return state;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public void setDecorator(CampaignNodeDecorator decorator) {
		this.decorator = decorator;
	}

	public void stateTransition(NodeState state) {
		this.state = state;
		state.work();
		// 通知副本，该节点发送改变
		Campaign campaign = CampaignMgr.getCampagin(campaignId);
		// @atuo 2016-09-05 副本进度指引
		if (campaign != null) {
			campaign.updateSpawnInfo(this);
		}
	}

	/** 延迟唤醒下一个 */
	class WakeNextDelayAction extends DelayAction {
		public WakeNextDelayAction(AbstractActionQueue queue, int delay) {
			super(queue, delay);
		}

		@Override
		public void execute() {
			wakeNext();
		}
	}

	/** 延迟唤醒自己 */
	class WakeSelfDelayAction extends DelayAction {
		SpwanNode node;

		public WakeSelfDelayAction(AbstractActionQueue queue, int delay, SpwanNode node) {
			super(queue, delay);
			this.node = node;
		}

		@Override
		public void execute() {
			node.revive();
		}
	}

	/** 复生 */
	public void revive() {
		reset();
		stateTransition(new WorkingState(this));
	}

	/** 销毁 */
	public void stop() {
		stateTransition(new OverState(this));
	}

	protected void wakeNext() {
		// 当前节点结束，唤醒下一个节点
		int[] spwanIds = spwanInfo.getNextSpawanIdAttr();

		if (spwanIds == null || spwanIds.length == 0) {
			return;
		}
		// 呼唤下一个节点，并且检测下一个节点的前置节点，是否均结束
		for (int nextTagId : spwanIds) {
			if (nextTagId == 0) {
				continue;
			}
			int nextNodeId = SpawnTemplateMgr.getSpwanId(nextTagId);
			SpwanNode node = field.getSpawnNode(nextNodeId);
			if (node == null) {
				return;
			}
			int[] preSpwanIds = node.getSpawnInfo().getPreSpawanIdAttr();
			if (preSpwanIds != null && preSpwanIds.length > 0) {
				for (int preTagId : preSpwanIds) {
					if (preTagId == 0) {
						continue;
					}
					int preNodeId = SpawnTemplateMgr.getSpwanId(preTagId);
					SpwanNode pre = field.getSpawnNode(preNodeId);
					if (pre == null) {
						continue;
					}
					if (pre.getSpawnInfo().getWakeType() == WAKE_OVER && pre.getState().getCode() != NodeState.OVER && pre.getState().getCode() != NodeState.DELETE) {
						return;
					}
				}
			}
			// 如果下一个节点，不在激活状态，则激活下一个刷怪点
			if (!(node.getState() instanceof WorkingState)) {
				node.stateTransition(new WorkingState(node));
			}
		}
	}

	public long getTimeControlerTime() {
		return timeControlerTime;
	}

	public void setTimeControlerTime(long timeControlerTime) {
		this.timeControlerTime = timeControlerTime;
	}

}
