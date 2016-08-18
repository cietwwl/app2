package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.sql.dao.SkillActionTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class SkillActionTemplateInfoDaoImpl extends BaseDao implements SkillActionTemplateInfoDao {

	@Override
	public List<SkillActionTemplateInfo> load() {
		String sql = "select * from tb_z_skill_actioninfo;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<SkillActionTemplateInfo> list = new ArrayList<SkillActionTemplateInfo>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SkillActionTemplateInfo skillTemp = new SkillActionTemplateInfo();
					skillTemp.setTemplateId(rs.getInt("templateId"));
					skillTemp.setMasterType(rs.getInt("masterType"));
					skillTemp.setMove(rs.getInt("move"));
					skillTemp.setSonType(rs.getInt("masterType"));
					skillTemp.setCombo(rs.getInt("combo"));
					skillTemp.setMaxCombo(rs.getInt("maxCombo"));
					skillTemp.setAnimation(rs.getInt("animation"));
					//skillTemp
					skillTemp.setAttackTimes(rs.getInt("attackTimes"));
					skillTemp.setCostType(rs.getInt("costType"));
					skillTemp.setAttackType(rs.getInt("attackType"));// 气血攻击
					skillTemp.setParamValue1(rs.getInt("paramValue1"));
					skillTemp.setParamParent1(rs.getInt("paramParent1"));
					skillTemp.setParamValue2(rs.getInt("paramValue2"));
					skillTemp.setParamParent2(rs.getInt("paramParent2"));
					skillTemp.setParamValue3(rs.getInt("paramValue3"));
					skillTemp.setParamParent3(rs.getInt("paramParent3"));
					skillTemp.setBufferIds(rs.getString("bufferIds"));
					skillTemp.setSnareIds(rs.getString("snareIds"));
					skillTemp.setRandom(rs.getInt("random"));
					skillTemp.setIsCrit(rs.getInt("isCrit"));
					skillTemp.setCooldown(rs.getInt("cooldown"));// 10秒CD时间
					list.add(skillTemp);
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
