package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.space.SpaceActionLogInfo;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.entity.space.SpaceMessageInfo;

/**
 *  空间DAO层接口定义
 * @author laofan
 *
 */
public interface SpaceDao {
	
	//=================================空间信息==============================
	/**
	 * 添加数据
	 * @param info
	 * @return
	 */
	public boolean add(SpaceInfo info);
	/**
	 * 更新
	 * @param info
	 * @return
	 */
	public boolean update(SpaceInfo info);
	
	/**
	 * 查询
	 * @param playerId
	 * @return
	 */
	public SpaceInfo get(long playerId);
	//=========================================================================
	//=================================留言相关记录===============================
	public boolean add(SpaceMessageInfo info);
	public Map<Integer, SpaceMessageInfo> getAll(long playerId,int max);
	public boolean del(int id);
	public int getMaxId();
	//=========================================================================
	//================================操作日志===================================
	public boolean add(SpaceActionLogInfo info);
	public List<SpaceActionLogInfo> getActionAll(long playerId,int max);
	public boolean del(SpaceActionLogInfo info);
	//=========================================================================
}
