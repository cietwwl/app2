package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.sql.dao.ItemTemplateInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ItemTemplateInfoDaoImpl extends BaseDao implements ItemTemplateInfoDao {

	@Override
	public List<ItemTemplateInfo> getItemTemps() {
		String sql = "SELECT * FROM tb_z_item_template";
		List<ItemTemplateInfo> temps = read(sql, null);
		return temps;
	}

	private List<ItemTemplateInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<ItemTemplateInfo> infos = null;
		ItemTemplateInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<ItemTemplateInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new ItemTemplateInfo();
					info.setId(rs.getInt("id"));
					info.setMasterType(rs.getInt("masterType"));
					info.setSonType(rs.getInt("sonType"));
					info.setName(rs.getString("name"));
					info.setProfession(rs.getInt("profession"));
					info.setLevel(rs.getInt("level"));
					info.setItemcolor(rs.getByte("itemcolor"));
					info.setExp(rs.getInt("exp"));
					info.setBuy(rs.getInt("buy"));
					info.setSell(rs.getInt("sell"));
					info.setAmount(rs.getInt("amount"));
					info.setInit_life_time(rs.getInt("init_life_time"));

					info.setItemBase(rs.getInt("itemBase"));
					info.setQualityCoefficient(rs.getInt("qualityCoefficient"));
					info.setGrow(rs.getInt("grow"));
					info.setBind(rs.getByte("bind"));
					info.setBindtime(rs.getInt("bindtime"));
					info.setId_action(rs.getString("id_action"));
					info.setJiachilimit(rs.getInt("jiachilimit"));
					info.setJiachi1(rs.getInt("jiachi1"));
					info.setStatistics1(rs.getInt("statistics1"));
					info.setJiachi2(rs.getInt("jiachi2"));
					info.setStatistics2(rs.getInt("statistics2"));
					info.setJiachi3(rs.getInt("jiachi3"));
					info.setStatistics3(rs.getInt("statistics3"));
					info.setJiachi4(rs.getInt("jiachi4"));
					info.setStatistics4(rs.getInt("statistics4"));

					info.setAwakeskill1(rs.getInt("awakeskill1"));
					info.setAwakenum1(rs.getInt("awakenum1"));
					info.setAddmoneytype1(rs.getInt("addmoneytype1"));
					info.setAddmoneynum1(rs.getInt("addmoneynum1"));

					info.setAwakeskill2(rs.getInt("awakeskill2"));
					info.setAwakenum2(rs.getInt("awakenum2"));
					info.setAddmoneytype2(rs.getInt("addmoneytype2"));
					info.setAddmoneynum2(rs.getInt("addmoneynum2"));

					info.setAwakeskill3(rs.getInt("awakeskill3"));
					info.setAwakenum3(rs.getInt("awakenum3"));
					info.setAddmoneytype3(rs.getInt("addmoneytype3"));
					info.setAddmoneynum3(rs.getInt("addmoneynum3"));

					info.setSuit_id(rs.getInt("suit_id"));
					info.setDes(rs.getString("des"));

					info.setResolveId(rs.getInt("resolveId"));
					info.setResolveCount(rs.getInt("resolveCount"));
					info.setIsVirtual(rs.getInt("isVirtual"));
					info.setSaveGuild(rs.getByte("saveGuild"));
					
					info.setScriptId(rs.getString("scriptId"));

					infos.add(info);
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
	public List<ItemTemplateInfo> getItemTemps(String name) {
		String sql = "SELECT * FROM tb_z_item_template where name like '%"+name+"%'";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.VARCHAR, name));
		List<ItemTemplateInfo> temps = read(sql, null);
		return temps;
	}

}
