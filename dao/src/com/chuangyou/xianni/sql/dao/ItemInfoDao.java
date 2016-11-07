package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.item.ItemInfo;

public interface ItemInfoDao {
	List<ItemInfo> getAllItem(long playerId);

	List<ItemInfo> getEquimpent(long playerId, int ObjectId);

	List<ItemInfo> getItemInfos(List<Integer> ids);

	boolean addItemInfo(ItemInfo info);

	boolean updateItemInfo(ItemInfo info);

	long getMaxItemId();

	List<ItemInfo> getAllItem(long playerId, List<Integer> tempId, int page, int pageSize);

	public int getCount(long playerId, List<Integer> tempId);
}
