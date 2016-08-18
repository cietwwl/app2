package com.chuangyou.xianni.team.reaction.abstractAction;

import com.chuangyou.common.protobuf.pb.team.TeamInfoRespProto.TeamInfoRespMsg;
import com.chuangyou.common.protobuf.pb.team.TeamMemberInfoProto.TeamMemberInfoMsg;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.team.struct.TeamMember;

/**
 * 队伍抽象基类action
 * @author laofan
 *
 */
public abstract class AbstractTeamAction extends Action {

	/** 执行player*/
	protected GamePlayer player;
	/**  协议  */
	protected PBMessage packet;
	
	protected short protocol;
	
	
	public AbstractTeamAction(GamePlayer player, PBMessage packet) {
		super(TeamMgr.getActionQueue());
		this.player = player;
		this.packet = packet;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(player==null ||player.getPlayerState() == PlayerState.OFFLINE)return;
		execute(player,packet);
	}
	
	
	public abstract void execute(GamePlayer player,PBMessage packet);




	public void setProtocol(short protocol) {
		this.protocol = protocol;
	}


	public short getProtocol() {
		return protocol;
	}
	
	/**
	 *  获取队伍信息包
	 * @param t
	 * @return
	 */
	protected TeamInfoRespMsg.Builder getTeamMsg(Team t){
		return t.getTeamMsg();
	}
	
	/**
	 * 队员信息
	 * @param playerId
	 * @return
	 */
	protected TeamMemberInfoMsg.Builder getTeamMemberMsg(long playerId){
		return new TeamMember(0, playerId).getTeamMemberMsg();
	}

}
