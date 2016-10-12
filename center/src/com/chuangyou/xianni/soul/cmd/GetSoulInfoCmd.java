package com.chuangyou.xianni.soul.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.soul.logic.GetSoulInfoLogic;

@Cmd(code=Protocol.C_REQ_GET_SOUL_INFO,desc="获取信息")
public class GetSoulInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		new GetSoulInfoLogic().process(player);
	}

}
