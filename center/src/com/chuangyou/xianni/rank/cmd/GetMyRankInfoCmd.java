package com.chuangyou.xianni.rank.cmd;

import com.chuangyou.common.protobuf.pb.rank.GetMyRankReqInfoReqProto.GetMyRankReqInfoReqMsg;
import com.chuangyou.common.protobuf.pb.rank.GetMyRankReqInfoRespProto.GetMyRankReqInfoRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.rank.constant.RankType;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_RANK_MYRANK, desc = "排行榜")
public class GetMyRankInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetMyRankReqInfoReqMsg req = GetMyRankReqInfoReqMsg.parseFrom(packet.getBytes());
		RankTempInfo info = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
		if(info!=null){
			GetMyRankReqInfoRespMsg.Builder resp = GetMyRankReqInfoRespMsg.newBuilder();
			resp.setRankType(req.getRankType());
			if(req.getRankType() == RankType.PET){
				resp.setParam(info.getPet());
			}
			
			if(req.getRankType() == RankType.MOUNT){
				resp.setParam(info.getMount());
			}
			
			if(req.getRankType() == RankType.MAGICWP){
				resp.setParam(info.getMagicwp());
			}
			
			if(resp.hasParam()){				
				player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_RANK_MYRANK,resp));
			}
		}
		
		
	}

}
