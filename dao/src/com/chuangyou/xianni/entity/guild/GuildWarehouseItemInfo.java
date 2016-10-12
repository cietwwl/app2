package com.chuangyou.xianni.entity.guild;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class GuildWarehouseItemInfo extends DataObject {

	/** 帮派id */
	private int guildId;
	
	/** 物品模板ID */
	private int itemTempId;
	
	/** 物品数量 */
	private int amount;

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public int getItemTempId() {
		return itemTempId;
	}

	public void setItemTempId(int itemTempId) {
		this.itemTempId = itemTempId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		if(this.amount != amount){
			this.setOp(Option.Update);
		}
		this.amount = amount;
	}
}
