package com.chuangyou.xianni.truck.action;

import java.util.HashSet;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.truck.RespTruckProtTimerProto.RespTruckProtTimer;
import com.chuangyou.common.protobuf.pb.truck.RespTruckProtectProto.RespTruckProtect;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.cmd.TruckReqProtectActionCmd;
import com.chuangyou.xianni.truck.helper.RobAndProtTruckHelper;
import com.chuangyou.xianni.truck.helper.TruckActionRespHelper;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.TransportHelper;
import com.chuangyou.xianni.warfield.helper.selectors.GuildSelectorHelper;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class GuildTruckDelayCount extends DelayAction {

	Truck					truck;
	int						delay;
	
	public GuildTruckDelayCount(Truck queue, int delay) {
		super(queue, delay);
		this.truck = queue;
		this.delay = delay;
	}

	@Override
	public void execute() {
		if(truck.getLivingState() == Living.DISTORY) 
		{
			return;
		}
		Field f = truck.getField();
		if(f == null)
		{
			this.getActionQueue().enDelayQueue(this);
			return;
		}
		Set<Long> nears = truck.getNears(new GuildSelectorHelper(truck));
		Set<Long> leaves = new HashSet<Long>();
//		Living master = f.getLiving(truck.getArmyId());
//		if(master == null)
//		{
//			this.truck.updateProtectorTimer(master.getId(), this.delay);
//			updateProtectorTimer(WorldMgr.getArmy(master.getId()));
//		}
		for(long id : truck.getProtectors())
		{
			if(!nears.contains(id))
			{
				leaves.add(id);	//已经离开了的
			}
		}
		//离开的玩家退出护镖
		for(long id : leaves)
		{
			ArmyProxy army = WorldMgr.getArmy(id);
			if(army == null) continue;
			RobAndProtTruckHelper.canelProt(army, this.truck);
		}
		//新加入的玩家进入护镖, 增加护镖时长
		for(long id : nears)
		{
			ArmyProxy army = WorldMgr.getArmy(id);
			if(army == null) continue;
			if(!this.truck.getProtectors().contains(id))	//新加入的护镖者
			{
				if(army.getPlayerId() != truck.getArmyId())
					RobAndProtTruckHelper.startProt(army, this.truck);
			}
			this.truck.updateProtectorTimer(id, this.delay);
			//更新护镖时间
			updateProtectorTimer(army);
		}
		this.execTime = System.currentTimeMillis() + delay;
		this.getActionQueue().enDelayQueue(this);
	}

	/**
	 * 
	 */
	private void updateProtectorTimer(ArmyProxy army)
	{
		//System.out.println("this.truck.getProtectorTimer().get(army.getPlayerId()) = " + this.truck.getProtectorTimer().get(army.getPlayerId()));
		RespTruckProtTimer.Builder protTimerBuidler = RespTruckProtTimer.newBuilder();
		protTimerBuidler.setTimer(this.truck.getProtectorTimer().get(army.getPlayerId()));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_PROT_TIMER, protTimerBuidler);
		army.sendPbMessage(pkg);
	}
}
