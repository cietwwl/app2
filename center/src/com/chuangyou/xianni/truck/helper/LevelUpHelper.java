package com.chuangyou.xianni.truck.helper;

import com.chuangyou.common.protobuf.pb.truck.RespTruckResultProto.RespTruckResult;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckLevelConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.truck.TruckTempMgr;
 
public class LevelUpHelper {
	
	/**
	 * 升级
	 * @param type
	 * @param addExp
	 */
	public static void levelUp(GamePlayer player, int type, int addExp, int state)
	{
		TruckInfo truckInfo = null;
		switch (type) {
		case TruckInfo.PERSONAL_TRUCK:
			truckInfo = player.getTruckInventory().getMyTruckInfo();
			break;
		case TruckInfo.TRUCKER:
			truckInfo = player.getTruckInventory().getTruckerInfo();
			break;
		case TruckInfo.GUILD_TRUCK:
			truckInfo = player.getTruckInventory().getGuildTruckInfo();
			break;
		}
		updateExp(truckInfo, addExp);
		truckInfo.setOp(Option.Update);
		RespTruckResult.Builder resultBuilder = RespTruckResult.newBuilder();
		resultBuilder.setType(type);
		resultBuilder.setState(state);
		resultBuilder.setExp(truckInfo.getExp());
		resultBuilder.setLevel(truckInfo.getLevel());
		resultBuilder.setSkillPoint(truckInfo.getSkillPoint());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_LVLUP, resultBuilder);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 更新经验等级
	 */
	private static void updateExp(TruckInfo truckInfo, int addExp)
	{
		TruckLevelConfig curTruckLevelConfig = TruckTempMgr.getPersonalTruckLevelConfig().get(truckInfo.getLevel());
		TruckLevelConfig nextTruckLevelConfig = TruckTempMgr.getPersonalTruckLevelConfig().get(truckInfo.getLevel() + 1);
		if(truckInfo.getExp() + addExp > curTruckLevelConfig.getNeedExp())
		{
			if(nextTruckLevelConfig == null)	//满级
			{
				truckInfo.setExp(curTruckLevelConfig.getNeedExp());
			}
			else
			{
				addExp -= curTruckLevelConfig.getNeedExp() - truckInfo.getExp();	//扣除此次升级的经验
				truckInfo.setLevel(nextTruckLevelConfig.getLevel());									//更新等级
				truckInfo.setSkillPoint(truckInfo.getSkillPoint() + nextTruckLevelConfig.getPoints());	//更新技能点
				if(addExp > nextTruckLevelConfig.getNeedExp())	//可以继续升级
				{
					truckInfo.setExp(0);			//当前经验清0
					updateExp(truckInfo, addExp);
				}
				else
				{
					truckInfo.setExp(addExp);			//变更经验
				}
			}
		}
		else
		{
			truckInfo.setExp(addExp);			//变更经验
		}
	}
}
