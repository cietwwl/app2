package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;
import com.chuangyou.xianni.sql.dao.FieldBossConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class FieldBossConfigDaoImpl extends BaseDao implements FieldBossConfigDao {

	@Override
	public Map<Integer, FieldBossCfg> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_field_boss";
		return read(sql);
	}
	
	private Map<Integer, FieldBossCfg> read(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, FieldBossCfg> infos = null;
		FieldBossCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, FieldBossCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new FieldBossCfg();
					info.setMonsterId(rs.getInt("monsterId"));
					info.setTagId(rs.getInt("tagId"));
					info.setTagIdEnd(rs.getInt("tagIdEnd"));
					info.setType(rs.getInt("type"));
					info.setCreateTimes(rs.getString("createTimes"));
					info.setCampaignChance(rs.getInt("campaignChance"));
					info.setOpenCampaignId(rs.getInt("openCampaignId"));
					info.setMinLevel(rs.getInt("minLevel"));
					info.setMaxLevel(rs.getInt("maxLevel"));
					info.setSingleTag(rs.getInt("singleTag"));
					info.setMultiTag(rs.getInt("multiTag"));
					info.setBornNotice(rs.getInt("bornNotice"));
					info.setDeadNotice(rs.getInt("deadNotice"));
					info.setSingleNotice(rs.getInt("singleNotice"));
					info.setMultiNotice(rs.getInt("multiNotice"));
					info.setFirstAwardItem(rs.getInt("firstAwardItem"));
					info.setFirstAwardNum(rs.getInt("firstAwardNum"));
					info.setNormalAwardItem(rs.getInt("normalAwardItem"));
					info.setNormalAwardNum(rs.getInt("normalAwardNum"));
					info.setDmgAwardItem(rs.getInt("dmgAwardItem"));
					info.setDmgAwardNum(rs.getInt("dmgAwardNum"));
					info.setShieldAwardItem(rs.getInt("shieldAwardItem"));
					info.setShieldAwardNum(rs.getInt("shieldAwardNum"));
					info.setTransferNpcId(rs.getInt("transferNpcId"));
					info.setTimeList();
					
					infos.put(info.getMonsterId(), info);
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
