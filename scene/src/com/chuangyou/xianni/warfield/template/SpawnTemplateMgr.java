package com.chuangyou.xianni.warfield.template;

import java.util.HashMap;
import java.util.Map;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SpawnTemplateMgr {

	// 刷新节点配置
	private static Map<Integer, Map<Integer, SpawnInfo>>	spwnInfo				= new HashMap<Integer, Map<Integer, SpawnInfo>>();
	// ID标记与节点ID关系表
	private static Map<Integer, Integer>					tagId_spanId_mapping	= new HashMap<>();

	public static boolean init() {
		return reloadSpawnInfoTemp();
	}

	public static Map<Integer, SpawnInfo> getFieldSpawnInfos(int mapKey) {
		return spwnInfo.get(mapKey);
	}

	public static int getSpwanId(int tagId) {
		if (tagId_spanId_mapping.containsKey(tagId)) {
			return tagId_spanId_mapping.get(tagId);
		} else {
			return 0;
		}
	}

	public static boolean reloadSpawnInfoTemp() {
		Map<Integer, SpawnInfo> spawns = DBManager.getSpawnInfoDao().getAll();
		for (Integer key : spawns.keySet()) {
			SpawnInfo spawn = spawns.get(key);

			// 全部节点
			if (!spwnInfo.containsKey(spawn.getMapid())) {
				spwnInfo.put(spawn.getMapid(), new HashMap<Integer, SpawnInfo>());
			}
			spwnInfo.get(spawn.getMapid()).put(spawn.getId(), spawn);
			tagId_spanId_mapping.put(spawn.getTagId(), spawn.getId());
		}
		return true;
	}
}
