package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerRespTruckCreatePriceProto.InnerRespTruckCreatePrice;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.helper.CheckPointRespHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_RESP_TRUCK_CREATEPRICE, desc = "回应创建镖车的价钱")
public class InnerTruckRespCreatePriceCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		InnerRespTruckCreatePrice msg = InnerRespTruckCreatePrice.parseFrom(packet.getBytes());
		CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CREATE, 0, msg.getPrice());
	}

}
