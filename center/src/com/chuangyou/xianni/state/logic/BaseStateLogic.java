package com.chuangyou.xianni.state.logic;

import com.chuangyou.common.protobuf.pb.state.StateOpRespProto.StateOpRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.state.template.StateTemplateMgr;

public class BaseStateLogic {

	protected StateConfig getNextStateConfig(GamePlayer player){
		int state = player.getBasePlayer().getPlayerInfo().getStateLv()+1;
		StateConfig stateConfig = StateTemplateMgr.getStates().get(state);
		if(stateConfig == null){
			Log.error("StateConfig：渡节配置表错误："+state);
			return null;
		}
		return stateConfig;
	}
	
	protected void sendResult(GamePlayer player,int op,int param1){
		StateOpRespMsg.Builder resp = StateOpRespMsg.newBuilder();
		resp.setOp(op);
		resp.setParam1(param1);
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_STATE_OP,resp));
	}
}
