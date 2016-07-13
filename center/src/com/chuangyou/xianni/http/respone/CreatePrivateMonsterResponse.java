package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.gather.CreatePrivateMonsterInnerProto.CreatePrivateMonsterInnerMsg;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

@HttpCmd(command="createMonster",desc="刷个人怪")
public class CreatePrivateMonsterResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		long playerId = Long.valueOf(params.get("playerId"));
		int monsterId = Integer.valueOf(params.get("tempId"));
		
		CreatePrivateMonsterInnerMsg.Builder msg = CreatePrivateMonsterInnerMsg.newBuilder();
		msg.setMonsterId(monsterId);
		msg.setPlayerId(playerId);
		msg.setLeaveTime(60000);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_CREATE_PIRVATE_MONSTER,msg);
		GatewayLinkedSet.send2Server(pkg);
		
		return HttpResult.getResult(Code.SUCCESS, "create private monster success");
	}

}
