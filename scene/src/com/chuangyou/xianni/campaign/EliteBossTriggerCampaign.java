package com.chuangyou.xianni.campaign;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.role.objects.Transfer;
import com.chuangyou.xianni.warfield.spawn.MonsterSpawnNode;
import com.chuangyou.xianni.warfield.spawn.WorkingState;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;

public class EliteBossTriggerCampaign extends Campaign {

	/** 对应传送门的ID，副本销毁时需要移除传送门 */
	private Transfer transfer;
	
	/** 副本内需要激活的节点ID，其他不激活 */
	private int tagId;
	
	public EliteBossTriggerCampaign(CampaignTemplateInfo tempInfo, Transfer enterTransfer, int tagId) {
		super(tempInfo, null, 0);
		// TODO Auto-generated constructor stub
		this.transfer = enterTransfer;
		this.tagId = tagId;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		
		int spawnId = SpawnTemplateMgr.getSpwanId(tagId);
		MonsterSpawnNode node = (MonsterSpawnNode)this.getSpwanNodes().get(spawnId);
		if(node == null){
			Log.error("刷怪表配置错误 : tagId = " + tagId + "   spawnId = " + spawnId);
			this.stop();
			return;
		}
		
		node.stateTransition(new WorkingState(node));
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		
		//副本销毁时移除传送门
		if(transfer != null){
			transfer.getField().leaveField(transfer, true);
			transfer = null;
		}
	}
	
	@Override
	public void clearCampaignData() {
		// TODO Auto-generated method stub
		super.clearCampaignData();
		
		if(transfer != null){
			transfer.getField().leaveField(transfer, true);
			transfer = null;
		}
	}

}
