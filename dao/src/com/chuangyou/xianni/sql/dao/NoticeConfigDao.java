package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.notice.NoticeCfg;

public interface NoticeConfigDao {

	public Map<Integer, NoticeCfg> getAll();
}
