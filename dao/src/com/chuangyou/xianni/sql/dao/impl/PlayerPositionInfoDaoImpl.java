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
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.sql.dao.PlayerPositionInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class PlayerPositionInfoDaoImpl extends BaseDao implements PlayerPositionInfoDao {

	@Override
	public boolean save(PlayerPositionInfo pinfo) {

		String sql = "REPLACE INTO tb_u_playerPositionInfo (playerId,mapId,mapTempId,x,y,z,preMapId,preMapTempId,prex,prey,prez) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, pinfo.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, pinfo.getMapId()));
		para.put(3, new DbParameter(Types.INTEGER, pinfo.getMapTempId()));
		para.put(4, new DbParameter(Types.INTEGER, pinfo.getX()));
		para.put(5, new DbParameter(Types.INTEGER, pinfo.getY()));
		para.put(6, new DbParameter(Types.INTEGER, pinfo.getZ()));
		para.put(7, new DbParameter(Types.INTEGER, pinfo.getPreMapId()));
		para.put(8, new DbParameter(Types.INTEGER, pinfo.getPreMapTempId()));
		para.put(9, new DbParameter(Types.INTEGER, pinfo.getPreX()));
		para.put(10, new DbParameter(Types.INTEGER, pinfo.getPreY()));
		para.put(11, new DbParameter(Types.INTEGER, pinfo.getPreZ()));
		boolean result = execNoneQuery(sql, para) > -1 ? true : false;
		pinfo.setOp(Option.None);
		return result;
	}

	@Override
	public PlayerPositionInfo get(long playerId) {
		String sql = "SELECT * FROM tb_u_playerPositionInfo WHERE playerId = " + playerId;
		List<PlayerPositionInfo> result = read(sql, null);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	private List<PlayerPositionInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<PlayerPositionInfo> infos = null;
		PlayerPositionInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<PlayerPositionInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new PlayerPositionInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setMapId(rs.getInt("mapId"));
					info.setMapTempId(rs.getInt("mapTempId"));
					info.setX(rs.getInt("x"));
					info.setY(rs.getInt("y"));
					info.setZ(rs.getInt("z"));

					info.setPreMapId(rs.getInt("preMapId"));
					info.setPreMapTempId(rs.getInt("preMapTempId"));
					info.setPreX(rs.getInt("prex"));
					info.setPreY(rs.getInt("prey"));
					info.setPreZ(rs.getInt("prez"));
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
