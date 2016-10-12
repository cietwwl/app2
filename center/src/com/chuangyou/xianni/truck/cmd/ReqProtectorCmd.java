package com.chuangyou.xianni.truck.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.truck.ReqProtectorLstProto.ReqProtectorLst;
import com.chuangyou.common.protobuf.pb.truck.RespProtectorLstProto.RespProtectorLst;
import com.chuangyou.common.protobuf.pb.truck.TruckProtectorProto.TruckProtector;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckInventory;
import com.chuangyou.xianni.truck.helper.InvitePlayerHelper;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 请求护镖列表
 * @author wkghost
 *
 */
@Cmd(code = Protocol.C_TRUCK_PROTECTOR, desc = "查询护镖人列表")
public class ReqProtectorCmd extends AbstractCommand{

	/** 好友 */
	private static final int TYPE_FRIEND = 1;
	/** 帮派成员 */
	private static final int TYPE_GUILD = 2;
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqProtectorLst reqPro = ReqProtectorLst.parseFrom(packet.getBytes());
		//好友列表
		List<Long> friends = player.getRelationInventory().getRelationIds(PlayerRelationConstant.FRIEND);
		RespProtectorLst.Builder lst = RespProtectorLst.newBuilder();
		lst.setTruckLd(reqPro.getTruckID());
		if(reqPro.getTrucktype() == TruckInventory.TYPE_P)
		{
			for(int i = 0; i<friends.size(); i++)
			{
				GamePlayer gamePlayer =  WorldMgr.getPlayer(friends.get(i));
				if(InvitePlayerHelper.checkPlayerStatu(gamePlayer))
				{
					lst.addProtectors(getProtector(gamePlayer, TYPE_FRIEND));
				}
			}
		}
		Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
		if(guild != null)
		{
			//帮派列表
			for(long id : guild.getMemberIds())
			{
				if(id == player.getPlayerId()) continue;
				GamePlayer gamePlayer = WorldMgr.getPlayer(id);
				if(InvitePlayerHelper.checkPlayerStatu(gamePlayer))
				{
					
					lst.addProtectors(getProtector(gamePlayer, TYPE_GUILD));
				}
			}
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_PROTECTORLST, lst);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 创建护镖人
	 * @param gamePlayer
	 * @param type
	 * @return
	 */
	private TruckProtector.Builder getProtector(GamePlayer gamePlayer, int type)
	{
		TruckProtector.Builder p = TruckProtector.newBuilder();
		p.setId(gamePlayer.getPlayerId());
		p.setType(type);	//帮众类型
		p.setNickName(gamePlayer.getNickName());
		p.setLevel(gamePlayer.getLevel());
		p.setSkin(gamePlayer.getBasePlayer().getPlayerInfo().getSkinId());
		return p;
	}

}
