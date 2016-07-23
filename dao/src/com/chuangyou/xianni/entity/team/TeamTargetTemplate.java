package com.chuangyou.xianni.entity.team;

public class TeamTargetTemplate {
	private int		id;				// 目标ID
	private int		parentId;		// 父ID
	private String	name;			// 姓名
	private int		levLimitMin;	// 进入最小等级
	private int		levLimitMax;	// 进入最大等级
	private int		targetType;		// 目标类型
	private int		target;			// 指向ID
	private int		entityId;		// 实体ID
	private String	v3;				// 指向位置

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevLimitMin() {
		return levLimitMin;
	}

	public void setLevLimitMin(int levLimitMin) {
		this.levLimitMin = levLimitMin;
	}

	public int getLevLimitMax() {
		return levLimitMax;
	}

	public void setLevLimitMax(int levLimitMax) {
		this.levLimitMax = levLimitMax;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getV3() {
		return v3;
	}

	public void setV3(String v3) {
		this.v3 = v3;
	}

}
