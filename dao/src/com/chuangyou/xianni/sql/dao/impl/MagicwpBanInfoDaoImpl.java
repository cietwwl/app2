package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.sql.dao.MagicwpBanInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class MagicwpBanInfoDaoImpl extends BaseDao implements MagicwpBanInfoDao {

	@Override
	public Map<Integer, MagicwpBanInfo> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_magicwp_ban_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(MagicwpBanInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_magicwp_ban_info(playerId,banId,position,level,exp,autoUpLevel,fragmentStr) "
				+ "values(?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getBanId()));
		params.put(3, new DbParameter(Types.TINYINT, info.getPosition()));
		params.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(5, new DbParameter(Types.INTEGER, info.getExp()));
		params.put(6, new DbParameter(Types.TINYINT, info.getAutoUpLevel()));
		params.put(7, new DbParameter(Types.VARCHAR, info.getFragmentStr()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(MagicwpBanInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_magicwp_ban_info set position=?,level=?,exp=?,autoUpLevel=?,fragmentStr=? "
				+ "where playerId=? and banId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.TINYINT, info.getPosition()));
		params.put(2, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(3, new DbParameter(Types.INTEGER, info.getExp()));
		params.put(4, new DbParameter(Types.TINYINT, info.getAutoUpLevel()));
		params.put(5, new DbParameter(Types.VARCHAR, info.getFragmentStr()));
		
		params.put(6, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(7, new DbParameter(Types.INTEGER, info.getBanId()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}
	
	private Map<Integer, MagicwpBanInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, MagicwpBanInfo> infos = null;
		MagicwpBanInfo info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpBanInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpBanInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setBanId(rs.getInt("banId"));
					info.setPosition(rs.getByte("position"));
					info.setLevel(rs.getInt("level"));
					info.setExp(rs.getInt("exp"));
					info.setAutoUpLevel(rs.getByte("autoUpLevel"));
					info.setFragmentStr(rs.getString("fragmentStr"));
					infos.put(info.getBanId(), info);
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
