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
		String sql = "INSERT INTO tb_u_user_info (userId,userName,passWord,serverId,serverName,loginTime1,loginTime2,loginIP1,loginIP2,email,qq,createTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, user.getUserId()));
		para.put(2, new DbParameter(Types.VARCHAR, user.getUserName()));
		para.put(3, new DbParameter(Types.VARCHAR, user.getPassWord()));
		para.put(4, new DbParameter(Types.INTEGER, user.getServerId()));
		para.put(5, new DbParameter(Types.VARCHAR, user.getServerName()));
		para.put(6, new DbParameter(Types.TIMESTAMP, user.getLoginTime1()));
		para.put(7, new DbParameter(Types.TIMESTAMP, user.getLoginTime2()));
		para.put(8, new DbParameter(Types.VARCHAR, user.getLoginIP1()));
		para.put(9, new DbParameter(Types.VARCHAR, user.getLoginIP2()));
		para.put(10, new DbParameter(Types.VARCHAR, user.getEmail()));
		para.put(11, new DbParameter(Types.VARCHAR, user.getQq()));
		para.put(12, new DbParameter(Types.TIMESTAMP, user.getCreateTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public long getMaxId() {
		String sql = "SELECT MAX(userId) AS maxid FROM tb_u_user_info";
		long maxId = 0;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					maxId = rs.getLong("maxid");
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
					info.setServerId(rs.getInt("serverId"));
					info.setServerName(rs.getString("serverName"));
					Timestamp tt = rs.getTimestamp("loginTime1");
					if (tt != null) {
						info.setLoginTime1(new Date(tt.getTime()));
					}
					Timestamp tt2 = rs.getTimestamp("loginTime2");
					if (tt2 != null) {
						info.setLoginTime2(new Date(tt2.getTime()));
					}
					info.setLoginIP1(rs.getString("loginIP1"));
					info.setLoginIP2(rs.getString("loginIP2"));

					info.setEmail(rs.getString("email"));
					info.setQq(rs.getString("qq"));

					Timestamp tt3 = rs.getTimestamp("createTime");
					if (tt3 != null) {
						info.setCreateTime(new Date(tt3.getTime()));
					}
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
