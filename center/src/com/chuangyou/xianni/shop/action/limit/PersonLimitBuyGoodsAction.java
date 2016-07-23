package com.chuangyou.xianni.shop.action.limit;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.entity.shop.ShopUserCache;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 个人限购商品购买
 * @author laofan
 *
 */
public class PersonLimitBuyGoodsAction extends BaseLimitBuyAction {

	public PersonLimitBuyGoodsAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void limitBuy(int num, long totalPrice) {
		// TODO Auto-generated method stub
		if(limitCheck(num, totalPrice)){			
			super.limitBuy(num, totalPrice);
		}else{
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.NPC_SHOP_NOT_ENGOUCH, Protocol.C_REQ_BUYGOODS,"商品数量不足");
			sendResult(false);
		}
	}

	
	/**
	 * 检测购买数理的合法性
	 */
	@Override
	public boolean limitCheck(int num, long totalPrice) {
		// TODO Auto-generated method stub
		ShopUserCache shop = player.getShopInventory().get(cfg.getId());
		if(shop.getBuyNum()+num>cfg.getPersonLimitNum()){			
			return false;
		}else{
			return true;
		}
	}


	@Override
	public boolean updateCache(int num, long totalPrice) {
		// TODO Auto-generated method stub
		ShopUserCache shop = player.getShopInventory().get(cfg.getId());
		shop.setBuyNum((short) (shop.getBuyNum()+num));
		shop.setOp(Option.Update);
		return true;
	}




}
