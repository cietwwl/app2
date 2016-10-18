package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.sql.dao.TaskTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

/**
 * 任务模板
 * 
 * @author laofan
 *
 */
public class TaskTemplateImpl extends BaseDao implements TaskTemplateDao {

	@Override
	public Map<Integer, TaskCfg> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_task_info;";
		return read(sql, null);
	}

	private Map<Integer, TaskCfg> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Integer, TaskCfg> infos = null;
		TaskCfg info = null;
		if (pstmt != null) {
			infos = new HashMap<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new TaskCfg();
					info.setTaskId(rs.getInt("taskId"));
					info.setTaskName(rs.getString("taskName"));
					info.setTaskType(rs.getByte("TaskType"));
					info.setTaskLink(rs.getByte("taskLink"));
					info.setTaskLv(rs.getShort("taskLv"));
					info.setTaskPre(rs.getInt("taskPre"));
					info.setTaskNext(rs.getInt("taskNext"));
					info.setAcceptTaskNpcId(rs.getInt("acceptTaskNpcId"));
					info.setAcceptScriptId(rs.getString("acceptScriptId"));
					info.setTaskTarget(rs.getByte("taskTarget"));
					info.setTargetId(rs.getInt("targetId"));
					info.setTargetNum(rs.getInt("targetNum"));
					info.setCompleteScriptId(rs.getString("completeScriptId"));
					info.setExp(rs.getInt("exp"));
					info.setItems(rs.getString("items"));
					info.setXiu(rs.getInt("xiu"));
					info.setMoney(rs.getLong("money"));
					info.setBindCash(rs.getInt("bindCash"));
					info.setCommitNpcId(rs.getInt("commitNpcId"));
					info.setCommitScriptId(rs.getString("commitScriptId"));
					info.setTaskTime(rs.getInt("taskTime"));
					info.setTagPar(rs.getString("tagPar"));
					info.setDropId(rs.getInt("dropId"));
					infos.put(info.getTaskId(), info);
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
