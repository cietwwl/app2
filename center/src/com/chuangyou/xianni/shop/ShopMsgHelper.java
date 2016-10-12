package com.chuangyou.xianni.shop;

import com.chuangyou.common.protobuf.pb.shop.BuyGoodsRespProto.BuyGoodsRespMsg;
import com.chuangyou.common.protobuf.pb.shop.GoodsInfoProto.GoodsInfo;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 商店消息回复处理器
 * @author laofan
 *
 */
public class ShopMsgHelper {


	/**
	 * 获取购买回馈消息
	 * @param player
	 * @param cfg
	 * @param isSuccess
	 * @return
	 */
	public static BuyGoodsRespMsg.Builder getBuyResult(GamePlayer player,ShopCfg cfg,boolean isSuccess){
		BuyGoodsRespMsg.Builder msg = BuyGoodsRespMsg.newBuilder();
		msg.setIsSuccess(isSuccess);		
		msg.setInfo(getGoodsInfoMsg(player,cfg));
		return msg;
	}
	
	
	/**
	 * 获取商品消息
	 * @param player
	 * @param cfg
	 * @return
	 */
	public static GoodsInfo.Builder getGoodsInfoMsg(GamePlayer player,ShopCfg cfg){
		GoodsInfo.Builder info = GoodsInfo.newBuilder();
		info.setPrivateId(cfg.getId());
		info.setItemType(cfg.getItemType());
		info.setBind(cfg.getBind());
		info.setMoneyType(cfg.getMoneyType());
		info.setPrice(cfg.getPrice());
		info.setDiscount(cfg.getDiscount());	
		info.setServerLimitNum(cfg.getServerLimitNum());
		if(cfg.getServerLimitNum()>0){
			info.setServerProcured(ShopServerManager.get(cfg.getId()).getBuyNum());
		}
		info.setPersonLimitNum(cfg.getPersonLimitNum());
		if(cfg.getPersonLimitNum()>0){
			info.setRoleProcured(player.getShopInventory().get(cfg.getId()).getBuyNum());
		}
		info.setTimeType(cfg.getTimeType());
		info.setShelvesTime(cfg.getShelvesTime());
		info.setShelfTime(cfg.getShelfTime());
		info.setStartTime(cfg.getStartTime());
		info.setEndTime(cfg.getEndTime());
		info.setResetTime(cfg.getResetTime());
		info.setTab(cfg.getTab());
		info.setSort(cfg.getSort());
		info.setIsPreview(cfg.getIsPreview());
		info.setEasyBuy(cfg.getEasyBuy());
		info.setVipLv(cfg.getVipLv());
		info.setDiscountStart(cfg.getDiscountStart());
		info.setDiscountEnd(cfg.getDiscountEnd());
		
		info.setMoneyType1(cfg.getMoneyType1());
		info.setPrice1(cfg.getPrice1());
		info.setLevel(cfg.getLevel());
		return info;
	}
	
	
	
}
