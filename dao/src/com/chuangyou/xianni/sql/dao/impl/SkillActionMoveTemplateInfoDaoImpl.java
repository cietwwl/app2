package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.skill.SkillActionMoveTempleteInfo;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.sql.dao.SkillActionMoveTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class SkillActionMoveTemplateInfoDaoImpl extends BaseDao implements
		SkillActionMoveTemplateInfoDao {

	@Override
	public Map<Integer, SkillActionMoveTempleteInfo> load() {
		String sql = "select * from tb_z_skill_actioninfo_move;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		Map<Integer, SkillActionMoveTempleteInfo> map = new HashMap<Integer, SkillActionMoveTempleteInfo>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SkillActionMoveTempleteInfo skillTemp = new SkillActionMoveTempleteInfo();
					skillTemp.setId(rs.getInt("id"));
					skillTemp.setMove_hitbackstep(rs.getInt("move_hitbackstep"));
					map.put(skillTemp.getId(), skillTemp);
				}
			} catch (SQLException e) {
				Log.error("执行出错:" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return map;
	}

}
