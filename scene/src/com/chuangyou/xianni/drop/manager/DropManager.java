package com.chuangyou.xianni.drop.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.constant.DropItemConstant;
import com.chuangyou.xianni.drop.objects.DropItem;
import com.chuangyou.xianni.drop.objects.DropPackage;
import com.chuangyou.xianni.drop.templete.DropTempleteMgr;
import com.chuangyou.xianni.entity.drop.DropInfo;
import com.chuangyou.xianni.entity.drop.DropItemInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class DropManager {

	private static List<DropItemInfo> getDropList(int id){
		
		DropInfo pool = DropTempleteMgr.getDropPool().get(id);
		
		List<DropItemInfo> dropItems = new ArrayList<>();
		Map<Integer, DropItemInfo> items = DropTempleteMgr.getDropItemMap().get(id);
		
		ThreadSafeRandom random = new ThreadSafeRandom();
		if(pool.getType() == DropItemConstant.DropType.probability){
			for(int i = 0; i < pool.getRepeat(); i++){
				for(DropItemInfo item: items.values()){
					int rate = item.getWeight();
					if(random.isSuccessful(rate, 1000000)){
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
	
	public static void drop(long playerId, long dropRoleId, int dropPoolId, Vector3 v3){
		DropInfo pool = DropTempleteMgr.getDropPool().get(dropPoolId);
		
		if(pool.getLimitType() > 0){
			long curTime = TimeUtil.getSysCurTimeMillis();
			if(curTime < TimeUtil.getDateByString(pool.getStartTime(), pool.getLimitType()).getTime() || curTime > TimeUtil.getDateByString(pool.getEndTime(), pool.getLimitType()).getTime()){
				return;
			}
		}
		
		List<DropItemInfo> dropItems = getDropList(dropPoolId);
		
		if(dropItems.size() <= 0) return;
		
		DropPackage drop = new DropPackage();
		drop.setDropId(IDMakerHelper.dropId());
		drop.setPlayerId(playerId);
		drop.setDropRoleId(dropRoleId);
		drop.setPoolId(dropPoolId);
		drop.setV3(v3);
		drop.setDropTime((new Date()).getTime());
		drop.setDropItems(new HashMap<>());
		
		for(DropItemInfo itemInfo: dropItems){
			DropItem item = new DropItem();
			item.setId(drop.getDropId() * 10 + drop.getDropItems().size());
			item.setDropItemTempId(itemInfo.getId());
			
			drop.getDropItems().put(item.getId(), item);
		}
		
		ArmyProxy army = WorldMgr.getArmy(playerId);
		Field field = FieldMgr.getIns().getField(army.getFieldId());
		
		field.addDrop(drop);
	}
	
	public static void dropFromMonster(int monsterId, long playerId, long dropRoleId, Vector3 v3){
		MonsterInfo info = MonsterInfoTemplateMgr.monsterInfoTemps.get(monsterId);
		if(info.getDrop1() > 0){
			drop(playerId, dropRoleId, info.getDrop1(), v3);
		}
		if(info.getDrop2() > 0){
			drop(playerId, dropRoleId, info.getDrop2(), v3);
		}
		if(info.getDrop3() > 0){
			drop(playerId, dropRoleId, info.getDrop3(), v3);
		}
		if(info.getDrop4() > 0){
			drop(playerId, dropRoleId, info.getDrop4(), v3);
		}
	}
}
