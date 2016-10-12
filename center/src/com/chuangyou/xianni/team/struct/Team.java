package com.chuangyou.xianni.team.struct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.campaign.CampaignOptionMsgProto.CampaignOptionMsg;
import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignMsgProto.CreateCampaignMsg;
import com.chuangyou.common.protobuf.pb.team.TeamInfoRespProto.TeamInfoRespMsg;
import com.chuangyou.xianni.campaign.action.CreateCampaignDelayAction;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.entity.team.TeamTargetTemplate;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.TeamTargetTempMgr;
import com.chuangyou.xianni.team.event.TeamEvent;
import com.chuangyou.xianni.team.event.TeamMemberEvent;
import com.chuangyou.xianni.team.pool.ApplyToTeamPool;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 队伍结构
 * 
 * @author laofan
 *
 */
public class Team extends AbstractEvent implements Comparable<Team> {

	/**
	 * 队伍ID
	 */
	private int					id;

	/**
	 * 队伍成员
	 */
	private List<TeamMember>	members		= new LinkedList<TeamMember>();

	/**
	 * 队长
	 */
	private TeamMember			leader;

	/**
	 * 申请池
	 */
	private ApplyToTeamPool		applyPools	= new ApplyToTeamPool();

	/**
	 * 队伍目标ID
	 */
	private int					targetId	= 0;

	/**
	 * 队伍状态 正常—准备—待进入(倒计时3秒)—进入目标
	 **/
	private int					teamStatu;

	/** 正常 */
	public static int			NORMAL		= 0;
	/** 准备 */
	public static int			PREPARE		= 1;
	/** 待进入 */
	public static int			GOING		= 2;
	/** 进入 */
	public static int			GET_IN		= 3;

	public Team(int id, long playerId, int targetId) {
		this.id = id;
		this.targetId = targetId;
		TeamMember tm = new TeamMember(id, playerId);
		this.members.add(tm);
		this.leader = tm;
	}

	/**
	 * 是否队伍已满
	 * 
	 * @return
	 */
	public boolean isFull() {
		if (members.size() >= TeamMgr.TEAM_MAX)
			return true;
		return false;
	}

	/**
	 * 获取队伍人数
	 * 
	 * @return
	 */
	public int getMemberSize() {
		return members.size();
	}

	/**
	 * 是否已经都离线
	 * 
	 * @return
	 */
	public boolean isALLOffline() {
		if (members.isEmpty())
			return true;
		for (TeamMember teamMember : members) {
			if (teamMember.isOnline())
				return false;
		}
		return true;
	}

	/**
	 * 是否队伍已空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return members.isEmpty();
	}

	/**
	 * 添加队伍成员
	 * 
	 * @param playerId
	 * @return ：添加失败返回null
	 */
	public TeamMember addMember(long playerId) {
		if (isFull())
			return null;
		if (applyPools.getPools().contains(playerId)) {
			applyPools.getPools().remove(playerId);
		}
		TeamMember tm = new TeamMember(id, playerId);
		tm.setOnline(true);
		members.add(tm);
		TeamMgr.getPlayerTeamMap().put(tm.getPlayerId(), tm);
		this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_ADD_MEMBER, tm));
		if (isFull()) {
			this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_FULL));
		}
		return tm;
	}

	/**
	 * 删除成员
	 * 
	 * @param playerId
	 * @return：失败返回null
	 */
	public TeamMember removeMember(TeamMember member) {
		if (!members.contains(member)) {
			return null;
		}
		TeamMgr.getPlayerTeamMap().remove(member.getPlayerId());
		members.remove(member);
		this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_REMOVE_MEMBER, member));
		if (this.isEmpty()) {
			this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_EMPTY));
		} else {
			if (this.isALLOffline()) {
				this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_ALLOFFLINE));
			}
		}
		return member;
	}

	/**
	 * 改变队长
	 * 
	 * @param playerId
	 * @return ： 成功返回新队长 失败返回null
	 */
	public TeamMember changeLeader(TeamMember newLeader) {
		if (newLeader == leader)
			return null;
		if (!members.contains(newLeader))
			return null;
		this.leader = newLeader;
		this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_LEADER_CHANGE, newLeader));
		return newLeader;
	}

	/**
	 * 改变队员在线离线状态
	 * 
	 * @param isOnline
	 * @param member
	 */
	public void changeLine(boolean isOnline, TeamMember member) {
		if (members.contains(member)) {
			member.setOnline(isOnline);
			if (isALLOffline()) {
				this.notifyListeners(new TeamEvent(this, this, EventNameType.TEAM_IS_ALLOFFLINE));
			} else {
				this.notifyListeners(new TeamMemberEvent(this, this, EventNameType.TEAM_CHNAGE_ONLINE, member));
			}
		}
	}

	/**
	 * 添加申请人员
	 * 
	 * @param playerId
	 * @return 成功返回true
	 */
	public boolean addApplyMember(long playerId) {
		applyPools.addMember(playerId);
		return true;
	}

	/**
	 * 删除申请人员
	 * 
	 * @param playerId
	 * @return 成功返回true
	 */
	public boolean removeApplyMember(long playerId) {
		applyPools.removeMember(playerId);
		return true;
	}

	public void clear() {
		members.clear();
		members = null;
		leader = null;
		applyPools.getPools().clear();
		applyPools = null;
	}

	/**
	 * 获取队伍信息包
	 * 
	 * @param t
	 * @return
	 */
	public TeamInfoRespMsg.Builder getTeamMsg() {
		TeamInfoRespMsg.Builder msg = TeamInfoRespMsg.newBuilder();
		msg.setLeaderPlayerId(this.getLeader().getPlayerId());
		msg.setTeamId(this.getId());
		msg.setTargetId(this.getTargetId());
		msg.setTeamStatu(this.getTeamStatu());
		for (TeamMember member : this.getMembers()) {
			msg.addInfos(member.getTeamMemberMsg());
		}
		return msg;
	}

	/**
	 * 获取队友ID列表
	 * 
	 * @param exp:除外
	 * @return
	 */
	public List<Long> getPlayers(long exp) {
		List<Long> list = new ArrayList<>();
		for (TeamMember teamMember : getMembers()) {
			if (teamMember.getPlayerId() != exp) {
				list.add(teamMember.getPlayerId());
			}
		}
		return list;
	}

	/** 切换状态 */
	public void changeTeamStatu(int statu) {
		this.teamStatu = statu;
		if (statu == Team.PREPARE) {
			changeMemberStatu(TeamMember.PREPARE, getLeader().getPlayerId());
		} else {
			if (statu == Team.NORMAL) {
				for (TeamMember member : getMembers()) {
					member.setStatu(TeamMember.DE_PREPARE);
				}
			}
			List<Long> playerIds = getPlayers(-1);
			TeamInfoRespMsg.Builder message = getTeamMsg();
			BroadcastUtil.sendBroadcastPacket(playerIds, Protocol.U_RESP_TEAM_INFO, message.build());
		}

	}

	/** 成员修改状态 */
	public void changeMemberStatu(int memberStatu, long playerId) {
		// 非此两种状态，不允许修改成员状态
		if (teamStatu != Team.PREPARE && teamStatu != Team.NORMAL) {
			return;
		}
		TeamMember member = getMember(playerId);
		member.setStatu(memberStatu);

		// 如果所有人准备好，就切换组队状态至待进入
		boolean ready = true;
		for (TeamMember m : getMembers()) {
			// 未准备
			if (m.getStatu() != TeamMember.PREPARE) {
				ready = false;
			}
		}
		if (ready) {
			this.teamStatu = GOING;
			TeamTargetTemplate target = TeamTargetTempMgr.get(targetId);
			
			if (target.getTargetType() == 2 && leader != null) {// 类型2 副本
				CreateCampaignDelayAction action = new CreateCampaignDelayAction(TeamMgr.getActionQueue(), this, target.getTarget(), 3000);
				TeamMgr.getActionQueue().getActionQueue().enDelayQueue(action);
			}
		}
		List<Long> playerIds = getPlayers(-1);
		TeamInfoRespMsg.Builder message = getTeamMsg();
		BroadcastUtil.sendBroadcastPacket(playerIds, Protocol.U_RESP_TEAM_INFO, message.build());

	}

	/** 前往副本目标 */
	public void goTarget(long playerId) {
		CampaignOptionMsg.Builder builder = CampaignOptionMsg.newBuilder();
		builder.setOptionType(CampaignConstant.JOIN_TEAM);
		builder.setParam1(id);
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player != null) {
			PBMessage message = MessageUtil.buildMessage(Protocol.S_CAMPAIGN_OPTION, builder);
			player.sendPbMessage(message);
		}
		TeamMember member = getMember(playerId);
		if (member != null) {
			member.setStatu(TeamMember.JOIN_TARGET);
			List<Long> playerIds = getPlayers(-1);
			TeamInfoRespMsg.Builder message = getTeamMsg();
			BroadcastUtil.sendBroadcastPacket(playerIds, Protocol.U_RESP_TEAM_INFO, message.build());
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TeamMember> getMembers() {

		List<TeamMember> list = new ArrayList<>();
		synchronized (members) {
			list.addAll(members);
		}
		return list;
	}

	public TeamMember getMember(long playerId) {
		for (TeamMember member : getMembers()) {
			if (playerId == member.getPlayerId()) {
				return member;
			}
		}
		return null;
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

	public int getTeamStatu() {
		return teamStatu;
	}

	public void setTeamStatu(int teamStatu) {
		this.teamStatu = teamStatu;
	}

	public void setApplyPools(ApplyToTeamPool applyPools) {
		this.applyPools = applyPools;
	}

	@Override
	public int compareTo(Team o) {
		// TODO Auto-generated method stub
		return this.getMemberSize() - o.getMemberSize();
	}

}
