package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.npcShop.NpcShopCfg;
import com.chuangyou.xianni.sql.dao.NpcShopConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class NpcShopConfigDaoImpl extends BaseDao implements NpcShopConfigDao {

	@Override
	public Map<Short, NpcShopCfg> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_npc_shop";
		return read(sql,null);
	}
	

	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private Map<Short, NpcShopCfg> read(String sqlText, Map<Integer, DbParameter> para) {
		
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Short, NpcShopCfg> infos = null;
		NpcShopCfg info = null;
		if (pstmt != null) {
			infos = new HashMap<Short, NpcShopCfg>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new NpcShopCfg();
					info.setId(rs.getShort("id"));
					info.setNpcid(rs.getInt("npcid"));
					info.setShopid(rs.getInt("shopid"));
					info.setConsumeType(rs.getByte("consumeType"));
					info.setItemType(rs.getInt("itemType"));
					info.setBind(rs.getByte("bind"));
					info.setMoneyType(rs.getInt("moneyType"));
					info.setPrice(rs.getLong("price"));
					info.setAmount(rs.getInt("amount"));
					info.setLimitBuynum(rs.getInt("limitBuynum"));
					info.setTimeType(rs.getByte("timeType"));
					info.setStartTime(rs.getString("startTime"));
					info.setEndTime(rs.getString("endTime"));
					info.setResetTime(rs.getInt("resetTime"));
					infos.put(info.getId(), info);
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

}
