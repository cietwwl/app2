package com.chuangyou.xianni.guild.cmd;

import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseReqProto.GuildWarehouseReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.guild.GuildWarehouseAction;
import com.chuangyou.xianni.guild.action.GuildWarehouseAddAction;
import com.chuangyou.xianni.guild.action.GuildWarehouseGiveAction;
import com.chuangyou.xianni.guild.action.GuildWarehouseStockAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_GUILD_WAREHOUSE_REQ, desc = "帮派仓库操作请求")
public class GuildWarehouseReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		GuildWarehouseReqMsg req = GuildWarehouseReqMsg.parseFrom(packet.getBytes());
		
		GuildBaseAction action = null;
		switch(req.getAction()){
			case GuildWarehouseAction.STOCK:
				action = new GuildWarehouseStockAction(player, packet);
				break;
			case GuildWarehouseAction.ADD_TO_STOCK:
				action = new GuildWarehouseAddAction(player, packet);
				break;
			case GuildWarehouseAction.GIVE_TO_MEMBER:
				action = new GuildWarehouseGiveAction(player, packet);
				break;
		}
		action.getActionQueue().enqueue(action);
	}

}
