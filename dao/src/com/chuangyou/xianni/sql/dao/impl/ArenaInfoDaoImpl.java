package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.arena.ArenaInfo;
import com.chuangyou.xianni.sql.dao.ArenaInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ArenaInfoDaoImpl extends BaseDao implements ArenaInfoDao {

	@Override
	public boolean saveOrUpdata(ArenaInfo arenaInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "REPLACE INTO tb_u_arena_info(playerId,challengeCount,winCount,freshTime,opponenter1,result1,opponenter2,result2,opponenter3,result3,opponenter4,result4,opponenter5,result5,opponenter6,result6,reward1,reward4,reward6) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, arenaInfo.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, arenaInfo.getChallengeCount()));
		params.put(3, new DbParameter(Types.INTEGER, arenaInfo.getWinCount()));
		params.put(4, new DbParameter(Types.TIMESTAMP, arenaInfo.getFreshTime()));
		params.put(5, new DbParameter(Types.BIGINT, arenaInfo.getOpponenter1()));
		params.put(6, new DbParameter(Types.INTEGER, arenaInfo.getResult1()));
		params.put(7, new DbParameter(Types.BIGINT, arenaInfo.getOpponenter2()));
		params.put(8, new DbParameter(Types.INTEGER, arenaInfo.getResult2()));
		params.put(9, new DbParameter(Types.BIGINT, arenaInfo.getOpponenter3()));
		params.put(10, new DbParameter(Types.INTEGER, arenaInfo.getResult3()));
		params.put(11, new DbParameter(Types.BIGINT, arenaInfo.getOpponenter4()));
		params.put(12, new DbParameter(Types.INTEGER, arenaInfo.getResult4()));
		params.put(13, new DbParameter(Types.BIGINT, arenaInfo.getOpponenter5()));
		params.put(14, new DbParameter(Types.INTEGER, arenaInfo.getResult5()));
		params.put(15, new DbParameter(Types.BIGINT, arenaInfo.getOpponenter6()));
		params.put(16, new DbParameter(Types.INTEGER, arenaInfo.getResult6()));
		params.put(17, new DbParameter(Types.INTEGER, arenaInfo.getReward1()));
		params.put(18, new DbParameter(Types.INTEGER, arenaInfo.getReward4()));
		params.put(19, new DbParameter(Types.INTEGER, arenaInfo.getReward6()));
		result = execNoneQuery(sql, params) > -1 ? true : false;
		arenaInfo.setOp(Option.None);
		return result;
	}

	@Override
	public ArenaInfo get(long playerId) {
		String sql = "SELECT * FROM tb_u_arena_info WHERE playerId = " + playerId +";";
		List<ArenaInfo> result = read(sql, null);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	private List<ArenaInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<ArenaInfo> infos = null;
		ArenaInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<ArenaInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new ArenaInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setChallengeCount(rs.getInt("challengeCount"));
					info.setWinCount(rs.getInt("winCount"));

					Timestamp time = rs.getTimestamp("freshTime");
					if (time != null) {
						info.setFreshTime(new Date(time.getTime()));
					} else {
						info.setFreshTime(new Date());
					}
					info.setOpponenter1(rs.getLong("opponenter1"));
					info.setResult1(rs.getInt("result1"));
					info.setOpponenter2(rs.getLong("opponenter2"));
					info.setResult2(rs.getInt("result2"));
					info.setOpponenter3(rs.getLong("opponenter3"));
					info.setResult3(rs.getInt("result3"));
					info.setOpponenter4(rs.getLong("opponenter4"));
					info.setResult4(rs.getInt("result4"));
					info.setOpponenter5(rs.getLong("opponenter5"));
					info.setResult5(rs.getInt("result5"));
					info.setOpponenter6(rs.getLong("opponenter6"));
					info.setResult6(rs.getInt("result6"));
					info.setReward1(rs.getInt("reward1"));
					info.setReward4(rs.getInt("reward4"));
					info.setReward6(rs.getInt("reward6"));
					info.setOp(Option.None);
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
