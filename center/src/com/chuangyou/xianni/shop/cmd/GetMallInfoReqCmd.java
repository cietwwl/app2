package com.chuangyou.xianni.shop.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.shop.GetMallInfoReqProto.GetMallInfoReqMsg;
import com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg;
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

@Cmd(code=Protocol.C_REQ_MALL_INFO,desc = "请求商城中的数据")
public class GetMallInfoReqCmd extends AbstractCommand {

	private GamePlayer player;
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		this.player = player;
		GetMallInfoReqMsg req = GetMallInfoReqMsg.parseFrom(packet.getBytes());
		int type = req.getRequestType();
		if(type == 1 ){
			doNormalResult();
		}else if(type == 2){
			if(player.getShopInventory().isRefreshData()){
				doNormalResult();
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
	private void doNormalResult(){
		GetMallInfoRespMsg.Builder resp = GetMallInfoRespMsg.newBuilder();
		resp.setResultType(1);
		resp.addInfos(getInfosMsg(ShopCfg.SHOP_NORMAL));
		resp.addInfos(getInfosMsg(ShopCfg.SHOP_FASION));
		resp.addInfos(getInfosMsg(ShopCfg.SHOP_LIMIT_BUY));
		resp.addInfos(getInfosMsg(ShopCfg.SHOP_POINTS));
		resp.addInfos(getInfosMsg(ShopCfg.SHOP_VIP));
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_MALL_INFO,resp);
		player.sendPbMessage(pkg);
		player.getShopInventory().setRefreshData(false);
	}
	
	/**
	 * 获取数据
	 * @param shopId
	 * @return
	 */
	private GetNpcShopInfoRespMsg.Builder getInfosMsg(int shopId){
		GetNpcShopInfoRespMsg.Builder infos= GetNpcShopInfoRespMsg.newBuilder();
		List<ShopCfg> list = ShopTemplateMgr.getShopList(shopId);
		infos.setNpcShopId(shopId);
		for (ShopCfg cfg : list) {
			ResetLogicFactory.getResetLogic(player, cfg).reset(player, cfg);
			infos.addNpcGoodInfos(ShopMsgHelper.getGoodsInfoMsg(player, cfg));
		}
		return infos;
	}

}
