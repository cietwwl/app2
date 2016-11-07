package com.chuangyou.xianni.battle.magicwpban;

import com.chuangyou.xianni.battle.magicwpban.decorator.AddGoldDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.AvatarContaractDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.BiteDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.BurningDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.CoolDownDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.DecomposeDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.DisableSkillDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.FixedBodyDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.FrozenDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.ImmuneDamageDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.InvincibleDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.MonstarDamageAddDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.ResistDeathDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.ResurrectionDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.SuckBloodDecorator;
import com.chuangyou.xianni.battle.magicwpban.decorator.WeaponDecorator;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.role.objects.Player;

public class MagicwpCompanentFactory {

	public static MagicwpCompanent buildMagicwpCompanent(Player master, MagicwpBanConstant ban, int level) {
		switch (ban) {
			case ADD_GOLD:
				return new MagicwpCompanent(new AddGoldDecorator(master, level));
			case SUCK_BLOOD:
				return new MagicwpCompanent(new SuckBloodDecorator(master, level));
			case DECOMPOSE:
				return new MagicwpCompanent(new DecomposeDecorator(master, level));
			case FIXED_BODY:
				return new MagicwpCompanent(new FixedBodyDecorator(master, level));
			case RESIST_DEATH:
				return new MagicwpCompanent(new ResistDeathDecorator(master, level));
			case BURNING:
				return new MagicwpCompanent(new BurningDecorator(master, level));
			case AVATAR_CONTRACT:
				return new MagicwpCompanent(new AvatarContaractDecorator(master, level));
			case IMMUNE_DAMAGE:
				return new MagicwpCompanent(new ImmuneDamageDecorator(master, level));
			case COOL_DOWN:
				return new MagicwpCompanent(new CoolDownDecorator(master, level));
			case DISABLE_SKILL:
				return new MagicwpCompanent(new DisableSkillDecorator(master, level));
			case MONSTER_DAMAGE_ADD:
				return new MagicwpCompanent(new MonstarDamageAddDecorator(master, level));
			case BITE:
				return new MagicwpCompanent(new BiteDecorator(master, level));
			case FROZEN:
				return new MagicwpCompanent(new FrozenDecorator(master, level));
			case WEAPON:
				return new MagicwpCompanent(new WeaponDecorator(master, level));
			case INVINCIBLE:
				return new MagicwpCompanent(new InvincibleDecorator(master, level));
			case RESURRECTION:
				return new MagicwpCompanent(new ResurrectionDecorator(master, level));
		}
		return null;
	}
}
