package com.chuangyou.xianni.entity.soul;

import com.chuangyou.common.protobuf.pb.soul.CardInfoProto.CardInfoMsg;
import com.chuangyou.xianni.entity.DataObject;

public class SoulCardInfo extends DataObject {
	
	public static final int ON = 1;
	public static final int OFF = 0;
	
	private long playerId;//` int(11) NOT NULL AUTO_INCREMENT,
	private int cardId;//` int(11) NOT NULL DEFAULT '0' COMMENT 'cardId',
	private int star;//` int(11) NOT NULL DEFAULT '0' COMMENT '星级',
	private long exp;//` bigint(20) NOT NULL DEFAULT '0' COMMENT '仙阶经验值',
	private int skill1;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int skill2;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int skill3;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int skill4;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int isPutOn;//0：未穿上 1：穿上
	private int lv = 1; //等 级
	private int remainTime;//技能洗炼剩余次数
	
	
	public CardInfoMsg.Builder getMsg(){
		CardInfoMsg.Builder msg = CardInfoMsg.newBuilder();
		msg.setCardId(cardId);
		msg.setStar(star);
		msg.setExp(exp);
		msg.setSkill1(skill1);
		msg.setSkill2(skill2);
		msg.setSkill3(skill3);
		msg.setSkill4(skill4);
		msg.setIsPutOn(isPutOn);
		msg.setLv(lv);
		msg.setRemainTime(remainTime);
		return msg;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getExp() {
		return exp;
	}
	public void setExp(long exp) {
		this.exp = exp;
	}
	public int getSkill1() {
		return skill1;
	}
	public void setSkill1(int skill1) {
		this.skill1 = skill1;
	}
	public int getSkill2() {
		return skill2;
	}
	public void setSkill2(int skill2) {
		this.skill2 = skill2;
	}
	public int getSkill3() {
		return skill3;
	}
	public void setSkill3(int skill3) {
		this.skill3 = skill3;
	}
	public int getSkill4() {
		return skill4;
	}
	public void setSkill4(int skill4) {
		this.skill4 = skill4;
	}
	public int getIsPutOn() {
		return isPutOn;
	}
	public void setIsPutOn(int isPutOn) {
		this.isPutOn = isPutOn;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}

	public int getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}
	
}
