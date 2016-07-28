package com.chuangyou.xianni.task.cmd;

import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.task.TaskChangeMapReqProto.TaskChangeMapReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_TASK_CHANGEMAP,desc="跨地图飞行")
public class TaskChangeMapCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TaskChangeMapReqMsg msg = TaskChangeMapReqMsg.parseFrom(packet.getBytes());
		int mapId = msg.getMapId();
		PBVector3 pos = msg.getPos();
		
		MapProxyManager.changeMap(player.getPlayerId(), mapId, pos.getX(), pos.getY(), pos.getZ());
	
	}

}
