package com.chuangyou.xianni.pet.manager;

import java.util.List;

import com.chuangyou.common.protobuf.pb.pet.PetSkillBeanProto.PetSkillBeanMsg;
import com.chuangyou.common.protobuf.pb.pet.PetSkillUpdateRespProto.PetSkillUpdateRespMsg;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class PetAttManager {
	/** 宠物技能最高等级，需跟随PetSkillLevel表数据 */
	public static final int PET_SKILL_LV_MAX = 10;
//	public static int getPetSkillGridInitNum() throws MXY2Exception {
//		Map<Integer, PetSkillSlot> map = DataDictionaryManager.getIns().getPetDataContainer().getPetSkillSlotMap();
//		int num = 0;
//		while (map.get(num+1).getNeedItemNum()==0) {
//			num++;
//		}
//		return num;
//	}
	/** 返回宠物技能等级表的键值 */
	public static int getPetSkillKey(PetSkill skill) {
		return skill.getSkillId() * 1000 + skill.getLevel();
	}
	/** 转换为序列化bean */
	public static PetSkillBeanMsg.Builder toBean(PetSkill info) {
		PetSkillBeanMsg.Builder bean = PetSkillBeanMsg.newBuilder();
		bean.setSkillId(info.getSkillId());
		bean.setState(info.getState());
		bean.setSlotIndex(info.getSlotIndex());
		bean.setLevel(info.getLevel());
		return bean;
	}
	/** 更新宠物技能前后台 */
	public static void updateSkill(GamePlayer player, PetSkill... petSkillArray) {
		PetSkillUpdateRespMsg.Builder msg = PetSkillUpdateRespMsg.newBuilder();
		for(PetSkill skill:petSkillArray){
			if(skill != null){
				player.getPetInventory().updatePetSkill(skill);
				msg.addSkillInfo(toBean(skill));
			}
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_SKILL_UPDATE, msg);
		player.sendPbMessage(p);
	}
}
