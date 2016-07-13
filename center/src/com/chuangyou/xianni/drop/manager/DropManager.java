package com.chuangyou.xianni.drop.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.constant.DropItemConstant;
import com.chuangyou.xianni.drop.templete.DropTempleteMgr;
import com.chuangyou.xianni.entity.drop.DropInfo;
import com.chuangyou.xianni.entity.drop.DropItemInfo;

public class DropManager {

	public static List<DropItemInfo> getDropList(int id){
		List<DropItemInfo> dropItems = new ArrayList<>();
		
		DropInfo pool = DropTempleteMgr.getDropPool().get(id);
		
		Map<Integer, DropItemInfo> items = DropTempleteMgr.getDropItemMap().get(id);
		
		ThreadSafeRandom random = new ThreadSafeRandom();
		if(pool.getType() == DropItemConstant.DropType.probability){
			for(int i = 0; i < pool.getRepeat(); i++){
				for(DropItemInfo item: items.values()){
					int rate = item.getWeight();
					if(random.isSuccessful(rate, 10000)){
						dropItems.add(item);
					}
				}
			}
		}else if(pool.getType() == DropItemConstant.DropType.weight){
			for(int i = 0; i < pool.getRepeat(); i++){
				
				int totalWeight = 0;
				List<Integer> itemIds = new ArrayList<>();
				for(DropItemInfo item: items.values()){
					totalWeight += item.getWeight();
					itemIds.add(item.getId());
				}
				
				int randomNum = random.next(1, totalWeight);
				
				int flag = 0;
				for(int itemId: itemIds){
					DropItemInfo item = items.get(itemId);
					if(randomNum > flag && randomNum <= item.getWeight()){
						dropItems.add(item);
						break;
					}
					flag += item.getWeight();
				}
			}
		}
		
		return dropItems;
	}
}
