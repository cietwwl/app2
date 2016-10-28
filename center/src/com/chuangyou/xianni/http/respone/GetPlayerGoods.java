package com.chuangyou.xianni.http.respone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.bag.BagHeroEquipment;
import com.chuangyou.xianni.bag.BaseBag;
import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.constant.ItemType;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.entity.item.ItemInfo;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.equip.EquipInventory;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayerGoods", desc = "查询玩家物品")
public class GetPlayerGoods implements BaseRespone {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getResult(Map<String, String> params) throws Exception {
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
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		List<ItemTemplateInfo> list = DBManager.getItemTemplateInfoDao().getItemTemps(itemName);
		if (list.size() > 0) {
			List<Integer> itemIds = new ArrayList<>();
			for (ItemTemplateInfo itemTemplateInfo : list) {
				itemIds.add(itemTemplateInfo.getId());
			}
			List<BaseItem> bagList = new ArrayList<>();
			if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
				BagHeroEquipment bagHeroEquipment = player.getBagInventory().getHeroEquipmentBag();
				BaseBag playerBag = player.getBagInventory().getPlayerBag();
				bagList.addAll(bagHeroEquipment.getItems());
				bagList.addAll(playerBag.getItems());
				List<Map> itemList = new ArrayList<>();

				for (BaseItem baseItem : bagList) {
					Map m = new HashMap<>();
					m.put("id", baseItem.getItemInfo().getId());
					m.put("typeId", baseItem.getItemTempInfo().getMasterType());
					m.put("itemName", baseItem.getItemTempInfo().getName());
					m.put("position", baseItem.getItemInfo().getPos());
					m.put("status", baseItem.getItemInfo().isBinds());
					if (baseItem.getItemTempInfo().getMasterType() == ItemType.MainType.EQUIP) {
						EquipBarInfo equipBarInfo = player.getEquipInventory().getEquipBarByPos(baseItem.getItemInfo().getPos());
						m.put("lv", equipBarInfo.getLevel());
						m.put("grade", equipBarInfo.getGrade());
						BaseProperty bagData = new BaseProperty();
						player.getBagInventory().getHeroEquipmentBag().getJiachiPro(baseItem.getItemTempInfo(), equipBarInfo, bagData, bagData);
						m.put("jiaChiPro", bagData);
					} else {
						m.put("lv", baseItem.getItemTempInfo().getLevel());
						m.put("grade", 0);
					}
					m.put("count", baseItem.getItemInfo().getCount());
					m.put("amount", baseItem.getItemTempInfo().getAmount());

					BaseProperty bagData = new BaseProperty();
					SimpleProperty pro = SkillUtil.readPro(baseItem.getItemInfo().getPro());
					SkillUtil.joinPro(bagData, pro.getType(), pro.getValue());
					m.put("basePro", bagData);
				}
			} else {

			}
		}

		List<ItemInfo> infos = DBManager.getItemInfoDao().getAllItem(playerId);

		return HttpResResult.getResult(Code.SUCCESS, data);
	}
}