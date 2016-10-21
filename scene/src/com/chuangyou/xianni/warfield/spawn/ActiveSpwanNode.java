package com.chuangyou.xianni.warfield.spawn;

import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 接触点
 * 
 * 召唤阵
 * 
 * 
 * 
 */
public class ActiveSpwanNode extends SpwanNode {
	protected int blood; // 节点血量（适用于需要循环开闭的节点，如传送阵）

	public ActiveSpwanNode(SpawnInfo spwanInfo, Field field) {
		super(spwanInfo, field);
	}

	public void start() {
		super.start();
	}

	public void active(ArmyProxy army) {
		super.active(army);
	}

	public void over() {
		super.over();
	}

}
