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
					config.setAlertRange(rs.getInt("alertRange"));
					config.setPatrolRange(rs.getInt("patrolRange"));
					config.setAttackDistance(rs.getInt("attackDistance"));
					config.setFollowUpDistance(rs.getInt("followUpDistance"));
					config.setMoveSpeed(rs.getInt("moveSpeed"));
					config.setAttackSpeed(rs.getInt("attackSpeed"));
					config.setRunBack(rs.getBoolean("runBack"));
					config.setFullState(rs.getBoolean("fullState"));
					config.setActiveAttack(rs.getInt("activeAttack"));
					config.setRewardExp(rs.getBoolean("isRewardExp"));
					config.setRewardStone(rs.getBoolean("isRewardStone"));
					config.setDropped(rs.getBoolean("isDropped"));
					config.setScript(rs.getInt("script"));
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
