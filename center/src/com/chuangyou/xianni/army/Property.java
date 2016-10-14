package com.chuangyou.xianni.army;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;

public class Property {
	private String	name;					// 属性名字
	private int		type;					// 属性类型
	private int		tempData;				// 模板数据加成
	private int		tempPer;				// 模板万分比加成

	private int		bagData;				// 背包数据加成
	private int		bagPer;					// 背包万分比加成

	private int		skillData		= 0;	// 技能数据加成
	private int		skillPer		= 0;	// 技能万分比加成

	private int		inverseBeadData	= 0;	// 天逆珠数据加成
	private int		inverseBeadper	= 0;	// 天逆珠万分比加成

	private int		mountData		= 0;	// 坐骑数据加成
	private int		mountPer		= 0;	// 坐骑万分比加成

	private int		magicwpData		= 0;	// 法宝属性加成
	private int		magicwpPer		= 0;	// 法宝万分比加成

	private int		fashionData		= 0;	// 时装属性加成
	private int		fashionPer		= 0;	// 时装万分比加成
	
	private int guildSkillData = 0; // 帮派技能加成
	private int guildSkillPer = 0; // 帮派技能万分比加成

	private int		soulData		= 0;	// 魂幡属性加成
	private int		soulPer			= 0;	// 魂幡万分比加成

	private int		artifactData	= 0;	// 神器属性加成
	private int		artifactPer		= 0;	// 神器万分比加成

	private int		petData			= 0;	// 宠物属性加成
	private int		petPer			= 0;	// 宠物万分比加成

	private int		stateData		= 0;	// 境界系统加成
	private int		statePer		= 0;	// 境界系统加成

	private int		avatarData		= 0;	// 分身系统加成
	private int		avatarPer		= 0;	// 分身系统加成

	private int		totalJoin;				// 总属性值
	private int		baseJoin;				// 基础值

	private boolean	isChange;				// 是否有改变
	private int		baseTotal;				// 总数的基数

	public Property(String name, int type) {
		this.name = name;
		this.type = type;
	}

	/** 清理背包属性 */
	public void clearBag() {
		this.bagData = 0;
		this.bagPer = 0;
		setChange(true);
	}

	private void fresh() {
		// 计算总的属性

		baseTotal = tempData + bagData + skillData + mountData + magicwpData + petData + fashionData + inverseBeadData + soulData + artifactData + stateData + guildSkillData + avatarData;
		int totalPer = tempPer + bagPer + skillPer + mountPer + magicwpPer + petPer + fashionPer + inverseBeadper + soulPer + artifactPer + statePer + guildSkillPer + avatarPer;

		totalJoin = baseTotal + baseTotal * totalPer / 10000;

		// 计算基础属性
		baseJoin = (tempData) + (tempData) * (tempPer) / 10000;
		setTotalJoin(totalJoin);
		isChange = false;
	}

	/**
	 * 背包加成属性
	 * 
	 * @return
	 */
	public int getBagTotal() {
		return bagData + (baseTotal * bagPer / 10000);
	}

	/**
	 * 坐骑加成属性
	 * 
	 * @return
	 */
	public int getMountTotal() {
		return mountData + (baseTotal * mountPer / 10000);
	}

	/**
	 * 法宝
	 * 
	 * @return
	 */
	public int getMagicwpTotal() {
		return magicwpData + (baseTotal * magicwpPer / 10000);
	}

	/**
	 * 魂幡
	 * 
	 * @return
	 */
	public int getSoulTotal() {
		return soulData + (baseTotal * soulPer / 10000);
	}

	/**
	 * 宠物
	 * 
	 * @return
	 */
	public int getPetTotal() {
		return petData + (baseTotal * petPer / 10000);
	}

	/**
	 * 境界
	 * 
	 * @return
	 */
	public int getStateTotal() {
		return stateData + (baseTotal * statePer / 10000);
	}
	
	/**
	 * 神器
	 * @return
	 */
	public int getArtifaceTotal(){
		return artifactData + (baseTotal * artifactPer / 10000);
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
		if (bagData != this.bagData) {
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

	public int getInverseBeadData() {
		return inverseBeadData;
	}

	public void setInverseBeadData(int inverseBeadData) {
		if (this.inverseBeadData != inverseBeadData)
			setChange(true);
		this.inverseBeadData = inverseBeadData;
	}

	public int getInverseBeadper() {
		return inverseBeadper;
	}

	public void setInverseBeadper(int inverseBeadper) {
		if (this.inverseBeadper != inverseBeadper)
			setChange(true);
		this.inverseBeadper = inverseBeadper;
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

	public int getFashionData() {
		return fashionData;
	}

	public void setFashionData(int fashionData) {
		if (fashionData != this.fashionData)
			setChange(true);
		this.fashionData = fashionData;
	}

	public int getFashionPer() {
		return fashionPer;
	}

	public void setFashionPer(int fashionPer) {
		if (fashionPer != this.fashionPer)
			setChange(true);
		this.fashionPer = fashionPer;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String toString() {
		return String.format("模板直接加成%s,模板百分比%s,", tempData, tempPer) + String.format("背包直接加成%s,背包百分比%s,", bagData, bagPer);
	}

	public int getSoulData() {
		return soulData;
	}

	public void setSoulData(int soulData) {
		if (this.soulData != soulData) {
			this.setChange(true);
			this.soulData = soulData;
		}
	}

	public int getSoulPer() {
		return soulPer;
	}

	public void setSoulPer(int soulPer) {
		if (this.soulPer != soulPer) {
			this.setChange(true);
			this.soulPer = soulPer;
		}
	}

	public int getArtifactData() {
		return artifactData;
	}

	public void setArtifactData(int artifactData) {
		if (artifactData != this.artifactData) {
			this.setChange(true);
		}
		this.artifactData = artifactData;
	}

	public int getArtifactPer() {
		return artifactPer;
	}

	public void setArtifactPer(int artifactPer) {
		if (artifactPer != this.artifactPer) {
			this.setChange(true);
		}
		this.artifactPer = artifactPer;
	}

	public int getStateData() {
		return stateData;
	}

	public void setStateData(int stateData) {
		if (this.stateData != stateData) {
			this.setChange(true);
			this.stateData = stateData;
		}
	}

	public int getStatePer() {
		return statePer;
	}

	public void setStatePer(int statePer) {
		if (this.statePer != statePer) {
			this.setChange(true);
			this.statePer = statePer;
		}
	}

	public int getGuildSkillData() {
		return guildSkillData;
	}

	public void setGuildSkillData(int guildSkillData) {
		if(guildSkillData != this.guildSkillData){
			this.setChange(true);
		}
		this.guildSkillData = guildSkillData;
	}

	public int getGuildSkillPer() {
		return guildSkillPer;
	}

	public void setGuildSkillPer(int guildSkillPer) {
		if(guildSkillPer != this.guildSkillPer){
			this.setChange(true);
		}
		this.guildSkillPer = guildSkillPer;
	}

	public int getAvatarData() {
		return avatarData;
	}

	public void setAvatarData(int avatarData) {
		if (this.avatarData != avatarData) {
			this.setChange(true);
			this.avatarData = avatarData;
		}
	}

	public int getAvatarPer() {
		return avatarPer;
	}

	public void setAvatarPer(int avatarPer) {
		if (this.avatarPer != avatarPer) {
			this.setChange(true);
			this.avatarPer = avatarPer;
		}
	}

}
