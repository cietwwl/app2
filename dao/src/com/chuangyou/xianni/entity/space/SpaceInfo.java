package com.chuangyou.xianni.entity.space;

import com.chuangyou.common.protobuf.pb.space.GetSpaceInfoRespProto.GetSpaceInfoRespMsg;
import com.chuangyou.common.protobuf.pb.space.NotifySpaceChangeProto.NotifySpaceChangeMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;


/**
 *  空间信息
 * @author laofan
 *
 */
public class SpaceInfo extends DataObject {
	
	/**  允许留言 */
	public static final int ALLOW_MSG = 0;
	/** 禁止留言 */
	public static final int NO_MSG = 1;
	/** 只有好友可以留言   */
	public static final int FRIEND_MSG = 2;
	
	//==============================================
	private long playerId;
	private String face="";
	private String signature="";
	private String city="";
	private String birthday="";
	private int popularity;
	private volatile int gift;
	private int isNoMsg;
	private volatile int likes;
	private volatile int flowers;
	private volatile int eggs;
	private volatile int curCollection;
	private int maxCollection;
	private int isEditBirthday;
	
	
	
	public GetSpaceInfoRespMsg.Builder getInfoMsg(){
		GetSpaceInfoRespMsg.Builder msg = GetSpaceInfoRespMsg.newBuilder();
		msg.setPlayerId(playerId);
		msg.setFace(face);
		msg.setSignature(signature);
		msg.setCity(city);
		msg.setBirthday(birthday);
		msg.setPopularity(popularity);
		msg.setGift(gift);
		msg.setIsNoMsg(isNoMsg);
		msg.setLikes(likes);
		msg.setFlowers(flowers);
		msg.setEggs(eggs);
		msg.setCurCollection(curCollection);
		msg.setMaxCollection(maxCollection);
		msg.setIsEditBirthday(isEditBirthday);
		return msg;
	}
	
	public NotifySpaceChangeMsg.Builder getChangeAttMsg(){
		NotifySpaceChangeMsg.Builder msg = NotifySpaceChangeMsg.newBuilder();
		msg.setPlayerId(playerId);
		msg.setGift(gift);
		msg.setLikes(likes);
		msg.setFlowers(flowers);
		msg.setEggs(eggs);
		msg.setPopularity(popularity);
		return msg;
	}
	
	
	
	public static SpaceInfo Builer(long playerId,String face,int maxCollection){
		SpaceInfo info = new SpaceInfo();
		info.setPlayerId(playerId);
		info.setFace(face);
		info.setSignature("");
		info.setCity("");
		info.setBirthday("");
		info.setPopularity(0);
		info.setGift(0);
		info.setIsNoMsg(0);
		info.setLikes(0);
		info.setFlowers(0);
		info.setEggs(0);
		info.setMaxCollection(maxCollection);
		info.setOp(Option.None);
		return info;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		if(!this.face.equals(face)){
			this.face = face;
			this.setOp(Option.Update);
		}
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		if(!this.signature.equals(signature)){
			this.signature = signature;
			this.setOp(Option.Update);
		}
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if(!this.city.equals(city)){
			this.city = city;
			this.setOp(Option.Update);
		}
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		if(!this.birthday.equals(birthday)){
			this.birthday = birthday;
			this.setOp(Option.Update);
		}
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		if(this.popularity!=popularity){
			this.popularity = popularity;
			this.setOp(Option.Update);
		}
	}
	public int getGift() {
		return gift;
	}
	public void setGift(int gift) {
		if(this.gift!=gift){
			this.gift = gift;
			this.setOp(Option.Update);
		}
	}
	public int getIsNoMsg() {
		return isNoMsg;
	}
	public void setIsNoMsg(int isNoMsg) {
		if(this.isNoMsg!=isNoMsg){
			this.isNoMsg = isNoMsg;
			this.setOp(Option.Update);			
		}
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getFlowers() {
		return flowers;
	}

	public void setFlowers(int flowers) {
		this.flowers = flowers;
	}

	public int getEggs() {
		return eggs;
	}

	public void setEggs(int eggs) {
		this.eggs = eggs;
	}

	public int getCurCollection() {
		return curCollection;
	}

	public void setCurCollection(int curCollection) {
		if(this.curCollection!=curCollection){
			this.curCollection = curCollection;
			this.setOp(Option.Update);			
		}
	}

	public int getMaxCollection() {
		return maxCollection;
	}

	public void setMaxCollection(int maxCollection) {
		this.maxCollection = maxCollection;
	}

	public int getIsEditBirthday() {
		return isEditBirthday;
	}

	public void setIsEditBirthday(int isEditBirthday) {
		this.isEditBirthday = isEditBirthday;
	}
	
}
