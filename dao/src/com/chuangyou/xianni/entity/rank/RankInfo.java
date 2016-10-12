package com.chuangyou.xianni.entity.rank;

import com.chuangyou.common.protobuf.pb.rank.RankInfoProto.RankInfoMsg;
import com.chuangyou.xianni.entity.DataObject;

public class RankInfo extends DataObject {	
	private int rankType;
	private int rankRange;
	private int rank;
	private long playerId;
	
	private String playerName;
	private String paramStr;
	private long param1;
	private long param2;
	private long param3;
	
	
	public RankInfoMsg.Builder getMsg(){
		RankInfoMsg.Builder msg = RankInfoMsg.newBuilder();
		msg.setRank(rank);
		msg.setPlayerId(playerId);
		if(playerName==null){
			playerName = "";
		}
		msg.setPlayerName(playerName);
		if(paramStr!=null){			
			msg.setParamStr(paramStr);
		}
		msg.setParam1(param1);
		msg.setParam2(param2);
		msg.setParam3(param3);
		return msg;
	}
	
	public int getRankType() {
		return rankType;
	}
	public void setRankType(int rankType) {
		this.rankType = rankType;
	}
	public int getRankRange() {
		return rankRange;
	}
	public void setRankRange(int rankRange) {
		this.rankRange = rankRange;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getParamStr() {
		return paramStr;
	}
	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}
	public long getParam1() {
		return param1;
	}
	public void setParam1(long param1) {
		this.param1 = param1;
	}
	public long getParam2() {
		return param2;
	}
	public void setParam2(long param2) {
		this.param2 = param2;
	}
	public long getParam3() {
		return param3;
	}
	public void setParam3(long param3) {
		this.param3 = param3;
	}
	
	
	

}
