package com.chuangyou.xianni.soul;

public class FuseSkillVo {
	private int skillId;
	private int color;
	public int getSkillId() {
		return skillId;
	}
	public FuseSkillVo(int skillId, int color) {
		super();
		this.skillId = skillId;
		this.color = color;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
}
