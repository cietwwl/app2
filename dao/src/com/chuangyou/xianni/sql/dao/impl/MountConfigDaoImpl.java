package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.mount.MountEquipCfg;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.mount.MountLevelCfg;
import com.chuangyou.xianni.entity.mount.MountWeaponCfg;
import com.chuangyou.xianni.sql.dao.MountConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class MountConfigDaoImpl extends BaseDao implements MountConfigDao {

	@Override
	public Map<Integer, MountLevelCfg> getLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_mount_level";
		
		return this.readLevel(sql);
	}

	@Override
	public Map<Integer, MountGradeCfg> getGrade() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_mount_grade";
		return this.readGrade(sql);
	}

	@Override
	public Map<Integer, Map<Integer, MountEquipCfg>> getEquip() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_mount_equip";
		return this.readEquip(sql);
	}

	@Override
	public Map<Integer, MountWeaponCfg> getWeapon() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_mount_weapon";
		return this.readWeapon(sql);
	}
	
	private Map<Integer, MountLevelCfg> readLevel(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MountLevelCfg> infos = null;
		MountLevelCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MountLevelCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MountLevelCfg();
					info.setLevel(rs.getInt("level"));
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

	private Map<Integer, MountGradeCfg> readGrade(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MountGradeCfg> infos = null;
		MountGradeCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MountGradeCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MountGradeCfg();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setGrade(rs.getInt("grade"));
					info.setColor(rs.getInt("color"));
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
					info.setGetMode(rs.getString("getMode"));
					info.setUpgradeItem(rs.getInt("upgradeItem"));
					info.setUpgradeItemNum(rs.getInt("upgradeItemNum"));
					info.setRate(rs.getInt("rate"));
					info.setBlessMax(rs.getInt("blessMax"));
					info.setFailBlessMin(rs.getInt("failBlessMin"));
					info.setFailBlessMax(rs.getInt("failBlessMax"));
					info.setBlessValve(rs.getInt("blessValve"));
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
	
	private Map<Integer, Map<Integer, MountEquipCfg>> readEquip(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, Map<Integer, MountEquipCfg>> infos = null;
		MountEquipCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, Map<Integer,MountEquipCfg>>();
			Map<Integer, MountEquipCfg> equipLevels = null;
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MountEquipCfg();
					info.setId(rs.getInt("id"));
					info.setLevel(rs.getInt("level"));
					info.setUpLevItem(rs.getInt("upLevItem"));
					info.setUpLevItemNum(rs.getInt("upLevItemNum"));
					info.setRate(rs.getInt("rate"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					
					equipLevels = infos.get(info.getId());
					if(equipLevels == null){
						equipLevels = new HashMap<Integer, MountEquipCfg>();
						infos.put(info.getId(), equipLevels);
					}
					equipLevels.put(info.getLevel(), info);
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
	
	private Map<Integer, MountWeaponCfg> readWeapon(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, MountWeaponCfg> infos = null;
		MountWeaponCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, MountWeaponCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new MountWeaponCfg();
					info.setGrade(rs.getInt("grade"));
					info.setModel(rs.getInt("model"));
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
					info.setUpgradeItem(rs.getInt("upgradeItem"));
					info.setUpgradeItemNum(rs.getInt("upgradeItemNum"));
					info.setRate(rs.getInt("rate"));
					info.setBlessMax(rs.getInt("blessMax"));
					info.setFailBlessMin(rs.getInt("failBlessMin"));
					info.setFailBlessMax(rs.getInt("failBlessMax"));
					info.setBlessValve(rs.getInt("blessValve"));
					infos.put(info.getGrade(), info);
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
