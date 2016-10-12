package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.reward.RewardTemplate;
import com.chuangyou.xianni.sql.dao.RewardTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class RewardTemplateDaoImp extends BaseDao implements RewardTemplateDao {

	@Override
	public List<RewardTemplate> getAll() {
		String sql = "SELECT * FROM tb_z_reward";
		return read(sql, null);
	}

	private List<RewardTemplate> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<RewardTemplate> infos = new ArrayList<>();
		RewardTemplate info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new RewardTemplate();
					info.setType(rs.getInt("type"));
					info.setName(rs.getString("name"));
					info.setParam1(rs.getInt("param1"));
					info.setParam2(rs.getInt("param2"));
					info.setItemTempId1(rs.getInt("itemTempId1"));
					info.setCount1(rs.getInt("count1"));
					info.setItemTempId2(rs.getInt("itemTempId1"));
					info.setCount2(rs.getInt("count1"));
					info.setItemTempId3(rs.getInt("itemTempId1"));
					info.setCount3(rs.getInt("count1"));
					info.setItemTempId4(rs.getInt("itemTempId1"));
					info.setCount4(rs.getInt("count1"));
					info.setItemTempId5(rs.getInt("itemTempId1"));
					info.setCount5(rs.getInt("count1"));
					info.setItemTempId5(rs.getInt("itemTempId1"));
					info.setExtendParam(rs.getString("extendParam"));
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
