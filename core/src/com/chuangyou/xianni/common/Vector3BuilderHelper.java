package com.chuangyou.xianni.common;

import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.util.Vector3;

public class Vector3BuilderHelper {

	/**
	 * Vector3 编译为 PBVector3
	 * 
	 * @param v3
	 * @return
	 */
	public static PBVector3.Builder build(Vector3 v3) {
		PBVector3.Builder builder = PBVector3.newBuilder();
		if (v3 != null) {
			builder.setX((int) (v3.x * Vector3.Accuracy));
			builder.setY((int) (v3.y * Vector3.Accuracy));
			builder.setZ((int) (v3.z * Vector3.Accuracy));
		}
		return builder;
	}

	/**
	 * PBVector3 编译为 PBVector3
	 * 
	 * @param pbV3
	 * @return
	 */
	public static PBVector3.Builder build(PBVector3 v3) {
		PBVector3.Builder builder = PBVector3.newBuilder();
		builder.setX(v3.getX());
		builder.setY(v3.getY());
		builder.setZ(v3.getZ());
		return builder;
	}

	/**
	 * PBVector3 转换为Vector3
	 * 
	 * @param pbV3
	 * @return
	 */
	public static Vector3 get(PBVector3 pbV3) {
		return new Vector3(pbV3.getX() * 1.0f / Vector3.Accuracy, pbV3.getY() * 1.0f / Vector3.Accuracy, pbV3.getZ() * 1.0f / Vector3.Accuracy);
	}
}
