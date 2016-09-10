package com.chuangyou.xianni.bag;

import java.util.Date;

import com.chuangyou.xianni.entity.log.ItemLogInfo;
import com.chuangyou.xianni.log.LogManager;

/**
 * 物品操作日志
 * 
 * @author Administrator
 * 
 */
public class ItemLogHelper {

	private BaseItem	baseItem;
	private ItemLogInfo	logItem;

	public void setBaseItem(BaseItem baseItem) {
		this.baseItem = baseItem;
	}

	public BaseItem getBaseItem() {
		return baseItem;
	}

	public void setLogItem(ItemLogInfo logItem) {
		this.logItem = logItem;
	}

	public ItemLogInfo getLogItem() {
		return logItem;
	}

	public static void create(BaseItem baseItem) {
		ItemLogHelper itemLog = new ItemLogHelper(baseItem);
		if (itemLog.getLogItem() != null) {
			itemLog.getLogItem().setOldCount(0);
			itemLog.commitChanges(baseItem.getItemInfo().getAddType());
		}
	}

	public ItemLogHelper(BaseItem baseItem) {
		if (baseItem != null && baseItem.getItemInfo() != null && baseItem.getItemTempInfo() != null) {
			this.baseItem = baseItem;
			logItem = new ItemLogInfo();
			logItem.setPlayerId(baseItem.getItemInfo().getPlayerId());
			logItem.setTemplateId(baseItem.getTemplateId());
			logItem.setItemId(baseItem.getItemInfo().getId());
			logItem.setOldCount(baseItem.getItemInfo().getCount());
			logItem.setOldPro(baseItem.getItemInfo().getPro());
			logItem.setOldGrow(baseItem.getItemInfo().getGrow());
			logItem.setOldAwaken(baseItem.getItemInfo().getAwaken());
			logItem.setOldAwakenPoint(baseItem.getItemInfo().getAwakenPoint());
			logItem.setOldStone(baseItem.getItemInfo().getStone());

		}
	}

	public void commitChanges(int changeType) {
		if (baseItem != null) {
			logItem.setNowCount(baseItem.getItemInfo().getCount());
			logItem.setChangeType(changeType);
			logItem.setNowCount(baseItem.getItemInfo().getCount());
			logItem.setNewPro(baseItem.getItemInfo().getPro());
			logItem.setNewGrow(baseItem.getItemInfo().getGrow());
			logItem.setNewAwaken(baseItem.getItemInfo().getAwaken());
			logItem.setNewAwakenPoint(baseItem.getItemInfo().getAwakenPoint());
			logItem.setNewStone(baseItem.getItemInfo().getStone());
			LogManager.addItemLog(logItem);
		}
	}
}
