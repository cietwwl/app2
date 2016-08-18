package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.relation.RelationInfo;

public interface RelationInfoDao {

	public List<RelationInfo> getPlayerRelations(long playerId);
	
	public boolean addRelation(RelationInfo relationInfo);
	
	public boolean removeRelation(long playerId1, long playerId2);
	
	public boolean updateRelation(RelationInfo relationInfo);
}
