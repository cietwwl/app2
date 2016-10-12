package com.chuangyou.xianni.guild.cmd;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.GuildApplyJoinAction;
import com.chuangyou.xianni.guild.action.GuildApplyListGetAction;
import com.chuangyou.xianni.guild.action.GuildApplyResponseAction;
import com.chuangyou.xianni.guild.action.GuildApplyResponseBatAction;
import com.chuangyou.xianni.guild.action.GuildExitAction;
import com.chuangyou.xianni.guild.action.GuildInfoGetAction;
import com.chuangyou.xianni.guild.action.GuildJobAppointAction;
import com.chuangyou.xianni.guild.action.GuildJobSeizeAction;
import com.chuangyou.xianni.guild.action.GuildJoinTypeSetAction;
import com.chuangyou.xianni.guild.action.GuildListGetAction;
import com.chuangyou.xianni.guild.action.GuildLogAction;
import com.chuangyou.xianni.guild.action.GuildMailAction;
import com.chuangyou.xianni.guild.action.GuildMainBuildLevelupAction;
import com.chuangyou.xianni.guild.action.GuildMemberListGetAction;
import com.chuangyou.xianni.guild.action.GuildMemberRemoveAction;
import com.chuangyou.xianni.guild.action.GuildNoticeGetAction;
import com.chuangyou.xianni.guild.action.GuildNoticeUpdateAction;
import com.chuangyou.xianni.guild.action.GuildShopLevelupAction;
import com.chuangyou.xianni.guild.action.GuildSkillLevelupAction;
import com.chuangyou.xianni.guild.action.GuildSkillListAction;
import com.chuangyou.xianni.guild.action.GuildSkillShopLevelUpAction;
import com.chuangyou.xianni.guild.action.GuildSupplyDonateAction;
import com.chuangyou.xianni.guild.action.GuildWarehouseLevelupAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_GUILD_ACTION_REQ, desc = "门派操作请求")
public class GuildOperateCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		
		GuildBaseAction action = null;
		switch(req.getAction()){
			case GuildOperateAction.REQUEST_GUILD_LIST:
				action = new GuildListGetAction(player, packet);
				break;
			case GuildOperateAction.REQUEST_GUILD_INFO:
				action = new GuildInfoGetAction(player, packet);
				break;
			case GuildOperateAction.REQUEST_GUILD_MEMBER:
				action = new GuildMemberListGetAction(player, packet);
				break;
			case GuildOperateAction.REQUEST_APPLY_JOIN:
				action = new GuildApplyJoinAction(player, packet);
				break;
			case GuildOperateAction.REQUEST_APPLY_LIST:
				action = new GuildApplyListGetAction(player, packet);
				break;
			case GuildOperateAction.RESPONSE_APPLY:
				action = new GuildApplyResponseAction(player, packet);
				break;
			case GuildOperateAction.JOINTYPE_SET:
				action = new GuildJoinTypeSetAction(player, packet);
				break;
			case GuildOperateAction.GUILD_EXIT:
				action = new GuildExitAction(player, packet);
				break;
			case GuildOperateAction.NOTICE_UPDATE:
				action = new GuildNoticeUpdateAction(player, packet);
				break;
			case GuildOperateAction.REMOVE_MEMBER:
				action = new GuildMemberRemoveAction(player, packet);
				break;
			case GuildOperateAction.APPOINT_JOB:
				action = new GuildJobAppointAction(player, packet);
				break;
			case GuildOperateAction.MAINBUILD_LEVELUP:
				action = new GuildMainBuildLevelupAction(player, packet);
				break;
			case GuildOperateAction.SKILLSHOP_LEVELUP:
				action = new GuildSkillShopLevelUpAction(player, packet);
				break;
			case GuildOperateAction.SHOP_LEVELUP:
				action = new GuildShopLevelupAction(player, packet);
				break;
			case GuildOperateAction.WAREHOUSE_LEVELUP:
				action = new GuildWarehouseLevelupAction(player, packet);
				break;
			case GuildOperateAction.GUILD_DONATE:
				action = new GuildSupplyDonateAction(player, packet);
				break;
			case GuildOperateAction.GUILD_NOTICE_GET:
				action = new GuildNoticeGetAction(player, packet);
				break;
			case GuildOperateAction.BAT_RESPONSE_APPLY:
				action = new GuildApplyResponseBatAction(player, packet);
				break;
			case GuildOperateAction.GUILD_SKILL_LIST:
				action = new GuildSkillListAction(player, packet);
				break;
			case GuildOperateAction.GUILD_SKILL_LEVELUP:
				action = new GuildSkillLevelupAction(player, packet);
				break;
			case GuildOperateAction.GUILD_MAIL:
				action = new GuildMailAction(player, packet);
				break;
			case GuildOperateAction.GUILD_LOG:
				action = new GuildLogAction(player, packet);
				break;
			case GuildOperateAction.GUILD_JOB_SEIZE:
				action = new GuildJobSeizeAction(player, packet);
				break;
		}
		action.getActionQueue().enqueue(action);
	}

}
