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
import com.chuangyou.xianni.entity.User.UserInfo;
import com.chuangyou.xianni.sql.dao.UserInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class UserDaoImpl extends BaseDao implements UserInfoDao {

	@Override
	public UserInfo getUser(String userName) {
		String sql = "SELECT * FROM tb_u_user_info WHERE userName = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.VARCHAR, userName));
		List<UserInfo> userInfo = read(sql, para);
		if (userInfo != null && userInfo.size() > 0) {
			return userInfo.get(0);
		}
		return null;
	}

	@Override
	public boolean save(UserInfo user) {
		boolean result = false;
		String sql = "INSERT INTO tb_u_user_info (userId,userName,passWord) VALUES (?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, user.getUserId()));
		para.put(2, new DbParameter(Types.VARCHAR, user.getUserName()));
		para.put(3, new DbParameter(Types.VARCHAR, user.getPassWord()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public long getMaxId() {
		String sql = "SELECT MAX(userId) AS maxid FROM tb_u_user_info";
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

	private List<UserInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<UserInfo> infos = null;
		UserInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<UserInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new UserInfo();
					info.setUserId(rs.getLong("userId"));
					info.setUserName(rs.getString("userName"));
					info.setPassWord(rs.getString("passWord"));
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
