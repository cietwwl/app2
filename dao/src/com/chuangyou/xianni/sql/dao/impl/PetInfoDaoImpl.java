package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.sql.dao.PetInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class PetInfoDaoImpl extends BaseDao implements PetInfoDao {

	@Override
	public Map<Integer, PetInfo> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_pet_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(PetInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		
		String sql = "insert tb_u_pet_info(playerId,petId,talent,level,levelExp,physique,quality,qualityBless,"
				+ " grade,gradeBless) values(?,?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getPetId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getTalent()));
		params.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(5, new DbParameter(Types.INTEGER, info.getLevelExp()));
		params.put(6, new DbParameter(Types.INTEGER, info.getPhysique()));
		params.put(7, new DbParameter(Types.INTEGER, info.getQuality()));
		params.put(8, new DbParameter(Types.INTEGER, info.getQualityBless()));
		params.put(9, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(10, new DbParameter(Types.INTEGER, info.getGradeBless()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(PetInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		
		String sql = "update tb_u_pet_info set talent=?,level=?,levelExp=?,physique=?,quality=?,qualityBless=?,"
				+ "grade=?,gradeBless=? where playerId=? and petId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, info.getTalent()));
		params.put(2, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(3, new DbParameter(Types.INTEGER, info.getLevelExp()));
		params.put(4, new DbParameter(Types.INTEGER, info.getPhysique()));
		params.put(5, new DbParameter(Types.INTEGER, info.getQuality()));
		params.put(6, new DbParameter(Types.INTEGER, info.getQualityBless()));
		params.put(7, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(8, new DbParameter(Types.INTEGER, info.getGradeBless()));
		
		params.put(9, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(10, new DbParameter(Types.INTEGER, info.getPetId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitUpdate(result);
		return false;
	}
	
	private Map<Integer, PetInfo> read(String sqlText, Map<Integer, DbParameter> params) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, PetInfo> infos = null;
		PetInfo info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setPetId(rs.getInt("petId"));
					info.setTalent(rs.getInt("talent"));
					info.setLevel(rs.getInt("level"));
					info.setLevelExp(rs.getInt("levelExp"));
					info.setPhysique(rs.getInt("physique"));
					info.setQuality(rs.getInt("quality"));
					info.setQualityBless(rs.getInt("qualityBless"));
					info.setGrade(rs.getInt("grade"));
					info.setGradeBless(rs.getInt("gradeBless"));
					infos.put(info.getPetId(), info);
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
