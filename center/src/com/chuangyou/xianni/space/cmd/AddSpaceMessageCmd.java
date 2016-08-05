package com.chuangyou.xianni.space.cmd;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.space.AddSpaceMessageReqProto.AddSpaceMessageReqMsg;
import com.chuangyou.common.protobuf.pb.space.AddSpaceMessageRespProto.AddSpaceMessageRespMsg;
import com.chuangyou.common.protobuf.pb.space.MessageInfoProto.MessageInfoMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.entity.space.SpaceMessageInfo;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_SPACE_ADD_MSG,desc="添加留言")
public class AddSpaceMessageCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		AddSpaceMessageReqMsg req = AddSpaceMessageReqMsg.parseFrom(packet.getBytes());
		GamePlayer reqPlayer = WorldMgr.getPlayer(req.getPlayerId());
		if(reqPlayer ==null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_ADD_MSG,"无效的playerID"+req.getPlayerId());		
			return;
		}
		SpaceInfo reqSpace = reqPlayer.getSpaceInventory().getSpaceInfo();
		if(reqSpace.getIsNoMsg() == SpaceInfo.NO_MSG){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_ADD_MSG,"空间主人禁止留言");		
			return;
		}
		if(reqSpace.getIsNoMsg() == SpaceInfo.FRIEND_MSG){
			if(!reqPlayer.getFriendInventory().getFriend().isFriend(player.getPlayerId())){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_ADD_MSG,"非好友禁止留言");		
				return;
			}
		}
		
		SpaceMessageInfo info = new SpaceMessageInfo();
		info.setCreateDate(new Date());
		info.setId(EntityIdBuilder.spaceMsgIdBuilder());
		info.setIsCollection(0);
		info.setMessage(req.getMessage());
		info.setReceivePlayerId(reqPlayer.getPlayerId());
		info.setSendPlayerId(player.getPlayerId());
		reqPlayer.getSpaceInventory().addMessage(info);
	
		AddSpaceMessageRespMsg.Builder resp = AddSpaceMessageRespMsg.newBuilder();
		resp.setPlayerId(reqPlayer.getPlayerId());
		MessageInfoMsg.Builder message = info.getMessageMsg();
		message.setSendName(player.getNickName());
		message.setSendFace(player.getSpaceInventory().getSpaceInfo().getFace());
		message.setSendLv(player.getLevel());
		resp.setMessage(message);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SPACE_ADD_MSG, resp);
		player.sendPbMessage(pkg);
		
		
	}

}
