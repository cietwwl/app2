package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.inverseBead.InverseBeadTem;
import com.chuangyou.xianni.sql.dao.InverseBeadTemDao;
import com.chuangyou.xianni.sql.db.BaseDao;

/**
 * 天逆珠
 * 
 * @author Administrator
 */
public class InverseBeadTemDaoImpl extends BaseDao implements InverseBeadTemDao {

	@Override
	public List<InverseBeadTem> load() {
		String sql = "select * from tb_z_inverse_bead;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<InverseBeadTem> list = new ArrayList<InverseBeadTem>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					InverseBeadTem tem = new InverseBeadTem();
					tem.setId(rs.getInt("id"));
					tem.setName(rs.getString("name"));
					tem.setNeedGoodsNum(rs.getInt("needGoodsNum"));
					tem.setAddVal(rs.getInt("addVal"));
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
