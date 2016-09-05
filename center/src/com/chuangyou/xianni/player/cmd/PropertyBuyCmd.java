package com.chuangyou.xianni.player.cmd;

import com.chuangyou.common.protobuf.pb.player.PlayerManaUpdateProto.PlayerManaUpdateMsg;
import com.chuangyou.common.protobuf.pb.player.PropertyBuyProto.PropertyBuyMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.event.PlayerPropertyUpdateEvent;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class PropertyBuyCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PropertyBuyMsg req = PropertyBuyMsg.parseFrom(packet.getBytes());
		
		int priceCount = SystemConfigTemplateMgr.getIntValue("player.mana.addcount");
		int price = SystemConfigTemplateMgr.getIntValue("player.mana.price");
		int needMoney = req.getBuyNum()/priceCount * price;
		
		if(player.getBasePlayer().getPlayerInfo().getCash() < needMoney){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode(), "仙玉不足");
			return;
		}
		
		if(!player.getBasePlayer().consumeCash(needMoney)) return;
		
		PlayerManaUpdateMsg.Builder msg = PlayerManaUpdateMsg.newBuilder();
		msg.setMana(req.getBuyNum());
		
		PBMessage p = MessageUtil.buildMessage(Protocol.S_PLAYER_MANA_UPDATE, msg);
		player.sendPbMessage(p);
	}

}
