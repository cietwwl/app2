package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.PlayerCreateResultInnerProto.PlayerCreateResultInnerMsg;
import com.chuangyou.common.protobuf.pb.PlayerCreateResultProto.PlayerCreateResultMsg;
import com.chuangyou.common.protobuf.pb.PlayerInfoListMsgProto.PlayerInfoListMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.G_PLAYER_CREATE_RESULT, desc = "创建角色结果")
public class PlayerCreateResultCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PlayerCreateResultInnerMsg req = PlayerCreateResultInnerMsg.parseFrom(packet.getBytes());
		
		Channel tempSession = UserMgr.removeTempSession(req.getUserId());
		
		if(req.getResultCode() == ErrorCode.SUCCESS){
			PlayerInfoListMsg plmsg = req.getPlayers();
			PBMessage p = MessageUtil.buildMessage(Protocol.U_G_PLAYER_LIST, plmsg);
			tempSession.writeAndFlush(p);
		}else{
			PlayerCreateResultMsg.Builder msg = PlayerCreateResultMsg.newBuilder();
			msg.setResultCode(req.getResultCode());
			PBMessage p = MessageUtil.buildMessage(Protocol.U_G_PLAYER_CREATE_RESULT, msg);
			tempSession.writeAndFlush(p);
		}
	}

}
