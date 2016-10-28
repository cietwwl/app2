package com.chuangyou.xianni.battle.damage;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;

/**
 * 伤害统计
 *
 */
public class DamageStatistic {

	/** 受伤害的怪物 */
	private Monster monster;
	
	/** 伤害记录 */
	private Map<Long, Long> damageMap = new HashMap<Long, Long>();
	
	/** 第一击伤害发起者ID */
	private long firstDamageSender;
	
	/** 造成最高伤害者ID */
	private long mostDamageSender;
	
	/** 击杀者 */
	private Living killer;
	
	public DamageStatistic(Monster monster) {
		// TODO Auto-generated constructor stub
		this.monster = monster;
	}
	
	/**
	 * 受到伤害时统计
	 * @param damage
	 */
	public void addDamage(Damage damage){
		if(damage.getDamageValue() >= 0){
			long sourceId = damage.getSource().getId();
			
			//第一击统计
			if(firstDamageSender <= 0){
				firstDamageSender = sourceId;
			}
			
			//伤害统计
			if(!damageMap.containsKey(sourceId)){
				damageMap.put(sourceId, 0l);
			}
			long damageTotal = damageMap.get(sourceId) + damage.getDamageValue();
			damageMap.put(sourceId, damageTotal);
			
			//最高伤害统计
			if(mostDamageSender > 0 && damageMap.containsKey(mostDamageSender) == true){
				long maxDamageValue = damageMap.get(mostDamageSender);
				if(damageTotal > maxDamageValue){
					mostDamageSender = sourceId;
				}
			}else{
				mostDamageSender = sourceId;
			}
		}
	}
	
	/**
	 * 怪物速归后重置伤害统计
	 */
	public void reset(){
		damageMap.clear();
		
		firstDamageSender = 0;
		mostDamageSender = 0;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Map<Long, Long> getDamageMap() {
		return damageMap;
	}

	public void setDamageMap(Map<Long, Long> damageMap) {
		this.damageMap = damageMap;
	}

	public long getFirstDamageSender() {
		return firstDamageSender;
	}

	public void setFirstDamageSender(long firstDamageSender) {
		this.firstDamageSender = firstDamageSender;
	}

	public long getMostDamageSender() {
		return mostDamageSender;
	}

	public void setMostDamageSender(long mostDamageSender) {
		this.mostDamageSender = mostDamageSender;
	}

	public Living getKiller() {
		return killer;
	}

	public void setKiller(Living killer) {
		this.killer = killer;
	}
}
