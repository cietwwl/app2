package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.sql.dao.ChatPrivateOfflineMsgDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ChatPrivateOfflineMsgDaoImpl extends BaseDao implements ChatPrivateOfflineMsgDao {

	@Override
	public List<ChatMsgInfo> getPlayerMsg(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_chat_history where receiverId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}
	
	@Override
	public List<ChatMsgInfo> getPlayerMsg(long playerId, long senderId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_chat_history where receiverId=? and senderId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		params.put(2, new DbParameter(Types.BIGINT, senderId));
		return read(sql, params);
	}
	
	private List<ChatMsgInfo> read(String sqlText, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sqlText, params);
		ResultSet rs = null;
		List<ChatMsgInfo> infos = null;
		ChatMsgInfo info = null;
		if(pst != null){
			infos = new ArrayList<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ChatMsgInfo();
					info.setChannel(rs.getInt("channel"));
					info.setSendTime(rs.getLong("sendTime"));
					info.setSenderId(rs.getLong("senderId"));
					info.setReceiverId(rs.getLong("receiverId"));
					info.setChatContent(rs.getString("chatContent"));
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

	@Override
	public boolean addMsg(ChatMsgInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_chat_history(channel, sendTime, senderId, receiverId, chatContent) "
				+ " values(?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getChannel()));
		params.put(2, new DbParameter(Types.BIGINT, info.getSendTime()));
		params.put(3, new DbParameter(Types.BIGINT, info.getSenderId()));
		params.put(4, new DbParameter(Types.BIGINT, info.getReceiverId()));
		params.put(5, new DbParameter(Types.VARCHAR, info.getChatContent()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return result;
	}

	@Override
	public boolean deletePlayerMsg(long playerId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_chat_history where receiverId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}
	
	@Override
	public boolean deletePlayerMsg(long playerId, long senderId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from tb_u_chat_history where receiverId=? and senderId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		params.put(2, new DbParameter(Types.BIGINT, senderId));
		result = execNoneQuery(sql, params) > -1? true: false;
		return result;
	}

}
