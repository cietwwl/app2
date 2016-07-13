package com.chuangyou.xianni.npcShop.cmd;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.npcShop.GetInfoByIdReqProto.GetInfoByIdReqMsg;
import com.chuangyou.common.protobuf.pb.npcShop.GetInfoByIdRespProto.GetInfoByIdRespMsg;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.npcShop.NpcShopCfg;
import com.chuangyou.xianni.entity.npcShop.NpcShopUserCache;
import com.chuangyou.xianni.npcShop.manager.NpcShopServerManager;
import com.chuangyou.xianni.npcShop.template.NpcShopTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_GET_INFO_BYID,desc="获取单个NPC商品信息")
public class GetInfoByIdCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetInfoByIdReqMsg req = GetInfoByIdReqMsg.parseFrom(packet.getBytes());
		int privateId = req.getPrivateId();
		NpcShopCfg cfg = NpcShopTemplateMgr.getNpcShopCfg((short)privateId);
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_BUYGOODS);
			return;
		}
		
		GetInfoByIdRespMsg.Builder resp = GetInfoByIdRespMsg.newBuilder();
		resp.setPrivateId(privateId);
		
		if(cfg.isExpired()){
			resp.setResetLast(-2);
		}else{
			if(cfg.getResetTime()>0){
				//是否需要重置
				long mm = new Date().getTime() - TimeUtil.getDateByString(cfg.getStartTime(),cfg.getTimeType()).getTime();
				long minute = mm/(1000*60);
				long time = minute/cfg.getResetTime();
				NpcShopUserCache userCache = player.getNpcShopInventory().get(cfg.getId());
				if(userCache.getResetTime()<time){
					userCache.setBuyNum((short)0);
					userCache.setResetTime((short)time);
					userCache.setOp(Option.Update);
				}
				long temp = TimeUtil.getDateByString(cfg.getStartTime(),cfg.getTimeType()).getTime()+(time+1)*cfg.getResetTime()*60*1000;
				resp.setResetLast(temp);
			}else{
				resp.setResetLast(-1);
			}
			resp.setRoleProcured(player.getNpcShopInventory().get((short)privateId).getBuyNum());
			resp.setServerProcured(NpcShopServerManager.get((short)privateId).getBuyNum());
			if(cfg.getAmount()==0){  //全服数据无限制
				resp.setServerProcured(-1);
			}
			if(cfg.getLimitBuynum() == 0){ //单个人购买无限制
				resp.setRoleProcured(-1);
			}
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_INFO_BYID, resp);
		player.sendPbMessage(pkg);
		
	}

}
