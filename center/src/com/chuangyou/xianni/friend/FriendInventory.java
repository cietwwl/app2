package com.chuangyou.xianni.friend;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 好友数据容器管理
 * @author laofan
 *
 */
public class FriendInventory extends AbstractEvent implements IInventory{

	/**
	 * 玩家
	 */
	private GamePlayer player;
	
	/** 好友 */
	private Friend friend;
	


	/** 上一次查找好友时间    */
	public long lastQueryFriendTime;
	


	public FriendInventory(GamePlayer player) {
		this.player = player;
	}
	
	
	/**
	 * 从DB加载数据
	 * @return
	 */
	public boolean loadFromDataBase(){
		if(player.getPlayerId()== 0){
			Log.error("FriendInventory.loadFromDataBase:playerId为0.错误的数据");
			return false;
		}
		friend = DBManager.getFrienddao().get(player.getPlayerId());
		if(friend==null){
			friend = new Friend();
			friend.setRoleId(player.getPlayerId());
			if(DBManager.getFrienddao().add(friend)==false)return false;
		}
		friend.loadToMap();
		return true;
	}
		
	/** 
	 * 卸载数据
	 * @return
	 */
	public boolean unloadData(){
		//this.friend.clear();
		this.friend = null;
		this.player = null;
		
		return true;
	}
	
	/**
	 * 数据同步数据库
	 * @return
	 */
	public boolean saveToDatabase() {
		if(friend==null)return true;
	
		if(friend.getOp() == Option.Update){
			if(DBManager.getFrienddao().update(friend)==false)return false;
		}
		return true;
	}
	
	
	public Friend getFriend() {
		return friend;
	}
	
	
	

	

}
