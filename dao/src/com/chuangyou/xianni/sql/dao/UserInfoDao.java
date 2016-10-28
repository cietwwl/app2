package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.User.UserInfo;

public interface UserInfoDao {
	public UserInfo getUser(String userName);

	public boolean save(UserInfo user);

	public long getMaxId();

	public UserInfo getUser(long userid);
}
