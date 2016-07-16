package com.chuangyou.xianni.army;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.property.SkillBaseProperty;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 所有属性集合,出站技能等汇聚的载体
 */
public abstract class Living {

	/** 客户端对应属性ID = 服务器属性ID+1 **/
	public static final int SOUL = 0; // 元魂
	public static final int BLOOD = 1; // 气血
	public static final int ATTACK = 2; // 攻击
	public static final int DEFENCE = 3; // 防御
	public static final int SOUL_ATTACK = 4; // 魂攻
	public static final int SOUL_DEFENCE = 5; // 魂防
	public static final int ACCURATE = 6; // 命中
	public static final int DODGE = 7; // 闪避
	public static final int CRIT = 8; // 暴击
	public static final int CRIT_DEFENCE = 9; // 抗暴
	public static final int CRIT_ADDTION = 10; // 暴击伤害
	public static final int CRIT_CUT = 11; // 抗暴减伤
	public static final int BLOOD_ATTACK_ADDTION = 12; // 气血伤害增加
	public static final int BLOOD_ATTACK_CUT = 13; // 气血伤害减免
	public static final int SOUL_ATTACK_ADDTION = 14; // 元魂伤害增加
	public static final int SOUL_ATTACK_CUT = 15; // 元魂伤害减免
	public static final int REGAIN_SOUL = 16; // 每10秒回魂
	public static final int REGAIN_BLOOD = 17; // 每10秒回血
	public static final int METAL = 18; // 金
	public static final int WOOD = 19; // 木
	public static final int WATER = 20; // 水
	public static final int FIRE = 21; // 火
	public static final int EARTH = 22; // 土
	public static final int METAL_DEFENCE = 23; // 金抗
	public static final int WOOD_DEFENCE = 24; // 木抗
	public static final int WATER_DEFENCE = 25; // 水抗
	public static final int FIRE_DEFENCE = 26; // 火抗
	public static final int EARTH_DEFENCE = 27; // 土抗
	public static final int SPEED = 28; // 移动速度

	// public static final int//当前元魂
	// public static final int //当前气血
	// public static final int //无敌
	// public static final int //最大元魂
	// public static final int //最大气血

	protected Property[] properties;

	public Living() {
		properties = new Property[29];
		properties[SOUL] = new Property("元魂");
		properties[BLOOD] = new Property("气血");
		properties[ATTACK] = new Property("攻击");
		properties[DEFENCE] = new Property("防御");
		properties[SOUL_ATTACK] = new Property("魂攻");
		properties[SOUL_DEFENCE] = new Property("魂防");
		properties[ACCURATE] = new Property("命中");
		properties[DODGE] = new Property("闪避");
		properties[CRIT] = new Property("暴击");
		properties[CRIT_DEFENCE] = new Property("抗暴");
		properties[CRIT_ADDTION] = new Property("暴击伤害");
		properties[CRIT_CUT] = new Property("抗暴减伤");
		properties[BLOOD_ATTACK_ADDTION] = new Property("气血伤害增加");
		properties[BLOOD_ATTACK_CUT] = new Property("气血伤害减免");
		properties[SOUL_ATTACK_ADDTION] = new Property("元魂伤害增加");
		properties[SOUL_ATTACK_CUT] = new Property("元魂伤害减免");
		properties[REGAIN_SOUL] = new Property("每10秒回魂");
		properties[REGAIN_BLOOD] = new Property("每10秒回血");
		properties[METAL] = new Property("金");
		properties[WOOD] = new Property("木");
		properties[WATER] = new Property("水");
		properties[FIRE] = new Property("火");
		properties[EARTH] = new Property("土");
		properties[METAL_DEFENCE] = new Property("金抗");
		properties[WOOD_DEFENCE] = new Property("木抗");
		properties[WATER_DEFENCE] = new Property("水抗");
		properties[FIRE_DEFENCE] = new Property("火抗");
		properties[EARTH_DEFENCE] = new Property("土抗");
		properties[SPEED] = new Property("速度");
	}

	public Property[] getProperty() {
		return properties;
	}

	public int getTotalProperty(int property) {
		return properties[property].getTotalJoin();
	}

	public int getBaseProperty(int property) {
		return properties[property].getBaseJoin();
	}

	public void clearBag() {
		for (int i = 0; i < properties.length; i++) {
			properties[i].clearBag();
		}
	}

	public Property getProperty(int property) {
		return properties[property];
	}

	public abstract void ghost(Living otherLiving);

	public void addBag(BaseProperty bagData, BaseProperty bagPer) {
		// 添加背包数据
		properties[SOUL].setBagData(bagData.getSoul());
		properties[BLOOD].setBagData(bagData.getBlood());
		properties[ATTACK].setBagData(bagData.getAttack());
		properties[DEFENCE].setBagData(bagData.getDefence());
		properties[SOUL_ATTACK].setBagData(bagData.getSoulAttack());
		properties[SOUL_DEFENCE].setBagData(bagData.getSoulDefence());
		properties[ACCURATE].setBagData(bagData.getAccurate());
		properties[DODGE].setBagData(bagData.getDodge());
		properties[CRIT].setBagData(bagData.getCrit());
		properties[CRIT_DEFENCE].setBagData(bagData.getCritDefence());
		properties[CRIT_ADDTION].setBagData(bagData.getCritAddtion());
		properties[CRIT_CUT].setBagData(bagData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setBagData(bagData.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setBagData(bagData.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setBagData(bagData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setBagData(bagData.getBloodAttackCut());
		properties[REGAIN_SOUL].setBagData(bagData.getRegainSoul());
		properties[REGAIN_BLOOD].setBagData(bagData.getRegainBlood());
		properties[METAL].setBagData(bagData.getMetal());
		properties[WOOD].setBagData(bagData.getWood());
		properties[WATER].setBagData(bagData.getWater());
		properties[FIRE].setBagData(bagData.getFire());
		properties[EARTH].setBagData(bagData.getEarth());
		properties[METAL_DEFENCE].setBagData(bagData.getMetalDefence());
		properties[WOOD_DEFENCE].setBagData(bagData.getWoodDefence());
		properties[WATER_DEFENCE].setBagData(bagData.getWaterDefence());
		properties[FIRE_DEFENCE].setBagData(bagData.getFireDefence());
		properties[EARTH_DEFENCE].setBagData(bagData.getEarthDefence());
		properties[SPEED].setBagData(bagData.getSpeed());

		// 添加背包百分比加成
		properties[SOUL].setBagPer(bagPer.getSoul());
		properties[BLOOD].setBagPer(bagPer.getBlood());
		properties[ATTACK].setBagPer(bagPer.getAttack());
		properties[DEFENCE].setBagPer(bagPer.getDefence());
		properties[SOUL_ATTACK].setBagPer(bagPer.getSoulAttack());
		properties[SOUL_DEFENCE].setBagPer(bagPer.getSoulDefence());
		properties[ACCURATE].setBagPer(bagPer.getAccurate());
		properties[DODGE].setBagPer(bagPer.getDodge());
		properties[CRIT].setBagPer(bagPer.getCrit());
		properties[CRIT_DEFENCE].setBagPer(bagPer.getCritDefence());
		properties[CRIT_ADDTION].setBagPer(bagPer.getCritAddtion());
		properties[CRIT_CUT].setBagPer(bagPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setBagPer(bagPer.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setBagPer(bagPer.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setBagPer(bagPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setBagPer(bagPer.getBloodAttackCut());
		properties[REGAIN_SOUL].setBagPer(bagPer.getRegainSoul());
		properties[REGAIN_BLOOD].setBagPer(bagPer.getRegainBlood());
		properties[METAL].setBagPer(bagPer.getMetal());
		properties[WOOD].setBagPer(bagPer.getWood());
		properties[WATER].setBagPer(bagPer.getWater());
		properties[FIRE].setBagPer(bagPer.getFire());
		properties[EARTH].setBagPer(bagPer.getEarth());
		properties[METAL_DEFENCE].setBagPer(bagPer.getMetalDefence());
		properties[WOOD_DEFENCE].setBagPer(bagPer.getWoodDefence());
		properties[WATER_DEFENCE].setBagPer(bagPer.getWaterDefence());
		properties[FIRE_DEFENCE].setBagPer(bagPer.getFireDefence());
		properties[EARTH_DEFENCE].setBagPer(bagPer.getEarthDefence());
		properties[SPEED].setBagPer(bagPer.getSpeed());
	}

	public void addTemp(BaseProperty tempData) {
		// 添加模板数据
		properties[SOUL].setTempData(tempData.getSoul());
		properties[BLOOD].setTempData(tempData.getBlood());
		properties[ATTACK].setTempData(tempData.getAttack());
		properties[DEFENCE].setTempData(tempData.getDefence());
		properties[SOUL_ATTACK].setTempData(tempData.getSoulAttack());
		properties[SOUL_DEFENCE].setTempData(tempData.getSoulDefence());
		properties[SPEED].setTempData(tempData.getSpeed());
 
		// // 添加背包百分比加成
		// properties[SOUL].setTempPer(tempPer.getSoul());
		// properties[BLOOD].setTempPer(tempPer.getBlood());
		// properties[ATTACK].setTempPer(tempPer.getAttack());
		// properties[DEFENCE].setTempPer(tempPer.getDefence());
		// properties[SOUL_ATTACK].setTempPer(tempPer.getSoulAttack());
		// properties[SOUL_DEFENCE].setTempPer(tempPer.getSoulDefence());
		// properties[ACCURATE].setTempPer(tempPer.getAccurate());
		// properties[DODGE].setTempPer(tempPer.getDodge());
		// properties[CRIT].setTempPer(tempPer.getCrit());
		// properties[CRIT_DEFENCE].setTempPer(tempPer.getCritDefence());
		// properties[CRIT_ADDTION].setTempPer(tempPer.getCritAddtion());
		// properties[CRIT_CUT].setTempPer(tempPer.getCritCut());
		// properties[ATTACK_ADDTION].setTempPer(tempPer.getBloodAttackAddtion());
		// properties[ATTACK_CUT].setTempPer(tempPer.getBloodAttackCut());
		// properties[SOUL_ATTACK_ADDTION].setTempPer(tempPer.getSoulAttackAddtion());
		// properties[SOUL_ATTACK_CUT].setTempPer(tempPer.getBloodAttackCut());
		// properties[REGAIN_SOUL].setTempPer(tempPer.getRegainSoul());
		// properties[REGAIN_BLOOD].setTempPer(tempPer.getRegainBlood());
		// properties[METAL].setTempPer(tempPer.getMetal());
		// properties[WOOD].setTempPer(tempPer.getWood());
		// properties[WATER].setTempPer(tempPer.getWater());
		// properties[FIRE].setTempPer(tempPer.getFire());
		// properties[EARTH].setTempPer(tempPer.getEarth());
		// properties[META_DEFENCE].setTempPer(tempPer.getMetalDefence());
		// properties[WOOD_DEFENCE].setTempPer(tempPer.getWoodDefence());
		// properties[WATER_DEFENCE].setTempPer(tempPer.getWaterDefence());
		// properties[FIRE_DEFENCE].setTempPer(tempPer.getFireDefence());
		// properties[EARTH_DEFENCE].setTempPer(tempPer.getEarthDefence());
		// properties[SPEED].setTempPer(tempPer.getSpeed());
 
	}

	/**
	 * 加入技能属性
	 * 
	 * @param tempData
	 * @param tempPer
	 */
	public void addSkillPro(SkillBaseProperty tempData) {
		// 添加技能属性
		properties[SOUL].setSkillData(tempData.getSoul());
		properties[BLOOD].setSkillData(tempData.getBlood());
		properties[ATTACK].setSkillData(tempData.getAttack());
		properties[DEFENCE].setSkillData(tempData.getDefence());
		properties[SOUL_ATTACK].setSkillData(tempData.getSoulAttack());
		properties[SOUL_DEFENCE].setSkillData(tempData.getSoulDefence());
		properties[ACCURATE].setSkillData(tempData.getAccurate());
		properties[DODGE].setSkillData(tempData.getDodge());
		properties[CRIT].setSkillData(tempData.getCrit());
		properties[CRIT_DEFENCE].setSkillData(tempData.getCritDefence());
		
		
//		properties[SOUL].setBagData(bagData.getSoul());
//		properties[BLOOD].setBagData(bagData.getBlood());
//		properties[ATTACK].setBagData(bagData.getAttack());
//		properties[DEFENCE].setBagData(bagData.getDefence());
//		properties[SOUL_ATTACK].setBagData(bagData.getSoulAttack());
//		properties[SOUL_DEFENCE].setBagData(bagData.getSoulDefence());
//		properties[ACCURATE].setBagData(bagData.getAccurate());
//		properties[DODGE].setBagData(bagData.getDodge());
//		properties[CRIT].setBagData(bagData.getCrit());
//		properties[CRIT_DEFENCE].setBagData(bagData.getCritDefence());
		properties[CRIT_ADDTION].setSkillData(tempData.getCritAddtion());
		properties[CRIT_CUT].setSkillData(tempData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setSkillData(tempData.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setSkillData(tempData.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setSkillData(tempData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setSkillData(tempData.getBloodAttackCut());
		properties[REGAIN_SOUL].setSkillData(tempData.getRegainSoul());
		properties[REGAIN_BLOOD].setSkillData(tempData.getRegainBlood());
		properties[METAL].setSkillData(tempData.getMetal());
		properties[WOOD].setSkillData(tempData.getWood());
		properties[WATER].setSkillData(tempData.getWater());
		properties[FIRE].setSkillData(tempData.getFire());
		properties[EARTH].setSkillData(tempData.getEarth());
		properties[METAL_DEFENCE].setSkillData(tempData.getMetalDefence());
		properties[WOOD_DEFENCE].setSkillData(tempData.getWoodDefence());
		properties[WATER_DEFENCE].setSkillData(tempData.getWaterDefence());
		properties[FIRE_DEFENCE].setSkillData(tempData.getFireDefence());
		properties[EARTH_DEFENCE].setSkillData(tempData.getEarthDefence());
//		properties[SPEED].setSkillData(tempData.getSpeed());
		// 添加背包百分比加成
		// properties[SOUL].setTempPer(tempPer.getSoul());
		// properties[BLOOD].setTempPer(tempPer.getBlood());
		// properties[ATTACK].setTempPer(tempPer.getAttack());
		// properties[DEFENCE].setTempPer(tempPer.getDefence());
		// properties[SOUL_ATTACK].setTempPer(tempPer.getSoulAttack());
		// properties[SOUL_DEFENCE].setTempPer(tempPer.getSoulDefence());
		// properties[ACCURATE].setTempPer(tempPer.getAccurate());
		// properties[DODGE].setTempPer(tempPer.getDodge());
		// properties[CRIT].setTempPer(tempPer.getCrit());
		// properties[CRIT_DEFENCE].setTempPer(tempPer.getCritDefence());
	}
	/**
	 * 添加坐骑属性
	 * @param mountData
	 */
	public void addMount(Map<Integer, Integer> propertyMap){
		for(int type: propertyMap.keySet()){
			if(type <= 0) continue;
			if(type == EnumAttr.SPEED.getValue()) continue;
			
			int value = propertyMap.get(type);
			
			if(type > 0 && type <= properties.length){
				properties[type - 1].setMountData(value);
			}
		}
	}
	
	/**
	 * 添加法宝属性
	 * @param magicwpData
	 */
	public void addMagicwp(Map<Integer, Integer> propertyMap){
		for(int type: propertyMap.keySet()){
			if(type <= 0) continue;
			int value = propertyMap.get(type);
			
			if(type > 0 && type <= properties.length){
				properties[type - 1].setMagicwpData(value);
			}
		}
	}
	
	/**
	 * 添加宠物属性
	 * @param petData
	 */
	public void addPet(Map<Integer, Integer> propertyMap){
		for(int type: propertyMap.keySet()){
			if(type <= 0) continue;
			int value = propertyMap.get(type);
			
			if(type > 0 && type <= properties.length){
				properties[type - 1].setPetData(value);
			}
		}
	}


	public void setBagPro(int type, int val) {
		properties[type].setBagData(val);
	}


	public void writeProto(GamePlayer player, PropertyListMsg.Builder propertyMsgs) {
		PlayerJoinInfo joinInfo = player.getBasePlayer().getPlayerJoinInfo();

		for (int index = 0; index < properties.length; index++) {
			Property property = properties[index];
			if (property != null) {
				updatePlayer(index, property, joinInfo);
				
				PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
				proMsg.setType(index + 1);
				property.writeProto(proMsg);
				propertyMsgs.addPropertys(proMsg);
			} else {
				Log.error("------丢失属性-----丢失属性------丢失属性-------丢失属性---", new Exception("丢失属性"));
			}
		}
	}
	
	public void writeUpdateProto(GamePlayer player, PlayerAttUpdateMsg.Builder msg){
		msg.setPlayerId(player.getPlayerId());
		PlayerJoinInfo joinInfo = player.getBasePlayer().getPlayerJoinInfo();
		for(int i = 0; i < properties.length; i++){
			Property property = properties[i];
			if(property != null){
				if(property.isChange()){
					updatePlayer(i, property, joinInfo);
					
					PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
					proMsg.setType(i + 1);
					property.writeProto(proMsg);
					msg.addAtt(proMsg);
				}
			}else {
				Log.error("------丢失属性-----丢失属性------丢失属性-------丢失属性---", new Exception("丢失属性"));
			}
		}
		
	}
	
	public void updatePlayer(int index, Property property, PlayerJoinInfo joinInfo){
		switch(index){
		case SOUL:
			joinInfo.setSoul(property.getTotalJoin());
			break;
		case BLOOD:
			joinInfo.setBlood(property.getTotalJoin());
			break;
		case ATTACK:
			joinInfo.setAttack(property.getTotalJoin());
			break;
		case DEFENCE:
			joinInfo.setDefence(property.getTotalJoin());
			break;
		case SOUL_ATTACK:
			joinInfo.setSoulAttack(property.getTotalJoin());
			break;
		case SOUL_DEFENCE:
			joinInfo.setSoulDefence(property.getTotalJoin());
			break;
		case ACCURATE:
			joinInfo.setAccurate(property.getTotalJoin());
			break;
		case DODGE:
			joinInfo.setDodge(property.getTotalJoin());
			break;
		case CRIT:
			joinInfo.setCrit(property.getTotalJoin());
			break;
		case CRIT_DEFENCE:
			joinInfo.setCritDefence(property.getTotalJoin());
			break;
		case CRIT_ADDTION:
			joinInfo.setCritAddtion(property.getTotalJoin());
			break;
		case CRIT_CUT:
			joinInfo.setCritCut(property.getTotalJoin());
			break;
		case BLOOD_ATTACK_ADDTION:
			joinInfo.setBloodAttackAddtion(property.getTotalJoin());
			break;
		case BLOOD_ATTACK_CUT:
			joinInfo.setBloodAttackCut(property.getTotalJoin());
			break;
		case SOUL_ATTACK_ADDTION:
			joinInfo.setSoulAttackAddtion(property.getTotalJoin());
			break;
		case SOUL_ATTACK_CUT:
			joinInfo.setSoulAttackCut(property.getTotalJoin());
			break;
		case REGAIN_SOUL:
			joinInfo.setRegainSoul(property.getTotalJoin());
			break;
		case REGAIN_BLOOD:
			joinInfo.setRegainBlood(property.getTotalJoin());
			break;
		case METAL:
			joinInfo.setMetal(property.getTotalJoin());
			break;
		case WOOD:
			joinInfo.setWood(property.getTotalJoin());
			break;
		case WATER:
			joinInfo.setWater(property.getTotalJoin());
			break;
		case FIRE:
			joinInfo.setFire(property.getTotalJoin());
			break;
		case EARTH:
			joinInfo.setEarth(property.getTotalJoin());
			break;
		case METAL_DEFENCE:
			joinInfo.setMetalDefence(property.getTotalJoin());
			break;
		case WOOD_DEFENCE:
			joinInfo.setWoodDefence(property.getTotalJoin());
			break;
		case WATER_DEFENCE:
			joinInfo.setWaterDefence(property.getTotalJoin());
			break;
		case FIRE_DEFENCE:
			joinInfo.setFireDefence(property.getTotalJoin());
			break;
		case EARTH_DEFENCE:
			joinInfo.setEarthDefence(property.getTotalJoin());
			break;
		case SPEED:
			joinInfo.setSpeed(property.getTotalJoin());
			break;
		default:
			break;
		}
		joinInfo.setOp(Option.Update);
	}

	
	
	
}
