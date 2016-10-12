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
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildInfo;
import com.chuangyou.xianni.sql.dao.GuildInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class GuildInfoDaoImpl extends BaseDao implements GuildInfoDao {

	@Override
	public List<GuildInfo> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_guild where isDelete = 0";
		return read(sql);
	}
	
	@Override
	public int getMaxGuildId() {
		// TODO Auto-generated method stub
		String sql = "SELECT MAX(guildId) AS maxid FROM tb_u_guild";
		int maxId = 0;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					maxId = rs.getInt("maxid");
					break;
				}
			} catch (SQLException e) {
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return maxId < 101 ? 101 : maxId + 1;
	}

	@Override
	public int getGuildNumByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from tb_u_guild where isDelete = 0 and name = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.VARCHAR, name));
		
		int count = 0;
		PreparedStatement pstmt = execQuery(sql, params);
		ResultSet rs = null;
		if(pstmt != null){
			try {
				rs = pstmt.executeQuery();
				while(rs.next()){
					count = rs.getInt(1);
					break;
				}
			} catch (SQLException e) {
				// TODO: handle exception
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return count;
	}

	@Override
	public boolean remove(GuildInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		if(info.getOp() == Option.Insert){
			result = this.addInfo(info, (byte)1);
		}else{
			String sql = "update tb_u_guild set isDelete = 1 where guildId=?";
			Map<Integer, DbParameter> params = new HashMap<>();
			params.put(1, new DbParameter(Types.INTEGER, info.getGuildId()));
			result = execNoneQuery(sql, params) > -1? true: false;
		}
		return result;
	}

	@Override
	public boolean add(GuildInfo info) {
		// TODO Auto-generated method stub
		return addInfo(info, (byte)0);
	}
	
	private boolean addInfo(GuildInfo info, byte isDelete){
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_guild(guildId,type,name,createTime,level,buildExp,totalBuildExp,supply,mainBuildLevel,skillShopLevel,"
				+ "shopLevel,warehouseLevel,notice,leaderId,joinType,levelLimit,fightLimit,isDelete,supplyCheckTime) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getGuildId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getType()));
		params.put(3, new DbParameter(Types.VARCHAR, info.getName()));
		params.put(4, new DbParameter(Types.BIGINT, info.getCreateTime()));
		params.put(5, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(6, new DbParameter(Types.BIGINT, info.getBuildExp()));
		params.put(7, new DbParameter(Types.BIGINT, info.getTotalBuildExp()));
		params.put(8, new DbParameter(Types.BIGINT, info.getSupply()));
		params.put(9, new DbParameter(Types.INTEGER, info.getMainBuildLevel()));
		params.put(10, new DbParameter(Types.INTEGER, info.getSkillShopLevel()));
		params.put(11, new DbParameter(Types.INTEGER, info.getShopLevel()));
		params.put(12, new DbParameter(Types.INTEGER, info.getWarehouseLevel()));
		params.put(13, new DbParameter(Types.VARCHAR, info.getNotice()));
		params.put(14, new DbParameter(Types.BIGINT, info.getLeaderId()));
		params.put(15, new DbParameter(Types.SMALLINT, info.getJoinType()));
		params.put(16, new DbParameter(Types.INTEGER, info.getLevelLimit()));
		params.put(17, new DbParameter(Types.INTEGER, info.getFightLimit()));
		params.put(18, new DbParameter(Types.TINYINT, isDelete));
		params.put(19, new DbParameter(Types.BIGINT, info.getSupplyCheckTime()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(GuildInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginUpdate();
		
		String sql = "update tb_u_guild set type = ?,name = ?,createTime = ?,level = ?,buildExp = ?, totalBuildExp = ?,supply = ?,mainBuildLevel = ?,skillShopLevel = ?,"
				+ "shopLevel = ?,warehouseLevel = ?,notice = ?,leaderId = ?,joinType = ?,levelLimit = ?,fightLimit = ?,supplyCheckTime = ? where guildId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getType()));
		params.put(2, new DbParameter(Types.VARCHAR, info.getName()));
		params.put(3, new DbParameter(Types.BIGINT, info.getCreateTime()));
		params.put(4, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(5, new DbParameter(Types.BIGINT, info.getBuildExp()));
		params.put(6, new DbParameter(Types.BIGINT, info.getTotalBuildExp()));
		params.put(7, new DbParameter(Types.BIGINT, info.getSupply()));
		params.put(8, new DbParameter(Types.INTEGER, info.getMainBuildLevel()));
		params.put(9, new DbParameter(Types.INTEGER, info.getSkillShopLevel()));
		params.put(10, new DbParameter(Types.INTEGER, info.getShopLevel()));
		params.put(11, new DbParameter(Types.INTEGER, info.getWarehouseLevel()));
		params.put(12, new DbParameter(Types.VARCHAR, info.getNotice()));
		params.put(13, new DbParameter(Types.BIGINT, info.getLeaderId()));
		params.put(14, new DbParameter(Types.SMALLINT, info.getJoinType()));
		params.put(15, new DbParameter(Types.INTEGER, info.getLevelLimit()));
		params.put(16, new DbParameter(Types.INTEGER, info.getFightLimit()));
		params.put(17, new DbParameter(Types.BIGINT, info.getSupplyCheckTime()));
		params.put(18, new DbParameter(Types.INTEGER, info.getGuildId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		
		info.commitUpdate(result);
		return result;
	}
	
	private List<GuildInfo> read(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		List<GuildInfo> infos = null;
		GuildInfo info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new GuildInfo();
					
					info.setGuildId(rs.getInt("guildId"));
					info.setType(rs.getInt("type"));
					info.setName(rs.getString("name"));
					info.setCreateTime(rs.getLong("createTime"));
					info.setLevel(rs.getInt("level"));
					info.setBuildExp(rs.getLong("buildExp"));
					info.setTotalBuildExp(rs.getLong("totalBuildExp"));
					info.setSupply(rs.getLong("supply"));
					info.setMainBuildLevel(rs.getInt("mainBuildLevel"));
					info.setSkillShopLevel(rs.getInt("skillShopLevel"));
					info.setShopLevel(rs.getInt("shopLevel"));
					info.setWarehouseLevel(rs.getInt("warehouseLevel"));
					info.setNotice(rs.getString("notice"));
					info.setLeaderId(rs.getLong("leaderId"));
					info.setJoinType(rs.getShort("joinType"));
					info.setLevelLimit(rs.getInt("levelLimit"));
					info.setFightLimit(rs.getInt("fightLimit"));
					info.setSupplyCheckTime(rs.getLong("supplyCheckTime"));
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
