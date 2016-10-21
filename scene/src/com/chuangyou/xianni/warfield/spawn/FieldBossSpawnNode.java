package com.chuangyou.xianni.warfield.spawn;

import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.fieldBoss.manager.FieldBossHelper;
import com.chuangyou.xianni.role.objects.FieldBoss;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.warfield.field.Field;

public abstract class FieldBossSpawnNode extends MonsterSpawnNode {

	public FieldBossSpawnNode(SpawnInfo info, Field field) {
		super(info, field);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reset() {
		super.reset();
	}
	
	@Override
	public void stateTransition(NodeState state) {
		super.stateTransition(state);
	}
	
	@Override
	public void prepare() {
		
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		if(state.getCode() == NodeState.WORK){
			createBoss();
		}
	}
	
	@Override
	public void over() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void lvingDie(Living living) {
		// TODO Auto-generated method stub
		if(field != null){
			children.remove(living.getId());
			field.addDeathLiving(living);
			stateTransition(new OverState(this));
			
			this.dieTrigger(living);
			
			FieldBossHelper.bossKillAward(this, (FieldBoss)living);
		}
	}
	
	public abstract FieldBossCfg getBossCfg();
	
	/**
	 * 刷新BOSS
	 */
	protected abstract void createBoss();
	
	/**
	 * 死亡触发事件
	 * @param living
	 */
	protected abstract void dieTrigger(Living living);

}
