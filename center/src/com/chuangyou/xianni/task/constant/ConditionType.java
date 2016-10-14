package com.chuangyou.xianni.task.constant;

public interface ConditionType {
	
	////////////////////////////////////////////////////////
	// 1杀怪、2通关副本、3npc对话、4采集、5获得道具、6交付道具
	/**
	 * 杀怪
	 */
	public static final byte	KILL_MONST				= 1;
	/**
	 * 通关副本
	 */
	public static final byte	PASS_FB					= 2;
	/**
	 * npc对话
	 */
	public static final byte	NPC_DIALOG				= 3;
	/**
	 * 采集
	 */
	public static final byte	PATCH					= 4;
	/**
	 * 获得道具
	 */
	public static final byte	GET_ITEM				= 5;
	/**
	 * 交付道具
	 */
	public static final byte	COMMIT_ITEM				= 6;
	/**
	 * 触发器系统
	 */
	public static final byte	TRIGGER					= 7;
	/**
	 * 天逆珠系统登记
	 *
	 **/
	public static final byte	T_SYSTEM				= 8;

	/** QTE界面交互 */
	public static final byte	QTE						= 9;

	/** 杀私有怪任务类型 */
	public static final byte	KILL_PRIVATE_MONSTER	= 10;
	/**
	 * 人物等级
	 */
	public static final byte    PLAYER_LV               = 11;
	/**
	 * 玩家战斗力
	 */
	public static final byte 	PLAYER_FIGHT                = 12;
	/**
	 * 修炼系统阶段等级
	 */
	public static final byte    SKILL_STAGE             = 13;
	/**
	 * 装备相关的任务
	 */
	public static final byte    EQUIP                   = 14;
	
	/**
	 * 魂幡
	 */
	public static final byte    SOUL                 = 15;
	
	/**
	 * 坐骑相关
	 */
	public static final byte 	MOUNT 					= 16;
	
	/**
	 * 神器
	 */
	public static final byte 	ARTIFACTDATA            = 17;
	
	/**
	 * 法宝等级
	 */
	public static final byte   MAGICWP  		= 18;
	
	/**
	 * 宠物
	 */
	public static final byte PET = 19;
	
	/**
	 * 运镖
	 */
	public static final byte Dart = 20;
	

	
	
	
	
	
	
	/////////////////////////////////////////////////////
}
