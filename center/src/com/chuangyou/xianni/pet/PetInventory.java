package com.chuangyou.xianni.pet;

import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.pet.manager.PetManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class PetInventory extends AbstractEvent implements IInventory {

	private GamePlayer player;
	
	private PetAtt petAtt;
	
	private Map<Integer, PetInfo> petInfoMap;
	
	private Map<Integer, PetSkill> petSkillMap;
	
	public PetInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		
		this.player = player;
	}
	
	/**
	 * 获取宠物总属性
	 * @return
	 */
	public PetAtt getPetAtt(){
		if(this.petAtt == null){
			petAtt = new PetAtt(player.getPlayerId());
			petAtt.setOp(Option.Insert);
		}
		return this.petAtt;
	}
	
	/**
	 * 更新宠物总属性
	 * @param info
	 * @return
	 */
	public boolean updatePetAtt(PetAtt info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		info.setOp(Option.Update);
		return true;
	}
	
	/**
	 * 获取某个宠物的信息
	 * @param petId
	 * @return
	 */
	public PetInfo getPetInfo(int petId){
		return this.petInfoMap.get(petId);
	}
	
	/**
	 * 激活宠物
	 * @param info
	 * @return
	 */
	public boolean addPetInfo(PetInfo info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		if(this.petInfoMap.get(info.getPetId()) != null){
			info.setOp(Option.Update);
		}else{
			info.setOp(Option.Insert);
		}
		this.petInfoMap.put(info.getPetId(), info);
		return true;
	}
	
	/**
	 * 更新宠物信息
	 * @param info
	 * @return
	 */
	public boolean updatePetInfo(PetInfo info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		info.setOp(Option.Update);
		return true;
	}
	
	/**
	 * 获取宠物技能信息
	 * @param skillId
	 * @return
	 */
	public PetSkill getPetSkill(int skillId){
		return this.petSkillMap.get(skillId);
	}
	
	/**
	 * 激活宠物技能
	 * @param info
	 * @return
	 */
	public boolean addPetSkill(PetSkill info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		if(this.petSkillMap.get(info.getSkillId()) != null){
			info.setOp(Option.Update);
		}else{
			info.setOp(Option.Insert);
		}
		this.petSkillMap.put(info.getSkillId(), info);
		return true;
	}
	
	/**
	 * 更新宠物技能信息
	 * @param info
	 * @return
	 */
	public boolean updatePetSkill(PetSkill info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		info.setOp(Option.Update);
		return true;
	}
	
	/**
	 * 获取宠物战斗总属性，给主角提供的总属性加成
	 * @return
	 */
	public Map<Integer, Integer> getPetPropertyMap(){
		return PetManager.computePetAtt(player);
	}
	
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		petAtt = DBManager.getPetAttDao().get(player.getPlayerId());
		petInfoMap = DBManager.getPetInfoDao().getAll(player.getPlayerId());
		petSkillMap = DBManager.getPetSkillDao().getAll(player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		player = null;
		
		petAtt = null;
		
		if(petInfoMap != null){
			petInfoMap.clear();
		}
		petInfoMap = null;
		
		if(petSkillMap != null){
			petSkillMap.clear();
		}
		petSkillMap = null;
		
		return false;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		short option = 0;
		if(petAtt != null){
			option = petAtt.getOp();
			if(option == Option.Insert){
				//DBManager.getPetAttDao().add(petAtt);
			}else if(option == Option.Update){
				DBManager.getPetAttDao().update(petAtt);
			}
		}
		
		if(petInfoMap != null && petInfoMap.size() > 0){
			for(PetInfo info:petInfoMap.values()){
				option = info.getOp();
				if(option == Option.Insert){
					DBManager.getPetInfoDao().add(info);
				}else if(option == Option.Update){
					DBManager.getPetInfoDao().update(info);
				}
			}
		}
		
		if(petSkillMap != null && petSkillMap.size() > 0){
			for(PetSkill info:petSkillMap.values()){
				option = info.getOp();
				if(option == Option.Insert){
					DBManager.getPetSkillDao().add(info);
				}else if(option == Option.Update){
					DBManager.getPetSkillDao().update(info);
				}
			}
		}
		return true;
	}

	public Map<Integer, PetInfo> getPetInfoMap() {
		return petInfoMap;
	}

	public Map<Integer, PetSkill> getPetSkillMap() {
		return petSkillMap;
	}

}
