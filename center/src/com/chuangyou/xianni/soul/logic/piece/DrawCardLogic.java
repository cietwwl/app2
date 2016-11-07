package com.chuangyou.xianni.soul.logic.piece;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.soul.CardPieceProto.CardPieceMsg;
import com.chuangyou.common.protobuf.pb.soul.DrawCardRespProto.DrawCardRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.common.util.random.IWeight;
import com.chuangyou.common.util.random.WeightRandomUtil;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.soul.CardRateConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.soul.logic.calc.weight.CardColorWeight;
import com.chuangyou.xianni.soul.logic.calc.weight.CardTypeWeight;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 抽卡基类
 * @author laofan
 *
 */
public abstract class DrawCardLogic {
	
	protected final List<CardPieceMsg> cards = new ArrayList<>();
	
	protected final int type;
	
	protected final GamePlayer player;
	

	public DrawCardLogic(int type, GamePlayer player) {
		this.type = type;
		this.player = player;
	}



	/**
	 * 处理抽卡
	 * @param player
	 * @param type
	 */
	public void process(){
		if(consumeMoney()){
			if(randomAndAddCard()){
				this.sendResult();
			}
		}
	}
	
	/**
	 *  随机和添加卡牌碎片
	 */
	protected boolean randomAndAddCard(){
		CardRateConfig config = getRateConfig();
		CardColorWeight cardColorWeight = this.getRandomCardColor(config);
		
		addDrawTime(); //添加次数
		CardTypeWeight cardTypeWeight = mustGetCardId();
		if(cardTypeWeight!=null){ //必出卡牌碎片
			
		}else{    //随机卡
			if(cardColorWeight == null){
				Log.error("抽卡时随机卡牌颜色出错：type"+type+"==>playerId:"+player.getPlayerId());
				return false;
			}
			int num = ThreadSafeRandom.getInstance().next(cardColorWeight.getStartNum(), cardColorWeight.getEndNum());
			cardTypeWeight =getRandomCard(cardColorWeight.getColor());
			if(cardTypeWeight == null){
				Log.error("抽卡时随机卡牌ID出错："+cardColorWeight.getColor()+"===>playerId:"+player.getPlayerId());
				return false;
			}
			cardTypeWeight.setNum(num);
		}
		if(!addCardPiece(cardTypeWeight.getCardId(),cardTypeWeight.getNum())){
			Log.error("添加卡牌出错:cardTypeWeight:"+cardTypeWeight.toString()+"===>playerId:"+player.getPlayerId());
			return false;
		}			
		
		return true;
	}
	
	
	/**
	 * 获取必出卡ID
	 * @return
	 */
	protected abstract CardTypeWeight mustGetCardId();
	
	/**
	 * 添加抽卡次数
	 */
	protected abstract void addDrawTime();
	/**
	 * 获取当前次数
	 * @return
	 */
	protected abstract int getCurDrawTime();
	/**
	 * 获取随机配置表
	 * @return
	 */
	protected abstract CardRateConfig getRateConfig();
	
	/**
	 * 消耗钱和添加抽奖次数
	 * @return
	 */
	protected abstract boolean consumeMoney();
	
	/**
	 * 发送结果回复
	 */
	protected abstract void sendResult();
	
	
	/**
	 * 向背包中添加卡牌碎片 
	 * @param templateId
	 * @param count
	 * @param player
	 * @return
	 */
	protected boolean addCardPiece(int templateId,int count){
		if(player.getBagInventory().addItem(templateId, count, ItemAddType.LUCK_CARD, true)){
			CardPieceMsg.Builder sub = CardPieceMsg.newBuilder();
			sub.setCardCount(count);
			sub.setCardId(templateId);
			cards.add(sub.build());
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 随机出卡的颜色
	 * @return
	 */
	protected CardColorWeight getRandomCardColor(CardRateConfig config){
		List<IWeight> list = new ArrayList<>();
		list.add(new CardColorWeight(1, config.getClipChanceWhiteStartNum(), config.getClipChanceWhiteEndNum(), config.getClipChanceWhite()));
		list.add(new CardColorWeight(2, config.getClipChanceGreenStartNum(), config.getClipChanceGreenEndNum(), config.getClipChanceGreen()));
		list.add(new CardColorWeight(3, config.getClipChanceBuleStartNum(), config.getClipChanceBuleEndNum(), config.getClipChanceBule()));
		list.add(new CardColorWeight(4, config.getClipChancePurpleStartNum(), config.getClipChancePurpleEndNum(), config.getClipChancePurple()));
		list.add(new CardColorWeight(5, config.getClipChanceOrangeStartNum(), config.getClipChanceOrangeEndNum(), config.getClipChanceOrange()));
		list.add(new CardColorWeight(6, config.getClipChanceRedStartNum(), config.getClipChanceRedEndNum(), config.getClipChanceRed()));
		CardColorWeight cardColorWeight = (CardColorWeight) WeightRandomUtil.getRandomWeight(list);
		return cardColorWeight;
	}
	
	
	/**
	 * 根据颜色随机出卡牌
	 * @param color
	 * @return
	 */
	protected CardTypeWeight getRandomCard(int color){
		List<SoulCardConfig> pools = SoulTemplateMgr.getColorPoolsMap().get(color);
		List<IWeight> cardList = new ArrayList<>();
		if(pools!=null){
			for (SoulCardConfig soulCardConfig : pools) {
				cardList.add(new CardTypeWeight(soulCardConfig.getId(), soulCardConfig.getWeight()));
			}
			CardTypeWeight cardTypeWeight = (CardTypeWeight) WeightRandomUtil.getRandomWeight(cardList);
			return cardTypeWeight;						
		}else{
			Log.error("随机池获取不到："+color);
		}
		return null;
	}
	

	/**
	 * 获取要发送给客户端的基本消息
	 * @return
	 */
	protected DrawCardRespMsg.Builder getBaseResultMsg(){
		DrawCardRespMsg.Builder resp = DrawCardRespMsg.newBuilder();
		resp.setType(type);
		resp.addAllInfos(cards);
		return resp;
	}
	
	
}
