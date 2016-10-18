package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerRespChangeResultProto.InnerRespChangeResult;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.helper.TruckActionRespHelper;
import com.chuangyou.xianni.truck.helper.TruckAttChgHelper;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;


@Cmd(code = Protocol.S_RESP_TRUCK_MATCHANGED, desc = "物资兑换完成,结算属性")
public class InnerTruckRespMatChangeResultCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		InnerRespChangeResult changed = InnerRespChangeResult.parseFrom(packet.getBytes());
		Field f = FieldMgr.getIns().getField(changed.getMapId());
		if(f == null)
		{
			System.err.println("找不到镖车的场景");
			return;
		}
		Truck truck = (Truck) f.getLiving(changed.getTruckId());
		if(truck == null) return;
		//结算属性
		//物资
		int curMat = truck.getProperty(EnumAttr.METAL.getValue()) + changed.getCount();
		truck.setProperty(EnumAttr.METAL, curMat);
		truck.updateProperty(EnumAttr.METAL, curMat);
		//更新物资到客户端
		TruckAttChgHelper.updateAtt(truck, EnumAttr.METAL, curMat);
//		//速度
//		long speed = TrcukCheckHelper.getTruckSpeed(truck.getProperty(EnumAttr.METAL.getValue()), curMat, truck.getProperty(EnumAttr.EARTH.getValue()));
//		truck.setProperty(EnumAttr.SPEED, speed);
//		truck.updateProperty(EnumAttr.SPEED, speed);
		//同步属性
		TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_ADDMAT_SUC, truck);
	}

}
