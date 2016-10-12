package com.chuangyou.xianni.guild.action.baseAction;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

/**
 * 玩家必须是一个帮派的成员才能进行的操作
 * @author Joseph
 *
 */
public abstract class GuildIsGuildMemberAction extends GuildBaseAction {

	public GuildIsGuildMemberAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub


		Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
		if(guild == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.PLAYER_NO_GUILD, packet.getCode(), "玩家还没有加入帮派");
			return;
		}
		
		GuildMemberInfo memberInfo = guild.getMember(player.getPlayerId());
		if(memberInfo == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		execute(player, packet, guild, memberInfo);
	}
	
	public abstract void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception;

}
