package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.CardLvConfig;
import com.chuangyou.xianni.entity.soul.CardSkillConfig;
import com.chuangyou.xianni.entity.soul.CardStarConfig;
import com.chuangyou.xianni.entity.soul.FuseItemConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulCardPiece;
import com.chuangyou.xianni.entity.soul.SoulFuseSkillConfig;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.entity.soul.SoulMakeConfig;

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
	public Map<Integer, SoulCardPiece> getCardPieces(long playerId);
	
	public boolean addSoulMake(SoulMake info);
	public boolean updateSoulMake(SoulMake info);
	public SoulMake getSoulMake(long playerId);
	
	
	public Map<Integer,SoulCardConfig> getCardConfigs();
	public Map<Integer,CardLvConfig> getCardLvConfigs();
	public Map<Integer, CardStarConfig> getCardStarConfig();
	public Map<Integer,CardSkillConfig> getCardSkillCofig();
	public Map<Integer, SoulMakeConfig> getSoulMakeConfig();
	public Map<Integer, SoulFuseSkillConfig> getFuseSkillConfig();
	public Map<Integer, FuseItemConfig> getFuseItemConfig();
	public Map<Integer, CardComboConfig> getCardComboConfig();
	
}
