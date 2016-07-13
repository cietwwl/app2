package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.sql.dao.TaskDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

/**
 * 任务DAO
 * @author laofan
 *
 */
public class TaskDaoImpl extends BaseDao implements TaskDao {

	@Override
	public boolean add(TaskInfo info) {
		// TODO Auto-generated method stub
		info.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_task_info (taskId,playerId,process,state,updateTime,createTime)"
				+ " VALUES (?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, info.getTaskId()));
		para.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(3, new DbParameter(Types.INTEGER, info.getProcess()));
		para.put(4, new DbParameter(Types.SMALLINT, info.getState()));
		para.put(5, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		para.put(6, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
	
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		
		return result;
	}

	@Override
	public boolean update(TaskInfo info) {
		// TODO Auto-generated method stub
		info.beginUpdate();
		boolean result =false;
		String sql = "update tb_u_task_info set process=?,state=?,updateTime=?,createTime=? where taskId=? and playerId=?";
		Map<Integer, DbParameter> para= new HashMap<>();
		para.put(1, new DbParameter(Types.INTEGER, info.getProcess()));
		para.put(2, new DbParameter(Types.SMALLINT, info.getState()));
		para.put(3, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		para.put(4, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
		para.put(5, new DbParameter(Types.INTEGER, info.getTaskId()));
		para.put(6, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, para)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}

	
	@Override
	public boolean del(TaskInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_task_info where taskId="+info.getTaskId()+" and playerId="+info.getPlayerId()+";";
		result = execNoneQuery(sql)>-1?true:false;
		return result;
	}

	
	@Override
	public Map<Integer, TaskInfo> get(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_task_info where playerId="+playerId+";";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		Map<Integer, TaskInfo> infos = null;
		TaskInfo info = null;
		if(pstmt!=null){
			infos = new HashMap<>();
			try {
				rs = pstmt.executeQuery();
				while(rs.next()){
					info = new TaskInfo();
					info.setTaskId(rs.getInt("taskId"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setProcess(rs.getInt("process"));
					info.setState(rs.getByte("state"));
					info.setUpdateTime(rs.getTimestamp("updateTime"));
					info.setCreateTime(rs.getTimestamp("createTime"));
					infos.put(info.getTaskId(), info);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				infos = null;
				Log.error("执行出错" + sql, e);
			}finally{
				closeConn(pstmt, rs);
			}
		}		
		return infos;
	}


}
