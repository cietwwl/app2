package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.sql.dao.GuildHistoryDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class GuildHistoryDaoImpl extends BaseDao implements GuildHistoryDao {

	@Override
	public GuildMemberInfo get(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild_history where playerId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(GuildMemberInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		
		String sql = "insert tb_u_guild_history(playerId,guildId,job,contributionTotal,contribution,fortune,consumeSupply) "
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
		
		String sql = "update tb_u_guild_history set guildId = ?,job = ?,contributionTotal = ?,"
				+ "contribution = ?,fortune = ?,consumeSupply = ? where playerId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getGuildId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getJob()));
		params.put(3, new DbParameter(Types.BIGINT, info.getContributionTotal()));
		params.put(4, new DbParameter(Types.BIGINT, info.getContribution()));
		params.put(5, new DbParameter(Types.INTEGER, info.getFortune()));
		params.put(6, new DbParameter(Types.BIGINT, info.getConsumeSupply()));
		params.put(7, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitUpdate(result);
		return result;
	}
	
	private GuildMemberInfo read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		GuildMemberInfo info = null;
		if(pst != null){
			try {
				rs = pst.executeQuery();
				if(rs.next()){
					info = new GuildMemberInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setGuildId(rs.getInt("guildId"));
					info.setJob(rs.getInt("job"));
					info.setContributionTotal(rs.getLong("contributionTotal"));
					info.setContribution(rs.getLong("contribution"));
					info.setFortune(rs.getInt("fortune"));
					info.setConsumeSupply(rs.getLong("consumeSupply"));
					info.setOp(Option.None);
				}
			} catch (Exception e) {
				// TODO: handle exception
				info = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return info;
	}

}
