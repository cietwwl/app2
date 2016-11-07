package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.welfare.WelfareTemplate;
import com.chuangyou.xianni.sql.dao.WelfareTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class WelfareTemplateDaoImpl extends BaseDao implements WelfareTemplateDao {

	@Override
	public List<WelfareTemplate> geTemplates() {
		List<WelfareTemplate> result = new ArrayList<>();
		String sql = "select * from tb_z_welfare";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			while (rs.next()) {
				WelfareTemplate info = new WelfareTemplate();
				info.setId(rs.getInt("id"));
				info.setType(rs.getInt("type"));;
				info.setAwardName(rs.getString("awardName"));
				info.setItem1(rs.getInt("item1"));
				info.setBind1(rs.getInt("bind1"));
				info.setNum1(rs.getInt("num1"));
				info.setItem2(rs.getInt("item2"));
				info.setBind2(rs.getInt("bind2"));
				info.setNum2(rs.getInt("num2"));
				info.setItem3(rs.getInt("item3"));
				info.setBind3(rs.getInt("bind3"));
				info.setNum3(rs.getInt("num3"));
				info.setItem4(rs.getInt("item4"));
				info.setBind4(rs.getInt("bind4"));
				info.setNum4(rs.getInt("num4"));
				info.setItem5(rs.getInt("item5"));
				info.setBind5(rs.getInt("bind5"));
				info.setNum5(rs.getInt("num5"));
				info.setItem6(rs.getInt("item6"));
				info.setBind6(rs.getInt("bind6"));
				info.setNum6(rs.getInt("num6"));
				info.setItem7(rs.getInt("item7"));
				info.setBind7(rs.getInt("bind7"));
				info.setNum7(rs.getInt("num7"));
				info.setItem8(rs.getInt("item8"));
				info.setBind8(rs.getInt("bind8"));
				info.setNum8(rs.getInt("num8"));
				info.setParameter1(rs.getInt("parameter1"));
				info.setParameter2(rs.getInt("parameter2"));
				info.setParameter3(rs.getInt("parameter3"));
				info.setParameter4(rs.getInt("parameter4"));
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
