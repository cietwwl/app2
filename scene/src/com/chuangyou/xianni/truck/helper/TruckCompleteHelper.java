package com.chuangyou.xianni.truck.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete;
import com.chuangyou.common.protobuf.pb.truck.InnerReqUpdateMaskProto.InnerReqUpdateMask;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.objects.TruckResultData;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.ExcludePetSelector;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class TruckCompleteHelper {
	/** 取消劫镖 */
	private static final int OP_CANCEL_ROB = 2;
	/** 取消护镖 */
	private static final int OP_CANCEL_PROT = 1;
	
	public static void onComplete(ArmyProxy army, Truck truck, int state)
	{
		truck.arrialGoal();
		//生成镖车结算数据
		if(truck.getTrucktype() == Truck.TRUCK_P)
		{
			personalResult(army, truck, state);
		}
		else if(truck.getTrucktype() == Truck.TRUCK_G)
		{
			guildResult(army, truck, state);
		}
		
		truck.clearProtector();
		for (Long id : truck.getRobbers()) {  
			removeTruckStatu(id, OP_CANCEL_ROB, truck);
		}
		truck.clearRobber();
		TruckMgr.removeTruck(truck.getId());	//在管理器中移除镖车
		truck.getTruckHelper().setTruckTimer(false);
		truck.getTruckHelper().setRelatedTruck(0);
		truck.getField().leaveField(truck, true);
		truck.destory();
	}
	
	/**
	 * 个人运镖结算
	 * @param army
	 * @param truck
	 * @param state
	 */
	private static void personalResult(ArmyProxy army, Truck truck, int state)
	{
		TruckResultData leaderResultData = createResultData(truck, state, TruckResultData.LEADER);
		if(army != null)
		{
			//移除自己的运镖状态
			army.getPlayer().getTruckHelper().setTruckTimer(false);
			army.getPlayer().getTruckHelper().setRelatedTruck(0);
			TruckRelationshipMgr.removeRelation(army.getPlayerId());
			TruckRelationshipMgr.removeOwnership(army.getPlayerId());
			//运镖结束同步附近玩家快照
			notifyNearsAttSnap(army.getPlayer(), truck);
			TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_FIANL_COMPLETE, truck);
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_REQTRUCKCOMPLETE, leaderResultData.getResultBuilder());
			army.sendPbMessage(pkg);
		}
		else
		{
			TruckMgr.addResultData(leaderResultData);
		}
		//个人镖车 - 镖师结算
		for (Long id : truck.getProtectors()) {  
			removeTruckStatu(id, OP_CANCEL_PROT, truck);
			TruckResultData memberResultData = createResultData(truck, state, TruckResultData.MEMBER);
			sendResultData(memberResultData);
		}
	}
	
	/**
	 * 帮派运镖结算
	 * @param army
	 * @param truck
	 * @param state
	 */
	private static void guildResult(ArmyProxy army, Truck truck, int state)
	{
		TruckResultData guildMemberResultData = createResultData(truck, state, TruckResultData.GUILDMEMBER);
		if(army != null)
		{
			//移除自己的运镖状态
			army.getPlayer().getTruckHelper().setTruckTimer(false);
			army.getPlayer().getTruckHelper().setRelatedTruck(0);
			TruckRelationshipMgr.removeRelation(army.getPlayerId());
			TruckRelationshipMgr.removeOwnership(army.getPlayerId());
			//运镖结束同步附近玩家快照
			notifyNearsAttSnap(army.getPlayer(), truck);
			TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_FIANL_COMPLETE, truck);
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_REQTRUCKCOMPLETE, guildMemberResultData.getResultBuilder());
			army.sendPbMessage(pkg);
		}
		else
		{
			TruckMgr.addResultData(guildMemberResultData);
		}
		//帮派镖车 - 镖师结算
		for (Long id : truck.getProtectors()) {  
			removeTruckStatu(id, OP_CANCEL_PROT, truck);
			if(truck.getProtectorTimer().containsKey(id) && 
					   truck.getProtectorTimer().get(id) > SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.AwardNeedTime"))
			{
				TruckResultData memberResultData = createResultData(truck, state, TruckResultData.GUILDMEMBER);
				sendResultData(memberResultData);
			}
		}
	}
	
	/**
	 * 创建镖头结算数据
	 * @return
	 */
	private static TruckResultData createResultData(Truck truck, int state, int truckerType)
	{
		TruckResultData resultData = new TruckResultData();
		resultData.setId(truck.getArmyId());
		resultData.setTrucktype(truck.getTrucktype());
		resultData.setRobbed(truck.isRobbed());
		resultData.setState(state);
		resultData.setLeaveMat(truck.getProperty(EnumAttr.METAL.getValue()));
		resultData.setTruckerType(truckerType);
		return resultData;
	}
	
	/**
	 * 发送结算数据
	 * @param data
	 */
	private static void sendResultData(TruckResultData data)
	{
		ArmyProxy proxy = WorldMgr.getArmy(data.getId());
		if(proxy == null || proxy.getPlayer() == null)
		{
			TruckMgr.addResultData(data);
		}
		else
		{
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_REQTRUCKCOMPLETE, data.getResultBuilder());
			proxy.sendPbMessage(pkg);
		}
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
		else if(type == OP_CANCEL_ROB)			//取消劫镖
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
