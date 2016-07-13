package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.script.manager.ScriptManager;

@HttpCmd(command="reload",desc="重新加载reload数据")
public class ReloadScriptRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		ScriptManager.reLoad();
		
		PBMessage p = MessageUtil.buildMessage(Protocol.S_SCRIPT_RELOAD);
		GatewayLinkedSet.send2Server(p);
		
		return HttpResult.getResult(Code.SUCCESS, "*_*script reload success*_*");
	}

}
