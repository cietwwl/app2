package com.chuangyou.xianni.skill.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.chuangyou.common.protobuf.pb.skill.SkillPropertyProto.SkillProperty;
import com.chuangyou.common.protobuf.pb.skill.SkillTotalProResMsgProto.SkillTotalProResMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.army.Living;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.log.SkillLogInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.property.SkillBaseProperty;
import com.chuangyou.xianni.entity.skill.SkillStage;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.log.LogManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.template.SkillTempMgr;

/**
 * 技能管理
 */
public class SkillManager {
	static final int MAX_SKILL_LV = 500;

	/**
	 * 英雄技能升级
	 */
	public static boolean upSkill1(GamePlayer player, int skillId) {
		SkillTempateInfo skillInfo = SkillTempMgr.getSkillTemp(skillId);// 要学习的技能配置
		if (skillInfo == null) {
			Log.error("upSkill but curSkill is not find template :" + skillId);
			return false;
		}
		// 当技能为1级技能，并且没有学，判定为学习初始技能
		SkillTempateInfo nextTemplate = null;
		HeroSkill olderSkill = player.getSkillInventory().getHeroSkill(skillId);
		if (skillInfo.getLevel() == 1 && olderSkill == null) {
			nextTemplate = skillInfo;
		} else {
			nextTemplate = SkillTempMgr.getSkillTemp(skillInfo.getNextTempId());
		}
		// 是否存在下个技能
		if (nextTemplate == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR5, Protocol.C_HERO_UPSKILL);
			return false;
		}

		if (nextTemplate.getLevel() > player.getBasePlayer().getPlayerInfo().getLevel()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR5, Protocol.C_HERO_UPSKILL);
			return false;
		}

		// 判断前置技能是否满足
//		String preTemplateId = skillInfo.getPreTemplateId();// 前置技能
//		if (preTemplateId != null && !preTemplateId.isEmpty()) {
//			String[] preTemplateIds = preTemplateId.split(",");
//			for (String id : preTemplateIds) {
//				int tempId = Integer.valueOf(id);
//				HeroSkill studied = player.getSkillInventory().getHeroSkill(tempId);
//				if (studied == null) {
//					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR2, Protocol.C_HERO_UPSKILL);
//					return false;
//				}
//			}
//		}

		// 判定是否足够支付技能升级消耗
		int payCode = upSkillPay(player, skillInfo);
		if (payCode != 0) {
			ErrorMsgUtil.sendErrorMsg(player, payCode, Protocol.C_HERO_UPSKILL, "消耗物品不足");
			return false;
		}

		// 升级处理
		if (olderSkill == null) {
			olderSkill = new HeroSkill(player.getPlayerId(), skillId);
			olderSkill.setType(skillInfo.getMasterType());
			olderSkill.setSubType(skillInfo.getSonType());
			olderSkill.setSkillLV(skillInfo.getLevel());
			olderSkill.setGrandsonType(skillInfo.getGrandsonType());
		} else {
			olderSkill.setSkillId(nextTemplate.getTemplateId());
		}

		player.getSkillInventory().addOrUpdate(olderSkill);
		player.getSkillInventory().saveToDatabase();

		if (player.getSkillInventory() != null) {
			player.getArmyInventory().updateHeroInfo();
		}
		// 日志
		SkillLogInfo log = new SkillLogInfo();
		log.setPlayerId(player.getPlayerId());
		log.setPerSkillId(olderSkill.getSkillId());
		log.setSkillId(skillInfo.getTemplateId());
		log.setSkillLV(skillInfo.getLevel());
		log.setCreateDate(new Date());
		LogManager.addSkillLog(log);
		return true;
	}

	/***
	 * 一键提升技能
	 * 
	 * @param player
	 * @return
	 */
	public static boolean oneKeyUpSkill(GamePlayer player) {
		// 获取当前所有学习的技能
		List<HeroSkill> toalSkills = new ArrayList<>(player.getSkillInventory().getHeroSkill().values());
		java.util.Collections.sort(toalSkills);

		for (int i = 0; i <= MAX_SKILL_LV; i++) {
			if (toalSkills.size() == 0) {
				break;
			}
			List<HeroSkill> beRemoved = new ArrayList<>();
			for (HeroSkill curSkill : toalSkills) {
				// System.out.println(curSkill.getSkillLV());
				// 升级失败，则加入删除名单
				if (!upSkill1(player, curSkill.getSkillId())) {
					beRemoved.add(curSkill);
				}
			}
			// 剔除升级失败技能
			for (HeroSkill hs : beRemoved) {
				toalSkills.remove(hs);
			}
		}
		return false;
	}

	/**
	 * 升技能阶段
	 */
	public static boolean upSkillStage(GamePlayer player, int stage) {
		// 技能阶段处理
		int skillStage = player.getBasePlayer().getPlayerInfo().getSkillStage() + 1;
		boolean isOpen = checkSkillStage(player, skillStage);
		if (isOpen) {
			player.getBasePlayer().updateSkillStage(skillStage);
			if (player.getSkillInventory() != null) {
				player.getSkillInventory().updataProperty();
			}
		} else {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR6, Protocol.C_HERO_UPHEROSTAGECMD);
			return false;
		}
		return true;
	}

	/**
	 * 检测技能阶段是否开启了
	 * 
	 * @param stageLv
	 *            要检测的节能阶段
	 * @return
	 */
	public static boolean checkSkillStage(GamePlayer player, int stageLv) {
		SkillStage skillStage = SkillTempMgr.getSkillStage(stageLv);
		if (skillStage == null) {
			return false;
		}
		String str = skillStage.getCondition();// 条件
		String[] condition = str.split(",");
		for (String string : condition) {
			if (string.isEmpty()) {
				continue;
			}
			int skillId = Integer.valueOf(string);// 需要的技能
			SkillTempateInfo skill = SkillTempMgr.getSkillTemp(skillId);
			// 查看是否满足学习技能
			for (int i = 0; i <= 100; i++) {
				if (skill == null) {
					return false;
				}
				if (player.getSkillInventory().getHeroSkill(skill.getTemplateId()) != null) {
					return true;
				}
				skill = SkillTempMgr.getSkillTemp(skill.getNextTempId());
			}
		}
		return true;
	}

	public static SkillTotalProResMsg.Builder getSkillTotalProResMsg(GamePlayer player, BaseProperty skillBasePro) {
		SkillTotalProResMsg.Builder msg = SkillTotalProResMsg.newBuilder();

		SkillProperty.Builder beanSoul = SkillProperty.newBuilder();
		beanSoul.setType(EnumAttr.SOUL.getValue());
		beanSoul.setPro(skillBasePro.getSoul());
		msg.addInfo(beanSoul);

		SkillProperty.Builder beanBlood = SkillProperty.newBuilder();
		beanBlood.setType(EnumAttr.BLOOD.getValue());
		beanBlood.setPro(skillBasePro.getBlood());
		msg.addInfo(beanBlood);

		SkillProperty.Builder beanAttack = SkillProperty.newBuilder();
		beanAttack.setType(EnumAttr.ATTACK.getValue());
		beanAttack.setPro(skillBasePro.getAttack());
		msg.addInfo(beanAttack);

		SkillProperty.Builder beanDefence = SkillProperty.newBuilder();
		beanDefence.setType(EnumAttr.DEFENCE.getValue());
		beanDefence.setPro(skillBasePro.getDefence());
		msg.addInfo(beanDefence);

		SkillProperty.Builder beanSoulAttack = SkillProperty.newBuilder();
		beanSoulAttack.setType(EnumAttr.SOUL_ATTACK.getValue());
		beanSoulAttack.setPro(skillBasePro.getSoulAttack());
		msg.addInfo(beanSoulAttack);

		SkillProperty.Builder beanSoulDefence = SkillProperty.newBuilder();
		beanSoulDefence.setType(EnumAttr.SOUL_DEFENCE.getValue());
		beanSoulDefence.setPro(skillBasePro.getSoulDefence());
		msg.addInfo(beanSoulDefence);

		SkillProperty.Builder beanAccurate = SkillProperty.newBuilder();
		beanAccurate.setType(EnumAttr.ACCURATE.getValue());
		beanAccurate.setPro(skillBasePro.getAccurate());
		msg.addInfo(beanAccurate);

		SkillProperty.Builder beanCrit = SkillProperty.newBuilder();
		beanCrit.setType(EnumAttr.CRIT.getValue());
		beanCrit.setPro(skillBasePro.getCrit());
		msg.addInfo(beanCrit);

		SkillProperty.Builder beanDodge = SkillProperty.newBuilder();
		beanDodge.setType(EnumAttr.DODGE.getValue());
		beanDodge.setPro(skillBasePro.getDodge());
		msg.addInfo(beanDodge);

		SkillProperty.Builder beanCritDefence = SkillProperty.newBuilder();
		beanCritDefence.setType(EnumAttr.CRIT_DEFENCE.getValue());
		beanCritDefence.setPro(skillBasePro.getCritDefence());
		msg.addInfo(beanCritDefence);
		msg.setStage(player.getBasePlayer().getPlayerInfo().getSkillStage());

		return msg;
	}

	/** 属性赋值 */
	public static SkillBaseProperty setPro(SkillBaseProperty proData, int type, int v) {
		switch (type) {
			case Living.SOUL:
				proData.addSoul(v);
				break;
			case Living.BLOOD:
				proData.addBlood(v);
				break;
			case Living.ATTACK:
				proData.addAttack(v);
				break;
			case Living.DEFENCE:
				proData.addDefence(v);
				break;
			case Living.SOUL_ATTACK:
				proData.addSoulAttack(v);
				break;
			case Living.SOUL_DEFENCE:
				proData.addSoulDefence(v);
				break;
			case Living.ACCURATE:
				proData.addAccurate(v);
				break;
			case Living.DODGE:
				proData.addDodge(v);
				break;
			case Living.CRIT:
				proData.addCrit(v);
				break;
			case Living.CRIT_DEFENCE:
				proData.addCritDefence(v);
				break;
			case Living.CRIT_ADDTION:
				proData.addCritAddtion(v);
				break;
			case Living.CRIT_CUT:
				proData.addCritCut(v);
				break;
			case Living.BLOOD_ATTACK_ADDTION:
				proData.addBloodAttackAddtion(v);
				break;
			case Living.BLOOD_ATTACK_CUT:
				proData.addBloodAttackCut(v);
				break;
			case Living.SOUL_ATTACK_ADDTION:
				proData.addSoulAttackAddtion(v);
				break;
			case Living.SOUL_ATTACK_CUT:
				proData.addSoulAttackCut(v);
				break;
			case Living.REGAIN_SOUL:
				proData.addRegainSoul(v);
				break;
			case Living.REGAIN_BLOOD:
				proData.addRegainBlood(v);
				break;
			case Living.METAL:
				proData.addMetal(v);
				break;
			case Living.WOOD:
				proData.addWood(v);
				break;
			case Living.WATER:
				proData.addWater(v);
				break;
			case Living.FIRE:
				proData.addFire(v);
				break;
			case Living.EARTH:
				proData.addEarth(v);
				break;
			case Living.METAL_DEFENCE:
				proData.addMetalDefence(v);
				break;
			case Living.WOOD_DEFENCE:
				proData.addWoodDefence(v);
				break;
			case Living.WATER_DEFENCE:
				proData.addWaterDefence(v);
				break;
			case Living.FIRE_DEFENCE:
				proData.addFireDefence(v);
				break;
			case Living.EARTH_DEFENCE:
				proData.addEarthDefence(v);
				break;
			case Living.SPEED:
				proData.addSpeed(v);
				break;
		}
		return proData;
	}

	private static int upSkillPay(GamePlayer player, SkillTempateInfo skillInfo) {
		int needStone = skillInfo.getNeedStone(); // 技能升级需要灵石
		int needRepair = skillInfo.getNeedRepair(); // 技能升级需要修为
		int needJade = skillInfo.getNeedJade(); // 技能升级需要仙玉
		// 技能升级需要物品 格式 物品id,数量#物品id,数量
		Map<Integer, Integer> costItems = decodeGoods(skillInfo.getNeedGoods());
		// 检测灵石
		if (needStone > 0 && player.getBasePlayer().getPlayerInfo().getMoney() < needStone) {
			return ErrorCode.Money_UnEnough;
		}
		// 检测仙玉
		if (needJade > 0 && player.getBasePlayer().getPlayerInfo().getCash() < needJade) {
			return ErrorCode.Cash_UnEnough;
		}
		// 检测修为
		if (player.getBasePlayer().getPlayerInfo().getRepair() < needRepair) {
			return ErrorCode.SKILL_UP_ERROR4;
		}
		// 检测物品
		for (Entry<Integer, Integer> entry : costItems.entrySet()) {
			if (player.getBagInventory().getPlayerBagItemCount(entry.getKey()) < entry.getValue()) {
				return ErrorCode.Prop_Is_Not_Enougth;
			}
		}

		// 扣灵石
		if (needStone > 0)
			player.getBasePlayer().consumeMoney(needStone, ItemRemoveType.UP_SKILL);
		// 扣仙玉
		if (needJade > 0)
			player.getBasePlayer().consumeCash(needJade, ItemRemoveType.UP_SKILL);
		// 扣修为
		if (needRepair > 0)
			player.getBasePlayer().consumeRepair(needRepair);
		// 扣道具
		// 检测物品
		for (Entry<Integer, Integer> entry : costItems.entrySet()) {
			player.getBagInventory().removeItemFromPlayerBag(entry.getKey(), entry.getValue(), ItemRemoveType.UPDATA_SKILL_USE);
		}
		return 0;
	}

	private static Map<Integer, Integer> decodeGoods(String needGoods) {
		Map<Integer, Integer> result = new HashMap<>();
		try {
			if (!StringUtils.isNullOrEmpty(needGoods)) {
				String[] needGoods_1 = needGoods.split("#");
				for (String string : needGoods_1) {
					String[] goods = string.split(",");
					result.put(Integer.valueOf(goods[0]), Integer.valueOf(goods[1]));
				}
			}
			return result;
		} catch (Exception e) {
			Log.error("decodeGoods has exception : " + needGoods, e);
			// 当配置数据出现异常，不允许进行升级
			result.put(-10000, Integer.MAX_VALUE);
		}
		return result;
	}
}
