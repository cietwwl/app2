package com.chuangyou.xianni.truck.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckInfoProto.InnerReqTruckInfo;
import com.chuangyou.common.protobuf.pb.truck.InnerRespTruckInfoProto.InnerRespTruckInfo;
import com.chuangyou.common.protobuf.pb.truck.TruckDataProto.TruckData;
import com.chuangyou.common.protobuf.pb.truck.TruckSkillProto.TruckSkill;
import com.chuangyou.xianni.activity.ActivityType;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.activity.ActivityInfo;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.script.IScript;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckInventory;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.truck.helper.TruckDataHelper;

@Cmd(code = Protocol.C_TRUCK_REQINFO, desc = "查询护镖信息")
public class ReqTruckInfoCmd extends AbstractCommand {

//	/** 降低开启运镖灵石消耗 */
//	private static final int CreateTruckDiscount = 30601;
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqTruckInfo req = InnerReqTruckInfo.parseFrom(packet.getBytes());
		boolean isCan = true;
		//TODO 获取能否运镖
//		if(req.getTruckType() == 1)	//个人运镖
//		{
//			isCan = player.getActivityInventory().isCanStart(ActivityType.PERSON_DART);//.getActivityInfo(ActivityType.PERSON_DART);
//		}
//		else						//帮派运镖
//		{
//			isCan = player.getActivityInventory().isCanStart(ActivityType.UNITY_DART);
//		}
		InnerRespTruckInfo.Builder info = InnerRespTruckInfo.newBuilder();
		info.setPlayerId(req.getPlayerId());
		TruckInfo myTruckInfo = null;
		if(req.getTruckType() == TruckInventory.TYPE_P)
		{
			myTruckInfo = player.getTruckInventory().getMyTruckInfo();
		}
		else//帮派镖车
		{
			myTruckInfo = player.getTruckInventory().getGuildTruckInfo();
		}
		//镖车
		TruckData.Builder truckDataBuilder = TruckDataHelper.getTruckData(myTruckInfo);
		//镖师
		TruckData.Builder truckerDataBuilder = TruckDataHelper.getTruckData(player.getTruckInventory().getTruckerInfo());
		for(TruckSkillInfo skillInfo : player.getTruckInventory().getSkills().values())
		{
			TruckSkill.Builder skillBuilder = TruckSkill.newBuilder();
			skillBuilder.setId(skillInfo.getSkillId());
			skillBuilder.setLevel(skillInfo.getLevel());
			skillBuilder.setType(skillInfo.getSkillType());
			if(skillInfo.getType() == TruckSkillInfo.SKILL_TRUCK)
			{
				truckDataBuilder.addSkills(skillBuilder);
			}
			else if(skillInfo.getType() == TruckSkillInfo.SKILL_TRUCKER)
			{
				truckerDataBuilder.addSkills(skillBuilder);
			}
			else if(skillInfo.getType() == TruckSkillInfo.SKILL_GIFT)
			{
				truckerDataBuilder.addGifts(skillBuilder);
			}
		}
		info.setTruckData(truckDataBuilder);
		info.setTruckerData(truckerDataBuilder);
		info.setTruckType(req.getTruckType());
		info.setLevel(myTruckInfo.getLevel());
		if(req.getPrice() > player.getBasePlayer().getPlayerInfo().getMoney())	//金币不够
		{
			isCan = false;
		}
		info.setCanDo(isCan?1:0);
		//扣除灵石
		if(isCan)	
		{
			player.getBasePlayer().consumeMoney(req.getPrice(),  ItemRemoveType.TRUCK_MAT);
			//TODO 更新掩码
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_RESP_TRUCK_INFO, info);
		player.sendPbMessage(pkg);
	}

}
