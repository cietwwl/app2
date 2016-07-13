package com.chuangyou.xianni.gather.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.gather.SearchPrivateMonsterInnerProto.SearchPrivateMonsterInnerMsg;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.PrivateMonsterMgr;
import com.chuangyou.xianni.role.objects.PrivateMonster;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

@Cmd(code=Protocol.S_SEARCH_PRIVATE_MONSTER,desc="查询怪物的存在")
public class SearchPrivateMonsterCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SearchPrivateMonsterInnerMsg msg = SearchPrivateMonsterInnerMsg.parseFrom(packet.getBytes());
		long playerId = msg.getPlayerId();
		long monsterId = msg.getMonsterId();
		
		SearchPrivateMonsterInnerMsg.Builder resp = msg.toBuilder();
		resp.setIsExisted(false);
		List<PrivateMonster> list = PrivateMonsterMgr.get(playerId);
		if(list.size()>0){
			for (PrivateMonster privateMonster : list) {
				if(privateMonster.getSkin()==monsterId){
					resp.setIsExisted(true);
					break;
				}
			}
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_INNER_SEARCH_MONSTER,resp);
		GatewayLinkedSet.send2Server(pkg);
	
	}

}
