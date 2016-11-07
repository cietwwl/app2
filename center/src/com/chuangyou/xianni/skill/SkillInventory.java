package com.chuangyou.xianni.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.chuangyou.xianni.constant.SkillConstant.SkillMainType;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.skill.SkillStage;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SkillInventory extends AbstractEvent implements IInventory {
	private GamePlayer				player;

	private Map<Integer, HeroSkill>	heroSkill	= new ConcurrentHashMap<>();

	public SkillInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		heroSkill.putAll(DBManager.getHeroSkillDao().getAll(player.getPlayerId()));
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
			for (HeroSkill skill : getToalSkills()) {
				if (skill.getOp() == Option.Insert) {
					DBManager.getHeroSkillDao().add(skill);
				} else if (skill.getOp() == Option.Update) {
					DBManager.getHeroSkillDao().update(skill);
				}
			}
		}
		return true;
	}

	public List<HeroSkill> getToalSkills() {
		return new ArrayList<>(heroSkill.values());
	}

	public Map<Integer, HeroSkill> getHeroSkill() {
		return heroSkill;
	}

	public HeroSkill getHeroSkill(int skillTempId) {
		return heroSkill.get(skillTempId);
	}

	/**
	 * 根据类型获取英雄技能
	 * 
	 * @param type
	 * @return
	 */
	public List<HeroSkill> getHeroSkillByType(int type) {
		List<HeroSkill> result = new ArrayList<>();
		for (HeroSkill heroSkill : getToalSkills()) {
			try {
				SkillTempateInfo skillInfo = SkillTempMgr.getSkillTemp(heroSkill.getSkillId());// 技能配置
				if (skillInfo.getMasterType() == type) {
					result.add(heroSkill);
				}
			} catch (Exception e) {
				System.out.println("======");
			}
		}
		return result;
	}

	/**
	 * 更新技能
	 * 
	 * @param heroSkill
	 * @return
	 */
	public boolean update(HeroSkill heroSkill) {
		if (heroSkill.getPlayerId() != player.getPlayerId()) {
			return false;
		}
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
		this.heroSkill.put(skill.getSkillId(), skill);
		return true;
	}

	/**
	 * 获取技能总属性(包括技能阶段的属性)
	 * 
	 * @param player
	 * @return
	 */
	public void getTotalPro(BaseProperty bagData, BaseProperty bagPer) {
		List<HeroSkill> skills = getHeroSkillByType(SkillMainType.PASSIVE);
		for (HeroSkill skill : skills) {
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
