package com.chuangyou.xianni.bag.script;

import com.chuangyou.xianni.script.IScript;

public interface IItemScript extends IScript {

	public boolean useItem(long playerId, int itemTempId, int count);
}
