package com.chuangyou.xianni.pet.manager;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.AttributeUtil;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetGradeCfg;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetLevelCfg;
import com.chuangyou.xianni.entity.pet.PetPhysiqueCfg;
import com.chuangyou.xianni.entity.pet.PetQualityCfg;
import com.chuangyou.xianni.entity.pet.PetSoulCfg;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;

public class PetManager {
	/**
	 * 计算玩家宠物属性
	 * @param roleId
	 * @throws MXY2Exception
	 */
	public static Map<Integer, Integer> computePetAtt(GamePlayer player){
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
		
		Map<Integer, PetInfo> rolePetInfo = player.getPetInventory().getPetInfoMap();
		for(PetInfo pet:rolePetInfo.values()){
			Map<Integer, Integer> petAtts = new HashMap<>();
			//等级加成
			PetLevelCfg petLevelCfg = PetTemplateMgr.getLevelTemps().get(pet.getPetId() * 1000 + pet.getLevel());
			AttributeUtil.putAttListToMap(petAtts, petLevelCfg.getAttList());
			
			PetInfoCfg petInfoCfg = PetTemplateMgr.getPetTemps().get(pet.getPetId());
			if(petInfoCfg.getIsSpecial() == 0){
				//炼体加成
				if(pet.getPhysique() > 0){
					PetPhysiqueCfg petPhyCfg = PetTemplateMgr.getPhysiqueTemps().get(pet.getPetId() * 1000 + pet.getPhysique());
					AttributeUtil.putAttListToMap(petAtts, petPhyCfg.getAttList());
				}
				
				//进阶加成
				if(pet.getGrade() > 0){
					PetGradeCfg petGradeCfg = PetTemplateMgr.getGradeTemps().get(pet.getPetId() * 1000 + pet.getGrade());
					AttributeUtil.putAttListToMap(petAtts, petGradeCfg.getAttList());
				}
				
				//品质加成
				if(pet.getQuality() > 0){
					PetQualityCfg petQualityCfg = PetTemplateMgr.getQualityTemps().get(pet.getPetId() * 1000 + pet.getQuality());
//					AttributeUtil.putAttListToMap(petAtts, petQualityCfg.getAttList());
					
					for(int attType:petAtts.keySet()){
						int attValue = petAtts.get(attType) * (1 + petQualityCfg.getAttPer()/1000);
						petAtts.put(attType, attValue);
					}
				}
			}
			AttributeUtil.putAttToMap(attMap, petAtts);
		}
		
		//炼魂加成
		PetAtt petAtt = player.getPetInventory().getPetAtt();
		PetSoulCfg petSoulCfg = PetTemplateMgr.getSoulTemps().get(petAtt.getSoulLv());
		AttributeUtil.putAttListToMap(attMap, petSoulCfg.getAttList());
		
		return attMap;
	}
}
