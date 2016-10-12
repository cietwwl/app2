package com.chuangyou.xianni.entity.guild;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class GuildMemberInfo extends DataObject {

	/** 玩家ID */
	private long playerId;
	/** 门派ID */
	private int guildId;
	/** 职位 */
	private int job;
	/** 总贡献 */
	private long contributionTotal;
	/** 可用贡献 */
	private long contribution;
	/** 运势 */
	private int fortune;
	
	/** 特效消耗 */
	private long consumeSupply;
	
	/** 夺权成功时间 */
	private long lastSeizeWinTime = 0;
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getGuildId() {
		return guildId;
	}
	public void setGuildId(int guildId) {
		this.setOp(Option.Update);
		this.guildId = guildId;
	}
	public int getJob() {
		return job;
	}
	public void setJob(int job) {
		this.setOp(Option.Update);
		this.job = job;
	}
	public long getContributionTotal() {
		return contributionTotal;
	}
	public void setContributionTotal(long contributionTotal) {
		this.setOp(Option.Update);
		this.contributionTotal = contributionTotal;
	}
	public long getContribution() {
		return contribution;
	}
	public void setContribution(long contribution) {
		this.setOp(Option.Update);
		this.contribution = contribution;
	}
	public int getFortune() {
		return fortune;
	}
	public void setFortune(int fortune) {
		this.setOp(Option.Update);
		this.fortune = fortune;
	}
	
	public long getConsumeSupply() {
		return consumeSupply;
	}
	public void setConsumeSupply(long consumeSupply) {
		this.setOp(Option.Update);
		this.consumeSupply = consumeSupply;
	}
	public boolean addContribution(long addValue){
		if(addValue <= 0) return false;
		this.setContribution(this.getContribution() + addValue);
		this.setContributionTotal(this.getContributionTotal() + addValue);
		return true;
	}
	
	public boolean consumeContribution(long value){
		if(value < 0) return false;
		if(value > this.getContribution()) return false;
		this.setContribution(this.getContribution() - value);
		return true;
	}
	public long getLastSeizeWinTime() {
		return lastSeizeWinTime;
	}
	public void setLastSeizeWinTime(long lastSeizeWinTime) {
		this.lastSeizeWinTime = lastSeizeWinTime;
	}
}
