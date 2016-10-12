package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.sql.dao.RobotTemplateDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class RobotTemplateDaoImpl extends BaseDao implements RobotTemplateDao {

	public List<RobotTemplate> getAll() {
		String sql = "SELECT * FROM tb_z_robot";
		List<RobotTemplate> result = read(sql, null);
		return result;
	}

	private List<RobotTemplate> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<RobotTemplate> infos = new ArrayList<>();
		RobotTemplate info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new RobotTemplate();
					info.setId(rs.getInt("id"));
					info.setType(rs.getInt("type"));
					info.setNickName(rs.getString("nickName"));
					info.setFluctuate(rs.getInt("fluctuate"));
					info.setModeId(rs.getInt("modeId"));
					info.setJob(rs.getInt("job"));
					info.setLevel(rs.getInt("level"));
					info.setMagicWeaponId(rs.getInt("magicWeaponId"));
					info.setFashionId(rs.getInt("fashionId"));
					info.setWeaponId(rs.getInt("weaponId"));
					info.setWingId(rs.getInt("wingId"));
					info.setMoveSpeed(rs.getInt("moveSpeed"));
					info.setAttackSpeed(rs.getInt("attackSpeed"));
					info.setSoulHpValue(rs.getLong("soulHpValue"));
					info.setHp(rs.getLong("hp"));
					info.setHurtValue(rs.getInt("hurtValue"));
					info.setArmorValue(rs.getInt("armorValue"));
					info.setSoulHurtValue(rs.getInt("soulHurtValue"));
					info.setSoulArmorValue(rs.getInt("soulArmorValue"));
					info.setHitRateValue(rs.getInt("hitRateValue"));
					info.setDodgeValue(rs.getInt("dodgeValue"));
					info.setCritValue(rs.getInt("critValue"));
					info.setToughnessValue(rs.getInt("toughnessValue"));
					info.setGuildId(rs.getInt("guildId"));
					info.setGuildJob(rs.getInt("guildJob"));
					info.setSkillIds(rs.getString("skillIds"));
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

}
