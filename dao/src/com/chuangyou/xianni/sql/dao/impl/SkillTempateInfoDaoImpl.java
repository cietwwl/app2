package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.sql.dao.SkillTempateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;

/**
 * 基础技能
 * 
 * @author Administrator
 */
public class SkillTempateInfoDaoImpl extends BaseDao implements SkillTempateInfoDao {

	@Override
	public List<SkillTempateInfo> load() {
		String sql = "select * from tb_z_skillinfo;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<SkillTempateInfo> list = new ArrayList<SkillTempateInfo>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SkillTempateInfo templateInfo = new SkillTempateInfo();
					templateInfo.setTemplateId(rs.getInt("templateId"));
					templateInfo.setTemplateName(rs.getString("templateName"));
					templateInfo.setIcons(rs.getString("icons"));
					templateInfo.setMasterType(rs.getInt("masterType"));
					templateInfo.setSonType(rs.getInt("sonType"));
					templateInfo.setGrandsonType(rs.getInt("grandsonType"));
					templateInfo.setLevel(rs.getInt("level"));
					templateInfo.setPreTemplateId(rs.getString("preTemplateId"));
					templateInfo.setNextTempId(rs.getInt("nextTemplateId"));
					templateInfo.setNeedGrades(rs.getInt("needGrades"));
					templateInfo.setUseWay(rs.getInt("useWay"));
					templateInfo.setActionId(rs.getInt("actionId"));
					templateInfo.setNeedStone(rs.getInt("needStone"));
					templateInfo.setNeedRepair(rs.getInt("needRepair"));
					templateInfo.setNeedJade(rs.getInt("needJade"));
					templateInfo.setNeedGoods(rs.getString("needGoods"));
					templateInfo.setPropertyIds(rs.getString("propertyIds"));
					templateInfo.setSysBufferIds(rs.getString("sysBufferIds"));
					templateInfo.setFightBufferIds(rs.getString("fightBufferIds"));
					templateInfo.setDescription(rs.getString("description"));
					list.add(templateInfo);
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
