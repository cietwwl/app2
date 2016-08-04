package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.campaign.CampaignRecordInfo;
import com.chuangyou.xianni.sql.dao.CampaignRecordInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class CampaignRecordInfoDaoImpl extends BaseDao implements CampaignRecordInfoDao {

	@Override
	public List<CampaignRecordInfo> getRecods(long playerId) {
		String sql = "SELECT * FROM tb_u_campaign_info WHERE playerId=" + playerId;
		return read(sql, null);
	}

	@Override
	public boolean saveOrUpdata(CampaignRecordInfo info) {
		boolean result = false;
		String sql = "REPLACE INTO tb_u_campaign_info(id,playerId,campaignId,point,statu,assess,updateTime) VALUES (?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, info.getId()));
		para.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(3, new DbParameter(Types.INTEGER, info.getCampaignId()));
		para.put(4, new DbParameter(Types.INTEGER, info.getPoint()));
		para.put(5, new DbParameter(Types.INTEGER, info.getStatu()));
		para.put(6, new DbParameter(Types.INTEGER, info.getAssess()));
		para.put(7, new DbParameter(Types.TIMESTAMP, info.getUpdataTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public int getMaxId() {
		String sql = "SELECT MAX(id) AS maxid FROM tb_u_campaign_info";
		int maxId = 0;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					maxId = rs.getInt("maxid");
					break;
				}
			} catch (SQLException e) {
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return maxId == 0 ? 1 : maxId + 1;
	}

	private List<CampaignRecordInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<CampaignRecordInfo> infos = null;
		CampaignRecordInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<CampaignRecordInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new CampaignRecordInfo();
					info.setId(rs.getInt("id"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setCampaignId(rs.getInt("campaignId"));
					info.setPoint(rs.getInt("point"));
					info.setStatu(rs.getInt("statu"));
					info.setAssess(rs.getInt("assess"));

					Timestamp tt = rs.getTimestamp("updataTime");
					if (tt != null) {
						info.setUpdataTime(new Date(tt.getTime()));
					}
					infos.add(info);
					info.setOp(Option.None);
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
