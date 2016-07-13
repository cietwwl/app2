package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.skill.SkillStage;
import com.chuangyou.xianni.sql.dao.SkillStageDao;
import com.chuangyou.xianni.sql.db.BaseDao;

/**
 * 技能阶段
 * 
 * @author Administrator
 */
public class SkillStageDaoImpl extends BaseDao implements SkillStageDao {

	@Override
	public List<SkillStage> load() {
		String sql = "select * from tb_z_skill_stage;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<SkillStage> list = new ArrayList<SkillStage>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SkillStage skillStage = new SkillStage();
					skillStage.setGradeLevel(rs.getInt("gradeLevel"));
					skillStage.setStageName(rs.getString("stageName"));
//					skillStage.setLevelName(rs.getString("levelName"));
					skillStage.setCondition(rs.getString("condition"));
					// skillStage.setAddTemplateId(rs.getInt("addTemplateId"));
					// skillStage.setLv(rs.getInt("lv"));
					skillStage.setSoul(rs.getInt("soul"));
					skillStage.setBlood(rs.getInt("blood"));
					skillStage.setAttack(rs.getInt("attack"));
					skillStage.setDefence(rs.getInt("defence"));
					skillStage.setSoulAttack(rs.getInt("soulAttack"));
					skillStage.setSoulDefence(rs.getInt("soulDefence"));
					skillStage.setAccurate(rs.getInt("accurate"));
					skillStage.setDodge(rs.getInt("dodge"));
					skillStage.setCrit(rs.getInt("crit"));
					skillStage.setCritDefence(rs.getInt("critDefence"));
					list.add(skillStage);
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
