package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.vip.VipBagTemplate;
import com.chuangyou.xianni.sql.dao.VipBagTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class VipBagTemplateDaoImpl extends BaseDao implements VipBagTemplateDao {

	@Override
	public List<VipBagTemplate> load() {
		String sql = "select * from tb_z_vip_bag;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<VipBagTemplate> list = new ArrayList<VipBagTemplate>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					VipBagTemplate tem = new VipBagTemplate();
					tem.setId(rs.getInt("id"));
					tem.setType(rs.getInt("type"));
					tem.setCondition(rs.getInt("condition"));
					tem.setBuyNeedStone(rs.getInt("buyNeedStone"));
					tem.setItem1(rs.getInt("item1"));
					tem.setItem2(rs.getInt("item2"));
					tem.setItem3(rs.getInt("item3"));
					tem.setItem4(rs.getInt("item4"));
					tem.setItem5(rs.getInt("item5"));
					tem.setNum1(rs.getInt("num1"));
					tem.setNum2(rs.getInt("num2"));
					tem.setNum3(rs.getInt("num3"));
					tem.setNum4(rs.getInt("num4"));
					tem.setNum5(rs.getInt("num5"));
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
