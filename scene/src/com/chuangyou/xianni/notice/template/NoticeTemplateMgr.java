package com.chuangyou.xianni.notice.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.notice.NoticeCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class NoticeTemplateMgr {

	private static Map<Integer, NoticeCfg> noticeCfgMap = new HashMap<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		noticeCfgMap = DBManager.getNoticeConfigDao().getAll();
		
		return true;
	}
	
	public static NoticeCfg getNoticeCfg(int noticeId){
		return noticeCfgMap.get(noticeId);
	}
}
