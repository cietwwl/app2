package com.chuangyou.xianni.warfield.spawn;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;

/** 管理且只管理大地图时间范围内刷新的刷怪点 */
public class TimeControlerNodeMgr {
	public static Set<SpwanNode> TM_NODES = new HashSet<>();

	public static void addNode(SpwanNode node) {
		SpawnInfo temp = node.getSpawnInfo();
		if (temp.getTimerType() == 0) {
			return;
		}
		if (StringUtils.isNullOrEmpty(temp.getTimerBegin()) && StringUtils.isNullOrEmpty(temp.getTimerEnd())) {
			return;
		}
		FieldInfo map = FieldTemplateMgr.getFieldTemp(temp.getMapid());
		if (map.getType() != 1) {
			return;
		}
		TM_NODES.add(node);
	}

	public static void check() {
		for (SpwanNode node : TM_NODES) {
			try {
				begin(node);
				end(node);
			} catch (Exception e) {
				Log.error("TimeControlerNodeMgr check error:" + node.getSpwanId(), e);
			}
		}
	}

	private static void begin(SpwanNode node) {
		long difference = difference(node.getTimeControlerTime(), node.getSpawnInfo().getTimerBegin());
		// 今天已经刷新过(5分钟刷新间隔)
		if (difference > -5 * 60 * 1000) {
			return;
		}
		// 刷新
		node.revive();
	}

	private static void end(SpwanNode node) {
		long difference = difference(node.getTimeControlerTime(), node.getSpawnInfo().getTimerEnd());
		// 今天已经结束
		if (difference > -5 * 60 * 1000) {
			return;
		}
		// 结束
		node.forceStop();
	}

	/** 前后五分钟之内 */
	private static long difference(long exeTime, String beginTime) {
		try {
			Date time = TimeUtil.getDate(beginTime);
			return exeTime - time.getTime();
		} catch (Exception e) {
			Log.error("difference" + beginTime, e);
		}
		return 10000000l;
	}
}
