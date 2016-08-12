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
import com.chuangyou.xianni.entity.item.ItemInfo;
import com.chuangyou.xianni.sql.dao.ItemInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ItemInfoDaoImpl extends BaseDao implements ItemInfoDao {

	@Override
	public List<ItemInfo> getAllItem(long playerId) {
		String sql = "SELECT * FROM tb_u_item_info WHERE playerId = " + playerId;
		List<ItemInfo> all = read(sql, null);
		return all;
	}

	@Override
	public List<ItemInfo> getEquimpent(long playerId, int ObjectId) {
		return null;
	}

	@Override
	public List<ItemInfo> getItemInfos(List<Integer> ids) {
		return null;
	}

	@Override
	public boolean addItemInfo(ItemInfo info) {
		boolean result = false;
		String sql = "INSERT INTO tb_u_item_info (id,templateId,playerId,isExist,objectId,bagType,pos,isBinds,isUsed,"
				+ "validDate,beginDate,count,removeType,removeDate,addType,addDate,pro,qualityCoefficient,grow,awaken,awakenPoint,stone)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getId()));
		para.put(2, new DbParameter(Types.BIGINT, info.getTemplateId()));
		para.put(3, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(4, new DbParameter(Types.BOOLEAN, info.isExist()));
		para.put(5, new DbParameter(Types.BIGINT, info.getObjectId()));
		para.put(6, new DbParameter(Types.INTEGER, info.getBagType()));
		para.put(7, new DbParameter(Types.INTEGER, info.getPos()));
		para.put(8, new DbParameter(Types.BOOLEAN, info.isBinds()));
		para.put(9, new DbParameter(Types.BOOLEAN, info.isUsed()));
		para.put(10, new DbParameter(Types.INTEGER, info.getValidDate()));
		para.put(11, new DbParameter(Types.TIMESTAMP, info.getBeginDate()));
		para.put(12, new DbParameter(Types.INTEGER, info.getCount()));
		para.put(13, new DbParameter(Types.INTEGER, info.getRemoveType()));
		para.put(14, new DbParameter(Types.TIMESTAMP, info.getRemoveDate()));
		para.put(15, new DbParameter(Types.INTEGER, info.getAddType()));
		para.put(16, new DbParameter(Types.TIMESTAMP, info.getAddDate()));
		para.put(17, new DbParameter(Types.INTEGER, info.getPro()));
		para.put(18, new DbParameter(Types.INTEGER, info.getQualityCoefficient()));
		para.put(19, new DbParameter(Types.INTEGER, info.getGrow()));
		para.put(20, new DbParameter(Types.INTEGER, info.getAwaken()));
		para.put(21, new DbParameter(Types.INTEGER, info.getAwakenPoint()));
		para.put(22, new DbParameter(Types.INTEGER, info.getStone()));
		info.setOp(Option.None);
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean updateItemInfo(ItemInfo info) {
		boolean result = false;
		String sql = "REPLACE INTO tb_u_item_info (id,templateId,playerId,isExist,objectId,bagType,pos,isBinds,isUsed,"
				+ "validDate,beginDate,count,removeType,removeDate,addType,addDate,pro,qualityCoefficient,grow,awaken,awakenPoint,stone)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getId()));
		para.put(2, new DbParameter(Types.BIGINT, info.getTemplateId()));
		para.put(3, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(4, new DbParameter(Types.BOOLEAN, info.isExist()));
		para.put(5, new DbParameter(Types.BIGINT, info.getObjectId()));
		para.put(6, new DbParameter(Types.INTEGER, info.getBagType()));
		para.put(7, new DbParameter(Types.INTEGER, info.getPos()));
		para.put(8, new DbParameter(Types.BOOLEAN, info.isBinds()));
		para.put(9, new DbParameter(Types.BOOLEAN, info.isUsed()));
		para.put(10, new DbParameter(Types.INTEGER, info.getValidDate()));
		para.put(11, new DbParameter(Types.TIMESTAMP, info.getBeginDate()));
		para.put(12, new DbParameter(Types.INTEGER, info.getCount()));
		para.put(13, new DbParameter(Types.INTEGER, info.getRemoveType()));
		para.put(14, new DbParameter(Types.TIMESTAMP, info.getRemoveDate()));
		para.put(15, new DbParameter(Types.INTEGER, info.getAddType()));
		para.put(16, new DbParameter(Types.TIMESTAMP, info.getAddDate()));
		para.put(17, new DbParameter(Types.INTEGER, info.getPro()));
		para.put(18, new DbParameter(Types.INTEGER, info.getQualityCoefficient()));
		para.put(19, new DbParameter(Types.INTEGER, info.getGrow()));
		para.put(20, new DbParameter(Types.INTEGER, info.getAwaken()));
		para.put(21, new DbParameter(Types.INTEGER, info.getAwakenPoint()));
		para.put(22, new DbParameter(Types.INTEGER, info.getStone()));

		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public long getMaxItemId() {

		String sql = "SELECT MAX(id) AS maxid FROM tb_u_item_info";
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

	private List<ItemInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<ItemInfo> infos = null;
		ItemInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<ItemInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new ItemInfo();
					info.setId(rs.getLong("id"));
					info.setTemplateId(rs.getInt("templateId"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setPos(rs.getShort("pos"));
					info.setExist(rs.getBoolean("isExist"));
					info.setObjectId(rs.getLong("objectId"));
					info.setBagType(rs.getShort("bagType"));
					info.setBinds(rs.getBoolean("isBinds"));
					info.setUsed(rs.getBoolean("isUsed"));
					info.setValidDate(rs.getInt("validDate"));
					
					Timestamp tt = rs.getTimestamp("beginDate");
					if (tt != null) {
						info.setBeginDate(new Date(tt.getTime()));
					}
					
					info.setCount(rs.getInt("count"));
					info.setRemoveType(rs.getShort("removeType"));
					tt = rs.getTimestamp("removeDate");
					if (tt != null) {
						info.setRemoveDate(new Date(tt.getTime()));
					}
					info.setAddType(rs.getShort("addType"));
					tt = rs.getTimestamp("addDate");
					if (tt != null) {
						info.setAddDate(new Date(tt.getTime()));
					}
					
					info.setPro(rs.getInt("pro"));
					info.setQualityCoefficient(rs.getInt("qualityCoefficient"));
					info.setGrow(rs.getInt("grow"));
					
					info.setAwaken(rs.getInt("awaken"));
					info.setAwakenPoint(rs.getInt("awakenPoint"));
					info.setStone(rs.getInt("stone"));
					
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
