package com.chuangyou.xianni.battle;

import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.battle.calc.CalcFactory;
import com.chuangyou.xianni.battle.calc.SkillCalc;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Robot;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.FieldConstants.FieldAttackRule;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

/**
 * 战斗指令工厂类
 * 
 */
public class OrderFactory {

	/**
	 * 创建战斗指令
	 * 
	 * @param source
	 * @param skillInfo
	 * @param targets
	 * @param aiInfo
	 * @return
	 */
	public static AttackOrder createAttackOrder(Living source, Skill skill, List<Living> targets, long attackId) {
		AttackOrder attackOrder = null;
		SkillCalc skillCalc = CalcFactory.createSkillCalc(skill.getTemplateInfo().getTemplateId());
		// skill.getTemplateInfo().getAttackType(),
		// source.getJob());
		if (targets != null) {
			attackOrder = new AttackOrder(source, skill, targets, attackId);
		} else {
			// TODO服务器自动寻找目标
		}
		attackOrder.setSkillCalc(skillCalc);
		return attackOrder;
	}

	public static boolean attackCheck(Field field, Player player, Living living) {
		try {
			if (player.getArmyId() != 0 && player.getArmyId() == living.getArmyId()) {
				return false;
			}
			// 无部队目标，锁定为可攻击
			if (living.getArmyId() == 0) {
				return true;
			}
			int fieldAttackRule = field.getAttackRule(player, living);

			if (fieldAttackRule == FieldAttackRule.ATTACK) {
				return true;
			}
			if (fieldAttackRule == FieldAttackRule.UNATTACK) {
				return false;
			}
			Player target = null;
			if (living instanceof Player) {
				target = (Player) living;
			} else {
				ArmyProxy army = WorldMgr.getArmy(living.getArmyId());
				if (army != null) {
					target = army.getPlayer();
				}
			}
			if (target == null) {
				return true;
			}

			String startTime = field.getFieldInfo().getStartBattleTime();
			String endTime = field.getFieldInfo().getEndBattleTime();
			if (fieldAttackRule == FieldAttackRule.USEPLAYERMODE) {// pk 地图才能攻击
				int openLv = SystemConfigTemplateMgr.getIntValue("pk.openLv");
				if (target.getSimpleInfo().getLevel() < openLv) {
					return false;
				}
				if (player.getArmyId() == target.getArmyId()) {
					return false;
				}
				if (player.getTeamId() != 0 && player.getTeamId() == target.getTeamId()) {// 队友
					return false;
				}
				if (player.getBattleMode() == BattleModeCode.sectsBattleMode) {// 帮派模式同帮派的不能对砍
					if (player.getSimpleInfo() != null || target.getSimpleInfo() != null) {
						if (player.getSimpleInfo().getGuildId() != 0 && player.getSimpleInfo().getGuildId() == target.getSimpleInfo().getGuildId()) {
							return false;
						}
					}
				}

				if (startTime != null && endTime != null && TimeUtil.checkPeriod(startTime, endTime)) {// 受保护时间
					if (target.getColour(target.getPkVal()) == BattleModeCode.white) {// 受地图保护
						return false;
					}
				}
				if (player.getBattleMode() == BattleModeCode.peaceBattleMode) {
					if (target.getBattleMode() == BattleModeCode.peaceBattleMode) {
						return false;
					}
					if (target.getColour(target.getPkVal()) == BattleModeCode.red) {
						return true;
					}
					if (!target.isFlashName()) {// 没有闪不能攻击
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			Log.error("--------attackCheck----------", e);
		}
		return false;
	}
	
	public static boolean attackCheck(Field field, Robot robot, Living living) {
		try {
			if (robot.getArmyId() != 0 && robot.getArmyId() == living.getArmyId()) {
				return false;
			}
			// 无部队目标，锁定为可攻击
			if (living.getArmyId() == 0) {
				return true;
			}
			int fieldAttackRule = field.getRobotAttackRule(robot, living);

			if (fieldAttackRule == FieldAttackRule.ATTACK) {
				return true;
			}
			if (fieldAttackRule == FieldAttackRule.UNATTACK) {
				return false;
			}
			Player target = null;
			if (living instanceof Player) {
				target = (Player) living;
			} else {
				ArmyProxy army = WorldMgr.getArmy(living.getArmyId());
				if (army != null) {
					target = army.getPlayer();
				}
			}
			if (target == null) {
				return true;
			}

			String startTime = field.getFieldInfo().getStartBattleTime();
			String endTime = field.getFieldInfo().getEndBattleTime();
			if (fieldAttackRule == FieldAttackRule.USEPLAYERMODE) {// pk 地图才能攻击
				int openLv = SystemConfigTemplateMgr.getIntValue("pk.openLv");
				if (target.getSimpleInfo().getLevel() < openLv) {
					return false;
				}
				if (robot.getArmyId() == target.getArmyId()) {
					return false;
				}
				if (robot.getTeamId() != 0 && robot.getTeamId() == target.getTeamId()) {// 队友
					return false;
				}
				if (robot.getBattleMode() == BattleModeCode.sectsBattleMode) {// 帮派模式同帮派的不能对砍
					if (robot.getSimpleInfo() != null || target.getSimpleInfo() != null) {
						if (robot.getSimpleInfo().getGuildId() != 0 && robot.getSimpleInfo().getGuildId() == target.getSimpleInfo().getGuildId()) {
							return false;
						}
					}
				}

				if (startTime != null && endTime != null && TimeUtil.checkPeriod(startTime, endTime)) {// 受保护时间
					if (target.getColour(target.getPkVal()) == BattleModeCode.white) {// 受地图保护
						return false;
					}
				}
				if (robot.getBattleMode() == BattleModeCode.peaceBattleMode) {
					if (target.getBattleMode() == BattleModeCode.peaceBattleMode) {
						return false;
					}
					if (target.getColour(target.getPkVal()) == BattleModeCode.red) {
						return true;
					}
					if (!target.isFlashName()) {// 没有闪不能攻击
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			Log.error("--------attackCheck----------", e);
		}
		return false;
	}
}
