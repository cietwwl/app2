package com.chuangyou.xianni.truck.helper;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class TruckAttChgHelper {

	/** 载重 */
	public static final int ATT_WEIGHT = 1;
	/** 物资 */
	public static final int ATT_MAT = 2;
	/** 劫镖进度 */
	public static final int ATT_ROBPROG = 3;
	
	/**
	 * 更新属性
	 */
	public static void updateAtt(Truck truck, EnumAttr att, int value)
	{
		if(truck == null) return;
		int type = enumAttTrans(att);
		if(type == 0) return;
		ArmyProxy army = WorldMgr.getArmy(truck.getArmyId());
		if(army != null)
		{
			syncToPlayer(army, truck.getId(), type, value);
		}
		sync(truck.getProtectors(), truck.getId(), type, value);
		sync(truck.getRobbers(), truck.getId(), type, value);
	}
	
	/**
	 * 
	 * @param player
	 * @param id
	 * @param type
	 * @param value
	 */
	private static void sync(Set<Long> player, long id, int type, int value)
	{
		for(Long playerId : player)
		{
			ArmyProxy ap = WorldMgr.getArmy(playerId);
			if(ap == null) continue;
			syncToPlayer(ap, id, type, value);
		}
	}
	
	/**]
	 * 
	 * @param army
	 * @param id
	 * @param type
	 * @param value
	 */
	private static void syncToPlayer(ArmyProxy army, long id, int type, int value)
	{
		RespTruckAttChg.Builder b = RespTruckAttChg.newBuilder();
		b.setTruckID(id);
		b.setType(type);
		b.setValue(value);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_ATTCHG, b);
		army.sendPbMessage(pkg);
	}
	
	/**
	 * 属性转换
	 */
	private static int enumAttTrans(EnumAttr att)
	{
		int ret = 0;
		switch (att) {
		case METAL:
			ret = ATT_MAT;
			break;
		case WATER:
			ret = ATT_WEIGHT;
			break;
		case PK_VAL:
			ret = ATT_ROBPROG;
			break;
		default:
			ret = 0;
			break;
		}
		return ret;
	}
}
