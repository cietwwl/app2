package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.role.RoleConfig;
import com.chuangyou.xianni.sql.dao.RoleConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

/**
 * 角色config
 * 
 * @author Administrator
 *
 */
public class RoleConfigImplDao extends BaseDao implements RoleConfigDao {

	@Override
	public List<RoleConfig> load() {
		String sql = "select * from tb_z_role_config;";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<RoleConfig> list = new ArrayList<RoleConfig>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					RoleConfig roleConfig = new RoleConfig();
					roleConfig.setId(rs.getInt("id"));
					roleConfig.setrType(rs.getInt("rType"));
					roleConfig.setDesc(rs.getString("desc"));
					roleConfig.setModelId(rs.getInt("modelId"));
					roleConfig.setShowModelId(rs.getInt("showModelId"));
					roleConfig.setMoveSpeed(rs.getDouble("moveSpeed"));
					roleConfig.setSkill(rs.getString("skill"));
					roleConfig.setCurSoul(rs.getInt("curSoul"));
					roleConfig.setCurBlood(rs.getInt("curBlood"));

					roleConfig.setSoul(rs.getInt("soul"));
					roleConfig.setBlood(rs.getInt("blood"));
					roleConfig.setAttack(rs.getInt("attack"));
					roleConfig.setDefence(rs.getInt("defence"));
					roleConfig.setSoulAttack(rs.getInt("soulAttack"));
					roleConfig.setSoulDefence(rs.getInt("soulDefence"));
					roleConfig.setAccurate(rs.getInt("accurate"));
					roleConfig.setDodge(rs.getInt("dodge"));
					roleConfig.setCrit(rs.getInt("crit"));
					roleConfig.setCritDefence(rs.getInt("critDefence"));

					roleConfig.setCritAddtion(rs.getInt("critAddtion"));
					roleConfig.setCritCut(rs.getInt("critCut"));
					roleConfig.setSoulAttackAddtion(rs.getInt("soulAttackAddtion"));
					roleConfig.setSoulAttackCut(rs.getInt("soulAttackCut"));
					roleConfig.setAttackAddtion(rs.getInt("attackAddtion"));
					roleConfig.setAttackCut(rs.getInt("attackCut"));
					roleConfig.setRegainSoul(rs.getInt("regainSoul"));
					roleConfig.setRegainBlood(rs.getInt("regainBlood"));
					roleConfig.setMetal(rs.getInt("metal"));
					roleConfig.setWood(rs.getInt("wood"));
					roleConfig.setWater(rs.getInt("water"));
					roleConfig.setFire(rs.getInt("fire"));
					roleConfig.setEarth(rs.getInt("earth"));
					roleConfig.setMetalDefence(rs.getInt("metalDefence"));
					roleConfig.setWoodDefence(rs.getInt("woodDefence"));
					roleConfig.setWaterDefence(rs.getInt("waterDefence"));
					roleConfig.setFireDefence(rs.getInt("fireDefence"));
					roleConfig.setEarthDefence(rs.getInt("earthDefence"));
					roleConfig.setSpeed(rs.getInt("speed"));

					list.add(roleConfig);
				}
			} catch (SQLException e) {
				Log.error("执行出错:" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return list;
	}
}
