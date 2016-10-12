package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.avatar.AvatarCorrespondTemplateInfo;
import com.chuangyou.xianni.sql.dao.AvatarCorrespondTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class AvatarCorrespondTemplateInfoDaoImpl extends BaseDao implements AvatarCorrespondTemplateInfoDao {

	@Override
	public List<AvatarCorrespondTemplateInfo> getAll() {
		String sql = "SELECT * FROM tb_z_avatar_correspond_template;";
		return read(sql, null);
	}

	private List<AvatarCorrespondTemplateInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<AvatarCorrespondTemplateInfo> infos = null;
		AvatarCorrespondTemplateInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new AvatarCorrespondTemplateInfo();
					info.setAvatarTempId(rs.getInt("id") / 1000);
					info.setLevel(rs.getInt("lv"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setNeedNum(rs.getInt("needNum"));
					info.setMaxPro(rs.getInt("proMax"));
					info.setRate(rs.getInt("rate"));
					info.setFitTime(rs.getInt("time"));
					info.setEnergyLimit(rs.getInt("air"));
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
