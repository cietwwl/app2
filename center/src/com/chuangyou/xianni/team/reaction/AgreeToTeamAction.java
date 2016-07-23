package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.AgreeAndRejectReqProto.AgreeAndRejectReqMsg;
import com.chuangyou.common.protobuf.pb.team.NotifyAgreeOrRejectRespProto.NotifyAgreeOrRejectRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;
import com.chuangyou.xianni.team.reaction.check.TeamCheck;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 队长同意或拒绝XXX的申请
 * @author laofan
 *
 */
public class AgreeToTeamAction extends TeamLeaderAction {

	protected boolean isAgree;
	protected long agreeId;

	public AgreeToTeamAction(GamePlayer player, PBMessage packet) {
		super(player,packet);
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		AgreeAndRejectReqMsg req = AgreeAndRejectReqMsg.parseFrom(packet.getBytes());
		isAgree = req.getIsAgree();
		agreeId = req.getPlayerId();
		if(isAgree == false){
			t.getApplyPools().getPools().remove(agreeId);
			sendNotify(false);
		}else{
			if(TeamCheck.check(player, agreeId, getProtocol(), t)==false)return;
			if(t.addMember(agreeId)!=null){
				sendNotify(true);
			}
		}	
	}
	

	/**
	 * 发送回映消息
	 * @param isAgree
	 */
	private void sendNotify(boolean isAgree){
		NotifyAgreeOrRejectRespMsg.Builder resp = NotifyAgreeOrRejectRespMsg.newBuilder();
		resp.setTeamId(t.getId());
		resp.setIsAgree(isAgree);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_NOTIFY_AGREE_REJECT,resp);
		WorldMgr.getPlayer(agreeId).sendPbMessage(pkg);
	}
	
	

	

}
