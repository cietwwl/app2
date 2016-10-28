package com.chuangyou.xianni.http.respone;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayerList", desc = "玩家查询")
public class GetPlayerList implements BaseRespone {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String account = params.get("account");// 账号名
		String user = params.get("user");// 角色id或角色名
		int userType = 1;// 0角色id查询,1角色名查询
		if (params.get("userType") != null) {
			userType = Integer.valueOf(params.get("userType"));
		}
		Date regBeginTime = null;
		Date regEndTime = null;
		if (params.get("regBeginTime") != null && !params.get("regBeginTime").equals("")) {
			regBeginTime = new Date(Long.valueOf(params.get("regBeginTime")) * 1000);// 注册开始时间
		}
		if (params.get("regEndTime") != null && !params.get("regEndTime").equals("")) {
			regEndTime = new Date(Long.valueOf(params.get("regEndTime")) * 1000);// 注册结束时间
		}
		int startLv = -1;
		if (params.get("startLv") != null) {
			startLv = Integer.valueOf(params.get("startLv"));
		}
		int endLv = -1;
		if (params.get("endLv") != null) {
			endLv = Integer.valueOf(params.get("endLv"));
		}

		int page = 1;
		if (params.get("page") != null) {
			page = Integer.valueOf(params.get("page"));// 当前页
		}
		int pageSize = 20;
		if (params.get("pageSize") != null) {
			pageSize = Integer.valueOf(params.get("pageSize"));// 显示条数
		}
		page = page < 1 ? 1 : page;
		pageSize = pageSize < 1 || pageSize > 40 ? 20 : pageSize;

		try {
			List<PlayerInfo> playerList = DBManager.getPlayerInfoDao().getPlayerList(account, user, userType, regBeginTime, regEndTime, startLv, endLv, page, pageSize);
			Map<String, Object> data = new HashMap<>();
			data.put("total", DBManager.getPlayerInfoDao().getCount(account, user, userType, regBeginTime, regEndTime, startLv, endLv));
			List<Map> list = new ArrayList<>();
			for (PlayerInfo info : playerList) {
				GamePlayer player = WorldMgr.getPlayerFromCache(info.getPlayerId());
				int isOnline = 0;
				if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
					isOnline = 1;
				}
				Map m = new HashMap<>();
				m.put("playerId", info.getPlayerId());
				m.put("account", info.getAccount());
				m.put("nickName", info.getNickName());
				m.put("level", info.getLevel());
				m.put("createTime", info.getCreateTime());
				m.put("viplv", info.getVipLevel());
				m.put("vipExp", info.getVipExp());
				m.put("isOnline", isOnline);
				list.add(m);
			}
			data.put("list", list);
			return HttpResResult.getResult(Code.SUCCESS, data);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResResult.getResult(Code.FAIL, "查询错误");
		}
	}
}