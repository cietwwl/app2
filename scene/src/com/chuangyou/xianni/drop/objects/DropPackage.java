package com.chuangyou.xianni.drop.objects;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.drop.DropItemProto.DropItemMsg;
import com.chuangyou.common.protobuf.pb.drop.DropPackageProto.DropPackageMsg;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.drop.templete.DropTempleteMgr;
import com.chuangyou.xianni.entity.drop.DropInfo;
import com.chuangyou.xianni.entity.drop.DropItemInfo;

public class DropPackage {

	/**
	 * 掉落ID
	 */
	private int dropId;
	
	/**
	 * 所属玩家ID
	 */
	private long playerId;
	
	/**
	 * 掉落者ID
	 */
	private long dropRoleId;
	
	/**
	 * 掉落池ID
	 */
	private int poolId;
	
	/**
	 * 掉落坐标
	 */
	private Vector3 v3;
	
	/**
	 * 掉落时间
	 */
	private long dropTime;
	
	/**
	 * 掉落物品
	 */
	private Map<Long, DropItem> dropItems;
	
	public DropPackageMsg.Builder buildProto(short action){
		DropPackageMsg.Builder msg = DropPackageMsg.newBuilder();
		msg.setAction(action);
		msg.setDropId(getDropId());
		msg.setPlayerId(getPlayerId());
		msg.setDropRoleId(getDropRoleId());
		msg.setDropTime(getDropTime());
		msg.setPosition(Vector3BuilderHelper.build(getV3()));
		for(DropItem item:dropItems.values()){
			DropItemMsg.Builder itemMsg = DropItemMsg.newBuilder();
			itemMsg.setId(item.getId());
			DropItemInfo itemTemp = DropTempleteMgr.getDropItemMap().get(getPoolId()).get(item.getDropItemTempId());
			itemMsg.setItemId(itemTemp.getItemId());
			itemMsg.setCount(itemTemp.getCount());
			msg.addItem(itemMsg);
		}
		return msg;
	}
	
	public DropInfo getDropTemplete(){
		return DropTempleteMgr.getDropPool().get(getPoolId());
	}
	
	public DropItemInfo getDropItemTemplete(long id){
		DropItem dropItem = dropItems.get(id);
		return DropTempleteMgr.getDropItemMap().get(getPoolId()).get(dropItem.getDropItemTempId());
	}

	public int getDropId() {
		return dropId;
	}

	public void setDropId(int dropId) {
		this.dropId = dropId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getDropRoleId() {
		return dropRoleId;
	}

	public void setDropRoleId(long dropRoleId) {
		this.dropRoleId = dropRoleId;
	}

	public int getPoolId() {
		return poolId;
	}

	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}
	
	public Vector3 getV3() {
		return v3;
	}

	public void setV3(Vector3 v3) {
		this.v3 = v3;
	}

	public long getDropTime() {
		return dropTime;
	}

	public void setDropTime(long dropTime) {
		this.dropTime = dropTime;
	}

	public Map<Long, DropItem> getDropItems() {
		return dropItems;
	}

	public void setDropItems(Map<Long, DropItem> dropItems) {
		this.dropItems = dropItems;
	}
}
