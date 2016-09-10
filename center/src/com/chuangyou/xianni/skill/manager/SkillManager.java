package com.chuangyou.xianni.skill.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg;
import com.chuangyou.common.protobuf.pb.skill.SkillPropertyProto.SkillProperty;
import com.chuangyou.common.protobuf.pb.skill.SkillTotalProResMsgProto.SkillTotalProResMsg;
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
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.skill.SkillInventory;
import com.chuangyou.xianni.skill.template.SkillTempMgr;

/**
 * 技能管理
 * 
 * @author Administrator
 */
public class SkillManager {
	/** 基础技能类型 **/
	public static final int baseSkillType = 2;

	/**
	 * 英雄技能升级
	 * 
	 * @param player
	 * @param skillId
	 *            要升级的技能id
	 * @return
	 */
	public static boolean upSkill(GamePlayer player, int skillId, PBMessage packet) {
		// System.out.println(
		// "Repair: " + player.getBasePlayer().getPlayerInfo().getRepair() + "
		// playerId: " + player.getBasePlayer().getPlayerInfo().getPlayerId() +
		// " skillId: " + skillId);
		SkillTempateInfo skillInfo = SkillTempMgr.getSkillTemp(skillId);// 要学习的技能配置
		if (skillInfo == null) { // 已经至最高级
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR5, packet.getCode());
			return false;
		}
		int upLv = skillInfo.getLevel();
		int nowSkillLV = getSkillTypeLv(player, skillInfo.getMasterType(), skillInfo.getSonType(), skillInfo.getGrandsonType());
		if (upLv - nowSkillLV != 1) { // 不可跳级
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR, packet.getCode());
			return false;
		}
		// 计算需要的资源
		HeroSkill alreadyStudySkill = getSkillTypeSkill(player, skillInfo.getMasterType(), skillInfo.getSonType(), skillInfo.getGrandsonType());
		int needStone = 0; // 技能升级需要灵石
		int needRepair = 0; // 技能升级需要修为
		int needJade = 0; // 技能升级需要仙玉
		String needGoods = ""; // 技能升级需要物品 格式 物品id,数量#物品id,数量
		if (alreadyStudySkill != null) {
			SkillTempateInfo temp = SkillTempMgr.getSkillTemp(alreadyStudySkill.getSkillId());// 技能配置
			needStone = temp.getNeedStone(); // 技能升级需要灵石
			needRepair = temp.getNeedRepair(); // 技能升级需要修为
			needJade = temp.getNeedJade(); // 技能升级需要仙玉
			needGoods = temp.getNeedGoods(); // 技能升级需要物品 格式 物品id,数量#物品id,数量
		}
		String preTemplateId = skillInfo.getPreTemplateId();// 前置技能
		// 判断前置技能是否满足
		if (preTemplateId != null && !preTemplateId.isEmpty()) {
			String[] preTemplateIds = preTemplateId.split(",");
			for (String id : preTemplateIds) {
				if (Integer.valueOf(id) == 0)
					continue;
				SkillTempateInfo skill = SkillTempMgr.getSkillTemp(Integer.valueOf(id));
				if (skill == null) {
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR2, packet.getCode());
					return false;
				}
				int masterType = skill.getMasterType();// 前置技能父类型
				int sonType = skill.getSonType();// 前置技能子类型
				int grandsonType = skill.getGrandsonType();
				int lv = skill.getLevel();// 前置技能等级(需要的等级)
				int skillLV = getSkillTypeLv(player, masterType, sonType, grandsonType);
				if (skillLV < lv) {
					// System.out.println("前置技能id：" + id + " 不满足 类型 ：" +
					// masterType + " " + sonType + " " + grandsonType);
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR3, packet.getCode());
					return false;
				}
			}
		}
		// 检测灵石
		if (needStone > 0 && player.getBasePlayer().getPlayerInfo().getMoney() < needStone) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode(), "灵石不足");
			return false;
		}
		// 检测仙玉
		if (needJade > 0 && player.getBasePlayer().getPlayerInfo().getCash() < needJade) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Cash_UnEnough, packet.getCode(), "仙玉不足");
			return false;
		}
		// 检测修为
		if (player.getBasePlayer().getPlayerInfo().getRepair() < needRepair) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR4, packet.getCode(), "修为不足");
			return false;
		}
		// 检测物品
		String[] needGoods_1 = needGoods.split("#");
		for (String string : needGoods_1) {
			String[] goods = string.split(",");
			if (goods.length == 0)
				continue;
			if (!goods[0].isEmpty() && player.getBagInventory().getPlayerBagItemCount(Integer.valueOf(goods[0])) < Integer.valueOf(goods[1])) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
				return false;
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
		for (String string : needGoods_1) {
			String[] goods = string.split(",");
			if (!goods[0].isEmpty())
				player.getBagInventory().removeItemFromPlayerBag(Integer.valueOf(goods[0]), Integer.valueOf(goods[1]), ItemRemoveType.USE);
		}

		// 升级处理
		HeroSkill heroSkill = new HeroSkill();
		heroSkill.setPlayerId(player.getPlayerId());
		heroSkill.setSkillId(skillId);
		heroSkill.setType(skillInfo.getMasterType());
		heroSkill.setSubType(skillInfo.getSonType());
		heroSkill.setSkillLV(skillInfo.getLevel());
		heroSkill.setGrandsonType(skillInfo.getGrandsonType());
		player.getSkillInventory().addOrUpdate(heroSkill);
		player.getSkillInventory().saveToDatabase();
		// 技能阶段处理
		// int skillStage =
		// player.getBasePlayer().getPlayerInfo().getSkillStage() + 1;
		// boolean isOpen = checkSkillStage(player, skillStage);
		// if (isOpen) {
		// player.getBasePlayer().getPlayerInfo().setSkillStage(skillStage);
		// }
		if (player.getSkillInventory() != null) {
			player.getSkillInventory().updataProperty();
		}
		// 日志
		SkillLogInfo log = new SkillLogInfo();
		log.setPlayerId(player.getPlayerId());
		if (alreadyStudySkill != null)
			log.setPerSkillId(alreadyStudySkill.getSkillId());
		log.setSkillId(skillInfo.getTemplateId());
		log.setSkillLV(skillInfo.getLevel());
		log.setCreateDate(new Date());
		LogManager.addSkillLog(log);
		return true;
	}

	/**
	 * 一键升级英雄主动技能
	 * 
	 * @param player
	 * @return
	 */
	public static boolean OneKeyUpSkill(GamePlayer player, PBMessage packet) {
		boolean run = false;
		int falseCount = 0;
		while (true) {
			Map<Integer, SkillTempateInfo> skillTempList = SkillTempMgr.getSkillTempByType(SkillInventory.initiativeSkillType, 3);// 主动技能配置列表
			Map<String, HeroSkill> skillMap = player.getSkillInventory().getHeroSkill(SkillInventory.initiativeSkillType, 3);// 英雄所有主动技能
			Map<String, HeroSkill> studyMap = new HashMap<>();// 已学习的
			for (Entry<String, HeroSkill> entry : skillMap.entrySet()) {
				HeroSkill temp = entry.getValue();
				studyMap.put(temp.getType() + "_" + temp.getSubType() + "_" + temp.getGrandsonType(), temp);
			}
			Map<String, Integer> map = new HashMap<>();// 类型-等级映射（根据等级排序使用）
			Map<String, Integer> map2 = new HashMap<>();// 类型-技能id映射
			for (Entry<Integer, SkillTempateInfo> entry : skillTempList.entrySet()) {
				SkillTempateInfo temp = entry.getValue();
				int type = temp.getMasterType();
				int subType = temp.getSonType();
				int grandsonType = temp.getGrandsonType();
				String key = type + "_" + subType + "_" + grandsonType;
				if (studyMap.containsKey(key)) {
					map.put(key, studyMap.get(key).getSkillLV());
					map2.put(key, studyMap.get(key).getSkillId());
				} else {
					map.put(key, 0);// 没有学习
				}
			}
			// 技能等级小的排前
			List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return (o1.getValue() - o2.getValue());
				}
			});
			// 学习技能
			int useStone = 0; // 技能升级需要灵石
			int useRepair = 0; // 技能升级需要修为
			int useJade = 0; // 技能升级需要仙玉
			Map<Integer, Integer> useGoods = new HashMap<>();// 消耗物品
			ArrayList<Integer> studySkillIds = new ArrayList<>();

			falseCount = 0;
			for (Entry<String, Integer> entry : infoIds) {
				String key = entry.getKey();
				if (map2.get(key) == null) {
					falseCount++;
					// System.out.println("key: " + key + " map2.get(key): " +
					// map2.get(key));
					continue;
				}
				SkillTempateInfo tempinfo = SkillTempMgr.getSkillTemp(map2.get(key));
				int nextSkillId = tempinfo.getNextTempId();
				if (nextSkillId <= 0) {
					falseCount++;
					continue;
				}
				boolean res = upSkill(player, nextSkillId, useStone, useRepair, useJade, useGoods);
				if (res) {
					run = true;
				} else {
					falseCount++;
				}
				// System.out.println("skillId: " + map2.get(key) + "
				// nextSkillId:" + nextSkillId + " res:" + res);
				studySkillIds.add(nextSkillId);
			}
			if (infoIds.size() == falseCount) {
				break;
			}
		}

		// 同步
		player.getArmyInventory().updateHeroInfo();
		// 写协议
		OneKeyUpSkillOkMsg.Builder msg = OneKeyUpSkillOkMsg.newBuilder();
		// msg.setNeedJade(useJade);
		// msg.setNeedStone(useStone);
		// msg.setNeedRepair(useRepair);
		// msg.addAllSkillId(studySkillIds);
		// UseGoods.Builder useGoodsMsg = UseGoods.newBuilder();
		// for (Entry<Integer, Integer> entry : useGoods.entrySet()) {
		// useGoodsMsg.setUseGoodsId(entry.getKey());
		// useGoodsMsg.setUseGoodsNum(entry.getValue());
		// }
		// msg.setUseGoods(useGoodsMsg);
		if (run == false) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR7, packet.getCode(), "资源不足,升级失败 ");
			return false;
		}
		return run;
	}

	/**
	 * 
	 * @param player
	 * @param skillId
	 *            要升到的等级
	 * @param useStone
	 * @param useRepair
	 * @param useJade
	 * @param useGoods
	 * @return
	 */
	private static boolean upSkill(GamePlayer player, int skillId, int useStone, int useRepair, int useJade, Map<Integer, Integer> useGoods) {
		SkillTempateInfo skillInfo = SkillTempMgr.getSkillTemp(skillId);// 技能配置
		if (skillInfo == null) { // 已经至最高级
			return false;
		}

		int upLv = skillInfo.getLevel();
		int nowSkillLV = getSkillTypeLv(player, skillInfo.getMasterType(), skillInfo.getSonType(), skillInfo.getGrandsonType());
		if (upLv - nowSkillLV != 1) { // 已经学习了
			return false;
		}

		String preTemplateId = skillInfo.getPreTemplateId();// 前置技能
		// 判断前置技能是否满足
		if (preTemplateId != null && !preTemplateId.isEmpty()) {
			String[] preTemplateIds = preTemplateId.split(",");
			for (String id : preTemplateIds) {
				if (Integer.valueOf(id) == 0)
					continue;
				SkillTempateInfo skill = SkillTempMgr.getSkillTemp(Integer.valueOf(id));
				if (skill == null) {
					return false;
				}
				int masterType = skill.getMasterType();// 前置技能父类型
				int sonType = skill.getSonType();// 前置技能子类型
				int grandsonType = skill.getGrandsonType();
				int lv = skill.getLevel();// 前置技能等级(需要的等级)
				int skillLV = getSkillTypeLv(player, masterType, sonType, grandsonType);
				if (skillLV < lv) {
					return false;
				}
			}
		}
		// 计算需要的资源
		HeroSkill alreadyStudySkill = getSkillTypeSkill(player, skillInfo.getMasterType(), skillInfo.getSonType(), skillInfo.getGrandsonType());
		int needStone = 0; // 技能升级需要灵石
		int needRepair = 0; // 技能升级需要修为
		int needJade = 0; // 技能升级需要仙玉
		String needGoods = ""; // 技能升级需要物品 格式 物品id,数量#物品id,数量
		if (alreadyStudySkill != null) {
			SkillTempateInfo temp = SkillTempMgr.getSkillTemp(alreadyStudySkill.getSkillId());// 技能配置
			needStone = temp.getNeedStone(); // 技能升级需要灵石
			needRepair = temp.getNeedRepair(); // 技能升级需要修为
			needJade = temp.getNeedJade(); // 技能升级需要仙玉
			needGoods = temp.getNeedGoods(); // 技能升级需要物品 格式 物品id,数量#物品id,数量
		}
		// 检测灵石
		if (needStone > 0 && player.getBasePlayer().getPlayerInfo().getMoney() < needStone) {
			return false;
		}
		// 检测仙玉
		if (needJade > 0 && player.getBasePlayer().getPlayerInfo().getCash() < needJade) {
			return false;
		}
		// 检测修为
		if (player.getBasePlayer().getPlayerInfo().getRepair() < needRepair) {
			return false;
		}
		// 检测物品
		String[] needGoods_1 = needGoods.split("#");
		for (String string : needGoods_1) {
			String[] goods = string.split(",");
			if (goods.length == 0)
				continue;
			if (!goods[0].isEmpty() && player.getBagInventory().getPlayerBagItemCount(Integer.valueOf(goods[0])) < Integer.valueOf(goods[1])) {
				return false;
			}
		}

		// 扣灵石
		if (needStone > 0) {
			player.getBasePlayer().consumeMoney(needStone, ItemRemoveType.UP_SKILL);
			useStone += needStone;
		}
		// 扣仙玉
		if (needJade > 0) {
			player.getBasePlayer().consumeCash(needJade, ItemRemoveType.UP_SKILL);
			useJade += needJade;
		}
		// 扣修为
		if (needRepair > 0) {
			player.getBasePlayer().consumeRepair(needRepair);
			useRepair += needRepair;
		}
		// 扣道具
		for (String string : needGoods_1) {
			String[] goods = string.split(",");
			if (!goods[0].isEmpty()) {
				player.getBagInventory().removeItemFromPlayerBag(Integer.valueOf(goods[0]), Integer.valueOf(goods[1]), ItemRemoveType.USE);
				int count = 0;
				if (useGoods.containsKey(Integer.valueOf(goods[0])))
					count = useGoods.get(Integer.valueOf(goods[0]));
				useGoods.put(Integer.valueOf(goods[0]), count + Integer.valueOf(goods[1]));
			}
		}
		// 升级处理
		HeroSkill heroSkill = new HeroSkill();
		heroSkill.setPlayerId(player.getPlayerId());
		heroSkill.setSkillId(skillId);
		heroSkill.setType(skillInfo.getMasterType());
		heroSkill.setSubType(skillInfo.getSonType());
		heroSkill.setSkillLV(skillInfo.getLevel());
		heroSkill.setGrandsonType(skillInfo.getGrandsonType());
		player.getSkillInventory().addOrUpdate(heroSkill);
		player.getSkillInventory().saveToDatabase();
		// 日志
		SkillLogInfo log = new SkillLogInfo();
		log.setPlayerId(player.getPlayerId());
		if (alreadyStudySkill != null)
			log.setPerSkillId(alreadyStudySkill.getSkillId());
		log.setSkillId(skillInfo.getTemplateId());
		log.setSkillLV(skillInfo.getLevel());
		log.setCreateDate(new Date());
		LogManager.addSkillLog(log);
		return true;
	}

	// public static void main(String args[]) {
	// Map<String, Integer> map = new HashMap<String, Integer>();
	// map.put("d", 2);
	// map.put("c", 1);
	// map.put("b", 1);
	// map.put("a", 3);
	// List<Map.Entry<String, Integer>> infoIds = new
	// ArrayList<Map.Entry<String, Integer>>(map.entrySet());
	// Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
	// public int compare(Map.Entry<String, Integer> o1, Map.Entry<String,
	// Integer> o2) {
	// return (o1.getValue() - o2.getValue());
	// }
	// });
	//
	// for (int i = 0; i < infoIds.size(); i++) {
	// String id = infoIds.get(i).toString();
	// // System.out.println(id);
	// }
	// }

	/**
	 * 升技能阶段
	 * 
	 * @param player
	 * @param stage
	 *            要升的技能阶段id
	 * @return
	 */
	public static boolean UpSkillStage(GamePlayer player, int stage, PBMessage packet) {
		// 技能阶段处理
		int skillStage = player.getBasePlayer().getPlayerInfo().getSkillStage() + 1;
		boolean isOpen = checkSkillStage(player, skillStage);
		// System.out.println("playerId:" +
		// player.getBasePlayer().getPlayerInfo().getPlayerId() + " skillStage:"
		// + skillStage + " res:" + isOpen);
		if (isOpen) {
			player.getBasePlayer().getPlayerInfo().setSkillStage(skillStage);
			if (player.getSkillInventory() != null) {
				player.getSkillInventory().updataProperty();
			}
		} else {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.SKILL_UP_ERROR6, packet.getCode());
			return false;
		}
		return true;
	}

	/**
	 * 获取英雄某种类型达到的等级数
	 * 
	 * @param player
	 * @param type
	 * @param subTpey
	 * @return
	 */
	private static int getSkillTypeLv(GamePlayer player, int type, int subTpey, int grandsonType) {
		Map<String, HeroSkill> skillMap = player.getSkillInventory().getHeroSkill();
		String key = type + "_" + subTpey + "_" + grandsonType;
		if (skillMap.containsKey(key)) {
			HeroSkill skill = skillMap.get(key);
			return skill.getSkillLV();
		}
		return 0;
	}

	/**
	 * 根据类型获取英雄当前学到的技能
	 * 
	 * @param player
	 * @param type
	 * @param subTpey
	 * @return
	 */
	private static HeroSkill getSkillTypeSkill(GamePlayer player, int type, int subTpey, int grandsonType) {
		Map<String, HeroSkill> skillMap = player.getSkillInventory().getHeroSkill();
		String key = type + "_" + subTpey + "_" + grandsonType;
		if (skillMap.containsKey(key)) {
			HeroSkill skill = skillMap.get(key);
			return skill;
		}
		return null;
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
		if (skillStage == null)
			return false;
		String str = skillStage.getCondition();// 条件
		String[] condition = str.split(",");
		for (String string : condition) {
			if (string.isEmpty())
				continue;
			int skillId = Integer.valueOf(string);// 需要的技能
			SkillTempateInfo skill = SkillTempMgr.getSkillTemp(skillId);
			if (skill == null)
				return false;
			int masterType = skill.getMasterType();// 前置技能父类型
			int sonType = skill.getSonType();// 前置技能子类型
			int grandsonType = skill.getGrandsonType();
			int needLv = skill.getLevel();// 前置技能等级(需要的等级)
			int skillLV = getSkillTypeLv(player, masterType, sonType, grandsonType);
			if (skillLV < needLv)
				return false;
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

		// SkillProperty.Builder critAddtion = SkillProperty.newBuilder();
		// critAddtion.setType(EnumAttr.CRIT_ADDTION.getValue());
		// critAddtion.setPro(skillBasePro.getCritAddtion());
		// msg.addInfo(critAddtion);

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

}
