package com.chuangyou.xianni.state.condition;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.task.constant.ConditionType;

/**
 * 工厂
 * @author laofan
 *
 */
public class StateConditionFactory {
	
	public static BaseStateCondition createCondition(StateConditionConfig config,StateConditionInfo info,GamePlayer player){
		switch(config.getTargetType()){
			case ConditionType.T_SYSTEM:
				return new TSystemStateCondition(info, config, player);
			case ConditionType.PLAYER_LV:
				return new PlayerLvStateCondition(info, config, player);
			case ConditionType.SOUL_LV:
				return new SoulLvStateCondition(info, config, player);
			case ConditionType.EQUIP:
				return new EquipStateCondition(info, config, player);
			case ConditionType.SOUL_STAR:
				return new SoulStarStateCondition(info, config, player);
			case ConditionType.SOUL_PROFICIENCY:
				return new SoulProStateCondition(info, config, player);
			case ConditionType.SKILL_STAGE:
				return new SkillStageStateCondition(info, config, player);
			case ConditionType.MOUNT:
				return new MountStateCondition(info, config, player);
			case ConditionType.ARTIFACTDATA:
				return new ArtifactStateCondition(info, config, player);
			case ConditionType.MAGICWP_ACTIVE:
				return new MagicActiveStateCondition(info, config, player);
			case ConditionType.MAGICWP:
				return new MagicWpStateCondition(info, config, player);
			case ConditionType.PET_ACTIVE:
				return new PetActiveStateCondition(info, config, player);
			case ConditionType.PET:
				return new PetStateCondition(info, config, player);
				
				
						
		}
		
		return null;
	}
}
