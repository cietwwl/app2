package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.common.SystemConfig;
import com.chuangyou.xianni.sql.dao.SystemConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class SystemConfigDaoImpl extends BaseDao implements SystemConfigDao {

	@Override
	public Map<String, SystemConfig> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_system";
		return this.read(sql);
	}
	
	private Map<String, SystemConfig> read(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<String, SystemConfig> infos = null;
		SystemConfig info = null;
		if(pst != null){
			infos = new HashMap<String, SystemConfig>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new SystemConfig();
					info.setKey(rs.getString("key"));
					info.setValue(rs.getInt("value"));
					info.set_desc(rs.getString("_desc"));
					infos.put(info.getKey(), info);
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
