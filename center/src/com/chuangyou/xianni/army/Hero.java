package com.chuangyou.xianni.army;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.SkillConstant.SkillMainType;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillInventory;

public class Hero extends Living {
	private GamePlayer	player;					// 当前用户ID
	private int			curBlood	= 100000;
	private int			curSoul		= 100000;

	public GamePlayer getPlayer() {
		return player;
	}

	public void setPlayer(GamePlayer player) {
		this.player = player;
	}

	public int getCurBlood() {
		return curBlood;
	}

	public void setCurBlood(int curBlood) {
		this.curBlood = curBlood;
	}

	public int getCurSoul() {
		return curSoul;
	}

	public void setCurSoul(int curSoul) {
		this.curSoul = curSoul;
	}

	public int getMana() {
		if (player.getBasePlayer() == null || player.getBasePlayer().getPlayerJoinInfo() == null) {
			return 0;
		}
		return player.getBasePlayer().getPlayerJoinInfo().getMana();
	}

	public Hero(GamePlayer player) {
		this.player = player;

	}

	@Override
	public void ghost(Living otherLiving) {

	}

	@Override
	public void addBag(BaseProperty bagData, BaseProperty bagPer) {
		super.addBag(bagData, bagPer);
	}

	public void writeHeroProto(GamePlayer gamePlayer, HeroInfoMsg.Builder heroInfo) {
		heroInfo.setPlayerId(player.getPlayerId());
		PropertyListMsg.Builder propertis = PropertyListMsg.newBuilder();
		writeProto(gamePlayer, propertis);

		int cSoul = getCurSoul();
		int cBloold = getCurBlood();
		// 当等级<=1每次登陆均满血
		if (gamePlayer.getLevel() <= 1) {
			cSoul = getTotalProperty(Living.SOUL);
			cBloold = getTotalProperty(Living.BLOOD);
		}

		PropertyMsg.Builder cur_hp = PropertyMsg.newBuilder();
		cur_hp.setType(EnumAttr.CUR_SOUL.getValue());
		cur_hp.setTotalPoint(cSoul);
		propertis.addPropertys(cur_hp);

		PropertyMsg.Builder cur_blood = PropertyMsg.newBuilder();
		cur_blood.setType(EnumAttr.CUR_BLOOD.getValue());
		cur_blood.setTotalPoint(cBloold);
		propertis.addPropertys(cur_blood);

		PropertyMsg.Builder mana = PropertyMsg.newBuilder();
		mana.setType(EnumAttr.MANA.getValue());
		mana.setTotalPoint(getMana());
		propertis.addPropertys(mana);

		// 仙力
		PropertyMsg.Builder avatarEnergy = PropertyMsg.newBuilder();
		avatarEnergy.setType(EnumAttr.AVATAR_ENERGY.getValue());
		avatarEnergy.setTotalPoint(player.getBasePlayer().getPlayerInfo().getAvatarEnergy());
		propertis.addPropertys(avatarEnergy);

		// 添加pk值
		PropertyMsg.Builder pkVal = PropertyMsg.newBuilder();
		pkVal.setType(EnumAttr.PK_VAL.getValue());
		pkVal.setTotalPoint(gamePlayer.getBasePlayer().getPlayerInfo().getPkVal());
		propertis.addPropertys(pkVal);

		PropertyMsg.Builder battleMode = PropertyMsg.newBuilder();
		battleMode.setType(EnumAttr.BATTLE_MODE.getValue());
		battleMode.setTotalPoint(gamePlayer.getBasePlayer().getPlayerInfo().getBattleMode());
		propertis.addPropertys(battleMode);

		heroInfo.setPropertis(propertis);

		List<HeroSkill> heroSkills = gamePlayer.getSkillInventory().getToalSkills();
		// System.out.println("-----------------------#");
		for (HeroSkill heroSkill : heroSkills) {
			if (heroSkill.getType() == SkillMainType.PASSIVE) {// 培养类技能
				continue;
			}
			// heroInfo.addBattleSkills(heroSkill.getSkillId());
			heroInfo.addSkillInfos(heroSkill.getSkillId());
		}
		// 添加融合技能
		heroInfo.addAllFuseSkills(player.getSoulInventory().getFuseSkillPacket(gamePlayer));
		// 添加魂幡等级
		int lv = LevelUpTempleteMgr.getSoulLevel(player.getSoulInventory().getSoulInfo().getExp());
		heroInfo.setSoulLv(lv);

	}

	/** 写入机器人信息 */
	public void writeRobotInfo(GamePlayer player, RobotInfoMsg.Builder robotInfo) {
		PlayerInfoMsg.Builder simpleInfo = PlayerInfoMsg.newBuilder();
		player.writePlayerInfoProto(simpleInfo);
		robotInfo.setSimpInfo(simpleInfo);

		PropertyListMsg.Builder propertyList = PropertyListMsg.newBuilder();
		writeProto(propertyList);

		int cSoul = getTotalProperty(Living.SOUL);
		int cBloold = getTotalProperty(Living.BLOOD);

		PropertyMsg.Builder cur_hp = PropertyMsg.newBuilder();
		cur_hp.setType(EnumAttr.CUR_SOUL.getValue());
		cur_hp.setTotalPoint(cSoul);
		propertyList.addPropertys(cur_hp);

		PropertyMsg.Builder cur_blood = PropertyMsg.newBuilder();
		cur_blood.setType(EnumAttr.CUR_BLOOD.getValue());
		cur_blood.setTotalPoint(cBloold);
		propertyList.addPropertys(cur_blood);
		robotInfo.setPropertis(propertyList);

		List<HeroSkill> heroSkills = player.getSkillInventory().getToalSkills();
		for (HeroSkill heroSkill : heroSkills) {
			if (heroSkill.getType() == SkillMainType.PASSIVE) {// 培养类技能
				continue;
			}
			robotInfo.addBattleSkills(heroSkill.getSkillId());
		}
	}
}
