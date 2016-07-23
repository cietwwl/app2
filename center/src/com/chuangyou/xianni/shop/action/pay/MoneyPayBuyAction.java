package com.chuangyou.xianni.shop.action.pay;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.action.BaseBuyGoodsAction;
import com.chuangyou.xianni.shop.action.IPayBuyGoods;

/**
 * 灵石购买
 * @author laofan
 *
 */
public class MoneyPayBuyAction extends BaseBuyGoodsAction implements IPayBuyGoods {

	public MoneyPayBuyAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean payBuyGoods(int num, long totalPrice) {
		// TODO Auto-generated method stub
		if(player.getBasePlayer().getPlayerInfo().getMoney()<totalPrice){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR,Protocol.C_REQ_BUYGOODS,"数据错误--金币不足");
			return false;
		}
		
		//todo扣金币
		player.getBasePlayer().consumeMoney(totalPrice);
		return true;
	}

}
