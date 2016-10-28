package com.chuangyou.xianni.entity.player;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.army.PlayerPositionInfoProto.PlayerPositionInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class PlayerPositionInfo extends DataObject {
	private long	playerId;
	// 当前地图位置
	private int		mapId;			// 地图ID
	private int		mapTempId;		// 地图模型ID
	private int		x;				// 坐标
	private int		y;
	private int		z;

	// 上一个地图位置
	private int		preMapId;
	private int		preMapTempId;
	private int		preX;
	private int		preY;
	private int		preZ;

	public void writeProto(PostionMsg.Builder proto) {
		proto.setMapId(mapId);
		proto.setMapKey(mapTempId);

		PBVector3.Builder vector3 = PBVector3.newBuilder();
		vector3.setX(x);
		vector3.setY(y);
		vector3.setZ(z);
		proto.setPostion(vector3.build());
	}

	public void writePlayerPositionInfoMsg(PlayerPositionInfoMsg.Builder proto) {
		PostionMsg.Builder curPos = PostionMsg.newBuilder();
		curPos.setMapId(mapId);
		curPos.setMapKey(mapTempId);
		PBVector3.Builder vector3 = PBVector3.newBuilder();
		vector3.setX(x);
		vector3.setY(y);
		vector3.setZ(z);
		curPos.setPostion(vector3);
		proto.setCurPos(curPos);

		PostionMsg.Builder prePos = PostionMsg.newBuilder();
		prePos.setMapId(preMapId);
		prePos.setMapKey(preMapTempId);
		PBVector3.Builder preV3 = PBVector3.newBuilder();
		preV3.setX(preX);
		preV3.setY(preY);
		preV3.setZ(preZ);
		prePos.setPostion(preV3);
		proto.setPrePos(prePos);

	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		setOp(Option.Update);
		this.mapId = mapId;
	}

	public int getMapTempId() {
		return mapTempId;
	}

	public void setMapTempId(int mapTempId) {
		setOp(Option.Update);
		this.mapTempId = mapTempId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		setOp(Option.Update);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		setOp(Option.Update);
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		setOp(Option.Update);
		this.z = z;
	}

	public int getPreMapId() {
		return preMapId;
	}

	public void setPreMapId(int preMapId) {
		setOp(Option.Update);
		this.preMapId = preMapId;
	}

	public int getPreMapTempId() {
		return preMapTempId;
	}

	public void setPreMapTempId(int preMapTempId) {
		setOp(Option.Update);
		this.preMapTempId = preMapTempId;
	}

	public int getPreX() {
		return preX;
	}

	public void setPreX(int preX) {
		setOp(Option.Update);
		this.preX = preX;
	}

	public int getPreY() {
		return preY;
	}

	public void setPreY(int preY) {
		setOp(Option.Update);
		this.preY = preY;
	}

	public int getPreZ() {
		return preZ;
	}

	public void setPreZ(int preZ) {
		setOp(Option.Update);
		this.preZ = preZ;
	}

}
