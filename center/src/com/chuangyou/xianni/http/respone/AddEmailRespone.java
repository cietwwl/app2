package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

/** 添加邮件   */
@HttpCmd(command="addEmail",desc="添加邮件")
public class AddEmailRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		long playerId = 0;
		try{
			playerId = Long.parseLong(params.get("playerId")); // 角色ID
		}catch(Exception e){
			return HttpResult.getResult(Code.ERROR, "参数错误");
		}
		String title = params.get("title");// 邮件标题
		String content = params.get("content");//邮件内容
		String attachment = params.get("attachment");//附件
		
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if(player==null || StringUtils.isNullOrEmpty(title) || StringUtils.isNullOrEmpty(content)){
			return HttpResult.getResult(Code.ERROR, "参数错误");
		}
		if(!StringUtils.isNullOrEmpty(attachment)){
			EmailManager.insertEmail(playerId, title, content,attachment);
		}else{
			EmailManager.insertEmail(playerId, title, content);
		}
		
		return HttpResult.getResult(Code.SUCCESS,"插入邮件成功");
	}

}
