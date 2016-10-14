package com.chuangyou.xianni.rank.logic;

import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.rank.constant.RankType;

public class UpdateRankLogic {
	
	public void updateRank(){
		RankServerManager.getInstance().callProcedure("playerFightRank", RankType.REPAIR, RankType.DAY);
		RankServerManager.getInstance().callProcedure("popularRank", RankType.POPULARITY, RankType.DAY);
		RankServerManager.getInstance().callProcedure("equipRank", RankType.EQUIP, RankType.DAY);
		RankServerManager.getInstance().callProcedure("magicwpRank", RankType.MAGICWP, RankType.DAY);
		RankServerManager.getInstance().callProcedure("mountRank", RankType.MOUNT, RankType.DAY);
		RankServerManager.getInstance().callProcedure("petRank", RankType.PET, RankType.DAY);
		RankServerManager.getInstance().callProcedure("soulRank", RankType.SOUL, RankType.DAY);
		RankServerManager.getInstance().callProcedure("avatarRank", RankType.AVATAR, RankType.DAY);
		RankServerManager.getInstance().callProcedure("stateRank", RankType.STATE, RankType.DAY);
		
		for(int i=1;i<20;i++){
			RankServerManager.getInstance().callProcedure("playerStateRank", RankType.STATE_LV+i, RankType.DAY);
		}
	}
}
