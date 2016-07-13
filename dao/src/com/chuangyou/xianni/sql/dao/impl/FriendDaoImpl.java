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
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.sql.dao.FriendDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

/**
 * 好友DAO
 * 
 * @author laofan
 *
 */
public class FriendDaoImpl extends BaseDao implements FriendDao {

	@Override
	public boolean add(Friend info) {
		// TODO Auto-generated method stub
		info.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_friend_info (roleId,friendIdAndTimes)" + " VALUES (?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getRoleId()));
		para.put(2, new DbParameter(Types.VARCHAR, info.getFriendIdAndTimes()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(Friend info) {
		// TODO Auto-generated method stub
		info.beginUpdate();
		boolean result = false;
		String sql = "UPDATE tb_u_friend_info SET friendIdAndTimes= ? " + " WHERE roleId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.VARCHAR, info.getFriendIdAndTimes()));
		para.put(2, new DbParameter(Types.BIGINT, info.getRoleId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public Friend get(long roleId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_u_friend_info WHERE roleId=" + roleId + ";";
		List<Friend> result = read(sql, null);
		if (result == null || result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}

	private List<Friend> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<Friend> infos = null;
		Friend info = null;
		if (pstmt != null) {
			infos = new ArrayList<Friend>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new Friend();
					info.setRoleId(rs.getLong("roleId"));
					info.setFriendIdAndTimes(rs.getString("friendIdAndTimes"));
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
