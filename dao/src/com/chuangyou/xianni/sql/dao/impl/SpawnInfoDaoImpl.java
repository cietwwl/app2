package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.sql.dao.SpawnInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class SpawnInfoDaoImpl extends BaseDao implements SpawnInfoDao {

	@Override
	public Map<Integer, SpawnInfo> getAll() {
		String sql = "SELECT * FROM tb_z_map_spawn";
		Map<Integer, SpawnInfo> result = read(sql, null);
		return result;
	}

	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private Map<Integer, SpawnInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Integer, SpawnInfo> infos = null;
		SpawnInfo info = null;
		if (pstmt != null) {
			infos = new HashMap<Integer, SpawnInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new SpawnInfo();
					info.setId(rs.getInt("id"));
					info.setMapid(rs.getInt("mapid"));
					info.setBound_x(rs.getInt("bound_x"));
					info.setBound_y(rs.getInt("bound_y"));
					info.setBound_z(rs.getInt("bound_z"));
					info.setInitStatu(rs.getInt("initStatu"));
					info.setTagId(rs.getInt("tagId"));
					info.setPreSpawanId(rs.getString("preSpawanId"));
					info.setNextSpawanId(rs.getString("nextSpawanId"));
					info.setRestType(rs.getInt("rest_type"));
					info.setRestSecs(rs.getInt("rest_secs"));
					info.setToalCount(rs.getInt("toalCount"));
					info.setMaxCount(rs.getInt("maxCount"));
					info.setCampaignFeatures(rs.getInt("campaignFeatures"));
					info.setEntityId(rs.getInt("entityId"));
					info.setEntityType(rs.getInt("entity_type"));
					info.setTimerType(rs.getInt("timer_type"));
					info.setTimerBegin(rs.getString("timer_begin"));
					info.setTimerEnd(rs.getString("timer_end"));
					info.setInitSecs(rs.getInt("init_secs"));
					info.setParam1(rs.getInt("param1"));
					info.setParam2(rs.getInt("param2"));
					info.setParam3(rs.getInt("param3"));
					info.setParam4(rs.getInt("param4"));
					info.setStrParam1(rs.getString("strParam1"));
					info.setStrParam2(rs.getString("strParam2"));
					info.setStrParam3(rs.getString("strParam3"));
					info.setPosition(new Vector3(rs.getInt("bound_x") / Vector3.Accuracy, rs.getInt("bound_y") / Vector3.Accuracy, rs.getInt("bound_z") / Vector3.Accuracy));
					infos.put(info.getId(), info);
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
