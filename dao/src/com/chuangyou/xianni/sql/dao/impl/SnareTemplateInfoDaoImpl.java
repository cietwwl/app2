package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.skill.SnareTemplateInfo;
import com.chuangyou.xianni.sql.dao.SnareTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class SnareTemplateInfoDaoImpl extends BaseDao implements SnareTemplateInfoDao {

	@Override
	public List<SnareTemplateInfo> load() {
		String sql = "select * from tb_z_snare_info;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<SnareTemplateInfo> list = new ArrayList<SnareTemplateInfo>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					SnareTemplateInfo templateInfo = new SnareTemplateInfo();
					templateInfo.setTemplateId(rs.getInt("id"));
					templateInfo.setSkinId(rs.getInt("skinId"));
					templateInfo.setType(rs.getInt("type"));
					templateInfo.setLifetime(rs.getInt("lifetime"));
					templateInfo.setValidCount(rs.getInt("validCount"));
					templateInfo.setExeWay(rs.getInt("exeWay"));
					templateInfo.setCoolDown(rs.getInt("coolDown"));
					templateInfo.setCheckX(rs.getInt("checkX"));
					templateInfo.setCheckZ(rs.getInt("checkZ"));
					templateInfo.setHp(rs.getInt("hp"));
					templateInfo.setTarget(rs.getInt("target"));
					templateInfo.setBornType(rs.getInt("bornType"));
					templateInfo.setBornAngle(rs.getInt("bornAngle"));
					templateInfo.setBornlength(rs.getInt("bornlength"));
					templateInfo.setMoveType(rs.getInt("moveType"));
					templateInfo.setMoveSpeed(rs.getInt("moveSpeed"));
					templateInfo.setDistance(rs.getInt("distance"));
					templateInfo.setLockingType(rs.getInt("lockingType"));
					templateInfo.setStateId(rs.getInt("stateId"));
					templateInfo.setAddBuffers(rs.getString("addBuffers"));
					templateInfo.setSoulPercent(rs.getInt("soulPercent"));
					templateInfo.setSoulValue(rs.getInt("soulValue"));
					templateInfo.setBloodPercent(rs.getInt("bloodPercent"));
					templateInfo.setBloodValue(rs.getInt("bloodValue"));
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
