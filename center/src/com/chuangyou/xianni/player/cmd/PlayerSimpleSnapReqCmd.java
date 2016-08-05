package com.chuangyou.xianni.player.cmd;

import com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg;
import com.chuangyou.common.protobuf.pb.PlayerSimpleSnapRespProto.PlayerSimpleSnapRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_PLAYER_SIMPLE,desc="查询角色简单信息")
public class PlayerSimpleSnapReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PlayerSimpleSnapReqMsg req = PlayerSimpleSnapReqMsg.parseFrom(packet.getBytes());
		long playerId = req.getPlayerId();
		GamePlayer p = WorldMgr.getPlayer(playerId);
		if(p!=null){
			PlayerSimpleSnapRespMsg.Builder resp = PlayerSimpleSnapRespMsg.newBuilder();
			resp.setPlayerId(playerId);
			Team t = TeamMgr.getPlayerTeam(playerId);
			if(t == null){
				resp.setTeamId(0);
			}else{
				resp.setTeamId(t.getId());
			}
			resp.setJob(p.getBasePlayer().getPlayerInfo().getJob());
			resp.setLevel(p.getBasePlayer().getPlayerInfo().getLevel());
			resp.setSkinId(p.getBasePlayer().getPlayerInfo().getSkinId());
			resp.setNickName(p.getBasePlayer().getPlayerInfo().getNickName());
			resp.setIsFriend(p.getFriendInventory().getFriend().isFriend(player.getPlayerId()));
			if (p.getPlayerState() == PlayerState.OFFLINE) {
				resp.setIsOnline(false);
			} else {
				resp.setIsOnline(true);	
			}
			
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_SIMPLE,resp);
			player.sendPbMessage(pkg);
		}		
	}

}
