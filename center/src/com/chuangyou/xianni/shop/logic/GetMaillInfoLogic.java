package com.chuangyou.xianni.shop.logic;

import java.util.List;

import com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg;
import com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopMsgHelper;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;

public class GetMaillInfoLogic {
	
	public void process(GamePlayer player,int type){
		if(type == 1 ){
			doNormalResult(player);
		}else if(type == 2){
			if(player.getShopInventory().isRefreshData()){
				doNormalResult(player);
			}else{
				GetMallInfoRespMsg.Builder resp = GetMallInfoRespMsg.newBuilder();
				resp.setResultType(2);
				PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_MALL_INFO,resp);
				player.sendPbMessage(pkg);
			}
		}
	}
	
	
	/**
	 * 做正常的数据返回处理
	 */
	public void doNormalResult(GamePlayer player){
		if(player.getShopInventory()==null)return;	
		doOneNormalResult(player,ShopCfg.SHOP_NORMAL);
		doOneNormalResult(player,ShopCfg.SHOP_FASION);
		doOneNormalResult(player,ShopCfg.SHOP_LIMIT_BUY);
		doOneNormalResult(player,ShopCfg.SHOP_POINTS);
		doOneNormalResult(player,ShopCfg.SHOP_VIP);
		player.getShopInventory().setRefreshData(false);
	}
	
	
	private void doOneNormalResult(GamePlayer player,int shopType){
		GetMallInfoRespMsg.Builder resp = GetMallInfoRespMsg.newBuilder();
		resp.setResultType(1);
		resp.addInfos(getInfosMsg(shopType,player));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_MALL_INFO,resp);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 获取数据
	 * @param shopId
	 * @return
	 */
	public GetNpcShopInfoRespMsg.Builder getInfosMsg(int shopId,GamePlayer player){
		GetNpcShopInfoRespMsg.Builder infos= GetNpcShopInfoRespMsg.newBuilder();
		List<ShopCfg> list = ShopTemplateMgr.getShopList(shopId);
		infos.setNpcShopId(shopId);
		if(list!=null){			
			for (ShopCfg cfg : list) {
				if(!cfg.isShow())continue;
				ResetLogicFactory.getResetLogic(player, cfg).reset(player, cfg);
				infos.addNpcGoodInfos(ShopMsgHelper.getGoodsInfoMsg(player, cfg));
			}
		}
		return infos;
	}
}	
