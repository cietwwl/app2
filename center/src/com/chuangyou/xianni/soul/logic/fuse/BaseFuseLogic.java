package com.chuangyou.xianni.soul.logic.fuse;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg;
import com.chuangyou.common.protobuf.pb.soul.SoulFuseRespProto.SoulFuseRespMsg;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class BaseFuseLogic {

	protected int op;
	/**
	 * 融合位置
	 */
	protected int index;
	protected GamePlayer player;
	protected SoulInfo soulInfo;
	
	public BaseFuseLogic(int op, GamePlayer player,int index) {
		super();
		this.op = op;
		this.player = player;
		this.index = index;
		this.soulInfo = player.getSoulInventory().getSoulInfo();
	}
	
	
	protected void sendResult(GamePlayer player){
		SoulFuseRespMsg.Builder msg =  getMsg(player);
	
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SOUL_FUSE,msg);
		player.sendPbMessage(pkg);		
	}
	
	
	/**
	 * 设置新技能
	 * @param newSkillId
	 */
	protected void setNewSkill(int newSkillId,int color){
		Date now = new Date();
		
		if (index == 1) {
			this.soulInfo.setFuseSkillId1(newSkillId);
			this.soulInfo.setFuseSkillColor1(color);
			this.soulInfo.setFuseSkillCreateTime1(now);
		} else if (index == 2) {
			this.soulInfo.setFuseSkillId2(newSkillId);
			this.soulInfo.setFuseSkillColor2(color);
			this.soulInfo.setFuseSkillCreateTime2(now);
		} else if (index == 3) {
			this.soulInfo.setFuseSkillId3(newSkillId);
			this.soulInfo.setFuseSkillColor3(color);
			this.soulInfo.setFuseSkillCreateTime3(now);
		} else if (index == 4) {
			this.soulInfo.setFuseSkillId4(newSkillId);
			this.soulInfo.setFuseSkillColor4(color);
			this.soulInfo.setFuseSkillCreateTime4(now);
		}
		soulInfo.setOp(Option.Update);
		//同步到scene服务器
		FuseSkillMsg.Builder msg = FuseSkillMsg.newBuilder();
		msg.setIndex(index);
		msg.setColor(color);
		msg.setFuseSkillId(newSkillId);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_REQ_SOUL_FUSESKILL_UPDATE,msg);
		player.sendPbMessage(pkg);
	}
	
	
	protected SoulFuseRespMsg.Builder getMsg(GamePlayer player){
		SoulFuseRespMsg.Builder msg =  SoulFuseRespMsg.newBuilder();
		msg.setOp(op);
		msg.setIndex(index);
		msg.setSoulInfo(player.getSoulInventory().getSoulInfo().getMsg());
		msg.addAllAtts(player.getSoulInventory().getList());
		return msg;
	}
	
	
	
}
