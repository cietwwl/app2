package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.drop.DropInfo;
import com.chuangyou.xianni.entity.drop.DropItemInfo;
import com.chuangyou.xianni.sql.dao.DropConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class DropConfigDaoImpl extends BaseDao implements DropConfigDao {

	@Override
	public Map<Integer, DropInfo> getDropInfo() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_drop_info";
		return readDropInfo(sql);
	}

	@Override
	public Map<Integer, Map<Integer, DropItemInfo>> getDropItem() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_drop_item_info";
		return readDropItemInfo(sql);
	}
	
	private Map<Integer, DropInfo> readDropInfo(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, DropInfo> infos = null;
		DropInfo info = null;
		if(pst != null){
			infos = new HashMap<Integer, DropInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new DropInfo();
					info.setId(rs.getInt("id"));
					info.setType(rs.getShort("type"));
					info.setRepeat(rs.getInt("repeat"));
					info.setLimitType(rs.getShort("limitType"));
					info.setStartTime(rs.getString("startTime"));
					info.setEndTime(rs.getString("endTime"));
					info.setVisibleType(rs.getShort("visibleType"));
					infos.put(info.getId(), info);
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
	
	private Map<Integer, Map<Integer, DropItemInfo>> readDropItemInfo(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, Map<Integer, DropItemInfo>> infos = null;
		DropItemInfo info = null;
		if(pst != null){
			infos = new HashMap<Integer, Map<Integer,DropItemInfo>>();
			Map<Integer, DropItemInfo> pool = null;
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new DropItemInfo();
					info.setId(rs.getInt("id"));
					info.setPoolId(rs.getInt("poolId"));
					info.setItemId(rs.getInt("itemId"));
					info.setCount(rs.getInt("count"));
					info.setWeight(rs.getInt("weight"));
					
					pool = infos.get(info.getPoolId());
					if(pool == null){
						pool = new HashMap<Integer, DropItemInfo>();
						infos.put(info.getPoolId(), pool);
					}
					pool.put(info.getId(), info);
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
