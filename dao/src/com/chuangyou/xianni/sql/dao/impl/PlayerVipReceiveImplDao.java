package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.vip.PlayerVipReceive;
import com.chuangyou.xianni.sql.dao.VipPlayerReceiveDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class PlayerVipReceiveImplDao extends BaseDao implements VipPlayerReceiveDao {
	@Override
	public List<PlayerVipReceive> getAll(long playerId) {
		String sql = "select * from tb_u_player_vip where playerId=?;";
		
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		PreparedStatement pstmt = execQuery(sql, params);
		ResultSet rs = null;
		List<PlayerVipReceive> list = new ArrayList<PlayerVipReceive>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					PlayerVipReceive tem = new PlayerVipReceive();
					tem.setId(rs.getInt("id"));
					tem.setPlayerId(rs.getLong("playerId"));
					tem.setVipType(rs.getInt("vipType"));
					tem.setVipId(rs.getInt("vipId"));
					tem.setReceiveTime(rs.getDate("receiveTime"));
					list.add(tem);
				}
			} catch (SQLException e) {
				Log.error("执行出错:" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return list;
	}

	@Override
	public boolean add(PlayerVipReceive playerVipReceive) {
		playerVipReceive.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_player_vip (playerId,vipType,vipId,receiveTime)" + " VALUES (?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, playerVipReceive.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, playerVipReceive.getVipType()));
		para.put(3, new DbParameter(Types.INTEGER, playerVipReceive.getVipId()));
		para.put(4, new DbParameter(Types.DATE, playerVipReceive.getReceiveTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		playerVipReceive.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(PlayerVipReceive playerVipReceive) {
		// TODO Auto-generated method stub
		return false;
	}
}
