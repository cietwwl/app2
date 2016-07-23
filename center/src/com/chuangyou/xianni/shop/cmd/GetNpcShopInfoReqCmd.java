package com.chuangyou.xianni.shop.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoReqProto.GetNpcShopInfoReqMsg;
import com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopMsgHelper;
import com.chuangyou.xianni.shop.logic.ResetLogicFactory;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;
import com.chuangyou.xianni.socket.Cmd;

/**
 *  根据shopId请求商店信息
 * @author laofan
 *
 */
@Cmd(code = Protocol.C_REQ_GETNPCSHOPINFO,desc="请求NPC商店信息")
public class GetNpcShopInfoReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetNpcShopInfoReqMsg req = GetNpcShopInfoReqMsg.parseFrom(packet.getBytes());
		int shopId = req.getShopId();
		
		List<ShopCfg> list = ShopTemplateMgr.getShopList(shopId);
		
		GetNpcShopInfoRespMsg.Builder resp = GetNpcShopInfoRespMsg.newBuilder();
		resp.setNpcShopId(shopId);
		for (ShopCfg shopCfg : list) {
			if(shopCfg.isExpired())continue;  //过期开未开售去掉
			ResetLogicFactory.getResetLogic(player, shopCfg).reset(player, shopCfg);
			resp.addNpcGoodInfos(ShopMsgHelper.getGoodsInfoMsg(player, shopCfg));
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETNPCSHOPINFO, resp);
		player.sendPbMessage(pkg);
	}

}
