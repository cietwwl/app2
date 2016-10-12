package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.avatar.AvatarTemplateInfo;
import com.chuangyou.xianni.sql.dao.AvatarTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class AvatarTemplateInfoDaoImpl extends BaseDao implements AvatarTemplateInfoDao {

	@Override
	public List<AvatarTemplateInfo> getAll() {
		String sql = "SELECT * FROM tb_z_avatar_template_info;";
		return read(sql, null);
	}

	private List<AvatarTemplateInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<AvatarTemplateInfo> infos = null;
		AvatarTemplateInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new AvatarTemplateInfo();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setSkillId(rs.getInt("skillId"));
					info.setCommonSkillId1(rs.getInt("commonSkillId1"));
					info.setCommonSkillId2(rs.getInt("commonSkillId2"));
					info.setCommonSkillId3(rs.getInt("commonSkillId3"));
					info.setCommonSkillId4(rs.getInt("commonSkillId4"));
					info.setAddtionPercent(rs.getInt("addtionPercent"));
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
