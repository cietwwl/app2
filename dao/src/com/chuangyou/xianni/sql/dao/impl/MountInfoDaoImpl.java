package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.sql.dao.MountInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class MountInfoDaoImpl extends BaseDao implements MountInfoDao {

	@Override
	public MountInfo get(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_mount_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return this.read(sql, params);
	}

	@Override
	public boolean add(MountInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_mount_info(playerId,mountId,level,upLevCd,grade,bless,useDanNum,weaponGrade,weaponBless) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getMountId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(4, new DbParameter(Types.BIGINT, info.getUpLevCd()));
		params.put(5, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(6, new DbParameter(Types.INTEGER, info.getBless()));
		params.put(7, new DbParameter(Types.INTEGER, info.getUseDanNum()));
		params.put(8, new DbParameter(Types.INTEGER, info.getWeaponGrade()));
		params.put(9, new DbParameter(Types.INTEGER, info.getWeaponBless()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(MountInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_mount_info set mountId=?,level=?,upLevCd=?,grade=?,"
				+ "bless=?,useDanNum=?,weaponGrade=?,weaponBless=? where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		
		params.put(1, new DbParameter(Types.INTEGER, info.getMountId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(3, new DbParameter(Types.BIGINT, info.getUpLevCd()));
		params.put(4, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(5, new DbParameter(Types.INTEGER, info.getBless()));
		params.put(6, new DbParameter(Types.INTEGER, info.getUseDanNum()));
		params.put(7, new DbParameter(Types.INTEGER, info.getWeaponGrade()));
		params.put(8, new DbParameter(Types.INTEGER, info.getWeaponBless()));
		params.put(9, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, params)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}
	
	private MountInfo read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		MountInfo info = null;
		if(pst != null){
			try {
				rs = pst.executeQuery();
				if(rs.next()){
					info = new MountInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setMountId(rs.getInt("mountId"));
					info.setLevel(rs.getInt("level"));
					info.setUpLevCd(rs.getLong("upLevCd"));
					info.setGrade(rs.getInt("grade"));
					info.setBless(rs.getInt("bless"));
					info.setUseDanNum(rs.getInt("useDanNum"));
					info.setWeaponGrade(rs.getInt("weaponGrade"));
					info.setWeaponBless(rs.getInt("weaponBless"));
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
