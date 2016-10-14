package com.chuangyou.xianni.player.logic;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.entity.level.LevelUp;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.player.BasePlayer;
import com.chuangyou.xianni.state.template.StateTemplateMgr;

public class PlayerAddExpLogic {

	/**
	 * 获取真正可添加的经验
	 * @param exp
	 * @param player
	 * @return
	 */
	public long getAddExp(long exp,BasePlayer player){
		StateConfig config = StateTemplateMgr.getStates().get(player.getPlayerInfo().getStateLv());
		if(config==null){
			Log.error("查询不到境界配置表："+player.getPlayerInfo().getStateLv()+"playerId:"+player.getPlayerInfo().getPlayerId());
			return 0;
		}
		LevelUp curLevelTemp = LevelUpTempleteMgr.getPlayerLevelUp(player.getPlayerInfo().getLevel());
		if(curLevelTemp == null){
			Log.error("查询不到升级配置表："+player.getPlayerInfo().getLevel()+"playerId:"+player.getPlayerInfo().getPlayerId());
			return 0;
		}
		int maxLv = config.getMaxLevel();
		if(player.getPlayerInfo().getLevel()>=maxLv){
			long realExp = exp/2;
			long curTemp = curLevelTemp.getExp()*4;
			if((player.getPlayerInfo().getExp()+realExp)<=curTemp){
				return realExp;
			}else{
				return curTemp - player.getPlayerInfo().getExp();
			}
		}else{
			return exp;
		}
	}
	
	/**
	 * 判断是否能升级下一级
	 * @param player
	 * @return
	 */
	public boolean isCanUpLevel(BasePlayer player){
		LevelUp curLevelTemp = LevelUpTempleteMgr.getPlayerLevelUp(player.getPlayerInfo().getLevel());
		StateConfig config = StateTemplateMgr.getStates().get(player.getPlayerInfo().getStateLv());
		if(config==null){
			Log.error("查询不到境界配置表："+player.getPlayerInfo().getStateLv()+"playerId:"+player.getPlayerInfo().getPlayerId());
			return false;
		}
		if(curLevelTemp == null){
			Log.error("查询不到升级配置表："+player.getPlayerInfo().getLevel()+"playerId:"+player.getPlayerInfo().getPlayerId());
			return false;
		}
		int maxLv = config.getMaxLevel();
		if(player.getPlayerInfo().getLevel()>=maxLv){
			return false;
		}else{
			if(player.getPlayerInfo().getExp()>=curLevelTemp.getExp()){
				return true;
			}
		}
		return false;
	}
}
