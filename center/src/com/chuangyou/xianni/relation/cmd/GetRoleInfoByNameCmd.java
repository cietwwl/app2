package com.chuangyou.xianni.relation.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.friend.GetRoleInfoByNameReqProto.GetRoleInfoByNameReqMsg;
import com.chuangyou.common.protobuf.pb.friend.GetRoleInfoByNameRespProto.GetRoleInfoByNameRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.manager.RelationProtoUtil;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_QUERYROLEBYNAME,desc="查询角色信息")
public class GetRoleInfoByNameCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(player.getRelationInventory() == null) return;
		
		if(System.currentTimeMillis() - player.getRelationInventory().lastQueryTime < 3*1000){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode());
			return;
		}
		player.getRelationInventory().lastQueryTime = System.currentTimeMillis();
		
		GetRoleInfoByNameReqMsg msg = GetRoleInfoByNameReqMsg.parseFrom(packet.getBytes());
		
		String roleName = msg.getRoleName();
		GamePlayer targetPlayer = null;
		
		List<GamePlayer> list = WorldMgr.getAllPlayers();
		for(GamePlayer p: list){
			if(p.getNickName().equals(roleName)){
				targetPlayer = p;
				break;
			}
		}
		
//		if(targetPlayer == null){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Friend_Search_NUll, packet.getCode());
//			return;
//		}
		
		GetRoleInfoByNameRespMsg.Builder resp = GetRoleInfoByNameRespMsg.newBuilder();
		if(targetPlayer != null){
			resp.setRoleInfo(RelationProtoUtil.change(targetPlayer));
		}else{
			PlayerInfo playerInfo = WorldMgr.getPlayerInfoFromDatabase(roleName);
			if(playerInfo != null){
				resp.setRoleInfo(RelationProtoUtil.change(playerInfo, PlayerState.OFFLINE));
			}
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_QUERYROLEBYNAME, resp);
		player.sendPbMessage(pkg);
		
	}

}
