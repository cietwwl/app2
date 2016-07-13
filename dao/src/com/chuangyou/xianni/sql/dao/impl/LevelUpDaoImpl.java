package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.level.LevelUp;
import com.chuangyou.xianni.sql.dao.LevelUpDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class LevelUpDaoImpl extends BaseDao implements LevelUpDao {

	@Override
	public Map<Integer, List<LevelUp>> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_level_up";
		return read(sql);
	}
	
	public Map<Integer, List<LevelUp>> read(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, List<LevelUp>> infos = null;
		LevelUp info = null;
		if(pst != null){
			infos = new HashMap<Integer, List<LevelUp>>();
			List<LevelUp> levels = null;
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new LevelUp();
					info.setType(rs.getInt("type"));
					info.setLevel(rs.getInt("level"));
					info.setExp(rs.getLong("exp"));
					info.setActiveObject(rs.getInt("activeObject"));
					
					levels = infos.get(info.getType());
					if(levels == null){
						levels = new ArrayList<>();
						infos.put(info.getType(), levels);
					}
					levels.add(info);
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
