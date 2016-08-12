package com.chuangyou.xianni.entity.equip;

import com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class EquipBarInfo extends DataObject {

	/**
	 * 玩家ID
	 */
	private long playerId;
	/**
	 * 装备栏位索引
	 */
	private short position;
	/**
	 * 装备栏等级
	 */
	private int level;
	/**
	 * 当前等级经验
	 */
	private int exp;
	/**
	 * 加持等级
	 */
	private int grade;
	/**
	 * 当前加持等级祝福值
	 */
	private int bless;
	
	public EquipBarInfo() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 创建初始化
	 * @param playerId
	 * @param position
	 */
	public EquipBarInfo(long playerId, short position) {
		this.playerId = playerId;
		this.position = position;
		this.level = 1;
		this.exp = 0;
		this.grade = 0;
		this.bless = 0;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public short getPosition() {
		return position;
	}
	public void setPosition(short position) {
		this.position = position;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		if(this.level != level){
			this.setOp(Option.Update);
			this.level = level;
		}
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		if(this.exp != exp){
			this.setOp(Option.Update);
			this.exp = exp;
		}
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		if(this.grade != grade){
			this.setOp(Option.Update);
			this.grade = grade;
		}
	}
	public int getBless() {
		return bless;
	}
	public void setBless(int bless) {
		if(this.bless != bless){
			this.setOp(Option.Update);
			this.bless = bless;
		}
	}
	
	public void writeProto(EquipBarInfoMsg.Builder proto){
		proto.setPosition(this.getPosition());
		proto.setLevel(this.getLevel());
		proto.setExp(this.getExp());
		proto.setGrade(this.getGrade());
		proto.setBless(this.getBless());
	}
}
