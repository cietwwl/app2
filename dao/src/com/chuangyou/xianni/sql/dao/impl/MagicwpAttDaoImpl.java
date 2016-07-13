package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.sql.dao.MagicwpAttDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class MagicwpAttDaoImpl extends BaseDao implements MagicwpAttDao {

	@Override
	public MagicwpAtt get(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_magicwp_att where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(MagicwpAtt info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_magicwp_att(playerId,curMagicwpId,useDanNum) "
				+ "values(?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getCurMagicwpId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getUseDanNum()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(MagicwpAtt info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_magicwp_att set curMagicwpId=?,useDanNum=? "
				+ "where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getCurMagicwpId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getUseDanNum()));
		params.put(3, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitUpdate(result);
		return result;
	}
	
	public MagicwpAtt read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		MagicwpAtt info = null;
		if(pst != null){
			try {
				rs = pst.executeQuery();
				if(rs.next()){
					info = new MagicwpAtt();
					info.setPlayerId(rs.getLong("playerId"));
					info.setCurMagicwpId(rs.getInt("curMagicwpId"));
					info.setUseDanNum(rs.getInt("useDanNum"));
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
