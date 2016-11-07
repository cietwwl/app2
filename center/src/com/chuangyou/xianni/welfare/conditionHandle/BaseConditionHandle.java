package com.chuangyou.xianni.welfare.conditionHandle;

import java.util.HashSet;
import java.util.Set;

import com.chuangyou.xianni.entity.welfare.WelfareConditionTemplate;
import com.chuangyou.xianni.entity.welfare.WelfareInfo;
import com.chuangyou.xianni.entity.welfare.WelfareTemplate;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.welfare.WelfareInventory;
import com.chuangyou.xianni.welfare.WelfareManager;

public abstract class BaseConditionHandle {
	protected GamePlayer		player;
	protected Set<WelfareInfo>	listenList	= new HashSet<>();

	public BaseConditionHandle(GamePlayer player) {
		this.player = player;
	}

	/**
	 * <pre>
	 * 添加监听事件
	 * </pre>
	 * 
	 * @param welfareInfo
	 */
	public void addEvent(WelfareInfo welfareInfo) {
		listenList.add(welfareInfo);
	}

	/**
	 * <pre>
	 * 移除事件
	 * </pre>
	 */
	public void removeEvent(WelfareInfo welfareInfo) {
		if (listenList.contains(welfareInfo)) {
			listenList.remove(welfareInfo);
		}
	}

	/** 判断礼包是否达成条件 */
	public abstract boolean judge(long parameter);

	/**
	 * 监听
	 */
	public void listen() {
		Set<Integer> result = new HashSet<>();
		for (WelfareInfo welfareInfo : listenList) {
			if (judgeOneWelfare(welfareInfo)) {
				result.add(welfareInfo.getWelfareId());
			}
		}
		for (Integer id : result)
			WelfareManager.updateOneWelfare(id, player.getWelfareInventory().getWelfareInfoMap().get(id).getStatus(), player);
	}

	public boolean judgeOneWelfare(WelfareInfo welfareInfo) {
		WelfareTemplate welfareTemplate = WelfareManager.getConfigId().get(welfareInfo.getWelfareId());
		WelfareConditionTemplate conditionTemplate = WelfareManager.getConditionConfig().get(welfareTemplate.getType());
		BaseConditionHandle handle1 = player.getWelfareInventory().getWelfareConditionHandleMap().get(conditionTemplate.getConditionType1());
		BaseConditionHandle handle2 = player.getWelfareInventory().getWelfareConditionHandleMap().get(conditionTemplate.getConditionType2());
		BaseConditionHandle handle3 = player.getWelfareInventory().getWelfareConditionHandleMap().get(conditionTemplate.getConditionType3());
		BaseConditionHandle handle4 = player.getWelfareInventory().getWelfareConditionHandleMap().get(conditionTemplate.getConditionType4());
		if ((handle1 == null || handle1.judge(welfareTemplate.getParameter1())) && (handle2 == null || handle2.judge(welfareTemplate.getParameter2()))
				&& (handle3 == null || handle3.judge(welfareTemplate.getParameter3())) && (handle4 == null || handle4.judge(welfareTemplate.getParameter4()))) {
			if (welfareInfo.getStatus() != WelfareInventory.STATE_1) {
				welfareInfo.setStatus(WelfareInventory.STATE_1);
				return true;
			}
		} else if (welfareInfo.getStatus() == WelfareInventory.STATE_1) {
			welfareInfo.setStatus(WelfareInventory.STATE_0);
			return true;
		}
		return false;
	}
}
