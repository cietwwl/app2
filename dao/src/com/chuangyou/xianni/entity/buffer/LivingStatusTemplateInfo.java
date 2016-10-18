package com.chuangyou.xianni.entity.buffer;

import java.util.ArrayList;
import java.util.List;

public class LivingStatusTemplateInfo {
	private int					id;								// 自增ID
	private String				name;							// 名称
	private int					type;							// 类型 0 一般 1 有益
																// 2 负面 3 控制
	private int					subBlood;						// 扣气血
	private int					subSoul;						// 扣元魂
	private int					addBlood;						// 气血治疗
	private int					addSoul;						// 元魂治疗
	private int					move;							// 移动
	private int					attackMove;						// 攻击位移
	private int					beHitMove;						// 被击位移
	private int					beHitFloat;						// 击飞
	private int					beHitDown;						// 击倒
	private int					normalAttack;					// 普通攻击
	private int					skillAttack;					// 释放主动技能
	private int					perks;							// 触发型技能
	private int					beControl;						// 是否吃控制
	
	/** 一般 */
	public static final int		COMMON		= 0;
	/** 有益 */
	public static final int		HELPFUL		= 1;
	/** 有害 */
	public static final int		DEHELPFUL	= 2;
	/** 控制 */
	public static final int		CONTROL		= 3;

	private List<LivingState>	affected	= new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubBlood() {
		return subBlood;
	}

	public void setSubBlood(int subBlood) {
		this.subBlood = subBlood;
		if (subBlood == 0) {
			affected.add(LivingState.SUB_BLOOD);
		}
	}

	public int getSubSoul() {
		return subSoul;
	}

	public void setSubSoul(int subSoul) {
		this.subSoul = subSoul;
		if (subSoul == 0) {
			affected.add(LivingState.SUB_SOUL);
		}
	}

	public int getAddBlood() {
		return addBlood;
	}

	public void setAddBlood(int addBlood) {
		this.addBlood = addBlood;
		if (addBlood == 0) {
			affected.add(LivingState.ADD_BLOOD);
		}
	}

	public int getAddSoul() {
		return addSoul;
	}

	public void setAddSoul(int addSoul) {
		this.addSoul = addSoul;
		if (addSoul == 0) {
			affected.add(LivingState.ADD_SOUL);
		}
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
		if (move == 0) {
			affected.add(LivingState.MOVE);
		}
	}

	public int getAttackMove() {
		return attackMove;
	}

	public void setAttackMove(int attackMove) {
		this.attackMove = attackMove;
		if (attackMove == 0) {
			affected.add(LivingState.ATTACK_MOVE);
		}
	}

	public int getBeHitMove() {
		return beHitMove;
	}

	public void setBeHitMove(int beHitMove) {
		this.beHitMove = beHitMove;
		if (beHitMove == 0) {
			affected.add(LivingState.BE_HIT_MOVE);
		}
	}

	public int getBeHitFloat() {
		return beHitFloat;
	}

	public void setBeHitFloat(int beHitFloat) {
		this.beHitFloat = beHitFloat;
		if (beHitFloat == 0) {
			affected.add(LivingState.BE_HIT_FLOAT);
		}
	}

	public int getBeHitDown() {
		return beHitDown;
	}

	public void setBeHitDown(int beHitDown) {
		this.beHitDown = beHitDown;
		if (beHitDown == 0) {
			affected.add(LivingState.BE_HIT_DOWN);
		}
	}

	public int getNormalAttack() {
		return normalAttack;
	}

	public void setNormalAttack(int normalAttack) {
		this.normalAttack = normalAttack;
		if (normalAttack == 0) {
			affected.add(LivingState.NORMAL_ATTACK);
		}
	}

	public int getSkillAttack() {
		return skillAttack;
	}

	public void setSkillAttack(int skillAttack) {
		this.skillAttack = skillAttack;
		if (skillAttack == 0) {
			affected.add(LivingState.SKILL_ATTAK);
		}
	}

	public int getPerks() {
		return perks;
	}

	public void setPerks(int perks) {
		this.perks = perks;
		if (perks == 0) {
			affected.add(LivingState.PERKS);
		}
	}

	public int getBeControl() {
		return beControl;
	}

	public void setBeControl(int beControl) {
		this.beControl = beControl;
		if (beControl == 0) {
			affected.add(LivingState.BE_CONTROL);
		}
	}

	public List<LivingState> getAffected() {
		return affected;
	}

}
