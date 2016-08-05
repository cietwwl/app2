package com.chuangyou.xianni.space.cmd;

import com.chuangyou.common.protobuf.pb.space.GetSpaceInfoReqProto.GetSpaceInfoReqMsg;
import com.chuangyou.common.protobuf.pb.space.GetSpaceInfoRespProto.GetSpaceInfoRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_GET_SPACE_INFO,desc="获取空间详情")
public class GetSpaceInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetSpaceInfoReqMsg req = GetSpaceInfoReqMsg.parseFrom(packet.getBytes());
		long reqPlayerId = req.getPlayerId();
		GamePlayer reqPlayer = WorldMgr.getPlayer(reqPlayerId);
		if(reqPlayer!=null){
			GetSpaceInfoRespMsg.Builder msg = reqPlayer.getSpaceInventory().getSpaceInfoMsg();
			boolean isFriends = reqPlayer.getFriendInventory().getFriend().isFriend(player.getPlayerId());
			msg.setIsFriends(isFriends);
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_SPACE_INFO,msg);
			player.sendPbMessage(pkg);		
		}else{
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_GET_SPACE_INFO,"无效的playerID"+reqPlayerId);		
		}
	}

}
