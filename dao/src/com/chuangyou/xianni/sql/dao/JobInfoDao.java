package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.job.JobInfo;

public interface JobInfoDao {
	public List<JobInfo> getJobInfos();

	public boolean addJobInfo(JobInfo info);

	public boolean updateJobInfo(JobInfo info);
}
