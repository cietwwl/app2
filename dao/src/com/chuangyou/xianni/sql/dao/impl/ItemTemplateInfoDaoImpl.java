package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.sql.dao.ItemTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ItemTemplateInfoDaoImpl extends BaseDao implements ItemTemplateInfoDao {

	@Override
	public List<ItemTemplateInfo> getItemTemps() {
		String sql = "SELECT * FROM tb_z_item_template";
		List<ItemTemplateInfo> temps = read(sql, null);
		return temps;
	}

	private List<ItemTemplateInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<ItemTemplateInfo> infos = null;
		ItemTemplateInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<ItemTemplateInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new ItemTemplateInfo();
					info.setId(rs.getInt("id"));
					info.setMasterType(rs.getInt("masterType"));
					info.setSonType(rs.getInt("sonType"));
					info.setName(rs.getString("name"));
					info.setAmount(rs.getInt("amount"));
					info.setStatistics1(rs.getInt("statistics1"));
					info.setStatistics2(rs.getInt("statistics2"));
					info.setStatistics3(rs.getInt("statistics3"));
					info.setStatistics4(rs.getInt("statistics4"));
					info.setItemBase(rs.getInt("itemBase"));
					info.setQualityCoefficient(rs.getInt("qualityCoefficient"));
					info.setGrow(rs.getInt("grow"));
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

}
