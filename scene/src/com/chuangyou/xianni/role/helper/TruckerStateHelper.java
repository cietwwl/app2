package com.chuangyou.xianni.role.helper;

import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;

/**
 * 运镖时刻
 * @author wkghost
 *
 */
public class TruckerStateHelper {

	///运镖时刻
	private boolean truckTimer;
	
	private Living l;
	/** 关联的镖车的ID */
	private long relatedTruck;
	
	public TruckerStateHelper(Living l)
	{
		this.l = l;
		this.setRelatedTruck(TruckRelationshipMgr.getRelationTruck(l.getId()));
		if(getRelatedTruck() > 0)
			setTruckTimer(true);
	}
	
	public boolean isTruckTimer() {
		return truckTimer;
	}
	
	/**
	 * 设置运镖时刻
	 * @param truckTimer
	 */
	public void setTruckTimer(boolean truckTimer) {
		this.truckTimer = truckTimer;
	}
	
	public long getRelatedTruck() {
		return relatedTruck;
	}
	
	public void setRelatedTruck(long relatedTruck) {
		this.relatedTruck = relatedTruck;
	}
	
	/**
	 * 运镖关系人死亡
	 */
	public void relationOnDie()
	{
		//在运镖时间中
		if(isTruckTimer())
		{
			Truck truck = TruckMgr.getTruck(getRelatedTruck());
			if(truck != null)
			{
				truck.dropMatrials(l.getId());
			}
		}
	}
	
	public void dispose()
	{
		this.l = null;
	}

}
