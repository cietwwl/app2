package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildApplyInfo;
import com.chuangyou.xianni.sql.dao.GuildApplyDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class GuildApplyDaoImpl extends BaseDao implements GuildApplyDao {

	@Override
	public ConcurrentHashMap<Long, GuildApplyInfo> getGuildAll(int guildId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild_apply where guildId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		return read(sql, params);
	}
	
	@Override
	public boolean add(GuildApplyInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		
		String sql = "insert tb_u_guild_apply(guildId,playerId,applyTime) values(?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getGuildId()));
		params.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(3, new DbParameter(Types.BIGINT, info.getApplyTime()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean remove(int guildId, long playerId) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		String sql = "delete from tb_u_guild_apply where guildId = ? and playerId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		params.put(2, new DbParameter(Types.BIGINT, playerId));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		return result;
	}
	
	@Override
	public boolean removeAll(int guildId) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		String sql = "delete from tb_u_guild_apply where guildId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		return result;
	}
	
	private ConcurrentHashMap<Long, GuildApplyInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		ConcurrentHashMap<Long, GuildApplyInfo> infos = null;
		GuildApplyInfo info = null;
		if(pst != null){
			infos = new ConcurrentHashMap<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildApplyInfo();
					info.setGuildId(rs.getInt("guildId"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setApplyTime(rs.getLong("applyTime"));
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
