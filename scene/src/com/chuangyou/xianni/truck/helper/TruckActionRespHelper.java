package com.chuangyou.xianni.truck.helper;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckStateProto.InnerReqTruckState;
import com.chuangyou.common.protobuf.pb.truck.RespAllTruckStatuProto.RespAllTruckStatu;
import com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu;
import com.chuangyou.common.protobuf.pb.truck.RespTruckActionProto.RespTruckAction;
import com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
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

public class TruckActionRespHelper {
	
	/** 开始劫镖 */
	public static final int ACTION_START_ROB = 1;
	/** 劫镖成功 */
	public static final int ACTION_ROB_SUC = 2;
	/** 退出劫镖 */
	public static final int ACTION_CANCEL_ROB = 3;
	/** 请求所有镖车的状态 */
	public static final int ACTION_REQ_ALLTRUCK = 4;
	/** 查询自己的镖车状态 */
	public static final int ACTION_REQ_MYTRUCK = 5;
	/** 起运 */
	public static final int ACTION_START_RUN = 6;
	/** 完成运镖 */
	public static final int ACTION_FIANL_COMPLETE = 7;
	/** 添加物质成功 */
	public static final int ACTION_ADDMAT_SUC = 8;
	/** 退出护镖 */
	public static final int ACTION_CANCEL_PROT = 9;
		
	/** 未任何状态  */
	public static final int STATE_NONE = 0;
	/** 运镖中 */
	public static final int STATE_RUNING = 1;
	/** 护镖中 */
	public static final int STATE_PROTING = 2;
	/** 劫镖中 */
	public static final int STATE_ROBING = 3;
	
	/**
	 * 更新运镖状态到Center
	 */
	public static void updateTruckState(ArmyProxy army, int state, Truck truck)
	{
		InnerReqTruckState.Builder builder = InnerReqTruckState.newBuilder();
		builder.setState(state);
		if(truck == null)
		{
			builder.setTrucktype(0);
		}
		else
		{
			builder.setTrucktype(truck.getTrucktype());
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_UPDATE_TRUCK_STATE, builder);
		army.sendPbMessage(pkg);
	}
	
	public static void truckAction(ArmyProxy army, int action, Truck truck)
	{
		RespTruckAction.Builder builder = RespTruckAction.newBuilder();
		builder.setAction(action);
		if(truck != null)
		{
			builder.setTruckID(truck.getId());
			builder.setNextCheckPoint(truck.getTargetCheckPoint());
		}
		else
		{
			builder.setTruckID(0);
			builder.setNextCheckPoint(0);
		}	
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_ACTION, builder);
		army.sendPbMessage(pkg);
	}
	
	/**
	 * 同步镖车状态
	 * @param army
	 */
	public static void syncTruckStatu(ArmyProxy army)
	{
		RespMyTruckStatu.Builder myTruckStatu = RespMyTruckStatu.newBuilder();
		Truck truck = TruckMgr.getTruck(TruckRelationshipMgr.getRelationTruck(army.getPlayerId()));
		if(truck == null)
		{
			myTruckStatu.setStatu(STATE_NONE);
			updateTruckState(army, STATE_NONE, null);
		}
		else
		{
			if(truck.getArmyId() == army.getPlayerId())
			{
				//更新功能使用次数
				FunctionHelper.funcUpdate(army, FunctionHelper.STATE_UPDATE, truck.getTruckSkillCount());
				myTruckStatu.setStatu(STATE_RUNING);	//运镖中
			}
			else
			{
				if(truck.getRobbers().contains(army.getPlayerId()))
				{
					myTruckStatu.setStatu(STATE_ROBING);	//劫镖中
				}
				else if(truck.getProtectors().contains(army.getPlayerId()))
				{
					myTruckStatu.setStatu(STATE_PROTING);	//护镖中
				}
			}
			myTruckStatu.setId(truck.getId());
			myTruckStatu.setNextCheckPoint(truck.getTargetCheckPoint());
			myTruckStatu.setTruckState(truck.getTruckState());
			myTruckStatu.setStatus(getStatuBuilder(truck));
		}
		updateTruckState(army, myTruckStatu.getStatu(), truck);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_MYSTATUS, myTruckStatu);
		army.sendPbMessage(pkg);
	}
	
	/**
	 * 发送所有镖车状态
	 * @param army
	 */
	public static void sendAllTruck(ArmyProxy army)
	{
		Iterator<Entry<Long, Truck>> it = TruckMgr.getAllTrucks().entrySet().iterator();
		RespAllTruckStatu.Builder allTruckBuilder = RespAllTruckStatu.newBuilder();
		while(it.hasNext())
		{
			Map.Entry<Long, Truck> entry = (Map.Entry<Long, Truck>) it.next();
			Truck t = entry.getValue();
			if(t == null) continue;
			if(t.getField().id != army.getFieldId()) continue;
			if(t.hasHiding()) continue;	//暗标中
			if(t.getArmyId() == army.getPlayerId()) continue;
			allTruckBuilder.addTrucks(getStatuBuilder(t));
		}
		PBMessage allTruckPkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_ALLSTATUS, allTruckBuilder);
		army.sendPbMessage(allTruckPkg);
	}
	
	/**
	 * 镖车状态
	 * @param t
	 * @return
	 */
	public static TruckStatu.Builder getStatuBuilder(Truck t)
	{
		TruckStatu.Builder statuBuilder = TruckStatu.newBuilder();
		statuBuilder.setId(t.getId());
		statuBuilder.setWeight(t.getProperty(EnumAttr.WATER.getValue()));	//载重
		statuBuilder.setLevel(t.getSimpleInfo().getLevel());				//等级
		statuBuilder.setMat(t.getProperty(EnumAttr.METAL.getValue()));		//物资
		
		statuBuilder.setRobProgres(t.getProperty(EnumAttr.PK_VAL.getValue()));	//劫镖值
		statuBuilder.setDurable(t.getProperty(EnumAttr.WOOD.getValue()));		//抗劫镖值
		statuBuilder.setTruckType(t.getTrucktype());
		statuBuilder.setOwnerName(t.getSimpleInfo().getNickName());
		PostionMsg.Builder tPos = PostionMsg.newBuilder();
		tPos.setMapId(t.getField().id);
		tPos.setMapKey(t.getField().getMapKey());
		PBVector3.Builder v3 = Vector3BuilderHelper.build(t.getPostion());
		tPos.setPostion(v3);
		statuBuilder.setPostionMsg(tPos);
		return statuBuilder;
	}
	
	/**
	 * 通知附近玩家的快照
	 */
	public static void notifyNearsAttSnap(Player player)
	{
		// 获取目前周围的玩家
		Set<Long> nears = player.getNears(new ExcludePetSelector(player));
		// 发布附近玩家的快照
		if (nears.size() > 0) {
			//System.err.println("有玩家进入视野");
			NotifyNearHelper.notifyAttSnap(player, nears);
			//NotifyNearHelper.notifyLeaveGrid(player, nears);
		}
	}
}
