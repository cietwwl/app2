package com.chuangyou.xianni.rank.logic;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.rank.RankCfg;
import com.chuangyou.xianni.entity.rank.RankInfo;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.rank.template.RankTempMgr;
import com.chuangyou.xianni.reward.RewardManager;

/**
 * 排行榜发奖逻辑
 * @author laofan
 *
 */
public class RankRewardLogic {

	/**
	 * 发奖
	 */
	public void reward(){
		Map<Integer, RankCfg> rankRewardTypes = RankTempMgr.getRankRewardTypes();
		for (RankCfg cfg : rankRewardTypes.values()) {
			if(cfg.getRewardType()>0){
				List<RankInfo> list= RankServerManager.getInstance().getRanks().get(cfg.getId()+"_1");
				if(list!=null){
					for (RankInfo rankInfo : list) {
						RewardManager.sendRankReward(rankInfo.getPlayerId(), rankInfo.getRank(), cfg.getRewardType(), cfg);
					}
				}
			}
		}
	}
}
