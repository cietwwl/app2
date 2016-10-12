package com.chuangyou.xianni.skill;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.skill.SkillStage;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SkillInventory extends AbstractEvent implements IInventory {
	/** 主动技能类型 */
	public static final int			initiativeSkillType	= 1;
	/** 被动技能类型（培养类 */
	public static final int			passiveSkillType	= 2;
	/** 普攻技能 */
	public static final int			pugongSkillType		= 3;
	private GamePlayer				player;
	private Map<String, HeroSkill>	heroSkill			= null;

	public SkillInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		heroSkill = DBManager.getHeroSkillDao().getAll(player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		heroSkill.clear();
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if (heroSkill != null && heroSkill.size() > 0) {
			for (Entry<String, HeroSkill> iterable : heroSkill.entrySet()) {
				HeroSkill skill = iterable.getValue();
				short option = skill.getOp();
				if (option == Option.Insert) {
					DBManager.getHeroSkillDao().add(skill);
				} else if (option == Option.Update) {
					DBManager.getHeroSkillDao().update(skill);
				}
			}
		}
		return true;
	}

	public Map<String, HeroSkill> getHeroSkill() {
		return heroSkill;
	}

	/**
	 * 根据类型获取英雄技能
	 * 
	 * @param type
	 * @return
	 */
	public Map<String, HeroSkill> getHeroSkill(int type, int type2) {
		Map<String, HeroSkill> heroSkillmap = new HashMap<String, HeroSkill>();
		for (Entry<String, HeroSkill> entry : heroSkill.entrySet()) {
			HeroSkill skill = entry.getValue();
			int skillId = skill.getSkillId();
			try {
				SkillTempateInfo skillInfo = SkillTempMgr.getSkillTemp(skillId);// 技能配置
				if (skillInfo.getMasterType() == type || skillInfo.getMasterType() == type2) {
					heroSkillmap.put(entry.getKey(), entry.getValue());
				}
			} catch (Exception e) {
				System.out.println("======");
			}
		}
		return heroSkillmap;
	}

	/**
	 * 更新技能
	 * 
	 * @param heroSkill
	 * @return
	 */
	public boolean update(HeroSkill heroSkill) {
		if (heroSkill.getPlayerId() != player.getPlayerId())
			return false;
		heroSkill.setOp(Option.Update);
		return true;
	}

	/**
	 * 新增技能
	 * 
	 * @param heroSkill
	 * @return
	 */
	public boolean addOrUpdate(HeroSkill skill) {
		if (skill.getPlayerId() != player.getPlayerId())
			return false;

		String key = skill.getType() + "_" + skill.getSubType() + "_" + skill.getGrandsonType();
		if (heroSkill.containsKey(key)) {
			skill.setOp(Option.Update);
		} else {
			skill.setOp(Option.Insert);
		}
		// boolean isInsert = true;
		// for (Entry<String, HeroSkill> entry : heroSkill.entrySet()) {
		// int type = entry.getValue().getType();
		// int subType = entry.getValue().getSubType();
		// int grandsonType = entry.getValue().getGrandsonType();
		// // SkillTempateInfo skillInfo =
		// SkillTempMgr.getSkillTemp(entry.getValue().getSkillId());// 技能配置
		// if (skill.getType() == type && skill.getSubType() == subType &&
		// skill.getGrandsonType() == grandsonType) {
		// isInsert = false;
		// }
		// }
		// if (isInsert) {
		// skill.setOp(Option.Insert);
		// } else {
		// skill.setOp(Option.Update);
		// }

		this.heroSkill.put(key, skill);
		return true;
	}

	/**
	 * 获取技能总属性(包括技能阶段的属性)
	 * 
	 * @param player
	 * @return
	 */
	public void getTotalPro(BaseProperty bagData, BaseProperty bagPer) {
		Map<String, HeroSkill> skillMap = getHeroSkill(passiveSkillType, 0);
		for (Entry<String, HeroSkill> entry : skillMap.entrySet()) {
			HeroSkill skill = entry.getValue();
			int skillId = skill.getSkillId();
			SkillUtil.getSkillProperty(bagData, bagPer, skillId);
		}
		// 技能阶段
		int skillStage = player.getBasePlayer().getPlayerInfo().getSkillStage();// 当前技能阶段
		SkillStage stage = SkillTempMgr.getSkillStage(skillStage);
		if (stage != null) {
			bagData.addSoul(stage.getSoul());
			bagData.addBlood(stage.getBlood());
			bagData.addAttack(stage.getAttack());
			bagData.addDefence(stage.getDefence());
			bagData.addSoulAttack(stage.getSoulAttack());
			bagData.addSoulDefence(stage.getSoulDefence());
			bagData.addAccurate(stage.getAccurate());
			bagData.addDodge(stage.getDodge());
			bagData.addCrit(stage.getCrit());
			bagData.addCritDefence(stage.getCritDefence());

			// SkillPropertyTemplateInfo template =
			// SkillTempMgr.getSkillProTemp(stage.getAddTemplateId());// 技能配置
			// if (template != null) {
			// proData.addSoul(template.getSoul());
			// proData.addBlood(template.getBlood());
			// proData.addAttack(template.getAttack());
			// proData.addDefence(template.getDefence());
			// proData.addSoulAttack(template.getSoulAttack());
			// proData.addSoulDefence(template.getSoulDefence());
			// proData.addAccurate(template.getAccurate());
			// proData.addDodge(template.getDodge());
			// proData.addCrit(template.getCrit());
			// proData.addCritDefence(template.getCritDefence());
			// }
		}
	}

	public void updataProperty() {
		if (player.getArmyInventory() != null) {
			BaseProperty skillData = new BaseProperty();
			BaseProperty skillPer = new BaseProperty();
			// 加入技能属性
			getTotalPro(skillData, skillPer);
			player.getArmyInventory().getHero().addSkillPro(skillData, skillPer);
			player.getArmyInventory().updateProperty();
		}
	}

}
