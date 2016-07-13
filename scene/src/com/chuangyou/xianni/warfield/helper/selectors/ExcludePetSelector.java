package com.chuangyou.xianni.warfield.helper.selectors;

import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.warfield.field.Field;

public class ExcludePetSelector extends AllSelectorHelper {

	public ExcludePetSelector(Living master) {
		super(master);
	}

	@Override
	public boolean selectorid(long id) {
		Field f = master.getField();
		if (f != null) {
			Living l = f.getLiving(id);
			// if(l != null && l.)
			// 排除宠物
			if (l.getArmyId() == master.getArmyId())
				return false;
		}
		return true;
	}
}
