package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseReqProto.GuildWarehouseReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg;
import com.chuangyou.common.util.LanguageSet;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.guild.GuildWarehouseItemInfo;
import com.chuangyou.xianni.guild.GuildWarehouseAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildWarehouseGiveAction extends GuildIsGuildMemberAction {

	public GuildWarehouseGiveAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		GuildJobPowerCfg powerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(powerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "配置错误");
			return;
		}
		
		if(powerCfg.getUseWarehouse() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		GuildWarehouseReqMsg req = GuildWarehouseReqMsg.parseFrom(packet.getBytes());
		
		//检查物品是否足够
		boolean itemEnough = true;
		for(GuildWarehouseItemInfoMsg itemMsg: req.getItemList()){
			int needNum = itemMsg.getAmount() * req.getMemberIdList().size();
			GuildWarehouseItemInfo itemInfo = guild.getItem(itemMsg.getItemTempId());
			if(itemInfo == null){
				itemEnough = false;
				break;
			}
			if(itemInfo.getAmount() < needNum){
				itemEnough = false;
				break;
			}
		}
		
		if(itemEnough == false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_WAREHOUSE_ITEM_UNENOUGH, packet.getCode(), "库存不足");
			return;
		}
		
		//检查要分配的成员id是否有效
		boolean isMember = true;
		for(long memberId: req.getMemberIdList()){
			GuildMemberInfo member = guild.getMember(memberId);
			if(member == null){
				isMember = false;
			}
		}
		if(isMember == false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_MEMBER_INVALID, packet.getCode(), "成员无效");
			return;
		}
		
		GuildWarehouseRespMsg.Builder respMsg = GuildWarehouseRespMsg.newBuilder();
		respMsg.setAction(GuildWarehouseAction.GIVE_TO_MEMBER);
		respMsg.setFlag(2);
		
		String successItems = "";
		//分配 - 扣除物品
		for(GuildWarehouseItemInfoMsg itemMsg: req.getItemList()){
			int needNum = itemMsg.getAmount() * req.getMemberIdList().size();
			GuildWarehouseItemInfo itemInfo = guild.removeItem(itemMsg.getItemTempId(), needNum);
			if(itemInfo == null){
				continue;
			}
			successItems += itemMsg.getItemTempId() + "," + itemMsg.getAmount() + ";";
			
			GuildWarehouseItemInfoMsg.Builder itemRespMsg = GuildWarehouseItemInfoMsg.newBuilder();
			itemRespMsg.setItemTempId(itemInfo.getItemTempId());
			itemRespMsg.setAmount(itemInfo.getAmount());
			respMsg.addItem(itemRespMsg);
		}
		//更新到的仓库物品同步给玩家
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_WAREHOUSE_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		//分配 - 发送邮件给玩家
		String emailTitle = LanguageSet.getResource("CenterServer.guild.emailTitle");
		String emailContent = LanguageSet.getResource("CenterServer.guild.warehouseEmailContent");
		for(long memberId: req.getMemberIdList()){
			EmailManager.insertEmail(memberId, emailTitle, emailContent, successItems);
		}
	}

}
