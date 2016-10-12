package com.chuangyou.xianni.entity.player;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.player.PlayerTimeMsgProto.PlayerTimeMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

/**
 * 玩家时间、硽码相关信息
 * 
 * @author Gxf
 *
 */
public class PlayerTimeInfo extends DataObject {


	private long	playerId;
	private int		sigleCampCount;				// 每日单人副本次数
	private int		challengeCampCount;			// 挑战副本次数
	private int 	personalTruckerProtCount;	// 个人运镖·护镖经验每日获得次数上限
	private int 	presonalTruckerExtReward;	// 镖师技能· 为他人护镖获得额外经验，1天最多获得一次
	private int 	presonalTruckerExtExp;		// 镖师技能· 为他人护镖获得额外奖励，1天最多获得一次
	private int 	addExpByTruckBroken;		// 镖师技能· 被劫镖时可直接获得镖师经验，1天最多获得一次
	private Date	resetTime;			// 重置时间
	private Date	offlineTime;		// 离线时间

	public PlayerTimeInfo() {
		resetTime = new Date();
	}

	public void reset() {
		setSigleCampCount(0);
		setChallengeCampCount(0);
		setPersonalTruckerProtCount(0);
		resetTime = new Date();
	}

	public int getSigleCampCount() {
		return sigleCampCount;
	}

	public void setSigleCampCount(int sigleCampCount) {
		setOp(Option.Update);
		this.sigleCampCount = sigleCampCount;
	}

	public Date getResetTime() {
		return resetTime;
	}

	public int getChallengeCampCount() {
		return challengeCampCount;
	}

	public void setChallengeCampCount(int challengeCampCount) {
		setOp(Option.Update);
		this.challengeCampCount = challengeCampCount;
	}

	public void setResetTime(Date resetTime) {
		setOp(Option.Update);
		this.resetTime = resetTime;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		setOp(Option.Update);
		this.offlineTime = offlineTime;
	}

	public void writeProto(PlayerTimeMsg.Builder builder) {
		builder.setChallengeCampCount(challengeCampCount);
		builder.setSigleCampCount(sigleCampCount);
	}

	public int getPersonalTruckerProtCount() {
		return personalTruckerProtCount;
	}

	public void setPersonalTruckerProtCount(int personalTruckerProtCount) {
		setOp(Option.Update);
		this.personalTruckerProtCount = personalTruckerProtCount;
	}

	public int getPresonalTruckerExtReward() {
		return presonalTruckerExtReward;
	}

	public void setPresonalTruckerExtReward(int presonalTruckerExtReward) {
		setOp(Option.Update);
		this.presonalTruckerExtReward = presonalTruckerExtReward;
	}

	public int getPresonalTruckerExtExp() {
		return presonalTruckerExtExp;
	}

	public void setPresonalTruckerExtExp(int presonalTruckerExtExp) {
		setOp(Option.Update);
		this.presonalTruckerExtExp = presonalTruckerExtExp;
	}

	public int getAddExpByTruckBroken() {
		return addExpByTruckBroken;
	}

	public void setAddExpByTruckBroken(int addExpByTruckBroken) {
		setOp(Option.Update);
		this.addExpByTruckBroken = addExpByTruckBroken;
	}
}
