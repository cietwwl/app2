package com.chuangyou.xianni.shop.action;

import com.chuangyou.common.protobuf.pb.shop.BuyGoodsRespProto.BuyGoodsRespMsg;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopMsgHelper;
import com.chuangyou.xianni.shop.action.limit.NoLimitBuyGoodsAction;
import com.chuangyou.xianni.shop.action.limit.PAndSLimitBuyGoodsAction;
import com.chuangyou.xianni.shop.action.limit.PersonLimitBuyGoodsAction;
import com.chuangyou.xianni.shop.action.limit.ServerLimitBuyGoodsAction;
import com.chuangyou.xianni.shop.logic.ResetLogicFactory;

public class BuyGoodsAction extends BaseBuyGoodsAction implements IBuyGoods {

	public BuyGoodsAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void buy(int num, long totalPrice) {
		// TODO Auto-generated method stub
		
		//todo 重置相关商品数据
		ResetLogicFactory.getResetLogic(player, cfg).reset(player, cfg);
		
		//todo 校对一下价格是否正确。若不正确。同步数据给客户端
		if(Math.ceil(cfg.getPrice()*num*getDiscount()*0.01)!=totalPrice){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ERROR_PRICE, Protocol.C_REQ_BUYGOODS,"价格不对");
			
			BuyGoodsRespMsg.Builder msg = ShopMsgHelper.getBuyResult(player, cfg, false);
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_BUYGOODS, msg);
			player.sendPbMessage(pkg);
			return;
		}
		if(player.getBasePlayer().getPlayerInfo().getVipLevel()<cfg.getVipLv() && cfg.getShopid() == ShopCfg.SHOP_VIP){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.VIP_LEVEL_UNENOUGH, Protocol.C_REQ_BUYGOODS,"VIP等级不够");
			return;
		}
		getLimitBuy().limitBuy(num, totalPrice);		
	}
	
	
	private int getDiscount(){
		if(cfg.getDiscountStart().equals("0") || cfg.getDiscountEnd().equals("0"))return cfg.getDiscount();
		if(TimeUtil.isInDate(System.currentTimeMillis(), 
				TimeUtil.getDateByString(cfg.getDiscountStart(),3),
				TimeUtil.getDateByString(cfg.getDiscountEnd(),3))){
			return cfg.getDiscount();
		}else{
			return 100;
		}
	}

	@Override
	public ILimitBuyGoods getLimitBuy() {
		// TODO Auto-generated method stub
		if(cfg!=null && player!=null){
			if(cfg.getPersonLimitNum()>0 && cfg.getServerLimitNum()>0){
				return new PAndSLimitBuyGoodsAction(player, cfg);
			}else if(cfg.getPersonLimitNum()>0){
				return new PersonLimitBuyGoodsAction(player, cfg);
			}else if(cfg.getServerLimitNum()>0){
				return new ServerLimitBuyGoodsAction(player, cfg);
			}else{
				return new NoLimitBuyGoodsAction(player, cfg);
			}
		}else{
			return null;
		}
	}

}
