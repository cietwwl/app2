package com.chuangyou.xianni.warfield.helper.selectors;

import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.warfield.field.Field;

public class PlayerCallExcludeMasterSelector extends PlayerSelectorHelper {

	public PlayerCallExcludeMasterSelector(Living master) {
		super(master);
	}

	@Override
	public boolean selectorid(long id) {
		// TODO Auto-generated method stub
		if (master != null) {
			Field f = master.getField();
			if (f != null) {
				Living l = f.getLiving(id);
				// if(l != null && l.)
				// 排除宠物
				if (l.getArmyId() == master.getArmyId())
					return false;
			}
		}
		return true;
	}
}
