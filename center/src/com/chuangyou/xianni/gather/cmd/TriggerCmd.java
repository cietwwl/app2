package com.chuangyou.xianni.gather.cmd;

import com.chuangyou.common.protobuf.pb.gather.TriggerReqProto.TriggerReqMsg;
import com.chuangyou.xianni.constant.RoleConstants.NpcType;
import com.chuangyou.xianni.entity.spawn.NpcInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.gather.script.ScriptLogic;
import com.chuangyou.xianni.npcDialog.NpcInfoTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code=Protocol.C_REQ_TRIGGER,desc="触发器")
public class TriggerCmd implements Command  {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TriggerReqMsg msg = TriggerReqMsg.parseFrom(packet.getBytes());
		long playerId = msg.getPlayerId();
		int  id = msg.getId();
		NpcInfo info = NpcInfoTemplateMgr.npcInfoTemps.get(id);
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if(player!=null && info!=null){
			if(info.getType() == NpcType.TRIGGER){
				player.notifyListeners(new ObjectEvent(this, id, EventNameType.TASK_TRIGGER));
			}
			ScriptLogic.doScript(info.getScriptId(), player,id);		
		}
	}

	

}
