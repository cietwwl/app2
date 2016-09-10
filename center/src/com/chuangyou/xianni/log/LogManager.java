package com.chuangyou.xianni.log;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.xianni.entity.log.ItemLogInfo;
import com.chuangyou.xianni.entity.log.MonetaryLogInfo;
import com.chuangyou.xianni.entity.log.SkillLogInfo;
import com.chuangyou.xianni.sql.logdao.LogDBManager;

public class LogManager {
	public static List<ItemLogInfo>			itemLogs			= new ArrayList<>();
	private static List<SkillLogInfo>		skillLogInfo		= new ArrayList<>();
	private static List<MonetaryLogInfo>	monetaryLogInfos	= new ArrayList<>();

	public static void saveLog() {
		saveItemLog();
		saveSkillLog();
		savaMonetaryLog();
	}

	/** 添加物品日志 */
	public static void addItemLog(ItemLogInfo itemLog) {
		itemLogs.add(itemLog);
	}

	/** 保存物品日志 */
	private static void saveItemLog() {
		List<ItemLogInfo> temp = new ArrayList<>();
		synchronized (itemLogs) {
			temp.addAll(itemLogs);
			itemLogs.clear();
		}
		LogDBManager.getItemLogDao().addList(temp);
	}

	/** 添加技能日志 */
	public static void addSkillLog(SkillLogInfo log) {
		skillLogInfo.add(log);
	}

	/** 保存技能日志 */
	private static void saveSkillLog() {
		List<SkillLogInfo> temp = new ArrayList<>();
		synchronized (skillLogInfo) {
			temp.addAll(skillLogInfo);
			skillLogInfo.clear();
		}
		LogDBManager.getSkillLogDaoImpl().addList(temp);
	}

	/** 添加虚拟货币日志 */
	public static void addMonetaryLog(MonetaryLogInfo log) {
		monetaryLogInfos.add(log);
	}

	/** 保存虚拟货币日志 */
	private static void savaMonetaryLog() {
		List<MonetaryLogInfo> temp = new ArrayList<>();
		synchronized (monetaryLogInfos) {
			temp.addAll(monetaryLogInfos);
			monetaryLogInfos.clear();
		}
		LogDBManager.getMonetaryLogInfoDao().addList(temp);
	}

}
