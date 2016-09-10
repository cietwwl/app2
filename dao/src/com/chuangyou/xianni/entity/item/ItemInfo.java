package com.chuangyou.xianni.entity.item;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg;
import com.chuangyou.common.protobuf.pb.item.ItemFullInfoMsgProto.ItemFullInfoMsg;
import com.chuangyou.common.protobuf.pb.item.ItemPosProto.ItemPosMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class ItemInfo extends DataObject implements Cloneable {
	private long	id;
	private int		templateId;			// 模板ID
	private long	playerId;			// 用户ID
	private short	pos;				// 位置
	private boolean	isExist;			// 是否存在
	private long	objectId;			// 所属对象ID（多对象装备背包使用）
	private short	bagType;			// 背包类型
	private boolean	isBinds;			// 是否绑定
	private boolean	isUsed;				// 是否使用
	private int		validDate;			// 单位分钟
	private Date	beginDate;			// 添加时间
	private int		count;				// 数量
	private short	removeType;			// 删除类型
	private Date	removeDate;			// 删除时间
	private short	addType;			// 添加类型
	private Date	addDate;			// 添加时间
	private boolean	isTips;				// 全屏幕提示,及时保存
	private boolean	isNew;				//
	private int		pro;				// 基础属性
	private int		qualityCoefficient;	// 品质系数
	private int		grow;				// 成长系数

	/** 觉醒等级 */
	private int		awaken;
	/** 觉醒突破点 */
	private int		awakenPoint;
	/** 注入魂石 */
	private int		stone;

	public ItemInfo() {
		setOp(Option.Insert);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		setOp(Option.Update);
		this.playerId = playerId;
	}

	public short getPos() {
		return pos;
	}

	public void setPos(short pos) {
		setOp(Option.Update);
		this.pos = pos;
	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		setOp(Option.Update);
		this.isExist = isExist;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		setOp(Option.Update);
		this.objectId = objectId;
	}

	public short getBagType() {
		return bagType;
	}

	public void setBagType(short bagType) {
		setOp(Option.Update);
		this.bagType = bagType;
	}

	public boolean isBinds() {
		return isBinds;
	}

	public void setBinds(boolean isBinds) {
		setOp(Option.Update);
		this.isBinds = isBinds;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		setOp(Option.Update);
		this.isUsed = isUsed;
	}

	public int getValidDate() {
		return validDate;
	}

	public void setValidDate(int validDate) {
		setOp(Option.Update);
		this.validDate = validDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		setOp(Option.Update);
		this.beginDate = beginDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		setOp(Option.Update);
		this.count = count;
	}

	public short getRemoveType() {
		return removeType;
	}

	public void setRemoveType(short removeType) {
		setOp(Option.Update);
		this.removeType = removeType;
	}

	public Date getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		setOp(Option.Update);
		this.removeDate = removeDate;
	}

	public short getAddType() {
		return addType;
	}

	public void setAddType(short addType) {
		setOp(Option.Update);
		this.addType = addType;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		setOp(Option.Update);
		this.addDate = addDate;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		setOp(Option.Update);
		this.isNew = isNew;
	}

	public boolean isTips() {
		return isTips;
	}

	public void setTips(boolean isTips) {
		this.isTips = isTips;
	}

	public void decCount(int value) {
		if (value > 0) {
			setCount(getCount() - value);
		}
	}

	public void addCount(int value) {
		if (value > 0) {
			setCount(getCount() + value);
		}
	}

	public int getPro() {
		return pro;
	}

	public void setPro(int pro) {
		setOp(Option.Update);
		this.pro = pro;
	}

	public int getQualityCoefficient() {
		return qualityCoefficient;
	}

	public void setQualityCoefficient(int qualityCoefficient) {
		setOp(Option.Update);
		this.qualityCoefficient = qualityCoefficient;
	}

	public int getGrow() {
		return grow;
	}

	public void setGrow(int grow) {
		setOp(Option.Update);
		this.grow = grow;
	}

	public int getAwaken() {
		return awaken;
	}

	public void setAwaken(int awaken) {
		setOp(Option.Update);
		this.awaken = awaken;
	}

	public int getAwakenPoint() {
		return awakenPoint;
	}

	public void setAwakenPoint(int awakenPoint) {
		setOp(Option.Update);
		this.awakenPoint = awakenPoint;
	}

	public int getStone() {
		return stone;
	}

	public void setStone(int stone) {
		setOp(Option.Update);
		this.stone = stone;
	}

	public void writeProto(ItemFullInfoMsg.Builder builder) {
		builder.setId(this.getId());
		builder.setTemplateId(this.getTemplateId());
		builder.setPos(this.getPos());
		builder.setBagType(this.getBagType());
		builder.setBind(this.isBinds);
		builder.setUsed(this.isUsed);
		builder.setCount(this.count);
		builder.setValidDate(this.validDate);
		builder.setPro(this.getPro());
		builder.setQualityCoefficient(this.getQualityCoefficient());
		builder.setGrow(this.grow);

		EquipInfoMsg.Builder equip = EquipInfoMsg.newBuilder();
		equip.setPos(this.getPosMsg());
		equip.setAwakenLevel(this.getAwaken());
		equip.setAwakenPoint(this.getAwakenPoint());
		equip.setStoneTempId(this.getStone());
		builder.setEquip(equip);

		if (this.beginDate != null) {
			builder.setBeginDate(this.beginDate.getTime());
		}
		builder.setIsNew(this.isNew);
		
		this.setNew(false);
	}

	public ItemPosMsg getPosMsg() {
		ItemPosMsg.Builder posMsg = ItemPosMsg.newBuilder();
		posMsg.setBagType(this.getBagType());
		posMsg.setPosition(this.getPos());
		posMsg.setItemId(this.getId());
		return posMsg.build();
	}

	@Override
	public Object clone() {
		try {
			ItemInfo itemInfoClone = (ItemInfo) super.clone();
			return itemInfoClone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
