//package com.chuangyou.xianni.inverseBead.template;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.chuangyou.xianni.entity.inverseBead.InverseBeadMonster;
//import com.chuangyou.xianni.sql.dao.DBManager;
//
//public class InverseBeadMonsterTemMgr {
//
//	private static Map<Integer, InverseBeadMonster> inverseBeadMonsterTem = new HashMap<Integer, InverseBeadMonster>();
//
//	public static boolean init() {
//		reloadTemplateData();
//		return true;
//	}
//
//	public static boolean reloadTemplateData() {
//		List<InverseBeadMonster> tem2 = DBManager.getInverseBeadMonsterTemDao().load();
//		if (tem2 != null && tem2.size() > 0) {
//			for (InverseBeadMonster t : tem2) {
//				inverseBeadMonsterTem.put(t.getId(), t);
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * 获取怪物刷新数据模板
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public static InverseBeadMonster getInverseBeadMonsterTem(int id) {
//		return inverseBeadMonsterTem.get(id);
//	}
//
//}
