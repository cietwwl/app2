package com.chuangyou.xianni.cooldown.obj;

import java.util.HashMap;

public interface CoolDownObj {
	/**
	 * 获取CD列表
	 * @return
	 */
	HashMap<String, CoolDown> getCooldowns();
}
