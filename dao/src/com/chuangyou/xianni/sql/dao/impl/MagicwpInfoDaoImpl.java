package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.sql.dao.MagicwpInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class MagicwpInfoDaoImpl extends BaseDao implements MagicwpInfoDao {

	@Override
	public Map<Integer, MagicwpInfo> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_magicwp_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(MagicwpInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_magicwp_info(playerId,magicwpId,level,upLevelCd,grade,refineAtts,unSaveAtt,tempAtt) "
				+ "values(?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getMagicwpId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(4, new DbParameter(Types.BIGINT, info.getUpLevelCd()));
		params.put(5, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(6, new DbParameter(Types.VARCHAR, info.getRefineAtts()));
		params.put(7, new DbParameter(Types.TINYINT, info.getUnSaveAtt()));
		params.put(8, new DbParameter(Types.VARCHAR, info.getTempAtt()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(MagicwpInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_magicwp_info set level=?,upLevelCd=?,grade=?,refineAtts=?,unSaveAtt=?,tempAtt=?"
				+ "where playerId=? and magicwpId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(2, new DbParameter(Types.BIGINT, info.getUpLevelCd()));
		params.put(3, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(4, new DbParameter(Types.VARCHAR, info.getRefineAtts()));
		params.put(5, new DbParameter(Types.TINYINT, info.getUnSaveAtt()));
		params.put(6, new DbParameter(Types.VARCHAR, info.getTempAtt()));
		
		params.put(7, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(8, new DbParameter(Types.INTEGER, info.getMagicwpId()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}
	
	private Map<Integer, MagicwpInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, MagicwpInfo> infos = null;
		MagicwpInfo info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setMagicwpId(rs.getInt("magicwpId"));
					info.setLevel(rs.getInt("level"));
					info.setUpLevelCd(rs.getLong("upLevelCd"));
					info.setGrade(rs.getInt("grade"));
					info.setRefineAtts(rs.getString("refineAtts"));
					info.setUnSaveAtt(rs.getByte("unSaveAtt"));
					info.setTempAtt(rs.getString("tempAtt"));
					infos.put(info.getMagicwpId(), info);
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
