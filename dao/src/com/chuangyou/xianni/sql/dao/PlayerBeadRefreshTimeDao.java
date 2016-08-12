package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;

public interface PlayerBeadRefreshTimeDao {

	/**
	 * 插入
	 * 
	 * @param
	 * @return
	 */
	public boolean add(PlayerBeadTimeInfo playerInverseBead);

	/**
	 * 更新
	 * 
	 * @param
	 * @return
	 */
	public boolean update(PlayerBeadTimeInfo playerInverseBead);

	/**
	 * 获取玩家天逆珠数据
	 * 
	 * @param playerId
	 * @return
	 */
	public PlayerBeadTimeInfo get(long playerId);

}
