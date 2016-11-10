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
import com.chuangyou.xianni.entity.welfare.WelfareInfo;
import com.chuangyou.xianni.sql.dao.WelfareDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class WelfareDaoImpl extends BaseDao implements WelfareDao {

	@Override
	public boolean add(WelfareInfo welfareInfo) {
		boolean result = false;
		if (!welfareInfo.beginAdd())
			return false;
		String sql = "INSERT INTO tb_u_welfare VALUES(?,?,?)";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, welfareInfo.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, welfareInfo.getWelfareId()));
		para.put(3, new DbParameter(Types.INTEGER, welfareInfo.getStatus()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		welfareInfo.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(WelfareInfo welfareInfo) {
		boolean result = false;
		if (!welfareInfo.beginUpdate())
			return false;
		String sql = "UPDATE tb_u_welfare SET playerId = ?, welfareId = ?, AND status = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, welfareInfo.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, welfareInfo.getWelfareId()));
		para.put(3, new DbParameter(Types.INTEGER, welfareInfo.getStatus()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		welfareInfo.commitUpdate(result);
		return result;
	}

	@Override
	public void addAll(List<WelfareInfo> welfareInfos) {
		for (WelfareInfo welfareInfo : welfareInfos) {
			add(welfareInfo);
		}
	}

	@Override
	public List<WelfareInfo> getWelfareInfosByPlayerId(long playerId) {
		List<WelfareInfo> result = new ArrayList<>();
		String sql = "SELECT * FROM tb_u_welfare WHERE playerId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, playerId));
		PreparedStatement pst = execQuery(sql, para);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			while (rs.next()) {
				WelfareInfo info = new WelfareInfo();
				info.setPlayerId(rs.getLong("playerId"));
				info.setWelfareId(rs.getInt("welfareId"));
				info.setStatus(rs.getInt("status"));
				info.setOp(Option.None);
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

	@Override
	public boolean updateStatus(WelfareInfo welfareInfo) {
		boolean result = false;
		if (!welfareInfo.beginUpdate())
			return false;
		String sql = "UPDATE tb_u_welfare SET status = ? WHERE playerId = ? AND welfareId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, welfareInfo.getStatus()));
		para.put(2, new DbParameter(Types.BIGINT, welfareInfo.getPlayerId()));
		para.put(3, new DbParameter(Types.INTEGER, welfareInfo.getWelfareId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		welfareInfo.commitUpdate(result);
		return result;
	}

	@Override
	public void update(List<WelfareInfo> welfareInfos) {
		for (WelfareInfo welfareInfo : welfareInfos) {
			update(welfareInfo);
		}
	}

	@Override
	public boolean remove(WelfareInfo welfareInfo) {
		boolean result = false;
		
		String sql = "DELETE FROM tb_u_welfare where playerId = ? and welfareId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, welfareInfo.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, welfareInfo.getWelfareId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		return result;
	}

}
