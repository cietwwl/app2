package com.chuangyou.xianni.sql.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildLogInfo;
import com.chuangyou.xianni.sql.dao.GuildLogInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class GuildLogInfoDaoImpl extends BaseDao implements GuildLogInfoDao {

	@Override
	public List<GuildLogInfo> getAll(int guildId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_log_guild where guildId = ? and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(operateTime) order by operateTime desc";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		
		return read(sql, params);
	}

	@Override
	public boolean add(GuildLogInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		
		String sql = "insert tb_log_guild(guildId,operateTime,operateType,playerId,value1,value2) "
				+ " values(?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getGuildId()));
		params.put(2, new DbParameter(Types.TIMESTAMP, info.getOperateTime()));
		params.put(3, new DbParameter(Types.SMALLINT, info.getOperateType()));
		params.put(4, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(5, new DbParameter(Types.BIGINT, info.getValue1()));
		params.put(6, new DbParameter(Types.BIGINT, info.getValue2()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean remove(GuildLogInfo info) {
		// TODO Auto-generated method stub
		if(info.getOp() == Option.Insert){
			return this.add(info);
		}
		return true;
	}
	
	private List<GuildLogInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		List<GuildLogInfo> infos = null;
		GuildLogInfo info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildLogInfo();
					info.setGuildId(rs.getInt("guildId"));
					Timestamp time = rs.getTimestamp("operateTime");
					if(time != null){
						info.setOperateTime(new Date(time.getTime()));
					}
					info.setOperateType(rs.getShort("operateType"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setValue1(rs.getLong("value1"));
					info.setValue2(rs.getLong("value2"));
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

}
