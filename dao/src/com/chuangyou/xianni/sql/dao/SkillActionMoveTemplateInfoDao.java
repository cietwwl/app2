package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.skill.SkillActionMoveTempleteInfo;

public interface SkillActionMoveTemplateInfoDao {
	public Map<Integer,SkillActionMoveTempleteInfo> load();
}
