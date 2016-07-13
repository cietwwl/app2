package com.chuangyou.xianni.team.struct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.team.TeamInfoRespProto.TeamInfoRespMsg;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.event.TeamEvent;
import com.chuangyou.xianni.team.event.TeamMemberEvent;
import com.chuangyou.xianni.team.pool.ApplyToTeamPool;

/**
 * 队伍结构
 * @author laofan
 *
 */
public class Team extends AbstractEvent{

	/**
	 * 队伍ID
	 */
	private int id;
	
	/**
	 * 队伍成员
	 */
	private List<TeamMember> members = new LinkedList<TeamMember>();
	
	/**
	 * 队长
	 */
	private TeamMember leader;
	
	/**
	 * 申请池
	 */
	private ApplyToTeamPool applyPools = new ApplyToTeamPool();
	
	/**
	 * 队伍目标ID
	 */
	private int targetId = 0;

	
	public Team(int id,long playerId,int targetId) {
		this.id = id;
		this.targetId = targetId;
		TeamMember tm = new TeamMember(id, playerId);
		this.members.add(tm);
		this.leader = tm;
	}
	
	/**
	 * 是否队伍已满
	 * @return
	 */
	public synchronized boolean isFull(){
		if(members.size()>=TeamMgr.TEAM_MAX)return true;
		return false;
	}
	
	/**
	 * 获取队伍人数
	 * @return
	 */
	public synchronized int getMemberSize(){
		return members.size();
	}
	/**
	 * 是否已经都离线
	 * @return
	 */
	public synchronized boolean isALLOffline(){
		if(members.isEmpty())return true;
		for (TeamMember teamMember : members) {
			if(teamMember.isOnline())return false;
		}
		return true;
	}
	
	/**
	 * 是否队伍已空
	 * @return
	 */
	public synchronized boolean isEmpty(){
		return members.isEmpty();
	}
	
	
	/**
	 * 添加队伍成员
	 * @param playerId
	 * @return ：添加失败返回null
	 */
	public synchronized TeamMember addMember(long playerId){
		if(isFull())return null;
		if(applyPools.getPools().contains(playerId)){
			applyPools.getPools().remove(playerId);
		}
		TeamMember tm = new TeamMember(id, playerId);
		tm.setOnline(true);
		members.add(tm);
		TeamMgr.getPlayerTeamMap().put(tm.getPlayerId(), tm);
		this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_ADD_MEMBER,tm));
		if(isFull()){
			this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_FULL));
		}
		return tm;
	}
	
	
	/**
	 * 删除成员
	 * @param playerId
	 * @return：失败返回null
	 */
	public synchronized TeamMember removeMember(TeamMember member){
		if(!members.contains(member)){
			return null;
		}
		TeamMgr.getPlayerTeamMap().remove(member.getPlayerId());
		members.remove(member);
		this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_REMOVE_MEMBER,member));
		if(this.isEmpty()){
			this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_EMPTY));
		}else{
			if(this.isALLOffline()){
				this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_ALLOFFLINE));
			}
		}		
		return member;
	}
	
	
	/**
	 * 改变队长
	 * @param playerId
	 * @return ： 成功返回新队长  失败返回null
	 */
	public synchronized TeamMember changeLeader(TeamMember newLeader){
		if(newLeader == leader) return null;
		if(!members.contains(newLeader))return null;
		this.leader = newLeader;
		this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_LEADER_CHANGE,newLeader));
		return newLeader;
	}
	
	/**
	 * 改变队员在线离线状态
	 * @param isOnline
	 * @param member
	 */
	public synchronized void synchronizedchangeLine(boolean isOnline,TeamMember member){
		if(members.contains(member)){
			member.setOnline(isOnline);
			if(isALLOffline()){
				this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_ALLOFFLINE));
			}else{
				this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_CHNAGE_ONLINE,member));
			}
		}
	}
	
	/**
	 * 添加申请人员
	 * @param playerId
	 * @return 成功返回true
	 */
	public boolean addApplyMember(long playerId){
		applyPools.addMember(playerId);
		return true;
	}
	
	/**
	 * 删除申请人员
	 * @param playerId
	 * @return 成功返回true
	 */
	public boolean removeApplyMember(long playerId){
		applyPools.removeMember(playerId);
		return true;
	}
	
	public void clear(){
		members.clear();
		members =null;
		leader = null;
		applyPools.getPools().clear();
		applyPools =null;
	}
	
	/**
	 *  获取队伍信息包
	 * @param t
	 * @return
	 */
	public TeamInfoRespMsg.Builder getTeamMsg(){
		TeamInfoRespMsg.Builder msg = TeamInfoRespMsg.newBuilder();
		msg.setLeaderPlayerId(this.getLeader().getPlayerId());
		msg.setTeamId(this.getId());
		msg.setTargetId(this.getTargetId());
		for (TeamMember member : this.getMembers()) {
			msg.addInfos(member.getTeamMemberMsg());
		}
		return msg;
	}
	

	/**
	 * 获取队友ID列表
	 * @param exp:除外
	 * @return
	 */
	public List<Long> getPlayers(long exp){
		List<Long> list= new ArrayList<>();
		for (TeamMember teamMember : members) {
			if(teamMember.getPlayerId()!=exp){
				list.add(teamMember.getPlayerId());
			}
		}
		return list;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TeamMember> getMembers() {
		return members;
	}

	public void setMembers(List<TeamMember> members) {
		this.members = members;
	}

	
	public ApplyToTeamPool getApplyPools() {
		return applyPools;
	}

	public TeamMember getLeader() {
		return leader;
	}

	public void setLeader(TeamMember leader) {
		this.leader = leader;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

}
