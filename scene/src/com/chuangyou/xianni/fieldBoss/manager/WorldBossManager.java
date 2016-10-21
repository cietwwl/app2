package com.chuangyou.xianni.fieldBoss.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossDieInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.fieldBoss.action.CreateWorldBossAction;
import com.chuangyou.xianni.fieldBoss.template.FieldBossTemplateMgr;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.spawn.WorkingState;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;

public class WorldBossManager {

	/**
	 * 当前节点
	 * 世界BOSS有多个埋点，但同一时刻只有一个节点生效
	 */
	private static SpwanNode curNode = null;
	
	/**
	 * 初始化世界BOSS
	 */
	public static void init(){
		FieldBossCfg worldBossCfg = FieldBossTemplateMgr.getWorldBossCfg();
		if(worldBossCfg == null){
			Log.warn("刷新世界BOSS出错，未配置世界BOSS：b_z_field_boss");
			return;
		}
		FieldBossDieInfo info = DBManager.getFieldBossInfoDao().get(worldBossCfg.getMonsterId());
		//如果有死亡记录，按记录的下次刷新时间刷新BOSS
		if(info != null){
			long nextTime = info.getNextTime().getTime();
			
			int delay = 0;
			if(nextTime <= System.currentTimeMillis()){
				delay = 1;
			}else{
				delay = (int)(nextTime - System.currentTimeMillis());
				if(delay < 0){
					Log.error("刷新世界BOSS出错，刷新时间计算错误");
					return;
				}
			}
			
			CreateWorldBossAction action = new CreateWorldBossAction(delay);
			action.getActionQueue().enDelayQueue(action);
		}else{
			//无记录，初始化第一次刷新BOSS时间
			initFirstNode();
		}
		Log.info("初始化世界BOSS完成");
	}
	
	/**
	 * 节点死亡/结束时调用
	 */
	public static void nodeOver(){
		computeNextNode();
	}
	
	/**
	 * 第一次开服时，初始化第一次刷新时间
	 */
	private static void initFirstNode(){
		FieldBossCfg worldBossCfg = FieldBossTemplateMgr.getWorldBossCfg();
		
		List<String> timeList = worldBossCfg.getTimeList();
		if(timeList == null || timeList.size() < 2){
			Log.error("刷新世界BOSS出错，世界BOSS刷新时间配置错误");
			return;
		}
		Calendar startTime = TimeUtil.getCalendar(TimeUtil.getDateFromNowByString(timeList.get(0)));
		Calendar endTime = TimeUtil.getCalendar(TimeUtil.getDateFromNowByString(timeList.get(1)));
		
		long currentTimeMillis = System.currentTimeMillis();
		int random = new ThreadSafeRandom().next(startTime.get(Calendar.HOUR), endTime.get(Calendar.HOUR));
		
		//开服时间超过今天刷新结束时间点，下一天刷新
		if(currentTimeMillis > endTime.getTimeInMillis()){
			startTime.add(Calendar.DATE, 1);
		}
		startTime.set(Calendar.HOUR, random);
		
		//下次刷新时间
		long nextTime = startTime.getTimeInMillis();
		
		int delay = 0;
		//随机到的时间如果在开服时间之前，立即刷新
		if(nextTime <= currentTimeMillis){
			delay = 1;
		}else{
			delay = (int)(nextTime - currentTimeMillis);
		}
		CreateWorldBossAction action = new CreateWorldBossAction(delay);
		action.getActionQueue().enDelayQueue(action);
		
		FieldBossHelper.bossNextTimeUpdate(worldBossCfg.getMonsterId(), 0, nextTime);
	}
	
	/**
	 * 计算下一次刷新世界BOSS
	 */
	private static void computeNextNode(){
		FieldBossCfg worldBossCfg = FieldBossTemplateMgr.getWorldBossCfg();
		if(worldBossCfg == null){
			Log.warn("刷新世界BOSS出错，未配置世界BOSS：b_z_field_boss");
			return;
		}
		
		List<String> timeList = worldBossCfg.getTimeList();
		if(timeList == null || timeList.size() < 2){
			Log.error("刷新世界BOSS出错，世界BOSS刷新时间配置错误");
			return;
		}
		Calendar startTime = TimeUtil.getCalendar(TimeUtil.getDateFromNowByString(timeList.get(0)));
		Calendar endTime = TimeUtil.getCalendar(TimeUtil.getDateFromNowByString(timeList.get(1)));
		
		long currentTimeMillis = System.currentTimeMillis();
		int random = new ThreadSafeRandom().next(startTime.get(Calendar.HOUR), endTime.get(Calendar.HOUR));
		
		if(currentTimeMillis > startTime.getTimeInMillis()){
			startTime.add(Calendar.DATE, 1);
		}
		startTime.set(Calendar.HOUR, random);
		
		long nextTime = startTime.getTimeInMillis();
		
		int delay = (int)(nextTime - currentTimeMillis);
		if(delay < 0){
			Log.error("刷新世界BOSS出错，刷新时间计算错误");
			return;
		}
		
		CreateWorldBossAction action = new CreateWorldBossAction(delay);
		action.getActionQueue().enDelayQueue(action);
		
		FieldBossHelper.bossNextTimeUpdate(worldBossCfg.getMonsterId(), currentTimeMillis, nextTime);
	}
	
	/**
	 * 时间到了创建下一个世界BOSS
	 */
	public static void createNext(){
		FieldBossCfg worldBossCfg = FieldBossTemplateMgr.getWorldBossCfg();
		if(worldBossCfg == null){
			Log.warn("刷新世界BOSS出错，未配置世界BOSS：b_z_field_boss");
			return;
		}
		
		int startTagId = worldBossCfg.getTagId();
		int endTagId = worldBossCfg.getTagIdEnd();
		
		if(startTagId > endTagId){
			Log.warn("刷新世界BOSS出错，b_z_field_boss : monsterId = " + worldBossCfg.getMonsterId());
			return;
		}
		
		//根据配置中的tagId段，取出所有在tagId段内的节点
		List<SpawnInfo> poolList = new ArrayList<>();
		for(int i = startTagId; i <= endTagId; i++){
			int spawnId = SpawnTemplateMgr.getSpwanId(i);
			SpawnInfo spawnInfo = SpawnTemplateMgr.getSpawnInfo(spawnId);
			if(spawnInfo != null){
				poolList.add(spawnInfo);
			}
		}
		
		if(poolList.size() <= 0){
			Log.warn("刷新世界BOSS出错，在spawn表中找不到对应的tagId配置，b_z_field_boss : monsterId = " + worldBossCfg.getMonsterId());
			return;
		}
		
		//在节点池中随机取一个节点
		int randomIndex = (new ThreadSafeRandom()).next(poolList.size());
		SpawnInfo spawnInfo = poolList.get(randomIndex);
		
		Field field =  FieldMgr.getIns().getField(spawnInfo.getMapid());
		if(field == null || field.getMapKey() != spawnInfo.getMapid()){
			Log.error("刷新世界BOSS出错,节点中配置的mapId未找到 : spawnId = " + spawnInfo.getId() + "   mapId = " + spawnInfo.getMapid());
			return;
		}
		SpwanNode spawnNode = field.getSpawnNode(spawnInfo.getId());
		if(spawnNode == null){
			Log.error("刷新世界BOSS出错，场景中没有刷新节点: mapId = " + spawnInfo.getMapid() + " , spawnId = " + spawnInfo.getId());
			return;
		}
		curNode = spawnNode;
		spawnNode.stateTransition(new WorkingState(spawnNode));
	}

	public static SpwanNode getCurNode() {
		return curNode;
	}
	
}
