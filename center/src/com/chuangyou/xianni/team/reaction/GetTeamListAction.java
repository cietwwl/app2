package com.chuangyou.xianni.team.reaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.team.GetTeamListReqProto.GetTeamListReqMsg;
import com.chuangyou.common.protobuf.pb.team.GetTeamListRespProto.GetTeamListRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.TeamTargetTempMgr;
import com.chuangyou.xianni.team.pool.TeamMatchPool;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.struct.Team;

/**
 * 根据目标获取队伍列表
 * @author laofan
 *
 */
public class GetTeamListAction extends TeamNoAction {

	/**
	 * 目标ID
	 */
	private int targetId;
	
	public GetTeamListAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 执行
	 */
	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetTeamListReqMsg msg = GetTeamListReqMsg.parseFrom(packet.getBytes());
		targetId = msg.getTargetId();
		
		if(targetId==TeamMgr.TEAM_NO_TARGET){
			noTargetSend(player);
		}else{
			targetSend(player);
		}
	}
	
	/**
	 * 有目标返回
	 * @param player
	 */
	private void targetSend(GamePlayer player){
		TeamMatchPool pool = TeamMgr.getTargetPools().get(targetId);
		if(pool!=null){
			pool.sort();
			List<Integer> list = pool.getClonePools();
			send(player,list.subList(0, Math.min(list.size(), 20)));
		}else{
			send(player,new ArrayList<Integer>());
		}
	}
	
	/**
	 *  无目标返回 -- 无目标可以当着一种特殊的目标 
	 * @param player
	 */
	private void noTargetSend(GamePlayer player){
		LinkedList<Integer> totalList = new LinkedList<>();
		Iterator<Entry<Integer,TeamMatchPool>> it = TeamMgr.getTargetPools().entrySet().iterator();
		while(it.hasNext()){
			Entry<Integer,TeamMatchPool> entry = it.next();
			int tar = entry.getKey();
			if(TeamTargetTempMgr.get(tar)!=null){
				int min = TeamTargetTempMgr.get(tar).getLevLimitMin();
				int max = TeamTargetTempMgr.get(tar).getLevLimitMax();
				if(player.getBasePlayer().getPlayerInfo().getLevel()>=min && player.getBasePlayer().getPlayerInfo().getLevel()<=max){
					totalList.addAll(entry.getValue().getClonePools());
				}
			}			
		}
		send(player,totalList.subList(0, Math.min(totalList.size(), 20)));
	}
	
	
	/**
	 * 发送
	 * @param player
	 */
	private void send(GamePlayer player,List<Integer> pool){
		
		GetTeamListRespMsg.Builder resp = GetTeamListRespMsg.newBuilder();
		if(pool!=null){
			for (int id : pool) {
				Team t = TeamMgr.getAllTeams().get(id);
				resp.addTeams(t.getTeamMsg());
			}
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_ERSP_GET_TEAM_LIST, resp);
		player.sendPbMessage(pkg);
	}

}
