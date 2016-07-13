/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD SQ team.
 */
package com.chuangyou.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * </pre>
 */
public class LordsHelper {
	public static List<String> createLordsInfo() {
		int job = 1;
		int initUserId = 348880;
		ThreadSafeRandom random = new ThreadSafeRandom();
		StringBuilder sb = new StringBuilder();
		List<String> sqlList = new ArrayList<String>();
		for (int i = 1; i <= 100; i++) {
			initUserId += i;
			int fightPower = random.next(20000, 100000);
			int readyScore = 0;// random.next(2000, 4000);
			String sql = "INSERT INTO `t_u_lordscross` VALUES ('" + initUserId + "', '第七大道', '至强的战士" + initUserId + "', '第七大道_" + initUserId + "', '" + job + "', '" + job
					+ "', '72', '/fashion_weapon_qixi01', '/wing_angle_big01', '/fashion_cloth_swimsuit03', '/fashion_hat_swimsuit01', '', '/fashion_weapon_qixi01', '0', '" + fightPower + "', '"
					+ readyScore + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00', '1', '2012-12-12 12:30:00', '', '', '1',0);\r\n";
			sqlList.add(sql);
		}

		job = 2;
		for (int i = 1; i <= 100; i++) {
			initUserId += i;
			int fightPower = random.next(20000, 100000);
			int readyScore = 0;
			String sql = "INSERT INTO `t_u_lordscross` VALUES ('" + initUserId + "', '第七大道', '至强的弓手" + initUserId + "', '第七大道_" + initUserId + "', '" + job + "', '" + job
					+ "', '72', '/fashion_weapon_qixi01', '/wing_angle_big01', '/fashion_cloth_swimsuit03', '/fashion_hat_swimsuit01', '', '/fashion_weapon_qixi01', '0', '" + fightPower + "', '"
					+ readyScore + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00', '1', '2012-12-12 12:30:00', '', '', '1',0);\r\n";
			sqlList.add(sql);
		}
		job = 3;
		for (int i = 1; i <= 100; i++) {
			initUserId += i;
			int fightPower = random.next(20000, 100000);
			int readyScore = 0;// random.next(2000, 4000);
			String sql = "INSERT INTO `t_u_lordscross` VALUES ('" + initUserId + "', '第七大道', '至强的法师" + initUserId + "', '第七大道_" + initUserId + "', '" + job + "', '" + job
					+ "', '72', '/fashion_weapon_qixi01', '/wing_angle_big01', '/fashion_cloth_swimsuit03', '/fashion_hat_swimsuit01', '', '/fashion_weapon_qixi01', '0', '" + fightPower + "', '"
					+ readyScore + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2000-01-01 00:00:00', '2000-01-01 00:00:00', '1', '2012-12-12 12:30:00', '', '', '1',0);\r\n";
			sqlList.add(sql);
		}
		return sqlList;
	}
}
