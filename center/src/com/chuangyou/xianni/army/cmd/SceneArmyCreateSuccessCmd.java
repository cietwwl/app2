package com.chuangyou.xianni.army.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.ChangeLineAction;

@Cmd(code = Protocol.C_SCENE_LOGIN_SUCCESS, desc = "部队创建成功")
public class SceneArmyCreateSuccessCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ChangeLineAction action = new ChangeLineAction(player, null, true);
		action.getActionQueue().enqueue(action);
		
		InverseBeadManager.syncSpawn(player);
		if (player.getAvatarInventory() != null) {
			player.getAvatarInventory().writeAvatarMsg2Scene();
		}

	}

}
