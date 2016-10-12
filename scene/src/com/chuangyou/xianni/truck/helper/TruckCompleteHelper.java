package com.chuangyou.xianni.truck.helper;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.ExcludePetSelector;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class TruckCompleteHelper {

	/** 成功完成 */
	public static final int STATE_SUC = 1;
	/** 超时 */
	public static final int STATE_TIMEOUT = 2;
	/** 打爆 */
	public static final int STATE_BREAK = 3;
	
	/** 取消劫镖 */
	private static final int OP_CANCEL_ROB = 2;
	/** 取消护镖 */
	private static final int OP_CANCEL_PROT = 1;
	
	public static void onComplete(ArmyProxy army, Truck truck, int state)
	{
		System.out.println("TruckCompleteHelper -- ");
		truck.arrialGoal();
		//移除自己的运镖状态
		army.getPlayer().getTruckHelper().setTruckTimer(false);
		army.getPlayer().getTruckHelper().setRelatedTruck(0);
		TruckRelationshipMgr.removeRelation(army.getPlayerId());
		TruckRelationshipMgr.removeOwnership(army.getPlayerId());
		notifyNearsAttSnap(army.getPlayer(), truck);
		TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_FIANL_COMPLETE, truck);	//完成运镖回应
		InnerReqTruckComplete.Builder truckCompleteMsg = InnerReqTruckComplete.newBuilder();  
		truckCompleteMsg.setTrucktype(truck.getTrucktype());
		truckCompleteMsg.setRobbed(truck.isRobbed()?1:0);	//被劫镖过
		truckCompleteMsg.setState(state);	//正常完成
		truckCompleteMsg.setMat(truck.getProperty(EnumAttr.METAL.getValue()));
		TruckMgr.removeTruck(truck.getId());	//在管理器中移除镖车
		truck.getTruckHelper().setTruckTimer(false);
		for (Long id : truck.getProtectors()) {  
			if(truck.getTrucktype() == Truck.TRUCK_G)
			{
				//帮派运镖检查护镖时长
				if(truck.getProtectorTimer().containsKey(id) && truck.getProtectorTimer().get(id) > SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.AwardNeedTime"))
					truckCompleteMsg.addProtectors(id);
			}
			else
			{
				truckCompleteMsg.addProtectors(id);
			}
			//truck.removeProtector(id);
			removeTruckStatu(id, OP_CANCEL_PROT, truck);
		}
		truck.clearProtector();
		for (Long id : truck.getRobbers()) {  
			//truck.removeRobber(id);
			removeTruckStatu(id, OP_CANCEL_ROB, truck);
		}
		truck.clearProtector();
		truck.getTruckHelper().setRelatedTruck(0);
		TruckRelationshipMgr.removeRelation(army.getPlayerId());
		TruckRelationshipMgr.removeOwnership(army.getPlayerId());
		army.getPlayer().getField().leaveField(truck, true);
		truck.destory();
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_REQTRUCKCOMPLETE, truckCompleteMsg);
		army.sendPbMessage(pkg);
	}
	
	/**
	 * 移除运镖状态
	 * @param id
	 */
	private static void removeTruckStatu(long id, int type, Truck truck)
	{
		ArmyProxy proxy = WorldMgr.getArmy(id);
		if(proxy == null) return;
		if(proxy.getPlayer() == null) return;
		proxy.getPlayer().getTruckHelper().setTruckTimer(false);
		proxy.getPlayer().getTruckHelper().setRelatedTruck(0);
		TruckRelationshipMgr.removeRelation(id);
		notifyNearsAttSnap(proxy.getPlayer(), truck);
		if(type == OP_CANCEL_PROT)	//取消护镖
		{
			TruckActionRespHelper.truckAction(proxy, TruckActionRespHelper.ACTION_CANCEL_PROT, null);
		}
		else			//取消劫镖
		{
			TruckActionRespHelper.truckAction(proxy, TruckActionRespHelper.ACTION_CANCEL_ROB, null);
		}
	}
	
	/**
	 * 通知附近玩家的快照
	 */
	private static void notifyNearsAttSnap(Player player, Truck truck)
	{
		// 获取目前周围的玩家
		Set<Long> nears = player.getNears(new ExcludePetSelector(player));
		nears.remove(truck.getId());
		// 发布附近玩家的快照
		if (nears.size() > 0) {
			//System.err.println("有玩家进入视野");
			NotifyNearHelper.notifyAttSnap(player, nears);
		}
	}
}
