package com.chuangyou.xianni.entity.soul;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg;
import com.chuangyou.common.protobuf.pb.soul.SoulInfoProto.SoulInfoMsg;
import com.chuangyou.xianni.entity.DataObject;


public class SoulInfo extends DataObject {
	private long playerId;// ` bigint(20) NOT NULL DEFAULT '0',
	private long exp=0;// ` bigint(20) NOT NULL DEFAULT '0' COMMENT '经验值',
	private int card1;// ` int(11) NOT NULL DEFAULT '0',
	private int card2;// ` int(11) NOT NULL DEFAULT '0',
	private int card3;// ` int(11) NOT NULL DEFAULT '0',
	private int card4;// ` int(11) NOT NULL DEFAULT '0',
	private int card5;// ` int(11) NOT NULL DEFAULT '0',
	private int card6;// ` int(11) NOT NULL DEFAULT '0',
	private int card7;// ` int(11) NOT NULL DEFAULT '0',
	private int card8;// ` int(11) NOT NULL DEFAULT '0',

	private int fuseSkillId1;// ` int(11) NOT NULL DEFAULT '0' COMMENT '融合技能',
	private int fuseSkillId2;// ` int(11) NOT NULL DEFAULT '0' COMMENT '融合技能',
	private int fuseSkillId3;// ` int(11) NOT NULL DEFAULT '0' COMMENT '融合技能',
	private int fuseSkillId4;// ` int(11) NOT NULL DEFAULT '0' COMMENT '融合技能',

	private Date fuseSkillCreateTime1=new Date();// ` timestamp NULL DEFAULT NULL COMMENT
										// '创建时间',
	private Date fuseSkillCreateTime2=new Date();// ` timestamp NULL DEFAULT NULL COMMENT
										// '创建时间',
	private Date fuseSkillCreateTime3=new Date();// ` timestamp NULL DEFAULT NULL COMMENT
										// '创建时间',
	private Date fuseSkillCreateTime4=new Date();// ` timestamp NULL DEFAULT NULL COMMENT
	
	private int fuseSkillColor1;
	private int fuseSkillColor2;
	private int fuseSkillColor3;
	private int fuseSkillColor4;
	
										// '创建时间',

	private int proficiency; //材料制作熟练度
	
	public SoulInfoMsg.Builder getMsg(){
		SoulInfoMsg.Builder msg = SoulInfoMsg.newBuilder();
		msg.setExp(exp);
		msg.setCard1(card1);
		msg.setCard2(card2);
		msg.setCard3(card3);
		msg.setCard4(card4);
		msg.setCard5(card5);
		msg.setCard6(card6);
		msg.setCard7(card7);
		msg.setCard8(card8);
		
		FuseSkillMsg.Builder subMsg = FuseSkillMsg.newBuilder();
		subMsg.setFuseSkillId(fuseSkillId1);
		subMsg.setFuseCreateTime(fuseSkillCreateTime1.getTime());
		subMsg.setColor(fuseSkillColor1);
		msg.setFuseSkill1(subMsg);
		
		FuseSkillMsg.Builder subMsg1 = FuseSkillMsg.newBuilder();
		subMsg1.setFuseSkillId(fuseSkillId2);
		subMsg1.setFuseCreateTime(fuseSkillCreateTime2.getTime());
		subMsg1.setColor(fuseSkillColor2);
		msg.setFuseSkill2(subMsg1);
		
		FuseSkillMsg.Builder subMsg2 = FuseSkillMsg.newBuilder();
		subMsg2.setFuseSkillId(fuseSkillId3);
		subMsg2.setFuseCreateTime(fuseSkillCreateTime3.getTime());
		subMsg2.setColor(fuseSkillColor3);
		msg.setFuseSkill3(subMsg2);
		
		FuseSkillMsg.Builder subMsg3 = FuseSkillMsg.newBuilder();
		subMsg3.setFuseSkillId(fuseSkillId4);
		subMsg3.setFuseCreateTime(fuseSkillCreateTime4.getTime());
		subMsg3.setColor(fuseSkillColor4);
		msg.setFuseSkill4(subMsg3);

		msg.setProficiency(proficiency);
		
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

	public int getCard1() {
		return card1;
	}

	public void setCard1(int card1) {
		this.card1 = card1;
	}

	public int getCard2() {
		return card2;
	}

	public void setCard2(int card2) {
		this.card2 = card2;
	}

	public int getCard3() {
		return card3;
	}

	public void setCard3(int card3) {
		this.card3 = card3;
	}

	public int getCard4() {
		return card4;
	}

	public void setCard4(int card4) {
		this.card4 = card4;
	}

	public int getCard5() {
		return card5;
	}

	public void setCard5(int card5) {
		this.card5 = card5;
	}

	public int getCard6() {
		return card6;
	}

	public void setCard6(int card6) {
		this.card6 = card6;
	}

	public int getCard7() {
		return card7;
	}

	public void setCard7(int card7) {
		this.card7 = card7;
	}

	public int getCard8() {
		return card8;
	}

	public void setCard8(int card8) {
		this.card8 = card8;
	}

	public int getFuseSkillId1() {
		return fuseSkillId1;
	}

	public void setFuseSkillId1(int fuseSkillId1) {
		this.fuseSkillId1 = fuseSkillId1;
	}

	public int getFuseSkillId2() {
		return fuseSkillId2;
	}

	public void setFuseSkillId2(int fuseSkillId2) {
		this.fuseSkillId2 = fuseSkillId2;
	}

	public int getFuseSkillId3() {
		return fuseSkillId3;
	}

	public void setFuseSkillId3(int fuseSkillId3) {
		this.fuseSkillId3 = fuseSkillId3;
	}

	public int getFuseSkillId4() {
		return fuseSkillId4;
	}

	public void setFuseSkillId4(int fuseSkillId4) {
		this.fuseSkillId4 = fuseSkillId4;
	}

	public Date getFuseSkillCreateTime1() {
		return fuseSkillCreateTime1;
	}

	public void setFuseSkillCreateTime1(Date fuseSkillCreateTime1) {
		this.fuseSkillCreateTime1 = fuseSkillCreateTime1;
	}

	public Date getFuseSkillCreateTime2() {
		return fuseSkillCreateTime2;
	}

	public void setFuseSkillCreateTime2(Date fuseSkillCreateTime2) {
		this.fuseSkillCreateTime2 = fuseSkillCreateTime2;
	}

	public Date getFuseSkillCreateTime3() {
		return fuseSkillCreateTime3;
	}

	public void setFuseSkillCreateTime3(Date fuseSkillCreateTime3) {
		this.fuseSkillCreateTime3 = fuseSkillCreateTime3;
	}

	public Date getFuseSkillCreateTime4() {
		return fuseSkillCreateTime4;
	}

	public void setFuseSkillCreateTime4(Date fuseSkillCreateTime4) {
		this.fuseSkillCreateTime4 = fuseSkillCreateTime4;
	}

	public int getProficiency() {
		return proficiency;
	}

	public void setProficiency(int proficiency) {
		this.proficiency = proficiency;
	}

	public int getFuseSkillColor1() {
		return fuseSkillColor1;
	}

	public void setFuseSkillColor1(int fuseSkillColor1) {
		this.fuseSkillColor1 = fuseSkillColor1;
	}

	public int getFuseSkillColor2() {
		return fuseSkillColor2;
	}

	public void setFuseSkillColor2(int fuseSkillColor2) {
		this.fuseSkillColor2 = fuseSkillColor2;
	}

	public int getFuseSkillColor3() {
		return fuseSkillColor3;
	}

	public void setFuseSkillColor3(int fuseSkillColor3) {
		this.fuseSkillColor3 = fuseSkillColor3;
	}

	public int getFuseSkillColor4() {
		return fuseSkillColor4;
	}

	public void setFuseSkillColor4(int fuseSkillColor4) {
		this.fuseSkillColor4 = fuseSkillColor4;
	}


}
