package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildSkillInfo;
import com.chuangyou.xianni.sql.dao.GuildSkillDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class GuildSkillDaoImpl extends BaseDao implements GuildSkillDao {

	@Override
	public Map<Integer, GuildSkillInfo> getAll(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild_skill where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, playerId));
		return read(sql, params);
	}

	@Override
	public boolean add(GuildSkillInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		
		String sql = "insert tb_u_guild_skill(playerId,guildSkillId,level) "
				+ " values(?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getGuildSkillId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getLevel()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(GuildSkillInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		
		String sql = "update tb_u_guild_skill set level = ? where playerId=? and guildSkillId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getGuildSkillId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitUpdate(result);
		return result;
	}
	
	private Map<Integer, GuildSkillInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		Map<Integer, GuildSkillInfo> infos = null;
		GuildSkillInfo info = null;
		if(pst != null){
			infos = new HashMap<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildSkillInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setGuildSkillId(rs.getInt("guildSkillId"));
					info.setLevel(rs.getInt("level"));
					info.setOp(Option.None);
					
					infos.put(info.getGuildSkillId(), info);
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
