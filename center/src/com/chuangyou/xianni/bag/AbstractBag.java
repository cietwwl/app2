package com.chuangyou.xianni.bag;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * <pre>
 * 抽象背包:仅此对格子基础操作
 * </pre>
 */
public abstract class AbstractBag {

	protected short				capability;
	private short				bagType;
	protected short				beginPos;
	private boolean				autoStack;
	protected BaseItem[]		baseItems;
	private long				objectId;
	GamePlayer					player;
	protected Object			lock;
	// 删除物品
	protected List<BaseItem>	removedList;

	/**
	 * @param capability
	 *            容量
	 * @param type
	 *            背包类型
	 * @param beginPos
	 *            起始位置
	 * @param autoStack
	 *            是否自动叠加
	 */
	public AbstractBag(short capability, short bagType, short beginPos, boolean autoStack, GamePlayer player,
			long objectId) {
		changeCount = new AtomicInteger();
		changeCount.set(0);
		this.capability = capability;
		this.bagType = bagType;
		this.beginPos = beginPos;
		this.autoStack = autoStack;
		this.objectId = objectId;
		baseItems = new BaseItem[capability];
		this.player = player;
		lock = new Object();
		removedList = new ArrayList<BaseItem>();
	}

	public short getCapability() {
		return capability;
	}

	public short getBagType() {
		return bagType;
	}

	public int getBeginPos() {
		return beginPos;
	}

	public boolean getAutoStack() {
		return autoStack;
	}

	public long getObjectId() {
		return objectId;
	}

	public BaseItem[] getBaseItem() {
		return baseItems;
	}

	/* 获取背包中的全部物品 */
	public List<BaseItem> getItems() {
		List<BaseItem> list = new ArrayList<BaseItem>();
		for (int i = beginPos; i < capability; i++) {
			if (baseItems[i] != null) {
				list.add(baseItems[i]);
			}
		}
		return list;
	}

	/* 背包扩容 */
	public boolean addCapability(short addBagCount) {
		try {
			ArrayExpander ae = new ArrayExpander();
			baseItems = (BaseItem[]) ae.expand(baseItems, capability + addBagCount);
			this.capability = (short) (this.capability + addBagCount);
			return true;
		} catch (Exception e) {
			Log.error("扩展背包容量出错", e);
			return false;
		}
	}

	/* 更新物品属性 */
	public void updateItem(BaseItem baseItem) {
		if (baseItem.getItemInfo().getBagType() == bagType) {
			if (baseItem.getItemInfo().getCount() <= 0) {
				removeByItem(baseItem, ItemRemoveType.TOCLEAR);
			} else {
				onPlaceChanged(baseItem.getItemInfo().getPos());
			}
		}
	}

	/* 移动两个格子之间数量 */
	public boolean moveItem(short startPos, short endPos, int count) {
		// 第一步：检验当前条件是否合法
		if ((startPos < 0) || (endPos < 0) || (startPos >= capability) || (endPos >= capability) || (count < 0))
			return false;
		if (startPos == endPos)
			return false;
		boolean result = false;
		if (!stackByPos(startPos, endPos, count)) {
			// 交换两个格子中的物品
			result = exchangeItem(startPos, endPos);
		} else {
			result = true;
		}

		if (result == true) {
			onPlaceChanged(startPos);
			onPlaceChanged(endPos);
		}
		return result;
	}

	/**
	 * 计算背包加成
	 * 
	 * @param bagData
	 * @param bagPer
	 */
	public abstract void getBagJoin(BaseProperty bagData, BaseProperty bagPer);

	/**
	 * 获取背包技能
	 */
	public abstract String getSkill();

	/* 叠加两个格子物品，指定数量 */
	public boolean stackByPos(short beginPos, short endPos, int itemCount) {
		if (beginPos == endPos) {
			return false;
		}
		BaseItem beginItem = baseItems[beginPos];
		BaseItem endItem = baseItems[endPos];
		if (beginItem == null) {
			return false;
		}
		if (itemCount < 0) {
			return false;
		}
		// 如果没有指定移动数量,0表示全部移动
		if (itemCount == 0) {
			if (beginItem.getItemInfo().getCount() > 0) {
				itemCount = beginItem.getItemInfo().getCount();
			} else {
				itemCount = 1;
			}
		}

		if (itemCount > beginItem.getItemInfo().getCount()) {
			return false;
		}

		// 先拆分后叠加
		if (endItem != null) {
			// 移动到目标后，总数量大于物品模板最大配置数量
			if (itemCount + endItem.getItemInfo().getCount() > endItem.getItemTempInfo().getAmount()) {
				// 剩余数量
				int des = endItem.getItemTempInfo().getAmount() - endItem.getItemInfo().getCount();
				if (des <= 0) {
					return false;
				}

				// 开始物品
				beginItem.decItem(des, endItem.getItemInfo().getId(), ItemRemoveType.SPLIT);

				// 结束物品
				endItem.addItem(des, beginItem.getItemInfo().getId(), beginItem.getItemInfo().isBinds(),
						ItemAddType.OVERLAY);
				// 更新物品锁状态
			} else {
				if (itemCount == beginItem.getItemInfo().getCount()) {
					removeByItem(beginItem, ItemRemoveType.SPILTDELETE);
				} else {
					beginItem.decItem(itemCount, endItem.getItemInfo().getId(), ItemRemoveType.SPLIT);
				}
				endItem.addItem(itemCount, beginItem.getItemInfo().getId(), beginItem.getItemInfo().isBinds(),
						ItemAddType.OVERLAY);
				// 更新物品锁状态
			}
			return true;
		} else if ((endItem == null) && (beginItem.getItemInfo().getCount() > itemCount)) {// 只拆分后新增
			BaseItem ghostBaseItem = ItemManager.ghost(beginItem, player.getPlayerId(), itemCount,
					ItemAddType.SPILTCREATE);
			if (addEmptyByPos(ghostBaseItem, endPos)) {
				beginItem.decItem(itemCount, ghostBaseItem.getItemInfo().getId(), ItemRemoveType.SPLIT);
				return true;
			}
		}
		return false;
	}

	/* 指定两个物品是否可叠 */
	public boolean stackByItem(BaseItem baseItem) {
		if (baseItem == null) {
			return false;
		}
		for (int i = capability - 1; i >= 0; i--) {
			if ((baseItems[i] != null) && (baseItem.statckForAuto(baseItems[i]) && (baseItem.getItemInfo().getCount()
					+ baseItems[i].getItemInfo().getCount() <= baseItems[i].getItemTempInfo().getAmount()))) {
				BaseItem otherBaseItem = baseItems[i];
				otherBaseItem.addItem(baseItem.getItemInfo().getCount(), baseItem.getItemInfo().getId(),
						baseItem.getItemInfo().isBinds(), baseItem.getItemInfo().getAddType());
				baseItem.getItemInfo().setExist(false);
				updateItem(baseItems[i]);
				return true;
			}
		}
		return false;
	}

	/** 移出将当前物品 */
	public boolean takeOutByItem(BaseItem baseItem) {
		if (baseItem == null) {
			return false;
		}
		int pos = -1;
		synchronized (lock) {
			for (int i = 0; i < getCapability(); i++) {
				if (baseItems[i] == baseItem) {
					pos = i;
					baseItems[i] = null;
					break;
				}
			}
		}
		if (pos != -1) {
			onPlaceChanged(pos);
			return true;
		}
		return false;
	}

	/* 移出将当前物品 */
	public boolean takeOutByPos(int pos) {
		return takeOutByItem(getItemByPos(pos));
	}

	/* 移除指定物品 */
	public boolean removeByItem(BaseItem item, short removeType) {
		if (item == null)
			return false;
		int pos = -1;
		synchronized (lock) {
			for (int i = 0; i < getCapability(); i++) {
				if (baseItems[i] == item) {
					pos = i;
					baseItems[i] = null;
					break;
				}
			}
		}
		if (pos != -1) {
			onPlaceChanged(pos);
			if (item.getItemInfo().getBagType() == this.getBagType() && item.getItemInfo().getPos() == pos) {
				item.delItem(item.getItemInfo().getCount(), removeType);
				synchronized (removedList) {
					removedList.add(item);
				}
			}
		}
		return pos != -1;
	}

	/* 移除指定位置物品 */
	public boolean removeByPos(short pos, short removeType) {
		return removeByItem(getItemByPos(pos), removeType);
	}

	/* 查找一个空格子，将该物品添加到该格子 */
	public boolean addEmptyByItem(BaseItem baseItem) {
		if (baseItem == null) {
			return false;
		}
		short pos = getEmptyPos(beginPos);
		return addEmptyByPos(baseItem, pos);
	}

	/* 指定一个空格子，将该物品添加到该格子 */
	public boolean addEmptyByPos(BaseItem baseItem, short pos) {
		if (baseItem == null || baseItem.getItemInfo() == null || baseItem.getItemTempInfo() == null) {
			return false;
		}
		// 位置非法
		if (pos >= capability || pos < 0) {
			return false;
		}
		// 物品是否可以放入
		if (checkBag(baseItem) == false) {
			return false;
		}
		// 是否空位
		if (baseItems[pos] != null) {
			pos = -1;
		} else {
			baseItems[pos] = baseItem;
			baseItem.moveItem(player.getPlayerId(), pos, getObjectId(), getBagType());
		}
		if (pos != -1)
			onPlaceChanged(pos);
		return pos != -1;
	}

	public boolean stackAllItems() {
		synchronized (lock) {
			try {
				for (int i = 0; i < capability; i++) {
					if (baseItems[i] == null) {
						continue;
					}
					for (int j = 0; j < capability; j++) {
						if ((baseItems[j] != null) && (baseItems[i] != null) && (baseItems[i] != baseItems[j])
								&& (baseItems[j].statckForAuto(baseItems[i]) && baseItems[i].getItemInfo().getCount()
										+ baseItems[j].getItemInfo().getCount() <= baseItems[j].getItemTempInfo()
												.getAmount())) {
							// 物品锁状态中更新
							baseItems[i].addItem(baseItems[j].getItemInfo().getCount(), 0,
									baseItems[j].getItemInfo().isBinds(), ItemAddType.SPILTCREATE);
							removeByItem(baseItems[j], ItemRemoveType.SPILTDELETE);
							updateItem(baseItems[i]);
						}
					}
					for (int place = 0; place < baseItems[i].getItemInfo().getPos(); place++) {
						if ((baseItems[place] == null) && (baseItems[place] != baseItems[i])) {
							exchangeItem((short) i, (short) place);
							break;
						}
					}
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/* 两个位置交换记录 */
	protected boolean exchangeItem(short startPos, short endPos) {
		if (startPos == endPos)
			return false;
		BaseItem startBase = baseItems[endPos];
		BaseItem endBase = baseItems[startPos];
		baseItems[startPos] = startBase;
		baseItems[endPos] = endBase;
		if (startBase != null) {
			startBase.moveItem(player.getPlayerId(), startPos, startBase.getItemInfo().getObjectId(), getBagType());
		}
		if (endBase != null) {
			endBase.moveItem(player.getPlayerId(), endPos, endBase.getItemInfo().getObjectId(), getBagType());
		}
		return true;

	}

	/* 获取指定格子中的物品 */
	public BaseItem getItemByPos(int pos) {
		if ((pos < 0) || (pos >= capability))
			return null;
		return baseItems[pos];
	}

	/* 指定范围 查找空格子 */
	public short getEmptyPos() {
		return getEmptyPos(beginPos);
	}

	/* 指定范围 查找空格子 */
	public short getEmptyPos(short minPos) {
		if (minPos >= capability)
			return -1;
		for (short i = minPos; i < capability; i++) {
			if (baseItems[i] == null) {
				return i;
			}
		}
		return -1;
	}

	/* 查找空位数量 */
	public short getEmptyCount() {
		return getEmptyCount(beginPos);
	}

	/* 查找空格子数量 */
	private short getEmptyCount(short minPos) {
		if (minPos < 0 || minPos >= capability)
			return 0;
		short count = 0;
		for (short i = minPos; i < capability; i++) {
			if (baseItems[i] == null) {
				count = (short) (count + 1);
			}
		}
		return count;
	}

	/* 检测当前是否为空格子 */
	public boolean checkEmpty(int pos) {
		if ((pos >= 0) && (pos < capability))
			return baseItems[pos] == null;
		else
			return false;
	}

	public int getAllItemCount() {
		int count = 0;
		synchronized (lock) {
			for (int i = 0; i < capability; i++) {
				if (baseItems[i] != null) {
					count += baseItems[i].getItemInfo().getCount();
				}
			}
		}
		return count;
	}

	/* 检测物品是否可添加到当前背包 */
	protected boolean checkBag(BaseItem baseItem) {
		switch (getBagType()) {
			case BagType.Play:
				return true;
			case BagType.HeroEquipment:
				return true;
			case BagType.VirtualValue:
				return true;
			default:
				return false;
		}
	}

	/* 获取所有物品排列位置 */
	public Hashtable<Short, BaseItem> getRawSpaces() {
		Hashtable<Short, BaseItem> infos = new Hashtable<Short, BaseItem>();
		for (Short i = 0; i < baseItems.length; i++) {
			if (baseItems[i] != null)
				infos.put(i, baseItems[i]);
		}
		return infos;
	}

	/* 获取所有物品排列位置 */
	public Map<Integer, BaseItem> getRawSpaces(Map<Integer, Integer> counts) {
		Map<Integer, BaseItem> dics = new HashMap<Integer, BaseItem>();
		synchronized (lock) {
			for (int i = 0; i < baseItems.length; i++) {
				if (baseItems[i] != null) {
					dics.put(i, baseItems[i]);
					counts.put(i, baseItems[i].getItemInfo().getCount());
				}
			}
		}
		return dics;
	}

	/* 清除所有物品 */
	public void clear() {
		beginChanges();
		for (int i = 0; i < capability; i++) {
			baseItems[i] = null;
			onPlaceChanged(i);
		}
		commitChanges();
	}

	protected List<Integer>	changedPlaces	= new ArrayList<Integer>();

	private AtomicInteger	changeCount;

	protected void onPlaceChanged(int place) {
		synchronized (lock) {
			if (changedPlaces.contains(place) == false) {
				changedPlaces.add(place);
			}
		}
		if ((changeCount.intValue() <= 0) && (changedPlaces.size() > 0)) {
			updateChangedPlaces();
		}

	}

	public void updateAll() {
		beginChanges();
		for (int i = 0; i < capability; i++) {
			if (baseItems[i] != null) {
				onPlaceChanged(i);
			}
		}
		commitChanges();
	}

	public void beginChanges() {
		changeCount.incrementAndGet();
	}

	public void commitChanges() {

		int changes = changeCount.decrementAndGet();
		if (changes < 0) {
			Log.error("Inventory changes counter is bellow zero (forgot to use BeginChanges?)!\n\n");
			changeCount.set(0);
		}
		if ((changes <= 0) && (changedPlaces.size() > 0)) {
			updateChangedPlaces();
		}

	}

	public void updateChangedPlaces() {
		synchronized (lock) {
			changedPlaces.clear();
		}
	}

	public final class ArrayExpander {
		@SuppressWarnings("rawtypes")
		public Object expand(Object array, int newSize) {
			synchronized (lock) {
				if (array == null) {
					return null;
				}
				Class c = array.getClass();
				if (c.isArray()) {
					int len = Array.getLength(array);
					if (len >= newSize) {
						return array;
					} else {
						Class cc = c.getComponentType();
						Object newArray = Array.newInstance(cc, newSize);
						System.arraycopy(array, 0, newArray, 0, len);
						return newArray;
					}
				} else {
					throw new ClassCastException("need  array");
				}
			}
		}
	}

}
