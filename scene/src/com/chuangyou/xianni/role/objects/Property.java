package com.chuangyou.xianni.role.objects;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;

public class Property {
	private String	name;		// 属性名字
	private int		type;		// 属性类型
	private int		initData;	// 初始属性
	private int		buffData;	// buff数据
	private int		avatarData;	// 分身属性

	private int		totalJoin;	// 总属性值
	private boolean	isChange;	// 是否有改变

	public Property(String name, int type) {
		this.name = name;
		this.type = type;
	}

	/** 清理背包属性 */
	public void clearBuff() {
		this.buffData = 0;
		setChange(true);
	}

	private void fresh() {
		// 计算总的属性
		totalJoin = initData + buffData + avatarData;
		// 计算基础属性
		setTotalJoin(totalJoin);
		isChange = false;
	}

	public void setBuffData(int buffData) {
		if (buffData != this.buffData) {
			setChange(true);
			this.buffData = buffData;
		}
	}

	public int getTotalJoin() {
		if (isChange) {
			fresh();
		}
		return totalJoin;
	}

	private void setTotalJoin(int totalJoin) {
		setChange(true);
		this.totalJoin = totalJoin;
	}

	public int getInitData() {
		return initData;
	}

	public void setInitData(int initData) {
		if (initData != this.initData) {
			setChange(true);
			this.initData = initData;
		}
	}

	public int getAvatarData() {
		return avatarData;
	}

	public void clearAvatarData() {
		this.avatarData = 0;
		setChange(true);
	}

	public void setAvatarData(int avatarData) {
		if (avatarData != this.avatarData) {
			setChange(true);
			this.avatarData = avatarData;
		}
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public void writeProto(PropertyMsg.Builder builder) {
		builder.setTotalPoint(getTotalJoin());
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
		return String.format("初始属性%s", initData) + String.format("buff加成%s", buffData);
	}
}
