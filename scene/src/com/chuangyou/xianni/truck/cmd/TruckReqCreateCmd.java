package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckInfoProto.InnerReqTruckInfo;
import com.chuangyou.common.protobuf.pb.truck.ReqCreateTruckProto.ReqCreateTruck;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.helper.CheckPointRespHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_CREATE, desc = "创建镖车")
public class TruckReqCreateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqCreateTruck msg = ReqCreateTruck.parseFrom(packet.getBytes());
		long truckId = TruckRelationshipMgr.getPlayerTruck(army.getPlayerId());
		if(truckId > 0)
		{
			CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CANTCREATE, 0, 0);
			return;
		}
		//请求镖车信息
		InnerReqTruckInfo.Builder builder = InnerReqTruckInfo.newBuilder();
		builder.setPlayerId(army.getPlayerId());
		builder.setTruckType(msg.getTrucktype());
		builder.setPrice(msg.getPrice());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_REQINFO, builder);
		army.sendPbMessage(pkg);
	}

}
