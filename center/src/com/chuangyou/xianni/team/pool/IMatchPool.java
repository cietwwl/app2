package com.chuangyou.xianni.team.pool;

/**
 * 匹配池
 * @author laofan
 *
 * @param <T>
 */
public interface IMatchPool<T> {

	/**
	 * 成员入池
	 * @param playerId
	 */
	public void addMember(T playerId);
	
	/**
	 * 成员从池中删除
	 * @param playerId
	 */
	public void removeMember(T playerId);
	
	/**
	 * 获取第一个元素
	 * @return
	 */
	public T peekFirst();
	
	/**
	 * 推出第一个元素
	 * @return
	 */
	public T pollFirst();
}
