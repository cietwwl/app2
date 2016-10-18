package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.rank.RankCfg;
import com.chuangyou.xianni.entity.rank.RankInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;

public interface RankDao {
	public boolean execProcedure(String rankName,int rankType,int rankRange);
	
	public Map<Integer, RankCfg> getRankRewardTypes();
	 
	public List<RankInfo> getRankListByType(int type,int range);
	public Map<String, List<RankInfo>> getRanks();
	
	public boolean addTempInfo(RankTempInfo info);
	public boolean updateTempInfo(RankTempInfo info);
	public Map<Long, RankTempInfo> getRankTempInfoMap();
	
}
 