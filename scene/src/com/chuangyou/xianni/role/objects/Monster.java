package com.chuangyou.xianni.role.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.chuangyou.common.protobuf.pb.PlayerKillMonsterProto.PlayerKillMonsterMsg;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai.proxy.MonsterAI;
import com.chuangyou.xianni.battle.action.MonsterPollingAction;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.config.SceneGlobal;
import com.chuangyou.xianni.cooldown.CoolDownTypes;
import com.chuangyou.xianni.drop.manager.DropManager;
import com.chuangyou.xianni.entity.skill.SkillActionMoveTempleteInfo;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.manager.SceneManagers;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.action.UpdatePositionAction;
import com.chuangyou.xianni.role.helper.Hatred;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.spawn.MonsterSpawnNode;

public class Monster extends ActiveLiving {
	protected MonsterSpawnNode	node;
	private MonsterInfo			monsterInfo;

	// 怪物攻击的初始技能，固定写死
	private int					skillId;
	// 初始位置
	private Vector3				initPosition;
	// 仇恨列表
	private List<Hatred>		hatreds	= new ArrayList<Hatred>();
	// 攻击目标
	private Long				target;
	// 当前使用的技能id
	private int					curSkillID;

	public int getCurSkillID() {
		return curSkillID;
	}

	public void setCurSkillID(int curSkillID) {
		this.curSkillID = curSkillID;
		// 写死一个怪物执行技能
		// Skill test = new Skill(BattleTempMgr.getActionInfo(curSkillID));
		// addSkill(test);
	}

	public Long getTarget() {
		return target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	public List<Hatred> getHatreds() {
		return hatreds;
	}

	public Vector3 getInitPosition() {
		return initPosition;
	}

	public Monster(MonsterSpawnNode node) {
		super(IDMakerHelper.nextID());
		setType(RoleType.monster);
		this.node = node;
		enDelayQueue(new MonsterAI(this));
		enDelayQueue(new UpdatePositionAction(this));
		enDelayQueue(new MonsterPollingAction(this));
		// setCurSkillID(1001);
	}

	public boolean onDie(Living killer) {
		if (super.onDie(killer)) {
			if (node != null) {
				node.lvingDie(this);
			}
			DropManager.dropFromMonster(this.getSkin(), killer.getArmyId(), this.getId(), this.getField().id, this.getPostion());
			if (killer != null && killer.getArmyId() > 0) {
				notifyCenter(this.getSkin(), killer.getArmyId());
			}
		}
		return true;
	}

	@Override
	public void enterField(Field f) {
		// TODO Auto-generated method stub
		super.enterField(f);
		initPosition = getPostion().clone();
	}

	/**
	 * 通知
	 * 
	 * @param tempId
	 * @param playerId
	 */
	protected void notifyCenter(int tempId, long playerId) {
		PlayerKillMonsterMsg.Builder msg = PlayerKillMonsterMsg.newBuilder();
		msg.setMonsterTemplateId(tempId);
		msg.setPlayerId(playerId);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_PLAYER_KILL_MONSTER, msg);
		GatewayLinkedSet.send2Server(pkg);
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	@Override
	protected void addHatred(Damage damage) {
		// TODO Auto-generated method stub
		super.addHatred(damage);
		// 排除自己给自己的伤害包
		if (damage.getSource().id != id) {
			if (damage.getDamageValue() < 0)
				return; // 移除增益效果
			if (damage.getSkillId() > 0 && damage.getTipType() != Damage.MISS) {
				// 非Buff伤害，更新受击时间搓
				SkillActionTemplateInfo actionInfo = BattleTempMgr.getActionInfo(damage.getSkillId());
				if (actionInfo != null) {
					SkillActionMoveTempleteInfo moveInfo = BattleTempMgr.getActionMoveInfo(actionInfo.getMove());
					if (moveInfo != null) {
						if (moveInfo.getMove_hitbackstep() > 0) {
							float hitbackstep = moveInfo.getMove_hitbackstep() / 10.0f;
							// System.out.println("hitbackstep = " + hitbackstep
							// + "getPostion() = " + getPostion() + "
							// damage.getSource().getPostion() = " +
							// damage.getSource().getPostion());
							Vector3 dir = Vector3.sub(getPostion(), damage.getSource().getPostion());
							dir.y = getPostion().y;
							Vector3 hitBackPoint = MathUtils.GetVector3ByDir(getPostion(), dir.getNormalize(), hitbackstep);
							// System.out.println("id = " + id + "hitBackPoint =
							// " + hitBackPoint);
							if (isValidPoint(hitBackPoint))
								setPostion(hitBackPoint);
						}
					}
				}
				addCooldown(CoolDownTypes.BE_ATTACK, null, SceneGlobal.AI_BEATTACK_TIME);
			}
			Hatred hatred = null;
			List<Hatred> hatreds = getHatreds();
			for (int i = 0; i < hatreds.size(); i++) {
				if (hatreds.get(i).getTarget() == damage.getSource().id) {
					hatred = hatreds.remove(i);
					break;
				}
			}
			if (hatred == null) {
				hatred = SceneManagers.hatredManager.getHatred();
				hatred.setTarget(damage.getSource().id);
				hatred.setFirstAttack(System.currentTimeMillis());
			}
			// 增加仇恨值
			hatred.setHatred(hatred.getHatred() + damage.getDamageValue());
			hatred.setLastAttack(System.currentTimeMillis());
			// 插入仇恨列表（按仇恨值大小排列）
			for (int i = 0; i < hatreds.size(); i++) {
				if (hatreds.get(i).getHatred() < hatred.getHatred()) {
					hatreds.add(i, hatred);
					return;
				}
			}
			hatreds.add(hatred);
		}
	}

	/**
	 * 清除仇恨
	 */
	public void cleanHatreds() {
		Iterator<Hatred> iter = this.getHatreds().iterator();
		while (iter.hasNext()) {
			Hatred hatred = iter.next();
			SceneManagers.hatredManager.removeHatred(hatred);
			iter.remove();
		}
	}

	/**
	 * 最大仇恨
	 * 
	 * @return
	 */
	public Hatred getMaxHatred() {
		if (getHatreds() != null && getHatreds().size() > 0) {
			Hatred max = getHatreds().get(0);
			return max;
		}
		return null;
	}

	/**
	 * 获得攻击目标
	 * 
	 * @return
	 */
	public Long getAttackTarget() {
		return getDefaultAttackTarget();
	}

	public void clearWorkBuffer() {
		workBuffers.clear();
	}

	/**
	 * 获取默认攻击目标
	 * 
	 * @return
	 */
	public Long getDefaultAttackTarget() {
		if (this.getHatreds().size() > 0) {
			Iterator<Hatred> iter = this.getHatreds().iterator();
			while (iter.hasNext()) {
				Hatred hatred = (Hatred) iter.next();
				Living target = getField().getLiving(hatred.getTarget());
				if (target == null)
					continue;
				if (target.isDie())
					continue;
				return hatred.getTarget();
			}
		}
		return -1l;
	}

	/**
	 * 移除一个仇恨
	 * 
	 * @param id
	 */
	public void removeHatred(Long id) {
		// 遍历仇恨列表
		Iterator<Hatred> iter = this.getHatreds().iterator();
		while (iter.hasNext()) {
			Hatred hatred = (Hatred) iter.next();
			if (hatred.getTarget() == id) {
				iter.remove();
				break;
			}
		}
	}

	/**
	 * 重新计算仇恨列表
	 */
	public void countHatreds() {
		// 仇恨过时，但在警戒范围内仇恨度最高的目标
		Hatred lastOverHatred = null;
		if (this.getHatreds().size() > 0) {
			long valid = System.currentTimeMillis() - SceneGlobal.AI_MONSTER_OVERDUE;
			// 遍历仇恨列表
			Iterator<Hatred> iter = this.getHatreds().iterator();
			while (iter.hasNext()) {
				Hatred hatred = (Hatred) iter.next();
				Living target = getField().getLiving(hatred.getTarget());
				if (target == null) {
					SceneManagers.hatredManager.removeHatred(hatred);
					iter.remove();
					continue;
				}
				if (target.isDie()) {
					SceneManagers.hatredManager.removeHatred(hatred);
					iter.remove();
					continue;
				}
				// 攻击过期，移出仇恨列表
				if (hatred.getLastAttack() < valid) {
					// 仇恨过时，但在警戒范围内仇恨度最高的目标，暂时保留在仇恨列表中
					if (lastOverHatred == null)
						lastOverHatred = hatred;
					else {
						// 移出仇恨列表
						SceneManagers.hatredManager.removeHatred(hatred);
						iter.remove();
					}
				}
			}
			if (lastOverHatred != null && this.getHatreds().size() > 1) {
				// 移出仇恨列表
				SceneManagers.hatredManager.removeHatred(lastOverHatred);
				this.getHatreds().remove(lastOverHatred);
			}
		}
	}

	public boolean isClear() {
		return System.currentTimeMillis() >= dieTime + 5 * 1000;
	}

	public MonsterInfo getMonsterInfo() {
		return monsterInfo;
	}

	public void setMonsterInfo(MonsterInfo monsterInfo) {
		this.monsterInfo = monsterInfo;
	}

}
