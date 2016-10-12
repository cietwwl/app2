package com.chuangyou.xianni.truck.helper;

import com.chuangyou.common.protobuf.pb.truck.RespTruckProtectProto.RespTruckProtect;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.cmd.TruckReqProtectActionCmd;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 运镖/劫镖
 * @author wkghost
 *
 */
public class RobAndProtTruckHelper {

	/**
	 * 劫镖
	 * @param army
	 * @param truck
	 */
	public static void startRob(ArmyProxy army, Truck truck)
	{
		army.getPlayer().getTruckHelper().setTruckTimer(true);
		army.getPlayer().getTruckHelper().setRelatedTruck(truck.getId());
		truck.addRobber(army.getPlayer().getId());//向镖车添加劫镖者
		TruckRelationshipMgr.setRelation(army.getPlayer().getId(), truck.getId());
		TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_START_ROB, truck);
		//同步镖车信息
		TruckActionRespHelper.syncTruckStatu(army);
		//同步附近玩家
		TruckActionRespHelper.notifyNearsAttSnap(army.getPlayer());
		//更新状态
		TruckActionRespHelper.updateTruckState(army, TruckActionRespHelper.STATE_ROBING, truck);
	}
	
	/**
	 * 取消劫镖
	 * @param army
	 * @param truck
	 */
	public static void cancelRob(ArmyProxy army, Truck truck)
	{
		army.getPlayer().getTruckHelper().setTruckTimer(false);
		army.getPlayer().getTruckHelper().setRelatedTruck(0);
		truck.removeRobber(army.getPlayer().getId());//移除镖车的劫镖者
		TruckRelationshipMgr.removeRelation(army.getPlayer().getId());
		TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_CANCEL_ROB, truck);
		TruckActionRespHelper.notifyNearsAttSnap(army.getPlayer());
		//更新状态
		TruckActionRespHelper.updateTruckState(army, TruckActionRespHelper.STATE_NONE, truck);
	}
	
	/**
	 * 
	 * @param army
	 * @param truck
	 */
	public static void startProt(ArmyProxy army, Truck truck)
	{
		
		//向保护者发送可以开始护镖
		RespTruckProtect.Builder resp = RespTruckProtect.newBuilder();		
		resp.setTruckID(truck.getId());
		resp.setProtectorId(truck.getArmyId());
		resp.setTrucktype(truck.getTrucktype());
		resp.setAction(TruckReqProtectActionCmd.RESP_AGREE);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_PROTECT_ACTION, resp);
		army.sendPbMessage(pkg);
		//切换护镖/运镖状态
		Player protector = army.getPlayer();
		protector.getTruckHelper().setRelatedTruck(truck.getId());
		TruckRelationshipMgr.setRelation(protector.getId(), truck.getId());//(action.getTruckId());
		truck.addProtector(protector.getId());
		TruckActionRespHelper.notifyNearsAttSnap(army.getPlayer());
		//同步镖车信息
		TruckActionRespHelper.syncTruckStatu(army);
		//更新状态
		TruckActionRespHelper.updateTruckState(army, TruckActionRespHelper.STATE_PROTING, truck);
	}
	
	/**
	 * 取消护镖
	 * @param army
	 * @param truck
	 */
	public static void canelProt(ArmyProxy army, Truck truck)
	{
		army.getPlayer().getTruckHelper().setTruckTimer(false);
		army.getPlayer().getTruckHelper().setRelatedTruck(0);
		truck.removeProtector(army.getPlayer().getId());//移除镖车的劫镖者
		TruckRelationshipMgr.removeRelation(army.getPlayer().getId());
		TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_CANCEL_PROT, truck);
		TruckActionRespHelper.notifyNearsAttSnap(army.getPlayer());
		//更新状态
		TruckActionRespHelper.updateTruckState(army, TruckActionRespHelper.STATE_NONE, truck);
	}
}
