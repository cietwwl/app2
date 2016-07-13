package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.pool.MemberMatchPool;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 队长操作队伍自动匹配
 * @author laofan
 *
 */
public class LeaderMatchAction extends TeamLeaderAction {

	public LeaderMatchAction(GamePlayer player, PBMessage packet,short p) {
		super(player, packet);
		this.protocol = p;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MemberMatchPool pools = TeamMgr.getMemeberTargetMatch().get(t.getTargetId());
		if(pools!=null && pools.getPools().size()>0){
			while(pools.getPools().size()>0){
				long pId = pools.pollFirst();
				GamePlayer p = WorldMgr.getPlayer(pId);
				if(p!=null && p.getPlayerState()==PlayerState.ONLINE){
					t.addMember(pId);
				}
				if(t.isFull()){
					break;
				}
			}
		}
		if(!t.isFull()){
			TeamMgr.addTeamTargetMatch(t.getTargetId(), t.getId());
		}
	}

}
