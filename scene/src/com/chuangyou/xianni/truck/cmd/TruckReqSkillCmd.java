package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum;
import com.chuangyou.common.protobuf.pb.truck.InnerRespTruckFuncConsumProto.InnerRespTruckFuncConsum;
import com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.truck.helper.FunctionHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_SKILL, desc = "使用镖师技能")
public class TruckReqSkillCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqTruckSkillAction msg = ReqTruckSkillAction.parseFrom(packet.getBytes());
		Truck truck = TruckMgr.getTruck(msg.getTruckId());
		if(truck == null) return;
		int freeCount = truck.getTruckSkillCount().get(msg.getSkillId());
		if(freeCount > 0)
		{
			FunctionHelper.skillHelper(army, truck, msg.getSkillId(), FunctionHelper.STATE_SUC);
		}
		else
		{
			//扣除物品
			TruckFun fun = TruckTempMgr.getTruckFuncs().get(msg.getSkillId());
			TruckSkillConfig skillconfig = TruckTempMgr.getAllSkillConfig().get(fun.getSkillBase());
			if(skillconfig == null) 
			{
				Log.error("skillId = " + msg.getSkillId() + " 找不到配置");
				FunctionHelper.skillHelper(army, truck, msg.getSkillId(), FunctionHelper.STATE_FAIL);
				return;
			}
			ConsumSystemConfig consumConfig = StateTemplateMgr.getConsums().get(skillconfig.getConsump());
			InnerReqTruckFuncConsum.Builder builder = InnerReqTruckFuncConsum.newBuilder();
			builder.setTruckid(truck.getId());
			builder.setItemtype(consumConfig.getExpendItem());
			builder.setItemcount(consumConfig.getExpendNum());
			builder.setSkillid(msg.getSkillId());
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_TRUCK_FUNCCONSUM, builder);
			army.sendPbMessage(pkg);
		}
	}

}
