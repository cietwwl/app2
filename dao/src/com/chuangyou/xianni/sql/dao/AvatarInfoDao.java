package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.avatar.AvatarInfo;

public interface AvatarInfoDao {
	public boolean saveOrUpdata(AvatarInfo avatarInfo);

	public List<AvatarInfo> get(long playerId);

	public int getMaxId();
}
