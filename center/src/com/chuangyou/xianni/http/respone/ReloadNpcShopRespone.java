package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.npcShop.template.NpcShopTemplateMgr;

@HttpCmd(command="reloadNpcShop",desc="重新导NPC商店表")
public class ReloadNpcShopRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		NpcShopTemplateMgr.reloadTemplateData();
		return HttpResult.getResult(Code.SUCCESS, "*_*npcShopConfig reload success*_*");
	}

}
