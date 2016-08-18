package com.chuangyou.xianni.relation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.relation.RelationInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.relation.manager.RelationManager;
import com.chuangyou.xianni.sql.dao.DBManager;

public class RelationInventory extends AbstractEvent implements IInventory {
	
	private GamePlayer player;
	
	private Set<Long> relationList;
	
	public RelationInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * @param targetId
	 * @param relation
	 */
	public void updateRelation(long targetId, short relation){
		if(relation == PlayerRelationConstant.NONE){
			relationList.remove(targetId);
		}
		RelationInfo info = RelationManager.getIns().getRelation(player.getPlayerId(), targetId);
		info.setRelation(player.getPlayerId(), relation);
		
		if(info.getRelation1() == PlayerRelationConstant.NONE && info.getRelation2() == PlayerRelationConstant.NONE){
			RelationManager.getIns().removeRelation(info.getMapKey());
		}
	}

	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		relationList = new HashSet<>();
		List<RelationInfo> list = DBManager.getRelationInfoDao().getPlayerRelations(player.getPlayerId());
		for(RelationInfo info: list){
			RelationInfo cacheInfo = RelationManager.getIns().getRelation(info.getPlayerId1(), info.getPlayerId2());
			if(cacheInfo == null){
				info.setPlayerState(player.getPlayerId(), true);
				RelationManager.getIns().addRelation(info);
			}else{
				cacheInfo.setPlayerState(player.getPlayerId(), true);
			}
			if(info.getPlayerId1() == player.getPlayerId()){
				relationList.add(info.getPlayerId2());
			}else if(info.getPlayerId2() == player.getPlayerId()){
				relationList.add(info.getPlayerId1());
			}
		}
		return true;
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		if(relationList != null){
			
			if(relationList.size() > 0){
				for(long targetId: relationList){
					RelationInfo info = RelationManager.getIns().getRelation(player.getPlayerId(), targetId);
					if(info != null){
						info.setPlayerState(player.getPlayerId(), false);
						
						if(info.isLoadData1() == false && info.isLoadData2() == false){
							RelationManager.getIns().removeRelation(info.getMapKey());
						}
					}
				}
			}
			
			relationList.clear();
		}
		relationList = null;
		
		player = null;
		
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(this.relationList != null & this.relationList.size() > 0){
			for(long targetId: relationList){
				RelationInfo info = RelationManager.getIns().getRelation(player.getPlayerId(), targetId);
				
				short option = info.getOp();
				if(option == Option.Insert){
					DBManager.getRelationInfoDao().addRelation(info);
				}else if(option == Option.Update){
					DBManager.getRelationInfoDao().updateRelation(info);
				}
			}
		}
		return true;
	}

}
