package com.chuangyou.xianni.avatar.cmd;

import com.chuangyou.common.protobuf.pb.avatar.AvatarOptionMsgProto.AvatarOptionMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_AVATAR_OPTION, desc = "分身操作")
public class AvatarOptionCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		AvatarOptionMsg msg = AvatarOptionMsg.parseFrom(packet.getBytes());
		// 激活分身
		if (msg.getOptionType() == 1) {
			if (player.getAvatarInventory() != null) {
				player.getAvatarInventory().activeAvatar(msg.getAvatarId());
			}
		}
		// 升星
		if (msg.getOptionType() == 2) {
			if (player.getAvatarInventory() != null) {
				player.getAvatarInventory().upStar(msg.getAvatarId());
			}
		}
		// 提升默契
		if (msg.getOptionType() == 3) {
			if (player.getAvatarInventory() != null) {
				player.getAvatarInventory().upCorrespond(msg.getAvatarId());
			}
		}
		// 出战
		if (msg.getOptionType() == 4) {
			if (player.getAvatarInventory() != null) {
				player.getAvatarInventory().fight(msg.getAvatarId(), msg.getParam());
			}
		}
		// 休息
		if (msg.getOptionType() == 5) {
			if (player.getAvatarInventory() != null) {
				player.getAvatarInventory().sleep(msg.getAvatarId());
			}
		}
	}

}
