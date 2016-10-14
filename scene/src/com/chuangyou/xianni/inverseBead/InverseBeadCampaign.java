package com.chuangyou.xianni.inverseBead;

import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.state.OpeningState;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.warfield.BeadFieldManager;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;

public class InverseBeadCampaign extends Campaign {
	private ArmyProxy army;

	public InverseBeadCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, int taskId) {
		super(tempInfo, creater, taskId);
		this.army = creater;
	}

	public boolean agreedToEnter(ArmyProxy army) {
		if (super.agreedToEnter(army)) {
			return army.getPlayerId() == creater;
		}
		return false;
	}

	/** 地图开始 */
	@Override
	public void start() {
		state = new OpeningState(this);
		// 获取副本所有地图
		Map<Integer, FieldInfo> finfos = FieldTemplateMgr.getCFieldInfos(tempId);
		// 创建当前地图
		int i = 1;
		for (Entry<Integer, FieldInfo> entry : finfos.entrySet()) {
			FieldInfo temp = entry.getValue();
			// 创建地图
			Field f = new BeadFieldManager(this.army).createCampaignField(temp.getMapKey(), temp.getType(), id);
			// Field f = FieldMgr.getIns().createCampaignField(temp.getMapKey(),
			// temp.getType(), id);
			Map<Integer, SpwanNode> sonNodes = f.getSpawnNodes();
			// 管理节点
			spwanNodes.putAll(sonNodes);
			allFields.put(f.id, f);
			tempFieldMapping.put(f.getMapKey(), f);
			indexMapping.put(temp.getCampaignIndex(), f.id);
			// 设置为初始地图
			if (i == 1) {
				starField = f;
			}
			i++;
		}

		this.beginTime = System.currentTimeMillis();
		this.endTime = beginTime + 2 * 60 * 60 * 1000;
		CampaignCheckAction action = new CampaignCheckAction(this);
		enDelayQueue(action);
	}

	/**
	 * 副本结束
	 */
	@Override
	public void over() {
		super.over();
	}

}
