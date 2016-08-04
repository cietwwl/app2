package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;
import com.chuangyou.xianni.sql.dao.PlayerInverseBeadDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class PlayerInverseBeadDaoImpl extends BaseDao implements PlayerInverseBeadDao {

	@Override
	public boolean add(PlayerInverseBead playerInverseBead) {

		playerInverseBead.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_inverse_bead (playerId,fiveElements,marking,stage,val)" + " VALUES (?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, playerInverseBead.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, playerInverseBead.getFiveElements()));
		para.put(3, new DbParameter(Types.INTEGER, playerInverseBead.getMarking()));
		para.put(4, new DbParameter(Types.INTEGER, playerInverseBead.getStage()));
		para.put(5, new DbParameter(Types.INTEGER, playerInverseBead.getVal()));
		para.put(6, new DbParameter(Types.INTEGER, playerInverseBead.getAttVal()));
		para.put(7, new DbParameter(Types.INTEGER, playerInverseBead.getAttVal2()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		playerInverseBead.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(PlayerInverseBead playerInverseBead) {

		boolean result = false;
		playerInverseBead.beginUpdate();
		String sql = "update tb_u_hero_skill_info set skillId=?,skillLV=? where playerId=? and type=? and subType=? and grandsonType=?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, playerInverseBead.getPlayerId()));
		para.put(2, new DbParameter(Types.INTEGER, playerInverseBead.getFiveElements()));
		para.put(3, new DbParameter(Types.INTEGER, playerInverseBead.getMarking()));
		para.put(4, new DbParameter(Types.INTEGER, playerInverseBead.getStage()));
		para.put(5, new DbParameter(Types.INTEGER, playerInverseBead.getVal()));
		para.put(6, new DbParameter(Types.INTEGER, playerInverseBead.getAttVal()));
		para.put(7, new DbParameter(Types.INTEGER, playerInverseBead.getAttVal2()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		playerInverseBead.commitUpdate(result);
		return result;
	}

	@Override
	public Map<String, PlayerInverseBead> getAll(long playerId) {

		String sql = "select * from tb_u_inverse_bead where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, playerId));

		PreparedStatement pst = execQuery(sql, params);
		ResultSet rs = null;
		Map<String, PlayerInverseBead> infos = new HashMap<String, PlayerInverseBead>();
		PlayerInverseBead info = null;
		if (pst != null) {
			try {
				rs = pst.executeQuery();
				while (rs.next()) {
					info = new PlayerInverseBead();
					info.setId(rs.getInt("id"));
					info.setPlayerId(rs.getInt("playerId"));
					info.setFiveElements(rs.getInt("fiveElements"));
					info.setMarking(rs.getInt("marking"));
					info.setStage(rs.getInt("stage"));
					info.setVal(rs.getInt("val"));
					info.setAttVal(rs.getInt("attVal"));
					info.setAttVal2(rs.getInt("attVal2"));

					infos.put(rs.getInt("fiveElements") + "_" + rs.getInt("marking"), info);
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
