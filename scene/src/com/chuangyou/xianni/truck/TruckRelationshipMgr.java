package com.chuangyou.xianni.truck;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 镖车与玩家关系对应表
 * @author wkghost
 *
 */
public class TruckRelationshipMgr {
	/**
	 * 关系对象
	 */
	private static Map<Long, Long>	relationship	= new ConcurrentHashMap<Long, Long>();
	/**
	 * 拥有关系对应
	 */
	private static Map<Long, Long> 	ownership		= new ConcurrentHashMap<Long, Long>();
	
	
	/**
	 * 通过玩家查找镖车ID
	 * @param playerid
	 * @return
	 */
	public static long getRelationTruck(long playerId)
	{
		if(relationship.containsKey(playerId))
			return relationship.get(playerId);
		return 0;		
	}
	
	/**
	 * 建立镖车与玩家之间的关系
	 */
	public static void setRelation(long playerId, long truckId)
	{
		relationship.put(playerId, truckId);
	}
	
	/**
	 * 移除镖车与玩家之间的关系
	 * @param playerId
	 */
	public static void removeRelation(long playerId)
	{
		if(relationship.containsKey(playerId))
			relationship.remove(playerId);
	}

	/**
	 * 设置从属关系
	 */
	public static void setOwnership(long playerId, long truckId)
	{
		ownership.put(playerId, truckId);
	}
	
	/**
	 * 获取Player的镖车
	 * @param playerId
	 * @return
	 */
	public static long getPlayerTruck(long playerId)
	{
		if(ownership.containsKey(playerId))
			return ownership.get(playerId);
		return 0;		
	}
	
	/**
	 * 移除镖车与玩家之间的关系
	 * @param playerId
	 */
	public static void removeOwnership(long playerId)
	{
		if(ownership.containsKey(playerId))
			ownership.remove(playerId);
	}
}
