package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;
import com.chuangyou.xianni.sql.dao.PlayerBeadRefreshTimeDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

/**
 * 天逆珠
 * 
 * @author Administrator
 */
public class InverseBeadRefreshDaoImpl extends BaseDao implements PlayerBeadRefreshTimeDao {

	@Override
	public boolean add(PlayerBeadTimeInfo playerInverseBead) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "replace into tb_u_player_bead_time_info(playerId,currRefreshId,beadRefreshId,beadRefreshDateTime,auraNum,auraRefreshDateTime) values(?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerInverseBead.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, playerInverseBead.getCurrRefreshId()));
		params.put(3, new DbParameter(Types.VARCHAR, playerInverseBead.getBeadRefreshId()));
		params.put(4, new DbParameter(Types.TIMESTAMP, playerInverseBead.getBeadRefreshDateTime()));
		params.put(5, new DbParameter(Types.INTEGER, playerInverseBead.getAuraNum()));
		params.put(6, new DbParameter(Types.TIMESTAMP, playerInverseBead.getAuraRefreshDateTime()));

		result = execNoneQuery(sql, params) > -1 ? true : false;
		playerInverseBead.setOp(Option.None);
		return result;
	}

	@Override
	public boolean update(PlayerBeadTimeInfo playerInverseBead) {
		boolean result = false;
		playerInverseBead.beginUpdate();
		String sql = "update tb_u_player_bead_time_info set currRefreshId=?,beadRefreshId=?,beadRefreshDateTime=?,auraNum=?,auraRefreshDateTime=? where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();

		params.put(1, new DbParameter(Types.INTEGER, playerInverseBead.getCurrRefreshId()));
		params.put(2, new DbParameter(Types.VARCHAR, playerInverseBead.getBeadRefreshId()));
		params.put(3, new DbParameter(Types.TIMESTAMP, playerInverseBead.getBeadRefreshDateTime()));
		params.put(4, new DbParameter(Types.INTEGER, playerInverseBead.getAuraNum()));
		params.put(5, new DbParameter(Types.TIMESTAMP, playerInverseBead.getAuraRefreshDateTime()));
		params.put(6, new DbParameter(Types.BIGINT, playerInverseBead.getPlayerId()));

		result = execNoneQuery(sql, params) > -1 ? true : false;
		playerInverseBead.commitUpdate(result);
		return result;

	}

	@Override
	public PlayerBeadTimeInfo get(long playerId) {
		String sql = "select * from tb_u_player_bead_time_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return readTimeInfo(sql, params);
	}

	private PlayerBeadTimeInfo readTimeInfo(String sqlText, Map<Integer, DbParameter> params) {
		PreparedStatement pstmt = execQuery(sqlText, params);
		ResultSet rs = null;
		PlayerBeadTimeInfo info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				if (rs.next()) {
					info = new PlayerBeadTimeInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setCurrRefreshId(rs.getInt("currRefreshId"));
					info.setBeadRefreshId(rs.getString("beadRefreshId"));
					Timestamp beadRefreshDateTime = rs.getTimestamp("beadRefreshDateTime");
					if (beadRefreshDateTime != null) {
						info.setBeadRefreshDateTime(beadRefreshDateTime);
					}
					info.setAuraNum(rs.getInt("auraNum"));
					Timestamp auraRefreshDateTime = rs.getTimestamp("auraRefreshDateTime");
					if (auraRefreshDateTime != null) {
						info.setAuraRefreshDateTime(auraRefreshDateTime);
					}
				}
			} catch (SQLException e) {
				info = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return info;
	}

}
