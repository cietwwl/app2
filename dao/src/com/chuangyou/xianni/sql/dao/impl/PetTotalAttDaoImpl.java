package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.sql.dao.PetTotalAttDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class PetTotalAttDaoImpl extends BaseDao implements PetTotalAttDao {

	@Override
	public PetAtt get(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_pet_att where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(PetAtt info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_pet_att(playerId,soulLv,soulExp,skillGrid,fightPetId) values(?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getSoulLv()));
		params.put(3, new DbParameter(Types.INTEGER, info.getSoulExp()));
		params.put(4, new DbParameter(Types.INTEGER, info.getSkillSlotNum()));
		params.put(5, new DbParameter(Types.INTEGER, info.getFightPetId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(PetAtt info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_pet_att set soulLv=?,soulExp=?,skillGrid=?,fightPetId=? where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getSoulLv()));
		params.put(2, new DbParameter(Types.INTEGER, info.getSoulExp()));
		params.put(3, new DbParameter(Types.INTEGER, info.getSkillSlotNum()));
		params.put(4, new DbParameter(Types.INTEGER, info.getFightPetId()));
		params.put(5, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitUpdate(result);
		return result;
	}
	
	private PetAtt read(String sqlText, Map<Integer, DbParameter> params) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		PetAtt info = null;
		if(pst != null){
			try {
				rs = pst.executeQuery();
				if(rs.next()){
					info = new PetAtt();
					info.setPlayerId(rs.getLong("playerId"));
					info.setSoulLv(rs.getInt("soulLv"));
					info.setSoulExp(rs.getInt("soulExp"));
					info.setSkillSlotNum(rs.getInt("skillGrid"));
					info.setFightPetId(rs.getInt("fightPetId"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				info = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return info;
	}

}
