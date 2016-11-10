package com.chuangyou.xianni.http.respone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.sql.dao.DBManager;

@HttpCmd(command = "getPlayerTask", desc = "查询玩家任务")
public class GetPlayerTaskResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		Map<String, Object> data = new HashMap<>();
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info != null) {
				long playerId = info.getPlayerId();
				Map<Integer, TaskInfo> infos = DBManager.getTaskdao().get(playerId);
				List<Object> list = new ArrayList<>();
				for (Entry<Integer, TaskInfo> entry : infos.entrySet()) {
					HashMap<String, Object> m = new HashMap<>();
					m.put("taskId", entry.getValue().getTaskId());
					m.put("state", entry.getValue().getState());// 状态 0：未接
																// 1：已接进行中
																// 2：完成未提交
																// 3：已经提交
					list.add(m);
				}
				data.put("list", list);
			}
		}
		return HttpResResult.getResult(Code.SUCCESS, data);
	}
}
