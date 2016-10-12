package com.chuangyou.xianni.guild.action;

import java.util.Iterator;

import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.guild.GuildWarehouseItemInfo;
import com.chuangyou.xianni.guild.GuildWarehouseAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildWarehouseStockAction extends GuildIsGuildMemberAction {

	public GuildWarehouseStockAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		Iterator<GuildWarehouseItemInfo> iterator = guild.getItemMap().values().iterator();
		
		GuildWarehouseRespMsg.Builder respMsg = GuildWarehouseRespMsg.newBuilder();
		respMsg.setAction(GuildWarehouseAction.STOCK);
		
		int count = 0;
		boolean hasStartPkg = false;
		while(iterator.hasNext()){
			GuildWarehouseItemInfo item = iterator.next();
			GuildWarehouseItemInfoMsg.Builder itemMsg = GuildWarehouseItemInfoMsg.newBuilder();
			itemMsg.setItemTempId(item.getItemTempId());
			itemMsg.setAmount(item.getAmount());
			respMsg.addItem(itemMsg);
			count ++;
			
			if(count%200 == 0 && iterator.hasNext()){
				if(count <= 200){
					sendListMsg(player, 0, respMsg);
					hasStartPkg = true;
				}else{
					sendListMsg(player, 1, respMsg);
				}
				respMsg = GuildWarehouseRespMsg.newBuilder();
			}
		}
		if(hasStartPkg == false){
			sendListMsg(player, 0, respMsg);
			respMsg = GuildWarehouseRespMsg.newBuilder();
		}
		
		sendListMsg(player, 2, respMsg);
	}
	
	private void sendListMsg(GamePlayer player, int flag, GuildWarehouseRespMsg.Builder msg){
		msg.setAction(GuildWarehouseAction.STOCK);
		msg.setFlag(flag);
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_WAREHOUSE_RESP, msg);
		player.sendPbMessage(respPkg);
	}

}
