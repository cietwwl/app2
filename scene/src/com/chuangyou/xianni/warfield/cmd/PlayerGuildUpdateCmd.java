package com.chuangyou.xianni.warfield.cmd;

import com.chuangyou.common.protobuf.pb.player.PlayerGuildInfoProto.PlayerGuildInfoMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_PLAYER_GUILD_UPDATE, desc = "玩家帮派变更")
public class PlayerGuildUpdateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PlayerGuildInfoMsg req = PlayerGuildInfoMsg.parseFrom(packet.getBytes());
		
		if(army.getPlayer() != null && army.getPlayer().getSimpleInfo() != null){
			army.getPlayer().getSimpleInfo().readGuildProto(req);
			
			NotifyNearHelper.notifyGuildChange(army, army.getPlayer().getNears(new PlayerSelectorHelper(army.getPlayer())));
		}
	}

}
