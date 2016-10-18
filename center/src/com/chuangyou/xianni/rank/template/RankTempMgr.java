package com.chuangyou.xianni.rank.template;

import java.util.Iterator;
import java.util.Map;

import com.chuangyou.xianni.entity.rank.RankCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 排行榜发奖模板表
 * @author laofan
 *
 */
public class RankTempMgr {
	
	private static Map<Integer, RankCfg> rankRewardTypes;
	
	public static boolean init() {
		return reloadTemplate();
	}

	public static boolean reloadTemplate() {
		// TODO Auto-generated method stub
		rankRewardTypes = DBManager.getRankDao().getRankRewardTypes();
		
		if(rankRewardTypes!=null && rankRewardTypes.size()>0){
			Iterator<RankCfg> it = rankRewardTypes.values().iterator();
			while(it.hasNext()){
				RankCfg cfg = it.next();
				if(cfg.getRewardType()==0){
					it.remove();
				}
			}
			return true;
		}
		return false;
	}

	public static Map<Integer, RankCfg> getRankRewardTypes() {
		return rankRewardTypes;
	}
	
	
}
