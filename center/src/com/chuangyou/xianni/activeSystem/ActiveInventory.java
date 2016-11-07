package com.chuangyou.xianni.activeSystem;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.chuangyou.xianni.activeSystem.template.ActiveTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.active.ActiveConfig;
import com.chuangyou.xianni.entity.active.ActiveInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.event.PlayerPropertyUpdateEvent;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.ActiveTask;
import com.chuangyou.xianni.retask.vo.TotalActiveTask;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 活跃系统
 * 
 * @author laofan
 *
 */
public class ActiveInventory implements IInventory {

	private final GamePlayer			player;

	private final ObjectListener		upLvListener;

	private boolean						isReady;

	/**
	 * 任务集合
	 */
	private Map<Integer, ActiveTask>	activeTasks	= new HashMap<>();

	public ActiveInventory(GamePlayer player) {
		this.player = player;
		this.upLvListener = new ObjectListener() {
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				PlayerPropertyUpdateEvent e = (PlayerPropertyUpdateEvent) event;
				Map<Integer, Long> map = e.getChangeMap();
				if (map != null) {
					for (Map.Entry<Integer, Long> entry : map.entrySet()) {
						if (entry.getKey() == EnumAttr.Level.getValue()) {
							long lv = entry.getValue();
							checkNewActive(lv);
						}
					}
				}
			}
		};
		player.getBasePlayer().addListener(upLvListener, EventNameType.UPDATE_PLAYER_PROPERTY);
	}

	/**
	 * 检测是否有新的活跃任务可以加入
	 * 
	 * @param lv
	 */
	private void checkNewActive(long lv) {
		Iterator<ActiveConfig> it = ActiveTemplateMgr.getActiveConfigs().values().iterator();
		while (it.hasNext()) {
			ActiveConfig config = it.next();
			if (!activeTasks.containsKey(config.getId()) && lv >= config.getNeedLv()) {
				ActiveTask task = createStateTask(config);
				// todo同步给客户端
				player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_NOTIFY_ACTIVE_PROCESS, ((ActiveInfo) task.getTaskInfo()).getMsg()));
			}
		}

	}

	/**
	 * 重置任务
	 * @param task
	 */
	public void resetTask(ActiveTask task) {
		task.getInfo().setProcess(0);
		task.getInfo().setCreateTime(new Date());
		task.getInfo().setUpdateTime(new Date());
		task.getInfo().setStatus(0);
		task.getInfo().setRewardTime(new Date(0));
		task.getInfo().setOp(Option.Update);
		task.addTrigger();
	}

	/**
	 * 重置
	 */
	private void resetAllActiveTasks() {
		// TODO Auto-generated method stub
		this.removeActiveTask();
		for (ActiveConfig config : ActiveTemplateMgr.getActiveConfigs().values()) {
			if(player.getBasePlayer().getPlayerInfo().getLevel()>=config.getNeedLv()){				
				createStateTask(config);
			}
		}
	}

	/**
	 * 创建
	 * 
	 * @param cfg
	 * @return
	 */
	private ActiveTask createStateTask(ActiveConfig cfg) {
		ActiveTask activeTask = activeTasks.get(cfg.getId());
		if (activeTask == null) {
			ActiveInfo taskInfo = new ActiveInfo();
			taskInfo.setPlayerId(player.getPlayerId());
			taskInfo.setActiveId(cfg.getId());
			taskInfo.setProcess(0);
			Date now = new Date();
			taskInfo.setCreateTime(now);
			taskInfo.setUpdateTime(now);
			taskInfo.setOp(Option.Insert);
			activeTask = createTask(cfg, taskInfo, true);
		}
		return activeTask;
	}

	
	// =================================================
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		Map<Integer, ActiveInfo> map = DBManager.getActiveDao().getActiveInfos(player.getPlayerId());
		Iterator<ActiveInfo> it = map.values().iterator();
		while (it.hasNext()) {
			ActiveInfo taskInfo = it.next();
			ActiveConfig cfg = ActiveTemplateMgr.getActiveConfigs().get(taskInfo.getActiveId());
			if (cfg != null) {
				createTask(cfg, taskInfo, false);
			}
		}
		
		if (activeTasks == null || activeTasks.size() == 0) {
			this.resetAllActiveTasks();
		}
		return true;
	}

	/**
	 * 创建活跃任务
	 * 
	 * @param cfg
	 * @param info
	 * @return
	 */
	private ActiveTask createTask(ActiveConfig cfg, ActiveInfo info, boolean isInit) {
		ActiveTask task = null;
		if (cfg.getType() == ActiveConfig.TOTAL_DAY || cfg.getType() == ActiveConfig.TOTAL_WEEK) {
			task = new TotalActiveTask(cfg, info, player);
		} else {
			task = new ActiveTask(cfg, info, player);
		}
		activeTasks.put(cfg.getId(), task);
		if (isInit) {
			task.initTask();
		}
		task.addTrigger();
		return task;
	}

	/**
	 * 删除触发器
	 */
	private void removeActiveTask() {
		if (activeTasks != null) {
			Iterator<ActiveTask> it = activeTasks.values().iterator();
			while (it.hasNext()) {
				ActiveTask at = it.next();
				at.removeTrigger();
				it.remove();
			}
		}
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		removeActiveTask();
		if (activeTasks != null) {
			activeTasks.clear();
		}

		player.getBasePlayer().addListener(upLvListener, EventNameType.UPDATE_PLAYER_PROPERTY);
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if (activeTasks != null) {
			Iterator<ActiveTask> it = activeTasks.values().iterator();
			while (it.hasNext()) {
				ActiveInfo info = (ActiveInfo) it.next().getTaskInfo();
				if (info.getOp() == Option.Insert) {
					DBManager.getActiveDao().addInfo(info);
				} else if (info.getOp() == Option.Update) {
					DBManager.getActiveDao().updateInfo(info);
				}
			}
		}
		return true;
	}

	public Map<Integer, ActiveTask> getActiveTasks() {
		return activeTasks;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

}
