package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.sql.dao.ShopConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

/**
 * 商店模板数据DAO层实现
 * @author laofan
 *
 */
public class ShopConfigDaoImpl extends BaseDao implements ShopConfigDao {

	@Override
	public Map<Integer, ShopCfg> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_shop_info";
		return read(sql,null);
	}
	

	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private Map<Integer, ShopCfg> read(String sqlText, Map<Integer, DbParameter> para) {
		
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		Map<Integer, ShopCfg> infos = null;
		ShopCfg info = null;
		if (pstmt != null) {
			infos = new HashMap<Integer, ShopCfg>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new ShopCfg();
					info.setId(rs.getInt("id"));
					info.setNpcid(rs.getInt("npcid"));
					info.setShopid(rs.getInt("shopid"));
					info.setItemType(rs.getInt("itemType"));
					info.setBind(rs.getByte("bind"));
					info.setMoneyType(rs.getInt("moneyType"));
					info.setPrice(rs.getLong("price"));
					info.setDiscount(rs.getByte("discount"));
					info.setServerLimitNum(rs.getInt("serverLimitNum"));
					info.setPersonLimitNum(rs.getInt("personLimitNum"));
					info.setTimeType(rs.getShort("timeType"));
					info.setShelvesTime(rs.getString("shelvesTime"));
					info.setShelfTime(rs.getString("shelfTime"));
					info.setStartTime(rs.getString("startTime"));
					info.setEndTime(rs.getString("endTime"));
					info.setResetTime(rs.getInt("resetTime"));
					info.setTab(rs.getInt("tab"));
					info.setSort(rs.getInt("sort"));
					info.setIsPreview(rs.getByte("isPreview"));
					info.setEasyBuy(rs.getByte("easyBuy"));
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
