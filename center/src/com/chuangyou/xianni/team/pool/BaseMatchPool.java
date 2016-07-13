package com.chuangyou.xianni.team.pool;

import java.util.LinkedList;

import com.chuangyou.common.protobuf.pb.team.ClearMatchRespProto.ClearMatchRespMsg;

/**
 * 个人匹配池
 * @author laofan
 *
 */
public abstract class BaseMatchPool<T> implements IMatchPool<T>{

	/**
	 * 入池的等待匹配的玩家ID
	 */
	protected LinkedList<T> pools = new LinkedList<>();
	
	/**
	 * 成员入池
	 * @param playerId
	 */
	public void addMember(T playerId){
		synchronized (pools) {
			removeMember(playerId);
			pools.offerFirst(playerId);
		}
	}
	
	/**
	 * 成员从池中删除
	 * @param playerId
	 */
	public void removeMember(T playerId){
		synchronized (pools) {
			int index = pools.indexOf(playerId);
			if(index!=-1){
				pools.remove(index);
			}
		}
	}
	
	/**
	 * 获取第一个元素
	 * @return
	 */
	public T peekFirst(){
		synchronized(pools){
			return pools.peekFirst();
		}
	}
	
	/**
	 * 推出第一个元素
	 * @return
	 */
	public T pollFirst(){
		synchronized(pools){
			return pools.pollFirst();
		}
	}

	public LinkedList<T> getPools() {
		return pools;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<T> getClonePools(){
		synchronized(pools){
			return (LinkedList<T>) pools.clone();
		}
	}
	
	/**
	 * 清空
	 */
	public void clear(){
		synchronized(pools){
			pools.clear();
		}
	}
	
	/**
	 * 清除匹配回应消息
	 * @return
	 */
	public ClearMatchRespMsg.Builder getMsg(){
		return ClearMatchRespMsg.newBuilder();
	}
}
