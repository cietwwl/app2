/**
 * 
 */
package com.chuangyou.xianni.http.respone;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;

/**
 * @author zhanghuibin
 * 
 */
@HttpCmd(command = "test", desc = "测试")
public class TestResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {

			String items = SystemConfigTemplateMgr.getMakeConfig()[1];
			String strs[] = items.split(",");
			Map<Integer, Integer> tempMap = new HashMap<>();
			for (String str : strs) {
				String it[] = str.split("\\*");
				if (it.length == 2) {
					int templateId = Integer.parseInt(it[0]);
					int count = Integer.parseInt(it[1]);
					tempMap.put(templateId, count);
				}
			}
			System.out.println(tempMap);
		
		
		
//		int code = Integer.valueOf(params.get("code"));
//		long playerId = Long.valueOf(params.get("playerId"));
//		GamePlayer player = WorldMgr.getPlayer(playerId);
//		if (code == 0) {
//
//			// 请求创建副本X
//			CreateCampaignMsg.Builder bbbb = CreateCampaignMsg.newBuilder();
//			bbbb.setCampaign(1);
//			bbbb.setUseItem(false);
//
//			// 向场景服务器申请创建副本
//			CreateCampaignAction action = new CreateCampaignAction(player, bbbb.build());
//			player.getActionQueue().enqueue(action);
//		} else {
//			QuitCampaignCmd cmd = new QuitCampaignCmd();
//			cmd.execute(player.getChannel(), new PBMessage(Protocol.C_QUIT_CAMPAIGN, playerId));
//		}

		return HttpResult.getResult(Code.SUCCESS, params.get("param"));
	}
}
