package com.chuangyou.xianni.soul.logic;

import java.util.Iterator;

import com.chuangyou.common.protobuf.pb.soul.GetSoulInfoRespProto.GetSoulInfoRespMsg;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GetSoulInfoLogic {

	
	public void process(GamePlayer player){
		GetSoulInfoRespMsg.Builder resp = GetSoulInfoRespMsg.newBuilder();
		resp.setSoulInfo(player.getSoulInventory().getSoulInfo().getMsg());
		Iterator<SoulCardInfo> it = player.getSoulInventory().getCards().values().iterator();
		while(it.hasNext()){
			resp.addCards(it.next().getMsg());
		}
		
		resp.addAllAtts(player.getSoulInventory().getList());
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_SOUL_INFO, resp);
		player.sendPbMessage(pkg);
	}
}
