package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.pet.PetGradeCfg;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetLevelCfg;
import com.chuangyou.xianni.entity.pet.PetPhysiqueCfg;
import com.chuangyou.xianni.entity.pet.PetQualityCfg;
import com.chuangyou.xianni.entity.pet.PetSkillInfoCfg;
import com.chuangyou.xianni.entity.pet.PetSkillLevelCfg;
import com.chuangyou.xianni.entity.pet.PetSkillSlotCfg;
import com.chuangyou.xianni.entity.pet.PetSoulCfg;
import com.chuangyou.xianni.sql.dao.PetConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class PetConfigDaoImpl extends BaseDao implements PetConfigDao {

	@Override
	public Map<Integer, PetInfoCfg> getPetInfo() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_info";
		return readPetInfo(sql);
	}
	
	private Map<Integer, PetInfoCfg> readPetInfo(String sqlText) {
		// TODO Auto-generated method stub

		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetInfoCfg> infos = null;
		PetInfoCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetInfoCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetInfoCfg();
					info.setId(rs.getInt("id"));
					info.setName(rs.getString("name"));
					info.setIcon(rs.getInt("icon"));
					info.setModel(rs.getString("model"));
					info.setIsSpecial(rs.getByte("isSpecial"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setSkillId(rs.getInt("skillId"));
					info.setZizhiItem1(rs.getInt("zizhiItem1"));
					info.setZizhiItem2(rs.getInt("zizhiItem2"));
					info.setAddZizhi(rs.getInt("addZizhi"));
					info.setDes(rs.getString("des"));
					info.setZizhiMax(rs.getInt("zizhiMax"));
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

	@Override
	public Map<Integer, PetGradeCfg> getGrade() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_grade";
		return readGrade(sql);
	}
	
	private Map<Integer, PetGradeCfg> readGrade(String sqlText) {
		// TODO Auto-generated method stub

		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetGradeCfg> infos = null;
		PetGradeCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetGradeCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetGradeCfg();
					info.setId(rs.getInt("id"));
					info.setGrade(rs.getInt("grade"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setUpgradeItem(rs.getInt("upgradeItem"));
					info.setUpgradeItemNum(rs.getInt("upgradeItemNum"));
					info.setRate(rs.getShort("rate"));
					info.setBlessMax(rs.getInt("blessMax"));
					info.setFailBlessMin(rs.getInt("failBlessMin"));
					info.setFailBlessMax(rs.getInt("failBlessMax"));
					info.setBlessValve(rs.getInt("blessValve"));
					info.setAttList();
					
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

	@Override
	public Map<Integer, PetLevelCfg> getLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_level";
		return readLevel(sql);
	}
	
	private Map<Integer, PetLevelCfg> readLevel(String sqlText) {
		// TODO Auto-generated method stub

		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetLevelCfg> infos = null;
		PetLevelCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetLevelCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetLevelCfg();
					info.setId(rs.getInt("id"));
					info.setLv(rs.getInt("lv"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setExp(rs.getInt("exp"));
					info.setItemId1(rs.getInt("itemId1"));
					info.setItemExp1(rs.getInt("itemExp1"));
					info.setItemId2(rs.getInt("itemId2"));
					info.setItemExp2(rs.getInt("itemExp2"));
					info.setItemId3(rs.getInt("itemId3"));
					info.setItemExp3(rs.getInt("itemExp3"));
					info.setAttList();
					
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

	@Override
	public Map<Integer, PetPhysiqueCfg> getPhysique() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_physique";
		return readPhysique(sql);
	}
	
	private Map<Integer, PetPhysiqueCfg> readPhysique(String sqlText) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetPhysiqueCfg> infos = null;
		PetPhysiqueCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetPhysiqueCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetPhysiqueCfg();
					info.setId(rs.getInt("id"));
					info.setLv(rs.getInt("lv"));
					info.setName(rs.getString("name"));
					info.setStar(rs.getInt("star"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setNeedNum(rs.getInt("needNum"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setAttList();
					
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

	@Override
	public Map<Integer, PetQualityCfg> getQuality() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_quality";
		return readQuality(sql);
	}
	
	private Map<Integer, PetQualityCfg> readQuality(String sqlText) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetQualityCfg> infos = null;
		PetQualityCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetQualityCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetQualityCfg();
					info.setId(rs.getInt("id"));
					info.setLv(rs.getInt("lv"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setNeedNum(rs.getInt("needNum"));
					info.setProMax(rs.getInt("proMax"));
					info.setRate(rs.getShort("rate"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setAttPer(rs.getShort("attPer"));
					info.setAttList();
					
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

	@Override
	public Map<Integer, PetSoulCfg> getSoul() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_soul";
		return readSoul(sql);
	}
	
	private Map<Integer, PetSoulCfg> readSoul(String sqlText) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetSoulCfg> infos = null;
		PetSoulCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetSoulCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetSoulCfg();
					info.setLv(rs.getInt("lv"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setNormalExp(rs.getInt("normalExp"));
					info.setCriticalExp(rs.getInt("criticalExp"));
					info.setCriticalPer(rs.getShort("criticalPer"));
					info.setExp(rs.getInt("exp"));
					info.setAtt1(rs.getInt("att1"));
					info.setAtt2(rs.getInt("att2"));
					info.setAtt3(rs.getInt("att3"));
					info.setAtt4(rs.getInt("att4"));
					info.setAtt5(rs.getInt("att5"));
					info.setAtt6(rs.getInt("att6"));
					info.setAtt7(rs.getInt("att7"));
					info.setAtt8(rs.getInt("att8"));
					info.setName(rs.getString("name"));
					info.setAtt9(rs.getInt("att9"));
					info.setAtt10(rs.getInt("att10"));
					info.setAtt11(rs.getInt("att11"));
					info.setAttList();
					
					infos.put(info.getLv(), info);
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

	@Override
	public Map<Integer, PetSkillInfoCfg> getSkillInfo() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_skill";
		return readSkillInfo(sql);
	}
	
	private Map<Integer, PetSkillInfoCfg> readSkillInfo(String sqlText) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetSkillInfoCfg> infos = null;
		PetSkillInfoCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetSkillInfoCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetSkillInfoCfg();
					info.setSkillid(rs.getInt("skillid"));
					info.setNeedjihuopet(rs.getInt("needjihuopet"));
					info.setNeedjihuoitem(rs.getInt("needjihuoitem"));
					info.setItem(rs.getInt("item"));
					info.setItemNum(rs.getInt("itemNum"));
					infos.put(info.getSkillid(), info);
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

	@Override
	public Map<Integer, PetSkillLevelCfg> getSkillLevel() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_skill_level";
		return readSkillLevel(sql);
	}
	
	private Map<Integer, PetSkillLevelCfg> readSkillLevel(String sqlText) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetSkillLevelCfg> infos = null;
		PetSkillLevelCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetSkillLevelCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetSkillLevelCfg();
					info.setSkillid(rs.getInt("skillid"));
					info.setSkillLv(rs.getInt("skillLv"));
					info.setLvupGold(rs.getInt("lvupGold"));
					infos.put(info.getSkillid(), info);
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

	@Override
	public Map<Integer, PetSkillSlotCfg> getSkillSlot() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_pet_skill_slot";
		return readSkillSlot(sql);
	}
	
	private Map<Integer, PetSkillSlotCfg> readSkillSlot(String sqlText) {
		// TODO Auto-generated method stub
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, PetSkillSlotCfg> infos = null;
		PetSkillSlotCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, PetSkillSlotCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new PetSkillSlotCfg();
					info.setId(rs.getInt("id"));
					info.setNeedItem(rs.getInt("needItem"));
					info.setNeedItemNum(rs.getInt("needItemNum"));
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
