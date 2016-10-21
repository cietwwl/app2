package com.chuangyou.xianni.task.cmd;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.gather.SearchPrivateMonsterInnerProto.SearchPrivateMonsterInnerMsg;
import com.chuangyou.common.protobuf.pb.task.TaskUpdateRespProto.TaskUpdateRespMsg;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.task.TaskTriggerInfo;
import com.chuangyou.xianni.task.manager.TaskManager;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code=Protocol.C_INNER_SEARCH_MONSTER,desc="查询怪物结果返回")
public class SearchPrivateMonsterCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SearchPrivateMonsterInnerMsg msg = SearchPrivateMonsterInnerMsg.parseFrom(packet.getBytes());
		long playerId = msg.getPlayerId();
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if(msg.getIsExisted()==false){
			if(player!=null && player.getPlayerState() == PlayerState.ONLINE){
				TaskTriggerInfo info = player.getTaskInventory().getTaskInfos().get(msg.getTaskId());
				if(info!=null){
					info.getInfo().setOp(Option.Update);
					info.getInfo().setUpdateTime(new Date());
					info.getInfo().setProcess(0);
					info.getInfo().setState(TaskInfo.UN_ACCEPT);
					
					TaskUpdateRespMsg.Builder notify = TaskUpdateRespMsg.newBuilder();
					notify.setInfo(TaskManager.getTaskMsg(info.getInfo()));
					PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKUPDATE,notify);
					player.sendPbMessage(pkg);
				}
			}
		}
	}

}
