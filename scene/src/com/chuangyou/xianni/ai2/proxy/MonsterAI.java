
package com.chuangyou.xianni.ai2.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.ai2.behavior.MonsterBaseBehavior;
import com.chuangyou.xianni.ai2.listener.AiCondition;
import com.chuangyou.xianni.ai2.listener.executor.BaseExecutor;
import com.chuangyou.xianni.config.SceneGlobal;
import com.chuangyou.xianni.entity.buffer.LivingState;
import com.chuangyou.xianni.entity.spawn.AIEventTemplate;
import com.chuangyou.xianni.manager.SceneManagers;
import com.chuangyou.xianni.role.action.UpdatePositionActionUtil;
import com.chuangyou.xianni.role.helper.Hatred;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.selectors.MonsterAttackerSelectorHelper;

public class MonsterAI extends AI {
	protected Map<AIState2, MonsterBaseBehavior>	behaviors;
	protected Map<Integer, AiCondition>				conditions;
	protected AIState2								current			= AIState2.INVALID;
	protected Monster								living;
	protected int									delay;
	// 计算仇恨时间
	private long									calHatredsTime	= 0;

	public MonsterAI(Monster m) {
		super(m);
		this.living = m;
		behaviors = new HashMap<AIState2, MonsterBaseBehavior>();
		conditions = new HashMap<>();
	}

	public void exec() {
		// AI已经死亡
		if (living.isDie()) {
			return;
		}

		if (!living.isArrial()) {
			UpdatePositionActionUtil.updataCurPos(living);
		}

		if (living.getHatreds().size() > 0 && current == AIState2.INVALID) {
			current = AIState2.IDLE;
		}

		long now = System.currentTimeMillis();
		// 搜索敌方以及附近的人
		if (now - living.getFindEnemyTime() >= SceneGlobal.AI_MONSTER_FIND_ENEMY) {
			boolean find = autoAddHatred();
			if (find && current == AIState2.INVALID) {
				current = AIState2.IDLE;
			}
			living.setFindEnemyTime(now);
		}

		// 重新计算仇恨
		if (now - calHatredsTime >= SceneGlobal.AI_MONSTER_HETRED_RECOUNT) {
			living.countHatreds();
			calHatredsTime = now;
		}

		if (current == AIState2.INVALID || living.isRunBack()) {
			return;
		}

		// 当怪物处于需移动并且状态不允许移动时,直接跳回
		if ((current == AIState2.PATROL || current == AIState2.CHASE || current == AIState2.RUNBACK || current == AIState2.RUNAWAY) && !living.checkStatus(LivingState.MOVE)) {
			living.stop(true);
			return;
		}

		MonsterBaseBehavior behavior = MonsterBaseBehavior.BehaviorBuilder(current, living);
		if (behavior == null) {
			return;
		}
		current = AIState2.INVALID;
		current = behavior.exe();
	}

	public String getDesc(AIState2 current) {
		switch (current) {
			case IDLE:
				return "IDLE";
			case PATROL:
				return "PATROL";
			case ATTACK:
				return "ATTACK";
			case CHASE:
				return "CHASE";
			case RUNBACK:
				return "RUNBACK";
			case RUNAWAY:
				return "RUNAWAY";
			default:
				return "没有任何状态";
		}
	}

	/**
	 * 将警戒内的对象加入仇恨列表
	 */
	protected boolean autoAddHatred() {
		Monster monster = (Monster) this.living;
		if (monster.getAiConfig() == null) {
			return false;
		}
		Set<Long> ids = monster.getNears(new MonsterAttackerSelectorHelper(this.living));// 获得警戒范围内的玩家
		if (ids == null || ids.size() == 0) {
			return false;
		}
		boolean activeAttackPlayer = monster.getAiConfig().isActiveAttackPlayer();
		boolean activeAttackSameMonster = monster.getAiConfig().isActiveAttackSameMonster();
		boolean activeAttackNotSameMonster = monster.getAiConfig().isActiveAttackNotSameMonster();
		if (activeAttackPlayer || activeAttackSameMonster || activeAttackNotSameMonster) {
			for (long id : ids) {
				Field f = this.living.getField();
				Living nearLiving = f.getLiving(id);
				if (nearLiving == null) {
					continue;
				}
				// 不主动攻击
				if (!activeAttackPlayer) {
					continue;
				}
				List<Hatred> hatreds = monster.getHatreds();
				for (int i = 0; i < hatreds.size(); i++) {
					if (i < hatreds.size() && hatreds.get(i).getTarget() == id) {
						continue;
					}
				}
				Hatred hatred = SceneManagers.hatredManager.getHatred();
				hatred.setTarget(id);
				hatred.setHatred(0);
				hatred.setLastAttack(System.currentTimeMillis());
				hatreds.add(hatred);
				if (living.getTarget() == 0) {
					living.countHatreds();
				}
			}
		}
		return true;
	}

	/** 初始化被动触发行为AI */
	public void initEventAI(List<AIEventTemplate> events) {
		Map<Integer, BaseExecutor> execurtors = new HashMap<>();
		for (AIEventTemplate template : events) {
			// 执行条件，达成条件触发行为
			AiCondition condition = null;
			if (conditions.containsKey(template.getConditionType())) {
				condition = conditions.get(template.getConditionType());
			} else {
				condition = AiCondition.createAiCondition(template);
			}
			if (condition == null) {
				continue;
			}

			// 具体执行行为，一个怪物同一种行为共用一个对象
			BaseExecutor executor = null;
			if (execurtors.containsKey(template.getExecutorType())) {
				executor = execurtors.get(template.getExecutorType());
			} else {
				BaseExecutor bexe = BaseExecutor.createExecutor(template);
				execurtors.put(template.getExecutorType(), bexe);
			}
			if (executor == null) {
				continue;
			}
			condition.add(template.getExecutorType(), executor);
		}
	}
}
