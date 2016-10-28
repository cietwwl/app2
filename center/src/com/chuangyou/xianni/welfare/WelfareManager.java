package com.chuangyou.xianni.welfare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.welfare.WelfareTemplate;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.WelfareTemplateDao;

public class WelfareManager {
	/**
	 * 配置表所有数据，索引是福利类型
	 */
	private static Map<Integer, List<WelfareTemplate>> config = new HashMap<>();
	

	public static boolean init() {
		reload();
		return true;
	}

	public static void reload() {
		WelfareTemplateDao dao = DBManager.getWelfareTemplateDao();
		List<WelfareTemplate> templates = dao.geTemplates();
		for (WelfareTemplate template : templates) {
			int type = template.getType();
			if (config.containsKey(type)) {
				config.get(type).add(template);
			} else {
				List<WelfareTemplate> list = new ArrayList<>();
				list.add(template);
				config.put(type, list);
			}
		}
	}
	
	public static Map<Integer, List<WelfareTemplate>> getConfig() {
		return config;
	}
	
	public static void setConfig(Map<Integer, List<WelfareTemplate>> config) {
		WelfareManager.config = config;
	}
}
