package com.chuangyou.xianni.sql.logdao;

import com.chuangyou.xianni.sql.logdao.impl.ItemLogDaoImpl;
import com.chuangyou.xianni.sql.logdao.impl.MonetaryLogInfoDaoImpl;
import com.chuangyou.xianni.sql.logdao.impl.SkillLogDaoImpl;

public class LogDBManager {
	/**
	 * 物品日志信息
	 */
	private static final ItemLogDao			itemLogDao		= new ItemLogDaoImpl();
	/**
	 * 技能日志信息
	 */
	private static final SkillLogDaoImpl	skillLogDaoImpl	= new SkillLogDaoImpl();

	public static ItemLogDao getItemLogDao() {
		return itemLogDao;
	}

	public static SkillLogDaoImpl getSkillLogDaoImpl() {
		return skillLogDaoImpl;
	}

	/** 虚拟货币信息 */
	private static final MonetaryLogInfoDao monetaryLogInfoDao = new MonetaryLogInfoDaoImpl();

	public static MonetaryLogInfoDao getMonetaryLogInfoDao() {
		return monetaryLogInfoDao;
	}

}
