package com.chuangyou.xianni.truck.helper;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.truck.TruckCheckPointConfig;
import com.chuangyou.xianni.truck.TruckTempMgr;

public class TrcukCheckHelper {

	/**
	 * 距离阈值
	 */
	public static final int THRESHOLD = 2;
	
	/**
	 * 检查点位置
	 * @return
	 */
	public static boolean checkPoint(Vector3 src, int checkpointId)
	{
		TruckCheckPointConfig checkPointInfo = TruckTempMgr.getCheckPoints().get(checkpointId);
		float dist = MathUtils.getDistByXZ(src, checkPointInfo.getPoint());
		if(dist < THRESHOLD) return true;
		return false;
	}
	
	/**
	 * 计算镖车的速度
	 * @param type	镖车类型
	 * @param mat	物资数量
	 * @return
	 */
	public static long getTruckSpeed(long weight, long mat, long curSpeed)
	{
		//[(最佳载重/现有物资)取小数点后2位,并最大值为1]*镖车速度
		float rate = weight / mat;
		if(rate > 1) rate = 1;
		return (long) (rate * curSpeed * 100);
	}
	
}
