package com.chuangyou.xianni.soul.logic.make;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class SoulMakeTaskLogic {

	/**
	 * 同步制作任务
	 * @param player
	 */
	public void syncSoulMakeTask(GamePlayer player){	
		if(player.getPlayerState() == PlayerState.OFFLINE)return;
		if(player.getSoulInventory().getSoulMake() != null){
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SOUL_MAKE_TASK,player.getSoulInventory().getSoulMake().getMsg());
			player.sendPbMessage(pkg);
		}
	}
}
