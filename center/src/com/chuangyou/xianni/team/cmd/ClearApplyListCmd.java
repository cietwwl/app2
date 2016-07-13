package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.ClearApplyListAction;

@Cmd(code=Protocol.C_REQ_TEAM_CLEAR_APPLY_LIST,desc="队长清空申请队列")
public class ClearApplyListCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		ClearApplyListAction action = new ClearApplyListAction(player, packet);
		action.setProtocol(Protocol.C_REQ_TEAM_CLEAR_APPLY_LIST);
		action.getActionQueue().enqueue(action);
	}

//	@Override
//	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		t.getApplyPools().clear();
//		
//		ClearApplyListRespMsg.Builder msg = ClearApplyListRespMsg.newBuilder();
//		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_CLEAR_APPLY_LIST, msg);
//		player.sendPbMessage(pkg);
//		
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_TEAM_CLEAR_APPLY_LIST;
//	}

}
