package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.equip.EquipAwakenCfg;
import com.chuangyou.xianni.entity.equip.EquipBarGradeCfg;
import com.chuangyou.xianni.entity.equip.EquipSuitCfg;
import com.chuangyou.xianni.sql.dao.EquipConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class EquipConfigDaoImpl extends BaseDao implements EquipConfigDao {

	@Override
	public Map<Short, Map<Integer, EquipBarGradeCfg>> getGrade() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_equipbar_grade";
		return this.readGrade(sql);
	}

	@Override
	public Map<Long, EquipAwakenCfg> getAwaken() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_equip_awaken";
		return this.readAwaken(sql);
	}

//	@Override
//	public Map<Integer, EquipResolveCfg> getResolve() {
//		// TODO Auto-generated method stub
//		String sql = "select * from tb_z_equip_resolve";
//		return this.readResolve(sql);
//	}

	@Override
	public Map<Integer, EquipSuitCfg> getSuit() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_equip_suit";
		return this.readSuit(sql);
	}
	
	private Map<Short, Map<Integer, EquipBarGradeCfg>> readGrade(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Short, Map<Integer, EquipBarGradeCfg>> infos = null;
		EquipBarGradeCfg info = null;
		if(pst != null){
			infos = new HashMap<Short, Map<Integer,EquipBarGradeCfg>>();
			Map<Integer, EquipBarGradeCfg> barGrades = null;
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new EquipBarGradeCfg();
					info.setPosition(rs.getShort("position"));
					info.setLevel(rs.getInt("level"));
					info.setUpgradeItem1(rs.getInt("upgradeItem1"));
					info.setUpgradeItemNum1(rs.getInt("upgradeItemNum1"));
					info.setUpgradeItem2(rs.getInt("upgradeItem2"));
					info.setUpgradeItemNum2(rs.getInt("upgradeItemNum2"));
					info.setUpgradeItem3(rs.getInt("upgradeItem3"));
					info.setUpgradeItemNum3(rs.getInt("upgradeItemNum3"));
					info.setRate(rs.getInt("rate"));
					info.setBlessMax(rs.getInt("blessMax"));
					info.setFailBlessMin(rs.getInt("failBlessMin"));
					info.setFailBlessMax(rs.getInt("failBlessMax"));
					info.setBlessValve(rs.getInt("blessValve"));
					info.setAddProperty(rs.getInt("addProperty"));
					
					barGrades = infos.get(info.getPosition());
					if(barGrades == null){
						barGrades = new HashMap<Integer, EquipBarGradeCfg>();
						infos.put(info.getPosition(), barGrades);
					}
					barGrades.put(info.getLevel(), info);
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
	
	private Map<Long, EquipAwakenCfg> readAwaken(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Long, EquipAwakenCfg> infos = null;
		EquipAwakenCfg info = null;
		if(pst != null){
			infos = new HashMap<Long, EquipAwakenCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new EquipAwakenCfg();
					info.setId(rs.getLong("id"));
					info.setWeaponId(rs.getInt("weaponId"));
					info.setLevel(rs.getInt("level"));
					info.setPoint(rs.getInt("point"));
					info.setMaxPoint(rs.getInt("maxPoint"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setNeedItemNum(rs.getInt("needItemNum"));
					info.setSkillId(rs.getInt("skillId"));
					info.setRate(rs.getInt("rate"));
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
	
//	private Map<Integer, EquipResolveCfg> readResolve(String sqlText){
//		PreparedStatement pst = execQuery(sqlText);
//		ResultSet rs = null;
//		Map<Integer, EquipResolveCfg> infos = null;
//		EquipResolveCfg info = null;
//		if(pst != null){
//			infos = new HashMap<Integer, EquipResolveCfg>();
//			try {
//				rs = pst.executeQuery();
//				while(rs.next()){
//					info = new EquipResolveCfg();
//					info.setEquipId(rs.getInt("equipId"));
//					info.setItem1(rs.getInt("item1"));
//					info.setNum1(rs.getInt("num1"));
//					info.setItem2(rs.getInt("item2"));
//					info.setNum2(rs.getInt("num2"));
//					info.setItem3(rs.getInt("item3"));
//					info.setNum3(rs.getInt("num3"));
//					info.setItem4(rs.getInt("item4"));
//					info.setNum4(rs.getInt("num4"));
//					info.setItem5(rs.getInt("item5"));
//					info.setNum5(rs.getInt("num5"));
//					info.setExp(rs.getLong("exp"));
//					infos.put(info.getEquipId(), info);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				infos = null;
//				
//				Log.error("执行出错" + sqlText, e);
//			} finally {
//				closeConn(pst, rs);
//			}
//		}
//		return infos;
//	}
	
	private Map<Integer, EquipSuitCfg> readSuit(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, EquipSuitCfg> infos = null;
		EquipSuitCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, EquipSuitCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new EquipSuitCfg();
					info.setId(rs.getInt("id"));
					info.setStone1(rs.getInt("stone1"));
					info.setStone2(rs.getInt("stone2"));
					info.setStone3(rs.getInt("stone3"));
					info.setStone4(rs.getInt("stone4"));
					info.setStone5(rs.getInt("stone5"));
					info.setStone6(rs.getInt("stone6"));
					info.setStone7(rs.getInt("stone7"));
					info.setStone8(rs.getInt("stone8"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt1(rs.getInt("att2"));
					info.setAtt1(rs.getInt("att3"));
					info.setAtt1(rs.getInt("att4"));
					info.setAtt1(rs.getInt("att5"));
					info.setAtt1(rs.getInt("att6"));
					info.setAtt1(rs.getInt("att7"));
					info.setAtt1(rs.getInt("att8"));
					info.setResolveItem(rs.getInt("resolveItem"));
					info.setResolveNum(rs.getInt("resolveNum"));
					
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
