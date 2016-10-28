package com.chuangyou.xianni.role.objects;

import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;

public class NPC extends Living {
	private String name;

	public NPC(long id, String name, SpwanNode node) {
		super(id);
		setType(RoleType.npc);
		this.name = name;
		this.node = node;
	}

	public String toString() {
		return "name :" + name + "  id:" + this.getId() + "   npcId :" + this.getSkin();
	}
}
