package com.chuangyou.xianni.relation.manager;

import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.relation.RelationInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

public class RelationManager {

	private static RelationManager _ins = new RelationManager();
	
	private ConcurrentHashMap<String, RelationInfo> relationMap = new ConcurrentHashMap<>();
	
	private RelationManager(){
		
	}
	
	public static RelationManager getIns(){
		return _ins;
	}
	
	public RelationInfo getRelation(long playerId1, long playerId2){
		String key = RelationUtil.getKey(playerId1, playerId2);
		return relationMap.get(key);
	}
	
	/**
	 * 添加关系
	 * @param relation
	 */
	public void addRelation(RelationInfo relation){
		relation.setOp(Option.Insert);
		relationMap.put(relation.getMapKey(), relation);
	}
	/**
	 * 删除关系
	 * @param key
	 */
	public void removeRelation(String key){
		RelationInfo info = relationMap.remove(key);
		if(info == null) return;
		DBManager.getRelationInfoDao().removeRelation(info.getPlayerId1(), info.getPlayerId2());
	}
	
	/**
	 * 从库里加载关系
	 * @param relation
	 */
	public void loadRelation(RelationInfo relation){
		relationMap.put(relation.getMapKey(), relation);
	}
	/**
	 * 卸载玩家数据
	 * @param key
	 */
	public void unloadRelation(String key){
		relationMap.remove(key);
	}

	public ConcurrentHashMap<String, RelationInfo> getRelationMap() {
		return relationMap;
	}
}
