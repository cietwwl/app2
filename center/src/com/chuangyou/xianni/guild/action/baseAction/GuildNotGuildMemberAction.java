package com.chuangyou.xianni.guild.action.baseAction;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

/**
 * 玩家不在帮派内才能进行的操作
 * @author Joseph
 *
 */
public abstract class GuildNotGuildMemberAction extends GuildBaseAction {

	public GuildNotGuildMemberAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(GuildManager.getIns().getPlayerGuild(player.getPlayerId()) != null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.PLAYER_ALREAD_IN_GUILD, packet.getCode(), "玩家已经在帮派里了");
			return;
		}
		executeNoGuild(player, packet);
	}
	
	public abstract void executeNoGuild(GamePlayer player, PBMessage packet) throws Exception;

}
