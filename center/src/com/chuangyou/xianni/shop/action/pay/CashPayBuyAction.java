package com.chuangyou.xianni.shop.action.pay;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.action.BaseBuyGoodsAction;
import com.chuangyou.xianni.shop.action.IPayBuyGoods;

/**
 * 仙玉购买
 * 
 * @author laofan
 *
 */
public class CashPayBuyAction extends BaseBuyGoodsAction implements IPayBuyGoods {

	public CashPayBuyAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean payBuyGoods(int num, long totalPrice) {
		// TODO Auto-generated method stub
		if (totalPrice > player.getBasePlayer().getPlayerInfo().getCash()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_BUYGOODS, "数据错误--仙玉不足");
			return false;
		} else {
			player.getBasePlayer().consumeCash((int) totalPrice, ItemRemoveType.CASH_BUY);
			return true;
		}
	}

}
