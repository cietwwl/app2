package com.chuangyou.xianni.battle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.battle.AttackBroadcastMsgProto.AttackBroadcastMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.battle.action.AddDelayBuffAction;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.buffer.BufferTargetType;
import com.chuangyou.xianni.battle.buffer.ExecWayType;
import com.chuangyou.xianni.battle.calc.SkillCalc;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.magicwpban.MagicwpCompanent;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.skill.SnareTemplateInfo;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.ActiveLiving;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Snare;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;

/**
 * <pre>
 * 攻击指令类
 * </pre>
 */
public class AttackOrder {
	protected static ThreadSafeRandom	rand		= new ThreadSafeRandom();
	private boolean						isExec;
	private Living						source;									// 技能源
	private Skill						skill;									// 使用技能
	private SkillCalc					skillCalc;								// 技能计算器
	private List<Living>				targets;								// 释放目标
	private List<Damage>				damages;								// 伤害
	private int							orderId;								// 指令伤害
	private boolean						isAttack	= true;
	private long						attackId;								// 攻击ID

	private PBVector3					current;								// 施法前位置
	private PBVector3					postion;								// 施法后位置

	private int							toalBloodDamage;						// 技能造成的所有气血伤害

	public AttackOrder(Living source, Skill skill, long attackId) {
		this.source = source;
		this.skill = skill;
		orderId = skill.getTemplateInfo().getTemplateId();
		damages = new LinkedList<Damage>();
		this.attackId = attackId;
	}

	public AttackOrder(Living source, Skill skill, List<Living> targets, long attackId) {
		this.source = source;
		this.skill = skill;
		this.targets = targets;
		orderId = skill.getTemplateInfo().getTemplateId();
		damages = new LinkedList<Damage>();
		this.attackId = attackId;
	}

	/** 一回合的攻击 */
	public boolean exec() {
		// 判断是否能释放执行
		if (!canAction()) {
			return true;
		}
		// 如果是变身技能,执行变身
		if (skill.getSkillId() == Player.TRANS_SKILL_ID && source instanceof Player) {
			Player ps = (Player) source;
			ps.transfiguration();
		}
		// 如果是变身技能,执行变身
		if (skill.getSkillId() == Player.UN_TRANS_SKILL_ID && source instanceof Player) {
			Player ps = (Player) source;
			ps.unTransfiguration();
		}

		if (targets == null || targets.size() == 0) {
			// 没有目标，只广播动作
			// Log.error("attackId :" + this.attackId);
			sendDamages();
			return false;
		}
		// 如果玩家已死亡，但使用了死亡可用的技能可以继续执行
		if (source.isDie()) {
			Log.error("sourcesource: " + source);
			isAttack = false;
			return false;
		}

		// 添加/执行技能Buffer
		addSkillBuffers();
		// 触发魂幡buff
		targetFuseBuffer();
		// 技能创建陷阱
		createSnare();
		// 计算技能攻击伤害值
		damages.addAll(skillCalc.calcEffect(source, targets, this));
		// 计算受伤到一定程度后，一些特殊处理，以及伤害生效
		calcDamage();
		// 冷却技能
		coolDown();
		// 执行结束，广播伤害
		sendDamages();
		// 法宝禁制生效
		calMagicwpCompanent();
		return true;
	}

	private void calMagicwpCompanent() {
		if (!(source instanceof Player)) {
			return;
		}
		Player ps = (Player) source;
		// 吸血禁制
		MagicwpCompanent suck_blood = ps.getMagicwpCompanent(MagicwpBanConstant.SUCK_BLOOD);
		if (suck_blood != null && suck_blood.isEffect()) {
			suck_blood.exe(toalBloodDamage);
		}
		// 朱雀羽
		MagicwpCompanent burning = ps.getMagicwpCompanent(MagicwpBanConstant.BURNING);
		if (burning != null && burning.isEffect()) {
			burning.exe();
		}

		// 定身
		MagicwpCompanent fixBody = ps.getMagicwpCompanent(MagicwpBanConstant.FIXED_BODY);
		if (fixBody != null && fixBody.isEffect()) {
			for (Living target : targets) {
				fixBody.exe(target);
			}
		}
		// 沉默目标
		MagicwpCompanent silent = ps.getMagicwpCompanent(MagicwpBanConstant.DISABLE_SKILL);
		if (silent != null && silent.isEffect()) {
			for (Living target : targets) {
				silent.exe(target);
			}
		}
		// 兵解体
		MagicwpCompanent weapon = ps.getMagicwpCompanent(MagicwpBanConstant.WEAPON);
		if (weapon != null && weapon.isEffect()) {
			weapon.exe();
		}

		// 百胜
		MagicwpCompanent invincible = ps.getMagicwpCompanent(MagicwpBanConstant.INVINCIBLE);
		if (invincible != null && invincible.isEffect()) {
			invincible.exe();
		}

		// 攻击自己的敌人,有可能被冻住
		for (Living target : targets) {
			if (target instanceof Player) {
				Player tplayer = (Player) target;
				MagicwpCompanent frozen = tplayer.getMagicwpCompanent(MagicwpBanConstant.FROZEN);
				if (frozen != null && frozen.isEffect()) {
					frozen.exe(source);
					break;
				}
			}
		}

	}

	/**
	 * 给伤害相关人推送伤害包
	 */
	private void sendDamages() {
		// 获取附近的人
		Set<Long> players = source.getNears(new PlayerSelectorHelper(source));
		// 添加自己
		players.add(source.getArmyId());
		// 广播指令
		AttackBroadcastMsg.Builder attackBroMsg = AttackBroadcastMsg.newBuilder();
		attackBroMsg.setSkillId(skill.getActionId());
		attackBroMsg.setLivingId(source.getId());
		attackBroMsg.setLivingType(source.getType());
		attackBroMsg.setExecTime(System.currentTimeMillis());
		attackBroMsg.setAttackId(attackId);

		if (targets != null && targets.size() != 0) {
			for (Living target : targets) {
				attackBroMsg.addTargets(target.getId());
			}
		}

		if (current != null) {
			attackBroMsg.setCurrent(current);
		}
		if (postion != null) {
			attackBroMsg.setPosition(postion);
		}

		if (damages == null) {
			return;
		}
		for (Damage damage : damages) {
			if (damage.getDamageValue() == 0 && damage.getTipType() != Damage.MISS) {
				continue;
			}
			DamageMsg.Builder dmsg = DamageMsg.newBuilder();
			damage.writeProto(dmsg);
			attackBroMsg.addDamages(dmsg);
		}
		// Log.error("----发送给客户端伤害包-------" + attackBroMsg.build());
		BroadcastUtil.sendBroadcastPacket(players, Protocol.U_G_ATTACK_SKILL, attackBroMsg.build());

	}

	/** 再次判断该技能是否能释放执行 */
	private boolean canAction() {
		if (skill == null) {
			System.err.println("技能is null");
			return false;
		}

		if (source.isPlayer()) {
			// 判断玩家，是否能够满足执行技能条件
			if (!source.paySkillExec() || !source.canAction()) {
				isAttack = false;
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查目标是否已经Dead
	 * 
	 * @return
	 */
	public boolean checkTargetsState() {
		if (targets == null) {
			return true;
		}
		for (Living living : targets) {
			if (living.isDie()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 计算目标受到的伤害 同时判断是否会触发相应的BUFFER效果
	 */
	private void calcDamage() {
		// 攻击者行动BUFFER执行
		source.execWayBuffer(this, ExecWayType.ATTACT);

		Set<Long> invincible = new HashSet<>();
		Set<Long> bites = new HashSet<>();
		for (Damage damage : damages) {
			Living target = damage.getTarget();
			if (target != null) {
				// 法宝禁制--是否豁免伤害
				if (target instanceof Player && !damage.isRestore()) {
					Player p = (Player) target;

					// 免疫并反弹收到伤害
					MagicwpCompanent bite = p.getMagicwpCompanent(MagicwpBanConstant.BITE);
					if (bites.contains(target.getId())) {
						bite.exe(damage);
						damage.setDamageValue(0);
						continue;
					}
					if (bite != null && bite.isEffect()) {
						bite.exe(damage);
						damage.setDamageValue(0);
						bites.add(target.getId());
						continue;
					}

					// 免疫但不反弹
					if (invincible.contains(target.getId())) {
						damage.setDamageValue(0);
						continue;
					}
					MagicwpCompanent companent = p.getMagicwpCompanent(MagicwpBanConstant.IMMUNE_DAMAGE);
					if (companent != null && companent.isEffect()) {
						damage.setDamageValue(0);
						invincible.add(target.getId());
						continue;
					}
				}
				// 受伤时
				if (damage.isHurt()) {
					target.execWayBuffer(this, ExecWayType.HURT);
				}
				// 回复时
				if (damage.isRestore()) {

				}
				int bloodHurt = target.takeDamage(damage);
				if (damage.getDamageType() == EnumAttr.CUR_BLOOD.getValue()) {
					addBloodDamage(bloodHurt);
				}
			}
		}

		// 伤害并包--同对象同类型伤害，合并数值
		Map<Long, Damage> bloodDamage = new HashMap<>();
		Map<Long, Damage> soulDamage = new HashMap<>();
		for (Damage damage : damages) {
			Damage matrix = null;
			if (damage.getDamageType() == EnumAttr.CUR_BLOOD.getValue()) {
				matrix = bloodDamage.get(damage.getTargetId());
				if (matrix != null) {
					matrix.setDamageValue(damage.getDamageValue() + matrix.getDamageValue());
					if (matrix.getLeftValue() >= damage.getLeftValue()) {
						matrix.setLeftValue(damage.getLeftValue());
					}
				} else {
					bloodDamage.put(damage.getTargetId(), damage);
				}
			} else if (damage.getDamageType() == EnumAttr.CUR_SOUL.getValue()) {
				matrix = soulDamage.get(damage.getTargetId());
				if (matrix != null) {
					matrix.setDamageValue(damage.getDamageValue() + matrix.getDamageValue());
					if (matrix.getLeftValue() >= damage.getLeftValue()) {
						matrix.setLeftValue(damage.getLeftValue());
					}
				} else {
					soulDamage.put(damage.getTargetId(), damage);
				}
			} else {
				Log.error("出现不属错误属性的伤害包:" + damage.getDamageType() + "  --" + damage.getFromType() + "---" + damage.getFromId());
			}
		}

		bites.clear();
		invincible.clear();
		damages.clear();
		damages.addAll(bloodDamage.values());
		damages.addAll(soulDamage.values());
		bloodDamage.clear();
		soulDamage.clear();
	}

	/**
	 * 冷却技能
	 */
	public void coolDown() {
		if (source != null && skill != null) {
			source.coolDown(skill);
		}
	}

	/** 触发魂幡buff */
	public void targetFuseBuffer() {
		// 魂幡技能buffer
		if (source.getType() != RoleType.player) {
			return;
		}
		// 是否属于普攻技能（普攻技能主类型3）
		if (skill.getTemplateInfo() == null || skill.getTemplateInfo().getMasterType() != 3) {
			return;
		}
		Player player = (Player) source;
		int index = skill.getTemplateInfo().getSonType() - 1;
		Buffer bufCreater = player.getFuseBuffer(index);
		if (bufCreater != null) {
			if (bufCreater.checkValid()) {
				bufCreater.execute(null, Damage.DEFAULT, Damage.DEFAULT, ExecWayType.FUSE);
			}
		}
	}

	/**
	 * 添加技能buffer
	 */
	public void addSkillBuffers() {
		SkillActionTemplateInfo skillAction = skill.getTemplateInfo();
		int[] bufferIds = skillAction.getBufferIdArr();
		if (bufferIds == null || bufferIds.length == 0) {
			return;
		}
		int random = skillAction.getRandom();
		if (rand.next(10000) >= random) {
			return;
		}

		for (int i = 0; i < bufferIds.length; i++) {
			int bufferId = bufferIds[i];
			SkillBufferTemplateInfo temp = BattleTempMgr.getBufferInfo(bufferId);

			if (temp == null) {
				continue;
			}
			if (temp.getDelay() == 0) {
				if (temp.getTargetType() == BufferTargetType.SOURCE) {
					Buffer buff = BufferFactory.createBuffer(source, source, temp);
					source.addBuffer(buff);
				}
				if (temp.getTargetType() == BufferTargetType.SKILL_TARGET) {
					for (Living target : targets) {
						Buffer buff = BufferFactory.createBuffer(source, target, temp);
						buff.setPermanent(false);
						target.addBuffer(buff);
					}
				}
			} else {
				AddDelayBuffAction delayAction = new AddDelayBuffAction(source, targets, temp);
				source.enDelayQueue(delayAction);
			}
		}
	}

	/**
	 * 技能创建陷阱
	 */
	private void createSnare() {
		if (source.getType() != RoleType.monster && source.getType() != RoleType.robot && source.getType() != RoleType.avatar) {
			return;
		}
		SkillActionTemplateInfo skillAction = skill.getTemplateInfo();
		ActiveLiving aliving = (ActiveLiving) source;
		int[] snareArr = skillAction.getSnareIdArr();
		if (snareArr == null || snareArr.length == 0) {
			return;
		}
		for (int snareId : snareArr) {
			SnareTemplateInfo stemp = BattleTempMgr.getSnareTemp(snareId);
			if (stemp == null) {
				continue;
			}

			Living target = null;
			if (targets != null && targets.size() > 0) {
				target = targets.get(0);
			}
			Snare snare = new Snare(stemp, aliving, target);
			aliving.addSnare(snare);
			snare.setArmyId(aliving.getArmyId());

			snare.setPostion(BattleUtil.getBornPos(aliving, target, stemp));
			Field field = aliving.getField();
			if (field != null) {
				field.enterField(snare);
			}
		}
	}

	public boolean isExec() {
		return isExec;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("source:" + source.getArmyId());
		for (Living target : targets) {
			sb.append("targets:" + target.getArmyId() + "  ");
		}
		sb.append("skill :" + skill.getSkillId());
		return sb.toString();

	}

	public Living getSource() {
		return source;
	}

	public Skill getSkill() {
		return skill;
	}

	public List<Damage> getDamages() {
		return damages;
	}

	public void setTargets(List<Living> targets) {
		this.targets = targets;
	}

	public List<Living> getTargets() {
		return targets;
	}

	public int getOrderId() {
		return orderId;
	}

	public SkillCalc getSkillCalc() {
		return skillCalc;
	}

	public void setSkillCalc(SkillCalc skillCalculator) {
		this.skillCalc = skillCalculator;
	}

	public boolean isAttack() {
		return isAttack;
	}

	public void setCurrent(PBVector3 current) {
		this.current = current;
	}

	public void setPostion(PBVector3 postion) {
		this.postion = postion;
	}

	public void addBloodDamage(int bloodDamageValue) {
		this.toalBloodDamage += bloodDamageValue;
	}

}