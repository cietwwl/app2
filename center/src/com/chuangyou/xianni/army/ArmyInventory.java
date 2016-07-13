package com.chuangyou.xianni.army;

import java.util.Random;

import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.property.SkillBaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.UnlineInventory;
import com.chuangyou.xianni.magicwp.manager.MagicwpManager;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.pet.manager.PetManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.manager.PlayerManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.manager.SkillManager;

public class ArmyInventory extends AbstractEvent implements UnlineInventory {
	private GamePlayer player;
	private Army army;
	Random random = new Random();

	public ArmyInventory(GamePlayer player) {
		this.player = player;
		army = new Army(player);

		// TODO 模拟英雄属性
		BaseProperty tempData = PlayerManager.getTempProperty(player);
		Hero hero = army.getHero();
		hero.addTemp(tempData);
		// 加入背包属性
		BaseProperty bagData = new BaseProperty();
		BaseProperty bagPer = new BaseProperty();
		if (player.getBagInventory() != null) {
			player.getBagInventory().getHeroEquipmentBag().getBagJoin(bagData, bagPer);
		}
		hero.addBag(bagData, bagPer);

		// 加入技能属性
		SkillBaseProperty skillBaseProperty = SkillManager.getTotalPro(player);
		hero.addSkillPro(skillBaseProperty);

		// 坐骑属性
		hero.addMount(MountManager.computeMountAtt(player));

		// 法宝属性
		hero.addMagicwp(MagicwpManager.computeMagicwpAtt(player));

		// 宠物属性
		hero.addPet(PetManager.computePetAtt(player));

		// updataHeroInfo();
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

	public void setArmy(Army army) {
		this.army = army;
	}

	@Override
	public boolean unLineload() {
		// TODO Auto-generated method stub
		return false;
	}
}
