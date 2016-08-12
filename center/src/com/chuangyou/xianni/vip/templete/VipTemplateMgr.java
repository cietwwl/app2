package com.chuangyou.xianni.vip.templete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.vip.VipBagTemplate;
import com.chuangyou.xianni.entity.vip.VipLevelTemplate;
import com.chuangyou.xianni.entity.vip.VipTemplate;
import com.chuangyou.xianni.sql.dao.DBManager;

public class VipTemplateMgr {
	/**
	 * 技能配置数据
	 */
	private static Map<Integer, VipTemplate> vipTemplate = new HashMap<Integer, VipTemplate>();
	private static Map<Integer, VipLevelTemplate> vipLevelTemplate = new HashMap<Integer, VipLevelTemplate>();
	private static Map<Integer, Map<Integer, VipBagTemplate>> vipBagTemplate = new HashMap<Integer, Map<Integer, VipBagTemplate>>();

	public static boolean init() {
		reloadPb();
		return true;
	}

	public static boolean reloadPb() {
		List<VipTemplate> vipTemp = DBManager.getVipTemplateDao().load();
		if (vipTemp != null && vipTemp.size() > 0) {
			for (VipTemplate tem : vipTemp) {
				vipTemplate.put(tem.getId(), tem);
			}
		}

		List<VipLevelTemplate> vipLevelTemp = DBManager.getVipLevelTemplateDao().load();
		if (vipLevelTemp != null && vipLevelTemp.size() > 0) {
			for (VipLevelTemplate tem : vipLevelTemp) {
				vipLevelTemplate.put(tem.getLv(), tem);
			}
		}

		List<VipBagTemplate> vipBagTemp = DBManager.getVipBagTemplateDao().load();
		if (vipBagTemp != null && vipBagTemp.size() > 0) {
			for (VipBagTemplate tem : vipBagTemp) {
				int type = tem.getType();
				if (!vipBagTemplate.containsKey(type)) {
					vipBagTemplate.put(type, new HashMap<Integer, VipBagTemplate>());
				}
				vipBagTemplate.get(type).put(tem.getId(), tem);
			}
		}

		return true;
	}

	public static VipTemplate getVipTemplate(int id) {
		return vipTemplate.get(id);
	}

	public static VipLevelTemplate getVipLevelTemplate(int id) {
		return vipLevelTemplate.get(id);
	}

	public static VipBagTemplate getvipBagTemplate(int type, int id) {
		if (!vipBagTemplate.containsKey(type))
			return null;
		return vipBagTemplate.get(type).get(id);
	}

}
