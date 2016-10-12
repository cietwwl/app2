package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqAddMatToBagProto.InnerReqAddMatToBag;
import com.chuangyou.common.protobuf.pb.truck.ReqPickMatProto.ReqPickMat;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Material;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_PICKMAT, desc = "采集物资")
public class TruckReqPickMatCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqPickMat matMsg = ReqPickMat.parseFrom(packet.getBytes());
		Material mat = (Material) army.getPlayer().getField().getLiving(matMsg.getMatId());
		if(mat == null) return;
		int matCout = mat.getProperty(EnumAttr.METAL.getValue());
		InnerReqAddMatToBag.Builder addMatBuilder = InnerReqAddMatToBag.newBuilder();
		addMatBuilder.setCount(matCout);
		addMatBuilder.setItemtype(mat.getProperty(EnumAttr.WOOD.getValue()));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_ADD_MAT_BAG, addMatBuilder);
		army.sendPbMessage(pkg);
		army.getPlayer().getField().leaveField(mat);
		mat.destory();
		
	}

}
