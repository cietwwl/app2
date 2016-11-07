package com.chuangyou.xianni.welfare;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.welfare.WelfareConditionTemplate;
import com.chuangyou.xianni.entity.welfare.WelfareInfo;
import com.chuangyou.xianni.entity.welfare.WelfareTemplate;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.welfare.conditionHandle.BaseConditionHandle;
import com.chuangyou.xianni.welfare.conditionHandle.LoginDaysCondition;
import com.chuangyou.xianni.welfare.conditionHandle.MaxTimeCondition;
import com.chuangyou.xianni.welfare.conditionHandle.MinLevelCondition;
import com.chuangyou.xianni.welfare.conditionHandle.MinTimeCondition;
import com.chuangyou.xianni.welfare.conditionHandle.OnlineTimeCondition;
import com.chuangyou.xianni.welfare.conditionHandle.SignCondition;

public class WelfareInventory extends AbstractEvent implements IInventory {

	// 礼包状态
	/** 未领取奖励 */
	public static final int						STATE_0						= 0;
	/** 可领取奖励 */
	public static final int						STATE_1						= 1;
	/** 已领取奖励 */
	public static final int						STATE_2						= 2;

	// 礼包类型
	/** 等级礼包 */
	public static final int						TYPE_LEVEL					= 1;
	/** 开服礼包 */
	public static final int						TYPE_OPEN_SERVICE			= 2;
	/** 新手礼包 */
	public static final int						TYPE_NEW					= 3;
	/** 签到礼包 */
	public static final int						TYPE_SIGN					= 4;
	/** 节日礼包 */
	public static final int						TYPE_FESTIVAL				= 5;

	// 重置方式（1：永不刷新，2：每日刷新，3：每月刷新）
	/** 永不刷新 */
	public static final int						NEVER_REFRESH				= 1;
	/** 每日刷新 */
	public static final int						REFRESH_EVERYDAY			= 2;
	/** 每月刷新 */
	public static final int						REFRESH_MONTHLY				= 3;

	private GamePlayer							player;

	/** 福利信息 */
	private Map<Integer, WelfareInfo>			welfareInfoMap				= new HashMap<>();

	/** 福利监听列表 */
	private Map<Integer, BaseConditionHandle>	welfareConditionHandleMap	= new HashMap<>();

	public WelfareInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		// 加载数据库数据
		List<WelfareInfo> welfareInfos = DBManager.getWelfaredao().getWelfareInfosByPlayerId(player.getPlayerId());
		for (WelfareInfo welfareInfo : welfareInfos) {
			welfareInfoMap.put(welfareInfo.getWelfareId(), welfareInfo);
		}
		// 配置表数据并与数据库数据进行比较，达到动态添加福利的目的
		List<WelfareInfo> infos = new ArrayList<>();
		for (List<WelfareTemplate> templates : WelfareManager.getConfigType().values()) {
			for (WelfareTemplate template : templates) {
				int id = template.getId();
				if (!welfareInfoMap.containsKey(id)) {
					WelfareInfo welfareInfo = new WelfareInfo();
					welfareInfo.setPlayerId(player.getPlayerId());
					welfareInfo.setWelfareId(id);
					welfareInfo.setStatus(STATE_0);
					welfareInfo.setOp(Option.Insert);
					infos.add(welfareInfo);
					welfareInfoMap.put(id, welfareInfo);
				}
			}
		}
		DBManager.getWelfaredao().addAll(infos);
		return true;
	}

	@Override
	public boolean unloadData() {
		welfareInfoMap = null;
		welfareConditionHandleMap = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		for (WelfareInfo welfareInfo : welfareInfoMap.values()) {
			if (welfareInfo.getOp() == Option.Update) {
				DBManager.getWelfaredao().updateStatus(welfareInfo);
			}
			if (welfareInfo.getOp() == Option.Insert) {
				DBManager.getWelfaredao().add(welfareInfo);
			}
		}
		return true;
	}

	public Map<Integer, WelfareInfo> getWelfareInfoMap() {
		return welfareInfoMap;
	}

	public void setWelfareInfoMap(Map<Integer, WelfareInfo> welfareInfoMap) {
		this.welfareInfoMap = welfareInfoMap;
	}

	/**
	 * 福利刷新机制
	 */
	public void reset() {
		for (WelfareInfo welfareInfo : welfareInfoMap.values()) {
			WelfareTemplate welfareTemplate = WelfareManager.getConfigId().get(welfareInfo.getWelfareId());
			if (welfareTemplate == null) {
				System.err.println("福利配置表找不到福利ID为：" + welfareInfo.getWelfareId() + "的数据");
				continue;
			}
			WelfareConditionTemplate conditionTemplate = WelfareManager.getConditionConfig().get(welfareTemplate.getType());
			if (conditionTemplate == null) {
				System.err.println("福利条件配置表找不到福利类型为：" + welfareTemplate.getType() + "的数据");
				continue;
			}
			int resetType = conditionTemplate.getResetType();
			if (resetType == REFRESH_EVERYDAY) {
				welfareInfo.setStatus(STATE_0);
			} else if (resetType == REFRESH_MONTHLY && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
				welfareInfo.setStatus(STATE_0);
			}
		}
	}

	/**
	 * 登录游戏时需要处理的逻辑
	 */
	public void login() {
		// 初始化福利处理列表
		welfareConditionHandleMap.put(WelfareConditionHandleFactory.LEVEL_MIN, new MinLevelCondition(player));
		welfareConditionHandleMap.put(WelfareConditionHandleFactory.LOGIN_DAYS, new LoginDaysCondition(player));
		welfareConditionHandleMap.put(WelfareConditionHandleFactory.SIGN, new SignCondition(player));
		welfareConditionHandleMap.put(WelfareConditionHandleFactory.ONLINE_TIME, new OnlineTimeCondition(player));
		welfareConditionHandleMap.put(WelfareConditionHandleFactory.MIN_TIME, new MinTimeCondition(player));
		welfareConditionHandleMap.put(WelfareConditionHandleFactory.MAX_TIME, new MaxTimeCondition(player));
		for (WelfareInfo welfareInfo : welfareInfoMap.values()) {
			if (welfareInfo.getStatus() == STATE_2) {
				continue;
			}
			WelfareTemplate welfareTemplate = WelfareManager.getConfigId().get(welfareInfo.getWelfareId());
			if (welfareTemplate == null) {
				System.err.println("福利配置表找不到福利ID为：" + welfareInfo.getWelfareId() + "的数据");
				continue;
			}
			WelfareConditionTemplate conditionTemplate = WelfareManager.getConditionConfig().get(welfareTemplate.getType());
			if (conditionTemplate == null) {
				System.err.println("福利条件配置表找不到福利类型为：" + welfareTemplate.getType() + "的数据");
				continue;
			}
			int cType = conditionTemplate.getConditionType1();
			if (welfareConditionHandleMap.get(cType) != null) {
				welfareConditionHandleMap.get(cType).addEvent(welfareInfo);
			}
			cType = conditionTemplate.getConditionType2();
			if (welfareConditionHandleMap.get(cType) != null) {
				welfareConditionHandleMap.get(cType).addEvent(welfareInfo);
			}
			cType = conditionTemplate.getConditionType3();
			if (welfareConditionHandleMap.get(cType) != null) {
				welfareConditionHandleMap.get(cType).addEvent(welfareInfo);
			}
			cType = conditionTemplate.getConditionType4();
			if (welfareConditionHandleMap.get(cType) != null) {
				welfareConditionHandleMap.get(cType).addEvent(welfareInfo);
			}
		}
	}

	public Map<Integer, BaseConditionHandle> getWelfareConditionHandleMap() {
		return welfareConditionHandleMap;
	}

	public void setWelfareConditionHandleMap(Map<Integer, BaseConditionHandle> welfareConditionHandleMap) {
		this.welfareConditionHandleMap = welfareConditionHandleMap;
	}
}
