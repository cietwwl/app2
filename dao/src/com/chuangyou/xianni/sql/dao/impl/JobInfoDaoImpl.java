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
import com.chuangyou.xianni.entity.job.JobInfo;
import com.chuangyou.xianni.sql.dao.JobInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class JobInfoDaoImpl extends BaseDao implements JobInfoDao {
	@Override
	public List<JobInfo> getJobInfos() {
		String sqlText = "SELECT  * FROM tb_z_jobinfo;";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<JobInfo> infos = null;
		if (pstmt != null) {
			infos = new ArrayList<JobInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					JobInfo tempInfo = new JobInfo();
					tempInfo.setJobName(rs.getString("JobName"));
					tempInfo.setIntervalUnit(rs.getInt("IntervalUnit"));
					tempInfo.setIntervalData(rs.getInt("IntervalData"));
					tempInfo.setIntervalBegin(rs.getInt("IntervalBegin"));
					tempInfo.setUpdateTime(rs.getTimestamp("UpdateTime"));
					tempInfo.setDesc(rs.getString("Desc"));
					infos.add(tempInfo);
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

	@Override
	public boolean updateJobInfo(JobInfo info) {
		boolean result = false;
		String sql = "UPDATE t_s_job SET `IntervalBegin`=?,`UpdateTime`=? WHERE (`JobName`=?); ";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, info.getIntervalBegin()));
		para.put(2, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		para.put(3, new DbParameter(Types.VARCHAR, info.getJobName()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean addJobInfo(JobInfo info) {
		boolean result = false;
		String sql = " INSERT INTO t_s_job(`JobName`, `IntervalUnit`, `IntervalData`, `IntervalBegin`,`UpdateTime`, `Desc`)  VALUES(?, ?, ?, ?, ?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.VARCHAR, info.getJobName()));
		para.put(2, new DbParameter(Types.INTEGER, info.getIntervalUnit()));
		para.put(3, new DbParameter(Types.INTEGER, info.getIntervalData()));
		para.put(4, new DbParameter(Types.INTEGER, info.getIntervalBegin()));
		para.put(5, new DbParameter(Types.TIMESTAMP, info.getUpdateTime()));
		para.put(6, new DbParameter(Types.VARCHAR, info.getDesc()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		return result;
	}
}
