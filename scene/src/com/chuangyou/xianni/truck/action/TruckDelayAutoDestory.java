package com.chuangyou.xianni.truck.action;

import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.helper.TruckCompleteHelper;
import com.chuangyou.xianni.truck.objects.TruckResultData;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

/**
 * 镖车自动销毁Action
 * 镖车创建后，x小时自动销毁
 * x : 根据不同的镖车配置时限
 * @author wkghost
 *
 */
public class TruckDelayAutoDestory extends DelayAction {

	Truck					truck;
	
	public TruckDelayAutoDestory(Truck queue, int delay) {
		super(queue, delay);
		this.truck = queue;
	}

	@Override
	public void execute() {
		if(truck.getLivingState() == Living.DISTORY) 
		{
			return;
		}
		ArmyProxy army = WorldMgr.getArmy(truck.getArmyId());
		TruckCompleteHelper.onComplete(army, truck, TruckResultData.STATE_TIMEOUT);
	}

}
