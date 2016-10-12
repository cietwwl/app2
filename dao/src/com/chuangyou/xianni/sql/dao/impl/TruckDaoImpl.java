package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.truck.TruckCheckPointConfig;
import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckLevelConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.sql.dao.TruckDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class TruckDaoImpl extends BaseDao implements TruckDao {

	@Override
	public List<TruckCheckPointConfig> getCheckPoints() {
		String sql = "SELECT * FROM tb_z_truck_checkpoint;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<TruckCheckPointConfig> infos = null;
		TruckCheckPointConfig info = null;
		if (pstmt != null) {
			infos = new ArrayList<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new TruckCheckPointConfig();
					info.setId(rs.getInt("id"));
					info.setPointType(rs.getInt("npcType"));
					info.setNpcId(rs.getInt("npcId"));
					int x = rs.getInt("x");
					int y = rs.getInt("y");
					int z = rs.getInt("z");
					info.setPoint(new Vector3(x/10.00f, y/10.00f, z/10.00f));
					info.setNextScene(rs.getInt("nextScene"));
					int next_x = rs.getInt("next_x");
					int next_y = rs.getInt("next_y");
					int next_z = rs.getInt("next_z");
					info.setNextPoint(new Vector3(next_x/10.00f, next_y/10.00f, next_z/10.00f));
					info.setIndividualNum(rs.getInt("individualNum"));
					info.setFactionNum(rs.getInt("FactionNum"));
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		
		return infos;
	}
	
	@Override
	public List<TruckLevelConfig> getLevelConfig() {
		String sql = "SELECT * FROM tb_z_truck_level;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<TruckLevelConfig> infos = new ArrayList<TruckLevelConfig>();
		TruckLevelConfig info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new TruckLevelConfig();
					info.setId(rs.getInt("id"));
					info.setType(rs.getInt("type"));
					info.setLevel(rs.getInt("level"));
					info.setPoints(rs.getInt("points"));
					info.setModelId(rs.getInt("modelId"));
					info.setSkillId(rs.getInt("skillId"));
					info.setNeedExp(rs.getInt("needExp"));
					//infos.put(info.getId(), info);
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

	@Override
	public List<TruckSkillConfig> getSkillConfig() {
		String sql = "SELECT * FROM tb_z_truck_skill;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<TruckSkillConfig> infos = new ArrayList<TruckSkillConfig>();
		TruckSkillConfig info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new TruckSkillConfig();
					info.setId(rs.getInt("id"));
					info.setEscortType(rs.getInt("EscortType"));
					info.setSkillType(rs.getInt("skillType"));
					info.setLevel(rs.getInt("level"));
					info.setNextSkillId(rs.getInt("nextSkillId"));
					info.setNeedPoints(rs.getInt("needPoints"));
					info.setValueType(rs.getInt("valueType"));
					info.setValue(rs.getInt("value"));
					info.setValuePercent(rs.getInt("valuePercent"));
					info.setConsump(rs.getInt("consump"));
					info.setBuff(rs.getInt("buff"));
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

	@Override
	public List<TruckFun> getSkillFunc() {
		String sql = "SELECT * FROM tb_z_truck_func;";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<TruckFun> infos = new ArrayList<TruckFun>();
		TruckFun info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new TruckFun();
					info.setId(rs.getInt("id"));
					info.setSkillCountBase(rs.getInt("skillCountBase"));
					info.setSkillCount(rs.getInt("skillCount"));
					info.setSkillExt1(rs.getInt("skillExt1"));
					info.setSkillExt2(rs.getInt("skillExt2"));
					info.setSkillExt3(rs.getInt("skillExt3"));
					info.setSkillExt4(rs.getInt("skillExt4"));
					info.setSkillExt5(rs.getInt("skillExt5"));
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}
	
	@Override
	public boolean addTruckSkill(TruckSkillInfo info) {
		if(!info.beginAdd()) return false;
		boolean result = false;
		String sql = "INSERT INTO tb_u_truck_skill (ownerId,trucktype,type,level,skillType,skillId)"
				+ " VALUES (?,?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getOwnerId()));
		para.put(2, new DbParameter(Types.INTEGER, info.getTrucktype()));
		para.put(3, new DbParameter(Types.INTEGER, info.getType()));
		para.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		para.put(5, new DbParameter(Types.INTEGER, info.getSkillType()));
		para.put(6, new DbParameter(Types.INTEGER, info.getSkillId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean updateTruckSkill(TruckSkillInfo info) {
		if(!info.beginUpdate())return false;
		boolean result =false;
		String sql = "update tb_u_truck_skill set ownerId=?,trucktype=?,type=?,level=?,skillType=?,skillId=? where ownerId=? and trucktype=? and skillType=?";
		Map<Integer, DbParameter> para= new HashMap<>();
		para.put(1, new DbParameter(Types.BIGINT, info.getOwnerId()));
		para.put(2, new DbParameter(Types.INTEGER, info.getTrucktype()));
		para.put(3, new DbParameter(Types.INTEGER, info.getType()));
		para.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		para.put(5, new DbParameter(Types.INTEGER, info.getSkillType()));
		para.put(6, new DbParameter(Types.INTEGER, info.getSkillId()));
		para.put(7, new DbParameter(Types.BIGINT, info.getOwnerId()));
		para.put(8, new DbParameter(Types.INTEGER, info.getTrucktype()));
		para.put(9, new DbParameter(Types.INTEGER, info.getSkillType()));
		result = execNoneQuery(sql, para)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}
	
	@Override
	public List<TruckSkillInfo> getTruckSkills(long playerId, int trucktype) {
		String sql = "select * from tb_u_truck_skill where ownerId="+playerId+";";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		List<TruckSkillInfo> skillInfos = new ArrayList<TruckSkillInfo>();
		if(pstmt!=null){
			try {
				rs = pstmt.executeQuery();
				while(rs.next()){
					TruckSkillInfo skillInfo = new TruckSkillInfo();
					skillInfo.setOwnerId(rs.getLong("ownerId"));
					skillInfo.setTrucktype(rs.getInt("trucktype"));
					skillInfo.setType(rs.getInt("type"));
					skillInfo.setSkillType(rs.getInt("skillType"));
					skillInfo.setLevel(rs.getInt("level"));
					skillInfo.setSkillId(rs.getInt("skillId"));
					skillInfos.add(skillInfo);
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.error("执行出错" + sql, e);
			}finally{
				closeConn(pstmt, rs);
			}
		}
		return skillInfos;
	}

	@Override
	public boolean delTruckSkill(TruckSkillInfo info) {
		boolean result = false;
		String sql = "delete from tb_u_truck_skill where ownerId="+info.getOwnerId()+";";
		result = execNoneQuery(sql)>-1?true:false;
		return result;
	}

	

	@Override
	public boolean addTruckInfo(TruckInfo info) {
		if(!info.beginAdd()) return false;
		boolean result = false;
		String sql = "INSERT INTO tb_u_truck_info (ownerId,exp,skillPoint,level,type)"
				+ " VALUES (?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getOwnerId()));
		para.put(2, new DbParameter(Types.INTEGER, info.getExp()));
		para.put(3, new DbParameter(Types.INTEGER, info.getSkillPoint()));
		para.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		para.put(5, new DbParameter(Types.INTEGER, info.getType()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean updateTruckInfo(TruckInfo info) {
		if(!info.beginUpdate())return false;
		boolean result =false;
		String sql = "update tb_u_truck_info set ownerId=?,exp=?,skillPoint=?,level=?,type=? where ownerId=? and type=?";
		Map<Integer, DbParameter> para= new HashMap<>();
		para.put(1, new DbParameter(Types.BIGINT, info.getOwnerId()));
		para.put(2, new DbParameter(Types.INTEGER, info.getExp()));
		para.put(3, new DbParameter(Types.INTEGER, info.getSkillPoint()));
		para.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		para.put(5, new DbParameter(Types.INTEGER, info.getType()));
		para.put(6, new DbParameter(Types.BIGINT, info.getOwnerId()));
		para.put(7, new DbParameter(Types.INTEGER, info.getType()));
		result = execNoneQuery(sql, para)>-1?true:false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public boolean delTruckInfo(TruckInfo info) {
		boolean result = false;
		String sql = "delete from tb_u_truck_info where ownerId="+info.getOwnerId()+";";
		result = execNoneQuery(sql)>-1?true:false;
		return result;
	}

	@Override
	public TruckInfo getTruckInfos(long playerId, int type) {
		String sql = "select * from tb_u_truck_info where ownerId="+playerId+" and type=" + type + ";";
		PreparedStatement pstmt = execQuery(sql);
		ResultSet rs = null;
		TruckInfo truckInfo = null;
		if(pstmt!=null){
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					truckInfo = new TruckInfo();
					truckInfo.setOwnerId(rs.getLong("ownerId"));
					truckInfo.setType(rs.getInt("type"));
					truckInfo.setExp(rs.getInt("exp"));
					truckInfo.setSkillPoint(rs.getInt("skillPoint"));
					truckInfo.setLevel(rs.getInt("level"));
				}
			}catch (SQLException e) {
				Log.error("执行出错" + sql, e);
			}finally{
				closeConn(pstmt, rs);
			}
			
		}
		return truckInfo;
	}

	

	

}
