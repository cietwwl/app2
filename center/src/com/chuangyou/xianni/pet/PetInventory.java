package com.chuangyou.xianni.pet;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.pet.PetActivateRespProto.PetActivateRespMsg;
import com.chuangyou.common.protobuf.pb.pet.PetInfoBeanProto.PetInfoBeanMsg;
import com.chuangyou.common.protobuf.pb.player.OtherPetProto.OtherPetMsg;
import com.chuangyou.xianni.army.Hero;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.pet.manager.PetManager;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.DBManager;

public class PetInventory extends AbstractEvent implements IInventory {

	private GamePlayer				player;

	private PetAtt					petAtt;

	private Map<Integer, PetInfo>	petInfoMap;

	private Map<Integer, PetSkill>	petSkillMap;

	public PetInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	/**
	 * 获取宠物总属性
	 * 
	 * @return
	 */
	public PetAtt getPetAtt() {
		if (this.petAtt == null) {
			petAtt = new PetAtt(player.getPlayerId());
			petAtt.setOp(Option.Insert);
		}
		return this.petAtt;
	}

	/**
	 * 更新宠物总属性
	 * 
	 * @param info
	 * @return
	 */
	public boolean updatePetAtt(PetAtt info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取某个宠物的信息
	 * 
	 * @param petId
	 * @return
	 */
	public PetInfo getPetInfo(int petId) {
		return this.petInfoMap.get(petId);
	}

	/**
	 * 激活宠物
	 * 
	 * @param info
	 * @return
	 */
	public boolean addPetInfo(PetInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		if (this.petInfoMap.get(info.getPetId()) != null) {
			info.setOp(Option.Update);
		} else {
			info.setOp(Option.Insert);
		}
		this.petInfoMap.put(info.getPetId(), info);
		return true;
	}

	/**
	 * 更新宠物信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean updatePetInfo(PetInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取宠物技能信息
	 * 
	 * @param skillId
	 * @return
	 */
	public PetSkill getPetSkill(int skillId) {
		return this.petSkillMap.get(skillId);
	}

	/**
	 * 激活宠物技能
	 * 
	 * @param info
	 * @return
	 */
	public boolean addPetSkill(PetSkill info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		if (this.petSkillMap.get(info.getSkillId()) != null) {
			info.setOp(Option.Update);
		} else {
			info.setOp(Option.Insert);
		}
		this.petSkillMap.put(info.getSkillId(), info);
		return true;
	}

	/**
	 * 更新宠物技能信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean updatePetSkill(PetSkill info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取宠物战斗总属性，给主角提供的总属性加成
	 * 
	 * @return
	 */
	public Map<Integer, Integer> getPetPropertyMap() {
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

		if (petInfoMap != null) {
			petInfoMap.clear();
		}
		petInfoMap = null;

		if (petSkillMap != null) {
			petSkillMap.clear();
		}
		petSkillMap = null;

		return false;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		short option = 0;
		if (petAtt != null) {
			option = petAtt.getOp();
			if (option == Option.Insert) {
				DBManager.getPetAttDao().add(petAtt);
			} else if (option == Option.Update) {
				DBManager.getPetAttDao().update(petAtt);
			}
		}

		if (petInfoMap != null && petInfoMap.size() > 0) {
			for (PetInfo info : petInfoMap.values()) {
				option = info.getOp();
				if (option == Option.Insert) {
					DBManager.getPetInfoDao().add(info);
				} else if (option == Option.Update) {
					DBManager.getPetInfoDao().update(info);
				}
			}
		}

		if (petSkillMap != null && petSkillMap.size() > 0) {
			for (PetSkill info : petSkillMap.values()) {
				option = info.getOp();
				if (option == Option.Insert) {
					DBManager.getPetSkillDao().add(info);
				} else if (option == Option.Update) {
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

	/**
	 * 激活宠物
	 * @param petTemplateId
	 */
	public void activePet(int petTemplateId){
		PetInfo pet = player.getPetInventory().getPetInfo(petTemplateId);
		
		if(pet != null){
			return;
		}
		
		PetInfoCfg petCfg = PetTemplateMgr.getPetTemps().get(petTemplateId);
		
		if(petCfg == null){
			return;
		}
		
		pet = new PetInfo(player.getPlayerId(), petTemplateId);
		addPetInfo(pet);
		
		PetActivateRespMsg.Builder msg = PetActivateRespMsg.newBuilder();
		
		PetInfoBeanMsg.Builder bean = PetInfoBeanMsg.newBuilder();
		bean.setPetId(pet.getPetId());
		bean.setTalent(pet.getTalent());
		bean.setLevel(pet.getLevel());
		bean.setLevelExp(pet.getLevelExp());
		bean.setPhysique(pet.getPhysique());
		bean.setQuality(pet.getQuality());
		bean.setQualityBless(pet.getQualityBless());
		bean.setGrade(pet.getGrade());
		bean.setGradeBless(pet.getGradeBless());
		msg.setPetInfo(bean);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_ACTIVATE, msg);
		player.sendPbMessage(p);
		
		//宠物总属性改变
//		PetManager.changePetAtt(roleId);
		//影响人物属性改变
		player.getPetInventory().updataProperty();
		
		player.notifyListeners(new ObjectEvent(this, pet.getPetId(), EventNameType.PET_ACTIVE));
	}
	
	
	/**
	 * 计算玩家宠物属性
	 * 
	 * @param roleId
	 * @throws MXY2Exception
	 */
	public void computePetAtt(BaseProperty petData, BaseProperty petPer) {
		// List<Integer> toalPro = new ArrayList<>();
		//
		// Map<Integer, PetInfo> rolePetInfo =
		// player.getPetInventory().getPetInfoMap();
		// for (PetInfo pet : rolePetInfo.values()) {
		// // 等级加成
		// PetLevelCfg petLevelCfg =
		// PetTemplateMgr.getLevelTemps().get(pet.getPetId() * 1000 +
		// pet.getLevel());
		// toalPro.addAll(petLevelCfg.getAttList());
		//
		// PetInfoCfg petInfoCfg =
		// PetTemplateMgr.getPetTemps().get(pet.getPetId());
		// if (petInfoCfg.getIsSpecial() == 0) {
		// // 炼体加成
		// if (pet.getPhysique() > 0) {
		// PetPhysiqueCfg petPhyCfg =
		// PetTemplateMgr.getPhysiqueTemps().get(pet.getPetId() * 1000 +
		// pet.getPhysique());
		// toalPro.addAll(petPhyCfg.getAttList());
		// }
		//
		// // 品质加成
		// if (pet.getQuality() > 0) {
		// PetQualityCfg petQualityCfg =
		// PetTemplateMgr.getQualityTemps().get(pet.getPetId() * 1000 +
		// pet.getQuality());
		// toalPro.addAll(petQualityCfg.getAttList());
		// }
		//
		// // 进阶加成
		// if (pet.getGrade() > 0) {
		// PetGradeCfg petGradeCfg =
		// PetTemplateMgr.getGradeTemps().get(pet.getPetId() * 1000 +
		// pet.getGrade());
		// toalPro.addAll(petGradeCfg.getAttList());
		// }
		// }
		// }
		//
		// // 炼魂加成
		// PetAtt petAtt = player.getPetInventory().getPetAtt();
		// PetSoulCfg petSoulCfg =
		// PetTemplateMgr.getSoulTemps().get(petAtt.getSoulLv());
		// toalPro.addAll(petSoulCfg.getAttList());

		Map<Integer, Integer> attMap = PetManager.computePetAtt(player);
		for (int attType : attMap.keySet()) {
			SimpleProperty property = SkillUtil.readPro(attType, attMap.get(attType));
			if (property.isPre()) {
				SkillUtil.joinPro(petPer, property.getType(), property.getValue());
			} else {
				SkillUtil.joinPro(petData, property.getType(), property.getValue());
			}
		}

		// for (Integer pro : toalPro) {
		// SimpleProperty property = SkillUtil.readPro(pro);
		// if (property.isPre()) {
		// SkillUtil.joinPro(petPer, property.getType(), property.getValue());
		// } else {
		// SkillUtil.joinPro(petData, property.getType(), property.getValue());
		// }
		// }
	}

	public void updataProperty() {
		if (player.getArmyInventory() != null) {
			BaseProperty skillData = new BaseProperty();
			BaseProperty skillPer = new BaseProperty();
			// 加入技能属性
			computePetAtt(skillData, skillPer);
			player.getArmyInventory().getHero().addPet(skillData, skillPer);
			player.getArmyInventory().updateProperty();
		}
	}

	/** 写入其他用户查看信息 */
	public void writeInSimpOtherSnap(OtherPetMsg.Builder proto) {
		if (petAtt == null || petInfoMap == null || petInfoMap.size() == 0) {
			return;
		}
		proto.setPlayerId(petAtt.getPlayerId());
		PetInfo petInfo = petInfoMap.get(petAtt.getFightPetId());
		if (petInfo == null) {
			return;
		}
		proto.setPetId(petAtt.getFightPetId());
		proto.setLevel(petInfo.getLevel());
		proto.setGrade(petInfo.getGrade());
		proto.setPhysique(petInfo.getPhysique());
		proto.setQuality(petInfo.getQuality());
		
		BaseProperty skillData = new BaseProperty();
		BaseProperty skillPer = new BaseProperty();
		// 加入技能属性
		computePetAtt(skillData, skillPer);
		Hero tempHero = new Hero(this.player);
		tempHero.addMagicwp(skillData, skillPer);
		PropertyListMsg.Builder propertyMsgs = PropertyListMsg.newBuilder();
		tempHero.writeProto(propertyMsgs);
		proto.setPropertitys(propertyMsgs);
		tempHero = null;
	}

}
