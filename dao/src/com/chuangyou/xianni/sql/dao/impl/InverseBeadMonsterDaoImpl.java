package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.inverseBead.InverseBeadMonster;
import com.chuangyou.xianni.sql.dao.InverseBeadMonsterTemDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class InverseBeadMonsterDaoImpl extends BaseDao implements InverseBeadMonsterTemDao {
	@Override
	public List<InverseBeadMonster> load() {
		String sql = "select * from tb_z_inverseBead_monster;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<InverseBeadMonster> list = new ArrayList<InverseBeadMonster>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					InverseBeadMonster tem = new InverseBeadMonster();
					tem.setId(rs.getInt("id"));
					tem.setMonsterId(rs.getInt("monsterId"));
					tem.setNextId(rs.getInt("nextId"));
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
}
