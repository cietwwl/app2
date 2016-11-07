package com.chuangyou.xianni.soul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.OtherSoulgProto.OtherSoulMsg;
import com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg;
import com.chuangyou.xianni.army.Hero;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.soul.logic.CheckFuseSkillLogic;
import com.chuangyou.xianni.soul.logic.calc.SoulCalcAddLogic;
import com.chuangyou.xianni.soul.logic.calc.SoulCalcLogic;
import com.chuangyou.xianni.soul.logic.make.KillMonsterListener;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SoulInventory extends AbstractEvent implements IInventory {

	private GamePlayer					player;
	/**
	 * 基本信息
	 */
	private SoulInfo					soulInfo;
	
	/**
	 * 拥有的卡
	 */
	private Map<Integer, SoulCardInfo>	cards;
	/**
	 * 拥有的碎片
	 */
//	private Map<Integer, SoulCardPiece>	pieces;

	/** 需要同步的属性协议 */
	private List<PropertyMsg>			list;

	/**
	 * 材料制作
	 */
	private SoulMake					soulMake;

	/**
	 * 杀怪监听
	 */
	private KillMonsterListener			listener;

	/** 临时位置《--》技能 */
	private Map<Integer, FuseSkillVo>	tempMap	= new HashMap<>();

	public SoulInventory(GamePlayer palyer) {
		this.player = palyer;
	}

	public SoulInfo getSoulInfo() {
		if (soulInfo == null) {
			soulInfo = new SoulInfo();
			soulInfo.setPlayerId(player.getPlayerId());
			soulInfo.setOp(Option.Insert);
			DBManager.getSoulDao().addSoulInfo(soulInfo);
		}
		return soulInfo;
	}

	public void addSoulCard(SoulCardInfo info) {
		if (cards.containsKey(info.getCardId()))
			return;
		cards.put(info.getCardId(), info);
		info.setOp(Option.Insert);
		DBManager.getSoulDao().addSoulCardInfo(info);
	}

//	public void addCardPiece(SoulCardPiece piece) {
//		if (pieces.containsKey(piece.getCardId()))
//			return;
//		pieces.put(piece.getCardId(), piece);
//		piece.setOp(Option.Insert);
//		DBManager.getSoulDao().addCardPieceInfo(piece);
//	}

	// ====================================================================================
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		soulInfo = DBManager.getSoulDao().getSoulInfo(player.getPlayerId());
		cards = DBManager.getSoulDao().getSoulCards(player.getPlayerId());
	//	pieces = DBManager.getSoulDao().getCardPieces(player.getPlayerId());
		this.soulMake = DBManager.getSoulDao().getSoulMake(player.getPlayerId());
		if (this.soulMake != null && this.soulMake.getState() == SoulMake.STATE_TASK_ING) {
			initListener();
		}
		return true;
	}

	/**
	 * 初始化监听
	 */
	public void initListener() {
		if (this.listener != null) {
			this.player.removeListener(this.listener, EventNameType.TASK_KILL_MONSTER);
		}
		this.listener = new KillMonsterListener(this.player);
		this.player.addListener(listener, EventNameType.TASK_KILL_MONSTER);
	}

	/**
	 * 移除监听
	 */
	public void removeListener() {
		if (this.listener != null) {
			this.player.removeListener(this.listener, EventNameType.TASK_KILL_MONSTER);
		}
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		if (cards != null) {
			cards.clear();
			cards = null;
		}
//		if (pieces != null) {
//			pieces.clear();
//			pieces = null;
//		}
		soulInfo = null;
		this.soulMake = null;

		removeListener();
		this.listener = null;

		if (this.tempMap != null) {
			this.tempMap.clear();
			this.tempMap = null;
		}
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		
		if (soulInfo != null) {
			new CheckFuseSkillLogic().checkFuseSkillAndSync(player);
			if (soulInfo.getOp() == Option.Update) {
				DBManager.getSoulDao().updateSoulInfo(soulInfo);
			} else if (soulInfo.getOp() == Option.Insert) {
				DBManager.getSoulDao().addSoulInfo(soulInfo);
			}
		}
		if (cards != null) {
			Iterator<SoulCardInfo> it = cards.values().iterator();
			while (it.hasNext()) {
				SoulCardInfo info = it.next();
				if (info.getOp() == Option.Update) {
					DBManager.getSoulDao().updateSoulCardInfo(info);
				} else if (info.getOp() == Option.Insert) {
					DBManager.getSoulDao().addSoulCardInfo(info);
				}
			}
		}
//		if (pieces != null) {
//			Iterator<SoulCardPiece> it = pieces.values().iterator();
//			while (it.hasNext()) {
//				SoulCardPiece info = it.next();
//				if (info.getOp() == Option.Update) {
//					DBManager.getSoulDao().updateCardPieceInfo(info);
//				} else if (info.getOp() == Option.Insert) {
//					DBManager.getSoulDao().addCardPieceInfo(info);
//				}
//			}
//		}

		if (soulMake != null) {
			if (soulMake.getOp() == Option.Update) {
				DBManager.getSoulDao().updateSoulMake(soulMake);
			} else if (soulMake.getOp() == Option.Insert) {
				DBManager.getSoulDao().addSoulMake(soulMake);
			}
		}

		return true;
	}

	/**
	 * 计算属性
	 * 
	 * @param soulData
	 * @param soulPer
	 */
	public void computeSoulAtt(BaseProperty soulData, BaseProperty soulPer) {
		new SoulCalcLogic().calcBaseSoulAtts(player, soulPer, soulData);
	}

	/**
	 * 更新属性
	 */
	public void updateProperty() {
		if (player.getArmyInventory() != null) {
			BaseProperty soulData = new BaseProperty();
			BaseProperty soulPer = new BaseProperty();

			computeSoulAtt(soulData, soulPer);
			player.getArmyInventory().getHero().addSoul(soulData, soulPer);
			player.getArmyInventory().updateProperty();
		}
	}

	public Map<Integer, SoulCardInfo> getCards() {
		return cards;
	}

//	public Map<Integer, SoulCardPiece> getPieces() {
//		return pieces;
//	}

	public List<PropertyMsg> getList() {
		return list;
	}

	public void setList(List<PropertyMsg> list) {
		this.list = list;
	}

	public SoulMake getSoulMake() {
		if (soulMake == null) {
			soulMake = new SoulMake();
			soulMake.setPlayerId(player.getPlayerId());
			soulMake.setOp(Option.Insert);
			DBManager.getSoulDao().addSoulMake(soulMake);
		}
		return soulMake;
	}

	public Map<Integer, FuseSkillVo> getTempMap() {
		return tempMap;
	}

	/**
	 * 获取魂幡技能列表消息
	 * 初始化时。同步给scene服务器
	 * @param player
	 * @return
	 */
	public List<FuseSkillMsg> getFuseSkillPacket(GamePlayer player) {
		List<FuseSkillMsg> list = new ArrayList<>();
		SoulCalcAddLogic logic = new SoulCalcAddLogic();
		
		if (soulInfo.getFuseSkillId1() > 0 && !logic.isExpire(soulInfo.getFuseSkillCreateTime1(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			FuseSkillMsg.Builder msg = FuseSkillMsg.newBuilder();
			msg.setIndex(1);
			msg.setColor(soulInfo.getFuseSkillColor1());
			msg.setFuseSkillId(soulInfo.getFuseSkillId1());
			list.add(msg.build());
		}
		if (soulInfo.getFuseSkillId2() > 0 && !logic.isExpire(soulInfo.getFuseSkillCreateTime2(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			FuseSkillMsg.Builder msg = FuseSkillMsg.newBuilder();
			msg.setIndex(2);
			msg.setColor(soulInfo.getFuseSkillColor2());
			msg.setFuseSkillId(soulInfo.getFuseSkillId2());
			list.add(msg.build());
		}
		if (soulInfo.getFuseSkillId3() > 0 && !logic.isExpire(soulInfo.getFuseSkillCreateTime3(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			FuseSkillMsg.Builder msg = FuseSkillMsg.newBuilder();
			msg.setIndex(3);
			msg.setColor(soulInfo.getFuseSkillColor3());
			msg.setFuseSkillId(soulInfo.getFuseSkillId3());
			list.add(msg.build());
		}
		if (soulInfo.getFuseSkillId4() > 0 && !logic.isExpire(soulInfo.getFuseSkillCreateTime4(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			FuseSkillMsg.Builder msg = FuseSkillMsg.newBuilder();
			msg.setIndex(4);
			msg.setColor(soulInfo.getFuseSkillColor4());
			msg.setFuseSkillId(soulInfo.getFuseSkillId4());
			list.add(msg.build());
		}
		return list;

	}

	/** 写入其他用户查看信息 */
	public void writeInSimpOtherSnap(OtherSoulMsg.Builder proto) {
		if (soulInfo == null || cards == null) {
			return;
		}
		proto.setPlayerId(soulInfo.getPlayerId());
		if (soulInfo.getCard1() != 0) {
			proto.setCard1(soulInfo.getCard1());
			SoulCardInfo info = cards.get(soulInfo.getCard1());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}
		if (soulInfo.getCard2() != 0) {
			proto.setCard2(soulInfo.getCard2());
			SoulCardInfo info = cards.get(soulInfo.getCard2());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}
		if (soulInfo.getCard3() != 0) {
			proto.setCard3(soulInfo.getCard3());
			SoulCardInfo info = cards.get(soulInfo.getCard3());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}
		if (soulInfo.getCard4() != 0) {
			proto.setCard4(soulInfo.getCard4());
			SoulCardInfo info = cards.get(soulInfo.getCard4());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}
		if (soulInfo.getCard5() != 0) {
			proto.setCard5(soulInfo.getCard5());
			SoulCardInfo info = cards.get(soulInfo.getCard5());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}
		if (soulInfo.getCard6() != 0) {
			proto.setCard6(soulInfo.getCard6());
			SoulCardInfo info = cards.get(soulInfo.getCard6());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}
		if (soulInfo.getCard7() != 0) {
			proto.setCard7(soulInfo.getCard7());
			SoulCardInfo info = cards.get(soulInfo.getCard7());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}
		if (soulInfo.getCard8() != 0) {
			proto.setCard8(soulInfo.getCard8());
			SoulCardInfo info = cards.get(soulInfo.getCard8());
			if (info != null) {
				proto.addCardInfos(info.getMsg());
			}
		}

		BaseProperty skillData = new BaseProperty();
		BaseProperty skillPer = new BaseProperty();
		// 加入技能属性
		computeSoulAtt(skillData, skillPer);
		Hero tempHero = new Hero(this.player);
		tempHero.addMagicwp(skillData, skillPer);
		PropertyListMsg.Builder propertyMsgs = PropertyListMsg.newBuilder();
		tempHero.writeProto(propertyMsgs);
		proto.setPropertitys(propertyMsgs);
		tempHero = null;
	}

}
