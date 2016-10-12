package com.chuangyou.xianni.rank.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.rank.GetRanksByIndexReqProto.GetRanksByIndexReqMsg;
import com.chuangyou.common.protobuf.pb.rank.GetRanksByIndexRespProto.GetRanksByIndexRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.rank.RankInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_RANK_GET_INDEX, desc = "排行榜")
public class GetRanksByIndexCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetRanksByIndexReqMsg  req = GetRanksByIndexReqMsg.parseFrom(packet.getBytes());
		
		String str = req.getRankType()+"_"+ req.getRankRange();
		List<RankInfo> list = RankServerManager.getInstance().getRanks().get(str);
		
		if(req.getStartIndex()>=req.getEndIndex()){		
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_RANK_GET_INDEX,"位错误：startIndex:"+req.getStartIndex()+"endIndex:"+req.getEndIndex());		
			return;
		}
		int startIndex = Math.max(0, req.getStartIndex());
		int endIndex  = Math.min(req.getEndIndex(),list.size());
		
		list = list.subList(startIndex, endIndex);
		
		GetRanksByIndexRespMsg.Builder resp = GetRanksByIndexRespMsg.newBuilder();
		resp.setRankRange(req.getRankRange());
		resp.setRankType(req.getRankType());
		for (RankInfo rankInfo : list) {
			resp.addRanks(rankInfo.getMsg());
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_RANK_GET_INDEX,resp);
		player.sendPbMessage(pkg);
			
	}

}
