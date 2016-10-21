package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpGradeCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpRefineCfg;
import com.chuangyou.xianni.sql.dao.MagicwpConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class MagicwpConfigDaoImpl extends BaseDao implements MagicwpConfigDao {

	@Override
	public Map<Integer, MagicwpCfg> getMagic() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_magicwp";
		return readMagicwp(sql);
	}

	@Override
	public Map<Integer, MagicwpLevelCfg> getLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_magicwp_level";
		return readMagicwpLevel(sql);
	}

	@Override
	public Map<Integer, MagicwpGradeCfg> getGrade() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_magicwp_grade";
		return readMagicwpGrade(sql);
	}

	@Override
	public Map<Integer, MagicwpRefineCfg> getRefine() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_magicwp_refine";
		return readMagicwpRefine(sql);
	}

	@Override
	public Map<Integer, MagicwpBanCfg> getBan() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_magicwp_ban";
		return readMagicwpBan(sql);
	}

	@Override
	public Map<Integer, MagicwpBanLevelCfg> getBanLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_magicwp_ban_level";
		return readMagicwpBanLevel(sql);
	}
	
	private Map<Integer, MagicwpCfg> readMagicwp(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MagicwpCfg> infos = null;
		MagicwpCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpCfg();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setColor(rs.getByte("color"));
					info.setIcon(rs.getInt("icon"));
					info.setModel(rs.getString("model"));
					info.setIsSpecial(rs.getByte("isSpecial"));
					info.setNeedDay(rs.getInt("needDay"));
					info.setNeedVip(rs.getInt("needVip"));
					info.setActiveCheck(rs.getInt("activeCheck"));
					info.setItemId(rs.getInt("itemId"));
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
	
	private Map<Integer, MagicwpLevelCfg> readMagicwpLevel(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MagicwpLevelCfg> infos = null;
		MagicwpLevelCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpLevelCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpLevelCfg();
					info.setId(rs.getInt("id"));
					info.setLv(rs.getInt("lv"));
					info.setNeedGold(rs.getInt("needGold"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setAtt9(rs.getInt("att9"));
					info.setAtt10(rs.getInt("att10"));
					info.setUpLevCd(rs.getInt("upLevCd"));
					info.setClearCdCash(rs.getInt("clearCdCash"));
					info.setAttMap();
					
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
	
	private Map<Integer, MagicwpGradeCfg> readMagicwpGrade(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MagicwpGradeCfg> infos = null;
		MagicwpGradeCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpGradeCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpGradeCfg();
					info.setId(rs.getInt("id"));
					info.setJieji(rs.getInt("jieji"));
					info.setNeedLv(rs.getInt("needLv"));
					info.setJinjieItem(rs.getInt("jinjieItem"));
					info.setItemNum(rs.getInt("itemNum"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setMaxAttMap();
					
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
	
	private Map<Integer, MagicwpRefineCfg> readMagicwpRefine(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MagicwpRefineCfg> infos = null;
		MagicwpRefineCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpRefineCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpRefineCfg();
					info.setId(rs.getInt("id"));
					info.setPro(rs.getInt("pro"));
					info.setRandomMin1(rs.getInt("randomMin1"));
					info.setRandomMax1(rs.getInt("randomMax1"));
					
					info.setRandomMin2(rs.getInt("randomMin2"));
					info.setRandomMax2(rs.getInt("randomMax2"));
					
					info.setRandomMin3(rs.getInt("randomMin3"));
					info.setRandomMax3(rs.getInt("randomMax3"));
					
					info.setRandomMin4(rs.getInt("randomMin4"));
					info.setRandomMax4(rs.getInt("randomMax4"));
					
					info.setRandomMin5(rs.getInt("randomMin5"));
					info.setRandomMax5(rs.getInt("randomMax5"));
					
					info.setRandomMin6(rs.getInt("randomMin6"));
					info.setRandomMax6(rs.getInt("randomMax6"));
					
					info.setRandomMin7(rs.getInt("randomMin7"));
					info.setRandomMax7(rs.getInt("randomMax7"));
					
					info.setRandomMin8(rs.getInt("randomMin8"));
					info.setRandomMax8(rs.getInt("randomMax8"));
					
					info.setAttMap();
					
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
	
	private Map<Integer, MagicwpBanCfg> readMagicwpBan(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MagicwpBanCfg> infos = null;
		MagicwpBanCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpBanCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpBanCfg();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setIcon(rs.getInt("icon"));
					info.setActiveItem1(rs.getInt("activeItem1"));
					info.setActiveItem2(rs.getInt("activeItem2"));
					info.setActiveItem3(rs.getInt("activeItem3"));
					info.setActiveItem4(rs.getInt("activeItem4"));
					info.setSkillId(rs.getInt("skillId"));
					info.setExp(rs.getInt("exp"));
					info.setDes(rs.getString("des"));
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
	
	private Map<Integer, MagicwpBanLevelCfg> readMagicwpBanLevel(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MagicwpBanLevelCfg> infos = null;
		MagicwpBanLevelCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MagicwpBanLevelCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MagicwpBanLevelCfg();
					info.setId(rs.getInt("id"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setExp(rs.getInt("exp"));
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

}
