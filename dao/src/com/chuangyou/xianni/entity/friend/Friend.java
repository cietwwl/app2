package com.chuangyou.xianni.entity.friend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.DataObject;


/**
 * 
 * 好友数据结构
 * @author laofan
 *
 */
public class Friend extends DataObject{
	
	/** 角色ID */
	private long roleId;
	/**角色拥有好友的ID,time联系时间,拼接字符串，格式为"xxxx,xxxxxx;xxxxxx,xxxxxx;xxxxxx,xxxxx"  */
	private String friendIdAndTimes = "";
	
	public String getFriendIdAndTimes() {
		return friendIdAndTimes;
	}

	public void setFriendIdAndTimes(String friendIdAndTimes) {
		this.friendIdAndTimes = friendIdAndTimes;
	}


	/** 好友ID与好友间联系时间记录集  */
	private Map<Long,Long> friendIdAndLinkTimeMap = new ConcurrentHashMap<Long,Long>();
	
	public void clear(){
		this.friendIdAndLinkTimeMap.clear();
		this.friendIdAndLinkTimeMap = null;
	}
	
	public Map<Long, Long> getFriendIdAndLinkTimeMap() {
		return friendIdAndLinkTimeMap;
	}


	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/** 加载数据入map */
	public synchronized void loadToMap(){
		if(friendIdAndTimes!=null && !friendIdAndTimes.equals("")){
			String[] items = friendIdAndTimes.split(";");
			for (String str : items) {
				String[] s = str.split(",");
				if(s.length==2){
					friendIdAndLinkTimeMap.put(Long.valueOf(s[0]), Long.valueOf(s[1]));
				}
			}
		}	
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 *  添加好友
	 * @param friendRoleId
	 */
	public synchronized void addFriend(long friendRoleId){
		if(!friendIdAndLinkTimeMap.containsKey(friendRoleId)){
			friendIdAndLinkTimeMap.put(friendRoleId, (long) 0);
			if(friendIdAndTimes.equals("")){
				friendIdAndTimes = friendRoleId + "," + "0";
			}else{
				friendIdAndTimes += ";" + friendRoleId + "," + "0";
			}
		}
	}
	
	/***
	 * 是否是好友
	 * @param roleId
	 * @return
	 */
	public boolean isFriend(long roleId){
		return friendIdAndLinkTimeMap.containsKey(roleId);
	}
	
	public void delFriend(long friendRoleId){
		if(friendIdAndLinkTimeMap.containsKey(friendRoleId)){
			friendIdAndLinkTimeMap.remove(friendRoleId);
			friendIdAndTimes = "";
			Iterator<Map.Entry<Long,Long>> entries = friendIdAndLinkTimeMap.entrySet().iterator();  
			  
			while (entries.hasNext()) {  
			    Map.Entry<Long,Long> entry = entries.next();  
			    friendIdAndTimes +=entry.getKey()+","+entry.getValue()+";";
			}  
			if(friendIdAndTimes.length()>0){
				friendIdAndTimes = friendIdAndTimes.substring(0, friendIdAndTimes.length()-1);
			}
		}
	}
	
	/**
	 * 好友列表
	 * @return
	 */
	public List<Long> toFriendIdList(){
	   return new ArrayList<Long>(friendIdAndLinkTimeMap.keySet());
	}
	
	
	/**
	 * 最近联系人列表（ 有序）
	 * @return
	 */
	public List<Long> toRecentlyLinkmansIdList(){
		List<Long> list = new ArrayList<Long>();
		
		List<Map.Entry<Long, Long>> infoIds = new ArrayList<Map.Entry<Long, Long>>(friendIdAndLinkTimeMap.entrySet());
		Collections.sort(infoIds,new Comparator<Map.Entry<Long, Long>>() {
			public int compare(Map.Entry<Long, Long> o1,Map.Entry<Long, Long> o2){
				//return (int) (o2.getValue() - o1.getValue());
				if(o2.getValue()>o1.getValue()){
					return 1;
				}else if(o2.getValue() < o1.getValue()){
					return -1;
				}else{
					return 0;
				}
			}		
		});
		for (int i = 0; i < infoIds.size(); i++) {
			if(infoIds.get(i).getValue()!=0){
				System.out.println(infoIds.get(i).toString());
				list.add(infoIds.get(i).getKey());
			}
		}
		return list;
	}
	
	
	/**
	 * 添加好友
	 * @param friendRoleId
	 */
	public synchronized void addRecentlyLinkman(long friendRoleId){
		
		if(friendIdAndLinkTimeMap.containsKey(friendRoleId)){
			friendIdAndLinkTimeMap.put(friendRoleId, new Date().getTime());
			String str = "";
			boolean firstFlag = false;
			for(Map.Entry<Long, Long> entry :friendIdAndLinkTimeMap.entrySet()){
				if(!firstFlag){
					str = entry.getKey()+"," + entry.getValue();
					firstFlag = true;
				}else{
					str +=(";" + entry.getKey()+"," + entry.getValue());
				}
			}
			friendIdAndTimes = str;
		}
	}
	
}
