package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.sql.dao.EquipBarInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class EquipBarInfoDaoImpl extends BaseDao implements EquipBarInfoDao {

	@Override
	public Map<Short, EquipBarInfo> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_equipbar_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(EquipBarInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_equipbar_info(playerId,position,level,exp,grade,bless)"
				+ " values(?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.SMALLINT, info.getPosition()));
		params.put(3, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(4, new DbParameter(Types.INTEGER, info.getExp()));
		params.put(5, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(6, new DbParameter(Types.INTEGER, info.getBless()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(EquipBarInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		String sql = "update tb_u_equipbar_info set level=?,exp=?,grade=?,bless=? where playerId=? adn position=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(2, new DbParameter(Types.INTEGER, info.getExp()));
		params.put(3, new DbParameter(Types.INTEGER, info.getGrade()));
		params.put(4, new DbParameter(Types.INTEGER, info.getBless()));
		
		params.put(5, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(6, new DbParameter(Types.SMALLINT, info.getPosition()));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}
	
	private Map<Short, EquipBarInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Short, EquipBarInfo> infos = null;
		EquipBarInfo info = null;
		if(pst != null){
			infos = new HashMap<Short, EquipBarInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new EquipBarInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setPosition(rs.getShort("position"));
					info.setLevel(rs.getInt("level"));
					info.setExp(rs.getInt("exp"));
					info.setGrade(rs.getInt("grade"));
					info.setBless(rs.getInt("bless"));
					info.setOp(Option.None);
					infos.put(info.getPosition(), info);
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
