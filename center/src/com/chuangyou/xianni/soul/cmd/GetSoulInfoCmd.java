package com.chuangyou.xianni.soul.cmd;

import java.util.Iterator;

import com.chuangyou.common.protobuf.pb.soul.GetSoulInfoRespProto.GetSoulInfoRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulCardPiece;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_GET_SOUL_INFO,desc="获取信息")
public class GetSoulInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetSoulInfoRespMsg.Builder resp = GetSoulInfoRespMsg.newBuilder();
		resp.setSoulInfo(player.getSoulInventory().getSoulInfo().getMsg());
		Iterator<SoulCardInfo> it = player.getSoulInventory().getCards().values().iterator();
		while(it.hasNext()){
			resp.addCards(it.next().getMsg());
		}
		
		Iterator<SoulCardPiece> pit =  player.getSoulInventory().getPieces().values().iterator();
		while(pit.hasNext()){
			resp.addCardPieces(pit.next().getMsg());
		}
		resp.addAllAtts(player.getSoulInventory().getList());
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_SOUL_INFO, resp);
		player.sendPbMessage(pkg);
	}

}
