package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.GuildConstant;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.entity.guild.GuildInfo;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildJoinTypeSetAction extends GuildIsGuildMemberAction {

	public GuildJoinTypeSetAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		//系统门派不能设置加入条件
		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD) return;
		
		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getJoinConditionSet() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		short joinType = (short)req.getParam1();
		int levellimit = (int)req.getParam2();
		int fightLimit = (int)req.getParam3();
		
		if(joinType != GuildConstant.JoinType.CHECK_JOIN && joinType != GuildConstant.JoinType.BAN && joinType != GuildConstant.JoinType.AUTO_CONDITION){
			return;
		}
		GuildInfo info = guild.getGuildInfo();
		info.setJoinType(joinType);
		info.setLevelLimit(levellimit);
		info.setFightLimit(fightLimit);
		
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.JOINTYPE_SET);
		respMsg.setResult(0);
		respMsg.setParam1(info.getJoinType());
		respMsg.setParam2(info.getLevelLimit());
		respMsg.setParam3(info.getFightLimit());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(pkg);
	}

}
