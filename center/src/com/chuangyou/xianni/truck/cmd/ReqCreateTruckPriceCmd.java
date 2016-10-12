package com.chuangyou.xianni.truck.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCreatePriceProto.InnerReqTruckCreatePrice;
import com.chuangyou.common.protobuf.pb.truck.InnerRespTruckCreatePriceProto.InnerRespTruckCreatePrice;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckInventory;

@Cmd(code = Protocol.C_TRUCK_CREATEPRICE, desc = "创建镖车价格")
public class ReqCreateTruckPriceCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqTruckCreatePrice msg = InnerReqTruckCreatePrice.parseFrom(packet.getBytes());
		int price = 0;
		if(msg.getTrucktype() == TruckInventory.TYPE_P)
		{
			price = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.Price") *
					SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.Start");
		}
		else//帮派镖车
		{
			price = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.Start") *
					SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.Price");
		}
		List<TruckSkillConfig> skillConfigs = player.getTruckInventory().getSkillConfigByValueType(30601);
		int discount = 0;
		for(int i = 0; i<skillConfigs.size(); i++)
		{
			discount += skillConfigs.get(i).getValuePercent();
		}
		price = (int) (price - discount * 0.0001 * price);
		InnerRespTruckCreatePrice.Builder builder = InnerRespTruckCreatePrice.newBuilder();
		builder.setPrice(price);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_RESP_TRUCK_CREATEPRICE, builder);
		player.sendPbMessage(pkg);
	}

}
