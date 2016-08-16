package com.chuangyou.xianni.army;

import java.util.Random;
import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.UnlineInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.manager.PlayerManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class ArmyInventory extends AbstractEvent implements UnlineInventory {
	private GamePlayer	player;
	private Army		army;
	Random				random	= new Random();

	public ArmyInventory(GamePlayer player) {
		this.player = player;
		army = new Army(player);

		// TODO 模拟英雄属性
		BaseProperty tempData = PlayerManager.getTempProperty(player);
		Hero hero = army.getHero();
		hero.addTemp(tempData);
		if (player.getBagInventory() != null) {
			// 加入背包属性
			BaseProperty bagData = new BaseProperty();
			BaseProperty bagPer = new BaseProperty();
			player.getBagInventory().getHeroEquipmentBag().getBagJoin(bagData, bagPer);
			hero.addBag(bagData, bagPer);
		}

		if (player.getSkillInventory() != null) {
			BaseProperty skillData = new BaseProperty();
			BaseProperty skillPer = new BaseProperty();
			// 加入技能属性
			player.getSkillInventory().getTotalPro(skillData, skillPer);
			hero.addSkillPro(skillData, skillPer);

		}

		if (player.getMountInventory() != null) {
			BaseProperty mountData = new BaseProperty();
			BaseProperty mountPer = new BaseProperty();
			// 加入坐骑属性
			player.getMountInventory().computeMountAtt(mountData, mountPer);
			// 坐骑属性
			hero.addMount(mountData, mountPer);
		}

		if (player.getMagicwpInventory() != null) {
			BaseProperty magicwpData = new BaseProperty();
			BaseProperty magicwpPer = new BaseProperty();
			// 加入法宝属性
			player.getMagicwpInventory().computeMagicwpAtt(magicwpData, magicwpPer);
			hero.addMagicwp(magicwpData, magicwpPer);
		}

		if (player.getPetInventory() != null) {
			BaseProperty petData = new BaseProperty();
			BaseProperty petPer = new BaseProperty();
			// 加入宠物属性
			player.getPetInventory().computePetAtt(petData, petPer);
			hero.addPet(petData, petPer);
		}
		
		if(player.getFashionInventory() != null){
			BaseProperty fashionData = new BaseProperty();
			BaseProperty fashionPer = new BaseProperty();
			// 加入时装属性
			player.getFashionInventory().computeFashionAtt(fashionData, fashionPer);
			hero.addFashion(fashionData, fashionPer);
		}
		
		if(player.getSoulInventory() != null){
			BaseProperty soulData = new BaseProperty();
			BaseProperty soulPer = new BaseProperty();
			//加入魂幡属性
			player.getSoulInventory().computeSoulAtt(soulData, soulPer);
			hero.addSoul(soulData, soulPer);
		}
		
		updateHeroInfo();
	}

	@Override
	public boolean loadFromDataBase() {
		// updataHeroInfo();
		return true;
	}

	@Override
	public boolean unloadData() {
		return false;
	}

	@Override
	public boolean saveToDatabase() {
		return false;
	}

	/**
	 * 更新玩家完整信息
	 */
	public void updateHeroInfo() {
		HeroInfoMsg.Builder heroInfo = HeroInfoMsg.newBuilder();
		this.army.getHero().writeHeroProto(player, heroInfo);
		PBMessage message = MessageUtil.buildMessage(Protocol.S_PLAYER_UPDATE, heroInfo);
		player.sendPbMessage(message);
	}

	/**
	 * 更新玩家改变的属性
	 */
	public void updateProperty() {
		PlayerAttUpdateMsg.Builder attMsg = PlayerAttUpdateMsg.newBuilder();
		this.army.getHero().writeUpdateProto(player, attMsg);
		PBMessage message = MessageUtil.buildMessage(Protocol.S_PROPERTY_UPDATE, attMsg);
		player.sendPbMessage(message);
	}

	public Army getArmy() {
		return army;
	}

	public Hero getHero() {
		return army.getHero();
	}

	public void setArmy(Army army) {
		this.army = army;
	}

	@Override
	public boolean unLineload() {
		// TODO Auto-generated method stub
		return false;
	}
}
