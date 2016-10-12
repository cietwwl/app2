package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;

public interface PlayerInverseBeadDao {
	/**
	 * 插入
	 * 
	 * @param
	 * @return
	 */
	public boolean add(PlayerInverseBead playerInverseBead);

	/**
	 * 更新
	 * 
	 * @param
	 * @return
	 */
	public boolean update(PlayerInverseBead playerInverseBead);

	/**
	 * 获取玩家天逆珠数据
	 * 
	 * @param playerId
	 * @return
	 */
	public Map<Integer, PlayerInverseBead> getAll(long playerId);
}
