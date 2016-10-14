package com.chuangyou.xianni.truck.cmd;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.helper.RobAndProtTruckHelper;
import com.chuangyou.xianni.truck.helper.RobHelper;
import com.chuangyou.xianni.truck.helper.TruckActionRespHelper;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.ExcludePetSelector;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_ACTION, desc = "镖车Action")
public class TruckReqActionCmd extends AbstractCommand {

	private Truck truck;

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqTruckAction action = ReqTruckAction.parseFrom(packet.getBytes());
		truck = TruckMgr.getTruck(action.getTargetTruckID());
		switch (action.getAction()) {
			case TruckActionRespHelper.ACTION_START_RUN:
				if(truck == null) return;//未发现镖车
				if(truck.getTruckState() != Truck.TRUCK_STATE_WAIT) return;
				truck.startRun(truck.getTargetCheckPoint()+1);
				TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_START_RUN, truck);
				break;
			case TruckActionRespHelper.ACTION_START_ROB:
				if(truck == null)  return; //未发现镖车
				RobAndProtTruckHelper.startRob(army, truck);
				break;
			case TruckActionRespHelper.ACTION_CANCEL_ROB:
				if(truck == null)  return; //未发现镖车
				RobAndProtTruckHelper.cancelRob(army, truck);
				break;
			case TruckActionRespHelper.ACTION_CANCEL_PROT:
				if(truck == null)  return; //未发现镖车
				RobAndProtTruckHelper.canelProt(army, truck);
				break;
			case TruckActionRespHelper.ACTION_ROB_SUC:
				if(truck == null)  return; //未发现镖车
				RobHelper.rob(army, truck);
				break;
			case TruckActionRespHelper.ACTION_REQ_MYTRUCK:
				//查询自己镖车的状态
				TruckActionRespHelper.syncTruckStatu(army);
				if(TruckMgr.getResultData(army.getPlayerId()) != null)
				{
					PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_REQTRUCKCOMPLETE, TruckMgr.getResultData(army.getPlayerId()).getResultBuilder());
					army.sendPbMessage(pkg);
					TruckMgr.removeResultData(army.getPlayerId());
				}
				break;
			case TruckActionRespHelper.ACTION_REQ_ALLTRUCK:
				TruckActionRespHelper.sendAllTruck(army);
				break;
		}
	}

	
	
}
