package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.fashion.FashionCfg;
import com.chuangyou.xianni.entity.fashion.FashionLevelCfg;
import com.chuangyou.xianni.entity.fashion.FashionQualityCfg;
import com.chuangyou.xianni.sql.dao.FashionConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class FashionConfigDaoImpl extends BaseDao implements FashionConfigDao {

	@Override
	public Map<Integer, FashionCfg> getFashion() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_fashion";
		return readFashion(sql);
	}

	@Override
	public Map<Integer, FashionLevelCfg> getLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_fashion_level";
		return readLevel(sql);
	}

	@Override
	public Map<Integer, FashionQualityCfg> getQuality() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_fashion_quality";
		return readQuality(sql);
	}
	
	private Map<Integer, FashionCfg> readFashion(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, FashionCfg> infos = null;
		FashionCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, FashionCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new FashionCfg();
					info.setId(rs.getInt("id"));
					info.setFashionName(rs.getString("fashionName"));
					info.setAttr1Base(rs.getInt("attr1Base"));
					info.setAttr1QualityAdd(rs.getInt("attr1QualityAdd"));
					info.setAttr1LevelAdd(rs.getInt("attr1LevelAdd"));
					info.setAttr2Base(rs.getInt("attr2Base"));
					info.setAttr2QualityAdd(rs.getInt("attr2QualityAdd"));
					info.setAttr2LevelAdd(rs.getInt("attr2LevelAdd"));
					info.setAttr3Base(rs.getInt("attr3Base"));
					info.setAttr3QualityAdd(rs.getInt("attr3QualityAdd"));
					info.setAttr3LevelAdd(rs.getInt("attr3LevelAdd"));
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
	
	private Map<Integer, FashionLevelCfg> readLevel(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, FashionLevelCfg> infos = null;
		FashionLevelCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, FashionLevelCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new FashionLevelCfg();
					info.setLevel(rs.getInt("level"));
					info.setExpMax(rs.getInt("expMax"));
					infos.put(info.getLevel(), info);
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
	
	private Map<Integer, FashionQualityCfg> readQuality(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, FashionQualityCfg> infos = null;
		FashionQualityCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, FashionQualityCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new FashionQualityCfg();
					info.setQuality(rs.getInt("quality"));
					info.setQualityName(rs.getString("qualityName"));
					info.setQualityUpConsume(rs.getInt("qualityUpConsume"));
					info.setLevelUpConsume(rs.getInt("levelUpConsume"));
					infos.put(info.getQuality(), info);
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
