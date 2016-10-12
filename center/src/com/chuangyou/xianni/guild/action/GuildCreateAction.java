package com.chuangyou.xianni.guild.action;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.guild.GuildCreateReqProto.GuildCreateReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildCreateRespProto.GuildCreateRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildInfoRespProto.GuildInfoRespMsg;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.guild.action.baseAction.GuildNotGuildMemberAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildCreateAction extends GuildNotGuildMemberAction {

	public GuildCreateAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeNoGuild(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GuildCreateReqMsg req = GuildCreateReqMsg.parseFrom(packet.getBytes());
		
		if(!StringUtils.verifyMaxByteLen(req.getGuildName(), 50)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_NAME_MAXLENGTH, packet.getCode(), "帮派名字过长");
			return;
		}
		
		boolean nameRepeat = false;
		Guild guild = null;
		ConcurrentHashMap<Integer, Guild> guildMap = GuildManager.getIns().getGuildMap();
		Iterator<Guild> iterator = guildMap.values().iterator();
		while(iterator.hasNext()){
			guild = iterator.next();
			if(guild.getGuildInfo().getName().equals(req.getGuildName().trim())){
				nameRepeat = true;
				break;
			}
		}
		if(nameRepeat == true){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_NAME_REPEAT, packet.getCode(), "帮派名字重复");
			return;
		}
		
		//消耗物品
		int createLevel = 1;
		if(req.getCreateType() == 1){
			int needMoney = SystemConfigTemplateMgr.getIntValue("guild.create.money");
			if(needMoney > player.getBasePlayer().getPlayerInfo().getMoney()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode(), "金币不足");
				return;
			}
			if(!player.getBasePlayer().consumeMoney(needMoney, ItemRemoveType.GUILD_CREATE)){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
				return;
			}
			
			createLevel = 1;
		}else if(req.getCreateType() == 2){
			int needItem = SystemConfigTemplateMgr.getIntValue("guild.create.item");
			int needNum = SystemConfigTemplateMgr.getIntValue("guild.create.itemNum");
			
			if(player.getBagInventory() == null) return;
			if(player.getBagInventory().getItemCount(needItem) < needNum){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, packet.getCode(), "道具不足");
				return;
			}
			if(!player.getBagInventory().removeItem(needItem, needNum, ItemRemoveType.GUILD_CREATE)){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
				return;
			}
			
			createLevel = 2;
		}
		guild = GuildManager.getIns().createGuild(player, req.getGuildName(), createLevel);
		
		GuildCreateRespMsg.Builder respMsg = GuildCreateRespMsg.newBuilder();
		respMsg.setResult(0);
		GuildInfoRespMsg.Builder infoRespMsg = GuildInfoRespMsg.newBuilder();
		guild.writeProto(infoRespMsg, player.getPlayerId());
		respMsg.setGuildInfo(infoRespMsg.build());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_CREATE, respMsg);
		player.sendPbMessage(pkg);
	}

}
