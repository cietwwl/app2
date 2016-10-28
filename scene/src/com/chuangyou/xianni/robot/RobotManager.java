package com.chuangyou.xianni.robot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chuangyou.xianni.constant.RobotConstant;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.sql.dao.DBManager;

public class RobotManager {
	public static Map<Integer, RobotTemplate> robots = new HashMap<>();

	public static boolean init() {
		return reload();
	}

	public static boolean reload() {
		List<RobotTemplate> robosList = DBManager.getRobotTemplateDao().getAll();
		for (RobotTemplate robot : robosList) {
			if (robot.getType() == RobotConstant.Type.PLOT_ROBOT) {
				robots.put(robot.getId(), robot);
			}
		}
		return true;
	}

	public static RobotTemplate getRobotTemplate(int robotId) {
		return robots.get(robotId);
	}

}
