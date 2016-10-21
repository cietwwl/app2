package com.chuangyou.xianni.team.struct;

import com.chuangyou.common.protobuf.pb.team.TeamMemberInfoProto.TeamMemberInfoMsg;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

public class TeamMember {

	private long		playerId;
	private int			teamId;
	private int			statu;				// 准备状态 0 未准备，1 已准备
	/** 是否在线 */
	private boolean		isOnline	= true;

	public static int	DE_PREPARE	= 0;
	public static int	PREPARE		= 1;
	public static int	LATER		= 2;
	public static int	JOIN_TARGET	= 3;

	public TeamMember(int teamId, long playerId) {
		this.teamId = teamId;
		this.playerId = playerId;
	}

	public int getTeamId() {
		return teamId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	/**
	 * 队员信息
	 * 
	 * @param playerId
	 * @return
	 */
	public TeamMemberInfoMsg.Builder getTeamMemberMsg() {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		TeamMemberInfoMsg.Builder m = TeamMemberInfoMsg.newBuilder();
		m.setPlayerId(player.getPlayerId());
		m.setLevel(player.getBasePlayer().getPlayerInfo().getLevel());
		m.setFight(player.getBasePlayer().getPlayerInfo().getFight());
		m.setName(player.getBasePlayer().getPlayerInfo().getNickName());
		m.setSkinId(player.getBasePlayer().getPlayerInfo().getSkinId());
		m.setStatu(getStatu());
		m.setFashionId(player.getBasePlayer().getPlayerInfo().getFashionId());
		m.setWeapon(player.getBasePlayer().getPlayerInfo().getWeaponId());
		m.setWing(player.getBasePlayer().getPlayerInfo().getWingId());
		if (player.getPlayerState() == PlayerState.OFFLINE) {
			m.setIsOnline(false);
			m.setMapId(0);
			m.setMapKey(0);
		} else {
			m.setIsOnline(true);
			m.setMapId(player.getBasePlayer().getPlayerPositionInfo().getMapId());
			m.setMapKey(player.getBasePlayer().getPlayerPositionInfo().getMapTempId());
		}
		return m;
	}

}
