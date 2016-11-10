package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.welfare.WelfareInfo;

public interface WelfareDao {
	boolean add(WelfareInfo welfareInfo);
	boolean update(WelfareInfo welfareInfo);
	void addAll(List<WelfareInfo> welfareInfo);
	void update(List<WelfareInfo> welfareInfo);
	List<WelfareInfo> getWelfareInfosByPlayerId(long playerId);
	boolean updateStatus(WelfareInfo welfareInfo);
	boolean remove(WelfareInfo welfareInfo);
}
