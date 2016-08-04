package com.chuangyou.common.util;

import java.util.Random;

public class MathUtils {

	/**
	 * 弧度转角度
	 */
	public static final float deg2ang = 57.29578f;

	/**
	 * 随机数
	 */
	private static final Random R = new Random();

	/**
	 * 计算概率 0 - 100
	 * 
	 * @param probability
	 * @return
	 */
	public static boolean GetProbability(int probability) {
		// System.out.println(R.nextFloat() * 100);
		// System.out.println(probability);
		// System.out.println((R.nextFloat() * 100) < probability);
		return (R.nextFloat() * 100) < probability;
	}

	/**
	 * 范围取值
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static float Clamp(float value, float min, float max) {
		if (value < min) {
			value = min;
			return value;
		}
		if (value > max) {
			value = max;
		}
		return value;
	}

	/**
	 * 求2点之前的插值-目标点的计算
	 * 
	 * @param form
	 * @param to
	 * @param distance
	 * @return
	 */
	public static Vector3 GetVector3InDistance(Vector3 form, Vector3 to, float pathDistance) {
		if (pathDistance == 0)
			return form;
		float totalDistance = Vector3.distance(form, to);
		if (pathDistance >= totalDistance)
			return to;
		float ratio = pathDistance / totalDistance;
		Vector3 result = Vector3.add(form, Vector3.multipy(Vector3.sub(to, form), ratio));
		return result;
	}

	/**
	 * 求2点之前的插值-目标点的计算
	 * 
	 * @param form
	 * @param to
	 * @param distance
	 * @return
	 */
	public static Vector3 GetVector3InDistance2(Vector3 form, Vector3 to, float pathDistance) {
		if (pathDistance == 0)
			return form;
		float totalDistance = Vector3.distance(form, to);

		float ratio = pathDistance / totalDistance;
		Vector3 result = Vector3.add(form, Vector3.multipy(Vector3.sub(to, form), ratio));
		return result;
	}

	/**
	 * 获取某个方向上的点
	 * 
	 * @param dir
	 * @param distance
	 * @return
	 */
	public static Vector3 GetVector3ByDir(Vector3 src, Vector3 dir, float distance) {
		return Vector3.add(src, Vector3.multipy(dir, distance));
	}

	/**
	 * 在一个中心随机一个点
	 * 
	 * @param center
	 * @param radius
	 * @return
	 */
	public static Vector3 GetRandomVector3ByCenter(Vector3 center, float radius) {
		return GetRandomVector3ByCenter(center, radius, true);
	}

	/**
	 * 在一个中心随机一个点
	 * 
	 * @param center
	 * @param radius
	 * @return
	 */
	public static Vector3 GetRandomVector3ByCenter(Vector3 center, float radius, boolean randomRadius) {
		float angle = 360 * R.nextFloat() / deg2ang;
		if (randomRadius)
			radius = radius * R.nextFloat();
		return new Vector3(center.x + (float) (Math.cos(angle) * radius), center.y, center.z + (float) (Math.sin(angle) * radius));
	}

	/**
	 * 随机范围
	 * 
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public static int randomClamp(int min, int max) {
		int v = (int) (min + Math.random() * (max - min + 1));
		return v;
	}

}
