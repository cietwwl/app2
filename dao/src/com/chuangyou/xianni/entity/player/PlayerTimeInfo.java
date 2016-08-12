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
	private int		sigleCampCount;		// 每日单人副本次数
	private int		challengeCampCount;	// 挑战副本次数
	private Date	resetTime;			// 重置时间

	public PlayerTimeInfo() {
		resetTime = new Date();
	}

	public void reset() {
		setSigleCampCount(0);
		setChallengeCampCount(0);
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

	public void writeProto(PlayerTimeMsg.Builder builder) {
		builder.addChallengeCampCount(challengeCampCount);
		builder.addSigleCampCount(sigleCampCount);
	}
}
