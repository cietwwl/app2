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
import com.chuangyou.xianni.entity.avatar.AvatarInfo;
import com.chuangyou.xianni.sql.dao.AvatarInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class AvatarInfoDaoImpl extends BaseDao implements AvatarInfoDao {

	@Override
	public boolean saveOrUpdata(AvatarInfo avatarInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "REPLACE INTO tb_u_avatar_info(id,playerId,tempId,grade,star,correspond,schedule,statu,cindex,createTime) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, avatarInfo.getId()));
		params.put(2, new DbParameter(Types.BIGINT, avatarInfo.getPlayerId()));
		params.put(3, new DbParameter(Types.INTEGER, avatarInfo.getTempId()));
		params.put(4, new DbParameter(Types.INTEGER, avatarInfo.getGrade()));
		params.put(5, new DbParameter(Types.INTEGER, avatarInfo.getStar()));
		params.put(6, new DbParameter(Types.INTEGER, avatarInfo.getCorrespond()));
		params.put(7, new DbParameter(Types.INTEGER, avatarInfo.getSchedule()));
		params.put(8, new DbParameter(Types.INTEGER, avatarInfo.getStatu()));
		params.put(9, new DbParameter(Types.INTEGER, avatarInfo.getIndex()));
		params.put(10, new DbParameter(Types.TIMESTAMP, avatarInfo.getCreateTime()));
		result = execNoneQuery(sql, params) > -1 ? true : false;
		avatarInfo.setOp(Option.None);
		return result;
	}

	@Override
	public int getMaxId() {
		String sql = "SELECT MAX(id) AS maxid FROM tb_u_avatar_info";
		int maxId = 0;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					maxId = rs.getInt("maxid");
					break;
				}
			} catch (SQLException e) {
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return maxId == 0 ? 1 : maxId + 1;
	}

	@Override
	public List<AvatarInfo> get(long playerId) {
		String sql = "SELECT * FROM tb_u_avatar_info WHERE playerId = " + playerId + ";";
		List<AvatarInfo> result = read(sql, null);
		if (result != null && result.size() > 0) {
			return result;
		}
		return null;
	}

	private List<AvatarInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<AvatarInfo> infos = null;
		AvatarInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<AvatarInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new AvatarInfo();
					info.setId(rs.getInt("id"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setTempId(rs.getInt("tempId"));
					info.setGrade(rs.getInt("grade"));
					info.setStar(rs.getInt("star"));
					info.setCorrespond(rs.getInt("correspond"));
					info.setSchedule(rs.getInt("schedule"));
					info.setStatu(rs.getInt("statu"));
					info.setIndex(rs.getInt("cindex"));
					Timestamp time = rs.getTimestamp("createTime");
					if (time != null) {
						info.setCreateTime(new Date(time.getTime()));
					} else {
						info.setCreateTime(new Date());
					}
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
