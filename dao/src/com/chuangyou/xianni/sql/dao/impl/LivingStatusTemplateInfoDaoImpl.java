package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.buffer.LivingStatusTemplateInfo;
import com.chuangyou.xianni.sql.dao.LivingStatusTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class LivingStatusTemplateInfoDaoImpl extends BaseDao implements LivingStatusTemplateInfoDao {

	@Override
	public List<LivingStatusTemplateInfo> getAll() {
		String sql = "SELECT * FROM tb_z_role_status";
		List<LivingStatusTemplateInfo> temps = read(sql, null);
		return temps;
	}

	private List<LivingStatusTemplateInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<LivingStatusTemplateInfo> infos = null;
		LivingStatusTemplateInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<LivingStatusTemplateInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new LivingStatusTemplateInfo();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setType(rs.getInt("type"));
					info.setSubBlood(rs.getInt("subBlood"));
					info.setSubSoul(rs.getInt("subSoul"));

					info.setAddBlood(rs.getInt("addBlood"));
					info.setAddSoul(rs.getInt("addSoul"));
					info.setMove(rs.getInt("move"));
					info.setAttackMove(rs.getInt("attackMove"));
					info.setBeHitMove(rs.getInt("beHitMove"));
					info.setBeHitFloat(rs.getInt("beHitFloat"));
					info.setBeHitDown(rs.getInt("beHitDown"));
					info.setNormalAttack(rs.getInt("normalAttack"));
					info.setSkillAttack(rs.getInt("skillAttack"));
					info.setPerks(rs.getInt("perks"));
					info.setBeControl(rs.getInt("beControl"));

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
