package com.chuangyou.xianni.npcShop.cmd;

import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.npcShop.GetNpcShopInfoReqProto.GetNpcShopInfoReqMsg;
import com.chuangyou.common.protobuf.pb.npcShop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg;
import com.chuangyou.common.protobuf.pb.npcShop.NpcGoodsInfoProto.NpcGoodsInfo;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.base.AbstractCommand;
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

@Cmd(code = Protocol.C_REQ_GETNPCSHOPINFO,desc="请求NPC商店信息")
public class GetNpcShopInfoReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetNpcShopInfoReqMsg req = GetNpcShopInfoReqMsg.parseFrom(packet.getBytes());
		int shopId = req.getShopId();
		List<NpcShopCfg> list = NpcShopTemplateMgr.getShopList(shopId);
		
		GetNpcShopInfoRespMsg.Builder resp = GetNpcShopInfoRespMsg.newBuilder();
		resp.setNpcShopId(shopId);
		for (NpcShopCfg npcShopCfg : list) {
			if(npcShopCfg.isExpired())continue;  //过期开未开售去掉
			NpcGoodsInfo.Builder good = NpcGoodsInfo.newBuilder();
			good.setPrivateId(npcShopCfg.getId());
			NpcShopUserCache userCache = player.getNpcShopInventory().get(npcShopCfg.getId());
			good.setConsumeType(npcShopCfg.getConsumeType());
			good.setItemType(npcShopCfg.getItemType());
			good.setBind(npcShopCfg.getBind());
			good.setMoneyType(npcShopCfg.getMoneyType());
			good.setPrice(npcShopCfg.getPrice());
			good.setAmount(npcShopCfg.getAmount());
			good.setLimitBuynum(npcShopCfg.getLimitBuynum());
			if(StringUtils.isNullOrEmpty(npcShopCfg.getEndTime())){
				good.setEndTime(0);
			}else{
				good.setEndTime(TimeUtil.getDateByString(npcShopCfg.getEndTime(),npcShopCfg.getTimeType()).getTime());
			}
						
			good.setRoleProcured(userCache.getBuyNum());
			good.setServerProcured(NpcShopServerManager.get(npcShopCfg.getId()).getBuyNum());
			
			if(npcShopCfg.getResetTime()>0){
				//是否需要重置
				long mm = new Date().getTime() - TimeUtil.getDateByString(npcShopCfg.getStartTime(),npcShopCfg.getTimeType()).getTime();
				long minute = mm/(1000*60);
				long time = minute/npcShopCfg.getResetTime();
				if(userCache.getResetTime()<time){
					userCache.setBuyNum((short)0);
					userCache.setResetTime((short)time);
					userCache.setOp(Option.Update);
				}
				long temp = TimeUtil.getDateByString(npcShopCfg.getStartTime(),npcShopCfg.getTimeType()).getTime()+(time+1)*npcShopCfg.getResetTime()*60*1000;
				good.setResetLast(temp);
			}else{
				good.setResetLast(-1);
			}
			
			if(npcShopCfg.getAmount()==0){  //全服数据无限制
				good.setServerProcured(-1);
			}
			if(npcShopCfg.getLimitBuynum() == 0){ //单个人购买无限制
				good.setRoleProcured(-1);
			}
			resp.addNpcGoodInfos(good);
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETNPCSHOPINFO, resp);
		player.sendPbMessage(pkg);
		
	}

}
