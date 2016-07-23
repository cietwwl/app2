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
 * 物品兑换
 * @author laofan
 *
 */
public class ItemPayBuyAction extends BaseBuyGoodsAction implements IPayBuyGoods {

	public ItemPayBuyAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean payBuyGoods(int num, long totalPrice) {
		// TODO Auto-generated method stub
		if(player.getBagInventory().getPlayerBag().isEnough(cfg.getMoneyType(), num)== false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR,Protocol.C_REQ_BUYGOODS,"数据错误--物品数量不足");
			return false;
		}else{
			player.getBagInventory().removeItem(cfg.getMoneyType(), num, ItemRemoveType.NPC_SHOP);
			return true;
		}			
	}

}
