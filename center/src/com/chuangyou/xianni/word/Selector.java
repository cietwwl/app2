package com.chuangyou.xianni.word;

import com.chuangyou.xianni.player.GamePlayer;

public abstract class Selector {

	public abstract boolean selectorOnline(GamePlayer player);
	
	public abstract boolean selectPlayer(GamePlayer player);
}
