package com.chuangyou.xianni.gather.script;

import com.chuangyou.xianni.script.IScript;

/**
 * 采集物+触发器类型触发脚本
 * @author laofan
 *
 */
public interface IGatherScript extends IScript {

	/**
	 * 
	 * @param playerId
	 * @param id
	 */
	public void doScript(long playerId,int id);
}
