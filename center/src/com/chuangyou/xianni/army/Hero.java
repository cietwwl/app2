package com.chuangyou.xianni.army;

import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillInventory;

public class Hero extends Living {
	private GamePlayer player;					// 当前用户ID
	private int curBlood = 100000;
	private int curSoul = 100000;

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

		PropertyMsg.Builder cur_hp = PropertyMsg.newBuilder();
		cur_hp.setType(EnumAttr.CUR_SOUL.getValue());
		cur_hp.setTotalPoint(getTotalProperty(Living.SOUL));
		propertis.addPropertys(cur_hp);

		PropertyMsg.Builder cur_blood = PropertyMsg.newBuilder();
		cur_blood.setType(EnumAttr.CUR_BLOOD.getValue());
		cur_blood.setTotalPoint(getTotalProperty(Living.BLOOD));
		propertis.addPropertys(cur_blood);
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

		Map<String, HeroSkill> heroSkills = gamePlayer.getSkillInventory().getHeroSkill();
		// System.out.println("-----------------------#");
		for (Entry<String, HeroSkill> entry : heroSkills.entrySet()) {
			HeroSkill heroSkill = entry.getValue();
			if (heroSkill.getType() == SkillInventory.passiveSkillType)// 被动技能
				continue;
			// }else if (heroSkill.getType()==1) {
			heroInfo.addBattleSkills(heroSkill.getSkillId());
			// System.out.println("skillId======" + heroSkill.getSkillId());
			// }
			heroInfo.addSkillInfos(heroSkill.getSkillId());

			// System.out.println("同步技能scene："+heroSkill.getSkillId());
		}

		// // // 同步技能
		// heroInfo.addSkillInfos(1001);
		// // // 出战技能
		// heroInfo.addBattleSkills(1001);
	}

}
