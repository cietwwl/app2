package com.chuangyou.xianni.truck.helper;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;

public class InvitePlayerHelper {
	
	/**
	 * 检查玩家状态
	 * @param player
	 * @return
	 */
	public static boolean checkPlayerStatu(GamePlayer player)
	{
		if(player == null) return false;
		//判断是否在线
		if(player.getPlayerState() == PlayerState.OFFLINE) return false;
		//判断是否在副本中			
		if(player.getCurCampaign() > 0) return false;
		return true;
	}
}
