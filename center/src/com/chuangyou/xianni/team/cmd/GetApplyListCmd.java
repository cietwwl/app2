package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.GetApplyListAction;

@Cmd(code=Protocol.C_REQ_GET_APPLY_LIST,desc="获取申请列表")
public class GetApplyListCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetApplyListAction action = new GetApplyListAction(player, packet);
		action.setProtocol(Protocol.C_REQ_GET_APPLY_LIST);
		action.getActionQueue().enqueue(action);
	}

	
	
//	@Override
//	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		GetApplyListTeamAction action = new GetApplyListTeamAction(player, t);
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_GET_APPLY_LIST;
//	}

}
