package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.vip.VipTemplate;
import com.chuangyou.xianni.sql.dao.VipTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;

/**
 * vip
 * 
 * @author Administrator
 */
public class VipTemDaoImpl extends BaseDao implements VipTemplateDao {

	@Override
	public List<VipTemplate> load() {
		String sql = "select * from tb_z_vip;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<VipTemplate> list = new ArrayList<VipTemplate>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					VipTemplate tem = new VipTemplate();
					tem.setId(rs.getInt("id"));
					tem.setVipDuration(rs.getInt("vipDuration"));
					tem.setMoney(rs.getDouble("money"));
					tem.setCash(rs.getInt("cash"));
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
