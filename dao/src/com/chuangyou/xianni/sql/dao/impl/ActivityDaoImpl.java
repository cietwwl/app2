package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.activity.ActivityConfig;
import com.chuangyou.xianni.entity.activity.ActivityInfo;
import com.chuangyou.xianni.sql.dao.ActivityDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ActivityDaoImpl extends BaseDao implements ActivityDao {

	@Override
	public Map<Integer, ActivityConfig> getActivityConfig() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_activity_config";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		ActivityConfig info = null;
		Map<Integer, ActivityConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new ActivityConfig();
				info.setId(rs.getInt("id"));
				info.setName(rs.getString("name"));
				info.setNeedLevel(rs.getInt("needLevel"));
				info.setStartTime(rs.getString("startTime"));
				info.setTimeParam(rs.getString("timeParam"));
				info.setTimeType(rs.getInt("timeType"));
				info.setEndTime(rs.getString("endTime"));
				info.setNumLimit(rs.getInt("numLimit"));
				info.setStatu(rs.getInt("isClose"));
				infos.put(info.getId(), info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}
		return infos;
	}

	@Override
	public boolean addInfo(ActivityInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (!info.beginAdd())
			return false;
		String sql = "insert into tb_u_activity_info (id,playerId,remainTime,param)values(?,?,?,?)";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, info.getId()));
		para.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		// para.put(3, new DbParameter(Types.INTEGER, info.getState()));
		para.put(3, new DbParameter(Types.INTEGER, info.getRemainTime()));
		para.put(4, new DbParameter(Types.INTEGER, info.getParam()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean updateInfo(ActivityInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (!info.beginUpdate())
			return false;
		String sql = "update tb_u_activity_info set remainTime=?,param=? where id=? and playerId=?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		// para.put(1, new DbParameter(Types.INTEGER, info.getState()));
		para.put(1, new DbParameter(Types.INTEGER, info.getRemainTime()));
		para.put(2, new DbParameter(Types.INTEGER, info.getParam()));
		para.put(3, new DbParameter(Types.INTEGER, info.getId()));
		para.put(4, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public Map<Integer, ActivityInfo> getInfos(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_activity_info where playerId=" + playerId;
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		ActivityInfo info = null;
		Map<Integer, ActivityInfo> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new ActivityInfo();
				info.setId(rs.getInt("id"));
				info.setPlayerId(rs.getLong("playerId"));
				// info.setState(rs.getInt("state"));
				info.setRemainTime(rs.getInt("remainTime"));
				info.setParam(rs.getInt("param"));
				infos.put(info.getId(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}
		return infos;
	}
}
