package com.chuangyou.xianni.guild.action.baseAction;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

public abstract class GuildBaseAction extends Action {

	private GamePlayer player;
	private PBMessage packet;
	
	public GuildBaseAction(GamePlayer player, PBMessage packet) {
		// TODO Auto-generated constructor stub
		super(GuildManager.getIns().getActionQueue());
		this.player = player;
		this.packet = packet;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(player == null) return;
		if(player.getGuildInventory() == null) return;
		try {
			execute(player, packet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.error("cdoe = " + packet.getCode() + ", has exception :", e);
		}
	}
	
	public abstract void execute(GamePlayer player, PBMessage packet) throws Exception;

}
