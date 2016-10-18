package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.sql.dao.MonsterInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class MonsterInfoDaoImpl extends BaseDao implements MonsterInfoDao {

	@Override
	public Map<Integer, MonsterInfo> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_z_monster_info";
		Map<Integer, MonsterInfo> result = read(sql, null);
		return result;
	}

	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private Map<Integer, MonsterInfo> read(String sqlText, Map<Integer, DbParameter> para) {

		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Integer, MonsterInfo> infos = null;
		MonsterInfo info = null;
		if (pstmt != null) {
			infos = new HashMap<Integer, MonsterInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new MonsterInfo();
					info.setMonsterId(rs.getInt("monsterId"));
					info.setName(rs.getString("name"));
					info.setSkin(rs.getInt("skin"));
					info.setLevel(rs.getInt("level"));
					info.setMonsterType(rs.getInt("monsterType"));

					info.setAlertRange(rs.getInt("alertRange"));
					info.setSeekEnemyRange(rs.getInt("seekEnemyRange"));
					info.setAttackRange(rs.getInt("attackRange"));
					info.setFollowUpDistance(rs.getInt("followUpDistance"));
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
					info.setHurtType(rs.getInt("hurtType"));
					info.setBeKilledExp(rs.getInt("beKilledExp"));
					info.setDropType(rs.getInt("dropType"));
					info.setDrop1(rs.getInt("drop1"));
					info.setDrop2(rs.getInt("drop2"));
					info.setDrop3(rs.getInt("drop3"));
					info.setDrop4(rs.getInt("drop4"));
					info.setSkillIds(rs.getString("skillIds"));
					info.setAiId(rs.getInt("aiId"));
					infos.put(info.getMonsterId(), info);
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
