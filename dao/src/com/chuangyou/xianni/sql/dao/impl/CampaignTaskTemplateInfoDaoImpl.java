package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.campaign.CampaignTaskTemplateInfo;
import com.chuangyou.xianni.sql.dao.CampaignTaskTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class CampaignTaskTemplateInfoDaoImpl extends BaseDao implements CampaignTaskTemplateInfoDao {

	@Override
	public List<CampaignTaskTemplateInfo> getAll() {
		String sql = "SELECT * FROM tb_z_campaign_task;";
		return read(sql, null);
	}

	private List<CampaignTaskTemplateInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<CampaignTaskTemplateInfo> infos = null;
		CampaignTaskTemplateInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new CampaignTaskTemplateInfo();
					info.setTaskId(rs.getInt("id"));
					info.setConditionType(rs.getInt("conditionType"));
					info.setName(rs.getString("name"));
					info.setPoint(rs.getInt("point"));
					info.setRepair(rs.getInt("repair"));
					info.setParam1(rs.getInt("param1"));
					info.setParam2(rs.getInt("param2"));
					info.setParam3(rs.getInt("param3"));
					info.setParam4(rs.getInt("param4"));
					info.setStrParam1(rs.getString("strParam1"));
					
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
