package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.ReqCheckPointProto.ReqCheckPoint;
import com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.truck.TruckCheckPointConfig;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.truck.helper.CheckPointRespHelper;
import com.chuangyou.xianni.truck.helper.TruckBillHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_CHECKPOINT, desc = "请求检查点")
public class TruckReqCheckPoint extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqCheckPoint checkpoint = ReqCheckPoint.parseFrom(packet.getBytes());
		int checkpointID = checkpoint.getCheckpointId();
		TruckCheckPointConfig checkPointTmp = TruckTempMgr.getCheckPoints().get(checkpointID);
		long truckId = TruckRelationshipMgr.getPlayerTruck(army.getPlayerId());
		if(checkPointTmp.getPointType() == TruckTempMgr.FRIST)
		{
			if(truckId == 0)	//未发现拥有的镖车
			{
				//1.查看是否存在和自己关联的镖车
				long relationTruckId = TruckRelationshipMgr.getRelationTruck(army.getPlayerId());
				if(relationTruckId == 0)
				{
					//CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CREATE, 0, 0);
					CheckPointRespHelper.reqCreateTruckPrice(army, checkpoint.getTrucktype());
				}
				else
				{
					//1. 不能创建镖车
					CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CANTCREATE, 0, 0);
				}
			}
			else
			{
				//1. 不能创建镖车
				CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CANTCREATE, 0, 0);
			}
		}
		else if(checkPointTmp.getPointType() == TruckTempMgr.MID)
		{
			if(truckId == 0)	//未发现拥有的镖车
			{
				//不是镖车创建点，且未发现镖车，提示要到劫镖处创建镖车
				CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_NOTOWNTRUCK, 0, 0);
				return;
			}
			
			Truck truck = (Truck) army.getPlayer().getField().getLiving(truckId);
			if(truck.getTruckState() == Truck.TRUCK_STATE_WAIT && checkpointID == truck.getTargetCheckPoint())
			{
				//返回应该购买的物质数量
				CheckPointRespHelper.syncCheckPointMaterials(army, truck);
			}
			else
			{
				//未到达检查点，或者已经途径过检查点了
				CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CANTADDMAT, 0, 0);
			}
		}
	}

}
