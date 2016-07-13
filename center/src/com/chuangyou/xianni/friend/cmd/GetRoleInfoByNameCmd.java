package com.chuangyou.xianni.friend.cmd;

import com.chuangyou.common.protobuf.pb.friend.GetRoleInfoByNameReqProto.GetRoleInfoByNameReqMsg;
import com.chuangyou.common.protobuf.pb.friend.GetRoleInfoByNameRespProto.GetRoleInfoByNameRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.friend.manager.FriendManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_QUERYROLEBYNAME,desc="查询角色信息")
public class GetRoleInfoByNameCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetRoleInfoByNameReqMsg msg = GetRoleInfoByNameReqMsg.parseFrom(packet.getBytes());
		
		String roleName = msg.getRoleName();
		
		//todo查询
			
		if(System.currentTimeMillis()-player.getFriendInventory().lastQueryFriendTime<3*1000){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode());
			return;
		}
		player.getFriendInventory().lastQueryFriendTime = System.currentTimeMillis();
		
		
		GamePlayer role = player;
		
		if(role == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Friend_Search_NUll, packet.getCode());
			return;
		}
		
		GetRoleInfoByNameRespMsg.Builder resp = GetRoleInfoByNameRespMsg.newBuilder();
		resp.setRoleInfo(FriendManager.change(role));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_QUERYROLEBYNAME, resp);
		player.sendPbMessage(pkg);
		
	}

}
