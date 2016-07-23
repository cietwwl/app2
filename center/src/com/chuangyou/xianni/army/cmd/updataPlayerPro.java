package com.chuangyou.xianni.army.cmd;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;
import io.netty.channel.Channel;

@Cmd(code = Protocol.C_PLAYER_UPDATA_PRO, desc = "更新玩家属性")
public class updataPlayerPro implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		PlayerAttUpdateMsg msg = PlayerAttUpdateMsg.parseFrom(packet.getBytes());
		GamePlayer player = WorldMgr.getPlayer(msg.getPlayerId());

		if (msg.getAttList() != null) {
			for (PropertyMsg property : msg.getAttList()) {
				if (property.getType() == EnumAttr.PK_VAL.getValue()) {
					player.getBasePlayer().getPlayerInfo().setPkVal((int) property.getTotalPoint());
					player.getBasePlayer().getPlayerInfo().setOp(Option.Update);
				} else if (property.getType() == EnumAttr.Exp.getValue()) {
					player.getBasePlayer().addExp(property.getTotalPoint());
				}
			}
		}
	}

}
