package com.chuangyou.xianni.sql.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulCardPiece;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.sql.dao.SoulDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class SoulDaoImpl extends BaseDao implements SoulDao {

		
	@Override
	public boolean addSoulInfo(SoulInfo info) {
		String sql = "insert into tb_u_soul_info (playerId,exp,card1,card2,card3,card4,card5,"
				+ "card6,card7,card8,fuseSkillId1,fuseSkillId2,fuseSkillId3,fuseSkillId4,fuseSkillCreateTime1,"
				+ "fuseSkillCreateTime2,fuseSkillCreateTime3,fuseSkillCreateTime4) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
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
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;
	}

	
	@Override
	public boolean updateSoulInfo(SoulInfo info) {
		// TODO Auto-generated method stub
		String sql = "update tb_u_soul_info set exp=?,card1=?,card2=?,card3=?,card4=?,card5=?,"
				+ "card6=?,card7=?,card8=?,fuseSkillId1=?,fuseSkillId2=?,fuseSkillId3=?,fuseSkillId4=?,fuseSkillCreateTime1=?,"
				+ "fuseSkillCreateTime2=?,fuseSkillCreateTime3=?,fuseSkillCreateTime4=? where playerId=?";
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
		params.put(18, new DbParameter(Types.BIGINT, info.getPlayerId()));
				
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitUpdate(result);
		return result;
	}
	

	@Override
	public boolean addSoulCardInfo(SoulCardInfo info) {
		String sql = "insert into tb_u_soul_cardInfo (playerId,cardId,start,exp,skill1,skill2,skill3,skill4,isPutOn) values(?,?,?,?,?,?,?,?,?)";
		if(!info.beginAdd())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getCardId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getStart()));
		params.put(4, new DbParameter(Types.BIGINT, info.getExp()));
		params.put(5, new DbParameter(Types.INTEGER, info.getSkill1()));
		params.put(6, new DbParameter(Types.INTEGER, info.getSkill2()));
		params.put(7, new DbParameter(Types.INTEGER, info.getSkill3()));
		params.put(8, new DbParameter(Types.INTEGER, info.getSkill4()));
		params.put(9, new DbParameter(Types.INTEGER, info.getIsPutOn()));
		
		boolean result = execNoneQuery(sql, params) > -1 ? true : false;
		info.commitAdd(result);
		return result;	
	}

	
	@Override
	public boolean updateSoulCardInfo(SoulCardInfo info) {
		String sql = "update tb_u_soul_cardInfo set start=?,exp=?,skill1=?,skill2=?,skill3=?,skill4=?,isPutOn=? where playerId=? and cardId=?";
		if(!info.beginUpdate())return false;
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getStart()));
		params.put(2, new DbParameter(Types.BIGINT, info.getExp()));
		params.put(3, new DbParameter(Types.INTEGER, info.getSkill1()));
		params.put(4, new DbParameter(Types.INTEGER, info.getSkill2()));
		params.put(5, new DbParameter(Types.INTEGER, info.getSkill3()));
		params.put(6, new DbParameter(Types.INTEGER, info.getSkill4()));
		params.put(7, new DbParameter(Types.INTEGER, info.getIsPutOn()));
		params.put(8, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(9, new DbParameter(Types.INTEGER, info.getCardId()));
		
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
		String sql = "update tb_u_soul_cardPiece count=? where playerId=? and cardId=?";
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
		return null;
	}
	


	@Override
	public Map<Integer, SoulCardInfo> getSoulCards(long playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, SoulCardInfo> getCardPieces(long playerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
