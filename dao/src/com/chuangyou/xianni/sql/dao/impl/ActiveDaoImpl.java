package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.active.ActiveConfig;
import com.chuangyou.xianni.entity.active.ActiveInfo;
import com.chuangyou.xianni.sql.dao.ActiveDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ActiveDaoImpl extends BaseDao implements ActiveDao {

	@Override
	public Map<Integer, ActiveConfig> getActiveConfig() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_active_config";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		ActiveConfig info = null;
		Map<Integer, ActiveConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new ActiveConfig();
				info.setId(rs.getInt("id"));
				info.setReward(rs.getInt("reward"));
				info.setType(rs.getInt("type"));
				info.setNeedLv(rs.getInt("needLv"));
				info.setTargetId(rs.getInt("targetId"));
				info.setTargetId1(rs.getInt("targetId1"));
				info.setTargetNum(rs.getInt("targetNum"));
				info.setTaskTarget(rs.getByte("taskTarget"));
				info.setTargetTrigger(rs.getInt("targetTrigger"));
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

	@Override
	public boolean addInfo(ActiveInfo info) {
		// TODO Auto-generated method stub
		
		String sql = "insert into tb_u_active_info "
				+ "(activeId,playerId,process,updateTime,createTime,status,rewardTime) values(?,?,?,?,?,?,?)";
		if (!info.beginAdd())
			return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getActiveId()));
		params.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getProcess()));
		params.put(4, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		params.put(5, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
		params.put(6, new DbParameter(Types.INTEGER, info.getStatus()));
		params.put(7, new DbParameter(Types.TIMESTAMP, info.getRewardTime()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public boolean updateInfo(ActiveInfo info) {
		// TODO Auto-generated method stub
		String sql = "update tb_u_active_info set process=?,updateTime=?,createTime=?,"
				+ "status=?,rewardTime=? where activeId=? and playerId=?";
		if (!info.beginUpdate())
			return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getProcess()));
		params.put(2, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		params.put(3, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
		params.put(4, new DbParameter(Types.INTEGER, info.getStatus()));
		params.put(5, new DbParameter(Types.TIMESTAMP, info.getRewardTime()));
		params.put(6, new DbParameter(Types.INTEGER, info.getActiveId()));
		params.put(7, new DbParameter(Types.BIGINT, info.getPlayerId()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
		
	}

	@Override
	public Map<Integer, ActiveInfo> getActiveInfos(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_active_info where playerId="+playerId;
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		ActiveInfo info = null;
		Map<Integer, ActiveInfo> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new ActiveInfo();
				info.setActiveId(rs.getInt("activeId"));
				info.setPlayerId(rs.getLong("playerId"));
				info.setProcess(rs.getInt("process"));
				info.setUpdateTime(rs.getTimestamp("updateTime"));
				info.setCreateTime(rs.getTimestamp("createTime"));
				infos.put(info.getActiveId(), info);
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
