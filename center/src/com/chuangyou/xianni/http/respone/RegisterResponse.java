package com.chuangyou.xianni.http.respone;

import java.util.Date;
import java.util.Map;

import com.chuangyou.common.util.NetConfigSet;
import com.chuangyou.xianni.entity.User.UserInfo;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.sql.dao.DBManager;

@HttpCmd(command = "register", desc = "账号注册")
public class RegisterResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String name = params.get("userName"); // 用户名
		String password = params.get("password");// 密令

		if (name == null || name.equals("") || password == null || password.equals("")) {
			return HttpResult.getResult(Code.ERROR, "参数错误");
		}
		// TODO 各种校验
		UserInfo other = DBManager.getUserDao().getUser(name);
		if (other != null) {
			return HttpResult.getResult(Code.SUCCESS, other.getUserId());
		}
		// TODO 判断该用户是否已经注册

		// TODO 没有注册，给用户注册

		// TODO 加载用户GamePlayer对象

		UserInfo user = new UserInfo();
		user.setUserId(EntityIdBuilder.userIdBuilder());
		user.setUserName(name);
		user.setPassWord(password);
		user.setCreateTime(new Date());
		user.setServerId(NetConfigSet.server_id);
		user.setServerName(NetConfigSet.server_name);

		DBManager.getUserDao().save(user);

		return HttpResult.getResult(Code.SUCCESS, user.getUserId());
	}

}
