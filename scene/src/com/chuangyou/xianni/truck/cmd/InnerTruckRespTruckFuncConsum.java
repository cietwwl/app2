package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerRespTruckFuncConsumProto.InnerRespTruckFuncConsum;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.helper.FunctionHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_RESP_TRUCK_FUNCCONSUM, desc = "技能消耗")
public class InnerTruckRespTruckFuncConsum extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		InnerRespTruckFuncConsum msg = InnerRespTruckFuncConsum.parseFrom(packet.getBytes());
		Truck truck = TruckMgr.getTruck(msg.getTruckid());
		if(truck == null) return;
		FunctionHelper.skillHelper(army, truck, msg.getSkillid(), msg.getState());
	}

}
