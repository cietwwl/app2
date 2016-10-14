package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.rank.logic.UpdateRankLogic;

@HttpCmd(command = "updateRank", desc = "刷新排行榜")
public class UpdateRankREspone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		new UpdateRankLogic().updateRank();
		System.out.println(System.currentTimeMillis() - start);
		return HttpResult.getResult(Code.SUCCESS, "*_*updateRank exec success*_*");
	}

}