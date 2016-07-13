package com.chuangyou.xianni.sql.logdao.impl;

import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.log.SkillLogInfo;
import com.chuangyou.xianni.sql.db.LogBaseDao;

public class SkillLogDaoImpl extends LogBaseDao {
	public boolean addList(List<SkillLogInfo> logs) {
		if (logs == null || logs.size() == 0) {
			return true;
		}
		int size = logs.size();// 一共有多少条数据
		for (int j = 0; j <= logs.size() / EXEC_COUNT_PRE_BAT; j++) {
			int begin = j * EXEC_COUNT_PRE_BAT;// 当前批次七点
			int end = (j + 1) * EXEC_COUNT_PRE_BAT; // 当前批次终点
			end = end > size ? size : end;
			addListFast(logs, begin, end);
		}
		return true;
	}

	private boolean addListFast(List<SkillLogInfo> messages, int begin, int end) {
		boolean result = false;
		try {
			if (messages == null || messages.size() == 0 || begin == end) {
				return true;
			}
			StringBuffer sqlbuf = new StringBuffer("INSERT INTO tb_log_skilllog(playerId,perSkillId,skillId,skillLV,createDate) VALUES ");
			for (int i = begin; i < end; i++) {
				SkillLogInfo t = messages.get(i);
				String value = "(%s,%s,%s,%s,'%s')";
				value = String.format(value, t.getPlayerId(), t.getPerSkillId(), t.getSkillId(), t.getSkillLV(), TimeUtil.getDateFormat(t.getCreateDate()));
				if (i == end - 1) {
					sqlbuf.append(value + ";");
				} else {
					sqlbuf.append(value + ",");
				}
			}
			result = execNoneQuery(sqlbuf.toString(), null) > -1 ? true : false;
		} catch (Exception e) {
			Log.error("insert into ItemLogInfo erro", e);
		}
		return result;
	}
}
