package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.sql.dao.PetSkillInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class PetSkillInfoDaoImpl extends BaseDao implements PetSkillInfoDao {

	@Override
	public Map<Integer, PetSkill> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_pet_skill_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(PetSkill info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_pet_skill_info(playerId,skillId,state,gridIndex,level) values(?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getSkillId()));
		params.put(3, new DbParameter(Types.TINYINT, info.getState()));
		params.put(4, new DbParameter(Types.TINYINT, info.getSlotIndex()));
		params.put(5, new DbParameter(Types.INTEGER, info.getLevel()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitAdd(result);
		return false;
	}

	@Override
	public boolean update(PetSkill info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_pet_skill_info set state=?,gridIndex=?,level=? where playerId=? and skillId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.TINYINT, info.getState()));
		params.put(2, new DbParameter(Types.TINYINT, info.getSlotIndex()));
		params.put(3, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(4, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(5, new DbParameter(Types.INTEGER, info.getSkillId()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}
	
	private Map<Integer, PetSkill> read(String sqlText, Map<Integer, DbParameter> params) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, PetSkill> infos = null;
		PetSkill info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetSkill>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetSkill();
					info.setPlayerId(rs.getLong("playerId"));
					info.setSkillId(rs.getInt("skillId"));
					info.setState(rs.getByte("state"));
					info.setSlotIndex(rs.getByte("gridIndex"));
					info.setLevel(rs.getInt("level"));
					infos.put(info.getSkillId(), info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}

}
