package com.chuangyou.xianni.soul.logic.make;

import com.chuangyou.common.protobuf.pb.soul.MakeSoulRespProto.MakeSoulRespMsg;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class BaseSoulMakeLogic {

	protected int op;
	protected GamePlayer player;
	protected SoulMake soulMake;
	protected SoulInfo soulInfo;
	
	
	public BaseSoulMakeLogic(int op, GamePlayer player) {
		this.op = op;
		this.player = player;
		this.soulMake = this.player.getSoulInventory().getSoulMake();
		soulInfo = this.player.getSoulInventory().getSoulInfo();
	}
	
	
	protected void sendResultMsg(){
		MakeSoulRespMsg.Builder resp = MakeSoulRespMsg.newBuilder();
		resp.setOp(this.op);
		
		resp.setSoulMake(this.soulMake.getMsg());
		resp.setProficiency(this.soulInfo.getProficiency());
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SOUL_MAKE,resp);
		player.sendPbMessage(pkg);
		
	}
	
	
	

}
