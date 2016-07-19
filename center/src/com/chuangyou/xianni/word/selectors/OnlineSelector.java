package com.chuangyou.xianni.word.selectors;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.word.Selector;

public class OnlineSelector extends Selector {

	@Override
	public boolean selectPlayer(GamePlayer player) {
		// TODO Auto-generated method stub
		if(player.getPlayerState() == PlayerState.ONLINE){
			return true;
		}
		return false;
	}

	@Override
	public boolean selectorOnline(GamePlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

}
