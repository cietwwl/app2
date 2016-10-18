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
import com.chuangyou.xianni.entity.rank.RankCfg;
import com.chuangyou.xianni.entity.rank.RankInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.sql.dao.RankDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class RankDaoImpl extends BaseDao implements RankDao {

	@Override
	public boolean execProcedure(String rankName,int rankType,int rankRange) {
		// TODO Auto-generated method stub
		
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, rankType));
		para.put(2, new DbParameter(Types.INTEGER, rankRange));
		return runProcePrepared(rankName,para);
	}

	
	@Override
	public List<RankInfo> getRankListByType(int type, int range) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_rank_info where rankType="+type+" and rankRange="+range+" order by rank limit 120";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		RankInfo info = null;
		List<RankInfo> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new ArrayList<>();
			while (rs.next()) {
				info = new RankInfo();
				info.setRankType(rs.getInt("rankType"));
				info.setRankRange(rs.getInt("rankRange"));
				info.setRank(rs.getInt("rank"));
				info.setPlayerId(rs.getLong("playerId"));
				info.setPlayerName(rs.getString("playerName"));
				info.setParamStr(rs.getString("paramStr"));
				info.setParam1(rs.getLong("param1"));
				info.setParam2(rs.getLong("param2"));
				info.setParam3(rs.getLong("param3"));
				infos.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}
		return infos;
	}
	
	@Override
	public Map<String, List<RankInfo>> getRanks() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_rank_info group by rankType,rankRange order by rank";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		RankInfo info = null;
		Map<String, List<RankInfo>> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<String, List<RankInfo>>();
			while (rs.next()) {
				info = new RankInfo();
				info.setRankType(rs.getInt("rankType"));
				info.setRankRange(rs.getInt("rankRange"));
				String str= info.getRankType()+"_"+info.getRankRange();				
				infos.put(str,getRankListByType(info.getRankType(),info.getRankRange()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}
		return infos;
	}

	
	@Override
	public boolean addTempInfo(RankTempInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(!info.beginAdd())return false;
		String sql = "insert into tb_u_rank_tempInfo (playerId,equip,magicwp,mount,pet,soul,avatar,state,artifact) values (?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(2, new DbParameter(Types.BIGINT, info.getEquip()));
		para.put(3, new DbParameter(Types.BIGINT, info.getMagicwp()));
		para.put(4, new DbParameter(Types.BIGINT, info.getMount()));
		para.put(5, new DbParameter(Types.BIGINT, info.getPet()));
		para.put(6, new DbParameter(Types.BIGINT, info.getSoul()));
		para.put(7, new DbParameter(Types.BIGINT, info.getAvatar()));
		para.put(8, new DbParameter(Types.BIGINT, info.getState()));
		para.put(9, new DbParameter(Types.BIGINT, info.getArtifact()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;	
	}

	@Override
	public boolean updateTempInfo(RankTempInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(!info.beginUpdate())return false;
		String sql = "update tb_u_rank_tempInfo set equip=?,magicwp=?,mount=?,pet=?,soul=?,avatar=?,state=?,artifact=? where playerId=?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();	
		para.put(1, new DbParameter(Types.BIGINT, info.getEquip()));
		para.put(2, new DbParameter(Types.BIGINT, info.getMagicwp()));
		para.put(3, new DbParameter(Types.BIGINT, info.getMount()));
		para.put(4, new DbParameter(Types.BIGINT, info.getPet()));
		para.put(5, new DbParameter(Types.BIGINT, info.getSoul()));
		para.put(6, new DbParameter(Types.BIGINT, info.getAvatar()));
		para.put(7, new DbParameter(Types.BIGINT, info.getState()));
		para.put(8, new DbParameter(Types.BIGINT, info.getArtifact()));
		para.put(9, new DbParameter(Types.BIGINT, info.getPlayerId()));	
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public Map<Long, RankTempInfo> getRankTempInfoMap() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_rank_tempInfo";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		RankTempInfo info = null;
		Map<Long, RankTempInfo> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new RankTempInfo();
				info.setPlayerId(rs.getLong("playerId"));
				info.setEquip(rs.getLong("equip"));
				info.setMagicwp(rs.getLong("magicwp"));
				info.setMount(rs.getLong("mount"));
				info.setPet(rs.getLong("pet"));
				info.setSoul(rs.getLong("soul"));
				info.setAvatar(rs.getLong("avatar"));
				info.setState(rs.getLong("state"));
				info.setArtifact(rs.getLong("artifact"));
				infos.put(info.getPlayerId(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}
		return infos;
		
	}


	@Override
	public Map<Integer, RankCfg> getRankRewardTypes() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_rank_config";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		RankCfg info = null;
		Map<Integer, RankCfg> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while (rs.next()) {
				info = new RankCfg();
				info.setId(rs.getInt("id"));
				info.setRewardType(rs.getInt("rewardType"));
				info.setMailTitle(rs.getString("mailTitle"));
				info.setMailText(rs.getString("mailText"));
				infos.put(info.getId(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		} finally {
			closeConn(pst, rs);
		}
		return infos;
	}



}
