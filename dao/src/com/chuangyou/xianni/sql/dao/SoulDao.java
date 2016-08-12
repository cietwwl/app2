package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulCardPiece;
import com.chuangyou.xianni.entity.soul.SoulInfo;

/**
 * 魂幡
 * @author laofan
 *
 */
public interface SoulDao {
	
	public boolean addSoulInfo(SoulInfo info);
	public boolean updateSoulInfo(SoulInfo info);
	public SoulInfo getSoulInfo(long playerId);
	
	
	public boolean addSoulCardInfo(SoulCardInfo info);
	public boolean updateSoulCardInfo(SoulCardInfo info);
	public Map<Integer, SoulCardInfo> getSoulCards(long playerId);

	public boolean addCardPieceInfo(SoulCardPiece info);
	public boolean updateCardPieceInfo(SoulCardPiece info);
	public Map<Integer, SoulCardInfo> getCardPieces(long playerId);
	
}
