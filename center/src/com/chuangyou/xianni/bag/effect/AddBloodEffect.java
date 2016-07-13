package com.chuangyou.xianni.bag.effect;

import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 使用物品增加血量
 */
public class AddBloodEffect extends AbstractUserItem {

	public AddBloodEffect(GamePlayer player, BaseItem baseItem, int count) {
		super(player, baseItem, count);
	}

	@Override
	public boolean execute() {
		return false;
	}

}
