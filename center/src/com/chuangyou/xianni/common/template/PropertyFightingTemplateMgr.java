package com.chuangyou.xianni.common.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.property.PropertyFightingTemplate;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.PropertyFightingTemplateDao;

public class PropertyFightingTemplateMgr {
	public static Map<Integer, Integer> propertyFightings = new HashMap<>();

	public static boolean init() {
		PropertyFightingTemplateDao dao = DBManager.getPropertyFightingTemplateDao();
		List<PropertyFightingTemplate> all = dao.getAll();
		for (PropertyFightingTemplate temp : all) {
			propertyFightings.put(temp.getProperty(), temp.getFighting());
		}
		return true;
	}

	public static int getFighting(EnumAttr attr) {
		return propertyFightings.get(attr.getValue());
	}

	public static int getFighting(int type) {
		return propertyFightings.get(type);
	}
}
