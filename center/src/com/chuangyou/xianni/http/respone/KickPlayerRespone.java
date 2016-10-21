package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;

@HttpCmd(command = "kickPlayer", desc = "踢玩家下线")
public class KickPlayerRespone implements BaseRespone {
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.valueOf(params.get("playerId"));
		
		
		return HttpResult.getResult(Code.SUCCESS, "kick player success！");
	}

}
