package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.property.PropertyFightingTemplate;
import com.chuangyou.xianni.sql.dao.PropertyFightingTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class PropertyFightingTemplateDaoImpl extends BaseDao implements PropertyFightingTemplateDao {

	public List<PropertyFightingTemplate> getAll() {
		String sql = "select * from tb_z_property_fighting;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<PropertyFightingTemplate> list = new ArrayList<PropertyFightingTemplate>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					PropertyFightingTemplate propertyFightingTemplate = new PropertyFightingTemplate();
					propertyFightingTemplate.setProperty(rs.getInt("property"));
					propertyFightingTemplate.setPname(rs.getString("pname"));
					propertyFightingTemplate.setFighting(rs.getInt("fighting"));

					list.add(propertyFightingTemplate);
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
