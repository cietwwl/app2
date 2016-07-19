package com.chuangyou.xianni.player;

import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.army.ArmyInfoMsgProto.ArmyInfoMsg;
import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PetInfoProto.PetInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.item.ItemFaceListMsgProto.ItemFaceListMsg;
import com.chuangyou.common.protobuf.pb.item.ItemFaceMsgProto.ItemFaceMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.army.ArmyInventory;
import com.chuangyou.xianni.bag.BagMessage;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.pet.PetInventory;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class PlayerInfoSendCmd {

	public static PlayerInfoMsg getProperPacket(GamePlayer gamePlayer) {
		try {
			BasePlayer basePlayer = gamePlayer.getBasePlayer();
			PlayerInfo info = basePlayer.getPlayerInfo();

			PlayerInfoMsg.Builder playerInfo = PlayerInfoMsg.newBuilder();
			info.writeProto(playerInfo, SystemConfigTemplateMgr.getIntValue("bag.initGridNum"));

			PostionMsg.Builder postionBuilder = PostionMsg.newBuilder();
			PlayerPositionInfo ppinfo = basePlayer.getPlayerPositionInfo();
			ppinfo.writeProto(postionBuilder);
			playerInfo.setPostionMsg(postionBuilder.build());
			return playerInfo.build();
		} catch (Exception e) {
			Log.error("通知更新领主属性失败!", e);
		}
		return null;
	}

	/**
	 * 玩家属性更新
	 * 
	 * @param changeMap
	 * @param playerId
	 * @return
	 */
	public static PlayerAttUpdateMsg getPropertyUpdatePacket(Map<Integer, Long> changeMap, long playerId){
		PlayerAttUpdateMsg.Builder msg = PlayerAttUpdateMsg.newBuilder();
		msg.setPlayerId(playerId);

		for (int type : changeMap.keySet()) {
			PropertyMsg.Builder attMsg = PropertyMsg.newBuilder();
			attMsg.setType(type);
			attMsg.setTotalPoint(changeMap.get(type));

			msg.addAtt(attMsg);
		}
		return msg.build();
	}

	/**
	 * 获取更新单个人物属性包(new)
	 * 
	 * @param type
	 * @param baseValue
	 * @param totalValue
	 * @return
	 */
	public static PlayerAttUpdateMsg getPropertyUpdatePacket(int type,long totalValue,long playerId){
		PlayerAttUpdateMsg.Builder msg = PlayerAttUpdateMsg.newBuilder();
		msg.setPlayerId(playerId);
		PropertyMsg.Builder childMsg = PropertyMsg.newBuilder();
		childMsg.setType(type);
		childMsg.setTotalPoint(totalValue);
		msg.addAtt(childMsg);
		return msg.build();
	}
	
	/**
	 * 获取人物快照信息包
	 * @param gamePlayer
	 * @return
	 */
	public static ArmyInfoMsg getArmyPacket(GamePlayer gamePlayer) {
		try {
			ArmyInfoMsg.Builder army = ArmyInfoMsg.newBuilder();
			army.setHeorAppearance(getProperPacket(gamePlayer));

			HeroInfoMsg.Builder heroInfo = HeroInfoMsg.newBuilder();
			ArmyInventory armytory = gamePlayer.getArmyInventory();
			if (armytory != null && armytory.getArmy() != null && armytory.getArmy().getHero() != null) {
				armytory.getArmy().getHero().writeHeroProto(gamePlayer, heroInfo);
			}
			army.setHeoBattleInfo(heroInfo);
			
			PetInfoMsg petInfo = getPetInfoPacket(gamePlayer);
			army.setPetBattleInfo(petInfo);
			
			return army.build();
		} catch (Exception e) {
			Log.error("生成部队信息异常", e);
		}
		return null;
	}
	
	/**
	 * 获取宠物信息包
	 * @param player
	 * @return
	 */
	public static PetInfoMsg getPetInfoPacket(GamePlayer player){
		PetInfoMsg.Builder petInfo = PetInfoMsg.newBuilder();
		petInfo.setPlayerId(player.getPlayerId());
		
		PetInventory petInventory = player.getPetInventory();
		if(petInventory == null) return null;
		PetAtt petAtt = player.getPetInventory().getPetAtt();
		petInfo.setPetTempId(petAtt.getFightPetId());
		petInfo.setPetSoul(petAtt.getSoulLv());
		
		if(petInfo.getPetTempId() > 0){
			PetInfo pet = player.getPetInventory().getPetInfo(petAtt.getFightPetId());
			if(pet != null){
				petInfo.setPetPhysique(pet.getPhysique());
				petInfo.setPetQuality(pet.getQuality());
			}
			Map<Integer, Integer> petAttMap = player.getPetInventory().getPetPropertyMap();
			for(int type:petAttMap.keySet()){
				PropertyMsg.Builder property = PropertyMsg.newBuilder();
				property.setType(type);
				property.setTotalPoint(petAttMap.get(type));
				
				petInfo.addPetProperty(property);
			}
		}
		return petInfo.build();
	}

	/**
	 * 发送物品外观列表
	 */
	public static void sendUpdateBagInventory(GamePlayer player, List<BagMessage> bagMessages) {
		ItemFaceListMsg.Builder movedList = ItemFaceListMsg.newBuilder();
		try {
			for (BagMessage info : bagMessages) {
				ItemFaceMsg.Builder updated = ItemFaceMsg.newBuilder();
				if (info.getItemInfo() != null) {
					updated.setTemplateId(info.getItemInfo().getTemplateId());
					updated.setPos(info.getItemInfo().getPos());
					updated.setBagType(info.getItemInfo().getBagType());
					updated.setBind(info.getItemInfo().isBinds());
					updated.setCount(info.getItemInfo().getCount());
				} else {
					updated.setTemplateId(-1);
					updated.setPos(info.getChangePos());
					updated.setBagType(info.getChangeBagType());
				}
				movedList.addItemFace(updated);
			}
			// 发送到客户端
			PBMessage resp = MessageUtil.buildMessage(Protocol.U_ITEM_FACE_LIST, movedList);
			System.out.println(player.getPlayerId() + "发送背包协议-=-------------------------------");
			player.sendPbMessage(resp);
		} catch (Exception e) {
			Log.error(String.format("用户%s物品位置更新出错!", player.getPlayerId()), e);
		}

	}

}
