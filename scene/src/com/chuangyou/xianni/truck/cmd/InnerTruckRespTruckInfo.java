package com.chuangyou.xianni.truck.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.truck.InnerReqInviteGuildProtProto.InnerReqInviteGuildProt;
import com.chuangyou.common.protobuf.pb.truck.InnerRespTruckInfoProto.InnerRespTruckInfo;
import com.chuangyou.common.protobuf.pb.truck.TruckDataProto.TruckData;
import com.chuangyou.common.protobuf.pb.truck.TruckSkillProto.TruckSkill;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.script.IScript;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.touchPoint.script.ITouchPointTrigger;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.truck.helper.CheckPointRespHelper;
import com.chuangyou.xianni.truck.helper.FunctionHelper;
import com.chuangyou.xianni.truck.helper.TrcukCheckHelper;
import com.chuangyou.xianni.truck.helper.TruckBillHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.SimplePlayerInfo;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code = Protocol.S_RESP_TRUCK_INFO, desc = "镖车数据")
public class InnerTruckRespTruckInfo extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		if(TruckTempMgr.getCheckPoints().size() <= 0)
		{
			System.err.println("Truck_CheckPoint静态表无数据");
			return;
		}
		InnerRespTruckInfo truckInfo = InnerRespTruckInfo.parseFrom(packet.getBytes());
//		if(truckInfo.getCanDo() == 0)	//不能运镖，可能时间过期或者次数不够
//		{
//			CheckPointRespHelper.respCheckPointStatus(army, CheckPointRespHelper.CHECKPOINT_CANTCREATE, 0);
//			return;
//		}
		Player owner = WorldMgr.getArmy(army.getPlayerId()).getPlayer();
		Map<Integer, List<TruckSkillInfo>> skills = getSkillInfos(	truckInfo.getTruckData().getSkillsList(),
				truckInfo.getTruckerData().getSkillsList(),
				truckInfo.getTruckerData().getGiftsList());
		Truck truck = new Truck(owner.getId(), IDMakerHelper.nextID(), truckInfo.getTruckType());
		owner.getTruckHelper().setRelatedTruck(truck.getId());
		truck.setSkills(skills);
		truck.createCheckPoint();
		//技能免费试用次数
		Map<Integer, Integer> truckSkillCount = new HashMap<Integer, Integer>();
		for(TruckFun fun : TruckTempMgr.getTruckFuncs().values())
		{
			truckSkillCount.put(fun.getId(), fun.getSkillCountBase() + TruckBillHelper.getFuncFreeCount(truck, fun.getId()));
		}
		truck.setTruckSkillCount(truckSkillCount);
		
		SimplePlayerInfo simplePlayerInfo = new SimplePlayerInfo();
		simplePlayerInfo.setLevel(truckInfo.getLevel());		//等级
		simplePlayerInfo.setPlayerId(army.getPlayerId());
		simplePlayerInfo.setNickName(owner.getSimpleInfo().getNickName());
		simplePlayerInfo.setGuildId(owner.getSimpleInfo().getGuildId());
		truck.setSimpleInfo(simplePlayerInfo);
		
		int maxWeight = TruckBillHelper.getMaxWeight(truck);	//载重
		int intMat = TruckBillHelper.getInitMat(truck);			//物资
		int baseSpeed = TruckBillHelper.getBaseSpeed(truck);	//基础速度
		int durable = TruckBillHelper.getDurable(truck);	//基础速度
		truck.setProperty(EnumAttr.METAL, intMat);			//物资
		truck.setProperty(EnumAttr.WOOD, durable);			//抗劫镖值，劫镖血量
		truck.setProperty(EnumAttr.WATER, maxWeight);		//载重
		truck.setProperty(EnumAttr.FIRE, intMat);			//初始物资
		truck.setProperty(EnumAttr.SPEED, baseSpeed * 100);		//基础速度
		
		truck.setProperty(EnumAttr.PK_VAL, 0);															//劫镖状态
		truck.setNextCheckPoint(TruckTempMgr.getFristCheckPoint().getId() + 1);
		truck.setPostion(TruckTempMgr.getFristCheckPoint().getPoint());
		truck.startRun(TruckTempMgr.getFristCheckPoint().getId()+1);
		simplePlayerInfo.setExp(truck.getTargetCheckPoint());			//当前目标点
		//镖头设置为运镖状态
		army.getPlayer().getTruckHelper().setTruckTimer(true);
		army.getPlayer().getField().enterField(truck);
		//更新技能免费次数
		FunctionHelper.funcUpdate(army, FunctionHelper.STATE_UPDATE, truckSkillCount);
		//帮派镖车招募成员护镖
		if(truck.getTrucktype() == Truck.TRUCK_G)
		{
			InnerReqInviteGuildProt.Builder inviteGuildsBuilder = InnerReqInviteGuildProt.newBuilder();
			inviteGuildsBuilder.setTruckId(truck.getId());
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_GUILD_PROT_INVITE, inviteGuildsBuilder);
			army.sendPbMessage(pkg);
		}
	}

	private Map<Integer, List<TruckSkillInfo>> getSkillInfos(List<TruckSkill> skills, List<TruckSkill> truckSkills, List<TruckSkill> gifts)
	{
		Map<Integer, List<TruckSkillInfo>> ret = new HashMap<Integer, List<TruckSkillInfo>>();
		List<List<TruckSkill>> allSkills = new ArrayList<List<TruckSkill>>();
		allSkills.add(skills);
		allSkills.add(truckSkills);
		allSkills.add(gifts);
		for(int i = 0; i<allSkills.size(); i++)
		{
			for(int j = 0; j<allSkills.get(i).size(); j++)
			{
				TruckSkill skill = allSkills.get(i).get(j);
				TruckSkillInfo skillInfo = new TruckSkillInfo();
				skillInfo.setLevel(skill.getLevel());
				skillInfo.setSkillId(skill.getId());
				skillInfo.setSkillType(skill.getType());
				int valueType = TruckTempMgr.getAllSkillConfig().get(skill.getId()).getValueType();
				if(valueType == 0) continue;
				if(!ret.containsKey(valueType))
					ret.put(valueType, new ArrayList<TruckSkillInfo>());
				ret.get(valueType).add(skillInfo);
			}
		}
		return ret;
	}
}
