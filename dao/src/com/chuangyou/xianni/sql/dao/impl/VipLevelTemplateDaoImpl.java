package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.vip.VipLevelTemplate;
import com.chuangyou.xianni.sql.dao.VipLevelTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class VipLevelTemplateDaoImpl extends BaseDao implements VipLevelTemplateDao {

	@Override
	public List<VipLevelTemplate> load() {
		String sql = "select * from tb_z_vip_level;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<VipLevelTemplate> list = new ArrayList<VipLevelTemplate>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					VipLevelTemplate tem = new VipLevelTemplate();
					tem.setLv(rs.getInt("lv"));
					tem.setNeedExp(rs.getInt("needExp"));
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
