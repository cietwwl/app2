package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossDieInfo;
import com.chuangyou.xianni.sql.dao.FieldBossInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class FieldBossInfoDaoImpl extends BaseDao implements FieldBossInfoDao {

	@Override
	public FieldBossDieInfo get(int monsterId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_field_boss where monsterId = ?";
		
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, monsterId));
		
		return read(sql, params);
	}

	@Override
	public boolean saveOrUpdate(FieldBossDieInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		String sql = "replace into tb_u_field_boss(monsterId,deathTime,nextTime) values(?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getMonsterId()));
		params.put(2, new DbParameter(Types.TIMESTAMP, info.getDeathTime()));
		params.put(3, new DbParameter(Types.TIMESTAMP, info.getNextTime()));
		
		result = execNoneQuery(sql, params) > -1? true: false;
		
		if(result == true){
			info.setOp(Option.None);
		}
		return false;
	}
	
	private FieldBossDieInfo read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		FieldBossDieInfo info = null;
		if(pst != null){
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new FieldBossDieInfo();
					info.setMonsterId(rs.getInt("monsterId"));
					Timestamp time = rs.getTimestamp("deathTime");
					if(time != null){
						info.setDeathTime(new Date(time.getTime()));
					}
					time = rs.getTimestamp("nextTime");
					if(time != null){
						info.setNextTime(new Date(time.getTime()));
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return info;
	}

}
