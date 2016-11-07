package com.chuangyou.xianni.state.condition;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.constant.ConditionType;
import com.chuangyou.xianni.state.condition.magicwp.MagicStateCondition;
import com.chuangyou.xianni.state.condition.pet.PetStateCondition;
import com.chuangyou.xianni.state.condition.soul.SoulStateCondition;

/**
 * 工厂
 * @author laofan
 *
 */
public class StateConditionFactory {
	
	public static BaseStateCondition createCondition(StateConditionConfig config,StateConditionInfo info,GamePlayer player){
		switch(config.getTargetType()){
			case ConditionType.KILL_MONST:
				return new KillMonsterStateCondition(info, config, player);
			case ConditionType.PASS_FB:
				return new PassFbStateCondition(info, config, player);
			case ConditionType.TRIGGER:
				return new TriggerStateCondition(info, config, player);
			case ConditionType.T_SYSTEM:
				return new TSystemStateCondition(info, config, player);
			case ConditionType.PLAYER_LV:
				return new PlayerLvStateCondition(info, config, player);
			case ConditionType.PLAYER_FIGHT:
				return new PlayerFightStateCondition(info, config, player);
			case ConditionType.SKILL_STAGE:
				return new SkillStageStateCondition(info, config, player);
			case ConditionType.EQUIP:
				return new EquipStateCondition(info, config, player);
			case ConditionType.SOUL:
				return new SoulStateCondition(info, config, player);
			case ConditionType.MOUNT:
				return new MountStateCondition(info, config, player);
			case ConditionType.ARTIFACTDATA:
				return new ArtifactStateCondition(info, config, player);
			case ConditionType.MAGICWP:
				return new MagicStateCondition(info, config, player);
			case ConditionType.PET:
				return new PetStateCondition(info, config, player);		
		}
		
		return null;
	}
}
