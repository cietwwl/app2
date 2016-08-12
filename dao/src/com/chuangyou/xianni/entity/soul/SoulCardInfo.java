package com.chuangyou.xianni.entity.soul;

import com.chuangyou.xianni.entity.DataObject;

public class SoulCardInfo extends DataObject {
	private long playerId;//` int(11) NOT NULL AUTO_INCREMENT,
	private long cardId;//` int(11) NOT NULL DEFAULT '0' COMMENT 'cardId',
	private int start;//` int(11) NOT NULL DEFAULT '0' COMMENT '星级',
	private long exp;//` bigint(20) NOT NULL DEFAULT '0' COMMENT '仙阶经验值',
	private int skill1;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int skill2;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int skill3;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int skill4;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
	private int isPutOn;//0：未穿上 1：穿上
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
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
	
}
