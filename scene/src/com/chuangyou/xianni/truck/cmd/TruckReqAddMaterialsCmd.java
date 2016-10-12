package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqChangeMatProto.InnerReqChangeMat;
import com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_ADDMATERIAL, desc = "添加物质")
public class TruckReqAddMaterialsCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqAddMaterials addMat = ReqAddMaterials.parseFrom(packet.getBytes());
		Truck truck = (Truck) army.getPlayer().getField().getLiving(addMat.getTruckid());
		if(truck == null)
		{
			Log.error(army.getPlayerId() + "：找不到镖车");
			return;
		}
		if(addMat.getAddtype() == 1)	//灵石添加
		{
			int remain = truck.getTargetCheckPointMatRemain();
			if(addMat.getCount() > remain)	
			{
				Log.error("需求购买的运镖物质数量大于以存在的数量");
				return;
			}
			truck.cutCheckPointMatRemain(truck.getTargetCheckPoint(), addMat.getCount());
		}
		InnerReqChangeMat.Builder changeMat = InnerReqChangeMat.newBuilder();
		changeMat.setTruckId(truck.getId());
		changeMat.setAddtype(addMat.getAddtype());
		changeMat.setCount(addMat.getCount());
		changeMat.setPrice(truck.getTargetCheckPointMatPrice());
		changeMat.setItemTypeId(addMat.getItemTypeId());
		changeMat.setMapId(truck.getField().id);		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_CHANGEMAT, changeMat);
		army.sendPbMessage(pkg);
	}

}
