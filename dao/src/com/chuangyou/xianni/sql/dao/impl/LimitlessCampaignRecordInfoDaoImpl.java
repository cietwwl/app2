package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.campaign.LimitlessCampaignRecordInfo;
import com.chuangyou.xianni.sql.dao.LimitlessCampaignRecordInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class LimitlessCampaignRecordInfoDaoImpl extends BaseDao implements LimitlessCampaignRecordInfoDao {

	@Override
	public boolean savaOrUpdata(LimitlessCampaignRecordInfo info) {
		info.beginUpdate();
		boolean result = false;
		String sql = "REPLACE INTO tb_u_limitless_campaign_record_info (playerId,campaignId,updataTime) VALUES (?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, info.getCampaignId()));
		para.put(3, new DbParameter(Types.BIGINT, info.getUpdataTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public LimitlessCampaignRecordInfo getByPlayerId(long playerId) {
		String sql = "SELECT * FROM tb_u_limitless_campaign_record_info;";
		List<LimitlessCampaignRecordInfo> result = read(sql, null);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	/**
	 * @param sqlText
	 * @param para
	 */
	private List<LimitlessCampaignRecordInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<LimitlessCampaignRecordInfo> infos = null;
		LimitlessCampaignRecordInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new LimitlessCampaignRecordInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setCampaignId(rs.getInt("campaignId"));
					info.setUpdataTime(rs.getLong("updataTime"));
					info.setOp(Option.None);
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
