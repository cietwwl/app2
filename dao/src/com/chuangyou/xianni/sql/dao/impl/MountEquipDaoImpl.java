package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.sql.dao.MountEquipDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class MountEquipDaoImpl extends BaseDao implements MountEquipDao {

	@Override
	public Map<Integer, MountEquipInfo> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_mount_equip_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return this.read(sql, params);
	}

	@Override
	public boolean add(MountEquipInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_mount_equip_info(playerId,equipId,equipLevel) values(?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getEquipId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getEquipLevel()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(MountEquipInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_mount_equip_info set equipLevel=? where playerId=? and equipId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, info.getEquipLevel()));
		params.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getEquipId()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}

	private Map<Integer, MountEquipInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, MountEquipInfo> infos = null;
		MountEquipInfo info = null;
		if(pst != null){
			infos = new HashMap<Integer, MountEquipInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MountEquipInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setEquipId(rs.getInt("equipId"));
					info.setEquipLevel(rs.getInt("equipLevel"));
					infos.put(info.getEquipId(), info);
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
