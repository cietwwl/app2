package com.chuangyou.xianni.npcShop.cmd;

import com.chuangyou.common.protobuf.pb.npcShop.NpcShopBuyGoodReqProto.NpcShopBuyGoodReqMsg;
import com.chuangyou.common.protobuf.pb.npcShop.NpcShopBuyGoodRespProto.NpcShopBuyGoodRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.npcShop.NpcShopCfg;
import com.chuangyou.xianni.npcShop.manager.NpcShopServerManager;
import com.chuangyou.xianni.npcShop.template.NpcShopTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_BUYGOODS,desc="购买NPC商店物品")
public class NpcShopBuyGoodCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		NpcShopBuyGoodReqMsg req = NpcShopBuyGoodReqMsg.parseFrom(packet.getBytes());
		short privateId = (short)req.getPrivateId();
		NpcShopCfg cfg = NpcShopTemplateMgr.getNpcShopCfg(privateId);
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_BUYGOODS);
			return;
		}
		
		if(!NpcShopServerManager.buy(privateId,(short) req.getCount(), player)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.NPC_SHOP_FAIL, Protocol.C_REQ_BUYGOODS);
			return;
		}
		
		NpcShopBuyGoodRespMsg.Builder resp = NpcShopBuyGoodRespMsg.newBuilder();
		resp.setPrivateId(req.getPrivateId());
		resp.setRoleProcured(player.getNpcShopInventory().get(privateId).getBuyNum());
		resp.setServerProcured(NpcShopServerManager.get(privateId).getBuyNum());
		if(cfg.getAmount()==0){  //全服数据无限制
			resp.setServerProcured(-1);
		}
		if(cfg.getLimitBuynum() == 0){ //单个人购买无限制
			resp.setRoleProcured(-1);
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_BUYGOODS, resp);
		player.sendPbMessage(pkg);
		
	}

}
