package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;


@HttpCmd(command="reloadTaskCfg",desc="重导任务模板表")
public class ReloadTaskTemplateRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		TaskTemplateMgr.reloadTemplate();
		return HttpResult.getResult(Code.SUCCESS, "*_*TaskTemplateCfg reload success*_*");
	}

}
