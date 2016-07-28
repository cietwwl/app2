package com.chuangyou.xianni.bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.LockData;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.army.Hero;
import com.chuangyou.xianni.battleMode.manager.BattleModeManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.constant.CommonType;
import com.chuangyou.xianni.constant.CommonType.CurrencyItemType;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemInfo; 
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.BasePlayer;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 用户+英雄背包管理
 */
public class BagInventory extends AbstractEvent implements IInventory {

	private GamePlayer					player;
	// 用户物品背包
	private BaseBag						playerBag;
	// 英雄ID+英雄装备列表
	private BagHeroEquipment			heroEquipment;
	// 背包过期物品
	private HashMap<Short, List<Short>>	validItemsPos;
	// 位置异常物品
	private List<BaseItem>				unLoadItem	= new ArrayList<BaseItem>();
	// 数据异常物品
	private HashMap<Short, List<Short>>	unusualItemsPos;

	private LockData					bagMoveLock;

	public BagInventory(GamePlayer player) {
		this.player = player;
		bagMoveLock = new LockData();

		playerBag = new BaseBag((short) (SystemConfigTemplateMgr.getIntValue("bag.initGridNum") + (short) player.getBasePlayer().getPlayerInfo().getpBagCount()), BagType.Play, (short) 0, true, player,
				0);
		validItemsPos = new HashMap<Short, List<Short>>();
		unusualItemsPos = new HashMap<Short, List<Short>>();
	}

	public boolean loadFromDataBase() {
		BasePlayer basePlayer = player.getBasePlayer();
		unLoadItem.clear();
		if (basePlayer != null) {
			heroEquipment = new BagHeroEquipment((short) 12, BagType.HeroEquipment, (short) 0, true, player, 1);// 创建英雄装备背包
		}
		heroEquipment.beginChanges();
		playerBag.beginChanges();

		StringBuilder sb = new StringBuilder();
		List<ItemInfo> items = ItemManager.list(player.getPlayerId());

		if ((items != null) && (items.size() > 0)) {
			for (ItemInfo info : items) {
				ItemTemplateInfo tempInfo = ItemManager.findItemTempInfo(info.getTemplateId());// 装备模板
				if (tempInfo != null) {
					BaseItem baseItem = new BaseItem(tempInfo, info);
					if (!baseItem.getItemInfo().isExist() || getBag(baseItem.getItemInfo().getBagType()) == null) {
						continue;
					}
					// 数量异常
					if (baseItem.getItemInfo().getCount() < 1) {
						List<Short> unusualList = unusualItemsPos.get(baseItem.getItemInfo().getBagType());
						if (unusualList == null) {
							unusualList = new ArrayList<Short>();
							unusualItemsPos.put(baseItem.getItemInfo().getBagType(), unusualList);
						}
						unusualList.add(baseItem.getItemInfo().getPos());
					}

					if (!baseItem.isValidItem() && !baseItem.hasJoin()) {
						List<Short> posList = validItemsPos.get(baseItem.getItemInfo().getBagType());
						if (posList == null) {
							posList = new ArrayList<Short>();
							validItemsPos.put(baseItem.getItemInfo().getBagType(), posList);
						}
						posList.add(baseItem.getItemInfo().getPos());
					}

					if (baseItem.getItemInfo().getBagType() == BagType.Play) {// 领主背包
						if (playerBag.addEmptyByPos(baseItem, baseItem.getItemInfo().getPos()) == false) {
							int oldCount = 0;
							int oldTempId = 0;
							int pos = baseItem.getItemInfo().getPos();
							BaseItem oldBaseItem = playerBag.getItemByPos(pos);
							if (oldBaseItem != null) {
								oldTempId = oldBaseItem.getTemplateId();
								oldCount = oldBaseItem.getItemInfo().getCount();
							} else {
								sb.append("OldItem null \r\n");
							}
							sb.append(" Error Pos : " + pos + "\r\n");
							sb.append(" Old TempId : " + oldTempId + ", Old Count : " + oldCount + "\r\n");
							sb.append(" New TempId : " + baseItem.getTemplateId() + ", New Count : " + baseItem.getItemInfo().getCount() + "; \r\n");
							unLoadItem.add(baseItem);
						}

						continue;
					}

					if (baseItem.getItemInfo().getBagType() == BagType.HeroEquipment) {// 英雄装备背包
						if (heroEquipment.addEmptyByPos(baseItem, baseItem.getItemInfo().getPos()) == false)
							unLoadItem.add(baseItem);
						{
							continue;
						}
					}
				} else {
					Log.warn(String.format("用户%s,%s加载物品模板%s不存在", player.getPlayerId(), player.getNickName(), info.getTemplateId()));
				}
			}
		}

		// 将出错的英雄物品放入领主背包
		if (unLoadItem.size() != 0) {
			for (BaseItem baseItem : unLoadItem) {
				playerBag.addEmptyByItem(baseItem);
				sb.append("从新放入新的物品 :tempId : " + baseItem.getTemplateId() + " , templateName : " + baseItem.getTemplateName() + " , count : " + baseItem.getItemInfo().getCount() + "\r\n");
			}
			unLoadItem.clear();
		}
		// 如果领主背包存在一个位置多个物品则打印日志
		if (sb.length() > 0) {
			Log.error("玩家领主背包物品位置异常,  userId: " + player.getPlayerId() + ", nickName: " + player.getNickName() + sb.toString());
		}
		playerBag.commitChanges();
		heroEquipment.commitChanges();

		return true;
	}

	/**
	 * 获取背包中的全部物品。
	 * 
	 * @return
	 */
	public List<BaseItem> getAllItems() {
		List<BaseItem> infos = new ArrayList<BaseItem>();
		for (BaseItem info : playerBag.getItems()) {
			infos.add(info);
		}

		for (BaseItem info : heroEquipment.getItems()) {
			infos.add(info);
		}
		return infos;
	}

	public void updateAll() {
		playerBag.updateAll();
		heroEquipment.updateAll();
	}

	/**
	 * 获取普通背包中的物品数量（英雄背包除外 ）
	 * 
	 * @param templateId
	 * @return
	 */
	public int getItemCount(int templateId) {
		return playerBag.getTemplateCount(templateId);
	}

	public int getPlayerBagItemCount(int templateId) {
		return playerBag.getTemplateCount(templateId);
	}

	public int getTemplateCountByBind(int templateId, short bindType) {
		if (bindType != BindType.BIND && bindType != BindType.NOBIND) {
			return 0;
		}
		return playerBag.getTemplateCountByBind(templateId, bindType == BindType.BIND);
	}

	/**
	 * 添加单个物品
	 * 
	 * @param templateId
	 * @param count
	 * @param addType
	 * @return
	 */
	public boolean addItem(int templateId, int count, short addType, boolean isBind) {
		switch (templateId) {
			case CurrencyItemType.CASH_ITEM:
				player.getBasePlayer().addCash(count);
				return true;
			case CurrencyItemType.CASH_BIND_ITEM:
				player.getBasePlayer().addBindCash(count);
				return true;
			case CurrencyItemType.MONEY_ITEM:
				player.getBasePlayer().addMoney(count);
				return true;
			case CurrencyItemType.REPAIR_ITEM:
				player.getBasePlayer().addRepair(count);
				return true;
			case CurrencyItemType.EXP:
				player.getBasePlayer().addExp(count);
				return true;
		}

		ItemTemplateInfo tempInfo = ItemManager.findItemTempInfo(templateId);
		if (tempInfo == null) {
			Log.error("add item bu template is  not exists,tempId :" + templateId + "  playerId :" + player.getPlayerId());
			return false;
		}
		BaseItem item = BaseItem.createBaseItem(tempInfo, count, addType);
		playerBag.beginChanges();
		boolean result = playerBag.addTemplate(item, item.getItemInfo().getCount());
		playerBag.commitChanges();
		if (result) {
			this.notifyListeners(new ObjectEvent(this, templateId, EventNameType.TASK_ITEM_CHANGE_ADD));
		}
		return result;
	}

	/**
	 * 添加物品到背包。如果背包满了。就以邮件发给玩家
	 * 
	 * @param templateId
	 * @param count
	 * @param addType
	 * @param isBind
	 * @return
	 */
	public void addItemInBagOrEmail(int templateId, int count, short addType, boolean isBind) {
		if (addItem(templateId, count, addType, isBind) == false) {
			EmailManager.insertEmail(player.getPlayerId(), "背包已满", "背包已经满。请整理背包在附件中收取物品", templateId + "," + count + ";");
		}
	}

	/**
	 * 删除普通背包中的物品(英雄背包除外)
	 * 
	 * @param templateId
	 * @param count
	 * @param type
	 * @return 成功:true 失败:false
	 */
	public boolean removeItem(int templateId, int count, short itemRemoveType) {
		int playerItemCount = playerBag.getTemplateCount(templateId);
		ItemTemplateInfo tempInfo = ItemManager.findItemTempInfo(templateId);
		if ((tempInfo != null) && (count <= playerItemCount)) {
			if ((playerItemCount > 0) && (count > 0)) {
				if (playerBag.removeTemplate(templateId, playerItemCount > count ? count : playerItemCount, itemRemoveType, BindType.ALL)) {
					count = count < playerItemCount ? 0 : count - playerItemCount;
				}
			}
			if (count != 0) {
				Log.error(String.format("Item Remover Error：PlayerId%s,TemplateId:%s,Count:%s PlayerBag:%s HideBag:%s Storage:%s", player.getPlayerId(), templateId, count, playerItemCount, 0, 0));
				return false;
			}
			this.notifyListeners(new ObjectEvent(this, templateId, EventNameType.TASK_ITEM_CHANGE_REDUCE));
			return true;
		}
		return false;
	}

	/**
	 * 删除普用户背包中的物品
	 * 
	 * @param templateId
	 * @param count
	 * @param type
	 * @return 成功:true 失败:false
	 */
	public boolean removeItemFromPlayerBag(int templateId, int count, short itemRemoveType) {
		int playerItemCount = playerBag.getTemplateCount(templateId);
		ItemTemplateInfo tempInfo = ItemManager.findItemTempInfo(templateId);
		if ((tempInfo != null) && (count <= playerItemCount)) {
			if ((playerItemCount > 0) && (count > 0)) {
				if (playerBag.removeTemplate(templateId, playerItemCount > count ? count : playerItemCount, itemRemoveType, BindType.ALL)) {
					count = count < playerItemCount ? 0 : count - playerItemCount;
				}
			}
			if (count != 0) {
				Log.error(String.format("Item Remover Error：PlayerId%s,TemplateId:%s,Count:%s PlayerBag:%s", player.getPlayerId(), templateId, count, playerItemCount));
				return false;
			}
			this.notifyListeners(new ObjectEvent(this, templateId, EventNameType.TASK_ITEM_CHANGE_REDUCE));
			return true;
		}
		return false;
	}

	/**
	 * @param bagType
	 * @param objectId
	 * @return
	 */
	public BaseBag getBag(int bagType) {
		switch (bagType) {
			case BagType.Play:
				return playerBag;
			case BagType.HeroEquipment:
				return heroEquipment;
			default:
				return null;
		}
	}

	/**
	 * 返回领主装备背包
	 * 
	 * @return
	 */
	public BaseBag getPlayerBag() {
		return playerBag;
	}

	public BagHeroEquipment getHeroEquipmentBag() {
		return heroEquipment;
	}

	public boolean addTempItem(List<BaseItem> items) {
		if (playerBag == null) {
			return false;
		}
		if ((items == null) || (items.size() == 0)) {
			return false;
		}
		// 一、物品分类
		List<BaseItem> bagItems = new ArrayList<BaseItem>();
		for (BaseItem info : items) {
			if (info == null || info.isEffective() == false || info.isAdd() == true) {
				continue;
			}
			info.setAdd(true);
			int tempId = info.getItemTempInfo().getId();
			int count = info.getItemInfo().getCount();
			switch (tempId) {
				case -100: // 添加货币X
					player.getBasePlayer().addMoney(count);
					break;
				default:
					bagItems.add(info);
					break;
			}
			if (info.getItemInfo().isTips()) {
				saveToDatabase();
			}
		}

		// 二、加入背包
		if (bagItems.size() > 0) {
			playerBag.beginChanges();
			for (BaseItem baseItem : bagItems) {
				playerBag.addTemplate(baseItem, baseItem.getItemInfo().getCount());
			}
			playerBag.commitChanges();
		}
		return true;
	}

	public int getEmptyCount() {
		if (playerBag == null)
			return 0;
		return playerBag.getEmptyCount();
	}

	public boolean saveToDatabase() {
		playerBag.saveDataBase();
		if (heroEquipment != null) {
			heroEquipment.saveDataBase();
		}
		return true;
	}

	/**
	 * 刷新英雄属性
	 * 
	 * @param heroId
	 */
	public void updateHeroProperties(long playerId) {
		try {
			BaseProperty bagData = new BaseProperty();
			BaseProperty bagPer = new BaseProperty();
			heroEquipment.getBagJoin(bagData, bagPer);
			Hero hero = player.getArmyInventory().getArmy().getHero();
			hero.addBag(bagData, bagPer);
			player.getArmyInventory().updateProperty();
		} catch (Exception e) {
			Log.error("更新异常,nickName : " + player.getNickName(), e);
		}
	}

	/**
	 * 卸载内存数据
	 */
	public boolean unloadData() {
		try {
			if (playerBag != null) {
				playerBag.clear();
			}
			if (heroEquipment != null) {
				heroEquipment.clear();
			}
			this.clearListener();
			return true;
		} catch (Exception e) {
			Log.error("卸载背包异常nickname " + player.getNickName(), e);
		}
		return false;
	}

	/** 装备操作 */
	public void equimentOption(int beginBagType, short beginPos) {
		int endBagType = 0;
		short endPos = -1;
		int weaponId = 0;

		if (beginBagType == BagType.HeroEquipment) { // 卸下装备
			endBagType = BagType.Play;
			endPos = -1;
			bagMoveReceive(beginBagType, beginPos, endBagType, endPos, (short) -1);
			updateHeroProperties(heroEquipment.getObjectId());
		}

		if (beginBagType == BagType.Play) { // 穿上装备
			BaseBag beginBag = getBag(beginBagType);
			BaseItem beginItem = beginBag.getItemByPos(beginPos);
			if (beginItem == null) {
				Log.error("hero equipment item but is empty");
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Exchange_Bag_Prop_Error, (byte)0);
				return;
			}
			if (beginItem.getItemTempInfo().getProfession() != 0 && beginItem.getItemTempInfo().getProfession() != player.getBasePlayer().getPlayerInfo().getJob()) {
				Log.error("hero equipment item error");
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Exchange_Bag_Prop_Error, (byte)0);
				return;
			}
			endBagType = BagType.HeroEquipment;
			endPos = heroEquipment.getPos(beginItem.getItemTempInfo());
			bagMoveReceive(beginBagType, beginPos, endBagType, endPos, (short) -1);
			weaponId = beginItem.getTemplateId();
			updateHeroProperties(heroEquipment.getObjectId());
		}

		// 操作武器
		if ((beginBagType == BagType.HeroEquipment && beginPos == 0) || endPos == 0) {
			PlayerAttUpdateMsg.Builder attMsg = PlayerAttUpdateMsg.newBuilder();
			PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
			proMsg.setType(EnumAttr.Weapon.getValue());
			proMsg.setTotalPoint(weaponId);
			attMsg.addAtt(proMsg);
			attMsg.setPlayerId(player.getPlayerId());
			PBMessage message = MessageUtil.buildMessage(Protocol.S_PROPERTY_UPDATE, attMsg);
			player.sendPbMessage(message);

			player.getBasePlayer().getPlayerInfo().setWeaponId(weaponId);
		}
	}

	public void bagMoveReceive(int beginBagType, short beginPos, int endBagType, short endPos, short count) {
		BaseBag beginBag = getBag(beginBagType);
		BaseBag endBag = getBag(endBagType);
		count = 1;
		// 规则1：beginPos=-1表示删除物品
		// 规则2：endPos=-1表示自动添加
		if (beginBag == null || endBag == null) {
			return;
		}
		if (bagMoveLock.beginLock()) {
			try {
				beginBag.beginChanges();
				endBag.beginChanges();
				if (beginPos != -1) {
					BaseItem beginItem = beginBag.getItemByPos(beginPos);
					if (endPos == -1) {
						// 自动添加
						if (endBag.stackByItem(beginItem) || (endBag.addEmptyByItem(beginItem))) {
							beginBag.takeOutByItem(beginItem);
						} else {
						}
					} else {
						/* 相同背包 */
						if (beginBagType == endBagType) {
							beginBag.moveItem(beginPos, endPos, count);
						}
						/* 不同背包 */
						else {

							/* 装备背包->领主背包 */
							if ((beginBagType == BagType.HeroEquipment) && (endBagType == BagType.Play)) {
								equipmentToPlayer(getHeroEquipmentBag(), beginPos, endPos, count);
							}

							/* 领主背包-->装备背包 */
							if ((beginBagType == BagType.Play) && (endBagType == BagType.HeroEquipment)) {
								playerToEquipment(getHeroEquipmentBag(), beginPos, endPos, count);
							}
						}
					}
				} else {
					// 删除物品
					if (endPos != -1) {
						beginBag.removeByPos(endPos, ItemRemoveType.DELETE);
					}
				}
				beginBag.commitChanges();
				endBag.commitChanges();
			} catch (Exception e) {
				Log.error("背包移动异常, nickName : " + player.getNickName() + ",beginBagType : " + beginBagType + ",beginPos : " + beginPos + ",endBagType : " + endBagType + ",endPos : " + endPos
						+ ",count : " + count, e);
			} finally {
				bagMoveLock.commitLock();
			}
		}
	}

	/**
	 * 装备背包->领主背包
	 */
	private void equipmentToPlayer(BagHeroEquipment heroEquipment, short beginPos, short endPos, short count) {
		// 领主背包(结束背包)
		try {
			BaseItem beginItem = heroEquipment.getItemByPos(beginPos);
			if (beginItem == null || beginItem.getItemInfo() == null || beginItem.getItemTempInfo() == null) {
				return;
			}
			if (endPos < playerBag.getBeginPos() || endPos > playerBag.getCapability()) {
				return;
			}
			// melody add (背包移动时候的时效判断)
			if (beginItem.isValidItem() && !beginItem.hasJoin()) {
				heroEquipment.removeByItem(beginItem, ItemRemoveType.VALID_DELETE);
				return;
			}
			BaseItem endItem = playerBag.getItemByPos(endPos);
			if (endItem != null) {
				if ((heroEquipment.isEquipSlot(beginPos) == true) && (heroEquipment.canEquipSlotContains(beginPos, endItem.getItemTempInfo()) == true) && (heroEquipment.isValid(endItem) == true)) {
					heroEquipment.takeOutByItem(beginItem);
					playerBag.takeOutByItem(endItem);
					playerBag.addEmptyByPos(beginItem, endPos);
					heroEquipment.addEmptyByPos(endItem, beginPos);
					return;
				}
				endPos = playerBag.getEmptyPos();
			}
			if (playerBag.checkEmpty(endPos)) {
				if (heroEquipment.takeOutByItem(beginItem)) {
					playerBag.addEmptyByItem(beginItem);
				}
			}
		} finally {
			updateHeroProperties(heroEquipment.getObjectId());
		}
	}

	/**
	 * 领主背包->装备
	 */
	private void playerToEquipment(BagHeroEquipment heroEquipment, short beginPos, short endPos, short count) {
		try {
			if (playerBag == null || heroEquipment == null) {
				return;
			}
			BaseItem beginItem = playerBag.getItemByPos(beginPos);
			if ((beginItem == null) || (beginItem.getItemInfo() == null) || (beginItem.getItemTempInfo() == null)) {
				return;
			}

			if (heroEquipment.isEquipSlot(endPos) == false || heroEquipment.canEquipSlotContains(endPos, beginItem.getItemTempInfo()) == false || (heroEquipment.isValid(beginItem) == false)) {
				return;
			}
			BaseItem endItem = heroEquipment.getItemByPos(endPos);
			if (endItem != null) {
				heroEquipment.takeOutByItem(endItem);
			}
			if (playerBag.takeOutByItem(beginItem)) {
				heroEquipment.addEmptyByPos(beginItem, endPos);
				if (endItem != null) {
					playerBag.addEmptyByItem(endItem);
				}
			}
		} finally {
			updateHeroProperties(heroEquipment.getObjectId());
		}
	}

	public void onUseItem(int templateId, boolean isUseItem) {
		// TODO 触发使用物品事件
	}

}
