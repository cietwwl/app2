package com.chuangyou.xianni.warfield.helper.selectors;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.spawn.AiConfig;
import com.chuangyou.xianni.role.helper.RoleConstants;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.role.template.AiConfigTemplateMgr;
import com.chuangyou.xianni.warfield.helper.Selector;

public class MonsterSelectPlayerSelectorHelper extends Selector {

	public MonsterSelectPlayerSelectorHelper(Living master) {
		super(master);
	}

	@Override
	public boolean selectorType(int type) {
		return type == RoleConstants.RoleType.player;
	}

	@Override
	public boolean selectorProtection(boolean protection) {
		return protection == false;
		// if (protection == true) {
		// return false;
		// }
		// return true;
	}

	@Override
	public boolean selectorid(long id) {
		return true;
	}

	@Override
	public boolean canSee(Living other) {
		int aiId = ((Monster) this.master).getMonsterInfo().getAiId();
		AiConfig aiConfig = AiConfigTemplateMgr.get(aiId);
		if (aiConfig == null || aiConfig.getAlertRange() <= 0)
			return false;
		Vector3 masterPostion = master.getPostion();
		Vector3 otherPostion = other.getPostion();
		float distance = Vector3.distance(masterPostion, otherPostion);
		if (distance <= aiConfig.getAlertRange())
			return true;
		return false;
	}

}
