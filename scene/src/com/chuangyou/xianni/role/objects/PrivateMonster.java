package com.chuangyou.xianni.role.objects;

import com.chuangyou.common.protobuf.pb.PlayerLeaveGridProto.PlayerLeaveGridMsg;
import com.chuangyou.xianni.drop.manager.DropManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.PrivateMonsterMgr;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class PrivateMonster extends Monster {
	private long	creater;
	private long	bornTime;
	private long	expiredTime;

	public PrivateMonster(long creater, int liveTime) {
		super(null);
		this.creater = creater;
		PrivateMonsterMgr.add(this);
		bornTime = System.currentTimeMillis();
		expiredTime = System.currentTimeMillis() + liveTime;

	}

	public boolean canSee(long id) {
		if (id == creater) {
			return true;
		}
		// 判断与创建人，是否属于同一个小组
		Team team = TeamMgr.getTeam(creater);
		if (team == null) {
			return false;
		}
		return team.inTeam(id);
	}

	// 移出视野
	public void disappear(long playerId) {
		ArmyProxy army = WorldMgr.getArmy(playerId);
		if (army != null) {
			PlayerLeaveGridMsg.Builder leaveMsg = PlayerLeaveGridMsg.newBuilder();
			leaveMsg.setId(id);
			PBMessage leavepkg = MessageUtil.buildMessage(Protocol.U_LEAVE_GRID, leaveMsg);
			army.sendPbMessage(leavepkg);
		}
	}

	// 进入视野
	public void appear(long playerId) {
		ArmyProxy army = WorldMgr.getArmy(playerId);
		if (army != null) {
			army.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_ATT_SNAP, getAttSnapMsg()));
		}
	}

	public long getCreater() {
		return creater;
	}

	public void onDie(Living killer) {
		synchronized (dieLock) {
			if (this.livingState == DIE) {
				return;
			}
			this.livingState = DIE;
		}
		clearWorkBuffer();
		// sendChangeStatuMsg(LIVING, livingState);死亡状态不推，客户端自己判断
		dieTime = System.currentTimeMillis();
		System.err.println("living :" + this.armyId + " is die");
		if (node != null) {
			node.lvingDie(this);
		}
		DropManager.dropFromMonster(this.getSkin(), killer.getArmyId(), this.getId(), this.getField().id, this.getPostion());
		notifyCenter(this.getSkin(), killer.getArmyId());
		this.clear();
		PrivateMonsterMgr.remove(this);
	}

	public long getBornTime() {
		return bornTime;
	}

	// 是否过期
	public boolean expired() {
		return System.currentTimeMillis() > expiredTime;
	}
}
