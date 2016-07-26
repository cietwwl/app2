package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.sql.dao.FieldInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class FieldInfoDaoImpl extends BaseDao implements FieldInfoDao {

	@Override
	public List<FieldInfo> getAll() {
		String sql = "SELECT * FROM tb_z_map_info";
		List<FieldInfo> result = read(sql, null);
		return result;
	}

	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private List<FieldInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<FieldInfo> infos = null;
		FieldInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<FieldInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new FieldInfo();
					info.setMapKey(rs.getShort("mapKey"));
					info.setName(rs.getString("name"));
					info.setType(rs.getByte("type"));
					info.setDesc(rs.getString("_desc"));
					info.setCampaignId(rs.getInt("campaignId"));
					info.setCampaignIndex(rs.getInt("campaignIndex"));
					info.setPosition(new Vector3(rs.getInt("x") / Vector3.Accuracy, rs.getInt("y") / Vector3.Accuracy, rs.getInt("z") / Vector3.Accuracy));
					info.setInitScriptId(rs.getInt("initScriptId"));
					info.setResName(rs.getString("resName"));
					info.setBattle(rs.getBoolean("isBattle"));
					info.setStartBattleTime(rs.getString("startTime"));
					info.setEndBattleTime(rs.getString("endTime"));
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
