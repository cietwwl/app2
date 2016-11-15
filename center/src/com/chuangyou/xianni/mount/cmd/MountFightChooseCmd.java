package com.chuangyou.xianni.mount.cmd;

import com.chuangyou.common.protobuf.pb.mount.MountFightChooseReqProto.MountFightChooseReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MOUNT_FIGHT_CHOOSE, desc = "选择出战坐骑")
public class MountFightChooseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MountFightChooseReqMsg req = MountFightChooseReqMsg.parseFrom(packet.getBytes());
		
		int mountId = req.getMountId();
		
		if(player.getMountInventory() != null){
			player.getMountInventory().mountFight(mountId, true);
		}
	}
}
