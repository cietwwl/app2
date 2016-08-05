package com.chuangyou.xianni.space.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.space.GetSpaceMessageReqProto.GetSpaceMessageReqMsg;
import com.chuangyou.common.protobuf.pb.space.GetSpaceMessageRespProto.GetSpaceMessageRespMsg;
import com.chuangyou.common.protobuf.pb.space.MessageInfoProto.MessageInfoMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.space.SpaceMessageInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_GET_SPACE_MESSAGE,desc="留言")
public class GetSpaceMessageCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetSpaceMessageReqMsg req = GetSpaceMessageReqMsg.parseFrom(packet.getBytes());
		GamePlayer reqPlayer = WorldMgr.getPlayer(req.getPlayerId());
		if(reqPlayer ==null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_GET_SPACE_MESSAGE,"无效的playerID"+req.getPlayerId());		
			return;
		}
		List<SpaceMessageInfo> list = reqPlayer.getSpaceInventory().getListMessages(req.getOp());
		
		if(req.getStartIndex()>=req.getEndIndex()){		
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_GET_SPACE_MESSAGE,"位错误：startIndex:"+req.getStartIndex()+"endIndex:"+req.getEndIndex()+"op:"+req.getOp());		
			return;
		}
		
		int startIndex = Math.max(0, req.getStartIndex());
		int endIndex  = Math.min(req.getEndIndex(),list.size());
		
		list = list.subList(startIndex, endIndex);
		GetSpaceMessageRespMsg.Builder resp = GetSpaceMessageRespMsg.newBuilder();
		resp.setPlayerId(reqPlayer.getPlayerId());
		resp.setOp(req.getOp());
		for (SpaceMessageInfo spaceMessageInfo : list) {
			MessageInfoMsg.Builder subMsg = spaceMessageInfo.getMessageMsg();
			GamePlayer sendPlayer = WorldMgr.getPlayer(spaceMessageInfo.getSendPlayerId());
			if(sendPlayer!=null){
				subMsg.setSendFace(sendPlayer.getSpaceInventory().getSpaceInfo().getFace());
				subMsg.setSendLv(sendPlayer.getLevel());
				subMsg.setSendName(sendPlayer.getNickName());
				resp.addInfos(subMsg);
			}
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_SPACE_MESSAGE,resp);
		player.sendPbMessage(pkg);
			
	}

}
