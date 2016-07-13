package com.chuangyou.xianni.login.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.role.RoleConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 配置
 * 
 * @author Administrator
 */
public class roleConfigMgr {
	/**
	 * 技能配置数据
	 */
	private static Map<Integer, RoleConfig> roleConfigMap = new HashMap<Integer, RoleConfig>();

	public static boolean init() {
		reloadPb();
		return true;
	}

	public static boolean reloadPb() {
		// 加载角色配置
		List<RoleConfig> roleConfigs = DBManager.getroleConfigDao().load();
		if (roleConfigs != null && roleConfigs.size() > 0) {
			for (RoleConfig role : roleConfigs) {
				roleConfigMap.put(role.getId(), role);
			}
		}

		return true;
	}

	/**
	 * 获取角色模板
	 * 
	 * @param templateId
	 * @return
	 */
	public static RoleConfig getRoleConfig(int templateId) {
		if (roleConfigMap.containsKey(templateId)) {
			return roleConfigMap.get(templateId);
		}
		return null;
	}

}
