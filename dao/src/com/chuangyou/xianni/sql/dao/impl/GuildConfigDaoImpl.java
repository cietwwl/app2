package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.guild.GuildDonateCfg;
import com.chuangyou.xianni.entity.guild.GuildJobCfg;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildSkillCfg;
import com.chuangyou.xianni.entity.guild.GuildSystemCfg;
import com.chuangyou.xianni.entity.guild.GuildWarehouseCfg;
import com.chuangyou.xianni.sql.dao.GuildConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class GuildConfigDaoImpl extends BaseDao implements GuildConfigDao {

	@Override
	public Map<Integer, GuildJobCfg> getJob() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_guild_job";
		return readJob(sql);
	}
	
	private Map<Integer, GuildJobCfg> readJob(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, GuildJobCfg> infos = null;
		GuildJobCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, GuildJobCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildJobCfg();
					
					info.setJobId(rs.getInt("jobId"));
					info.setName(rs.getString("name"));
					info.setRepair(rs.getInt("repair"));
					info.setCostItem(rs.getInt("costItem"));
					info.setCostCount(rs.getInt("costCount"));
					info.setSeizeDay(rs.getInt("seizeDay"));
					
					infos.put(info.getJobId(), info);
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
	public Map<Integer, GuildJobPowerCfg> getJobPower() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_guild_job_power";
		return readJobPower(sql);
	}
	
	private Map<Integer, GuildJobPowerCfg> readJobPower(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, GuildJobPowerCfg> infos = null;
		GuildJobPowerCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, GuildJobPowerCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildJobPowerCfg();
					
					info.setJob(rs.getInt("job"));
					info.setJoinActivity(rs.getByte("joinActivity"));
					info.setOpenActivity(rs.getByte("openActivity"));
					info.setBlessMember(rs.getByte("blessMember"));
					info.setEnterGuildMap(rs.getByte("enterGuildMap"));
					info.setApplyHandle(rs.getByte("applyHandle"));
					info.setMassEmail(rs.getByte("massEmail"));
					info.setJoinConditionSet(rs.getByte("joinConditionSet"));
					info.setWriteNotice(rs.getByte("writeNotice"));
					info.setAppointJob(rs.getByte("appointJob"));
					info.setExit(rs.getByte("exit"));
					info.setRemoveMember(rs.getByte("removeMember"));
					info.setGiveLeader(rs.getByte("giveLeader"));
					info.setReplaceLeader(rs.getByte("replaceLeader"));
					info.setUseWarehouse(rs.getByte("useWarehouse"));
					info.setBuildLevelup(rs.getByte("buildLevelup"));
					info.setJobCount(rs.getInt("jobCount"));
					
					infos.put(info.getJob(), info);
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
	public Map<Integer, GuildDonateCfg> getDonate() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_guild_donate";
		return readDonate(sql);
	}
	
	private Map<Integer, GuildDonateCfg> readDonate(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, GuildDonateCfg> infos = null;
		GuildDonateCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, GuildDonateCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildDonateCfg();
					
					info.setType(rs.getInt("type"));
					info.setItem(rs.getInt("item"));
					info.setContribution(rs.getLong("contribution"));
					info.setBuildExp(rs.getLong("buildExp"));
					info.setSupply(rs.getLong("supply"));
					
					infos.put(info.getType(), info);
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
	public Map<Integer, GuildWarehouseCfg> getWarehouse() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_guild_warehouse";
		return readWarehouse(sql);
	}
	private Map<Integer, GuildWarehouseCfg> readWarehouse(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, GuildWarehouseCfg> infos = null;
		GuildWarehouseCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, GuildWarehouseCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildWarehouseCfg();
					info.setLevel(rs.getInt("level"));
					info.setGuildMaxSupply(rs.getLong("guildMaxSupply"));
					info.setWarehouseSize(rs.getInt("warehouseSize"));
					
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
	
	@Override
	public Map<Integer, Map<Integer, GuildSkillCfg>> getSkill() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_guild_skill_level";
		return readSkill(sql);
	}
	
	private Map<Integer, Map<Integer, GuildSkillCfg>> readSkill(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, Map<Integer, GuildSkillCfg>> infos = null;
		GuildSkillCfg info = null;
		if(pst != null){
			infos = new HashMap<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildSkillCfg();
					info.setId(rs.getInt("id"));
					info.setLevel(rs.getInt("level"));
					info.setSkillId(rs.getInt("skillId"));
					info.setNeedSupply(rs.getLong("needSupply"));
					info.setNeedContribution(rs.getLong("needContribution"));
					info.setSkillShopLevel(rs.getInt("skillShopLevel"));
					info.setPlayerStateLevel(rs.getInt("playerStateLevel"));
					
					Map<Integer, GuildSkillCfg> skillLevels = infos.get(info.getId());
					if(skillLevels == null){
						skillLevels = new HashMap<Integer, GuildSkillCfg>();
						infos.put(info.getId(), skillLevels);
					}
					skillLevels.put(info.getLevel(), info);
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
	public List<GuildSystemCfg> getGuildCfg() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_guild_system";
		return readGuild(sql);
	}
	
	private List<GuildSystemCfg> readGuild(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		List<GuildSystemCfg> infos = null;
		GuildSystemCfg info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildSystemCfg();
					
					info.setGuildId(rs.getInt("guildId"));
					info.setName(rs.getString("name"));
					info.setLevel(rs.getInt("level"));
					info.setMainBuildLevel(rs.getInt("mainBuildLevel"));
					info.setSkillShopLevel(rs.getInt("skillShopLevel"));
					info.setShopLevel(rs.getInt("shopLevel"));
					info.setWarehouseLevel(rs.getInt("warehouseLevel"));
					info.setNotice(rs.getString("notice"));
					
					infos.add(info);
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
