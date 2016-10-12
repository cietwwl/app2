package com.chuangyou.xianni.guild.action;

import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.guild.manager.GuildManager;

public class GuildCheckOfflineAction extends Action {

	public GuildCheckOfflineAction() {
		super(GuildManager.getIns().getActionQueue());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		GuildManager.getIns().checkGuildOffline();
	}

}
