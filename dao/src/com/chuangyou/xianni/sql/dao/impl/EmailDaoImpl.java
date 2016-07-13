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
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.sql.dao.EmailDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;


/**
 * 邮件数据DAO层
 * @author laofan
 *
 */
public class EmailDaoImpl extends BaseDao implements EmailDao {
	
	@Override
	public int add(Email email) {
		// TODO Auto-generated method stub
		email.beginAdd();
		int result = 0;
		String sql = "INSERT INTO tb_u_email_info (roleId,title,content,createTime,getAttachmentTime,"
				+ "delEmailTime,status,attachment)"
				+ " VALUES (?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
	//	para.put(1, new DbParameter(Types.INTEGER, email.getPrivateId()));
		para.put(1, new DbParameter(Types.BIGINT, email.getRoleId()));
		para.put(2, new DbParameter(Types.VARCHAR, email.getTitle()));
		para.put(3, new DbParameter(Types.VARCHAR, email.getContent()));
		para.put(4, new DbParameter(Types.TIMESTAMP, email.getCreateTime()));
		para.put(5, new DbParameter(Types.TIMESTAMP, email.getGetAttachmentTime()));
		para.put(6, new DbParameter(Types.TIMESTAMP, email.getDelEmailTime()));
		para.put(7, new DbParameter(Types.TINYINT, email.getStatus()));
		para.put(8, new DbParameter(Types.VARCHAR, email.getAttachment()));
		result = execLastId(sql, para);
		email.commitAdd(result>0);
		return result;
	}

	@Override
	public boolean delete(long privateId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "DELETE from tb_u_email_info WHERE privateId = " + privateId;
		result = execNoneQuery(sql) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean update(Email email) {
		email.beginUpdate();
		boolean result = false;
		String sql = "UPDATE tb_u_email_info SET roleId= ? ,title = ?,content = ?,createTime = ?,getAttachmentTime = ?,delEmailTime = ?,status = ?,attachment = ?"
				+ " WHERE privateId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, email.getRoleId()));
		para.put(2, new DbParameter(Types.VARCHAR, email.getTitle()));
		para.put(3, new DbParameter(Types.VARCHAR, email.getContent()));
		para.put(4, new DbParameter(Types.TIMESTAMP, email.getCreateTime()));
		para.put(5, new DbParameter(Types.TIMESTAMP, email.getGetAttachmentTime()));
		para.put(6, new DbParameter(Types.TIMESTAMP, email.getDelEmailTime()));
		para.put(7, new DbParameter(Types.TINYINT, email.getStatus()));
		para.put(8, new DbParameter(Types.VARCHAR, email.getAttachment()));
		para.put(9, new DbParameter(Types.INTEGER, email.getPrivateId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		email.commitUpdate(result);
		return result;
	}

		
	private List<Email> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<Email> infos = null;
		Email info = null;
		if (pstmt != null) {
			infos = new ArrayList<Email>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new Email();
					info.setPrivateId(rs.getInt("privateId"));
					info.setRoleId(rs.getLong("roleId"));
					info.setTitle(rs.getString("title"));
					info.setContent(rs.getString("content"));
					info.setCreateTime(rs.getTimestamp("createTime"));
					info.setGetAttachmentTime(rs.getTimestamp("getAttachmentTime"));
					info.setDelEmailTime(rs.getTimestamp("delEmailTime"));
					info.setStatus(rs.getByte("status"));
					info.setAttachment(rs.getString("attachment"));
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


	@Override
	public List<Email> getAll(long roleId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_u_email_info WHERE roleId=" + roleId + " and status!=3 order by createTime desc limit 200;";
		List<Email> result = read(sql, null);
		if (result == null) {
			return null;
		} else {
			return result;
		}
		
	}

}
