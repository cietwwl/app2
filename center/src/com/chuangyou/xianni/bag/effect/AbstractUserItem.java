package com.chuangyou.xianni.bag.effect;

import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 使用物品生效抽象类
 */
public abstract class AbstractUserItem {
	protected GamePlayer	player;
	protected BaseItem		baseItem;
	protected int			count;

	public AbstractUserItem(GamePlayer player, BaseItem baseItem, int count) {
		this.player = player;
		this.baseItem = baseItem;
		this.count = count;
	}

	public BaseItem getBaseItem() {
		return this.baseItem;
	}

	public GamePlayer getPlayer() {
		return this.player;
	}

	public abstract boolean execute();

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
