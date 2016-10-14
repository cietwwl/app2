package com.chuangyou.xianni.role.action;

import com.chuangyou.xianni.world.ArmyProxy;

/**
 * duj
 * @author laofan
 *
 */
public class StateRevivalPlayerAction extends RevivalPlayerAction {

	public StateRevivalPlayerAction(ArmyProxy army) {
		super(army);
		this.execTime = System.currentTimeMillis() + 1000;
	}

}
