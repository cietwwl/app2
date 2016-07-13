package com.chuangyou.xianni.task.cmd;

import com.chuangyou.common.protobuf.pb.task.PassFbInnerProto.PassFbInnerMsg;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code=Protocol.C_REQ_PASS_FB,desc="通关副本")
public class PassFbCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PassFbInnerMsg msg = PassFbInnerMsg.parseFrom(packet.getBytes());
		for(long playerId:msg.getPlayersList()){
			GamePlayer player = WorldMgr.getPlayer(playerId);
			if(player!=null && player.getPlayerState() == PlayerState.ONLINE){
				player.notifyListeners(new ObjectEvent(this, msg.getCampaignId(), EventNameType.TASK_PASS_FB));
			}
		}
	}

}
