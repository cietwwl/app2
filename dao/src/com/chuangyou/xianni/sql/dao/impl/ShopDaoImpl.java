package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.shop.ShopServerCache;
import com.chuangyou.xianni.entity.shop.ShopUserCache;
import com.chuangyou.xianni.sql.dao.ShopDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;


/**
 * 商店DAO层实现
 * @author laofan
 *
 */
public class ShopDaoImpl extends BaseDao implements ShopDao {

	@Override
	public Map<Integer,ShopUserCache> getUserAll(long playerId) {
		String sql = "SELECT * FROM tb_u_shop_usercache WHERE playerId=" + playerId + ";";
		Map<Integer,ShopUserCache> result = readUserCache(sql, null);
		return result;
		
	}

	private Map<Integer,ShopUserCache> readUserCache(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Integer,ShopUserCache> infos = null;
		ShopUserCache info = null;
		if (pstmt != null) {
			infos = new HashMap<>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new ShopUserCache();
					info.setBuyNum(rs.getShort("buyNum"));
					info.setPlayerId(rs.getLong("playerId"));
					info.setPrivateId(rs.getInt("privateId"));
					info.setResetTime(rs.getShort("resetTime"));
					info.setLastUpdateTime(rs.getTimestamp("lastUpdateTime"));
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
	public boolean addShopUserCache(ShopUserCache info) {
		// TODO Auto-generated method stub
		info.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_shop_usercache (playerId,privateId,buyNum,resetTime,lastUpdateTime)"
				+ " VALUES (?,?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		para.put(2, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		para.put(3, new DbParameter(Types.SMALLINT, info.getBuyNum()));
		para.put(4, new DbParameter(Types.SMALLINT, info.getResetTime()));
		para.put(5, new DbParameter(Types.TIMESTAMP, info.getLastUpdateTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean deleteShopUserCache(ShopUserCache info) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "DELETE from tb_u_shop_usercache WHERE privateId = " + info.getPrivateId() +"and playerId = "+info.getPlayerId();
		result = execNoneQuery(sql) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean updateShopUserCache(ShopUserCache info) {
		// TODO Auto-generated method stub
		info.beginUpdate();
		boolean result = false;
		String sql = "UPDATE tb_u_shop_usercache SET buyNum= ?,resetTime=?,lastUpdateTime=? "
				+ " WHERE privateId = ? and playerId = ?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.SMALLINT, info.getBuyNum()));
		para.put(2, new DbParameter(Types.SMALLINT, info.getResetTime()));
		para.put(3, new DbParameter(Types.TIMESTAMP, info.getLastUpdateTime()));
		para.put(4, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		para.put(5, new DbParameter(Types.BIGINT, info.getPlayerId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	@Override
	public ConcurrentHashMap<Integer, ShopServerCache> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_u_shop_servercache;";
		return read(sql, null);
	}	
		
	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private  ConcurrentHashMap<Integer, ShopServerCache> read(String sqlText, Map<Integer, DbParameter> para) {
		
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		ConcurrentHashMap<Integer, ShopServerCache> infos = null;
		 ShopServerCache info = null;
		if (pstmt != null) {
			infos = new ConcurrentHashMap<Integer, ShopServerCache>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new ShopServerCache();
					info.setPrivateId(rs.getShort("privateId"));
					info.setBuyNum(rs.getInt("buyNum"));
					info.setResetTime(rs.getShort("resetTime"));
					info.setLastUpdateTime(rs.getTimestamp("lastUpdateTime"));
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
	public boolean addShopServerCache(ShopServerCache info) {
		// TODO Auto-generated method stub
		info.beginAdd();
		boolean result = false;
		String sql = "INSERT INTO tb_u_shop_servercache (privateId,buyNum,resetTime,lastUpdateTime)"
				+ " VALUES (?,?,?,?);";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		para.put(2, new DbParameter(Types.INTEGER, info.getBuyNum()));
		para.put(3, new DbParameter(Types.SMALLINT, info.getResetTime()));
		para.put(4, new DbParameter(Types.TIMESTAMP, info.getLastUpdateTime()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean deleteShopServerCache(ShopServerCache info) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "DELETE from tb_u_shop_servercache WHERE privateId = "+info.getPrivateId()+";";
		result = execNoneQuery(sql) > -1 ? true : false;
		return result;
		
	}

	@Override
	public boolean updateShopServerCache(ShopServerCache info) {
		// TODO Auto-generated method stub
		info.beginUpdate();
		boolean result = false;
		String sql = "UPDATE tb_u_shop_servercache SET buyNum= ?,resetTime=?,lastUpdateTime=? "
				+ " WHERE privateId = ?;";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.INTEGER, info.getBuyNum()));
		para.put(2, new DbParameter(Types.SMALLINT, info.getResetTime()));
		para.put(3, new DbParameter(Types.TIMESTAMP, info.getLastUpdateTime()));
		para.put(4, new DbParameter(Types.SMALLINT, info.getPrivateId()));
		result = execNoneQuery(sql, para) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}

	
	
	
}
