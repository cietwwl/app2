package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.property.SkillPropertyTemplateInfo;
import com.chuangyou.xianni.sql.dao.SkillPropertyTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class SkillPropertyTemplateInfoDaoImpl extends BaseDao implements SkillPropertyTemplateInfoDao {

	// @Override
	// public List<SkillPropertyTemplateInfo> load() {
	// String sql = "select * from tb_z_skill_property;";
	// PreparedStatement pstmt = execQuery(sql);
	// ResultSet rs = null;
	// List<SkillPropertyTemplateInfo> list = new
	// ArrayList<SkillPropertyTemplateInfo>();
	// if (pstmt != null) {
	// try {
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// SkillPropertyTemplateInfo skillPropertyTemplateInfo = new
	// SkillPropertyTemplateInfo();
	// skillPropertyTemplateInfo.setTemplateId(rs.getInt("templateId"));
	// skillPropertyTemplateInfo.setType(rs.getInt("type"));
	// skillPropertyTemplateInfo.setSoul(rs.getInt("soul"));
	// skillPropertyTemplateInfo.setBlood(rs.getInt("blood"));
	// skillPropertyTemplateInfo.setAttack(rs.getInt("attack"));
	// skillPropertyTemplateInfo.setDefence(rs.getInt("defence"));
	// skillPropertyTemplateInfo.setSoulAttack(rs.getInt("soulAttack"));
	// skillPropertyTemplateInfo.setSoulDefence(rs.getInt("soulDefence"));
	// skillPropertyTemplateInfo.setAccurate(rs.getInt("accurate"));
	// skillPropertyTemplateInfo.setDodge(rs.getInt("dodge"));
	// skillPropertyTemplateInfo.setCrit(rs.getInt("crit"));
	// skillPropertyTemplateInfo.setCritDefence(rs.getInt("critDefence"));
	// skillPropertyTemplateInfo.setCritAddtion(rs.getInt("critAddtion"));
	// skillPropertyTemplateInfo.setCritCut(rs.getInt("critCut"));
	// skillPropertyTemplateInfo.setSoulAttackAddtion(rs.getInt("soulAttackAddtion"));
	// skillPropertyTemplateInfo.setSoulAttackCut(rs.getInt("soulAttackCut"));
	// skillPropertyTemplateInfo.setBloodAttackAddtion(rs.getInt("attackAddtion"));
	// skillPropertyTemplateInfo.setBloodAttackCut(rs.getInt("attackCut"));
	// skillPropertyTemplateInfo.setRegainSoul(rs.getInt("regainSoul"));
	// skillPropertyTemplateInfo.setRegainBlood(rs.getInt("regainBlood"));
	// skillPropertyTemplateInfo.setMetal(rs.getInt("metal"));
	// skillPropertyTemplateInfo.setWood(rs.getInt("wood"));
	// skillPropertyTemplateInfo.setWater(rs.getInt("water"));
	// skillPropertyTemplateInfo.setFire(rs.getInt("fire"));
	// skillPropertyTemplateInfo.setEarth(rs.getInt("earth"));
	// skillPropertyTemplateInfo.setMetalDefence(rs.getInt("metalDefence"));
	// skillPropertyTemplateInfo.setWoodDefence(rs.getInt("woodDefence"));
	// skillPropertyTemplateInfo.setWaterDefence(rs.getInt("waterDefence"));
	// skillPropertyTemplateInfo.setFireDefence(rs.getInt("fireDefence"));
	// skillPropertyTemplateInfo.setEarthDefence(rs.getInt("earthDefence"));
	// skillPropertyTemplateInfo.setSpeed(rs.getInt("speed"));
	// list.add(skillPropertyTemplateInfo);
	// }
	// } catch (SQLException e) {
	// Log.error("执行出错:" + sql, e);
	// } finally {
	// closeConn(pstmt, rs);
	// }
	// }
	// return list;
	// }
}
