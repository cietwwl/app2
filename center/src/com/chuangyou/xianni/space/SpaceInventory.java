package com.chuangyou.xianni.space;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

import com.chuangyou.common.protobuf.pb.space.GetSpaceInfoRespProto.GetSpaceInfoRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.space.SpaceActionLogInfo;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.entity.space.SpaceMessageInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;


/**
 *  空间数据管理
 * @author laofan
 *
 */
public class SpaceInventory extends AbstractEvent implements IInventory {
	
	private static final int MAX_ACTION = 100;
	/** 收藏最大扩展上限 */
	public static final int MAX_COLLECTION = 1000;
	/**
	 * 未收藏最大上限
	 */
	public static final int MAX_NO_COLLECTION = 100;
	/** 初始收藏数量  */
	public static final int INIT_COLLECTION = 100;
	
	private GamePlayer player;
	
	/**
	 * 空间信息
	 */
	private SpaceInfo spaceInfo;
	
	/**
	 * 留言
	 */
	private Map<Integer, SpaceMessageInfo> messages;
	
	/**
	 *  操作记录
	 */
	private List<SpaceActionLogInfo> actions;
	
	
	
	public SpaceInventory(GamePlayer player) {
		this.player = player;
	}

	
	/**
	 * 获取留言详情
	 * @return
	 */
	public Map<Integer, SpaceMessageInfo> getMessages() {
		if(messages==null){
			messages = DBManager.getSpaceDao().getAll(player.getPlayerId(),spaceInfo.getMaxCollection()+MAX_NO_COLLECTION);
		}
		return messages;
	}

	
	/**
	 * 获取留言列表
	 * @param type 1:所有  2：收藏的
	 * @return
	 */
	public List<SpaceMessageInfo> getListMessages(int type) {
		List<SpaceMessageInfo> list = new ArrayList<>();
		list.addAll(getMessages().values());
		if(type == 2){
			Iterator<SpaceMessageInfo> it = list.iterator();
			while(it.hasNext()){
				if(it.next().getIsCollection() == SpaceMessageInfo.NO_COLLECTION){
					it.remove();
				}
			}
		}
		return list;
	}
	
	
	/**
	 *  获取操作日志
	 * @return
	 */
	public List<SpaceActionLogInfo> getActions() {
		if(actions == null){
			actions = DBManager.getSpaceDao().getActionAll(player.getPlayerId(),MAX_ACTION);
		}
		synchronized (actions) {	
			List<SpaceActionLogInfo> list = new ArrayList<SpaceActionLogInfo>();
			list.addAll(actions);
			return list;
		}
	}
	
	
	/**
	 * 添加操作日志
	 * @param info
	 * @return
	 */
	public void addAction(SpaceActionLogInfo info){
		if(info.getReceivePlayerId()!=player.getPlayerId())return;
		if(actions == null)getActions();
		synchronized (actions) {	
			if(actions.size()>MAX_ACTION){
				SpaceActionLogInfo temp = actions.remove(actions.size()-1);
				DBManager.getSpaceDao().del(temp);
			}
			info.setOp(Option.Insert);
			actions.add(info);
			DBManager.getSpaceDao().add(info);
		}
	}
	
	
	/**
	 *  删除留言
	 * @param messageId
	 * @return
	 */
	private boolean delMsg(int messageId){
		if(messages.containsKey(messageId)){
			messages.remove(messageId);
		}
		return DBManager.getSpaceDao().del(messageId);
	}
	
	/**
	 * 删除留言
	 * @param info
	 * @return
	 */
	public boolean delMsg(SpaceMessageInfo info){
		info.setOp(Option.Delete);
		boolean b = delMsg(info.getId());
		this.calcCurCollection();
		return b;
	}
	
	/**
	 * 删除某个人的留言
	 * @param playerId
	 * @return
	 */
	public void delPlayerMsg(long playerId){
		Iterator<Entry<Integer, SpaceMessageInfo>> it = messages.entrySet().iterator();
		while(it.hasNext()){
			SpaceMessageInfo info = it.next().getValue();
			if(info.getSendPlayerId() == playerId){
				it.remove();
				delMsg(info.getId());
			}
		}
		this.calcCurCollection();
	}
	
	/**
	 * 删除所有留言
	 * @param flag ：true：包括收藏  false:不包括收藏 
	 */
	public void delAllMsg(boolean flag){
		Iterator<Entry<Integer, SpaceMessageInfo>> it = messages.entrySet().iterator();
		while(it.hasNext()){
			SpaceMessageInfo info = it.next().getValue();
			if(flag | info.getIsCollection() == SpaceMessageInfo.NO_COLLECTION){
				it.remove();
				delMsg(info.getId());
			}
		}
		this.calcCurCollection();
	}
	
	
	
	/**
	 * 消除所有收藏标记
	 */
	public void delAllCollection(){
		Iterator<Entry<Integer, SpaceMessageInfo>> it = messages.entrySet().iterator();
		int count = 0;
		SpaceMessageInfo info = null;
		while(it.hasNext()){
			count++;
			info = it.next().getValue();
			if(info.getIsCollection() == SpaceMessageInfo.COLLECTIONED){
				info.setIsCollection(SpaceMessageInfo.NO_COLLECTION);
			}
			if(count>=MAX_NO_COLLECTION){
				delMsg(info.getId());
			}
		}
		if(info!=null)spaceInfo.setCurCollection(0);
	}
	
	
	/**
	 * 计算当前收藏数量
	 * @return
	 */
	public int calcCurCollection(){
		Iterator<Entry<Integer, SpaceMessageInfo>> it = messages.entrySet().iterator();
		int count = 0;
		SpaceMessageInfo info = null;
		while(it.hasNext()){
			info = it.next().getValue();
			if(info.getIsCollection() == SpaceMessageInfo.COLLECTIONED && info.getOp()!=Option.Delete){
				count++;				
			}
		}
		spaceInfo.setCurCollection(count);
		return spaceInfo.getCurCollection();
	}
	
	/**
	 * 添加留言
	 * @param info
	 * @return
	 */
	public void addMessage(SpaceMessageInfo info){
		if(messages.containsKey(info.getId())){
			Log.error("info.getId()"+info.getId()+"留言ID号重复");
			return;
		}
		
		if(info.getReceivePlayerId() == player.getPlayerId()){
			//如果超出上线。删除最后一条未被收藏的留言
			if(messages.size() > spaceInfo.getMaxCollection() + MAX_NO_COLLECTION){
				ConcurrentSkipListMap<Integer, SpaceMessageInfo> temp = (ConcurrentSkipListMap<Integer, SpaceMessageInfo>) messages;
				Iterator<Integer> it = temp.navigableKeySet().descendingIterator();
				while(it.hasNext()){					
					int key = it.next();
					if(temp.get(key).getIsCollection() == SpaceMessageInfo.NO_COLLECTION){	
						it.remove();
						DBManager.getSpaceDao().del(key);
						break;
					}
				}
			}
			messages.put(info.getId(), info);
			info.setOp(Option.Insert);
			DBManager.getSpaceDao().add(info);
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		this.spaceInfo = DBManager.getSpaceDao().get(player.getPlayerId());
		if(this.spaceInfo == null){
			this.spaceInfo = SpaceInfo.Builer(player.getPlayerId(), player.getBasePlayer().getPlayerInfo().getSkinId()+"",INIT_COLLECTION);
			this.spaceInfo.setOp(Option.Insert);
			DBManager.getSpaceDao().add(spaceInfo);
		}
		return true;
	}

	
	@Override
	public boolean unloadData() {
		saveToDatabase();
		this.spaceInfo = null;
		
		if(messages!=null){			
			messages.clear();
			messages = null;
		}
		
		if(actions!=null){			
			synchronized (actions) {			
				actions.clear();
				actions = null;
			}
		}
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		boolean result = true;
		
		if(this.spaceInfo!=null && this.spaceInfo.getOp()==Option.Update){
			result = result & DBManager.getSpaceDao().update(spaceInfo);
		}
		if(this.spaceInfo!=null && this.spaceInfo.getOp() == Option.Insert){
			result = result & DBManager.getSpaceDao().add(spaceInfo);
		}
		if(messages != null){			
			Iterator<Entry<Integer, SpaceMessageInfo>> it = messages.entrySet().iterator();
			while(it.hasNext()){
				SpaceMessageInfo info = it.next().getValue();
				if(info.getOp() == Option.Insert){
					result = result & DBManager.getSpaceDao().add(info);
				}
			}
		}
		if(actions!=null){	
			synchronized (actions) {
				for (SpaceActionLogInfo spaceActionLogInfo : actions) {
					if(spaceActionLogInfo.getOp() == Option.Insert){
						result = result & DBManager.getSpaceDao().add(spaceActionLogInfo);
					}
				}
			}
		}
		
		return result;
	}


	public SpaceInfo getSpaceInfo() {
		return spaceInfo;
	}
	
	/**
	 * 点赞
	 */
	public void addLikes(){
		synchronized (spaceInfo) {
			spaceInfo.setLikes(spaceInfo.getLikes() + 1);
			spaceInfo.setPopularity(spaceInfo.getPopularity() + 1);
		}
	}
	
	/**
	 * 送花
	 */
	public void addFlower(){
		synchronized (spaceInfo) {
			spaceInfo.setFlowers(spaceInfo.getFlowers()+1);
			spaceInfo.setPopularity(spaceInfo.getPopularity()+10);
		}
	}
	
	/**
	 * 鸡蛋
	 */
	public void addEggs(){
		synchronized (spaceInfo) {		
			spaceInfo.setEggs(spaceInfo.getEggs()+1);
			spaceInfo.setPopularity(spaceInfo.getPopularity() - 10);
			if(spaceInfo.getPopularity()<0){
				spaceInfo.setPopularity(0);
			}
		}
	}
	
	
	/**
	 * 添加礼物
	 */
	private Object giftLock = new Object();	
	public void addGift(int num){
		synchronized (giftLock) {
			spaceInfo.setGift(spaceInfo.getGift()+num);
		}
	}
	
	/**
	 * 消耗礼物
	 */
	public boolean consumeGift(){
		synchronized (giftLock) {
			if(spaceInfo.getGift()<1)return false;
			spaceInfo.setGift(spaceInfo.getGift()-1);
			return true;
		}
	}
	
	/**
	 * 获取空间信息
	 * @return
	 */
	public GetSpaceInfoRespMsg.Builder getSpaceInfoMsg(){
		GetSpaceInfoRespMsg.Builder msg = spaceInfo.getInfoMsg();
		msg.setLevel(player.getLevel());
		msg.setName(player.getNickName());
		msg.setPartnerName("");
		msg.setUnityName("");
		msg.setTotalMsg(getMessages().size());
		return msg;
	}
	
	
}
