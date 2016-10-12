package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.sql.dao.GuildMemberDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class GuildMemberDaoImpl extends BaseDao implements GuildMemberDao {

	@Override
	public ConcurrentHashMap<Long, GuildMemberInfo> getGuildAll(int guildId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild_player where guildId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		return readMembers(sql, params);
	}
	
	@Override
	public List<GuildMemberInfo> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild_player";
		return read(sql);
	}

	@Override
	public boolean add(GuildMemberInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		
		String sql = "insert tb_u_guild_player(playerId,guildId,job,contributionTotal,contribution,fortune,consumeSupply) "
				+ " values(?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getGuildId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getJob()));
		params.put(4, new DbParameter(Types.BIGINT, info.getContributionTotal()));
		params.put(5, new DbParameter(Types.BIGINT, info.getContribution()));
		params.put(6, new DbParameter(Types.INTEGER, info.getFortune()));
		params.put(7, new DbParameter(Types.BIGINT, info.getConsumeSupply()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(GuildMemberInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		
		String sql = "update tb_u_guild_player set job = ?,contributionTotal = ?,"
				+ "contribution = ?,fortune = ?,consumeSupply = ? where playerId = ? and guildId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getJob()));
		params.put(2, new DbParameter(Types.BIGINT, info.getContributionTotal()));
		params.put(3, new DbParameter(Types.BIGINT, info.getContribution()));
		params.put(4, new DbParameter(Types.INTEGER, info.getFortune()));
		params.put(5, new DbParameter(Types.BIGINT, info.getConsumeSupply()));
		params.put(6, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(7, new DbParameter(Types.INTEGER, info.getGuildId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitUpdate(result);
		return result;
	}

	@Override
	public boolean remove(long playerId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_guild_player where playerId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}
	
	@Override
	public boolean removeAll(int guildId) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		String sql = "delete from tb_u_guild_player where guildId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		return result;
	}
	
	private List<GuildMemberInfo> read(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		List<GuildMemberInfo> infos = null;
		GuildMemberInfo info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildMemberInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setGuildId(rs.getInt("guildId"));
					info.setJob(rs.getInt("job"));
					info.setContributionTotal(rs.getLong("contributionTotal"));
					info.setContribution(rs.getLong("contribution"));
					info.setFortune(rs.getInt("fortune"));
					info.setConsumeSupply(rs.getLong("consumeSupply"));
					info.setOp(Option.None);
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
	
	private ConcurrentHashMap<Long, GuildMemberInfo> readMembers(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		ConcurrentHashMap<Long, GuildMemberInfo> infos = null;
		GuildMemberInfo info = null;
		if(pst != null){
			infos = new ConcurrentHashMap<Long, GuildMemberInfo>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildMemberInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setGuildId(rs.getInt("guildId"));
					info.setJob(rs.getInt("job"));
					info.setContributionTotal(rs.getLong("contributionTotal"));
					info.setContribution(rs.getLong("contribution"));
					info.setFortune(rs.getInt("fortune"));
					info.setConsumeSupply(rs.getLong("consumeSupply"));
					info.setOp(Option.None);
					infos.put(info.getPlayerId(), info);
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
