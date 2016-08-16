package com.chuangyou.xianni.entity.skill;

/** 陷阱，召唤物信息 */
public class SnareTemplateInfo {
	private int		templateId;		// id
	private int		skinId;			// 模型ID
	private int		type;			// 陷阱类型
	private int		lifetime;		// 陷阱生命周期
	private int		validCount;		// 陷阱生效次数
	private int		exeWay;			// 生效时机 1 进入时 2对陷阱对象定时执行 3 对象离开时执行
	private int		coolDown;		// 生效间隔
	private int		checkX;			// 校验范围X
	private int		checkZ;			// 校验范围Z
	private int		hp;				// 血量
	private int		target;			// 作用目标 0 敌方 1 友方 2 敌方怪物 3 敌方玩家

	private int		bornType;		// 出生模式 1 在自身位置 2 在目标位置（无目标是在相对坐标点生成
									// ）3在相对坐标点生成(相对坐标点不在有效区域时,在自身与目标点连成直线上,距离目标点最近的点)
	private int		bornAngle;		// 出生角度
	private int		bornlength;		// 距离

	private int		moveType;		// 移动模式 1 不动 2 直线移动 3 跟踪移动 4 随机移动(在生成点)
	private int		lockingType;	// 锁定模式 1 不锁定目标 2 释放时释放者的目标 3 攻击到的第一个目标 4
									// 攻击陷阱的目标

	private String	addBuffers;		// 陷阱执行时，给执行对象添加 buffer ，用逗号分隔
	private int[]	arrBufferIds;	// buffer集合
	private int		stateId;		// 作用状态ID
	private int		soulPercent;	// 元魂万分比
	private int		soulValue;		// 元魂固定值

	private int		bloodPercent;	// 气血万分比
	private int		bloodValue;		// 气血固定值

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public int getSkinId() {
		return skinId;
	}

	public void setSkinId(int skinId) {
		this.skinId = skinId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLifetime() {
		return lifetime;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	public int getValidCount() {
		return validCount  + 10;
	}

	public void setValidCount(int validCount) {
		this.validCount = validCount;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	public int getCheckX() {
		return checkX;
	}

	public void setCheckX(int checkX) {
		this.checkX = checkX;
	}

	public int getCheckZ() {
		return checkZ;
	}

	public void setCheckZ(int checkZ) {
		this.checkZ = checkZ;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getBornType() {
		return bornType;
	}

	public void setBornType(int bornType) {
		this.bornType = bornType;
	}

	public int getMoveType() {
		return moveType;
	}

	public void setMoveType(int moveType) {
		this.moveType = moveType;
	}

	public int getLockingType() {
		return lockingType;
	}

	public void setLockingType(int lockingType) {
		this.lockingType = lockingType;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getSoulPercent() {
		return soulPercent;
	}

	public void setSoulPercent(int soulPercent) {
		this.soulPercent = soulPercent;
	}

	public int getSoulValue() {
		return soulValue;
	}

	public void setSoulValue(int soulValue) {
		this.soulValue = soulValue;
	}

	public int getBloodPercent() {
		return bloodPercent;
	}

	public void setBloodPercent(int bloodPercent) {
		this.bloodPercent = bloodPercent;
	}

	public int getBloodValue() {
		return bloodValue;
	}

	public void setBloodValue(int bloodValue) {
		this.bloodValue = bloodValue;
	}

	public int getExeWay() {
		return exeWay;
	}

	public void setExeWay(int exeWay) {
		this.exeWay = exeWay;
	}

	public String getAddBuffers() {
		return addBuffers;
	}

	public void setAddBuffers(String addBuffers) {
		this.addBuffers = addBuffers;
		if (addBuffers != null && !addBuffers.equals("")) {
			String[] bids = addBuffers.split(",");
			arrBufferIds = new int[bids.length];
			for (int i = 0; i < bids.length; i++) {
				if (bids[i].isEmpty())
					continue;
				arrBufferIds[i] = Integer.valueOf(bids[i]);
			}
		}
	}

	public int[] getArrBufferIds() {
		return arrBufferIds;
	}

	public void setArrBufferIds(int[] arrBufferIds) {
		this.arrBufferIds = arrBufferIds;
	}

	public int getBornAngle() {
		return bornAngle;
	}

	public void setBornAngle(int bornAngle) {
		this.bornAngle = bornAngle;
	}

	public int getBornlength() {
		return bornlength;
	}

	public void setBornlength(int bornlength) {
		this.bornlength = bornlength;
	}

}
