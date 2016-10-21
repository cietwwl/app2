package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.sql.dao.StateDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class StateDaoImpl extends BaseDao implements StateDao {

	@Override
	public Map<Integer, StateConditionConfig> getStateConditionConfig() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_state_condition";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		StateConditionConfig info = null;
		Map<Integer, StateConditionConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new StateConditionConfig();
				info.setId(rs.getInt("id"));
				info.setTargetType(rs.getInt("targetType"));
				info.setTargetNum(rs.getInt("targetNum"));
				info.setTargetId(rs.getInt("targetId"));
				info.setTargetId1(rs.getInt("targetId1"));
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
	public Map<Integer, StateConfig> getStateConfig() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_state_config";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		StateConfig info = null;
		Map<Integer, StateConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new StateConfig();
				info.setId(rs.getInt("id"));
				info.setName(rs.getString("name"));
				info.setCondition(rs.getString("condition"));
				info.setTickets(rs.getString("tickets"));
				info.setCampaignID(rs.getInt("campaignID"));
				info.setMaxLevel(rs.getInt("maxLevel"));
				info.setProperty(rs.getString("property"));
				info.setAwardItem(rs.getInt("awardItem"));
				info.setEvents(rs.getString("events"));
				info.setEndEvents(rs.getString("endEvents"));
				info.setMaxProcess(rs.getInt("maxProcess"));
				info.setStartEvent(rs.getInt("startEvent"));
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
	public boolean addInfo(StateConditionInfo info) {
		// TODO Auto-generated method stub
		String sql = "insert into tb_u_state_condition (stateId,playerId,process,updateTime,createTime) values(?,?,?,?,?)";
		if (!info.beginAdd())
			return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getStateId()));
		params.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getProcess()));
		params.put(4, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		params.put(5, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
	
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public boolean updateInfo(StateConditionInfo info) {
		// TODO Auto-generated method stub
		String sql = "update tb_u_state_condition set process=?,updateTime=?,createTime=? where stateId=? and playerId=?";
		if (!info.beginUpdate())
			return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getProcess()));
		params.put(2, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		params.put(3, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
		params.put(4, new DbParameter(Types.INTEGER, info.getStateId()));
		params.put(5, new DbParameter(Types.BIGINT, info.getPlayerId()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	
	@Override
	public Map<Integer, StateConditionInfo> getStateConditions(long playerId) {
		// TODO Auto-generated method stub
		
		String sql = "select * from tb_u_state_condition where playerId="+playerId;
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		StateConditionInfo info = null;
		Map<Integer, StateConditionInfo> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new StateConditionInfo();
				info.setStateId(rs.getInt("stateId"));
				info.setPlayerId(rs.getLong("playerId"));
				info.setProcess(rs.getInt("process"));
				info.setUpdateTime(rs.getTimestamp("updateTime"));
				info.setCreateTime(rs.getTimestamp("createTime"));
				infos.put(info.getStateId(), info);
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
	public Map<Integer, ConsumSystemConfig> getConsumConfig() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_consump_system";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		ConsumSystemConfig info = null;
		Map<Integer, ConsumSystemConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new ConsumSystemConfig();
				info.setId(rs.getInt("id"));
				info.setExpendItem(rs.getInt("expendItem"));
				info.setExpendNum(rs.getInt("expendNum"));
				info.setParam1(rs.getInt("param1"));
				info.setParam2(rs.getInt("param2"));
				info.setParam3(rs.getInt("param3"));
				info.setParam4(rs.getInt("param4"));
				info.setParam5(rs.getString("param5"));
				info.setParam6(rs.getString("param6"));
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
	public Map<Integer, StateEventConfig> getStateEventConfig() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_state_event";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		StateEventConfig info = null;
		Map<Integer, StateEventConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new StateEventConfig();
				info.setId(rs.getInt("id"));
				info.setType(rs.getInt("type"));
				info.setCoolDown(rs.getInt("coolDown"));
				info.setComboCd(rs.getInt("comboCd"));
				info.setComboChance(rs.getInt("comboChance"));
				info.setComboEventID(rs.getInt("comboEventID"));
				info.setProcess(rs.getInt("process"));
				info.setQteId(rs.getInt("qteId"));
				info.setQteLimitTime(rs.getInt("qteLimitTime"));
				info.setQteChance(rs.getInt("qteChance"));
				info.setQteSucessEventID(rs.getInt("qteSucessEventID"));
				info.setParam1(rs.getInt("param1"));
				info.setParam2(rs.getInt("param2"));
				info.setParam3(rs.getInt("param3"));
				info.setParam4(rs.getInt("param4"));
				info.setParamStr(rs.getString("paramStr"));
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
