package com.chuangyou.xianni.relation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.friend.GetFriendsRespProto.GetFriendsRespMsg;
import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.relation.RelationInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.manager.RelationManager;
import com.chuangyou.xianni.relation.manager.RelationProtoUtil;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

public class RelationInventory extends AbstractEvent implements IInventory {
	
	private GamePlayer player;
	
	private Set<Long> relationList;
	
	/** 上一次查找时间    */
	public long lastQueryTime;
	
	public RelationInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * 获取与某玩家的关系
	 * @param playerId
	 * @return
	 */
	public RelationInfo getRelation(long playerId){
		RelationInfo relation = RelationManager.getIns().getRelation(player.getPlayerId(), playerId);
		if(relation != null && !relationList.contains(playerId)){
			relationList.add(playerId);
		}
		if(relation == null && relationList.contains(playerId)){
			relationList.remove(playerId);
		}
		return relation;
	}
	
	/**
	 * 添加跟某玩家的关系
	 * @param playerId
	 * @param relationType
	 */
	public void addRelation(long playerId, short relationType){
		if(this.getRelation(playerId) != null) return;
		
		this.relationList.add(playerId);
		
		RelationInfo relation = new RelationInfo(player.getPlayerId(), playerId);
		relation.setRelation(player.getPlayerId(), relationType);
		relation.setPlayerState(player.getPlayerId(), true);
		
		GamePlayer targetPlayer = WorldMgr.getPlayer(playerId);
		if(targetPlayer.getRelationInventory() != null){
			relation.setPlayerState(playerId, true);
		}else{
			relation.setPlayerState(playerId, false);
		}
		RelationManager.getIns().addRelation(relation);
	}
	
//	/**
//	 * 别人添加我
//	 * @param playerId
//	 */
//	public void playerAddMe(long playerId){
//		if(!this.relationList.contains(playerId)){
//			this.relationList.add(playerId);
//		}
//	}
	
	/**
	 * 更新关系
	 * @param targetId
	 * @param relation
	 */
	public void updateRelation(long targetId, short relation){
		RelationInfo info = RelationManager.getIns().getRelation(player.getPlayerId(), targetId);
		info.setRelation(player.getPlayerId(), relation);
		
		if(info.getRelation1() == PlayerRelationConstant.NONE && info.getRelation2() == PlayerRelationConstant.NONE){
			RelationManager.getIns().removeRelation(info.getMapKey());
			relationList.remove(targetId);
		}
	}
	
	/**
	 * 获取关系列表
	 * @param relationType
	 * @return
	 */
	public List<Long> getRelationIds(short relationType){
		List<Long> idList = new ArrayList<>();
		for(long playerId: relationList){
			RelationInfo relation = RelationManager.getIns().getRelation(player.getPlayerId(), playerId);
			if(relation == null){
				relationList.remove(playerId);
				continue;
			}
			if(relation.getRelationType(player.getPlayerId()) == relationType){
				idList.add(playerId);
			}
		}
		return idList;
	}
	
	/**
	 * 发送关系列表给玩家
	 * @param relationType
	 */
	public void sendRelationList(short relationType){
		List<Long> idList = this.getRelationIds(relationType);
		
		GetFriendsRespMsg.Builder msg = GetFriendsRespMsg.newBuilder();
		
		for(long playerId: idList){
			GamePlayer relationPlayer = WorldMgr.getPlayer(playerId);
			if(relationPlayer != null){
				msg.addFriends(RelationProtoUtil.change(relationPlayer));
			}
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETFRIENDS, msg);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 判断玩家是否是自己的某种关系
	 * @param playerId
	 * @param relationType
	 * @return
	 */
	public boolean isRelationTypeTargetToSelf(long playerId, short relationType){
		RelationInfo relation = this.getRelation(playerId);
		if(relation == null){
			return false;
		}
		return relation.getRelationType(player.getPlayerId()) == relationType;
	}
	/**
	 * 判断自己是否是玩家的某种关系
	 * @param playerId
	 * @param relationType
	 * @return
	 */
	public boolean isRelationTypeSelfToTarget(long playerId, short relationType){
		RelationInfo relation = this.getRelation(playerId);
		if(relation == null){
			return false;
		}
		return relation.getRelationType(playerId) == relationType;
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
				RelationManager.getIns().loadRelation(info);
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
							RelationManager.getIns().unloadRelation(info.getMapKey());
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
				RelationInfo info = this.getRelation(targetId);
				
				if(info == null) continue;
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
