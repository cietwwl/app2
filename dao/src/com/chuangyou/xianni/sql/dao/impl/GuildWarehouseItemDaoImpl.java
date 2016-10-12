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
import com.chuangyou.xianni.entity.guild.GuildWarehouseItemInfo;
import com.chuangyou.xianni.sql.dao.GuildWarehouseItemDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class GuildWarehouseItemDaoImpl extends BaseDao implements GuildWarehouseItemDao {

	@Override
	public List<GuildWarehouseItemInfo> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild_warehouse";
		return read(sql);
	}
	
	@Override
	public ConcurrentHashMap<Integer, GuildWarehouseItemInfo> getAll(int guildId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild_warehouse where guildId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		return read(sql, params);
	}

	@Override
	public boolean add(GuildWarehouseItemInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		
		String sql = "insert tb_u_guild_warehouse(guildId, itemTempId, amount) values(?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getGuildId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getItemTempId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getAmount()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(GuildWarehouseItemInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		
		String sql = "update tb_u_guild_warehouse set amount = ? where guild = ? and itemTempId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getAmount()));
		params.put(2, new DbParameter(Types.INTEGER, info.getGuildId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getItemTempId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitUpdate(result);
		return result;
	}

	@Override
	public boolean remove(int guildId, int itemTempId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_guild_warehouse where guild = ? and itemTempId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		params.put(2, new DbParameter(Types.INTEGER, itemTempId));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}

	@Override
	public boolean removeAll(int guildId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_guild_warehouse where guild = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, guildId));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}
	
	private List<GuildWarehouseItemInfo> read(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		List<GuildWarehouseItemInfo> infos = null;
		GuildWarehouseItemInfo info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildWarehouseItemInfo();
					info.setGuildId(rs.getInt("guildId"));
					info.setItemTempId(rs.getInt("itemTempId"));
					info.setAmount(rs.getInt("amount"));
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
	
	private ConcurrentHashMap<Integer, GuildWarehouseItemInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		ConcurrentHashMap<Integer, GuildWarehouseItemInfo> infos = null;
		GuildWarehouseItemInfo info = null;
		if(pst != null){
			infos = new ConcurrentHashMap<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildWarehouseItemInfo();
					info.setGuildId(rs.getInt("guildId"));
					info.setItemTempId(rs.getInt("itemTempId"));
					info.setAmount(rs.getInt("amount"));
					info.setOp(Option.None);
					infos.put(info.getItemTempId(), info);
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
