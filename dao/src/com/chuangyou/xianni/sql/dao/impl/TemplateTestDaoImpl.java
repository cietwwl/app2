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
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.test.TemplateTest;
import com.chuangyou.xianni.sql.dao.TemplateTestDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class TemplateTestDaoImpl extends BaseDao implements TemplateTestDao {

	@Override
	public boolean updata(TemplateTest info) {
		boolean result = false;
		String sql = "UPDATE tb_templateTest SET name= ? ,createTime = ? WHERE id = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.VARCHAR, info.getName()));
		para.put(2, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
		para.put(11, new DbParameter(Types.INTEGER, info.getId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public TemplateTest get(int id) {
		String sql = "SELECT * FROM tb_templateTest WHERE id=" + id + ";";
		List<TemplateTest> result = read(sql, null);
		if (result == null) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public boolean add(TemplateTest test) {
		boolean result = false;
		String sql = "INSERT INTO tb_templateTest (id,name,createTime) VALUES (?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, test.getId()));
		para.put(2, new DbParameter(Types.VARCHAR, test.getName()));
		para.put(11, new DbParameter(Types.TIMESTAMP, test.getCreateTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		String sql = "DELETE from tb_templatetest WHERE id = " + id;
		result = execNoneQuery(sql) > -1 ? true : false;
		return result;
	}

	@Override
	public List<TemplateTest> getList(List<Integer> ids) {
		String sql = "SELECT * FROM tb_templatetest ORDER BY createTime ASC";
		List<TemplateTest> result = read(sql, null);
		return result;
	}

	@Override
	public boolean batchSave(List<TemplateTest> list) {
		if (list == null || list.size() == 0) {
			return true;
		}
		StringBuffer sqlbuf = new StringBuffer("INSERT INTO tb_templatetest(id,name,createTime" + ") VALUES ");
		for (int i = 0; i < list.size(); i++) {
			TemplateTest t = list.get(i);
			String value = "(%s,'%s','%s')";
			value = String.format(value, t.getId(), t.getName(), TimeUtil.getDateFormat(t.getCreateTime()));
			if (i == list.size() - 1) {
				sqlbuf.append(value + ";");
			} else {
				sqlbuf.append(value + ",");
			}
		}
		return execNoneQuery(sqlbuf.toString(), null) > -1 ? true : false;
	}

	@Override
	public boolean updateName(int id, String name) {
		return false;
	}

	private List<TemplateTest> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<TemplateTest> infos = null;
		TemplateTest info = null;
		if (pstmt != null) {
			infos = new ArrayList<TemplateTest>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new TemplateTest();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setCreateTime(rs.getDate("createTime"));
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
	public List<TemplateTest> getAll() {
		String sql = "SELECT * FROM tb_templatetest";
		List<TemplateTest> result = read(sql, null);
		return result;
	}

}
