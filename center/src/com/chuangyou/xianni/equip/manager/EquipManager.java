package com.chuangyou.xianni.equip.manager;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipInfoRespProto.EquipInfoRespMsg;
import com.chuangyou.common.protobuf.pb.item.ItemPosProto.ItemPosMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.bag.BaseBag;
import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.bag.ItemLogHelper;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.EquipConstant;
import com.chuangyou.xianni.constant.ItemType;
import com.chuangyou.xianni.entity.equip.EquipAwakenCfg;
import com.chuangyou.xianni.entity.equip.EquipSuitCfg;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemChangeType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.equip.EquipOperateAction;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class EquipManager {

	public static void weaponAwaken(GamePlayer player, ItemPosMsg itemPos, short protocol){
		BaseItem equip = checkItem(player, itemPos.getBagType(), itemPos.getPosition(), itemPos.getItemId(), protocol, true);
		if(equip == null) return;
		if(equip.getItemTempInfo().getMasterType() != ItemType.MainType.EQUIP || equip.getItemTempInfo().getSonType() != EquipConstant.EquipType.weapon){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "类型错误");
			return;
		}
		long awakenTempId = equip.getTemplateId()*10000l + equip.getItemInfo().getAwaken()*100 + equip.getItemInfo().getAwakenPoint();
		EquipAwakenCfg cfg = EquipTemplateMgr.getAwakenMap().get(awakenTempId);
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "装备觉醒配置错误");
			return;
		}
		long nextAwakenTempId = (long)equip.getTemplateId()*10000 + (equip.getItemInfo().getAwaken()+1)*100 + 0;
		if(EquipTemplateMgr.getAwakenMap().get(nextAwakenTempId) == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_AWAKEN_MAX, protocol, "觉醒等级已达到上限");
			return;
		}
		
		if(player.getBagInventory().getItemCount(cfg.getNeedItem()) < cfg.getNeedItemNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, protocol, "物品数量不足");
			return;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(cfg.getNeedItem(), cfg.getNeedItemNum(), ItemRemoveType.EQUIP_AWAKEN)) return;
		
		
		ItemLogHelper logHelper = new ItemLogHelper(equip);
		boolean hasChange = false;
		boolean uplevel = false;
		boolean isSuccess = (new ThreadSafeRandom()).isSuccessful(cfg.getRate(), 10000);
		if(isSuccess == true){
			equip.getItemInfo().setAwakenPoint(equip.getItemInfo().getAwakenPoint() + 1);
			if(equip.getItemInfo().getAwakenPoint() >= cfg.getMaxPoint()){
				equip.getItemInfo().setAwaken(equip.getItemInfo().getAwaken() + 1);
				equip.getItemInfo().setAwakenPoint(0);
				uplevel = true;
				hasChange = true;
			}
		}else{
			if(equip.getItemInfo().getAwakenPoint() > 0){
				equip.getItemInfo().setAwakenPoint(equip.getItemInfo().getAwakenPoint() - 1);
				hasChange = true;
			}
		}
		if(hasChange == true){
			logHelper.commitChanges(ItemChangeType.EQUIP_AWAKEN);
		}
		
		EquipInfoRespMsg.Builder msg = EquipInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.Equip.AWAKEN);
		if(isSuccess){
			msg.setResult(0);
		}else{
			msg.setResult(1);
		}
		EquipInfoMsg.Builder infoMsg = EquipInfoMsg.newBuilder();
		infoMsg.setPos(equip.getItemInfo().getPosMsg());
		infoMsg.setAwakenLevel(equip.getItemInfo().getAwaken());
		infoMsg.setAwakenPoint(equip.getItemInfo().getAwakenPoint());
		infoMsg.setStoneTempId(equip.getItemInfo().getStone());
		msg.setEquip(infoMsg);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIP_INFO, msg);
		player.sendPbMessage(p);
		
		if(uplevel == true){
			if(equip.getItemInfo().getBagType() == BagType.HeroEquipment && equip.getItemInfo().getPos() == EquipConstant.EquipPosition.weaponPosition){
				PlayerAttUpdateMsg.Builder attMsg = PlayerAttUpdateMsg.newBuilder();
				PropertyMsg.Builder awakenMsg = PropertyMsg.newBuilder();
				awakenMsg.setType(EnumAttr.WEAPON_AWAKEN.getValue());
				awakenMsg.setTotalPoint(equip.getItemInfo().getAwaken());
				attMsg.addAtt(awakenMsg);
				attMsg.setPlayerId(player.getPlayerId());
				
				PBMessage pkg = MessageUtil.buildMessage(Protocol.S_ATTRIBUTE_SCENE_UPDATE, attMsg);
				player.sendPbMessage(pkg);
			}
		}
	}
	
	public static void equipStone(GamePlayer player, ItemPosMsg equipPos, ItemPosMsg stonePos, short protocol){
		BaseItem equip = checkItem(player, equipPos.getBagType(), equipPos.getPosition(), equipPos.getItemId(), protocol, true);
		if(equip == null) return;
		if(equip.getItemTempInfo().getMasterType() != ItemType.MainType.EQUIP){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "类型错误");
			return;
		}
		
		BaseItem stone = checkItem(player, stonePos.getBagType(), stonePos.getPosition(), stonePos.getItemId(), protocol, true);
		if(stone == null) return;
		if(stone.getItemTempInfo().getMasterType() != ItemType.MainType.STONE){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ITEM_NOT_STONE, protocol, "物品不是宝石");
			return;
		}
		if(stone.getItemTempInfo().getSonType() != equip.getItemTempInfo().getSonType()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.STONE_POS_ERROR, protocol, "宝石装备部位不对");
			return;
		}
		
		if(!player.getBagInventory().removeItemByBaseItem((short)stonePos.getBagType(), stone, 1, ItemRemoveType.EQUIP_STONE)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "未知错误");
			return;
		}
		ItemLogHelper logHelper = new ItemLogHelper(equip);
		equip.getItemInfo().setStone(stone.getTemplateId());
		logHelper.commitChanges(ItemChangeType.EQUIP_STONE);
		
		EquipInfoRespMsg.Builder msg = EquipInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.Equip.STONE);
		EquipInfoMsg.Builder infoMsg = EquipInfoMsg.newBuilder();
		infoMsg.setPos(equip.getItemInfo().getPosMsg());
		infoMsg.setAwakenLevel(equip.getItemInfo().getAwaken());
		infoMsg.setAwakenPoint(equip.getItemInfo().getAwakenPoint());
		infoMsg.setStoneTempId(equip.getItemInfo().getStone());
		msg.setEquip(infoMsg);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIP_INFO, msg);
		player.sendPbMessage(p);
		
		if(equip.getItemInfo().getBagType() == BagType.HeroEquipment){
			player.getBagInventory().updateHeroProperties(player.getPlayerId());
		}
	}
	
	public static boolean equipResolve(GamePlayer player, ItemPosMsg req, short protocol, boolean hint){
		BaseItem equip = checkItem(player, req.getBagType(), req.getPosition(), req.getItemId(), protocol, hint);
		if(equip == null) return false;
		if(equip.getItemTempInfo().getMasterType() != ItemType.MainType.EQUIP){
			if(hint == true) ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "类型错误");
			return false;
		}
		
//		EquipResolveCfg cfg = EquipTemplateMgr.getResolveMap().get(equip.getItemTempInfo().getId());
//		if(cfg == null){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_UN_RESOLVE, protocol, "此装备不可分解");
//			return;
//		}
		
		if(!player.getBagInventory().removeItemByBaseItem((short)req.getBagType(), equip, 1, ItemRemoveType.RESOLVE)){
			if(hint == true) ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "物品扣除失败");
			return false;
		}
		
		//注魂
		if(equip.getItemInfo().getStone() > 0){
			ItemTemplateInfo stoneCfg = ItemManager.findItemTempInfo(equip.getItemInfo().getStone());
			if(stoneCfg != null){
				if(stoneCfg.getSuit_id() > 0){
					EquipSuitCfg suitCfg = EquipTemplateMgr.getSuitMap().get(stoneCfg.getSuit_id());
					if(suitCfg != null){
						player.getBagInventory().addItemInBagOrEmail(suitCfg.getResolveItem(), suitCfg.getResolveNum(), ItemAddType.EQUIP_RESOLVE, true);
					}
				}
			}
		}
//		Map<Integer, Integer> items = cfg.getItems();
//		for(int key: items.keySet()){
//			player.getBagInventory().addItemInBagOrEmail(key, items.get(key), ItemAddType.EQUIP_RESOLVE, true);
//		}
		//经验
//		int exp = equip.getItemTempInfo().getExp();
//		player.getBasePlayer().addEquipExp(exp);
		player.getBagInventory().addItemInBagOrEmail(equip.getItemTempInfo().getResolveId(), equip.getItemTempInfo().getResolveCount(), ItemAddType.EQUIP_RESOLVE, true);
		
		//觉醒，公式出来后添加
		
		if(hint == true){
			EquipInfoRespMsg.Builder msg = EquipInfoRespMsg.newBuilder();
			msg.setAction(EquipOperateAction.Equip.STONE);
			EquipInfoMsg.Builder infoMsg = EquipInfoMsg.newBuilder();
			infoMsg.setPos(equip.getItemInfo().getPosMsg());
			infoMsg.setAwakenLevel(equip.getItemInfo().getAwaken());
			infoMsg.setAwakenPoint(equip.getItemInfo().getAwakenPoint());
			infoMsg.setStoneTempId(equip.getItemInfo().getStone());
			PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIP_INFO, msg);
			player.sendPbMessage(p);
		}
		
		if(equip.getItemInfo().getBagType() == BagType.HeroEquipment){
			player.getBagInventory().updateHeroProperties(player.getPlayerId());
		}
		return true;
	}
	
	public static BaseItem checkItem(GamePlayer player, int bagType, int position, long itemId, short protocol, boolean hint){
		BaseBag bag = player.getBagInventory().getBag(bagType);
		if(bag == null){
			if(hint == true) ErrorMsgUtil.sendErrorMsg(player, ErrorCode.BAG_NOT_FIND, protocol, "背包类型错误");
			return null;
		}
		BaseItem item = bag.getItemByPos(position);
		if(item == null){
			if(hint == true) ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_IS_NOT_Existed, protocol, "物品不存在");
			return null;
		}
		if(item.getItemInfo() == null){
			if(hint == true) ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_IS_NOT_Existed, protocol, "物品不存在");
			return null;
		}
		if(item.getItemInfo().getId() != itemId){
			if(hint == true) ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "提供的装备ID和背包位置不对应");
			return null;
		}
		return item;
	}
}
