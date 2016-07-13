package com.chuangyou.xianni.bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemInfo;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.ItemInfoDao;

public class ItemManager {
	// 物品模板+模板信息
	public static HashMap<Integer, ItemTemplateInfo> itemTempInfoMap;

	public static boolean init() {
		itemTempInfoMap = loadItemTempInfoDb();
		return true;
	}

	public static HashMap<Integer, ItemTemplateInfo> loadItemTempInfoDb() {
		HashMap<Integer, ItemTemplateInfo> infos = new HashMap<Integer, ItemTemplateInfo>();
		List<ItemTemplateInfo> list = DBManager.getItemTemplateInfoDao().getItemTemps();
		for (ItemTemplateInfo itemTempInfo : list) {
			infos.put(itemTempInfo.getId(), itemTempInfo);
		}

		return infos;
	}

	public static List<ItemInfo> list(long playerId) {
		List<ItemInfo> infos = DBManager.getItemInfoDao().getAllItem(playerId);
		return infos;
	}

	public static List<ItemInfo> list(long playerId, int ObjectId) {
		List<ItemInfo> infos = DBManager.getItemInfoDao().getEquimpent(playerId, ObjectId);
		return infos;
	}

	public static List<ItemInfo> getItemInfos(List<Integer> idList) {
		return DBManager.getItemInfoDao().getItemInfos(idList);
	}

	public static void save(List<BaseItem> itemsMap) {
		if ((itemsMap == null) || (itemsMap.size() == 0)) {
			return;
		}
		ItemInfoDao itemDao = DBManager.getItemInfoDao();
		for (BaseItem baseItem : itemsMap) {
			if (baseItem == null) {
				continue;
			}
			save(baseItem.getItemInfo(), itemDao);
		}
	}

	public static void save(BaseItem info) {
		if (info == null)
			return;
		List<BaseItem> infos = new ArrayList<BaseItem>(1);
		infos.add(info);
		save(infos);

	}

	public static ItemInfo getItemInfo(int itemId) {
		List<Integer> itemIds = new ArrayList<Integer>();
		itemIds.add(itemId);
		List<ItemInfo> itemInfos = getItemInfos(itemIds);
		if (itemInfos.size() > 0) {
			for (ItemInfo itemInfo : itemInfos) {
				if (itemInfo.getId() == itemId) {
					return itemInfo;
				}
			}
		}
		return null;
	}

	public static boolean save(ItemInfo info, ItemInfoDao itemDao) {
		if (info.getOp() == Option.Insert) {
			if (!itemDao.addItemInfo(info)) {
				Log.error("itemInfo add error id is" + info.getId());
				return false;
			}
		}
		if (info.getOp() == Option.Update) {
			if (!itemDao.updateItemInfo(info)) {
				Log.error("iteminfo update error id is" + info.getId());
				return false;
			}
		}
		return true;
	}

	/* 新建物品： 克隆一个物品 */
	public static BaseItem ghost(BaseItem baseItem, long playerId, int itemCount, short changeType) {
		if ((baseItem == null) || (baseItem.getItemInfo() == null) || (baseItem.getItemTempInfo() == null)
				|| (baseItem.getItemInfo().isExist() == false))
			return null;
		if (itemCount <= 0) {
			return null;
		}
		ItemInfo ghostItemInfo = (ItemInfo) baseItem.getItemInfo().clone();
		// 以下部分不能克隆
		ghostItemInfo.setId(EntityIdBuilder.itemIdBuilder());
		ghostItemInfo.setPos((short) -1);
		ghostItemInfo.setBagType(BagType.New);
		ghostItemInfo.setAddDate(new java.util.Date());
		ghostItemInfo.setAddType(changeType);
		ghostItemInfo.setCount(itemCount);
		BaseItem ghostBaseItem = new BaseItem(baseItem.getItemTempInfo(), ghostItemInfo);
		ghostItemInfo.setPlayerId(playerId);
		ghostItemInfo.setOp(Option.Insert);
		// 记录日志
		return ghostBaseItem;
	}

	public static int itemCount(BaseItem item, int itemMasteType) {
		if (item == null || item.getItemInfo() == null || item.getItemTempInfo().getMasterType() != itemMasteType) {
			return 0;
		}
		if (item.getItemTempInfo().getId() > 0) {
			return item.getItemInfo().getCount();
		}
		return 0;
	}

	public static ItemTemplateInfo findItemTempInfo(int templateId) {
		return itemTempInfoMap.get(templateId);
	}
}
