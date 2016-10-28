package com.chuangyou.xianni.role.objects;

import java.util.Set;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager;
import com.chuangyou.xianni.world.ArmyProxy;

public class Transfer extends Living {

	public static final int	FIELD_TRANSFER		= 1;
	public static final int	CAMPAIGN_TRANSFER	= 2;

	/** 目标类型1地图 2副本 */
	private int				targetType;
	/** 目标地图或副本ID */
	private int				targetId;
	/** 目标位置 */
	private Vector3			transferPos;

	/** 传送门触发玩家最低等级 */
	private int				minLevel			= 0;
	/** 传送门触发玩家最高等级 */
	private int				maxLevel			= 0;
	
	/** 可进入玩家列表 */
	private Set<Long>		canEnterIds			= null;

	public Transfer(long id, int type) {
		// TODO Auto-generated constructor stub
		super(id);
		setType(RoleType.transfer);
	}
	
	public void transfer(ArmyProxy army){
		if(targetId <= 0){
			return;
		}
		
//		if (maxLevel > 0) {
//			int playerLevel = army.getPlayer().getSimpleInfo().getLevel();
//			if (playerLevel < minLevel || playerLevel >= maxLevel) {
//				return;
//			}
//		}
//		if(canEnterIds != null){
//			if(!canEnterIds.contains(army.getPlayerId())){
//				return;
//			}
//		}
		if(targetType == 1){
			if(transferPos == null){
				return;
			}
			ScriptInterfaceScenceManager.changeMap(army.getPlayerId(), targetId, (int)transferPos.getX(), (int)transferPos.getY(), (int)transferPos.getZ(), (int)transferPos.getAngle());
		}else{
			Campaign campaign = CampaignMgr.getCampagin(targetId);
			if (campaign != null) {
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

	public Set<Long> getCanEnterIds() {
		return canEnterIds;
	}

	public void setCanEnterIds(Set<Long> canEnterIds) {
		this.canEnterIds = canEnterIds;
	}
	
	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		super.clearData();
		
		if(canEnterIds != null){
			canEnterIds.clear();
			canEnterIds = null;
		}
	}
}
