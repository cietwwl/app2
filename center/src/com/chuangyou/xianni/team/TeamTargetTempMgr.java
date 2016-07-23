package com.chuangyou.xianni.team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.team.TeamTargetTemplate;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.TeamTargetTemplateDao;

public class TeamTargetTempMgr {
	private static Map<Integer, TeamTargetTemplate> teamTargetTemplates = new HashMap<>();

	public static boolean init() {
		TeamTargetTemplateDao dao = DBManager.getTeamTargetTemplateDao();

		List<TeamTargetTemplate> all = dao.getAll();
		if (all.size() == 0) {
			return true;
		}
		for (TeamTargetTemplate tem : all) {
			teamTargetTemplates.put(tem.getId(), tem);
		}
		return true;
	}

	public static TeamTargetTemplate get(int targetId) {
		return teamTargetTemplates.get(targetId);
	}
}
