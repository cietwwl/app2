package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.spawn.AiConfig;
import com.chuangyou.xianni.sql.dao.AiConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class AiConfigDaoImpl extends BaseDao implements AiConfigDao {

	@Override
	public Map<Integer, AiConfig> getAll() {
		String sql = "SELECT * FROM tb_z_ai_config";
		Map<Integer, AiConfig> result = read(sql, null);
		return result;
	}

	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private Map<Integer, AiConfig> read(String sqlText, Map<Integer, DbParameter> para) {

		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Integer, AiConfig> list = new HashMap<Integer, AiConfig>();

		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					AiConfig config = new AiConfig();
					config.setId(rs.getInt("id"));
 
					config.setRunBack(rs.getBoolean("runBack"));
					config.setFullState(rs.getBoolean("fullState"));
					config.setActiveAttackPlayer(rs.getBoolean("activeAttackPlayer"));
					config.setActiveAttackSameMonster(rs.getBoolean("activeAttackSameMonster"));
					config.setActiveAttackNotSameMonster(rs.getBoolean("activeAttackNotSameMonster"));
					config.setOpenRunBack(rs.getBoolean("isOpenRunBack"));
					config.setRewardExp(rs.getInt("rewardExp"));
					config.setDropSet(rs.getString("dropSet"));
					config.setScript(rs.getString("script"));
					list.put(config.getId(), config);
				}
			} catch (SQLException e) {
				list.clear();
				System.out.println("执行出错" + sqlText);
				System.out.println(e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return list;

	}
}
