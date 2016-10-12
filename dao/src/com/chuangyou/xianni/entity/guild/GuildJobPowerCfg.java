package com.chuangyou.xianni.entity.guild;

public class GuildJobPowerCfg {
	private int job;//职位(1掌门 2 副掌门 3 长老 4 护法 5 精英 6 普通)
	private byte joinActivity;//参加活动
	private byte openActivity; // tinyint(4) DEFAULT NULL COMMENT 开启活动,
	private byte blessMember; // tinyint(4) DEFAULT NULL COMMENT 祝福门派成员,
	private byte enterGuildMap; // tinyint(4) DEFAULT NULL COMMENT 进入门派地图,
	private byte applyHandle; // tinyint(4) DEFAULT NULL COMMENT 申请审核,
	private byte massEmail; // tinyint(4) DEFAULT NULL COMMENT 群发邮件,
	private byte joinConditionSet; // tinyint(4) DEFAULT NULL COMMENT 申请设置,
	private byte writeNotice; // tinyint(4) DEFAULT NULL COMMENT 发布公告,
	private byte appointJob; // tinyint(4) DEFAULT NULL COMMENT 成员任命,
	private byte exit; // tinyint(4) DEFAULT NULL COMMENT 退出门派,
	private byte removeMember; // tinyint(4) DEFAULT NULL COMMENT 踢出门派,
	private byte giveLeader; // tinyint(4) DEFAULT NULL COMMENT 转让掌门,
	private byte replaceLeader; // tinyint(4) DEFAULT NULL COMMENT 取代掌门,
	private byte useWarehouse; // tinyint(4) DEFAULT NULL COMMENT 分配仓库物品,
	private byte buildLevelup; // tinyint(4) DEFAULT NULL COMMENT 建筑升级,
	private int jobCount; // int(11) DEFAULT NULL COMMENT 任命数,
	public int getJob() {
		return job;
	}
	public void setJob(int job) {
		this.job = job;
	}
	public byte getJoinActivity() {
		return joinActivity;
	}
	public void setJoinActivity(byte joinActivity) {
		this.joinActivity = joinActivity;
	}
	public byte getOpenActivity() {
		return openActivity;
	}
	public void setOpenActivity(byte openActivity) {
		this.openActivity = openActivity;
	}
	public byte getBlessMember() {
		return blessMember;
	}
	public void setBlessMember(byte blessMember) {
		this.blessMember = blessMember;
	}
	public byte getEnterGuildMap() {
		return enterGuildMap;
	}
	public void setEnterGuildMap(byte enterGuildMap) {
		this.enterGuildMap = enterGuildMap;
	}
	public byte getApplyHandle() {
		return applyHandle;
	}
	public void setApplyHandle(byte applyHandle) {
		this.applyHandle = applyHandle;
	}
	public byte getMassEmail() {
		return massEmail;
	}
	public void setMassEmail(byte massEmail) {
		this.massEmail = massEmail;
	}
	public byte getJoinConditionSet() {
		return joinConditionSet;
	}
	public void setJoinConditionSet(byte joinConditionSet) {
		this.joinConditionSet = joinConditionSet;
	}
	public byte getWriteNotice() {
		return writeNotice;
	}
	public void setWriteNotice(byte writeNotice) {
		this.writeNotice = writeNotice;
	}
	public byte getAppointJob() {
		return appointJob;
	}
	public void setAppointJob(byte appointJob) {
		this.appointJob = appointJob;
	}
	public byte getExit() {
		return exit;
	}
	public void setExit(byte exit) {
		this.exit = exit;
	}
	public byte getRemoveMember() {
		return removeMember;
	}
	public void setRemoveMember(byte removeMember) {
		this.removeMember = removeMember;
	}
	public byte getGiveLeader() {
		return giveLeader;
	}
	public void setGiveLeader(byte giveLeader) {
		this.giveLeader = giveLeader;
	}
	public byte getReplaceLeader() {
		return replaceLeader;
	}
	public void setReplaceLeader(byte replaceLeader) {
		this.replaceLeader = replaceLeader;
	}
	public byte getUseWarehouse() {
		return useWarehouse;
	}
	public void setUseWarehouse(byte useWarehouse) {
		this.useWarehouse = useWarehouse;
	}
	public byte getBuildLevelup() {
		return buildLevelup;
	}
	public void setBuildLevelup(byte buildLevelup) {
		this.buildLevelup = buildLevelup;
	}
	public int getJobCount() {
		return jobCount;
	}
	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}
}
