package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.vip.PlayerVipReceive;

public interface VipPlayerReceiveDao {

	/**
	 * 插入
	 * 
	 * @param
	 * @return
	 */
	public boolean add(PlayerVipReceive playerVipReceive);

	/**
	 * 更新
	 * 
	 * @param
	 * @return
	 */
	public boolean update(PlayerVipReceive playerVipReceive);

	/**
	 * 获取数据
	 * 
	 * @param playerId
	 * @return
	 */
	public List<PlayerVipReceive> getAll(long playerId);
}
