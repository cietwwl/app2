package com.chuangyou.xianni.truck.helper;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCreatePriceProto.InnerReqTruckCreatePrice;
import com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.world.ArmyProxy;

public class CheckPointRespHelper {
	//检查点状态， 创建镖车
	public static final int CHECKPOINT_CREATE = 1;
	//检查点状态，添加物质
	public static final int CHECKPOINT_ADDMAT = 2;
	//不能创建镖车
	public static final int CHECKPOINT_CANTCREATE = 3;
	//不能添加物质
	public static final int CHECKPOINT_CANTADDMAT = 4;
	//未创建镖车
	public static final int CHECKPOINT_NOTOWNTRUCK = 5;
	
	
	/**
	 * 同步检查点的ID
	 * @param checkpointId
	 * @param truck
	 */
	public static void syncCheckPointMaterials(ArmyProxy army, Truck truck)
	{
		int remain = truck.getTargetCheckPointMatRemain();
		int price = truck.getTargetCheckPointMatPrice();
		respCheckPointStatus(army, CHECKPOINT_ADDMAT, remain, price);
	}
	
	/**
	 * 更新可购买的资源的数量
	 * @param checkpointId
	 * @param truck
	 * @param cut
	 */
	public static void updateCheckPointMaterials(int checkpointId, Truck truck, int cut)
	{
		truck.cutCheckPointMatRemain(checkpointId, cut);
	}
	
	/**
	 * 回应检查点的状态
	 * @param army
	 * @param status
	 * @param remain
	 */
	public static void respCheckPointStatus(ArmyProxy army, int status, int remain, int price)
	{
		RespCheckPoint.Builder respCheckPoint = RespCheckPoint.newBuilder();
		respCheckPoint.setStatu(status);
		respCheckPoint.setRemain(remain);
		respCheckPoint.setPrice(price);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_CHECKPOINT, respCheckPoint);
		army.sendPbMessage(pkg);
	}
	
	/**
	 * 请求创建镖车的价格
	 * @param army
	 * @param trucktype
	 */
	public static void reqCreateTruckPrice(ArmyProxy army, int trucktype)
	{
		InnerReqTruckCreatePrice.Builder builder = InnerReqTruckCreatePrice.newBuilder();
		builder.setTrucktype(trucktype);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_CREATEPRICE, builder);
		army.sendPbMessage(pkg);
	}
}
