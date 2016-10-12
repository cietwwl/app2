package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.avatar.AvatarUpGradeTemplate;
import com.chuangyou.xianni.sql.dao.AvatarUpGradeTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class AvatarUpGradeTemplateDaoImpl extends BaseDao implements AvatarUpGradeTemplateDao {

	@Override
	public List<AvatarUpGradeTemplate> getAll() {
		String sql = "SELECT * FROM tb_z_avatar_upgrade_template";
		return read(sql, null);
	}

	private List<AvatarUpGradeTemplate> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<AvatarUpGradeTemplate> infos = null;
		AvatarUpGradeTemplate info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new AvatarUpGradeTemplate();
					info.setAvatarTempId(rs.getInt("id"));
					info.setLevel(rs.getInt("lv"));
					info.setBlood(rs.getInt("att1") % 1000000);
					info.setSoul(rs.getInt("att2") % 1000000);
					info.setAttack(rs.getInt("att3") % 1000000);
					info.setDefence(rs.getInt("att4") % 1000000);
					info.setAccurate(rs.getInt("att5") % 1000000);
					info.setDodge(rs.getInt("att6") % 1000000);
					info.setCrit(rs.getInt("att7") % 1000000);
					info.setCritDefence(rs.getInt("att8") % 1000000);
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

}
