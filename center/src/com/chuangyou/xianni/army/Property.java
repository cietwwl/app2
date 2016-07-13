package com.chuangyou.xianni.army;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;

public class Property {
	private String	name;				// 属性名字
	private int		tempData;			// 模板数据加成
	private int		tempPer;			// 模板万分比加成

	private int		bagData;			// 背包数据加成
	private int		bagPer;				// 背包万分比加成

	private int		skillData	= 0;	// 技能数据加成
	private int		skillPer	= 0;	// 技能百分比加成

	private int		mountData	= 0;	// 坐骑数据加成
	private int		mountPer	= 0;	// 坐骑百分比加成

	private int		magicwpData	= 0;	// 法宝属性加成
	private int		magicwpPer	= 0;	// 法宝百分比加成

	private int		petData		= 0;	// 宠物属性加成
	private int		petPer		= 0;	// 宠物百分比加成

	private int		totalJoin;			// 总属性值
	private int		baseJoin;			// 基础值

	private boolean	isChange;			// 是否有改变

	public Property(String name) {
		this.name = name;
	}

	/** 清理背包属性 */
	public void clearBag() {
		this.bagData = 0;
		this.bagPer = 0;
		setChange(true);
	}

	private void fresh() {
		// 计算总的属性

		int totalData = tempData + bagData + skillData + mountData + magicwpData + petData;
		int totalPer = tempPer + bagPer + skillPer + mountPer + magicwpPer + petPer;
		totalJoin = totalData + totalData * totalPer / 100;

		// 计算基础属性
		baseJoin = (tempData) + (tempData) * (tempPer) / 10000;
		setTotalJoin(totalJoin);
		isChange = false;
	}

	public int getTempData() {
		return tempData;
	}

	public void setTempData(int tempData) {
		if (tempData != this.tempData)
			setChange(true);
		this.tempData = tempData;
	}

	public int getTempPer() {
		return tempPer;
	}

	public void setTempPer(int tempPer) {
		if (tempPer != this.tempPer)
			setChange(true);
		this.tempPer = tempPer;
	}

	public int getBagData() {
		return bagData;
	}

	public void setBagData(int bagData) {
		if (bagData != this.bagData){
			setChange(true);
		}
		this.bagData = bagData;
	}

	public int getBagPer() {
		return bagPer;
	}

	public void setBagPer(int bagPer) {
		if (bagPer != this.bagPer)
			setChange(true);
		this.bagPer = bagPer;
	}

	public int getSkillData() {
		return skillData;
	}

	public void setSkillData(int skillData) {
		if (skillData != this.skillData)
			setChange(true);
		this.skillData = skillData;
	}

	public int getSkillPer() {
		return skillPer;
	}

	public void setSkillPer(int skillPer) {
		if (skillPer != this.skillPer)
			setChange(true);
		this.skillPer = skillPer;
	}

	public int getMountData() {
		return mountData;
	}

	public void setMountData(int mountData) {
		if (mountData != this.mountData)
			setChange(true);
		this.mountData = mountData;
	}

	public int getMountPer() {
		return mountPer;
	}

	public void setMountPer(int mountPer) {
		if (mountPer != this.mountPer)
			setChange(true);
		this.mountPer = mountPer;
	}

	public int getMagicwpData() {
		return magicwpData;
	}

	public void setMagicwpData(int magicwpData) {
		if (magicwpData != this.magicwpData)
			setChange(true);
		this.magicwpData = magicwpData;
	}

	public int getMagicwpPer() {
		return magicwpPer;
	}

	public void setMagicwpPer(int magicwpPer) {
		if (magicwpPer != this.magicwpPer)
			setChange(true);
		this.magicwpPer = magicwpPer;
	}

	public int getPetData() {
		return petData;
	}

	public void setPetData(int petData) {
		if (petData != this.petData)
			setChange(true);
		this.petData = petData;
	}

	public int getPetPer() {
		return petPer;
	}

	public void setPetPer(int petPer) {
		if (petPer != this.petPer)
			setChange(true);
		this.petPer = petPer;
	}

	public int getTotalJoin() {
		if (isChange) {
			fresh();
		}
		return totalJoin;
	}

	public void setTotalJoin(int totalJoin) {
		setChange(true);
		this.totalJoin = totalJoin;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public int getBaseJoin() {
		return baseJoin;
	}

	public void setBaseJoin(int baseJoin) {
		this.baseJoin = baseJoin;
	}

	public void writeProto(PropertyMsg.Builder builder) {
		builder.setTotalPoint(getTotalJoin());
		builder.setBasePoint(getBaseJoin());
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return String.format("模板直接加成%s,模板百分比%s,", tempData, tempPer) + String.format("背包直接加成%s,背包百分比%s,", bagData, bagPer);
	}
}
