package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.relation.RelationInfo;
import com.chuangyou.xianni.sql.dao.RelationInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class RelationInfoDaoImpl extends BaseDao implements RelationInfoDao {

	@Override
	public List<RelationInfo> getPlayerRelations(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_relation where playerId1 = ? or playerId2 = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		params.put(2, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean addRelation(RelationInfo relationInfo) {
		// TODO Auto-generated method stub
		relationInfo.beginAdd();
		boolean result = false;
		String sql = "insert tb_u_relation(playerId1,playerId2,relation1,relation2) "
				+ " values(?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, relationInfo.getPlayerId1()));
		params.put(2, new DbParameter(Types.BIGINT, relationInfo.getPlayerId2()));
		params.put(3, new DbParameter(Types.SMALLINT, relationInfo.getRelation1()));
		params.put(4, new DbParameter(Types.SMALLINT, relationInfo.getRelation2()));
		result = execNoneQuery(sql, params) > -1? true: false;
		relationInfo.commitAdd(result);
		return result;
	}

	@Override
	public boolean removeRelation(long playerId1, long playerId2) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_relation where playerId1 = ? and playerId2 = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId1));
		params.put(2, new DbParameter(Types.BIGINT, playerId2));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}

	@Override
	public boolean updateRelation(RelationInfo relationInfo) {
		// TODO Auto-generated method stub
		relationInfo.beginUpdate();
		boolean result = false;
		String sql = "update tb_u_relation set relation1=?,relation2=? where playerId1 = ? and playerId2 = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.SMALLINT, relationInfo.getRelation1()));
		params.put(2, new DbParameter(Types.SMALLINT, relationInfo.getRelation2()));
		params.put(3, new DbParameter(Types.BIGINT, relationInfo.getPlayerId1()));
		params.put(4, new DbParameter(Types.BIGINT, relationInfo.getPlayerId2()));
		result = execNoneQuery(sql, params) > -1? true: false;
		relationInfo.commitUpdate(result);
		return result;
	}
	
	public List<RelationInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		List<RelationInfo> infos = null;
		RelationInfo info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new RelationInfo();
					info.setPlayerId1(rs.getLong("playerId1"));
					info.setPlayerId2(rs.getLong("playerId2"));
					info.setRelation1(rs.getShort("relation1"));
					info.setRelation2(rs.getShort("relation2"));
					
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
