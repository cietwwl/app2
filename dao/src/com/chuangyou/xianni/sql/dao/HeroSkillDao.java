package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.hero.HeroSkill;

public interface HeroSkillDao {
	/**
	 * 插入
	 * @param email
	 * @return
	 */
	public boolean add(HeroSkill heroSkill);

	/**
	 * 更新
	 * @param email
	 * @return
	 */
	public boolean update(HeroSkill heroSkill);

	/**
	 * 获取玩家所有技能
	 * @param playerId
	 * @return
	 */
	public Map<String, HeroSkill> getAll(long playerId);
}
