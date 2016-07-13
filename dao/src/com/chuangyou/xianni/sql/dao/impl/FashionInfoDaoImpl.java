package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.fashion.FashionInfo;
import com.chuangyou.xianni.sql.dao.FashionInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class FashionInfoDaoImpl extends BaseDao implements FashionInfoDao {

	@Override
	public Map<Integer, FashionInfo> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_fashion where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(FashionInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_fashion(playerId,fashionId,quality,level,exp,isEquiped) "
				+ "values(?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getFashionId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getQuality()));
		params.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(5, new DbParameter(Types.INTEGER, info.getExp()));
		params.put(6, new DbParameter(Types.INTEGER, info.getIsEquiped()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(FashionInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_fashion set quality=?,level=?,exp=?,isEquiped=? "
				+ "where playerId=? and fashionId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getQuality()));
		params.put(2, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(3, new DbParameter(Types.INTEGER, info.getExp()));
		params.put(4, new DbParameter(Types.INTEGER, info.getIsEquiped()));
		params.put(5, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(6, new DbParameter(Types.INTEGER, info.getFashionId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitUpdate(result);
		return result;
	}
	
	private Map<Integer, FashionInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, FashionInfo> infos = null;
		FashionInfo info = null;
		if(pst != null){
			infos = new HashMap<Integer, FashionInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new FashionInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setFashionId(rs.getInt("fashionId"));
					info.setQuality(rs.getInt("quality"));
					info.setLevel(rs.getInt("level"));
					info.setExp(rs.getInt("exp"));
					info.setIsEquiped(rs.getByte("isEquiped"));
					infos.put(info.getFashionId(), info);
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
