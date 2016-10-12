package com.chuangyou.xianni.entity.state;

import java.util.HashMap;
import java.util.Map;

//import io.netty.util.internal.StringUtil;


public class StateConfig {
	
	private int id;
	
	private String name;
	
	private String condition;
	
	private String tickets;
	
	private int campaignID;
	
	private int maxLevel;
	
	private String property;
	
	private int awardItem;

	
	/**
	 * 获取消耗物品
	 * @return
	 */
	public Map<Integer, Integer> getTicketsMap(){
		Map<Integer, Integer> map = new HashMap<>();
		
//		if(!StringUtil.isNullOrEmpty(tickets)){			
//			String strs[] = tickets.split(",");
//			Map<Integer, Integer> tempMap = new HashMap<>();
//			for (String str : strs) {
//				String it[] = str.split("\\*");
//				if (it.length == 2) {
//					int templateId = Integer.parseInt(it[0]);
//					int count = Integer.parseInt(it[1]);
//					tempMap.put(templateId, count);
//				}
//			}
//		}
		
		return map;
	}
	
	public int[] getConditions(){
		String[] str = condition.split(",");
		int[] cons = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			String string = str[i];
			cons[i] = Integer.parseInt(string);
		}
		return cons;
 	}
	
	public int[] getPropertyList(){
		String[] str = property.split(",");
		int[] cons = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			String string = str[i];
			cons[i] = Integer.parseInt(string);
		}
		return cons;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getTickets() {
		return tickets;
	}

	public void setTickets(String tickets) {
		this.tickets = tickets;
	}

	public int getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(int campaignID) {
		this.campaignID = campaignID;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public int getAwardItem() {
		return awardItem;
	}

	public void setAwardItem(int awardItem) {
		this.awardItem = awardItem;
	}
	
	
	
	
}
