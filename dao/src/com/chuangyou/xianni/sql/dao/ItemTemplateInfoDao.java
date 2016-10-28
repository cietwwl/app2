package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.item.ItemTemplateInfo;

public interface ItemTemplateInfoDao {

	List<ItemTemplateInfo> getItemTemps();

	List<ItemTemplateInfo> getItemTemps(String name);
}
