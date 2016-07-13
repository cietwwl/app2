package com.chuangyou.xianni.map.cmd;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_SCENE_CREATE_MAP, desc = "scene通知创建一个地图代理器")
public class CreateMapProxyCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		PostionMsg msg = PostionMsg.parseFrom(packet.getBytes());
		int mapKey = msg.getMapKey();
		int mapId = msg.getMapId();

		MapProxyManager.createMapProxy(mapId, mapKey);
	}

}
