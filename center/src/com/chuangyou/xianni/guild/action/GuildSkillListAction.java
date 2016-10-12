package com.chuangyou.xianni.guild.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildSkillListProto.GuildSkillListMsg;
import com.chuangyou.xianni.entity.guild.GuildSkillInfo;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildSkillListAction extends GuildBaseAction {

	public GuildSkillListAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		if(player.getGuildInventory() == null) return;
		
		List<GuildSkillInfo> skillList = new ArrayList<>();
		Map<Integer, GuildSkillInfo> skillMap = player.getGuildInventory().getSkillMap();
		skillList.addAll(skillMap.values());
		
		GuildSkillListMsg.Builder msg = GuildSkillListMsg.newBuilder();
		for(GuildSkillInfo skillInfo: skillList){
			GuildSkillInfoMsg.Builder infoMsg = GuildSkillInfoMsg.newBuilder();
			infoMsg.setGuildSkillId(skillInfo.getGuildSkillId());
			infoMsg.setLevel(skillInfo.getLevel());
			msg.addSkillInfo(infoMsg);
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_SKILL_ALL, msg);
		player.sendPbMessage(pkg);
	}

}
