package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.avatar.AvatarStarTemplate;
import com.chuangyou.xianni.sql.dao.AvatarStarTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class AvatarStarTemplateDaoImpl extends BaseDao implements AvatarStarTemplateDao {

	@Override
	public List<AvatarStarTemplate> getAll() {
		String sql = "SELECT * FROM tb_z_avatar_star_template;";
		return read(sql, null);
	}

	private List<AvatarStarTemplate> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<AvatarStarTemplate> infos = null;
		AvatarStarTemplate info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new AvatarStarTemplate();
					info.setAvatarTempId(rs.getInt("id") / 1000);
					info.setLevel(rs.getInt("id") % 1000);
					info.setNeedItem(rs.getInt("needItem"));
					info.setNeedNum(rs.getInt("needNum"));
					info.setSkillId(rs.getInt("skillId"));
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
