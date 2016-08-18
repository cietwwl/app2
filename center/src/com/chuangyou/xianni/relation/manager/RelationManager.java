package com.chuangyou.xianni.relation.manager;

import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.relation.RelationInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

public class RelationManager {

	private static RelationManager _ins = new RelationManager();
	
	private ConcurrentHashMap<String, RelationInfo> relationMap = new ConcurrentHashMap<>();
	
	public static RelationManager getIns(){
		return _ins;
	}
	
	public RelationInfo getRelation(long playerId1, long playerId2){
		String key = RelationUtil.getKey(playerId1, playerId2);
		return relationMap.get(key);
	}
	
	public void addRelation(RelationInfo relation){
		relationMap.put(relation.getMapKey(), relation);
	}
	
	public void removeRelation(String key){
		RelationInfo info = relationMap.remove(key);
		if(info == null) return;
		DBManager.getRelationInfoDao().removeRelation(info.getPlayerId1(), info.getPlayerId2());
	}

	public ConcurrentHashMap<String, RelationInfo> getRelationMap() {
		return relationMap;
	}
}
