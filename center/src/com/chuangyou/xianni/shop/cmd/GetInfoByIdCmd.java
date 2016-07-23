package com.chuangyou.xianni.shop.cmd;

import com.chuangyou.common.protobuf.pb.shop.GetInfoByIdReqProto.GetInfoByIdReqMsg;
import com.chuangyou.common.protobuf.pb.shop.GetInfoByIdRespProto.GetInfoByIdRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopMsgHelper;
import com.chuangyou.xianni.shop.logic.ResetLogicFactory;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_GET_INFO_BYID,desc="获取单个商品信息")
public class GetInfoByIdCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetInfoByIdReqMsg req = GetInfoByIdReqMsg.parseFrom(packet.getBytes());
		int privateId = req.getPrivateId();
		ShopCfg cfg = ShopTemplateMgr.getShopCfg(privateId);
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_BUYGOODS,"查无此商品");
			return;
		}
		//重置一下数据
		ResetLogicFactory.getResetLogic(player, cfg).reset(player, cfg);
		
		GetInfoByIdRespMsg.Builder resp = GetInfoByIdRespMsg.newBuilder();
		resp.setNpcId(cfg.getNpcid());
		resp.setShopId(cfg.getShopid());
		resp.setInfo(ShopMsgHelper.getGoodsInfoMsg(player, cfg));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_INFO_BYID, resp);
		player.sendPbMessage(pkg);
		
	}

}
