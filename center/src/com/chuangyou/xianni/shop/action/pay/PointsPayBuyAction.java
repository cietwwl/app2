package com.chuangyou.xianni.shop.action.pay;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.action.BaseBuyGoodsAction;
import com.chuangyou.xianni.shop.action.IPayBuyGoods;

/**
 * 积分兑换
 * @author laofan
 *
 */
public class PointsPayBuyAction extends BaseBuyGoodsAction implements IPayBuyGoods {

	public PointsPayBuyAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean payBuyGoods(int num, long totalPrice) {
		// TODO Auto-generated method stub
		if(totalPrice>player.getBasePlayer().getPlayerInfo().getPoints()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR,Protocol.C_REQ_BUYGOODS,"数据错误--积分不足");
			return false;
		}else{
			player.getBasePlayer().consumePoints((int) totalPrice);
			return true;
		}
		
		
	}

}
