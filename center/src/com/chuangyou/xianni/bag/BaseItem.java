package com.chuangyou.xianni.bag;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.constant.ItemType;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemInfo;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;

public class BaseItem {
	private ItemTemplateInfo	itemTempInfo;
	private ItemInfo			itemInfo;
	private boolean				isAdd;			// 是否已经添加

	public BaseItem(ItemTemplateInfo temp, ItemInfo info) {
		this.itemTempInfo = temp;
		this.itemInfo = info;
		isAdd = false;
	}

	public ItemTemplateInfo getItemTempInfo() {
		return itemTempInfo;
	}

	public void setItemTempInfo(ItemTemplateInfo itemTempInfo) {
		this.itemTempInfo = itemTempInfo;
	}

	public ItemInfo getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfo itemInfo) {
		this.itemInfo = itemInfo;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	/* 移动物品 */
	public void moveItem(long playerId, short pos, long objectId, short bagType) {
		// 背包类型相同
		if (!(getItemInfo().getPos() == pos && getItemInfo().getPlayerId() == playerId && getItemInfo().getObjectId() == objectId && getItemInfo().getBagType() == bagType)) {
			getItemInfo().setPos(pos);
			getItemInfo().setPlayerId(playerId);
			getItemInfo().setObjectId(objectId);
			getItemInfo().setBagType(bagType);
		} else {
			// Log.error("----------------------");
		}
	}

	/* 添加一个来源外部的物品 ，当前物品在数据库中已存在 */
	public boolean addItemOther(long playerId, int itemCount, boolean isBind, short changeType) {
		ItemLogHelper mgr = new ItemLogHelper(this);
		getItemInfo().setPlayerId(playerId);
		getItemInfo().setCount(itemCount);
		getItemInfo().setAddType(changeType);
		if (isBind) {
			getItemInfo().setBinds(isBind);
		}
		getItemInfo().setAddDate(TimeUtil.getSysteCurTime());
		mgr.commitChanges(changeType);
		return true;
	}

	/* 增加物品数量 */
	public void addItem(int count, long affectId, boolean isBind, short changeType) {
		ItemLogHelper mgr = new ItemLogHelper(this);
		this.getItemInfo().addCount(count);
		this.getItemInfo().setAddType(changeType);
		if (isBind) {
			this.getItemInfo().setBinds(isBind);
		}
		mgr.commitChanges(changeType);
	}

	/* 减少物品数量 */
	public void decItem(int count, long affectId, short changeType) {
		ItemLogHelper mgr = new ItemLogHelper(this);
		this.getItemInfo().decCount(count);
		this.getItemInfo().setRemoveDate(TimeUtil.getSysteCurTime());
		this.getItemInfo().setRemoveType(changeType);
		mgr.commitChanges(changeType);
	}

	/* 删除物品 */
	public void delItem(int count, short changeType) {
		ItemLogHelper mgr = new ItemLogHelper(this);
		this.getItemInfo().setPos((short) -1);
		this.getItemInfo().setBagType(BagType.Recycle);
		this.getItemInfo().setRemoveType(changeType);
		this.getItemInfo().setRemoveDate(TimeUtil.getSysteCurTime());
		this.getItemInfo().setExist(false);
		this.getItemInfo().setCount(0);
		mgr.commitChanges(changeType);
	}

	/**
	 * 手工强制叠加（不判断绑定）
	 */
	public boolean stackForHand(BaseItem baseItem) {
		return canStackedTo(baseItem, false);
	}

	/**
	 * 自动叠加（判断绑定类型）
	 */
	public boolean statckForAuto(BaseItem baseItem) {
		return canStackedTo(baseItem, true);
	}

	/**
	 * 检测两个物品是否可以叠加
	 * 
	 * @param otherBaseItem
	 * @return
	 */
	private boolean canStackedTo(BaseItem otherBaseItem, boolean checkBind) {
		ItemTemplateInfo tempInfo = otherBaseItem.getItemTempInfo();
		// 同一个物品不允许叠加
		if (this == otherBaseItem) {
			return false;
		}
		// 不同模板ID物品不允许叠加
		if (itemInfo.getTemplateId() != tempInfo.getId()) {
			return false;
		}
		// 已经叠加到最大数量不允许叠加
		if (itemTempInfo.getAmount() <= 1 || tempInfo.getAmount() < otherBaseItem.itemInfo.getCount()) {
			return false;
		}
		// 已经使用合未使用物品，不允许叠加
		if (itemInfo.isUsed() != otherBaseItem.getItemInfo().isUsed()) {
			return false;
		}
		// 不叠加绑定物品但是叠加两个物品绑定状态不一致
		if (checkBind && (itemInfo.isBinds() != otherBaseItem.getItemInfo().isBinds())) {
			return false;
		}
		return true;
	}

	public boolean isMaxCount() {
		return itemTempInfo.getAmount() - itemInfo.getCount() == 0 ? true : false;
	}

	public int getTemplateId() {

		return itemTempInfo.getId();
	}

	public boolean isValidItem() {
		if (itemInfo.getValidDate() != 0 && itemInfo.isUsed()) {
			long currentTimeSec = TimeUtil.getSysCurSeconds();
			long validDate = itemInfo.getValidDate() * 60;
			if (TimeUtil.getDateToSeconds(itemInfo.getBeginDate()) + validDate < currentTimeSec) {
				return false;
			}
		}
		return true;
	}

	public boolean isCanJoin() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 物品是否镶嵌有宝石
	 */
	public boolean hasJoin() {
		return false;
	}

	public String getTemplateName() {
		return itemTempInfo.getName();
	}

	/**
	 * 创建一个物品(用于临时物品)
	 * 
	 * @param template
	 * @param count
	 * @param type
	 * @return
	 */
	public static BaseItem createBaseItem(ItemTemplateInfo template, int count, short addType, boolean isBind) {
		if (template == null) {
			Log.error("create item of itemTemp is Null");
			return null;
		}

		ItemInfo info = new ItemInfo();
		info.setTemplateId(template.getId());
		info.setId(0);
		info.setBagType(BagType.New);
		info.setExist(true);
		info.setAddDate(TimeUtil.getDefaultDate());
		info.setBeginDate(TimeUtil.getSysteCurTime());
		info.setValidDate(0);
		info.setCount(count);
		info.setBinds(isBind);
		info.setTips(false);
		info.setUsed(false);
		info.setNew(true);
		info.setAddType(addType);
		info.setRemoveDate(TimeUtil.getDefaultDate());
		if (template.getMasterType() == ItemType.MainType.EQUIP) {// 类型装备
			int itemBase = template.getItemBase();
			int key = itemBase / 1000000;
			int val = itemBase % 1000000;
			float r = (100 + MathUtils.randomClamp(-5, 5)) / 100F;
			val = (int) (val * r);
			info.setPro(key * 1000000 + val);
			info.setQualityCoefficient(template.getQualityCoefficient());
			float r2 = (100 + MathUtils.randomClamp(-5, 5)) / 100F;
			int grow = (int) (template.getGrow() * r2);
			info.setGrow(grow);

			info.setAwaken(0);
			info.setAwakenPoint(0);
			info.setStone(0);
		}
		info.setOp(Option.Insert);
		BaseItem bseItem = new BaseItem(template, info);
		return bseItem;
	}

	/*
	 * 物品是否有效
	 */
	public boolean isEffective() {
		return getItemInfo() != null && getItemInfo().isExist() != false && getItemTempInfo() != null;
	}
}
