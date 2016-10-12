package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.space.SpaceActionLogInfo;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.entity.space.SpaceMessageInfo;
import com.chuangyou.xianni.sql.dao.SpaceDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class SpaceDaoImpl extends BaseDao implements SpaceDao {

	@Override
	public boolean add(SpaceInfo info) {
		// TODO Auto-generated method stub
		String sql = "insert into tb_u_space_info (playerId,face,signature,city,birthday,popularity,gift,isNoMsg,likes,flowers,eggs,curCollection,maxCollection,isEditBirthday) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.VARCHAR, info.getFace()));
		params.put(3, new DbParameter(Types.VARCHAR, info.getSignature()));
		params.put(4, new DbParameter(Types.VARCHAR, info.getCity()));
		params.put(5, new DbParameter(Types.VARCHAR, info.getBirthday()));
		params.put(6, new DbParameter(Types.INTEGER, info.getPopularity()));
		params.put(7, new DbParameter(Types.INTEGER, info.getGift()));
		params.put(8, new DbParameter(Types.INTEGER, info.getIsNoMsg()));
		params.put(9, new DbParameter(Types.INTEGER, info.getLikes()));
		params.put(10, new DbParameter(Types.INTEGER, info.getFlowers()));
		params.put(11, new DbParameter(Types.INTEGER, info.getEggs()));
		params.put(12, new DbParameter(Types.INTEGER, info.getCurCollection()));
		params.put(13, new DbParameter(Types.INTEGER, info.getMaxCollection()));
		params.put(14, new DbParameter(Types.INTEGER, info.getIsEditBirthday()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(SpaceInfo info) {
		// TODO Auto-generated method stub
		String sql = "update tb_u_space_info set face=?,signature=?,city=?,birthday=?,popularity=?,gift=?,isNoMsg=?,likes=?,flowers=?,eggs=?,curCollection=?,maxCollection=?,isEditBirthday=? where playerId=?";
		if(!info.beginUpdate())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.VARCHAR, info.getFace()));
		params.put(2, new DbParameter(Types.VARCHAR, info.getSignature()));
		params.put(3, new DbParameter(Types.VARCHAR, info.getCity()));
		params.put(4, new DbParameter(Types.VARCHAR, info.getBirthday()));
		params.put(5, new DbParameter(Types.INTEGER, info.getPopularity()));
		params.put(6, new DbParameter(Types.INTEGER, info.getGift()));
		params.put(7, new DbParameter(Types.INTEGER, info.getIsNoMsg()));
		params.put(8, new DbParameter(Types.INTEGER, info.getLikes()));
		params.put(9, new DbParameter(Types.INTEGER, info.getFlowers()));
		params.put(10, new DbParameter(Types.INTEGER, info.getEggs()));
		params.put(11, new DbParameter(Types.INTEGER, info.getCurCollection()));
		params.put(12, new DbParameter(Types.INTEGER, info.getMaxCollection()));
 		params.put(13, new DbParameter(Types.INTEGER, info.getIsEditBirthday()));
		params.put(14, new DbParameter(Types.BIGINT, info.getPlayerId()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
		
	}

	@Override
	public SpaceInfo get(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_space_info where playerId="+playerId;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		SpaceInfo info = null;
		List<SpaceInfo> infos = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				infos = new ArrayList<>();
				while (rs.next()) {
					info = new SpaceInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setFace(rs.getString("face"));
					info.setSignature(rs.getString("signature"));
					info.setCity(rs.getString("city"));
					info.setBirthday(rs.getString("birthday"));
					info.setPopularity(rs.getInt("popularity"));
					info.setGift(rs.getInt("gift"));
					info.setIsNoMsg(rs.getInt("isNoMsg"));
					info.setLikes(rs.getInt("likes"));
					info.setFlowers(rs.getInt("flowers"));
					info.setEggs(rs.getInt("eggs"));
					info.setCurCollection(rs.getInt("curCollection"));
					info.setMaxCollection(rs.getInt("maxCollection"));
					info.setIsEditBirthday(rs.getInt("isEditBirthday"));
					info.setOp(Option.None);
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sql);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		if(infos!=null && infos.size()>0){
			return infos.get(0);
		}
		return null;
	}
	
	
	
	@Override
	public boolean add(SpaceMessageInfo info) {
		// TODO Auto-generated method stub
		String sql = "insert into tb_u_space_message (id,sendPlayerId,receivePlayerId,createDate,message,isCollection) values(?,?,?,?,?,?)";
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getId()));
		params.put(2, new DbParameter(Types.BIGINT, info.getSendPlayerId()));
		params.put(3, new DbParameter(Types.BIGINT, info.getReceivePlayerId()));
		params.put(4, new DbParameter(Types.TIMESTAMP, info.getCreateDate()));
		params.put(5, new DbParameter(Types.VARCHAR, info.getMessage()));
		params.put(6, new DbParameter(Types.INTEGER, info.getIsCollection()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	
	@Override
	public Map<Integer, SpaceMessageInfo> getAll(long playerId,int max) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_space_message where receivePlayerId="+playerId +" order by id desc limit "+max;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		SpaceMessageInfo info = null;
		Map<Integer, SpaceMessageInfo> infos = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				infos = new ConcurrentSkipListMap<>(new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						return o2-o1;
					}
				});
				
				while (rs.next()) {
					info = new SpaceMessageInfo();
					info.setId(rs.getInt("id"));
					info.setSendPlayerId(rs.getLong("sendPlayerId"));
					info.setReceivePlayerId(rs.getLong("receivePlayerId"));
					info.setCreateDate(rs.getTimestamp("createDate"));
					info.setMessage(rs.getString("message"));
					info.setIsCollection(rs.getInt("isCollection"));
					info.setOp(Option.None);
					infos.put(info.getId(), info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sql);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

	@Override
	public boolean del(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from tb_u_space_message where id="+id;
		boolean result = execNoneQuery(sql, null) > -1 ? true : false;
		return result;
	}

	@Override
	public boolean add(SpaceActionLogInfo info) {
		// TODO Auto-generated method stub
		String sql = "insert into tb_u_space_actionLog (sendPlayerId,receivePlayerId,createTime,action) values(?,?,?,?)";
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getSendPlayerId()));
		params.put(2, new DbParameter(Types.BIGINT, info.getReceivePlayerId()));
		params.put(3, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
		params.put(4, new DbParameter(Types.INTEGER, info.getAction()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public List<SpaceActionLogInfo> getActionAll(long playerId,int max) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_space_actionLog where receivePlayerId="+playerId+" order by createTime desc limit "+max;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		SpaceActionLogInfo info = null;
		List<SpaceActionLogInfo> infos = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				infos = new ArrayList<>();
				while (rs.next()) {
					info = new SpaceActionLogInfo();
					info.setSendPlayerId(rs.getLong("sendPlayerId"));
					info.setReceivePlayerId(rs.getLong("receivePlayerId"));
					info.setCreateTime(rs.getTimestamp("createTime"));
					info.setAction(rs.getInt("action"));
					info.setOp(Option.None);
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sql);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

	@Override
	public boolean del(SpaceActionLogInfo info) {
		// TODO Auto-generated method stub
		String sql = "delete from tb_u_space_actionLog where receivePlayerId=? and sendPlayerId=? and createTime=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getSendPlayerId()));
		params.put(2, new DbParameter(Types.BIGINT, info.getReceivePlayerId()));
		params.put(3, new DbParameter(Types.TIMESTAMP, info.getCreateTime()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		if(result)info.setOp(Option.None);
		return result;
	}

	@Override
	public int getMaxId() {
		// TODO Auto-generated method stub
		String sql = "SELECT MAX(id) AS maxid FROM tb_u_space_message";
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
		return maxId == 0 ? 1 : maxId + 1;
	}

}
