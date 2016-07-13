package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;

public interface MagicwpAttDao {
	public MagicwpAtt get(long playerId);
	
	public boolean add(MagicwpAtt info);
	
	public boolean update(MagicwpAtt info);
}
