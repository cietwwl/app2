package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.truck.TruckCheckPointConfig;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.truck.helper.CheckPointRespHelper;
import com.chuangyou.xianni.truck.helper.TrcukCheckHelper;
import com.chuangyou.xianni.truck.helper.TruckCompleteHelper;
import com.chuangyou.xianni.warfield.helper.TransportHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_ARRIALCHECKPOINT, desc = "到达检查点")
public class TruckReqArrialCheckPointCmd extends AbstractCommand {
	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqArrialCheckPoint arrialCheckPoint = ReqArrialCheckPoint.parseFrom(packet.getBytes());
		long truckId = arrialCheckPoint.getTruckID();
		Truck truck = TruckMgr.getTruck(truckId);//(Truck) army.getPlayer().getField().getLiving(truckId);
		if(truck == null) return;
		if(truck.getTruckState() != Truck.TRUCK_STATE_RUN) return;
		//检查位置
		if(!TrcukCheckHelper.checkPoint(truck.getPostion(), arrialCheckPoint.getCurCheckPoint()))
		{
			Log.error("镖车位置与检查点不够"+TrcukCheckHelper.THRESHOLD+"米");
			return;
		}
		if(truck.getTargetCheckPoint() == TruckTempMgr.getLastCheckPoint().getId())
		{
			TruckCompleteHelper.onComplete(army, truck, TruckCompleteHelper.STATE_SUC);
		}
		else
		{
			if(arrialCheckPoint.getCurCheckPoint() != truck.getTargetCheckPoint())
			{
				CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CANTADDMAT, 0, 0);
				return;
			}
			truck.arrialCheckPoint();
			TruckCheckPointConfig checkPointInfo = TruckTempMgr.getCheckPoints().get(truck.getTargetCheckPoint());
			if(checkPointInfo.getPointType() == TruckTempMgr.TRANSPORT)
			{
				//切换下一个路点
				truck.startRun(truck.getTargetCheckPoint()+1);
				// 转场
				TransportHelper.truckTransport(truck, checkPointInfo.getNextPoint(), checkPointInfo.getNextScene());
				TransportHelper.transport(army, checkPointInfo.getNextPoint(), checkPointInfo.getNextScene());
			}
			else if(checkPointInfo.getPointType() == TruckTempMgr.MID)
			{
				CheckPointRespHelper.syncCheckPointMaterials(army, truck);
			}
		}
	}
	
	
	
}
