package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.sql.dao.SkillBufferTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class SkillBufferTemplateInfoDaoImpl extends BaseDao implements SkillBufferTemplateInfoDao {

	@Override
	public List<SkillBufferTemplateInfo> load() {
		String sql = "select * from tb_z_skill_buffer;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<SkillBufferTemplateInfo> list = new ArrayList<SkillBufferTemplateInfo>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SkillBufferTemplateInfo skillBufferTmep = new SkillBufferTemplateInfo();
					skillBufferTmep.setTemplateId(rs.getInt("templateId"));
					skillBufferTmep.setBufferName(rs.getString("bufferName"));
					skillBufferTmep.setIcon(rs.getInt("icon"));
					skillBufferTmep.setIsHelpful(rs.getInt("isHelpful"));
					skillBufferTmep.setType(rs.getInt("type"));
					skillBufferTmep.setFromType(rs.getInt("fromType"));
					skillBufferTmep.setTargetType(rs.getInt("targetType"));
					skillBufferTmep.setExeWay(rs.getInt("exeWay"));
					skillBufferTmep.setDurableType(rs.getInt("durableType"));// 耐久类型
					skillBufferTmep.setExeTime(rs.getInt("exeTime"));// 10秒
					skillBufferTmep.setExeCount(rs.getInt("exeCount"));
					skillBufferTmep.setCooldown(rs.getInt("cooldown"));// 100被伤害者
																		// 100秒内免疫该类型BUUFF
					skillBufferTmep.setOverlayType(rs.getInt("overlayType"));
					skillBufferTmep.setOverlayWay(rs.getInt("overlayWay"));
					skillBufferTmep.setLevel(rs.getInt("level"));
					skillBufferTmep.setIsTips(rs.getInt("isTips"));
					skillBufferTmep.setIsSave(rs.getInt("isSave"));
					skillBufferTmep.setValueType(rs.getInt("valueType"));
					skillBufferTmep.setValue(rs.getInt("value"));
					skillBufferTmep.setValuePercent(rs.getInt("valuePercent"));

					skillBufferTmep.setValueType1(rs.getInt("valueType1"));
					skillBufferTmep.setValue1(rs.getInt("value1"));
					skillBufferTmep.setValuePercent1(rs.getInt("valuePercent1"));

					skillBufferTmep.setStatus(rs.getInt("status"));
					skillBufferTmep.setDelay(rs.getInt("delay"));
					skillBufferTmep.setCostCount(rs.getInt("costCount"));

					skillBufferTmep.setParam1(rs.getInt("param1"));
					skillBufferTmep.setParam2(rs.getInt("param2"));
					skillBufferTmep.setParam3(rs.getInt("param3"));
					skillBufferTmep.setParam4(rs.getInt("param4"));
					list.add(skillBufferTmep);
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
