package com.chuangyou.xianni.role.objects;

import com.chuangyou.common.protobuf.pb.PlayerAttSnapProto.PlayerAttSnapMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg.Builder;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.role.action.MaterialAutoDestroy;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;

/**
 * 镖车物资
 * @author wkghost
 *
 */
public class Material extends Living {

	private int trucktype = 0;
	
	public Material(long armyId, long id, int trucktype) {
		super(armyId, id);
		setType(RoleType.matrial);
		this.trucktype = trucktype;
		getTruckHelper().setTruckTimer(true);
		getTruckHelper().setRelatedTruck(armyId);	//设置关联的镖车
		this.enDelayQueue(new MaterialAutoDestroy(this, SystemConfigTemplateMgr.getIntValue("drop.package.overtime") * 1000));
	}
	
	/**
	 * 镖车类型
	 * @return
	 */
	public int getTruckType()
	{
		return trucktype;
	}
	
	/**
	 * 获取场景对象的快照信息
	 * 
	 * @return
	 */
	@Override
	public PlayerAttSnapMsg.Builder getAttSnapMsg() {
		if (cacheAttSnapPacker == null)
			cacheAttSnapPacker = PlayerAttSnapMsg.newBuilder();
		cacheAttSnapPacker.setPlayerId(id);
		cacheAttSnapPacker.setType(getType());
		cacheAttSnapPacker.setSkinId(1033010);
		cacheAttSnapPacker.setPostion(Vector3BuilderHelper.build(getPostion()));
		cacheAttSnapPacker.setTarget(Vector3BuilderHelper.build(getTargetPostion()));
		cacheAttSnapPacker.setOwnerId(getArmyId());
		return cacheAttSnapPacker;
	}

	/**
	 * 获取详情
	 */
	@Override
	public Builder getBattlePlayerInfoMsg() {
		cachBattleInfoPacket = BattleLivingInfoMsg.newBuilder();
		cachBattleInfoPacket.setLivingId(getId());
		cachBattleInfoPacket.setSkinId(1033010);
		cachBattleInfoPacket.setPlayerId(getArmyId());
		cachBattleInfoPacket.setType(getType());
		
		PropertyMsg.Builder pmsg = PropertyMsg.newBuilder();
		//物资
		pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.METAL.getValue());
		pmsg.setTotalPoint(getProperty(EnumAttr.METAL.getValue()));
		cachBattleInfoPacket.addPropertis(pmsg);
		//镖车物资的itemtype
		pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.WOOD.getValue());
		pmsg.setTotalPoint(getProperty(EnumAttr.WOOD.getValue()));
		cachBattleInfoPacket.addPropertis(pmsg);
		
		if (getPostion() != null) {
			cachBattleInfoPacket.setPostion(Vector3BuilderHelper.build(getPostion()));
		} else {
			cachBattleInfoPacket.setPostion(Vector3BuilderHelper.build(Vector3.Invalid));
		}
		return cachBattleInfoPacket;
	}
}
