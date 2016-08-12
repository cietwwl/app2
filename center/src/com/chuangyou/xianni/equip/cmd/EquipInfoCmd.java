package com.chuangyou.xianni.equip.cmd;

import com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipInfoReqProto.EquipInfoReqMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipInfoRespProto.EquipInfoRespMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.bag.BaseBag;
import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.EquipConstant;
import com.chuangyou.xianni.constant.ItemType;
import com.chuangyou.xianni.entity.equip.EquipAwakenCfg;
import com.chuangyou.xianni.entity.equip.EquipSuitCfg;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.equip.EquipOperateAction;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_EQUIP_INFO, desc = "装备信息操作")
public class EquipInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		EquipInfoReqMsg req = EquipInfoReqMsg.parseFrom(packet.getBytes());
		
		switch(req.getAction()){
		case EquipOperateAction.Equip.AWAKEN:
			weaponAwaken(player, req, packet.getCode());
			break;
		case EquipOperateAction.Equip.STONE:
			equipStone(player, req, packet.getCode());
			break;
		case EquipOperateAction.Equip.RESOLVE:
			equipResolve(player, req, packet.getCode());
			break;
		}
	}
	
	private void weaponAwaken(GamePlayer player, EquipInfoReqMsg req, short protocol){
		BaseItem equip = checkItem(player, req.getBagType(), req.getPosition(), req.getEquipId(), protocol);
		if(equip == null) return;
		if(equip.getItemTempInfo().getMasterType() != ItemType.MainType.EQUIP || equip.getItemTempInfo().getSonType() != EquipConstant.EquipType.weapon){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "类型错误");
			return;
		}
		
		EquipAwakenCfg cfg = EquipTemplateMgr.getAwakenMap().get(equip.getTemplateId()*10000 + equip.getItemInfo().getAwaken()*100 + equip.getItemInfo().getAwakenPoint());
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "装备觉醒配置错误");
			return;
		}
		if(EquipTemplateMgr.getAwakenMap().get(equip.getTemplateId()*10000 + (equip.getItemInfo().getAwaken()+1)*100 + 0) == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_AWAKEN_MAX, protocol, "觉醒等级已达到上限");
			return;
		}
		
		if(player.getBagInventory().getItemCount(cfg.getNeedItem()) < cfg.getNeedItemNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, protocol, "物品数量不足");
			return;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(cfg.getNeedItem(), cfg.getNeedItemNum(), ItemRemoveType.USE)) return;
		
		boolean isSuccess = (new ThreadSafeRandom()).isSuccessful(cfg.getRate(), 10000);
		if(isSuccess == true){
			equip.getItemInfo().setAwakenPoint(equip.getItemInfo().getAwakenPoint() + 1);
			if(equip.getItemInfo().getAwakenPoint() >= cfg.getMaxPoint()){
				equip.getItemInfo().setAwaken(equip.getItemInfo().getAwaken() + 1);
				equip.getItemInfo().setAwakenPoint(0);
			}
		}else{
			if(equip.getItemInfo().getAwakenPoint() > 0){
				equip.getItemInfo().setAwakenPoint(equip.getItemInfo().getAwakenPoint() - 1);
			}
		}
		
		EquipInfoRespMsg.Builder msg = EquipInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.Equip.AWAKEN);
		EquipInfoMsg.Builder infoMsg = EquipInfoMsg.newBuilder();
		infoMsg.setEquipId(equip.getItemInfo().getId());
		infoMsg.setAwakenLevel(equip.getItemInfo().getAwaken());
		infoMsg.setAwakenPoint(equip.getItemInfo().getAwakenPoint());
		infoMsg.setStoneTempId(equip.getItemInfo().getStone());
		PBMessage p = MessageUtil.buildMessage(Protocol.C_EQUIP_INFO, msg);
		player.sendPbMessage(p);
	}
	
	private void equipStone(GamePlayer player, EquipInfoReqMsg req, short protocol){
		BaseItem equip = checkItem(player, req.getBagType(), req.getPosition(), req.getEquipId(), protocol);
		if(equip == null) return;
		if(equip.getItemTempInfo().getMasterType() != ItemType.MainType.EQUIP || equip.getItemTempInfo().getSonType() != EquipConstant.EquipType.weapon){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "类型错误");
			return;
		}
		
		BaseItem stone = checkItem(player, req.getStoneBagType(), req.getStonePos(), req.getStoneId(), protocol);
		if(stone == null) return;
		if(stone.getItemTempInfo().getMasterType() != ItemType.MainType.STONE){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ITEM_NOT_STONE, protocol, "物品不是宝石");
			return;
		}
		if(stone.getItemTempInfo().getSonType() != equip.getItemTempInfo().getSonType()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.STONE_POS_ERROR, protocol, "宝石装备部位不对");
			return;
		}
		
		if(!player.getBagInventory().getBag(req.getStoneBagType()).removeByPos((short)req.getStonePos(), ItemRemoveType.USE)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "未知错误");
			return;
		}
		
		equip.getItemInfo().setStone(stone.getTemplateId());
		
		EquipInfoRespMsg.Builder msg = EquipInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.Equip.STONE);
		EquipInfoMsg.Builder infoMsg = EquipInfoMsg.newBuilder();
		infoMsg.setEquipId(equip.getItemInfo().getId());
		infoMsg.setAwakenLevel(equip.getItemInfo().getAwaken());
		infoMsg.setAwakenPoint(equip.getItemInfo().getAwakenPoint());
		infoMsg.setStoneTempId(equip.getItemInfo().getStone());
		PBMessage p = MessageUtil.buildMessage(Protocol.C_EQUIP_INFO, msg);
		player.sendPbMessage(p);
		
		if(equip.getItemInfo().getBagType() == BagType.HeroEquipment){
			player.getBagInventory().updateHeroProperties(player.getPlayerId());
		}
	}
	
	private void equipResolve(GamePlayer player, EquipInfoReqMsg req, short protocol){
		BaseItem equip = checkItem(player, req.getBagType(), req.getPosition(), req.getEquipId(), protocol);
		if(equip == null) return;
		if(equip.getItemTempInfo().getMasterType() != ItemType.MainType.EQUIP){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "类型错误");
			return;
		}
		
//		EquipResolveCfg cfg = EquipTemplateMgr.getResolveMap().get(equip.getItemTempInfo().getId());
//		if(cfg == null){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_UN_RESOLVE, protocol, "此装备不可分解");
//			return;
//		}
		
		if(!player.getBagInventory().getBag(req.getBagType()).removeByPos((short)req.getPosition(), ItemRemoveType.RESOLVE)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "物品扣除失败");
			return;
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
		int exp = equip.getItemTempInfo().getExp();
		player.getBasePlayer().addEquipExp(exp);
		
		//觉醒，公式出来后添加
		
		EquipInfoRespMsg.Builder msg = EquipInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.Equip.STONE);
		EquipInfoMsg.Builder infoMsg = EquipInfoMsg.newBuilder();
		infoMsg.setEquipId(equip.getItemInfo().getId());
		infoMsg.setAwakenLevel(equip.getItemInfo().getAwaken());
		infoMsg.setAwakenPoint(equip.getItemInfo().getAwakenPoint());
		infoMsg.setStoneTempId(equip.getItemInfo().getStone());
		PBMessage p = MessageUtil.buildMessage(Protocol.C_EQUIP_INFO, msg);
		player.sendPbMessage(p);
		
		if(equip.getItemInfo().getBagType() == BagType.HeroEquipment){
			player.getBagInventory().updateHeroProperties(player.getPlayerId());
		}
	}
	
	private BaseItem checkItem(GamePlayer player, int bagType, int position, long itemId, short protocol){
		BaseBag bag = player.getBagInventory().getBag(bagType);
		if(bag == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.BAG_NOT_FIND, protocol, "背包类型错误");
			return null;
		}
		BaseItem item = bag.getItemByPos(position);
		if(item == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_IS_NOT_Existed, protocol, "物品不存在");
			return null;
		}
		if(item.getItemInfo() == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_IS_NOT_Existed, protocol, "物品不存在");
			return null;
		}
		if(item.getItemInfo().getId() != itemId){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "提供的装备ID和背包位置不对应");
			return null;
		}
		return item;
	}

}
