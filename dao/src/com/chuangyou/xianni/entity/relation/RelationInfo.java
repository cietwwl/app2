package com.chuangyou.xianni.entity.relation;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class RelationInfo extends DataObject {

	/**
	 * 玩家1ID
	 */
	private long playerId1;
	
	/**
	 * 玩家2ID
	 */
	private long playerId2;
	
	/**
	 * 玩家1和2的关系
	 */
	private short relation1;
	
	/**
	 * 玩家2和1的关系
	 */
	private short relation2;
	
	/**
	 * 玩家1是否加载
	 */
	private boolean loadData1;
	
	/**
	 * 玩家2是否加载
	 */
	private boolean loadData2;
	
	public RelationInfo() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param playerId1
	 * @param playerId2
	 */
	public RelationInfo(long playerId1, long playerId2){
		if(playerId1 < playerId2){
			this.playerId1 = playerId1;
			this.playerId2 = playerId2;
		}
	}

	public long getPlayerId1() {
		return playerId1;
	}

	public void setPlayerId1(long playerId1) {
		this.playerId1 = playerId1;
	}

	public long getPlayerId2() {
		return playerId2;
	}

	public void setPlayerId2(long playerId2) {
		this.playerId2 = playerId2;
	}

	public short getRelation1() {
		return relation1;
	}

	public void setRelation1(short relation1) {
		if(relation1 != this.relation1){
			this.setOp(Option.Update);
			this.relation1 = relation1;
		}
	}

	public short getRelation2() {
		return relation2;
	}

	public void setRelation2(short relation2) {
		if(relation2 != this.relation2){
			this.setOp(Option.Update);
			this.relation2 = relation2;
		}
	}

	public boolean isLoadData1() {
		return loadData1;
	}

	public void setLoadData1(boolean loadData1) {
		this.loadData1 = loadData1;
	}

	public boolean isLoadData2() {
		return loadData2;
	}

	public void setLoadData2(boolean loadData2) {
		this.loadData2 = loadData2;
	}
	
	/**
	 * 设置关系
	 * @param selfId
	 * @param relation
	 */
	public void setRelation(long selfId, short relation){
		if(selfId == playerId1){
			this.setRelation1(relation);
		}else if(selfId == playerId2){
			this.setRelation2(relation);
		}
	}
	/**
	 * 设置玩家是否加载
	 * @param playerId
	 * @param state
	 */
	public void setPlayerState(long playerId, boolean state){
		if(playerId == this.playerId1){
			this.setLoadData1(state);
		}else{
			this.setLoadData2(state);
		}
	}
	/**
	 * 获取缓存数据时的key值
	 * @return
	 */
	public String getMapKey(){
		String key = "";
		if(playerId1 < playerId2){
			key = playerId1 + "_" + playerId2;
		}else{
			key = playerId2 + "_" + playerId1;
		}
		return key;
	}
}
