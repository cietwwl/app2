package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.mount.MountSpecialGet;
import com.chuangyou.xianni.sql.dao.MountSpecialInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class MountSpecialInfoDaoImpl extends BaseDao implements MountSpecialInfoDao {

	@Override
	public Map<Integer, MountSpecialGet> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_mount_special where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return this.read(sql, params);
	}

	@Override
	public boolean add(MountSpecialGet info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_mount_special(playerId,mountId) values(?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getMountId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean delete(long playerId, int mountId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_mount_special where playerId=? and mountId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		params.put(2, new DbParameter(Types.INTEGER, mountId));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}

	private Map<Integer, MountSpecialGet> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, MountSpecialGet> infos = null;
		MountSpecialGet info = null;
		if(pst != null){
			infos = new HashMap<Integer, MountSpecialGet>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MountSpecialGet();
					info.setPlayerId(rs.getLong("playerId"));
					info.setMountId(rs.getInt("mountId"));
					infos.put(info.getMountId(), info);
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
