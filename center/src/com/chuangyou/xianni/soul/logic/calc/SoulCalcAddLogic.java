package com.chuangyou.xianni.soul.logic.calc;

import java.util.Date;

public class SoulCalcAddLogic {

	
	/**
	 * 判断时间是否过期
	 * 
	 * @param startTime
	 * @param totalTime
	 * @return
	 */
	public boolean isExpire(Date startTime, int totalTime) {
		if (System.currentTimeMillis() - startTime.getTime() > totalTime) {
			return true;
		}
		return false;
	}
}
