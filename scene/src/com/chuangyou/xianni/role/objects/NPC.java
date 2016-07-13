package com.chuangyou.xianni.role.objects;

import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;

public class NPC extends Living {
	private String name;

	public NPC(long id, String name) {
		super(id);
		setType(RoleType.npc);
		this.name = name;
	}

	public String toString() {
		return "name :" + name + "  id:" + this.getId() + "   npcId :" + this.getSkin();
	}
}
