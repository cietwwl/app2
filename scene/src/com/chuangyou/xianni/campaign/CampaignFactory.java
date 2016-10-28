package com.chuangyou.xianni.campaign;

import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignType;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.inverseBead.InverseBeadCampaign;
import com.chuangyou.xianni.role.objects.Transfer;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampaignFactory {

	/**
	 * 创建副本
	 */
	public static Campaign createCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, int taskId) {
		switch (tempInfo.getType()) {
			case CampaignType.SINGLE:
				return new SingleCampaign(tempInfo, creater, taskId);
			case CampaignType.TEAM:
				return new TeamCampaign(tempInfo, creater, taskId);
			case CampaignType.AVATAR:
				return new AvatarCampaign(tempInfo, creater);
			case CampaignType.BEAD:
				return new InverseBeadCampaign(tempInfo, creater, taskId);
			case CampaignType.THE_PLANE:
				return new PlaneCampaign(tempInfo, creater, taskId);
			default:
				break;
		}
		Log.error("the campaignType not exists ," + tempInfo.getTemplateId());
		return null;
	}

	/**
	 * 创建挑战副本
	 */
	public static Campaign createArenaBattleCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, RobotInfoMsg robotDATA) {
		return new ArenaBattleCampaign(tempInfo, creater, robotDATA);
	}

	/**
	 * 创建1V1副本
	 */
	public static Campaign createPvP1v1Campaign(CampaignTemplateInfo tempInfo, ArmyProxy attacker, ArmyProxy defencer) {
		return new PvP1v1Campaign(tempInfo, attacker, defencer);
	}

	/**
	 * 创建帮派夺权副本
	 * 
	 * @param tempInfo
	 * @param creater
	 * @param robotDATA
	 * @return
	 */
	public static Campaign createGuildSeizeCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, RobotInfoMsg robotDATA) {
		return new ArenaGuildSeizeCampaign(tempInfo, creater, robotDATA);
	}

	/**
	 * 创建境界副本
	 * 
	 * @return
	 */
	public static Campaign createStateConfig(CampaignTemplateInfo tempInfo, ArmyProxy creater, StateConfig config) {
		return new StateCampaign(tempInfo, creater, config);
	}

	/**
	 * 创建精英BOSS死亡触发的事件副本
	 * 
	 * @param tempInfo
	 * @param transfer
	 *            对应的传送门
	 * @param tagId
	 *            副本刷节点怪tagId
	 * @return
	 */
	public static Campaign createEliteTriggerCampaign(CampaignTemplateInfo tempInfo, Transfer transfer, int tagId) {
		return new EliteBossTriggerCampaign(tempInfo, transfer, tagId);
	}

	/**
	 * 创建世界BOSS死亡触发的事件副本
	 * 
	 * @param tempInfo
	 * @param transfer
	 * @return
	 */
	public static Campaign createWorldBossTriggerCampaign(CampaignTemplateInfo tempInfo, Transfer transfer) {
		return new WorldBossTriggerCampaign(tempInfo, transfer);
	}

}
