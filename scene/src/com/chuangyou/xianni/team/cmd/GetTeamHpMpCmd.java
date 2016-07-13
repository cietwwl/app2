package com.chuangyou.xianni.team.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.team.HpMpMapInfoRespProto.HpMpMapInfoRespMsg;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code=Protocol.S_REQ_TEAM_INFO,desc="定时拉队伍血量信息")
public class GetTeamHpMpCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		Team t = TeamMgr.getTeam(army.getPlayerId());
		if(t!=null && t.getMembers().size()>1){
			List<Long> members = t.getMembers(army.getPlayerId());
			for (Long pid : members) {
				ArmyProxy m = WorldMgr.getArmy(pid);
				if(m!=null){
					HpMpMapInfoRespMsg.Builder msg = HpMpMapInfoRespMsg.newBuilder();
					msg.setPlayerId(pid);
					msg.setCurBlood(m.getPlayer().getCurBlood());
					msg.setCurSoul(m.getPlayer().getCurSoul());
					msg.setMaxBlood(m.getPlayer().getMaxBlood());
					msg.setMaxSoul(m.getPlayer().getMaxSoul());
					msg.setMapId(m.getPlayer().getField().id);
					msg.setMapKey(m.getPlayer().getField().getMapKey());
					PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_HP_MP,msg);
					army.sendPbMessage(pkg);
				}
			}
		}

	}

}
