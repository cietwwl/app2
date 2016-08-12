package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.PlayerDataMsgProto.PlayerDataMsg;
import com.chuangyou.common.protobuf.pb.PlayerLoadDataMsgProto.PlayerLoadDataMsg;
import com.chuangyou.common.protobuf.pb.campaign.CampaignOptionMsgProto.CampaignOptionMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.army.ArmyInventory;
import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.campaign.CampaignInventory;
import com.chuangyou.xianni.equip.EquipInventory;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.logic.GetMaillInfoLogic;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.task.logic.GetTaskLogic;
import com.chuangyou.xianni.team.reaction.GetTeamInfoAction;

/**
 * <pre>
 * 客户端请求发送相关数据
 * </pre>
 */
@Cmd(code = Protocol.C_PLAYER_DATA, desc = "登录加载")
public class LoginLoadCmd extends AbstractCommand {
	static final int	SYS			= 1;
	static final int	USER		= 2;
	static final int	ARMY		= 3;
	static final int	BAG			= 4;
	static final int	TASK		= 5;
	static final int	CAMPAIGN	= 6;
	static final int	TEAM		= 7;
	static final int	SHOP		= 8;
	static final int	EQUIP		= 9;

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		System.err.println(player.getPlayerId() + "登录第六步：登录加载用户数据");
		PlayerDataMsg dataType = PlayerDataMsg.parseFrom(packet.toByteArray());
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
			if (dataType.getDataType() == CAMPAIGN && player.getCurCampaign() > 0) {
				CampaignOptionMsg.Builder builder = CampaignOptionMsg.newBuilder();
				builder.setOptionType(1);// 请求副本信息
				builder.setParam1(player.getCurCampaign());
				PBMessage message = MessageUtil.buildMessage(Protocol.S_CAMPAIGN_OPTION, builder);
				player.sendPbMessage(message);

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
			}

			// 加载好友列表
			if (dataType.getDataType() == SYS) {
			}

			// 装备栏位信息
			if (dataType.getDataType() == EQUIP) {
				EquipInventory equipInventory = player.getEquipInventory();
				if (equipInventory != null) {
					equipInventory.updateAllInfo();
				}
			}

		} catch (Exception e) {
			Log.error("发送用户数据 失败,nickname " + "nickname" + ", userId " + player.getPlayerId(), e);
		} finally {
			PlayerLoadDataMsg.Builder builder = PlayerLoadDataMsg.newBuilder();
			builder.setLoadDataType(dataType.getDataType());
			PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DATA_LOAD_STATU, builder);
			player.sendPbMessage(message);
			
			InverseBeadManager.syncSpawn(player);
		}
	}

	private void checkLoginState(GamePlayer player) {

	}

}
