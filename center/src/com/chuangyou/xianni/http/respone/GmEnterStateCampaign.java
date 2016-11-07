package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command="enterStateCampaign",desc="直接进入境界副本")
public class GmEnterStateCampaign implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		int stateId = Integer.parseInt(params.get("stateId")); //操作类型
		long playerId = Long.valueOf(params.get("playerId"));
		
		StateConfig stateConfig = StateTemplateMgr.getStates().get(stateId);
		
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE && stateConfig!=null) {
			CreateStateCampaignMsg.Builder msg = CreateStateCampaignMsg.newBuilder();
			msg.setCampaignId(stateConfig.getCampaignID());
			msg.setStateId(stateConfig.getId());
			player.sendPbMessage(MessageUtil.buildMessage(Protocol.S_CREATE_STATE_CAMPAIGN,msg));
		}
		
		return HttpResult.getResult(Code.SUCCESS, "*_*StateGm exec fail*_*");
	
	}

}
