package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.spawn.NpcInfo;
import com.chuangyou.xianni.sql.dao.NpcInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class NpcInfoDaoImpl extends BaseDao implements NpcInfoDao {

	@Override
	public List<NpcInfo> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_z_npc_info";
		List<NpcInfo> result = read(sql, null);
		return result;
	}
	
	/**
	 * @param sqlText
	 * @param para
	 * @return
	 */
	private List<NpcInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<NpcInfo> infos = null;
		NpcInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<NpcInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new NpcInfo();
					info.setNpcId(rs.getInt("npcId"));
					info.setSkin(rs.getInt("skin"));
					info.setType(rs.getInt("type"));
					info.setName(rs.getString("name"));
					info.setRedValue(rs.getInt("redValue"));
					info.setLevel(rs.getInt("level"));
					info.setScriptId(rs.getString("scriptId"));
					info.setIntParam1(rs.getInt("intParam1"));
					info.setIntParam2(rs.getInt("intParam2"));
					info.setStrParam3(rs.getString("strParam3"));
					//infos.put(info.getNpcId(), info);
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				System.out.println("执行出错" + sqlText);
				System.out.println(e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
		
	}

}
