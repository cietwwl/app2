package com.chuangyou.xianni.truck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.warfield.field.Field;

public class TruckMgr {
	private static Map<Long, Truck> trucks = new HashMap<Long, Truck>();
	
	public static Map<Long, Truck> getAllTrucks()
	{
		return trucks;
	}
	
	/**
	 * 添加一个镖车
	 */
	public static void addTruck(Truck truck)
	{
		synchronized (trucks) {
			trucks.put(truck.getId(), truck);
		}
	}
	
	/**
	 * 移动一个镖车
	 * @param id
	 */
	public static void removeTruck(long id)
	{
		synchronized (trucks) {
			trucks.remove(id);
		}
	}
	
	/**
	 * 检查是否存在镖车
	 * */
	public static boolean hasTruck(long id)
	{
		if(trucks.containsKey(id))
			return true;
		return false;
	}
	
	/**
	 * 获取一个镖车
	 * @param id
	 * @return
	 */
	public static Truck getTruck(long id)
	{
		if(hasTruck(id))
			return trucks.get(id);
		return null;
	}
}
