package com.chuangyou.xianni.chat.manager.action;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.Team;

public class ChatTeamAction extends ChatBaseAction {

	@Override
	public List<Long> getReceivers(GamePlayer sender, ChatMsgInfo sendMsg) {
		// TODO Auto-generated method stub
		Team team = TeamMgr.getPlayerTeam(sender.getPlayerId());
		List<Long> receivers = new ArrayList<>();
		if(team == null){
			receivers.add(sender.getPlayerId());
		}else{
			receivers = team.getPlayers(0);
		}
		return receivers;
	}

	@Override
	protected int getCoolingTime() {
		// TODO Auto-generated method stub
		return ChatConstant.CoolingTime.TEAM;
	}

}
