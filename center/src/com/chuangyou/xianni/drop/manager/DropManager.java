package com.chuangyou.xianni.drop.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.common.util.random.WeightRandomUtil;
import com.chuangyou.xianni.constant.DropItemConstant;
import com.chuangyou.xianni.drop.templete.DropTempleteMgr;
import com.chuangyou.xianni.entity.drop.DropInfo;
import com.chuangyou.xianni.entity.drop.DropItemInfo;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.player.GamePlayer;

public class DropManager {

	public static List<DropItemInfo> getDropList(int id) {
		List<DropItemInfo> dropItems = new ArrayList<>();

		DropInfo pool = DropTempleteMgr.getDropPool().get(id);

		Map<Integer, DropItemInfo> items = DropTempleteMgr.getDropItemMap().get(id);

		ThreadSafeRandom random = new ThreadSafeRandom();
		if (pool.getType() == DropItemConstant.DropType.PROBABILITY) {
			for (int i = 0; i < pool.getRepeat(); i++) {
				for (DropItemInfo item : items.values()) {
					int rate = item.getWeight();
					if (random.isSuccessful(rate, 10000)) {
						dropItems.add(item);
					}
				}
			}
		} else if (pool.getType() == DropItemConstant.DropType.WEIGHT) {
			for (int i = 0; i < pool.getRepeat(); i++) {

				List<DropItemInfo> itemList = new ArrayList<>();
				itemList.addAll(items.values());
				
				DropItemInfo resultItem = WeightRandomUtil.getRandomWeight(itemList);
				if(resultItem != null){
					dropItems.add(resultItem);
				}
			}
		} else if (pool.getType() == DropItemConstant.DropType.SPECIAL_WEIGHT){
			for (int i = 0; i < pool.getRepeat(); i++) {

				List<DropItemInfo> itemList = new ArrayList<>();
				itemList.addAll(items.values());
				
				DropItemInfo resultItem = WeightRandomUtil.getRandomWeight(pool.getTotalWeight(), itemList);
				if(resultItem != null){
					dropItems.add(resultItem);
				}
			}
		}

		return dropItems;
	}
	
	
	public static void dropTaskItems(GamePlayer player,int id){
		List<DropItemInfo> list= getDropList(id);
		for (DropItemInfo dropItemInfo : list) {
			player.getBagInventory().addItem(dropItemInfo.getItemId(), dropItemInfo.getCount(),ItemAddType.TASK_DROP, false);
		}
	}
}
