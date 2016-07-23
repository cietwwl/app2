package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.team.TeamTargetTemplate;
import com.chuangyou.xianni.sql.dao.TeamTargetTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class TeamTargetTemplateDaoImpl extends BaseDao implements TeamTargetTemplateDao {

	@Override
	public List<TeamTargetTemplate> getAll() {
		String sql = "select * from tb_z_team_target;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<TeamTargetTemplate> list = new ArrayList<TeamTargetTemplate>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					TeamTargetTemplate teamTargetTemplate = new TeamTargetTemplate();
					teamTargetTemplate.setId(rs.getInt("id"));
					teamTargetTemplate.setParentId(rs.getInt("parentId"));
					teamTargetTemplate.setName(rs.getString("name"));
					teamTargetTemplate.setLevLimitMin(rs.getInt("levLimitMin"));
					teamTargetTemplate.setLevLimitMax(rs.getInt("levLimitMax"));
					teamTargetTemplate.setTargetType(rs.getInt("targetType"));
					teamTargetTemplate.setTarget(rs.getInt("targetType"));
					teamTargetTemplate.setEntityId(rs.getInt("entityId"));
					teamTargetTemplate.setV3(rs.getString("v3"));
					list.add(teamTargetTemplate);
				}
			} catch (SQLException e) {
				Log.error("执行出错:" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return list;
	}

}
