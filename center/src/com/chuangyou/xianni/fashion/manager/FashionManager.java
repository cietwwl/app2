package com.chuangyou.xianni.fashion.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg;
import com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.EquipConstant;
import com.chuangyou.xianni.entity.fashion.FashionCfg;
import com.chuangyou.xianni.entity.fashion.FashionInfo;
import com.chuangyou.xianni.entity.fashion.FashionLevelCfg;
import com.chuangyou.xianni.entity.fashion.FashionQualityCfg;
import com.chuangyou.xianni.fashion.template.FashionTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class FashionManager {
	/** 转换为序列化bean */
	public static FashionBeanMsg.Builder toBean(FashionInfo info) {
		FashionBeanMsg.Builder bean = FashionBeanMsg.newBuilder();
		bean.setId(info.getFashionId());
		bean.setQuality(info.getQuality());
		bean.setLevel(info.getLevel());
		bean.setExp(info.getExp());
		bean.setIsEquiped(info.getIsEquiped());
		return bean;
	}
	/** 返回时装类型 */
	public static int getFashionType(FashionInfo fashion) {
		return fashion.getFashionId() / 10000;
	}
	private static int getFashionAtt(FashionInfo fashion) {
		switch (getFashionType(fashion)) {
		case EquipConstant.EquipType.fashion_weapon:
			return EnumAttr.Weapon.getValue();
		case EquipConstant.EquipType.fashion_clothe:
			return EnumAttr.Clothes.getValue();
		case EquipConstant.EquipType.fashion_cape:
			return EnumAttr.BeiShi.getValue();
		}
		return 0;
	}
	/** 更新时装前后台 */
	public static void updateFashion(FashionInfo fashion, GamePlayer player) {
		player.getFashionInventory().updateFashion(fashion);
		
		FashionUpdateRespMsg.Builder msg = FashionUpdateRespMsg.newBuilder();
    	msg.addFashion(toBean(fashion));
		PBMessage p = MessageUtil.buildMessage(Protocol.U_FASHION_UPDATE, msg);
		player.sendPbMessage(p);
	}
	
	/**
	 * 更新人物装备形象
	 * @param fashion
	 * @param channel
	 * @throws MXY2Exception
	 */
	public static void updateRoleEquipSkin(FashionInfo fashion, GamePlayer player) {
		if(fashion.isEquiped()){
			player.getBasePlayer().commitChages(getFashionAtt(fashion), fashion.getFashionId());
		}else{
			int fashionType = getFashionType(fashion);
			if(fashionType == EquipConstant.EquipType.fashion_cape){
				player.getBasePlayer().commitChages(EnumAttr.BeiShi.getValue(), 0);
			}else if(fashionType == EquipConstant.EquipType.fashion_clothe){
				BaseItem item = player.getBagInventory().getHeroEquipmentBag().getItemByPos(EquipConstant.EquipPosition.bodyPosition);
	    		int equipId = item != null?item.getTemplateId():0;
	    		player.getBasePlayer().commitChages(EnumAttr.Clothes.getValue(), equipId);
			}else if(fashionType == EquipConstant.EquipType.fashion_weapon){
				BaseItem item = player.getBagInventory().getHeroEquipmentBag().getItemByPos(EquipConstant.EquipPosition.weaponPosition);
	    		int equipId = item != null? item.getTemplateId():0;
	    		player.getBasePlayer().commitChages(EnumAttr.Weapon.getValue(), equipId);
			}
		}
	}
	
	/** 进阶 */
	public static boolean gradeUp(FashionInfo fashion, GamePlayer player, short protocolCode) {
		
		FashionLevelCfg nextLevel = FashionTemplateMgr.getLevelTemps().get(fashion.getLevel() + 1);
		FashionQualityCfg nextQuality = FashionTemplateMgr.getQualityTemps().get(fashion.getQuality());
		
		FashionLevelCfg level = FashionTemplateMgr.getLevelTemps().get(fashion.getLevel());
		FashionQualityCfg quality = FashionTemplateMgr.getQualityTemps().get(fashion.getQuality());
		
		int needItem = SystemConfigTemplateMgr.getIntValue("fashion.grade.up.item");
		int needNum = quality.getQualityUpConsume();
		if (fashion.getExp()<level.getExpMax() || nextLevel != null) {//经验或等级未满，则增加经验
			//最高品质的最高等级，不可增加经验
			if (nextLevel == null && nextQuality == null) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Fashion_Quality_Max, protocolCode);
				return false;
			}
	    	//扣物品
			if(player.getBagInventory().getPlayerBagItemCount(needItem) < needNum){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, protocolCode, "物品不足");
				return false;
			}
			if(!player.getBagInventory().removeItemFromPlayerBag(needItem, needNum, protocolCode)) return false;
			//增加经验值
	    	ThreadSafeRandom rnd = new ThreadSafeRandom();
	    	int addExp = rnd.next(1, 3);
	    	fashion.setExp(fashion.getExp()+addExp);
	    	//若经验已满，且等级未满，则升级
	    	if (fashion.getExp()>=level.getExpMax() && nextLevel != null) {
	    		fashion.setLevel(fashion.getLevel()+1);
	    		fashion.setExp(0);
	    	}
		} else if (nextQuality != null) {//经验和等级均已满，但品质未满，则升品质
	    	//扣物品
			if(player.getBagInventory().getPlayerBagItemCount(needItem) < needNum){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, protocolCode, "物品不足");
				return false;
			}
			if(!player.getBagInventory().removeItemFromPlayerBag(needItem, needNum, protocolCode)) return false;
			//升品质
			fashion.setQuality(fashion.getQuality()+1);
			fashion.setLevel(0);
			fashion.setExp(0);
		} else {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Fashion_Quality_Max, protocolCode);
			return false;
		}
		//更新前后台
		updateFashion(fashion, player);
		
		//更新属性
//		RoleManager.updateAtt(fashion.getRoleId(), DataDictionaryManager.getIns().getFashionAttrEnumList(fashion.getFashionId()));
		
		return true;
	}
	/** 激活 */
	public static boolean activateFashion(int fashionId, GamePlayer player, short protocolCode) {
		int needNum = 1;
    	FashionInfo fashion = player.getFashionInventory().getFashion(fashionId);
		if (fashion!=null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Fashion_Beactive, protocolCode);
			return false;
		}
		int fashionNum = player.getBagInventory().getPlayerBagItemCount(fashionId);
		if (fashionNum<needNum) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Fashion_Beactive_Less, protocolCode);
			return false;
		}
    	//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(fashionId) < needNum){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, protocolCode, "物品不足");
			return false;
		}
		player.getBagInventory().removeItemFromPlayerBag(fashionId, needNum, protocolCode);
		
		fashion = new FashionInfo(player.getPlayerId(), fashionId);
		player.getFashionInventory().addFashion(fashion);
		//更新前后台
		updateFashion(fashion, player);
		
		//更新属性
//		RoleManager.updateAtt(fashion.getRoleId(), DataDictionaryManager.getIns().getFashionAttrEnumList(fashion.getFashionId()));
		return true;
	}
	private static void putAttribValue(Map<Integer, Integer> attribMap, int attAndBase, int qualityAdd, int levelAdd, FashionInfo fashion) {
		int att = attAndBase / 1000000;//属性类型
		int base = attAndBase % 1000000;//基础值
		
		int maxLevel = SystemConfigTemplateMgr.getIntValue("fashion.grade.max");
		int value = base 
				+ (levelAdd * maxLevel + qualityAdd) * (fashion.getQuality() - 1)
				+ levelAdd * fashion.getLevel();
		attribMap.put(att, value);
	}
	
	/** 返回某时装贡献的属性值列表 */
	public static Map<Integer, Integer> getAttValMap(FashionInfo fashion) {
		Map<Integer, Integer> attribMap = new HashMap<>();
		
		FashionCfg tmpl = FashionTemplateMgr.getFashionTemps().get(fashion.getFashionId());
		putAttribValue(attribMap, tmpl.getAttr1Base(), tmpl.getAttr1QualityAdd(), tmpl.getAttr1LevelAdd(), fashion);
		putAttribValue(attribMap, tmpl.getAttr2Base(), tmpl.getAttr2QualityAdd(), tmpl.getAttr2LevelAdd(), fashion);
		putAttribValue(attribMap, tmpl.getAttr3Base(), tmpl.getAttr3QualityAdd(), tmpl.getAttr3LevelAdd(), fashion);
		
		return attribMap;
	}
	/** 返回某玩家时装贡献的总属性值列表 */
	public static Map<Integer, Integer> getAttValMap(GamePlayer player) {
		Map<Integer, Integer> totalMap = new HashMap<>();
		
		Map<Integer, FashionInfo> fashionMap = player.getFashionInventory().getFashionMap();
		for (FashionInfo fashion : fashionMap.values()) {
			Map<Integer, Integer> map = getAttValMap(fashion);
			for (Entry<Integer, Integer> attVal : map.entrySet()) {
				Integer totalVal = totalMap.get(attVal.getKey());
				if (totalVal==null) {
					totalVal = attVal.getValue();
				} else {
					totalVal += attVal.getValue();
				}
				totalMap.put(attVal.getKey(), totalVal);
			}
		}
		
		return totalMap;
	}

}
