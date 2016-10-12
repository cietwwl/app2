package com.chuangyou.xianni.rank;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.rank.RankInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 排行榜数据
 * @author laofan
 *
 */
public class RankServerManager{

	private static final RankServerManager instance = new RankServerManager();
	/**
	 * 榜单数据
	 */
	private Map<String, List<RankInfo>> ranks;
	
	/**
	 * 榜单排名需要的临时数据
	 */
	private Map<Long, RankTempInfo> rankTempInfos;
	
	
	public static RankServerManager getInstance(){
		return instance;
	}
	
	/**
	 * 初始化
	 * @return
	 */
	public boolean init(){
		return loadFromDataBase();
	}
	
	/**
	 * 获取临时数据
	 * @param playerId
	 * @return
	 */
	public RankTempInfo getRankTempInfo(long playerId){
		RankTempInfo info = rankTempInfos.get(playerId);
		if(info == null){
			info = new RankTempInfo();
			info.setPlayerId(playerId);
			info.setOp(Option.Insert);
			rankTempInfos.put(playerId, info);
		}
		return info;
	}
	
	/**
	 * 调存储过程
	 * @param produceName
	 * @param type
	 * @param typeRange
	 * @return
	 */
	public boolean callProcedure(String produceName,int type,int typeRange){
		boolean flag = DBManager.getRankDao().execProcedure(produceName,type,typeRange);
		if(flag){
			String str = type+"_"+typeRange;
			if(ranks!=null){
				if(ranks.containsKey(str)){
					ranks.get(str).clear();
				}
				ranks.put(str, DBManager.getRankDao().getRankListByType(type, typeRange));
			}
		}
		return flag;
	}
	
	//////////////////////////////////////////////////////////////
	//数据落地与同步内存
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		ranks = DBManager.getRankDao().getRanks();	
		rankTempInfos = DBManager.getRankDao().getRankTempInfoMap();
		return true;
	}

	public boolean unloadData() {
		// TODO Auto-generated method stub
		if(ranks!=null){
			ranks.clear();
			ranks = null;
		}
		if(rankTempInfos!=null){
			rankTempInfos.clear();
			rankTempInfos = null;
		}
		return true;
	}

	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(rankTempInfos!=null){
			Iterator<RankTempInfo> it = rankTempInfos.values().iterator();
			while(it.hasNext()){
				RankTempInfo info = it.next();
				if(info.getOp()==Option.Insert){
					DBManager.getRankDao().addTempInfo(info);
				}else if(info.getOp() == Option.Update){
					DBManager.getRankDao().updateTempInfo(info);
				}
			}
		}		
		return true;
	}

	/////////////////////////////////////////////////////////////////////////////
	
	
	public Map<String, List<RankInfo>> getRanks() {
		return ranks;
	}

	public Map<Long, RankTempInfo> getRankTempInfos() {
		return rankTempInfos;
	}

}
