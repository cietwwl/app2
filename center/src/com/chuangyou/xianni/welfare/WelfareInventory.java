package com.chuangyou.xianni.welfare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.welfare.WelfareInfo;
import com.chuangyou.xianni.entity.welfare.WelfareTemplate;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class WelfareInventory extends AbstractEvent implements IInventory {
	
	//礼包状态
	/** 未领取奖励 */
	public static final int STATE_0 = 0;
	/** 可领取奖励 */
	public static final int STATE_1 = 1;
	/** 已领取奖励 */
	public static final int STATE_2 = 2;
	
	//礼包类型
	/** 等级礼包 */
	public static final int TYPE_LEVEL = 1;
	/** 开服礼包 */
	public static final int TYPE_OPEN_SERVICE = 2;
	/** 新手礼包 */
	public static final int TYPE_NEW = 3;
	/** 签到礼包 */
	public static final int TYPE_SIGN = 4;
	/** 节日礼包 */
	public static final int TYPE_FESTIVAL = 5;
	
	private GamePlayer player;
	
	/** 福利信息 */
	private Map<Integer, WelfareInfo> welfareInfoMap = new HashMap<>();
	
	public WelfareInventory(GamePlayer player) {
		this.player = player;
	}
	
	@Override
	public boolean loadFromDataBase() {
		//加载数据库数据
		List<WelfareInfo> welfareInfos = DBManager.getWelfaredao().getWelfareInfosByPlayerId(player.getPlayerId());
		for (WelfareInfo welfareInfo : welfareInfos) {
			welfareInfoMap.put(welfareInfo.getWelfareId(), welfareInfo);
		}
		//配置表数据并与数据库数据进行比较，达到动态添加福利的目的
		List<WelfareInfo> infos = new ArrayList<>();
		for (List<WelfareTemplate> templates : WelfareManager.getConfig().values()) {
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
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		for (WelfareInfo welfareInfo : welfareInfoMap.values()) {
			if (welfareInfo.getOp() == Option.Update) {
				DBManager.getWelfaredao().updateStatus(welfareInfo);
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
	
}
