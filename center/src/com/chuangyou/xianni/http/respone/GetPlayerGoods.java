package com.chuangyou.xianni.http.respone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.constant.ItemType;
import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.entity.item.ItemInfo;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.http.util.CalFighting;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.impl.EquipBarInfoDaoImpl;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayerGoods", desc = "查询玩家物品")
public class GetPlayerGoods implements BaseRespone {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		int page = 1;
		if (params.get("page") != null) {
			page = Integer.valueOf(params.get("page"));// 当前页
		}
		int pageSize = 20;
		if (params.get("pageSize") != null) {
			pageSize = Integer.valueOf(params.get("pageSize"));// 显示条数
		}
		String nickName = params.get("nickName");
		String itemName = params.get("itemName");

		long playerId = 0;
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info == null) {
				return HttpResResult.getResult(Code.FAIL, "用户不存在");
			}
			playerId = info.getPlayerId();
		}
		Map<String, Object> data = new HashMap<>();
		// GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		List<ItemTemplateInfo> list = DBManager.getItemTemplateInfoDao().getItemTemps(itemName);
		List<Map> itemList = new ArrayList<>();

		List<Integer> itemIds = new ArrayList<>();
		for (ItemTemplateInfo itemTemplateInfo : list) {
			itemIds.add(itemTemplateInfo.getId());
		}
		// List<BaseItem> runBagList = new ArrayList<>();
		List<BaseItem> bagList = new ArrayList<>();
		// if (player != null && player.getPlayerState() == PlayerState.ONLINE
		// && false == true) {// 用户在线
		// BagHeroEquipment bagHeroEquipment =
		// player.getBagInventory().getHeroEquipmentBag();
		// BaseBag playerBag = player.getBagInventory().getPlayerBag();
		// runBagList.addAll(bagHeroEquipment.getItems());
		// runBagList.addAll(playerBag.getItems());
		// for (BaseItem baseItem : runBagList) {
		// if (itemName == null || "".equals(itemName)) {
		// bagList.add(baseItem);
		// }
		// if (itemIds.contains(baseItem.getItemTempInfo().getId())) {
		// bagList.add(baseItem);
		// }
		// }
		// } else {
		List<ItemInfo> items = DBManager.getItemInfoDao().getAllItem(playerId, itemIds, page, pageSize);
		for (ItemInfo itemInfo : items) {
			int tempId = itemInfo.getTemplateId();
			ItemTemplateInfo temp = ItemManager.findItemTempInfo(tempId);
			if (temp != null) {
				BaseItem baseItem = new BaseItem(temp, itemInfo);
				bagList.add(baseItem);
			}
		}
		// }
		CalFighting calFighting = new CalFighting();
		for (BaseItem baseItem : bagList) {
			BaseProperty bagPer = new BaseProperty();
			BaseProperty bagData = new BaseProperty();
			Map m = new HashMap<>();
			m.put("id", baseItem.getItemInfo().getId());
			m.put("tempId", baseItem.getItemTempInfo().getId());
			m.put("typeId", baseItem.getItemTempInfo().getMasterType());
			m.put("itemName", baseItem.getItemTempInfo().getName());
			m.put("position", baseItem.getItemInfo().getPos());
			m.put("status", baseItem.getItemInfo().isBinds());
			// 加持属性
			// EquipBarInfo equipBarInfo =
			// player.getEquipInventory().getEquipBarByPos(baseItem.getItemInfo().getPos());
			EquipBarInfoDaoImpl equipBarInfoDaoImpl = new EquipBarInfoDaoImpl();
			EquipBarInfo equipBarInfo = equipBarInfoDaoImpl.getEquipBarInfo(playerId, baseItem.getItemInfo().getPos());
			if (equipBarInfo != null && baseItem.getItemTempInfo().getMasterType() == ItemType.MainType.EQUIP) {
				m.put("lv", equipBarInfo.getLevel());
				m.put("grade", equipBarInfo.getGrade());
				BaseProperty bagData1 = new BaseProperty();
				calFighting.getJiachiPro(baseItem.getItemTempInfo(), equipBarInfo, bagData1, bagData1);
				calFighting.getJiachiPro(baseItem.getItemTempInfo(), equipBarInfo, bagPer, bagData);
				m.put("jiaChiPro", bagData1);
			} else {
				m.put("lv", baseItem.getItemTempInfo().getLevel());
				m.put("grade", 0);
			}

			m.put("count", baseItem.getItemInfo().getCount());
			m.put("amount", baseItem.getItemTempInfo().getAmount()); // 叠加上线
			m.put("bagType", baseItem.getItemInfo().getBagType());
			// 基础属性
			BaseProperty basePro = new BaseProperty();
			float baseVal = calFighting.getItemProVal(baseItem.getItemInfo());
			SimpleProperty pro = SkillUtil.readPro(baseItem.getItemInfo().getPro());
			SkillUtil.joinPro(basePro, pro.getType(), (int) Math.ceil(baseVal));
			m.put("basePro", basePro);
			// 战力计算
			if (pro.isPre()) {
				SkillUtil.joinPro(bagPer, pro.getType(), (int) Math.ceil(baseVal));
			} else {
				SkillUtil.joinPro(bagData, pro.getType(), (int) Math.ceil(baseVal));
			}
			calFighting.addBag(bagData, bagPer);
			long fighting = calFighting.getFighting();
			m.put("fighting", fighting);
			itemList.add(m);
		}

		data.put("list", itemList);
		data.put("total", DBManager.getItemInfoDao().getCount(playerId, itemIds));
		return HttpResResult.getResult(Code.SUCCESS, data);
	}
}