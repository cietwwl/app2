/**
 * 
 */
package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignMsgProto.CreateCampaignMsg;
import com.chuangyou.xianni.campaign.action.CreateCampaignAction;
import com.chuangyou.xianni.campaign.cmd.QuitCampaignCmd;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * @author zhanghuibin
 * 
 */
@HttpCmd(command = "test", desc = "测试")
public class TestResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {

		int code = Integer.valueOf(params.get("code"));
		long playerId = Long.valueOf(params.get("playerId"));
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (code == 0) {

			// 请求创建副本X
			CreateCampaignMsg.Builder bbbb = CreateCampaignMsg.newBuilder();
			bbbb.setCampaign(1);
			bbbb.setUseItem(false);

			// 向场景服务器申请创建副本
			CreateCampaignAction action = new CreateCampaignAction(player, bbbb.build());
			player.getActionQueue().enqueue(action);
		} else {
			QuitCampaignCmd cmd = new QuitCampaignCmd();
			cmd.execute(player.getChannel(), new PBMessage(Protocol.C_QUIT_CAMPAIGN, playerId));
		}

		return HttpResult.getResult(Code.SUCCESS, params.get("param"));
	}
}
