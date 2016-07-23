package com.chuangyou.xianni.shop.action.limit;

import com.chuangyou.common.protobuf.pb.shop.BuyGoodsRespProto.BuyGoodsRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopMsgHelper;
import com.chuangyou.xianni.shop.action.BaseBuyGoodsAction;
import com.chuangyou.xianni.shop.action.ILimitBuyGoods;
import com.chuangyou.xianni.shop.action.IPayBuyGoods;
import com.chuangyou.xianni.shop.action.pay.BindCashPayBuyAction;
import com.chuangyou.xianni.shop.action.pay.CashPayBuyAction;
import com.chuangyou.xianni.shop.action.pay.ItemPayBuyAction;
import com.chuangyou.xianni.shop.action.pay.MoneyPayBuyAction;
import com.chuangyou.xianni.shop.action.pay.PointsPayBuyAction;

/**
 * 商品限购基类
 * @author laofan
 *
 */
public abstract class BaseLimitBuyAction extends BaseBuyGoodsAction implements ILimitBuyGoods {

	public BaseLimitBuyAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPayBuyGoods getPayBuyGoods() {
		// TODO Auto-generated method stub
		if(cfg!=null && player!=null){
			if(cfg.getMoneyType() == EnumAttr.MONEY.getValue()){
				return new MoneyPayBuyAction(player, cfg);
			}else if(cfg.getMoneyType() == EnumAttr.CASH.getValue()){
				return new CashPayBuyAction(player, cfg);
			}else if(cfg.getMoneyType() == EnumAttr.CASH_BIND.getValue()){
				return new BindCashPayBuyAction(player, cfg);
			}else if(cfg.getMoneyType() == EnumAttr.POINTS.getValue()){
				return new PointsPayBuyAction(player, cfg);
			}else {
				if(ItemManager.findItemTempInfo(cfg.getMoneyType())!=null){
					return new ItemPayBuyAction(player, cfg);
				}else{
					Log.error(cfg.getId()+"商店表中，该商品消耗配置错误",new Exception());
				}
			}
		}	
		return null;
	}


	@Override
	public void limitBuy(int num, long totalPrice) {
		// TODO Auto-generated method stub
		//扣钱
		if(getPayBuyGoods().payBuyGoods(num, totalPrice)){
			if(updateCache(num, totalPrice)){
				addItemInBag(num, totalPrice);
				sendResult(true);
			}
		}
	}
	
	/**
	 * 更新计数器
	 * @return
	 */
	public boolean updateCache(int num, long totalPrice){
		return true;
	}
	
	/**
	 * 添加物品入背包
	 * @return
	 */
	protected boolean addItemInBag(int num, long totalPrice){
		boolean isBind = false;
		if(cfg.getBind() == 1){
			isBind = true;
		}
		player.getBagInventory().addItemInBagOrEmail(cfg.getItemType(), num, ItemAddType.NPC_SHOP_ADD, isBind);
		return true;
	}
	
	/**
	 * 发送返回结果
	 * @param isSuccess
	 */
	protected void sendResult(boolean isSuccess){
		BuyGoodsRespMsg.Builder msg = ShopMsgHelper.getBuyResult(player, cfg, isSuccess);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_BUYGOODS, msg);
		player.sendPbMessage(pkg);
	}
	

}
