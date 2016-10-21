package com.chuangyou.xianni.role.objects;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;
import com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager;
import com.chuangyou.xianni.world.ArmyProxy;

public class Transfer extends Living {
	
	public static final int FIELD_TRANSFER = 1;
	public static final int CAMPAIGN_TRANSFER = 2;

	/** 目标类型1地图 2副本 */
	private int targetType;
	/** 目标地图或副本ID */
	private int targetId;
	
	private Vector3 transferPos;
	
	/** 传送门触发玩家最低等级 */
	private int minLevel = 0;
	/** 传送门触发玩家最高等级 */
	private int maxLevel = 0;
	
	public Transfer(long id, int type) {
		// TODO Auto-generated constructor stub
		super(id);
		setType(RoleType.transfer);
	}
	
	public void transfer(ArmyProxy army){
		if(targetType == 1){
			if(transferPos == null) return;
			ScriptInterfaceScenceManager.changeMap(army.getPlayerId(), targetId, (int)transferPos.x, (int)transferPos.y, (int)transferPos.z, (int)transferPos.angle);
		}else{
			Campaign campaign = CampaignMgr.getCampagin(targetId);
			if(campaign != null){
				if(maxLevel > 0){
					int playerLevel = army.getPlayer().getSimpleInfo().getLevel();
					if(playerLevel < minLevel || playerLevel >= maxLevel){
						return;
					}
				}
				campaign.onPlayerEnter(army);
			}
		}
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public Vector3 getTransferPos() {
		return transferPos;
	}

	public void setTransferPos(Vector3 transferPos) {
		this.transferPos = transferPos;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
}
