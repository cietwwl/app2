package com.chuangyou.xianni.role.objects;

import java.util.List;

import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.xianni.battle.action.RobotPollingAction;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.campaign.ArenaBattleCampaign;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.SimplePlayerInfo;
import com.chuangyou.xianni.world.WorldMgr;

public class Robot extends ActiveLiving {

	private ArenaBattleCampaign campaign;

	public Robot(long id) {
		super(id);
		setType(RoleType.robot);
	}

	public Robot(ArenaBattleCampaign campaign) {
		super(IDMakerHelper.nextID());
		this.campaign = campaign;
		setType(RoleType.robot);
		RobotPollingAction robotAction = new RobotPollingAction(this);
		this.enDelayQueue(robotAction);
	}

	public boolean onDie(Living killer) {
		if (super.onDie(killer) && type == RoleType.robot && campaign != null) {
			campaign.win();
		}
		return true;
	}

	/** 浸染 */
	public void instill(RobotInfoMsg data) {
		simpleInfo = new SimplePlayerInfo();
		simpleInfo.readProto(data.getSimpInfo());
		setSkin(simpleInfo.getSkinId());
		// 速度
		ArmyProxy army = WorldMgr.getArmy(armyId);
		if (army != null) {
			setProperty(EnumAttr.SPEED, army.getPlayer().getSpeed());
		} else {
			setProperty(EnumAttr.SPEED, 600);
		}
		setArmyId(simpleInfo.getPlayerId());
		// 注入属性
		readProperty(data.getPropertis());

		if (getCurBlood() <= 0) {
			setSoulState(true);
		}

		List<Integer> skillIds = data.getBattleSkillsList();
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
