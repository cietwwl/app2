package com.chuangyou.xianni.warfield.spawn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.util.AccessTextFile;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.config.SceneGlobal;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.SpwanInfoType;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.role.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.warfield.field.Field;

public class MonsterSpawnNode extends SpwanNode { // 刷怪模板
	private int					toalCount;							// 刷怪总数
	private int					curCount;							// 当前数量
	private ThreadSafeRandom	random	= new ThreadSafeRandom();
	private Map<Long, Living>	children;							// 子孙们

	public MonsterSpawnNode(SpawnInfo info, Field field) {
		super(info, field);
		this.nodeType = SpwanInfoType.MONSTER;
		this.children = new ConcurrentHashMap<>();
	}

	public void reset() {
		this.toalCount = 0;
		this.curCount = 0;
		this.children.clear();
	}

	public void stateTransition(NodeState state) {
		super.stateTransition(state);
	}

	@Override
	public void prepare() {
		System.out.println(".........准备状态...........");
	}

	@Override
	public void start() {
		super.start();
		System.err.println("spwanInfo :" + spwanInfo.getId());
		while (curCount < spwanInfo.getMaxCount() && (toalCount < spwanInfo.getToalCount() || spwanInfo.getToalCount() <= 0)) {
			createChildren();
		}
	}

	public void lvingDie(Living living) {
		if (field != null) {
			children.remove(living.getId());
			field.addDeathLiving(living);
			if (isOver()) {
				stateTransition(new OverState(this));
			} else {
				field.enDelayQueue(new CreateChildAction());
			}
		}
	}

	class CreateChildAction extends DelayAction {
		public CreateChildAction() {
			super(field, SceneGlobal.MONSTER_SPAWN_TIME);
		}

		@Override
		public void execute() {
			createChildren();
		}
	}

	private void createChildren() {
		if (spwanInfo.getToalCount() > 0 && toalCount >= spwanInfo.getToalCount()) {
			return;
		}
		int randomx = spwanInfo.getBound_x();
		int randomy = spwanInfo.getBound_y();
		int randomz = spwanInfo.getBound_z();

		Monster monster = new Monster(this);
		System.out.println("monster - skin = " + spwanInfo.getEntityId() + "map:" + field.getMapKey());
		MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get(spwanInfo.getEntityId());
		if (monsterInfo != null) {
			// monster.setSkin(monsterInfo.getSkin());
			monster.setPostion(new Vector3(randomx / Vector3.Accuracy, randomy / Vector3.Accuracy, randomz / Vector3.Accuracy));
			instill(monster, monsterInfo);
			children.put(monster.getId(), monster);
			field.enterField(monster);
			// NotifyNearHelper.notifyNearPlayer(field, monster,
			// monster.getPostion());
		} else {
			System.err.println(spwanInfo.getId() + "----" + spwanInfo.getEntityId() + " 在MonsterInfo里面未找到配置");
		}
		curCount++;
		toalCount++;
	}

	/** 浸染 */
	public static void instill(Monster monster, MonsterInfo monsterInfo) {
		monster.setMonsterInfo(monsterInfo);
		monster.setSpeed(monsterInfo.getMoveSpeed());
		monster.setSkin(monsterInfo.getMonsterId());

		monster.setProperty(EnumAttr.MAX_BLOOD, monsterInfo.getHp());
		monster.setProperty(EnumAttr.BLOOD, monsterInfo.getHp());
		monster.setProperty(EnumAttr.CUR_BLOOD, monsterInfo.getHp());

		monster.setProperty(EnumAttr.MAX_SOUL, monsterInfo.getSoulHpValue());
		monster.setProperty(EnumAttr.SOUL, monsterInfo.getSoulHpValue());
		monster.setProperty(EnumAttr.CUR_SOUL, monsterInfo.getSoulHpValue());

		monster.setProperty(EnumAttr.ATTACK, monsterInfo.getHurtValue());
		monster.setProperty(EnumAttr.DEFENCE, monsterInfo.getArmorValue());
		monster.setProperty(EnumAttr.SOUL_ATTACK, monsterInfo.getSoulHurtValue());
		monster.setProperty(EnumAttr.SOUL_DEFENCE, monsterInfo.getSoulArmorValue());
		monster.setProperty(EnumAttr.ACCURATE, monsterInfo.getHitRateValue());
		monster.setProperty(EnumAttr.DODGE, monsterInfo.getDodgeValue());
		monster.setProperty(EnumAttr.CRIT, monsterInfo.getCritValue());
		monster.setProperty(EnumAttr.CRIT_DEFENCE, monsterInfo.getToughnessValue());

		if (monsterInfo.getHp() <= 0) {
			monster.setSoulState(true);
		}
		String skillIds = monsterInfo.getSkillIds();
		if (skillIds != null && !skillIds.isEmpty()) {
			for (String str : skillIds.split(",")) {
				SkillTempateInfo skillTempateInfo = BattleTempMgr.getBSkillInfo(Integer.valueOf(str));
				if (skillTempateInfo == null) {
					continue;
				}
				Skill skill = new Skill(BattleTempMgr.getActionInfo(skillTempateInfo.getActionId()));
				skill.setSkillTempateInfo(skillTempateInfo);
				monster.addSkill(skill);
				// monster.setSkillId(monsterInfo.get);
			}
		} else {
			// 测试
			Skill test = new Skill(BattleTempMgr.getActionInfo(1001));
			monster.addSkill(test);
		}

	}

	public boolean isOver() {
		return spwanInfo.getToalCount() > 0 && toalCount >= spwanInfo.getToalCount() && children.size() == 0;
	}

}
