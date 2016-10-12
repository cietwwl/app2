package com.chuangyou.xianni.entity.avatar;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.avatar.AvatarBeanProto.AvatarBeanMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class AvatarInfo extends DataObject {
	private int		id;					// 唯一ID
	private long	playerId;			// 用户ID
	private int		tempId;				// 模板ID
	private int		grade;				// 等级
	private int		star;				// 星级
	private int		correspond;			// 默契等级
	private int		schedule;			// 默契进度
	private int		statu;				// 当前状态 0 为出战 1 出战
	private int		index;				// 出战顺序
	private int		skillId;			// 技能ID
	private int		skillStrengthen1;	// 神通ID1
	private int		skillStrengthen2;	// 神通ID1
	private int		skillStrengthen3;	// 神通ID1
	private int		skillStrengthen4;	// 神通ID1
	private int		skillStrengthen5;	// 神通ID1
	private Date	createTime;			// 创建时间

	public AvatarInfo(int id, long playerId, int tempId, int skillId) {
		this.createTime = new Date();
		this.playerId = playerId;
		this.tempId = tempId;
		this.correspond = 1;
		this.grade = 1;
		this.skillId = skillId;
		setOp(Option.Insert);
	}

	public AvatarInfo() {

	}

	public void writeProto(AvatarBeanMsg.Builder builder) {
		builder.setIndexId(id);
		builder.setPlayerId(playerId);
		builder.setTempId(tempId);
		builder.setGrade(grade);
		builder.setStar(star);
		builder.setCorrespond(correspond);
		builder.setSchedule(schedule);
		builder.setStatu(statu);
		builder.setSort(index);
		builder.setSkillId(skillId);
		builder.setSkillStrengthen1(skillStrengthen1);
		builder.setSkillStrengthen2(skillStrengthen2);
		builder.setSkillStrengthen3(skillStrengthen3);
		builder.setSkillStrengthen4(skillStrengthen4);
		builder.setSkillStrengthen5(skillStrengthen5);

	}

	public void addskill(int skillId, int index) {
		if (index == 0) {
			setSkillId(skillId);
		}
		if (index == 1) {
			setSkillStrengthen1(skillId);
		}
		if (index == 2) {
			setSkillStrengthen2(skillId);
		}
		if (index == 3) {
			setSkillStrengthen3(skillId);
		}
		if (index == 4) {
			setSkillStrengthen4(skillId);
		}
		if (index == 5) {
			setSkillStrengthen5(skillId);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getTempId() {
		return tempId;
	}

	public void setTempId(int tempId) {
		this.tempId = tempId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		if (this.grade != grade) {
			this.grade = grade;
			setOp(Option.Update);
		}
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		if (this.star != star) {
			this.star = star;
			setOp(Option.Update);
		}
	}

	public int getCorrespond() {
		return correspond;
	}

	public void setCorrespond(int correspond) {
		if (this.correspond != correspond) {
			this.correspond = correspond;
			setOp(Option.Update);
		}
	}

	public int getSchedule() {
		return schedule;
	}

	public void setSchedule(int schedule) {
		if (this.schedule != schedule) {
			this.schedule = schedule;
			setOp(Option.Update);
		}
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		if (this.statu != statu) {
			this.statu = statu;
			setOp(Option.Update);
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		if (this.index != index) {
			this.index = index;
			setOp(Option.Update);
		}
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		if (this.skillId != skillId) {
			setOp(Option.Update);
			this.skillId = skillId;
		}
	}

	public int getSkillStrengthen1() {
		return skillStrengthen1;
	}

	public void setSkillStrengthen1(int skillStrengthen1) {
		if (this.skillStrengthen1 != skillStrengthen1) {
			this.skillStrengthen1 = skillStrengthen1;
			setOp(Option.Update);
		}

	}

	public int getSkillStrengthen2() {
		return skillStrengthen2;
	}

	public void setSkillStrengthen2(int skillStrengthen2) {
		if (this.skillStrengthen2 != skillStrengthen2) {
			this.skillStrengthen2 = skillStrengthen2;
			setOp(Option.Update);
		}
	}

	public int getSkillStrengthen3() {
		return skillStrengthen3;
	}

	public void setSkillStrengthen3(int skillStrengthen3) {
		if (this.skillStrengthen3 != skillStrengthen3) {
			this.skillStrengthen3 = skillStrengthen3;
			setOp(Option.Update);
		}
	}

	public int getSkillStrengthen4() {
		return skillStrengthen4;
	}

	public void setSkillStrengthen4(int skillStrengthen4) {
		if (this.skillStrengthen4 != skillStrengthen4) {
			this.skillStrengthen4 = skillStrengthen4;
			setOp(Option.Update);
		}
	}

	public int getSkillStrengthen5() {
		return skillStrengthen5;
	}

	public void setSkillStrengthen5(int skillStrengthen5) {
		if (this.skillStrengthen5 != skillStrengthen5) {
			this.skillStrengthen5 = skillStrengthen5;
			setOp(Option.Update);
		}
	}

}
