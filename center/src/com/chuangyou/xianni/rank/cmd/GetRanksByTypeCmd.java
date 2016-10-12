package com.chuangyou.xianni.rank.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.rank.GetRanksByTypeReqProto.GetRanksByTypeReqMsg;
import com.chuangyou.common.protobuf.pb.rank.GetRanksByTypeRespProto.GetRanksByTypeRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.rank.RankInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_RANK_GET_TOTAL, desc = "排行榜")
public class GetRanksByTypeCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetRanksByTypeReqMsg req = GetRanksByTypeReqMsg.parseFrom(packet.getBytes());
		
		String str = req.getRankType()+"_"+ req.getRankRange();
		
		List<RankInfo> list = RankServerManager.getInstance().getRanks().get(str);
		if(list!=null){
			GetRanksByTypeRespMsg.Builder resp = GetRanksByTypeRespMsg.newBuilder();
			resp.setRankType(req.getRankType());
			resp.setRankRange(req.getRankRange());
			resp.setTotalRanks(list.size());
			RankInfo info = getRankInfo(player.getPlayerId(),list);
			if(info!=null){
				resp.setMyRank(info.getMsg());
			}
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_RANK_GET_TOTAL,resp);
			player.sendPbMessage(pkg);
		}
	}
	
	private RankInfo getRankInfo(long playerId,List<RankInfo> list){
		for (RankInfo rankInfo : list) {
			if(rankInfo.getPlayerId() == playerId)return rankInfo;
		}
		return null;
	}

}
