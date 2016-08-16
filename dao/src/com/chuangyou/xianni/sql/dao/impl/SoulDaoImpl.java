package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.soul.CardLvConfig;
import com.chuangyou.xianni.entity.soul.CardSkillConfig;
import com.chuangyou.xianni.entity.soul.CardStarConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulCardPiece;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.entity.soul.SoulMakeConfig;
import com.chuangyou.xianni.sql.dao.SoulDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class SoulDaoImpl extends BaseDao implements SoulDao {

		
	@Override
	public boolean addSoulInfo(SoulInfo info) {
		String sql = "insert into tb_u_soul_info (playerId,exp,card1,card2,card3,card4,card5,"
				+ "card6,card7,card8,fuseSkillId1,fuseSkillId2,fuseSkillId3,fuseSkillId4,fuseSkillCreateTime1,"
				+ "fuseSkillCreateTime2,fuseSkillCreateTime3,fuseSkillCreateTime4,proficiency) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.BIGINT, info.getExp()));
		params.put(3, new DbParameter(Types.INTEGER, info.getCard1()));
		params.put(4, new DbParameter(Types.INTEGER, info.getCard2()));
		params.put(5, new DbParameter(Types.INTEGER, info.getCard3()));
		params.put(6, new DbParameter(Types.INTEGER, info.getCard4()));
		params.put(7, new DbParameter(Types.INTEGER, info.getCard5()));
		params.put(8, new DbParameter(Types.INTEGER, info.getCard6()));
		params.put(9, new DbParameter(Types.INTEGER, info.getCard7()));
		params.put(10, new DbParameter(Types.INTEGER, info.getCard8()));
		params.put(11, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(12, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(13, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(14, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(15, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime1()));
		params.put(16, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime2()));
		params.put(17, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime3()));
		params.put(18, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime4()));	
		params.put(19, new DbParameter(Types.INTEGER, info.getProficiency()));	
		
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	
	@Override
	public boolean updateSoulInfo(SoulInfo info) {
		// TODO Auto-generated method stub
		String sql = "update tb_u_soul_info set exp=?,card1=?,card2=?,card3=?,card4=?,card5=?,"
				+ "card6=?,card7=?,card8=?,fuseSkillId1=?,fuseSkillId2=?,fuseSkillId3=?,fuseSkillId4=?,fuseSkillCreateTime1=?,"
				+ "fuseSkillCreateTime2=?,fuseSkillCreateTime3=?,fuseSkillCreateTime4=?,proficiency=? "
				+ "where playerId=?";
		if(!info.beginUpdate())return false;
		
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getExp()));
		params.put(2, new DbParameter(Types.INTEGER, info.getCard1()));
		params.put(3, new DbParameter(Types.INTEGER, info.getCard2()));
		params.put(4, new DbParameter(Types.INTEGER, info.getCard3()));
		params.put(5, new DbParameter(Types.INTEGER, info.getCard4()));
		params.put(6, new DbParameter(Types.INTEGER, info.getCard5()));
		params.put(7, new DbParameter(Types.INTEGER, info.getCard6()));
		params.put(8, new DbParameter(Types.INTEGER, info.getCard7()));
		params.put(9, new DbParameter(Types.INTEGER, info.getCard8()));
		params.put(10, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(11, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(12, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(13, new DbParameter(Types.INTEGER, info.getFuseSkillId1()));
		params.put(14, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime1()));
		params.put(15, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime2()));
		params.put(16, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime3()));
		params.put(17, new DbParameter(Types.TIMESTAMP, info.getFuseSkillCreateTime4()));	
		params.put(18, new DbParameter(Types.INTEGER, info.getProficiency()));
		params.put(19, new DbParameter(Types.BIGINT, info.getPlayerId()));
				
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}
	

	@Override
	public boolean addSoulCardInfo(SoulCardInfo info) {
		String sql = "insert into tb_u_soul_cardInfo (playerId,cardId,star,exp,skill1,skill2,skill3,skill4,isPutOn,lv) values(?,?,?,?,?,?,?,?,?,?)";
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getCardId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getStar()));
		params.put(4, new DbParameter(Types.BIGINT, info.getExp()));
		params.put(5, new DbParameter(Types.INTEGER, info.getSkill1()));
		params.put(6, new DbParameter(Types.INTEGER, info.getSkill2()));
		params.put(7, new DbParameter(Types.INTEGER, info.getSkill3()));
		params.put(8, new DbParameter(Types.INTEGER, info.getSkill4()));
		params.put(9, new DbParameter(Types.INTEGER, info.getIsPutOn()));
		params.put(10, new DbParameter(Types.INTEGER, info.getLv()));
		
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;	
	}

	
	@Override
	public boolean updateSoulCardInfo(SoulCardInfo info) {
		String sql = "update tb_u_soul_cardInfo set star=?,exp=?,skill1=?,skill2=?,skill3=?,skill4=?,isPutOn=?,lv=? where playerId=? and cardId=?";
		if(!info.beginUpdate())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getStar()));
		params.put(2, new DbParameter(Types.BIGINT, info.getExp()));
		params.put(3, new DbParameter(Types.INTEGER, info.getSkill1()));
		params.put(4, new DbParameter(Types.INTEGER, info.getSkill2()));
		params.put(5, new DbParameter(Types.INTEGER, info.getSkill3()));
		params.put(6, new DbParameter(Types.INTEGER, info.getSkill4()));
		params.put(7, new DbParameter(Types.INTEGER, info.getIsPutOn()));
		params.put(8, new DbParameter(Types.INTEGER, info.getLv()));
		params.put(9, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(10, new DbParameter(Types.INTEGER, info.getCardId()));
		
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;				
	}

	@Override
	public boolean addCardPieceInfo(SoulCardPiece info) {
		String sql = "insert into tb_u_soul_cardPiece (playerId,cardId,count) values (?,?,?)";
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getCardId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getCount()));
		
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;	
	}

	@Override
	public boolean updateCardPieceInfo(SoulCardPiece info) {
		String sql = "update tb_u_soul_cardPiece set count=? where playerId=? and cardId=?";
		if(!info.beginUpdate())return false;
		
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getCount()));
		params.put(2, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getCardId()));
		
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;		
	}

	@Override
	public SoulInfo getSoulInfo(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_soul_info where playerId="+playerId;
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		SoulInfo info = null;
		if(pst!=null){
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new SoulInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setExp(rs.getLong("exp"));
					info.setCard1(rs.getInt("card1"));
					info.setCard2(rs.getInt("card2"));
					info.setCard3(rs.getInt("card3"));
					info.setCard4(rs.getInt("card4"));
					info.setCard5(rs.getInt("card5"));
					info.setCard6(rs.getInt("card6"));
					info.setCard7(rs.getInt("card7"));
					info.setCard8(rs.getInt("card8"));
					
					info.setFuseSkillId1(rs.getInt("fuseSkillId1"));
					info.setFuseSkillId2(rs.getInt("fuseSkillId2"));
					info.setFuseSkillId3(rs.getInt("fuseSkillId3"));
					info.setFuseSkillId4(rs.getInt("fuseSkillId4"));
					
					info.setFuseSkillCreateTime1(rs.getTimestamp("fuseSkillCreateTime1"));
					info.setFuseSkillCreateTime2(rs.getTimestamp("fuseSkillCreateTime2"));
					info.setFuseSkillCreateTime3(rs.getTimestamp("fuseSkillCreateTime3"));
					info.setFuseSkillCreateTime4(rs.getTimestamp("fuseSkillCreateTime4"));
					
					info.setProficiency(rs.getInt("proficiency"));
					break;					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				info = null;
				Log.error("执行出错" + sql, e);
			} finally{
				closeConn(pst, rs);
			}
		}
		return info;
	}
	
	


	@Override
	public Map<Integer, SoulCardInfo> getSoulCards(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from  tb_u_soul_cardInfo where playerId="+playerId;
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		SoulCardInfo info = null;
		Map<Integer, SoulCardInfo> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new ConcurrentHashMap<>();
			while(rs.next()){
				info = new SoulCardInfo();
				info.setPlayerId(rs.getLong("playerId"));
				info.setCardId(rs.getInt("cardId"));
				info.setStar(rs.getInt("star"));
				info.setExp(rs.getLong("exp"));
				info.setSkill1(rs.getInt("skill1"));
				info.setSkill2(rs.getInt("skill2"));
				info.setSkill3(rs.getInt("skill3"));
				info.setSkill4(rs.getInt("skill4"));
				info.setIsPutOn(rs.getInt("isPutOn"));
				info.setLv(rs.getInt("lv"));
				infos.put(info.getCardId(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		} finally{
			closeConn(pst, rs);
		}
		return infos;
	}

	@Override
	public Map<Integer, SoulCardPiece> getCardPieces(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_soul_cardPiece where playerId="+playerId;
		PreparedStatement pst = this.execQuery(sql);
		ResultSet rs = null;
		Map<Integer, SoulCardPiece> infos = null;
		try {
			rs = pst.executeQuery();
			SoulCardPiece info = null;
			infos = new ConcurrentHashMap<>();
			while(rs.next()){
				info = new SoulCardPiece();
				info.setPlayerId(rs.getLong("playerId"));
				info.setCardId(rs.getInt("cardId"));
				info.setCount(rs.getInt("count"));
				infos.put(info.getCardId(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		}finally{
			closeConn(pst, rs);
		}
		return infos;
	}


	@Override
	public Map<Integer, SoulCardConfig> getCardConfigs() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_soul_template";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		SoulCardConfig info = null;
		Map<Integer, SoulCardConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while(rs.next()){
				info = new SoulCardConfig();
				info.setId(rs.getInt("id"));
				info.setName(rs.getString("name"));
				info.setQuality(rs.getInt("quality"));
				info.setAttrInit1(rs.getInt("attrInit1"));
				info.setAttrAdd1(rs.getInt("attrAdd1"));
				info.setAttrInit2(rs.getInt("attrInit2"));
				info.setAttrAdd2(rs.getInt("attrAdd2"));
				info.setAttrInit3(rs.getInt("attrInit3"));
				info.setAttrAdd3(rs.getInt("attrAdd3"));
				info.setAttrInit4(rs.getInt("attrInit4"));
				info.setAttrAdd4(rs.getInt("attrAdd4"));
				info.setCombo1(rs.getInt("combo1"));
				info.setCombo2(rs.getInt("combo2"));
				info.setCombo3(rs.getInt("combo3"));
				info.setCombo4(rs.getInt("combo4"));
				info.setNeedClip(rs.getInt("needClip"));
				info.setSkill(rs.getInt("skill"));	
				infos.put(info.getId(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		}finally{
			closeConn(pst, rs);
		}	
		return infos;
	}

	
	@Override
	public Map<Integer, CardLvConfig> getCardLvConfigs() {
		String sql = "select * from tb_z_soul_cardLevel";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		CardLvConfig info = null;
		Map<Integer, CardLvConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while(rs.next()){
				info = new CardLvConfig();
				info.setLev(rs.getInt("lev"));
				info.setExp(rs.getInt("exp"));
				info.setSpendItem(rs.getInt("spendItem"));
				info.setAddExp(rs.getInt("addExp"));
				infos.put(info.getLev(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		}finally{
			closeConn(pst, rs);
		}
	
		return infos;
	}
	
	
	@Override
	public Map<Integer, CardStarConfig> getCardStarConfig()  {
		String sql = "select * from tb_z_soul_star";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		CardStarConfig info = null;
		Map<Integer, CardStarConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while(rs.next()){
				info = new CardStarConfig();
				info.setStar(rs.getInt("star"));
				info.setSpendNum(rs.getInt("spendNum"));
				info.setAttrAdd(rs.getInt("attrAdd"));
				infos.put(info.getStar(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		}finally{
			closeConn(pst, rs);
		}
	
		return infos;
	}


	@Override
	public Map<Integer, CardSkillConfig> getCardSkillCofig() {
		// TODO Auto-generated method stub
		String sql = "select * from tb_z_soul_skill";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		CardSkillConfig info = null;
		Map<Integer, CardSkillConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while(rs.next()){
				info = new CardSkillConfig();
				info.setId(rs.getInt("id"));
				info.setName(rs.getString("name"));
				infos.put(info.getId(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		}finally{
			closeConn(pst, rs);
		}
	
		return infos;
	}


	@Override
	public boolean addSoulMake(SoulMake info) {
		// TODO Auto-generated method stub
		String sql ="insert into tb_u_soul_itemMake (playerId,state,startTime,killNum,totalTime,itemId) values(?,?,?,?,?,?)";
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getState()));
		params.put(3, new DbParameter(Types.TIMESTAMP, info.getStartTime()));
		params.put(4, new DbParameter(Types.INTEGER, info.getKillNum()));
		params.put(5, new DbParameter(Types.INTEGER, info.getTotalTime()));
		params.put(6, new DbParameter(Types.INTEGER, info.getItemId()));
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;	
	}


	@Override
	public boolean updateSoulMake(SoulMake info) {
		// TODO Auto-generated method stub
		String sql = "update tb_u_soul_itemMake set state=?,startTime=?,killNum=?,totalTime=?,itemId=? where playerId=?";
		if(!info.beginUpdate())return false;
		
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getState()));
		params.put(2, new DbParameter(Types.TIMESTAMP, info.getStartTime()));
		params.put(3, new DbParameter(Types.INTEGER, info.getKillNum()));
		params.put(4, new DbParameter(Types.INTEGER, info.getTotalTime()));
		params.put(5, new DbParameter(Types.INTEGER, info.getItemId()));
		params.put(6, new DbParameter(Types.BIGINT, info.getPlayerId()));
		
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;		
	}


	@Override
	public SoulMake getSoulMake(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_soul_itemMake where playerId="+playerId;
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		SoulMake info = null;
		if(pst!=null){
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new SoulMake();
					info.setPlayerId(rs.getLong("playerId"));
					info.setState(rs.getInt("state"));
					info.setStartTime(rs.getTimestamp("startTime"));
					info.setKillNum(rs.getInt("killNum"));
					info.setTotalTime(rs.getInt("totalTime"));
					info.setItemId(rs.getInt("itemId"));
					break;					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				info = null;
				Log.error("执行出错" + sql, e);
			} finally{
				closeConn(pst, rs);
			}
		}
		return info;
		
	}


	@Override
	public Map<Integer, SoulMakeConfig> getSoulMakeConfig() {
		String sql = "select * from tb_z_soul_proficiency";
		PreparedStatement pst = execQuery(sql);
		ResultSet rs = null;
		SoulMakeConfig info = null;
		Map<Integer, SoulMakeConfig> infos = null;
		try {
			rs = pst.executeQuery();
			infos = new HashMap<>();
			while(rs.next()){
				info = new SoulMakeConfig();
				info.setProficiency(rs.getInt("proficiency"));
				info.setGreen(rs.getInt("green"));
				info.setBule(rs.getInt("bule"));
				info.setPurple(rs.getInt("purple"));
				info.setOrange(rs.getInt("orange"));
				infos.put(info.getProficiency(), info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			infos = null;
			Log.error("执行出错" + sql, e);
		}finally{
			closeConn(pst, rs);
		}	
		return infos;
	}
	
}