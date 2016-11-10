package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.sevenDaysGiftPacks.WelfareConditionRecordInfo;
import com.chuangyou.xianni.sql.dao.WelfareConditionRecordDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class WelfareConditionRecordDaoImpl extends BaseDao implements WelfareConditionRecordDao {

	/** 通过玩家ID获取福利信息 */
	@Override
	public WelfareConditionRecordInfo getInfoByPlayerId(long playerId) {

		String sql = "SELECT * FROM tb_u_welfare_condition WHERE playerId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, playerId));
		PreparedStatement pst = execQuery(sql, para);
		ResultSet rs = null;
		WelfareConditionRecordInfo info = null;
		try {
			rs = pst.executeQuery();
			if (rs.next()) {
				info = new WelfareConditionRecordInfo();
				info.setPlayerId(playerId);
				info.setTime(rs.getInt("time"));
				info.setDays(rs.getInt("days"));
				info.setOnLineTime(rs.getInt("onLineTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}
		return info;
	}

	@Override
	public boolean update(WelfareConditionRecordInfo sevenDaysGiftPackageInfo) {
		if (!sevenDaysGiftPackageInfo.beginUpdate())
			return false;
		boolean result;
		String sql = "UPDATE tb_u_welfare_condition SET time = ?, days = ?, onlineTime = ? WHERE playerId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, sevenDaysGiftPackageInfo.getTime()));
		para.put(2, new DbParameter(Types.INTEGER, sevenDaysGiftPackageInfo.getDays()));
		para.put(3, new DbParameter(Types.INTEGER, sevenDaysGiftPackageInfo.getOnLineTime()));
		para.put(4, new DbParameter(Types.BIGINT, sevenDaysGiftPackageInfo.getPlayerId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		sevenDaysGiftPackageInfo.commitUpdate(result);
		return result;
	}

	@Override
	public boolean add(WelfareConditionRecordInfo sevenDaysGiftPackageInfo) {
		if (!sevenDaysGiftPackageInfo.beginAdd())
			return false;
		boolean result;
		String sql = "INSERT INTO tb_u_welfare_condition VALUES(?, ?, ?, ?)";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, sevenDaysGiftPackageInfo.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, sevenDaysGiftPackageInfo.getTime()));
		para.put(3, new DbParameter(Types.INTEGER, sevenDaysGiftPackageInfo.getDays()));
		para.put(4, new DbParameter(Types.INTEGER, sevenDaysGiftPackageInfo.getOnLineTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		sevenDaysGiftPackageInfo.commitAdd(result);
		return result;
	}

}
