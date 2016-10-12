package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseReqProto.GuildWarehouseReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.guild.GuildWarehouseItemInfo;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.guild.GuildWarehouseAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildWarehouseAddAction extends GuildIsGuildMemberAction {

	public GuildWarehouseAddAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		GuildWarehouseReqMsg req = GuildWarehouseReqMsg.parseFrom(packet.getBytes());
		
		//检查物品是否能存入、检查背包里的物品数量够不够
		boolean itemEnough = true;
		for(GuildWarehouseItemInfoMsg itemMsg: req.getItemList()){
			ItemTemplateInfo itemCfg = ItemManager.findItemTempInfo(itemMsg.getItemTempId());
			if(itemCfg == null){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_IS_NOT_Existed, packet.getCode(), "物品不存在");
				return;
			}
			if(itemCfg.getSaveGuild() == 0){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ITEM_NOT_SAVE_GUILD, packet.getCode(), "物品不能存入帮派仓库");
				return;
			}
			
			int hasCount = player.getBagInventory().getItemCount(BagType.Play, itemMsg.getItemTempId(), BindType.NOBIND);
			if(hasCount < itemMsg.getAmount()){
				itemEnough = false;
				break;
			}
		}
		
		if(itemEnough == false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, packet.getCode(), "道具不足");
			return;
		}
		
		GuildWarehouseRespMsg.Builder respMsg = GuildWarehouseRespMsg.newBuilder();
		respMsg.setAction(GuildWarehouseAction.ADD_TO_STOCK);
		respMsg.setFlag(2);
		for(GuildWarehouseItemInfoMsg itemMsg: req.getItemList()){
			if(!player.getBagInventory().removeItem(BagType.Play, itemMsg.getItemTempId(), itemMsg.getAmount(), BindType.NOBIND, ItemRemoveType.GUILD_WAREHOUSE_ADD)){
				continue;
			}
			GuildWarehouseItemInfo itemInfo = guild.addItem(itemMsg.getItemTempId(), itemMsg.getAmount());
			
			if(itemInfo != null){
				GuildWarehouseItemInfoMsg.Builder itemRespMsg = GuildWarehouseItemInfoMsg.newBuilder();
				itemRespMsg.setItemTempId(itemInfo.getItemTempId());
				itemRespMsg.setAmount(itemInfo.getAmount());
				respMsg.addItem(itemRespMsg);
			}
		}
		
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_WAREHOUSE_RESP, respMsg);
		player.sendPbMessage(respPkg);
	}

}
