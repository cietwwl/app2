package com.chuangyou.xianni.warfield.helper.selectors;

import com.chuangyou.xianni.constant.RoleConstants;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.warfield.helper.Selector;

public class MonsterSelectorHelper extends Selector {

	public MonsterSelectorHelper(Living master) {
		super(master);
	}

	@Override
	public boolean selectorType(int type) {
		if (type == RoleConstants.RoleType.monster)
			return true;
		return false;
	}

	@Override
	public boolean selectorProtection(boolean protection) {
		return protection != true;
	}

	@Override
	public boolean selectorid(long id) {
		return true;
	}

}
