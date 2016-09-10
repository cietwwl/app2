package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.sql.dao.HeroSkillDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class HeroSkillDaoImpl extends BaseDao implements HeroSkillDao {

	@Override
	public boolean add(HeroSkill heroSkill) {
		heroSkill.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_hero_skill_info (playerId,skillId,type,subType,grandsonType,skillLV)" + " VALUES (?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, heroSkill.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, heroSkill.getSkillId()));
		para.put(3, new DbParameter(Types.INTEGER, heroSkill.getType()));
		para.put(4, new DbParameter(Types.INTEGER, heroSkill.getSubType()));
		para.put(5, new DbParameter(Types.INTEGER, heroSkill.getGrandsonType()));
		para.put(6, new DbParameter(Types.INTEGER, heroSkill.getSkillLV()));

		result = execNoneQuery(sql, para) > -1 ? true : false;
		heroSkill.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(HeroSkill heroSkill) {
		boolean result = false;
		heroSkill.beginUpdate();
		String sql = "update tb_u_hero_skill_info set skillId=?,skillLV=? where playerId=? and type=? and subType=? and grandsonType=?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, heroSkill.getSkillId()));
		para.put(2, new DbParameter(Types.INTEGER, heroSkill.getSkillLV()));
		para.put(3, new DbParameter(Types.INTEGER, heroSkill.getPlayerId()));
		para.put(4, new DbParameter(Types.INTEGER, heroSkill.getType()));
		para.put(5, new DbParameter(Types.INTEGER, heroSkill.getSubType()));
		para.put(6, new DbParameter(Types.INTEGER, heroSkill.getGrandsonType()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		heroSkill.commitUpdate(result);
		return result;
	}

	@Override
	public Map<String, HeroSkill> getAll(long playerId) {
		String sql = "select * from tb_u_hero_skill_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, playerId));

		PreparedStatement pst = execQuery(sql, params);
		ResultSet rs = null;
		Map<String, HeroSkill> infos = new HashMap<String, HeroSkill>();
		HeroSkill info = null;
		if (pst != null) {
			try {
				rs = pst.executeQuery();
				while (rs.next()) {
					info = new HeroSkill();
					info.setPlayerId(rs.getLong("playerId"));
					info.setSkillId(rs.getInt("skillId"));
					info.setType(rs.getInt("type"));
					info.setSubType(rs.getInt("subType"));
					info.setGrandsonType(rs.getInt("grandsonType"));
					info.setSkillLV(rs.getInt("skillLV"));
					infos.put(rs.getInt("type") + "_" + rs.getInt("subType") + "_" + rs.getInt("grandsonType"), info);
				}
			} catch (Exception e) {
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}

}
