package com.chuangyou.xianni.entity.arena;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.arena.ArenaInfoMsgProto.ArenaInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class ArenaInfo extends DataObject {
	private long	playerId;		// 玩家ID
	private int		challengeCount;	// 挑战次数
	private int		winCount;		// 胜利次数
	private Date	freshTime;		// 刷新时间
	private int		reward1;		// 是否领取1胜奖励
	private int		reward4;		// 是否领取4胜奖励
	private int		reward6;		// 是否领取7胜奖励
	private long	opponenter1;	// 对手1ID
	private int		result1;		// 挑战结果
	private long	opponenter2;	// 对手2ID
	private int		result2;		// 挑战结果
	private long	opponenter3;	// 对手3ID
	private int		result3;		// 挑战结果
	private long	opponenter4;	// 对手4ID
	private int		result4;		// 挑战结果
	private long	opponenter5;	// 对手5ID
	private int		result5;		// 挑战结果
	private long	opponenter6;	// 对手6ID
	private int		result6;		// 挑战结果

	public void writeProto(ArenaInfoMsg.Builder builder) {
		builder.setPlayerId(playerId);
		builder.setChallengeCount(challengeCount);
		builder.setWinCount(winCount);
		builder.setFreshTime(freshTime.getTime());
		builder.setReward1(reward1);
		builder.setReward4(reward4);
		builder.setReward6(reward6);
	}

	public void reset() {
		challengeCount = 0;
		winCount = 0;
		freshTime = new Date();
		reward1 = 0;
		reward4 = 0;
		reward6 = 0;

	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getChallengeCount() {
		return challengeCount;
	}

	public void setChallengeCount(int challengeCount) {
		this.challengeCount = challengeCount;
		setOp(Option.Update);
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
		setOp(Option.Update);
	}

	public Date getFreshTime() {
		return freshTime;
	}

	public void setFreshTime(Date freshTime) {
		this.freshTime = freshTime;
		setOp(Option.Update);
	}

	public long getOpponenter1() {
		return opponenter1;
	}

	public void setOpponenter1(long opponenter1) {
		this.opponenter1 = opponenter1;
		setOp(Option.Update);
	}

	public int getResult1() {
		return result1;
	}

	public void setResult1(int result1) {
		this.result1 = result1;
		setOp(Option.Update);
	}

	public long getOpponenter2() {
		return opponenter2;
	}

	public void setOpponenter2(long opponenter2) {
		this.opponenter2 = opponenter2;
		setOp(Option.Update);
	}

	public int getResult2() {
		return result2;
	}

	public void setResult2(int result2) {
		this.result2 = result2;
		setOp(Option.Update);
	}

	public long getOpponenter3() {
		return opponenter3;
	}

	public void setOpponenter3(long opponenter3) {
		this.opponenter3 = opponenter3;
		setOp(Option.Update);
	}

	public int getResult3() {
		return result3;
	}

	public void setResult3(int result3) {
		this.result3 = result3;
		setOp(Option.Update);
	}

	public long getOpponenter4() {
		return opponenter4;
	}

	public void setOpponenter4(long opponenter4) {
		this.opponenter4 = opponenter4;
		setOp(Option.Update);
	}

	public int getResult4() {
		return result4;
	}

	public void setResult4(int result4) {
		this.result4 = result4;
		setOp(Option.Update);
	}

	public long getOpponenter5() {
		return opponenter5;
	}

	public void setOpponenter5(long opponenter5) {
		this.opponenter5 = opponenter5;
		setOp(Option.Update);
	}

	public int getResult5() {
		return result5;
	}

	public void setResult5(int result5) {
		this.result5 = result5;
		setOp(Option.Update);
	}

	public long getOpponenter6() {
		return opponenter6;
	}

	public void setOpponenter6(long opponenter6) {
		this.opponenter6 = opponenter6;
		setOp(Option.Update);
	}

	public int getResult6() {
		return result6;
	}

	public void setResult6(int result6) {
		this.result6 = result6;
		setOp(Option.Update);
	}

	public int getReward1() {
		return reward1;
	}

	public void setReward1(int reward1) {
		this.reward1 = reward1;
		setOp(Option.Update);
	}

	public int getReward4() {
		return reward4;
	}

	public void setReward4(int reward4) {
		this.reward4 = reward4;
		setOp(Option.Update);
	}

	public int getReward6() {
		return reward6;
	}

	public void setReward6(int reward6) {
		this.reward6 = reward6;
		setOp(Option.Update);
	}

}
