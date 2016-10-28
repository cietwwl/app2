package com.chuangyou.xianni.role.objects;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.SimplePlayerInfo;

public class PlotRobot extends Robot {

	public PlotRobot(long id) {
		super(id);
		setType(RoleType.plot);
	}

	public void instill(ArmyProxy army, RobotTemplate template) {
		setArmyId(army.getPlayerId());
		setSkin(template.getId());
		simpleInfo = new SimplePlayerInfo();
		simpleInfo.setPlayerId(army.getPlayerId());
		simpleInfo.setNickName(template.getNickName());
		simpleInfo.setLevel(template.getLevel());
		simpleInfo.setSkinId(template.getId());
		simpleInfo.setFashionId(template.getFashionId());
		simpleInfo.setWeaponId(template.getWeaponId());
		simpleInfo.setMagicWeaponId(template.getMagicWeaponId());
		simpleInfo.setWingId(template.getWingId());
		simpleInfo.setGuildId(template.getGuildId());
		simpleInfo.setGuildJob(template.getGuildJob());

		setProperty(EnumAttr.SPEED, army.getPlayer().getSpeed());
		setProperty(EnumAttr.BLOOD, template.getHp());
		setProperty(EnumAttr.CUR_BLOOD, template.getHp());
		setProperty(EnumAttr.SOUL, template.getSoulHpValue());
		setProperty(EnumAttr.CUR_SOUL, template.getSoulHpValue());
		setProperty(EnumAttr.ATTACK, template.getHurtValue());
		setProperty(EnumAttr.DEFENCE, template.getArmorValue());
		setProperty(EnumAttr.SOUL_ATTACK, template.getSoulHurtValue());
		setProperty(EnumAttr.SOUL_DEFENCE, template.getSoulArmorValue());
		setProperty(EnumAttr.ACCURATE, template.getHitRateValue());
		setProperty(EnumAttr.DODGE, template.getDodgeValue());
		setProperty(EnumAttr.CRIT, template.getCritValue());
		setProperty(EnumAttr.CRIT_DEFENCE, template.getToughnessValue());

		List<Integer> skillIds = new ArrayList<>();
		if (!StringUtils.isNullOrEmpty(template.getSkillIds())) {
			String[] strSkillIds = template.getSkillIds().split(",");
			for (String strId : strSkillIds) {
				if (strId == null || strId.equals("0")) {
					continue;
				}
				skillIds.add(Integer.valueOf(strId));
			}
		}

		if (skillIds != null) {
			for (Integer skillId : skillIds) {

				SkillTempateInfo skillTempateInfo = BattleTempMgr.getBSkillInfo(Integer.valueOf(skillId));
				if (skillTempateInfo == null) {
					continue;
				}
				if (BattleTempMgr.getActionInfo(skillTempateInfo.getActionId()) == null) {
					continue;
				}
				Skill skill = new Skill(BattleTempMgr.getActionInfo(skillTempateInfo.getActionId()));
				skill.setSkillTempateInfo(skillTempateInfo);
				addSkill(skill);
			}
		}
	}
}
