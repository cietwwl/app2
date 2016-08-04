package com.chuangyou.xianni.entity.skill;

/** 执行技能模板 */
public class SkillActionTemplateInfo {
	private int templateId; // 模板ID
	private int masterType; // 主类型
	private int sonType; // 子类型
	private int useWay; // 使用方式 1 主动 2 默认 3 条件触发 等等
	private int selectorType; // 扇形？矩阵
	private int selectorTarget; // 目标选择器 全体||N个
	private int selectorCount; // 搜索个数
	private int selectorLength; // 搜索距离（半径或者边长）
	private int selectorAngle; // 搜索角度
	private int move;			//
	
	private int selector; // 目标选择器：1 随机敌人 2 敌方全体等
	private int attackTimes; // 攻击次数
	private int costType; // 使用消耗类型 0 无消耗，1 魂 2 血等
	private int costCount; // 消耗数量
	private int attackType; // 攻击类型
	private int paramValue1; // 攻击参数（固定数值）气血
	private int paramValue2; // 攻击参数（固定数值）元魂		
	private int paramValue3; // 攻击参数
	private int paramParent1; // 攻击参数(百分比) 气血
	private int paramParent2; // 攻击参数(百分比) 元魂
	private int paramParent3; // 攻击参数(百分比)
	private String bufferIds; // 攻击产生bufferId集
	private int random; // 技能随机数
	private int isCrit; // 是否参与暴击计算
	private int priority; // 技能优先级 （当同等满足条件时，有限级高技能先释放）
	private int costTime; // 技能释放时间（时间内，不再接受攻击指令）
	private int cooldown; // cd时间
	private int combo; // 连击
	private int maxCombo; // 最大连接数
	private int animation; // 动作

	private int[] bufferIdArr; // buffer数组

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public int getMasterType() {
		return masterType;
	}

	public void setMasterType(int masterType) {
		this.masterType = masterType;
	}

	public int getSonType() {
		return sonType;
	}

	public void setSonType(int sonType) {
		this.sonType = sonType;
	}

	public int getUseWay() {
		return useWay;
	}

	public void setUseWay(int useWay) {
		this.useWay = useWay;
	}

	public int getSelectorType() {
		return selectorType;
	}

	public void setSelectorType(int selectorType) {
		this.selectorType = selectorType;
	}

	public int getSelectorTarget() {
		return selectorTarget;
	}

	public void setSelectorTarget(int selectorTarget) {
		this.selectorTarget = selectorTarget;
	}

	public int getSelectorCount() {
		return selectorCount;
	}

	public void setSelectorCount(int selectorCount) {
		this.selectorCount = selectorCount;
	}

	public int getSelectorLength() {
		return selectorLength;
	}

	public void setSelectorLength(int selectorLength) {
		this.selectorLength = selectorLength;
	}

	public int getSelectorAngle() {
		return selectorAngle;
	}

	public void setSelectorAngle(int selectorAngle) {
		this.selectorAngle = selectorAngle;
	}

	public int getSelector() {
		return selector;
	}

	public void setSelector(int selector) {
		this.selector = selector;
	}

	public int getAttackTimes() {
		return attackTimes;
	}

	public void setAttackTimes(int attackTimes) {
		this.attackTimes = attackTimes;
	}

	public int getCostType() {
		return costType;
	}

	public void setCostType(int costType) {
		this.costType = costType;
	}

	public int getCostCount() {
		return costCount;
	}

	public void setCostCount(int costCount) {
		this.costCount = costCount;
	}

	public int getAttackType() {
		return attackType;
	}

	public void setAttackType(int attackType) {
		this.attackType = attackType;
	}

	public int getParamValue1() {
		return paramValue1;
	}

	public void setParamValue1(int paramValue1) {
		this.paramValue1 = paramValue1;
	}

	public int getParamValue2() {
		return paramValue2;
	}

	public void setParamValue2(int paramValue2) {
		this.paramValue2 = paramValue2;
	}

	public int getParamValue3() {
		return paramValue3;
	}

	public void setParamValue3(int paramValue3) {
		this.paramValue3 = paramValue3;
	}

	public int getParamParent1() {
		return paramParent1;
	}

	public void setParamParent1(int paramParent1) {
		this.paramParent1 = paramParent1;
	}

	public int getParamParent2() {
		return paramParent2;
	}

	public void setParamParent2(int paramParent2) {
		this.paramParent2 = paramParent2;
	}

	public int getParamParent3() {
		return paramParent3;
	}

	public void setParamParent3(int paramParent3) {
		this.paramParent3 = paramParent3;
	}

	public String getBufferIds() {
		return bufferIds;
	}

	public void setBufferIds(String bufferIds) {
		if (bufferIds != null) {
			String[] bids = bufferIds.split(",");
			bufferIdArr = new int[bids.length];
			for (int i = 0; i < bids.length; i++) {
				if (bids[i].isEmpty())
					continue;
				bufferIdArr[i] = Integer.valueOf(bids[i]);
			}
		}
		this.bufferIds = bufferIds;
	}

	public int getRandom() {
		return random;
	}

	public void setRandom(int random) {
		this.random = random;
	}

	public int getIsCrit() {
		return isCrit;
	}

	public void setIsCrit(int isCrit) {
		this.isCrit = isCrit;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getCostTime() {
		return costTime;
	}

	public void setCostTime(int costTime) {
		this.costTime = costTime;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getCombo() {
		return combo;
	}

	public void setCombo(int combo) {
		this.combo = combo;
	}

	public int getMaxCombo() {
		return maxCombo;
	}

	public void setMaxCombo(int maxCombo) {
		this.maxCombo = maxCombo;
	}

	public int getAnimation() {
		return animation;
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public int[] getBufferIdArr() {
		return bufferIdArr;
	}

	public void setBufferIdArr(int[] bufferIdArr) {
		this.bufferIdArr = bufferIdArr;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

}
