package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.sql.dao.CampaignTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class CampaignTemplateInfoDaoImpl extends BaseDao implements CampaignTemplateInfoDao {

	@Override
	public List<CampaignTemplateInfo> getAll() {
		String sql = "SELECT * FROM tb_z_campaign_info";
		return read(sql, null);
	}

	private List<CampaignTemplateInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<CampaignTemplateInfo> infos = null;
		CampaignTemplateInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new CampaignTemplateInfo();
					info.setTemplateId(rs.getInt("templateId"));
					info.setCampaignName(rs.getString("campaignName"));
					info.setStoryId(rs.getInt("storyId"));
					info.setType(rs.getInt("type"));
					info.setMinLevel(rs.getInt("minLevel"));
					info.setMaxLevel(rs.getInt("maxLevel"));
					info.setPreCampaignId(rs.getInt("preCampaignId"));
					info.setNextCampaignId(rs.getString("nextCampaignId"));
					info.setCapacity(rs.getInt("capacity"));
					info.setRewardItems(rs.getString("rewardItems"));
					info.setDescription(rs.getString("description"));
					info.setOpenType(rs.getInt("openType"));
					info.setOpenParams(rs.getString("openParams"));
					info.setOpenTime(rs.getInt("openTime"));
					info.setStartScriptId(rs.getInt("startScriptId"));
					info.setEndScriptId(rs.getInt("endScriptId"));
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
