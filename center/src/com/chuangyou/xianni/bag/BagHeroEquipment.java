package com.chuangyou.xianni.bag;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.constant.ItemType;
import com.chuangyou.xianni.constant.EquipConstant.EquipPosition;
import com.chuangyou.xianni.constant.EquipConstant.EquipType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.GamePlayer;

public class BagHeroEquipment extends BaseBag {

	/**
	 * 英雄装备背包个性化
	 * 
	 * @param capability
	 *            容量
	 * @param bagType
	 * @param beginSlot
	 * @param autoStack
	 * @param player
	 * @param objectId
	 */
	public BagHeroEquipment(short capability, short bagType, short beginSlot, boolean autoStack, GamePlayer player, long objectId) {
		super(capability, bagType, beginSlot, autoStack, player, objectId);
	}

	@Override
	public boolean moveItem(short startPos, short endPos, int count) {
		if (baseItems[startPos] == null)
			return false;
		// 开始位置不为空，结束位置不为空
		if (isEquipSlot(startPos) && (isEquipSlot(endPos)) && (baseItems[endPos] != null)
				&& (baseItems[startPos].getItemTempInfo().getMasterType() == baseItems[endPos].getItemTempInfo().getMasterType())) {
			if (!canEquipSlotContains(startPos, baseItems[endPos].getItemTempInfo()))
				endPos = getEmptyPos(getCapability());
		} else { // 开始位置不为空，结束位置为空
			if (isEquipSlot(endPos)) {
				if (!canEquipSlotContains(endPos, baseItems[startPos].getItemTempInfo())) {
					updateItem(baseItems[startPos]);
					return false;
				}

				if (!baseItems[startPos].isValidItem()) {
					updateItem(baseItems[startPos]);
					return false;
				}
			}

			if (isEquipSlot(startPos)) {
				if ((baseItems[endPos] != null) && (!canEquipSlotContains(startPos, baseItems[endPos].getItemTempInfo()))) {
					updateItem(baseItems[startPos]);
					return false;
				}
			}
		}
		return super.moveItem(startPos, endPos, count);
	}

	@Override
	public boolean addEmptyByPos(BaseItem baseItem, short pos) {
		if (super.addEmptyByPos(baseItem, pos)) {
			baseItem.getItemInfo().setBinds(true);
			return true;
		}
		return false;
	}

	public void getBagJoin(BaseProperty bagData, BaseProperty bagPer) {
		super.getBagJoin(bagData, bagPer);
	}

	public boolean canEquipSlotContains(int slot, ItemTemplateInfo tempInfo) {
		return true;
	}

	/**
	 * 是否为身上插槽
	 * 
	 * @param slot
	 * @return
	 */
	public boolean isEquipSlot(int slot) {
		return ((slot >= 0) && (slot < this.getCapability()));
	}

	public boolean isValid(BaseItem itemInfo) {
		if ((player != null && player.getBasePlayer() != null)) {
			// 检查是否能够装备上来
		} else {
			Log.error(String.format("当前用户未找到英雄%s", getObjectId()));
			return false;
		}
		return true;
	}

	public int strengthenData(int tempData, int strengthenGrade) {
		return tempData > 0 ? (int) (tempData * strengthenGrade * 0.1 + strengthenGrade * 5) : 0;
	}

	/**
	 * <pre>
	 * 获取时装对应英雄背包位置
	 * </pre>
	 * 
	 * @param tempInfo
	 * @return
	 */
	public short getPos(ItemTemplateInfo tempInfo) {
		short pos = -1;
		if (tempInfo.getMasterType() == ItemType.MainType.EQUIP) {
			switch (tempInfo.getSonType()) {
				case EquipType.weapon:// 武器
					pos = EquipPosition.weaponPosition;
					break;
				case EquipType.head:// 头盔
					pos = EquipPosition.headPosition;
					break;
				case EquipType.necklace:// 项链
					pos = EquipPosition.necklacePosition;
					break;
				case EquipType.body:// 衣服
					pos = EquipPosition.bodyPosition;
					break;
				case EquipType.ring:// 戒指
					pos = EquipPosition.ringPosition;
					break;
				case EquipType.hand:// 手套
					pos = EquipPosition.handPosition;
					break;
				case EquipType.ornament:// 玉佩
					pos = EquipPosition.ornamentPosition;
					break;
				case EquipType.foot:// 鞋子
					pos = EquipPosition.footPosition;
					break;
				default:
					pos = -1;
					break;
			}
		}
		return pos;
	}
}
