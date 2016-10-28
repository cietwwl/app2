package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.notice.NoticeCfg;
import com.chuangyou.xianni.sql.dao.NoticeConfigDao;
import com.chuangyou.xianni.sql.db.BaseDao;

public class NoticeConfigDaoImpl extends BaseDao implements NoticeConfigDao {

	@Override
	public Map<Integer, NoticeCfg> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_notice";
		return read(sql);
	}
	
	private Map<Integer, NoticeCfg> read(String sqlText){
		PreparedStatement pst = execQuery(sqlText);
		ResultSet rs = null;
		Map<Integer, NoticeCfg> infos = null;
		NoticeCfg info = null;
		if(pst != null){
			infos = new HashMap<Integer, NoticeCfg>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new NoticeCfg();
					info.setNoticeId(rs.getInt("noticeId"));
					info.setChannel(rs.getInt("channel"));
					info.setNotifyRange(rs.getInt("notifyRange"));
					info.setContent(rs.getString("content"));
					info.setDesc(rs.getString("desc"));
					info.setNoticeClose(rs.getByte("noticeClose"));
					
					infos.put(info.getNoticeId(), info);
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
