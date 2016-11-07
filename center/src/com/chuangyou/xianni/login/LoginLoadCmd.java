package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.PlayerDataMsgProto.PlayerDataMsg;
import com.chuangyou.common.protobuf.pb.PlayerLoadDataMsgProto.PlayerLoadDataMsg;
import com.chuangyou.common.protobuf.pb.campaign.CampaignOptionMsgProto.CampaignOptionMsg;
import com.chuangyou.common.protobuf.pb.space.GetSpaceInfoRespProto.GetSpaceInfoRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.activeSystem.logic.GetAllActiveLogic;
import com.chuangyou.xianni.activity.template.ActivityTemplateMgr;
import com.chuangyou.xianni.army.ArmyInventory;
import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.campaign.CampaignInventory;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.equip.EquipInventory;
import com.chuangyou.xianni.guild.action.GuildInfoGetAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.RelationInventory;
import com.chuangyou.xianni.shop.logic.GetMaillInfoLogic;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.soul.logic.GetSoulInfoLogic;
import com.chuangyou.xianni.soul.logic.make.SoulMakeTaskLogic;
import com.chuangyou.xianni.task.logic.GetTaskLogic;
import com.chuangyou.xianni.team.reaction.GetTeamInfoAction;
import com.chuangyou.xianni.vip.manager.VipManager;
import com.chuangyou.xianni.welfare.WelfareManager;

/**
 * <pre>
 * 客户端请求发送相关数据
 * </pre>
 */
@Cmd(code = Protocol.C_PLAYER_DATA, desc = "登录加载")
public class LoginLoadCmd extends AbstractCommand {

	static final int	SYS				= 1;
	static final int	USER			= 2;
	static final int	ARMY			= 3;
	static final int	BAG				= 4;
	static final int	TASK			= 5;
	static final int	CAMPAIGN		= 6;
	static final int	TEAM			= 7;
	static final int	SHOP			= 8;
	static final int	EQUIP			= 9;
	static final int	FRIEND			= 10;
	static final int	VIP				= 11;
	static final int	SOUL_MAKE_TASK	= 12;
	static final int	SOUL_INFO		= 13;
	static final int	SPACE_SELF		= 14;
	static final int	ACTIVITY_INIT	= 15;
	static final int	GUILD_INFO		= 16;
	/** 分身 */
	static final int	AVATAR			= 17;
	/**
	 * 活跃系统
	 */
	static final int    ATIVE_SYSTEM    = 19;
	/** 福利 */
	static final int	WELFARE			= 18;

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		PlayerDataMsg dataType = PlayerDataMsg.parseFrom(packet.toByteArray());
		System.err.println(player.getPlayerId() + "登录第六步：登录加载用户数据 type = " + dataType.getDataType());
		try {
			// 系统状态
			if (dataType.getDataType() == SYS) {

			}

			// 加载用户
			if (dataType.getDataType() == USER) {
				PlayerInfoSendCmd.sendPlayerTimeData(player);
			}

			// 用户部队
			if (dataType.getDataType() == ARMY) {
				ArmyInventory armyInventory = player.getArmyInventory();
				if (armyInventory != null) {
					armyInventory.updateHeroInfo();
				}
			}

			// 用户背包
			if (dataType.getDataType() == BAG) {
				BagInventory bagInventory = player.getBagInventory();
				if (bagInventory != null) {
					bagInventory.updateAll();
				}
			}
			if (dataType.getDataType() == TASK) {
				new GetTaskLogic().process(player);
			}
			if (dataType.getDataType() == CAMPAIGN) {
				if (player.getCurCampaign() > 0) {
					CampaignOptionMsg.Builder builder = CampaignOptionMsg.newBuilder();
					builder.setOptionType(CampaignConstant.GET_INFO);// 请求副本信息
					builder.setParam1(player.getCurCampaign());
					PBMessage message = MessageUtil.buildMessage(Protocol.S_CAMPAIGN_OPTION, builder);
					player.sendPbMessage(message);
				}
				CampaignInventory campaignInventory = player.getCampaignInventory();
				if (campaignInventory != null) {
					campaignInventory.updataAll();
				}
			}

			// 队伍
			if (dataType.getDataType() == TEAM) {
				GetTeamInfoAction action = new GetTeamInfoAction(player, null);
				action.getActionQueue().enqueue(action);
			}

			// 商店
			if (dataType.getDataType() == SHOP) {
				new GetMaillInfoLogic().doNormalResult(player);
				// new GetMaillInfoLogic().doNormalResult(player);
			}

			// 加载好友列表
			if (dataType.getDataType() == FRIEND) {
				RelationInventory relationInventory = player.getRelationInventory();
				if (relationInventory != null) {
					relationInventory.sendRelationList(PlayerRelationConstant.FRIEND);
				}
			}

			// 装备栏位信息
			if (dataType.getDataType() == EQUIP) {
				EquipInventory equipInventory = player.getEquipInventory();
				if (equipInventory != null) {
					equipInventory.updateAllInfo();
				}
			}
			if (dataType.getDataType() == VIP) {
				VipManager.getVipInfo(player);
			}

			// soul task
			if (dataType.getDataType() == SOUL_MAKE_TASK) {
				new SoulMakeTaskLogic().syncSoulMakeTask(player);
			}

			// soul
			if (dataType.getDataType() == SOUL_INFO) {
				new GetSoulInfoLogic().process(player);
			}

			if (dataType.getDataType() == SPACE_SELF) {
				if (player.getSpaceInventory() == null)
					return;
				GetSpaceInfoRespMsg.Builder msg = player.getSpaceInventory().getSpaceInfoMsg();
				msg.setIsFriends(false);
				PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_SPACE_INFO, msg);
				player.sendPbMessage(pkg);
			}
			// 日常活动
			if (dataType.getDataType() == ACTIVITY_INIT) {
				ActivityTemplateMgr.sendTempsMsg(player);
				if (player.getActivityInventory() != null) {
					player.getActivityInventory().sendAllActivityInfos();
				}
				if (player.getArenaInventory() != null) {
					player.getArenaInventory().sendArenaInfo();
				}
			}
			// 所在帮派信息
			if (dataType.getDataType() == GUILD_INFO) {
				GuildBaseAction action = new GuildInfoGetAction(player, packet);
				action.getActionQueue().enqueue(action);
			}
			// 分身
			if (dataType.getDataType() == AVATAR) {
				if (player.getAvatarInventory() != null) {
					player.getAvatarInventory().sendAllAvatarInfos();
				}
			}
			//活跃系统
			if(dataType.getDataType() == ATIVE_SYSTEM){
				if(player.getActiveInventory() !=null){
					new GetAllActiveLogic().process(player);
				}
			}
			// 福利
			if (dataType.getDataType() == WELFARE && player.getWelfareConditionRecordInventory() != null) {
				//推送福利信息
				WelfareManager.sendAllWelfareInfo(player);
			}

		} catch (Exception e) {
			Log.error("发送用户数据 失败,nickname " + player.getNickName() + ", userId " + player.getPlayerId(), e);
		} finally {
			// Log.error("Protocol.U_G_DATA_LOAD_STATU = " +
			// Protocol.U_G_DATA_LOAD_STATU + " type = " +
			// dataType.getDataType());
			PlayerLoadDataMsg.Builder builder = PlayerLoadDataMsg.newBuilder();
			builder.setLoadDataType(dataType.getDataType());
			PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DATA_LOAD_STATU, builder);
			player.sendPbMessage(message);
		}
	}

	private void checkLoginState(GamePlayer player) {
		
	}

}
