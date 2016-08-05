package com.chuangyou.xianni.space.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg;
import com.chuangyou.common.protobuf.pb.space.GetSpaceActionReqProto.GetSpaceActionReqMsg;
import com.chuangyou.common.protobuf.pb.space.GetSpaceActionRespProto.GetSpaceActionRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.space.SpaceActionLogInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_GET_SPACE_ACTION_LOG,desc="操作记录")
public class GetSpaceActionLogCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		GetSpaceActionReqMsg req = GetSpaceActionReqMsg.parseFrom(packet.getBytes());
		GamePlayer reqPlayer = WorldMgr.getPlayer(req.getPlayerId());
		if(reqPlayer ==null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_GET_SPACE_ACTION_LOG,"无效的playerID"+req.getPlayerId());		
			return;
		}
		List<SpaceActionLogInfo> list = reqPlayer.getSpaceInventory().getActions();
		
		if(req.getStartIndex()>=req.getEndIndex()){		
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_GET_SPACE_ACTION_LOG,"位错误：startIndex:"+req.getStartIndex()+"endIndex:"+req.getEndIndex());		
			return;
		}
		int startIndex = Math.max(0, req.getStartIndex());
		int endIndex  = Math.min(req.getEndIndex(),list.size());
		
		list = list.subList(startIndex, endIndex);
		
		GetSpaceActionRespMsg.Builder resp = GetSpaceActionRespMsg.newBuilder();
		for (SpaceActionLogInfo spaceActionLogInfo : list) {
			ActionLogMsg.Builder subMsg = spaceActionLogInfo.getMsg();
			GamePlayer sendPlayer = WorldMgr.getPlayer(spaceActionLogInfo.getSendPlayerId());
			if(sendPlayer!=null){
				subMsg.setSendFace(sendPlayer.getSpaceInventory().getSpaceInfo().getFace());
				subMsg.setSendLv(sendPlayer.getLevel());
				subMsg.setSendName(sendPlayer.getNickName());
				resp.addInfos(subMsg);
			}
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_SPACE_ACTION_LOG,resp);
		player.sendPbMessage(pkg);
		
	}

}
