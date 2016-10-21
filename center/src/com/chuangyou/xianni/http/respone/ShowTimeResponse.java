package com.chuangyou.xianni.http.respone;

import java.util.Date;
import java.util.Map;

import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;

@HttpCmd(command = "showTime", desc = "显示时间")
public class ShowTimeResponse implements BaseRespone {
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		return HttpResult.getResult(Code.SUCCESS, TimeUtil.getDateFormat(new Date()));
	}

}
