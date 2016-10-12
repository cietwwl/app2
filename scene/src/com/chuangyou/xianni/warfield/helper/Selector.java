package com.chuangyou.xianni.warfield.helper;

import com.chuangyou.xianni.role.helper.RoleConstants;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;
import com.chuangyou.xianni.role.objects.Living;

public abstract class Selector {
	protected Living master;

	public Selector(Living master) {
		this.master = master;
	}

	public abstract boolean selectorType(int type);

	public abstract boolean selectorid(long id);

	public abstract boolean selectorProtection(boolean protection);

	/**
	 * 判断运镖时间 (在运镖时间不处理)
	 * @param other
	 * @return
	 */
	private boolean checkTimer(Living other)
	{
		if(!master.getTruckHelper().isTruckTimer() && !other.getTruckHelper().isTruckTimer())	//都不在运镖时间
			return canSeeOther(other);
		return false;
	}
	
	/**
	 * 判断运镖时间 (在运镖时间不处理)
	 * @param other
	 * @return
	 */
	private boolean checkTimerAndRelation(Living m, Living ot)
	{
		//System.out.println("m = " + m + " ot = " + ot);
		if(!m.getTruckHelper().isTruckTimer() && !ot.getTruckHelper().isTruckTimer())	//都不在运镖时间
		{
			return canSeeOther(ot);
		}
		else if(m.getTruckHelper().isTruckTimer() && ot.getTruckHelper().isTruckTimer())	//都在运镖时间
		{
			//System.out.println("m = " + m.getId() + " ot = " + ot.getId() + " mrt = " + m.getTruckHelper().getRelatedTruck() + " otrt = " + ot.getTruckHelper().getRelatedTruck());
			if(m.getTruckHelper().getRelatedTruck() == ot.getTruckHelper().getRelatedTruck())
			{
				return canSeeOther(ot);
			}
			else
			{
				return false;
			}
		}
//		else if(!m.getTruckHelper().isTruckTimer() && ot.getTruckHelper().isTruckTimer()) return super.canSee(ot);	//玩家不在运镖, 检查的对象在运镖
//		else if(m.getTruckHelper().isTruckTimer() && !ot.getTruckHelper().isTruckTimer()) return false;
		return false;
	}
	
	/**
	 * 
	 * @param m
	 * @param ot
	 * @return
	 */
	private boolean checkTimerAndRelationByMaster(Living m, Living ot)
	{
		if(m.getType() == RoleType.player)
		{
			Living otherMaster = m.getField().getLiving(ot.getArmyId());
			if(otherMaster == null) return canSeeOther(ot);
			return checkTimerAndRelation(m, otherMaster);
		}
		else if(ot.getType() == RoleType.player)
		{
			Living mMaster = m.getField().getLiving(m.getArmyId());
			if(mMaster == null) return canSeeOther(ot);
			return checkTimerAndRelation(mMaster, ot);
		}
		return canSeeOther(ot);
	}
	
	private boolean canSeeOther(Living other) {
		return master.canSee(other.getId()) && other.canSee(master.getId());
	}
	
	public boolean canSee(Living other) {
		int relation = RoleConstants.TruckTimerRelation[master.getType()-1][other.getType()-1];
		switch (relation) {
			case 0:
				return true;
			case 1:
				return canSeeOther(other);
			case 2:
				return checkTimerAndRelation(master, other);
			case 3:
				return checkTimer(other);
			case 4:
				return checkTimerAndRelationByMaster(master, other);
		}
		return canSeeOther(other);
	}

}
