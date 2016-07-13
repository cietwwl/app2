package com.chuangyou.xianni.npcShop.manager;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.npcShop.NpcShopCfg;
import com.chuangyou.xianni.entity.npcShop.NpcShopServerCache;
import com.chuangyou.xianni.npcShop.template.NpcShopTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class NpcShopServerManager {

	/** 初始化完成标记 */
	private static boolean isInited = false;
	/** 商店数据 */
	private static Map<Short, NpcShopServerCache> shops;

	private static Object buyLock = new Object();

	/** 初始化 */
	public static boolean init() {
		loadFromDatabase();
		isInited = true;
		return true;
	}

	/** 获取相关信息 */
	public static NpcShopServerCache get(short privateId) {
		if (isInited == false) {
			init();
		}
		return shops.get(privateId);
	}

	/**
	 * 申请购买某种物品
	 * 
	 * @param privateId
	 * @param num
	 * @return :true 可成功购买 并修改相关数据 false:不可成功购买
	 */
	public static boolean buy(short privateId, short num, GamePlayer player) {
		if (isInited == false) {
			init();
		}
		synchronized (buyLock) {
			NpcShopCfg cfg = NpcShopTemplateMgr.getNpcShopCfg(privateId);
			if (cfg == null)
				return false;
			long totalC = cfg.getPrice() * num;
			NpcShopServerCache serverCache = shops.get(privateId);
			if (cfg.getAmount() > 0 && serverCache.getBuyNum() + num > cfg.getAmount()) {
				return false; // 数据不够
			}
			int buyNum = player.getNpcShopInventory().get(privateId).getBuyNum() + num;
			if (cfg.getLimitBuynum() > 0 && buyNum > cfg.getLimitBuynum()) {
				return false; // 数量不够
			}
			if (cfg.getConsumeType() == 1) { // 消耗钱

				if (cfg.getMoneyType() == EnumAttr.MONEY.getValue()) {
					if (totalC > player.getBasePlayer().getPlayerInfo().getMoney()) {
						return false;
					} else {
						player.getBasePlayer().consumeMoney((int) totalC);
						player.getBagInventory().addItemInBagOrEmail(cfg.getItemType(), num, ItemAddType.NPC_SHOP_ADD,
								true);
					}
				} else if (cfg.getMoneyType() == EnumAttr.CASH.getValue()) {
					if (totalC > player.getBasePlayer().getPlayerInfo().getCash()) {
						return false;
					} else {
						player.getBasePlayer().consumeCash((int) totalC);
						player.getBagInventory().addItemInBagOrEmail(cfg.getItemType(), num, ItemAddType.NPC_SHOP_ADD,
								true);
					}
				} else if (cfg.getMoneyType() == EnumAttr.CASH_BIND.getValue()) {
					if (totalC > player.getBasePlayer().getPlayerInfo().getBindCash() +  player.getBasePlayer().getPlayerInfo().getCash()) {
						return false;
					} else {
						if(totalC>player.getBasePlayer().getPlayerInfo().getBindCash()){
							long temp = (totalC - player.getBasePlayer().getPlayerInfo().getBindCash());
							player.getBasePlayer().consumeBindCach(player.getBasePlayer().getPlayerInfo().getBindCash());
							player.getBasePlayer().consumeCash((int)temp);
						}else{							
							player.getBasePlayer().consumeBindCach((int) totalC);
						}
						player.getBagInventory().addItemInBagOrEmail(cfg.getItemType(), num, ItemAddType.NPC_SHOP_ADD,
								true);
					}
				}else{
					return false;
				}
			} else if (cfg.getConsumeType() == 2) { // 消耗物品
				if(player.getBagInventory().getPlayerBag().isEnough(cfg.getMoneyType(), num)== false){
					return false;
				}else{
					player.getBagInventory().removeItem(cfg.getMoneyType(), num, ItemRemoveType.NPC_SHOP);
					player.getBagInventory().addItemInBagOrEmail(cfg.getItemType(), num, ItemAddType.NPC_SHOP_ADD, true);
				}				
			}

			serverCache.setBuyNum(serverCache.getBuyNum() + num);
			serverCache.setOp(Option.Update);
			player.getNpcShopInventory().get(privateId).setBuyNum((short) buyNum);
			player.getNpcShopInventory().get(privateId).setOp(Option.Update);
			return true;
		}
	}

	////////////////////////////////////////////////////////////////////////
	//
	/**
	 * 加载DB数据
	 * 
	 * @return
	 */
	private static void loadFromDatabase() {
		shops = DBManager.getNpcshopdao().getAll();

		// 检测是否有静态表中的数据而全服表中没有。
		for (NpcShopCfg cfg : NpcShopTemplateMgr.getShops().values()) {
			NpcShopServerCache serverCache = shops.get(cfg.getId());
			if (serverCache == null) {
				serverCache = new NpcShopServerCache();
				serverCache.setBuyNum(0);
				serverCache.setPrivateId(cfg.getId());
				serverCache.setResetTime((short) 0);
				shops.put(serverCache.getPrivateId(), serverCache);
				serverCache.setOp(Option.Insert);
			}
		}
		update();
	}

	/**
	 * 保存数据入DB
	 */
	public static void save() {
		if (isInited == false)
			return;
		Iterator<Map.Entry<Short, NpcShopServerCache>> entries = shops.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<Short, NpcShopServerCache> entry = entries.next();
			NpcShopServerCache shop = entry.getValue();

			short option = shop.getOp();
			if (option == Option.Update) {
				DBManager.getNpcshopdao().updateNpcShopServerCache(shop);
			} else if (option == Option.Insert) {
				DBManager.getNpcshopdao().addNpcShopServerCache(shop);
			}
		}
	}

	/**
	 * 更新状态
	 */
	public static void update() {
		if (isInited == false)
			return;
		Iterator<Map.Entry<Short, NpcShopServerCache>> entries = shops.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<Short, NpcShopServerCache> entry = entries.next();
			NpcShopServerCache shop = entry.getValue();
			NpcShopCfg cfg = NpcShopTemplateMgr.getNpcShopCfg(shop.getPrivateId());
			if (!cfg.isExpired() && cfg.getAmount() > 0) {
				long mm = new Date().getTime() - TimeUtil.getDateByString(cfg.getStartTime(),cfg.getTimeType()).getTime();
				long minute = mm / (1000 * 60);
				long time = minute / cfg.getResetTime();
				if (shop.getResetTime() < time) {
					synchronized (buyLock) {
						shop.setBuyNum(0);
					}
					shop.setResetTime((short) time);
					shop.setOp(Option.Update);
				}
			}
		}
	}

}
