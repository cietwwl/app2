package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.npcShop.NpcShopServerCache;
import com.chuangyou.xianni.entity.npcShop.NpcShopUserCache;
import com.chuangyou.xianni.sql.dao.NpcShopDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;


/**
 * NPC商店DAO层
 * @author laofan
 *
 */
public class NpcShopDaoImpl extends BaseDao implements NpcShopDao {

	@Override
	public Map<Short,NpcShopUserCache> getUserAll(long playerId) {
		String sql = "SELECT * FROM tb_u_npc_shop_usercache WHERE playerId=" + playerId + ";";
		Map<Short,NpcShopUserCache> result = readUserCache(sql, null);
		return result;
		
	}

	private Map<Short,NpcShopUserCache> readUserCache(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Short,NpcShopUserCache> infos = null;
		NpcShopUserCache info = null;
		if (pstmt != null) {
			infos = new HashMap<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new NpcShopUserCache();
					info.setBuyNum(rs.getShort("buyNum"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setPrivateId(rs.getShort("privateId"));
					info.setResetTime(rs.getShort("resetTime"));
					infos.put(info.getPrivateId(), info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}
	
	
	@Override
	public boolean addNpcShopUserCache(NpcShopUserCache info) {
		// TODO Auto-generated method stub
		info.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_npc_shop_usercache (playerId,privateId,buyNum,resetTime)"
				+ " VALUES (?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(2, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		para.put(3, new DbParameter(Types.SMALLINT, info.getBuyNum()));
		para.put(4, new DbParameter(Types.SMALLINT, info.getResetTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean deleteNpcShopUserCache(NpcShopUserCache info) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "DELETE from tb_u_npc_shop_usercache WHERE privateId = " + info.getPrivateId() +"and playerId = "+info.getPlayerId();
		result = execNoneQuery(sql) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean updateNpcShopUserCache(NpcShopUserCache info) {
		// TODO Auto-generated method stub
		info.beginUpdate();
		boolean result = false;
		String sql = "UPDATE tb_u_npc_shop_usercache SET buyNum= ?,resetTime=? "
				+ " WHERE privateId = ? and playerId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.SMALLINT, info.getBuyNum()));
		para.put(2, new DbParameter(Types.SMALLINT, info.getResetTime()));
		para.put(3, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		para.put(4, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public Map<Short, NpcShopServerCache> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_u_npc_shop_servercache;";
		return read(sql, null);
	}	
	
	
	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private Map<Short, NpcShopServerCache> read(String sqlText, Map<Integer, DbParameter> para) {
		
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Short, NpcShopServerCache> infos = null;
		NpcShopServerCache info = null;
		if (pstmt != null) {
			infos = new HashMap<Short, NpcShopServerCache>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new NpcShopServerCache();
					info.setPrivateId(rs.getShort("privateId"));
					info.setBuyNum(rs.getInt("buyNum"));
					info.setResetTime(rs.getShort("resetTime"));
					infos.put(info.getPrivateId(), info);
				}
			} catch (SQLException e) {
				infos = null;
				//Log.error("执行出错" + sqlText, e);
				System.out.println("执行出错" + sqlText);
				System.out.println(e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}
	

	@Override
	public boolean addNpcShopServerCache(NpcShopServerCache info) {
		// TODO Auto-generated method stub
		info.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_npc_shop_servercache (privateId,buyNum,resetTime)"
				+ " VALUES (?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		para.put(2, new DbParameter(Types.INTEGER, info.getBuyNum()));
		para.put(3, new DbParameter(Types.SMALLINT, info.getResetTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean deleteNpcShopServerCache(NpcShopServerCache info) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "DELETE from tb_u_npc_shop_servercache WHERE privateId = "+info.getPrivateId()+";";
		result = execNoneQuery(sql) > -1 ? true : false;
		return result;
		
	}

	@Override
	public boolean updateNpcShopServerCache(NpcShopServerCache info) {
		// TODO Auto-generated method stub
		info.beginUpdate();
		boolean result = false;
		String sql = "UPDATE tb_u_npc_shop_servercache SET buyNum= ?,resetTime=? "
				+ " WHERE privateId = ?;";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, info.getBuyNum()));
		para.put(2, new DbParameter(Types.SMALLINT, info.getResetTime()));
		para.put(3, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	
	
	
}
