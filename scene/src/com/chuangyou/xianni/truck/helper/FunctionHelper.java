package com.chuangyou.xianni.truck.helper;

import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.truck.RespTruckSkillActionProto.RespTruckSkillAction;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.RoleConstants;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.state.StateTemplateMgr;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

/**
 * 功能次数
 * @author wkghost
 *
 */
public class FunctionHelper {

	/** 招募 */
	public static final int FUN_ZHAOMU = 40100;
	
	/** 激励 */
	public static final int FUN_JILI = 40200;
	
	/** 坚守 */
	public static final int FUN_JIANSHOU = 40300;
	
	/** 捷运 */
	public static final int FUN_JIEYUN = 40400;
	
	/** 暗标 */
	public static final int FUN_ANBIAO = 40500;
	
	/** 使用成功 */
	public static final int STATE_SUC = 1;
	/** 失败 */
	public static final int STATE_FAIL = 2;
	/** 更新 */
	public static final int STATE_UPDATE = 3;
	
	/**
	 * 扣除技能次数
	 * @param truck
	 * @param id
	 */
	public static void cutFuncCount(Truck truck, int id)
	{
		int freeCount = truck.getTruckSkillCount().get(id);
		freeCount--;
		if(freeCount < 0)
			freeCount = 0;
		truck.getTruckSkillCount().put(id, freeCount);
	}
	
	/**
	 * 更新次数
	 * @param army
	 * @param state
	 * @param funsCount
	 */
	public static void funcUpdate(ArmyProxy army, int state, Map<Integer, Integer> funsCount)
	{
		RespTruckSkillAction.Builder actionBuilder = RespTruckSkillAction.newBuilder();
		actionBuilder.setResult(state);
		int[] funcs = new int[funsCount.size()];
		for(Integer key : funsCount.keySet())
		{
			//actionBuilder.addRemain(count);
			int index = 0;
			switch(key)
			{
			case FUN_ZHAOMU:
				index = 0;
				break;
			case FUN_JILI:
				index = 1;
				break;
			case FUN_JIANSHOU:
				index = 2;
				break;
			case FUN_JIEYUN:
				index = 3;
				break;
			case FUN_ANBIAO:
				index = 4;
				break;
			}
			funcs[index] = funsCount.get(key);
		}
		for(int i = 0; i<funcs.length; i++)
		{
			actionBuilder.addRemain(funcs[i]);
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_USESKILL, actionBuilder);
		army.sendPbMessage(pkg);
	}
	
	/**
	 * 使用技能
	 */
	public static void skillHelper(ArmyProxy army, Truck truck, int skillId, int state)
	{
		if(state == STATE_SUC)
		{
			FunctionHelper.cutFuncCount(truck, skillId);
			FunctionHelper.funcUpdate(army, FunctionHelper.STATE_SUC, truck.getTruckSkillCount());
			TruckFun fun = TruckTempMgr.getTruckFuncs().get(skillId);
			//ConsumSystemConfig consumConfig = StateTemplateMgr.getConsums().get(skillConfig.getConsump());
			List<TruckSkillConfig> skillConfigs = TruckBillHelper.getTruckSkillsByValueType(truck, fun.getSkillExt1());
			switch(fun.getId())
			{
			case FUN_JILI:
				for(int i = 0; i<skillConfigs.size(); i++)
				{
					addBuff(army.getPlayer(), army.getPlayer(), skillConfigs.get(i).getBuff());
				}
				break;
			case FUN_JIANSHOU:
				for(int i = 0; i<skillConfigs.size(); i++)
				{
					addBuff(army.getPlayer(), army.getPlayer(), skillConfigs.get(i).getBuff());
				}
				break;
			case FUN_JIEYUN:
				for(int i = 0; i<skillConfigs.size(); i++)
				{
					addBuff(army.getPlayer(), truck, skillConfigs.get(i).getBuff());
				}
				break;
			case FUN_ANBIAO:
				int hideSec = TruckTempMgr.getAllSkillConfig().get(fun.getSkillBase()).getValue();
				for(int i = 0; i<skillConfigs.size(); i++)
				{
					hideSec += skillConfigs.get(i).getValue();
				}
				truck.updateHideTimestramp(System.currentTimeMillis(), hideSec);
				break;
			}
		}
		else
		{
			FunctionHelper.funcUpdate(army, FunctionHelper.STATE_FAIL, truck.getTruckSkillCount());
		}
	}
	
	/**
	 * 添加buff
	 */
	private static void addBuff(Living src, Living target, int buffId)
	{
		SkillBufferTemplateInfo buffInfo = BattleTempMgr.getBufferInfo(buffId);
		if(buffInfo != null)
		{
			Buffer buff = BufferFactory.createBuffer(src, target, buffInfo);
			target.addBuffer(buff);
		}
	}
	
}
