package com.chuangyou.xianni.shop.cmd;

import com.chuangyou.common.protobuf.pb.shop.BuyGoodsReqProto.BuyGoodsReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.action.BuyGoodsAction;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_BUYGOODS,desc="购买商店物品")
public class ShopBuyGoodCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		BuyGoodsReqMsg req = BuyGoodsReqMsg.parseFrom(packet.getBytes());
		int privateId = req.getPrivateId();
		int num = req.getCount();
		long totalPrice = req.getTotalMoney();
		ShopCfg cfg = ShopTemplateMgr.getShopCfg(privateId);
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.NO_THE_GOODS, Protocol.C_REQ_BUYGOODS,"查无此商品");
			return;
		}
		new BuyGoodsAction(player, cfg).buy(num, totalPrice);
		
	}

}
