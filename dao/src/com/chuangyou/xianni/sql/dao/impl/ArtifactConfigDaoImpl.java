package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.artifact.ArtifactGradeupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactInfoCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelLevelCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelSuitCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactLevelupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactSuitCfg;
import com.chuangyou.xianni.sql.dao.ArtifactConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class ArtifactConfigDaoImpl extends BaseDao implements ArtifactConfigDao {

	@Override
	public Map<Integer, ArtifactInfoCfg> getArtifactInfo() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_artifact";
		return readArtifactInfo(sql);
	}

	@Override
	public Map<Long, ArtifactLevelupCfg> getArtifactLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_artifact_levelup";
		return readArtifactLevel(sql);
	}

	@Override
	public Map<Long, ArtifactGradeupCfg> getArtifactGrade() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_artifact_gradeup";
		return readArtifactGrade(sql);
	}

	@Override
	public List<ArtifactSuitCfg> getArtifactSuit() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_artifact_suit";
		return readArtifactSuit(sql);
	}

	@Override
	public Map<Integer, List<ArtifactJewelLevelCfg>> getArtifactJewelLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_artifact_jewel_levelup";
		return readArtifactJewelLevel(sql);
	}

	@Override
	public List<ArtifactJewelSuitCfg> getArtifactJewelSuit() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_artifact_jewel_suit";
		return readArtifactJewelSuit(sql);
	}
	
	private Map<Integer, ArtifactInfoCfg> readArtifactInfo(String sql){
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		Map<Integer, ArtifactInfoCfg> infos = null;
		ArtifactInfoCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, ArtifactInfoCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ArtifactInfoCfg();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setIcon(rs.getInt("icon"));
					info.setType(rs.getInt("type"));
					info.setJewel1(rs.getInt("jewel1"));
					info.setLevel1(rs.getInt("level1"));
					info.setJewel2(rs.getInt("jewel2"));
					info.setLevel2(rs.getInt("level2"));
					info.setJewel3(rs.getInt("jewel3"));
					info.setLevel3(rs.getInt("level3"));
					info.setJewel4(rs.getInt("jewel4"));
					info.setLevel4(rs.getInt("level4"));
					
					infos.put(info.getId(), info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}
	
	private Map<Long, ArtifactLevelupCfg> readArtifactLevel(String sql){
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		Map<Long, ArtifactLevelupCfg> infos = null;
		ArtifactLevelupCfg info = null;
		if(pst != null){
			infos = new HashMap<Long, ArtifactLevelupCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ArtifactLevelupCfg();
					info.setId(rs.getLong("id"));
					info.setArtifactId(rs.getInt("artifactId"));
					info.setLv(rs.getInt("lv"));
					info.setItem(rs.getInt("item"));
					info.setCount(rs.getInt("count"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setAttlist();
					
					infos.put(info.getId(), info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}
	
	private Map<Long, ArtifactGradeupCfg> readArtifactGrade(String sql){
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		Map<Long, ArtifactGradeupCfg> infos = null;
		ArtifactGradeupCfg info = null;
		if(pst != null){
			infos = new HashMap<Long, ArtifactGradeupCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ArtifactGradeupCfg();
					info.setId(rs.getLong("id"));
					info.setArtifactId(rs.getInt("artifactId"));
					info.setStarLvl(rs.getInt("starLvl"));
					info.setStarNum(rs.getInt("starNum"));
					info.setColor(rs.getInt("color"));
					info.setAttr(rs.getInt("attr"));
					info.setCostItem(rs.getInt("costItem"));
					info.setCostNum(rs.getInt("costNum"));
					info.setStarAmuletId(rs.getInt("starAmuletId"));
					info.setStarAmuletNum(rs.getInt("starAmuletNum"));
					info.setBlessMax(rs.getInt("blessMax"));
					info.setBlessrate(rs.getInt("blessrate"));
					info.setRate(rs.getInt("rate"));
					
					infos.put(info.getId(), info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}
	
	private List<ArtifactSuitCfg> readArtifactSuit(String sql){
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		List<ArtifactSuitCfg> infos = null;
		ArtifactSuitCfg info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ArtifactSuitCfg();
					info.setId(rs.getInt("id"));
					info.setSuitid(rs.getInt("suitid"));
					info.setAtt(rs.getInt("att"));
					
					infos.add(info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}
	
	private Map<Integer, List<ArtifactJewelLevelCfg>> readArtifactJewelLevel(String sql){
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		Map<Integer, List<ArtifactJewelLevelCfg>> infos = null;
		ArtifactJewelLevelCfg info = null;
		if(pst != null){
			infos = new HashMap<>();
			List<ArtifactJewelLevelCfg> levels;
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ArtifactJewelLevelCfg();
					info.setId(rs.getLong("id"));
					info.setJewelId(rs.getInt("jewelId"));
					info.setLevel(rs.getInt("level"));
					info.setAttr(rs.getInt("attr"));
					info.setItem(rs.getInt("item"));
					info.setCount(rs.getInt("count"));
					info.setExp(rs.getInt("exp"));
					info.setMaxExp(rs.getInt("maxExp"));
					info.setLevelItem(rs.getInt("levelItem"));
					info.setLevelCount(rs.getInt("levelCount"));
					
					levels = infos.get(info.getJewelId());
					if(levels == null){
						levels = new ArrayList<>();
						infos.put(info.getJewelId(), levels);
					}
					levels.add(info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}
	
	private List<ArtifactJewelSuitCfg> readArtifactJewelSuit(String sql){
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		List<ArtifactJewelSuitCfg> infos = null;
		ArtifactJewelSuitCfg info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ArtifactJewelSuitCfg();
					info.setId(rs.getInt("id"));
					info.setLevel(rs.getInt("level"));
					info.setAttr1(rs.getInt("attr1"));
					info.setAttr2(rs.getInt("attr2"));
					info.setAttr3(rs.getInt("attr3"));
					info.setAttr4(rs.getInt("attr4"));
					info.setAttr5(rs.getInt("attr5"));
					info.setAttr6(rs.getInt("attr6"));
					info.setAttr7(rs.getInt("attr7"));
					info.setAttr8(rs.getInt("attr8"));
					info.setAttList();
					
					infos.add(info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}

}
