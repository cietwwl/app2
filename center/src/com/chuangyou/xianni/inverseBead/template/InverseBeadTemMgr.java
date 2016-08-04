package com.chuangyou.xianni.inverseBead.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.inverseBead.InverseBeadTem;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

public class InverseBeadTemMgr {

	private static Map<Integer, InverseBeadTem> inverseBeadTem = new HashMap<Integer, InverseBeadTem>();
	// ID标记与节点ID关系表
	private static Map<Integer, SpawnInfo> tagId_spanId_mapping = new HashMap<>();

	public static boolean init() {
		reloadTemplateData();
		return true;
	}

	public static boolean reloadTemplateData() {
		List<InverseBeadTem> tem = DBManager.getInversebeadtemdao().load();
		if (tem != null && tem.size() > 0) {
			for (InverseBeadTem t : tem) {
				inverseBeadTem.put(t.getId(), t);
			}
		}
		Map<Integer, SpawnInfo> spawns = DBManager.getSpawnInfoDao().getAll();
		for (SpawnInfo spawn : spawns.values()) {
			tagId_spanId_mapping.put(spawn.getTagId(), spawn);
		}

		return true;
	}

	/**
	 * 获取升级数据模板
	 * 
	 * @param id
	 * @return
	 */
	public static InverseBeadTem getTemp(int id) {
		return inverseBeadTem.get(id);
	}

	/**
	 * 获取怪物刷新数据模板
	 * 
	 * @param id
	 * @return
	 */

	public static SpawnInfo getSpwanId(int tagId) {
		return tagId_spanId_mapping.get(tagId);
	}
}
