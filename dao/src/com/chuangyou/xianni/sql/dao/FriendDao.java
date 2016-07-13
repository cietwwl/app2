package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.friend.Friend;

public interface FriendDao {
	
	/**  添加一条数据    */
	public boolean add(Friend info);
	/**  更新一条数据   */
	public boolean update(Friend info);
	/**  获取好友  */
	public Friend get(long roleId);
}
