package com.chuangyou.xianni.battle.cmd;

import com.chuangyou.common.protobuf.pb.army.RobotInfoListProto.RobotInfoListMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_SYNC_AVATAR_DATA, desc = "分身数据重载")
public class SyncAvatarDataCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		RobotInfoListMsg datas = RobotInfoListMsg.parseFrom(packet.getBytes());
		army.loadAvatarData(datas.getRobotDatasList());
	}

}
