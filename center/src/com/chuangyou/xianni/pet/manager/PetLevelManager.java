package com.chuangyou.xianni.pet.manager;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetLevelCfg;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;

public class PetLevelManager {

	public static boolean petUpLevelByItem(GamePlayer player, PetInfo pet, int useItem, short protocolCode){
		PetLevelCfg petLevel = PetTemplateMgr.getLevelTemps().get(pet.getPetId() * 1000 + pet.getLevel());
		
		int hasExp = 0;
		if(useItem == petLevel.getItemId1()){
			hasExp = petLevel.getItemExp1();
		}else if(useItem == petLevel.getItemId2()){
			hasExp = petLevel.getItemExp2();
		}else if(useItem == petLevel.getItemId3()){
			hasExp = petLevel.getItemExp3();
		}else{
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Use_Error, protocolCode);
			return false;
		}
		
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(useItem) < 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, protocolCode);
			return false;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(useItem, 1, ItemRemoveType.PET_LEVELUP)) return false;
		
		int curLevelExp = pet.getLevelExp();
		int targetLevel = pet.getLevel();
		while(petLevel != null && hasExp >= petLevel.getExp() - curLevelExp){
			targetLevel ++;
			hasExp -= petLevel.getExp() - curLevelExp;
			petLevel = PetTemplateMgr.getLevelTemps().get(pet.getPetId() * 1000 + targetLevel);
			curLevelExp = 0;
		}
		if(petLevel == null){
			targetLevel --;
			hasExp = 0;
		}
		pet.setLevel(targetLevel);
		pet.setLevelExp(curLevelExp + hasExp);
		
		return player.getPetInventory().updatePetInfo(pet);
	}
	
	public static boolean petUpLevelOneKey(GamePlayer player, PetInfo pet, short protocolCode){
		PetLevelCfg petLevel = PetTemplateMgr.getLevelTemps().get(pet.getPetId() * 1000 + pet.getLevel());
		
		int hasItemNum1 = player.getBagInventory().getPlayerBagItemCount(petLevel.getItemId1());
		int hasItemNum2 = player.getBagInventory().getPlayerBagItemCount(petLevel.getItemId2());
		int hasItemNum3 = player.getBagInventory().getPlayerBagItemCount(petLevel.getItemId3());
		if(hasItemNum1 <= 0 && hasItemNum2 <= 0 && hasItemNum3 <= 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, protocolCode);
			return false;
		}
		
		int needExp = petLevel.getExp() - pet.getLevelExp();
		
		int needItemNum1 = (int) Math.ceil(needExp / petLevel.getItemExp1());
		
		if(hasItemNum1 >= needItemNum1){
			if(needItemNum1 <= 1){
				return PetLevelManager.petUpLevelByItem(player, pet, petLevel.getItemId1(), protocolCode);
				
			}
			//扣物品
			if(!player.getBagInventory().removeItemFromPlayerBag(petLevel.getItemId1(), needItemNum1, ItemRemoveType.PET_LEVELUP)) return false;
			
			pet.setLevel(pet.getLevel() + 1);
			pet.setLevelExp(needItemNum1 * petLevel.getItemExp1() - needExp);
		}else{
			//扣物品
			if(!player.getBagInventory().removeItemFromPlayerBag(petLevel.getItemId1(), hasItemNum1, ItemRemoveType.PET_LEVELUP)) return false;
			
			int addExp = hasItemNum1 * petLevel.getItemExp1();
			pet.setLevelExp(pet.getLevelExp() + addExp);
			needExp -= addExp;
			
			int needItemNum2 = (int) Math.ceil(needExp / petLevel.getItemExp2());
			
			if(hasItemNum2 >= needItemNum2){
				if(needItemNum2 <= 1){
					return PetLevelManager.petUpLevelByItem(player, pet, petLevel.getItemId2(), protocolCode);
				}
				//扣物品
				if(!player.getBagInventory().removeItemFromPlayerBag(petLevel.getItemId2(), needItemNum2, ItemRemoveType.PET_LEVELUP)) return false;
				
				pet.setLevel(pet.getLevel() + 1);
				pet.setLevelExp(needItemNum2 * petLevel.getItemExp2() - needExp);
			}else{
				//扣物品
				if(!player.getBagInventory().removeItemFromPlayerBag(petLevel.getItemId2(), hasItemNum2, ItemRemoveType.PET_LEVELUP)) return false;
				
				addExp = hasItemNum2 * petLevel.getItemExp2();
				pet.setLevelExp(pet.getLevelExp() + addExp);
				needExp -= addExp;
				
				int needItemNum3 = (int) Math.ceil(needExp / petLevel.getItemExp3());
				
				if(hasItemNum3 >= needItemNum3){
					if(needItemNum3 <= 1){
						return PetLevelManager.petUpLevelByItem(player, pet, petLevel.getItemId3(), protocolCode);
					}
					//扣物品
					if(!player.getBagInventory().removeItemFromPlayerBag(petLevel.getItemId3(), needItemNum3, ItemRemoveType.PET_LEVELUP)) return false;
					
					pet.setLevel(pet.getLevel() + 1);
					pet.setLevelExp(needItemNum3 * petLevel.getItemExp3() - needExp);
				}else{
					//扣物品
					if(!player.getBagInventory().removeItemFromPlayerBag(petLevel.getItemId3(), hasItemNum3, ItemRemoveType.PET_LEVELUP)) return false;
					
					addExp = hasItemNum3 * petLevel.getItemExp3();
					pet.setLevelExp(pet.getLevelExp() + addExp);
				}
			}
		}
		return player.getPetInventory().updatePetInfo(pet);
	}
}
