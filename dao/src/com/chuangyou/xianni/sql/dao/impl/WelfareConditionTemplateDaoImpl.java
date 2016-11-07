package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.welfare.WelfareConditionTemplate;
import com.chuangyou.xianni.sql.dao.WelfareConditionTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class WelfareConditionTemplateDaoImpl extends BaseDao implements WelfareConditionTemplateDao {

	@Override
	public List<WelfareConditionTemplate> geTemplates() {
		List<WelfareConditionTemplate> result = new ArrayList<>();
		String sql = "select * from tb_z_welfare_condition";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			while (rs.next()) {
				WelfareConditionTemplate info = new WelfareConditionTemplate();
				info.setWelfareType(rs.getInt("welfareType"));
				info.setResetType(rs.getInt("resetType"));
				info.setConditionType1(rs.getInt("conditionType1"));
				info.setConditionType2(rs.getInt("conditionType2"));
				info.setConditionType3(rs.getInt("conditionType3"));
				info.setConditionType4(rs.getInt("conditionType4"));
				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}

		return result;
	}

}
