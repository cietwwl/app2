package com.chuangyou.xianni.relation.manager;

public class RelationUtil {

	public static String getKey(long playerId1, long playerId2){
		String key = "";
		if(playerId1 < playerId2){
			key = playerId1 + "_" + playerId2;
		}else{
			key = playerId2 + "_" + playerId1;
		}
		return key;
	}
}
