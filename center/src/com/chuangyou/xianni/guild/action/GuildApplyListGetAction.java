package com.chuangyou.xianni.guild.action;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.guild.GuildApplyInfoProto.GuildApplyInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildApplyListProto.GuildApplyListMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.guild.GuildApplyInfo;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class GuildApplyListGetAction extends GuildIsGuildMemberAction {

	public GuildApplyListGetAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) {
		// TODO Auto-generated method stub

		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getApplyHandle() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		ConcurrentHashMap<Long, GuildApplyInfo> applyMap = guild.getApplyMap();
		Iterator<GuildApplyInfo> iterator = applyMap.values().iterator();
		
		GuildApplyListMsg.Builder msg = GuildApplyListMsg.newBuilder();
		while(iterator.hasNext()){
			GuildApplyInfo info = iterator.next();
			GamePlayer applyPlayer = WorldMgr.getPlayer(info.getPlayerId());
			
			GuildApplyInfoMsg.Builder infoMsg = GuildApplyInfoMsg.newBuilder();
			infoMsg.setPlayerId(applyPlayer.getPlayerId());
			infoMsg.setPlayerName(applyPlayer.getNickName());
			infoMsg.setLevel(applyPlayer.getLevel());
			infoMsg.setFight(applyPlayer.getBasePlayer().getPlayerInfo().getFight());
			infoMsg.setIconId(1031001);
			infoMsg.setApplyTime(info.getApplyTime());
			msg.addInfo(infoMsg);
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_APPLY_LIST, msg);
		player.sendPbMessage(pkg);
	}

}
