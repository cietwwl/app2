package com.chuangyou.xianni.battle.buffer.specialbuf;

import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.soul.SoulFuseSkillConfig;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;

public class TruckAttributesBuffer extends AttributesBuffer {

	public TruckAttributesBuffer(Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(source, target, bufferInfo);
	}

	public int getAddValue(int type, int initValue) {
		int addValue = 0;
		if (getBufferInfo().getValueType() == type) {
			int effectValue = getBufferInfo().getValue() + (int) Math.ceil(initValue * (getBufferInfo().getValuePercent() / 10000f));
			addValue = calSoullv(effectValue, SoulFuseSkillConfig.EFFECT);
		}
		if (getBufferInfo().getValueType1() == type) {
			int effectValue = getBufferInfo().getValue1() + (int) Math.ceil(initValue * (getBufferInfo().getValuePercent1() / 10000f));
			addValue = calSoullv(effectValue, SoulFuseSkillConfig.EFFECT);
		}
		int rate = 0;
		long truckId = TruckRelationshipMgr.getRelationTruck(target.getArmyId());
		Truck truck = TruckMgr.getTruck(truckId);
		if (truck != null) {
			rate = truck.getProtectors().size();
		}
		return addValue * rate;
	}

}
