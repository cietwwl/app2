package com.chuangyou.xianni.role.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.chuangyou.common.protobuf.pb.PlayerAttSnapProto.PlayerAttSnapMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BufferMsgProto.BufferMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageListMsgProtocol.DamageListMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.protobuf.pb.battle.LivingStateChangeMsgProto.LivingStateChangeMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferOverlayType;
import com.chuangyou.xianni.battle.buffer.BufferState;
import com.chuangyou.xianni.battle.buffer.BufferType;
import com.chuangyou.xianni.battle.buffer.ExecWayType;
import com.chuangyou.xianni.battle.buffer.specialbuf.AttackConvertSoulAttackBuffer;
import com.chuangyou.xianni.battle.buffer.specialbuf.AttributesBuffer;
import com.chuangyou.xianni.battle.buffer.specialbuf.SoulAttackConvertAttackBuffer;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.damage.effect.DamageEffecter;
import com.chuangyou.xianni.battle.damage.effect.DamageEffecterFactory;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.constant.DamageEffecterType;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.RoleConstants;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.cooldown.CoolDownTypes;
import com.chuangyou.xianni.cooldown.obj.CoolDown;
import com.chuangyou.xianni.entity.buffer.LivingState;
import com.chuangyou.xianni.entity.buffer.LivingStatusTemplateInfo;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.TruckerStateHelper;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.grid.Grid;
import com.chuangyou.xianni.warfield.grid.GridCoord;
import com.chuangyou.xianni.warfield.grid.GridItem;
import com.chuangyou.xianni.warfield.helper.Selector;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.SimplePlayerInfo;
import com.chuangyou.xianni.world.WorldMgr;

/***
 * 需要瘦身
 * 
 * @author Administrator
 *
 */
public class Living extends LivingProperties {
	public static final int						ALIVE					= 0;				// 活着
	public static final int						DIE						= 1;				// 死亡
	public static final int						DISTORY					= 2;				// 移除

	/** 对象的生死 生/死 */
	static final int							LIVING					= 1;
	/** 对象作战状态 元魂/气血 */
	static final int							FIGHT_STATU				= 2;
	/** 是否闪名 */
	static final int							BATTLE_MODE				= 3;
	/** 陷阱行为 */
	static final int							ACTION_STATU			= 4;
	/** 合体状态 */
	static final int							TRANSFIGURATION_STATU	= 5;

	/// livingId
	protected long								id;
	// 部队ID
	protected long								armyId;
	/// 皮肤， npc：npcid
	protected int								skin;
	/// Living类型
	protected int								type;
	/// 格子索引
	protected int								gridIndex;

	/// 简单用户信息
	protected SimplePlayerInfo					simpleInfo;
	protected int								job;										// 职业
	protected int								curSoul;									// 当前血量

	/**
	 * 气血效果 气血值=0 进入元魂状态，当脱离战斗状态或者气血恢复100%时脱离元魂状态
	 * 受到所有的异常状态时间延长50%，造成的伤害提高10%，魂防-50% 回复的气血值暂时没有效果，直到脱离元魂状态
	 * 离开战斗状态20秒后开始自动回复气血值 气血值>0 免疫硬直和浮空效果 战斗状态每10秒一次的自动回复气血
	 **/
	protected int								curBlood;									// 当前气血(变动)

	protected int								mana;										// 灵力
	protected int								pkVal;										// pk
																							// 值
	protected int								battleMode;									// 攻击模式
	// 进场保护,不可攻击
	protected boolean							protection;

	// 生存状态
	private int									livingState;

	/** 战斗形态 :是否处于元魂状态 */
	protected boolean							isSoulState				= false;

	/** 是否处于战斗状态 */
	protected boolean							fightState				= false;
	/** 最后一次战斗时间 */
	protected long								lastFightTm;

	/**
	 * 位置
	 */
	protected Vector3							postion;
	/**
	 * 方向
	 */
	protected Vector3							dir						= Vector3.Zero();

	/**
	 * 目标位置
	 */
	protected Vector3							targetPostion			= Vector3.Invalid;

	/**
	 * 所在的场景
	 */
	protected Field								field;

	/** 怪物出生节点 */
	protected SpwanNode							node;
	/** 主动技能 */
	protected Map<Integer, Skill>				drivingSkills;

	/** 被动技能带来的常驻buffer <execWay,<bufferId,buffer>> */
	protected Map<Integer, List<Buffer>>		permanentBuffer;

	/** 存活的临时buffer <execWay,<bufferId,buffer>> */
	protected Map<Integer, List<Buffer>>		workBuffers;

	/** 根据buffer类型存放工作buffer映射 */
	protected Map<Integer, List<Buffer>>		typeBuffers;

	/** 所有的buffer */
	protected Map<Long, Buffer>					allBuffers;

	/** 状态管理器 */
	protected Map<LivingState, AtomicInteger>	livingStatus;

	/**
	 * 运镖
	 */
	private TruckerStateHelper					truckHelper;

	public TruckerStateHelper getTruckHelper() {
		return truckHelper;
	}

	public HashMap<String, CoolDown> getCooldowns() {
		return cooldowns;
	}

	/** buffer回收站--待定，看是否需要 */
	// -------------------------------------------
	/** (气血&&元魂)自动恢复时间 */
	protected long						restoreTime	= System.currentTimeMillis();

	/** 死亡时间 */
	protected long						dieTime;

	protected boolean					canAction;

	private static EnumAttr[]			propertys;

	/** 队伍ID */
	private int							teamId;

	/**
	 * 魂幡值
	 */
	private long						soulExp;

	/** cd对象 */
	protected HashMap<String, CoolDown>	cooldowns	= new HashMap<String, CoolDown>();

	protected Object					dieLock		= new Object();

	static {
		propertys = new EnumAttr[] { EnumAttr.CUR_SOUL, EnumAttr.MAX_SOUL, EnumAttr.SOUL, EnumAttr.BLOOD, EnumAttr.MAX_BLOOD, EnumAttr.CUR_BLOOD, EnumAttr.ATTACK, EnumAttr.DEFENCE,
				EnumAttr.SOUL_ATTACK, EnumAttr.SOUL_DEFENCE, EnumAttr.ACCURATE, EnumAttr.DODGE, EnumAttr.CRIT, EnumAttr.CRIT_DEFENCE, EnumAttr.CRIT_ADDTION, EnumAttr.CRIT_CUT, EnumAttr.ATTACK_ADDTION,
				EnumAttr.ATTACK_CUT, EnumAttr.SOUL_ATTACK_ADDTION, EnumAttr.SOUL_ATTACK_CUT, EnumAttr.REGAIN_SOUL, EnumAttr.REGAIN_BLOOD, EnumAttr.METAL, EnumAttr.WOOD, EnumAttr.WATER, EnumAttr.FIRE,
				EnumAttr.EARTH, EnumAttr.METAL_DEFENCE, EnumAttr.WOOD_DEFENCE, EnumAttr.WATER_DEFENCE, EnumAttr.FIRE_DEFENCE, EnumAttr.EARTH_DEFENCE, EnumAttr.SPEED, EnumAttr.TEAM_ID, EnumAttr.PK_VAL,
				EnumAttr.BATTLE_MODE, EnumAttr.MANA };
	}

	public Vector3 getPostion() {
		return postion;
	}

	public void setPostion(Vector3 postion) {
		this.postion = postion;
	}

	public Vector3 getDir() {
		return dir;
	}

	public void setDir(Vector3 dir) {
		this.dir = dir;
	}

	public Vector3 getTargetPostion() {
		return targetPostion;
	}

	public void setTargetPostion(Vector3 targetPostion) {
		this.targetPostion = targetPostion;
	}

	public int getGridIndex() {
		return gridIndex;
	}

	public void setGridIndex(int gridIndex) {
		this.gridIndex = gridIndex;
	}

	public Field getField() {
		return field;
	}

	public Living(long armyId, long id) {
		super(ThreadManager.actionExecutor);
		this.armyId = armyId;
		this.id = id;
		this.drivingSkills = new HashMap<>();
		// this.mapSkill = new HashMap<>();
		this.permanentBuffer = new ConcurrentHashMap<>();
		this.workBuffers = new ConcurrentHashMap<>();
		this.typeBuffers = new ConcurrentHashMap<>();
		setLivingState(ALIVE);
		this.postion = Vector3.Invalid;
		this.allBuffers = new ConcurrentHashMap<>();
		truckHelper = new TruckerStateHelper(this);
		initState();
	}

	public Living(long id) {
		super(ThreadManager.actionExecutor);
		this.id = id;
		this.drivingSkills = new HashMap<>();
		// this.mapSkill = new HashMap<>();
		this.permanentBuffer = new ConcurrentHashMap<>();
		this.workBuffers = new ConcurrentHashMap<>();
		this.typeBuffers = new ConcurrentHashMap<>();
		this.postion = Vector3.Invalid;
		setLivingState(ALIVE);
		this.allBuffers = new ConcurrentHashMap<>();
		truckHelper = new TruckerStateHelper(this);
		initState();

	}

	/** 初始化状态 */
	private void initState() {
		livingStatus = new HashMap<>();
		for (LivingState state : LivingState.values()) {
			AtomicInteger value = new AtomicInteger(0);
			livingStatus.put(state, value);
		}
	}

	/** 判断是否死亡 */
	public synchronized boolean isDie() {
		if (otherDamageCalWay()) {
			return curBlood <= 0 || livingState == DIE || livingState == DISTORY;
		}
		return curSoul <= 0 || livingState == DIE || livingState == DISTORY;
	}

	/** 死亡规则：玩家元婴期以前，不计算魂血，魂伤扣气血 */
	public boolean otherDamageCalWay() {
		if (type != RoleType.player && type != RoleType.robot) {
			return false;
		}
		if (simpleInfo == null) {
			return false;
		}
		return simpleInfo.getStateLv() < 3;
	}

	/**
	 * 技能重新CD
	 */
	public void coolDown(Skill skill) {
		skill.setLastUsed(System.currentTimeMillis());
	}

	/**
	 * 进入地图/场景
	 * 
	 * @param f
	 */
	public void enterField(Field f) {
		field = f;
	}

	/**
	 * 离开
	 * 
	 * @param f
	 */
	public void leaveField() {
		field = null;
	}

	// living当前是否处于可行动状态
	public boolean canAction() {
		return true;
	}

	// 是否足够支付技能消耗
	public boolean paySkillExec() {
		return true;
	}

	// 获取技能
	public Skill getSkill(int skillId) {
		return drivingSkills.get(skillId);
	}

	/**
	 * 添加一个常驻buffer
	 */
	public void addPermanentBuffer(Buffer buff) {
		List<Buffer> exeWayBuffers = permanentBuffer.get(buff.getExeWay());
		if (exeWayBuffers == null) {
			exeWayBuffers = new ArrayList<>();
			permanentBuffer.put(buff.getExeWay(), exeWayBuffers);
		}

		List<Buffer> typeBufList = typeBuffers.get(buff.getType());
		if (typeBuffers == null) {
			typeBufList = new ArrayList<>();
			typeBuffers.put(buff.getType(), typeBufList);
		}

		exeWayBuffers.add(buff);
	}

	/**
	 * 更新buffer
	 * 
	 * @param buffer
	 */
	public void upBuffer(Buffer buffer) {
		BufferMsg.Builder bmsg = BufferMsg.newBuilder();
		bmsg.setOption(2);// 更新
		buffer.writeProto(bmsg);
		sendBufferChange(bmsg.build());
	}

	public void addBuffer(Buffer buff) {
		// 死亡不再添加buff
		if (isDie()) {
			return;
		}

		// 有免疫buff时判断是否被免疫
		if (buff.getBufferInfo() != null) {
			List<Buffer> immuneBufferList = getTypeBuffers(BufferType.BUFF_OR_DEBUFF_IMMUNE);
			for (Buffer curBuff : immuneBufferList) {
				if (curBuff.getBufferInfo() != null) {
					if (curBuff.getBufferInfo().getIsHelpful() != buff.getBufferInfo().getIsHelpful()) {
						return;
					}
				}
			}
		}

		for (Buffer older : imageBuffs()) {
			if (older.getOverlayType() != 0 && buff.getOverlayWay() != 0 && buff.getOverlayType() == older.getOverlayType() && buff.getOverlayWay() == older.getOverlayWay()) {
				overlay(older, buff);
				return;
			}
		}
		simpleAdd(buff);
	}

	/**
	 * 添加一个可执行buffer
	 */
	public void simpleAdd(Buffer buff) {

		List<Buffer> exeWayBuffers = workBuffers.get(buff.getExeWay());

		if (exeWayBuffers == null) {
			exeWayBuffers = new ArrayList<>();
			workBuffers.put(buff.getExeWay(), exeWayBuffers);
		}

		List<Buffer> typeBufList = typeBuffers.get(buff.getType());
		if (typeBufList == null) {
			typeBufList = new ArrayList<>();
			typeBuffers.put(buff.getType(), typeBufList);
		}

		exeWayBuffers.add(buff);
		typeBufList.add(buff);
		allBuffers.put(buff.getBufferId(), buff);
		BufferMsg.Builder bmsg = BufferMsg.newBuilder();
		bmsg.setOption(1);// 添加
		buff.writeProto(bmsg);
		sendBufferChange(bmsg.build());

		if (buff.getStatus() != 0) {
			addLivingState(buff.getStatus());
		}

		buff.afterAdd();

		if (buff.getBufferInfo().getExeWay() == ExecWayType.ADD && buff.checkValid()) {
			buff.execute(null, Damage.DEFAULT, Damage.DEFAULT, ExecWayType.ADD);
		}
	}

	/**
	 * 移除一个buffer
	 */
	public boolean removeBuffer(Buffer buff) {
		boolean result = false;
		List<Buffer> exeWayBuffers = workBuffers.get(buff.getExeWay());
		allBuffers.remove(buff.getBufferId());
		if (exeWayBuffers != null) {
			result = exeWayBuffers.remove(buff);
			BufferMsg.Builder bmsg = BufferMsg.newBuilder();
			bmsg.setBufferId(buff.getBufferId());
			bmsg.setOption(4);// 4 删除
			bmsg.setSourceId(buff.getSource().getId());
			bmsg.setTargetId(buff.getTarget().getId());
			sendBufferChange(bmsg.build());
			buff.stop();
		}

		List<Buffer> typeArr = typeBuffers.get(buff.getType());
		if (typeArr != null) {
			typeArr.remove(buff);
		}

		if (buff.getStatus() != 0) {
			removeLivingState(buff.getStatus());
		}
		if (buff.getType() == BufferType.ATTR_BODY) {
			if (buff.getBufferInfo().getValueType() != 0) {
				refreshProperties(buff.getBufferInfo().getValueType());
			}
			if (buff.getBufferInfo().getValueType1() != 0) {
				refreshProperties(buff.getBufferInfo().getValueType1());
			}
		}
		return result;
	}

	protected void sendBufferChange(BufferMsg msg) {
		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		if (this.armyId > 0) {
			nears.add(this.armyId);
		}
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_G_BUFFER_OPTION, msg);
	}

	/**
	 * 获取某种执行的所有buffer
	 */
	public List<Buffer> getExeWayBuffers(int exeWay) {
		List<Buffer> toal = new ArrayList<>();

		List<Buffer> pbuff = permanentBuffer.get(exeWay);
		if (pbuff != null) {
			toal.addAll(pbuff);
		}

		List<Buffer> wbuff = workBuffers.get(exeWay);
		if (wbuff != null) {
			toal.addAll(wbuff);
		}
		return toal;
	}

	/** 获取某种类型的buff */
	public List<Buffer> getTypeBuffers(int type) {
		List<Buffer> toal = new ArrayList<>();

		List<Buffer> typeBuff = typeBuffers.get(type);
		if (typeBuff != null) {
			toal.addAll(typeBuff);
		}
		return toal;
	}

	/**
	 * 
	 * @param pmsg
	 */
	public void readProperty(PropertyListMsg pmsg) {
		List<PropertyMsg> properties = new ArrayList<>(pmsg.getPropertysList());
		readProperty(properties);
	}

	public void updataProperty(List<PropertyMsg> properties) {
		readProperty(properties);
		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_G_BATTLEPLAYERINFO, getBattlePlayerInfoMsg().build());
	}

	/**
	 * 单个人物属性更新方法
	 * 
	 * @param type
	 * @param value
	 */
	public void updateProperty(EnumAttr type, long value) {
		// 修改玩家属性
		List<PropertyMsg> properties = new ArrayList<>();
		PropertyMsg.Builder p = PropertyMsg.newBuilder();

		p.setBasePoint(value);
		p.setTotalPoint(value);
		p.setType(type.getValue());
		properties.add(p.build());
		this.readProperty(properties);

		PlayerAttUpdateMsg.Builder msg = PlayerAttUpdateMsg.newBuilder();
		msg.setPlayerId(this.getId());
		msg.addAtt(p);

		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_RESP_PLAYER_ATT_UPDATE, msg.build());

	}

	/**
	 * 更新他人人物属性
	 * 
	 * @param type
	 * @param value
	 */
	public void updateProperty(Living living, List<PropertyMsg> properties) {
		// 修改玩家属性
		living.readProperty(properties);
		PlayerAttUpdateMsg.Builder msg = PlayerAttUpdateMsg.newBuilder();
		msg.setPlayerId(living.getArmyId());
		msg.addAllAtt(properties);

		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(living.getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_RESP_PLAYER_ATT_UPDATE, msg.build());
	}

	/**
	 * 更新属性只广播自己
	 * 
	 * @param type
	 * @param value
	 */
	public void upProperty(Living living, List<PropertyMsg> properties) {
		// 修改玩家属性
		living.readProperty(properties);
		PlayerAttUpdateMsg.Builder msg = PlayerAttUpdateMsg.newBuilder();
		msg.setPlayerId(living.getArmyId());
		msg.addAllAtt(properties);
		ArmyProxy army = WorldMgr.getArmy(armyId);
		if (army != null) {
			PBMessage message = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, msg.build());
			army.sendPbMessage(message);
		}
	}

	public void readProperty(List<PropertyMsg> properties) {
		List<PropertyMsg> temp = new ArrayList<>(properties);
		if (temp != null && temp.size() > 0) {
			for (PropertyMsg p : temp) {
				// 设置最大气血
				if (p.getType() == EnumAttr.BLOOD.getValue()) {
					long add = p.getTotalPoint() - getProperty(EnumAttr.BLOOD.getValue());
					if (add != 0) {
						long maxBlood = getProperty(EnumAttr.MAX_BLOOD.getValue());
						long newMaxB = maxBlood + add;
						setProperty(EnumAttr.MAX_BLOOD, newMaxB);
						PropertyMsg.Builder mbMsg = PropertyMsg.newBuilder();
						mbMsg.setType(EnumAttr.MAX_BLOOD.getValue());
						mbMsg.setTotalPoint(newMaxB);
						properties.add(mbMsg.build());
						if (maxBlood != 0) {
							addCurBlood((int) add, DamageEffecterType.BLOOD, 0, 0);
						}
					}
				}
				// 设置最大元魂
				if (p.getType() == EnumAttr.SOUL.getValue()) {
					long add = p.getTotalPoint() - getProperty(EnumAttr.SOUL.getValue());
					if (add != 0) {
						long maxSoul = getProperty(EnumAttr.MAX_SOUL.getValue());
						long newMaxS = maxSoul + add;
						setProperty(EnumAttr.MAX_SOUL, newMaxS);
						PropertyMsg.Builder msMsg = PropertyMsg.newBuilder();
						msMsg.setType(EnumAttr.MAX_SOUL.getValue());
						msMsg.setTotalPoint(newMaxS);
						properties.add(msMsg.build());
						if (maxSoul != 0) {
							addCurSoul((int) add, DamageEffecterType.SOUL, 0, 0);
						}
					}
				}
			}
			for (PropertyMsg p : properties) {
				EnumAttr attr = EnumAttr.getEnumAttrByValue(p.getType());
				if (attr == null) {
					continue;
				}
				this.setProperty(attr, p.getTotalPoint());
			}
		}
	}

	/** 计算伤害 */
	public int takeDamage(Damage damage) {
		DamageEffecter effecter = DamageEffecterFactory.effecterBuilder(damage);
		// if (damage.getSource().getType() == RoleType.monster &&
		// damage.getTarget().getType() == RoleType.monster) {
		// Log.error("damageV1 :" + damage.getDamageValue(), new Exception());
		// }
		effecter.exec(this, damage);
		// if (damage.getSource().getType() == RoleType.monster &&
		// damage.getTarget().getType() == RoleType.monster) {
		// Log.error("damageV2 :" + damage.getDamageValue(), new Exception());
		// }
		if (isDie()) {
			onDie(damage.getSource());
		}

		addHatred(damage);
		return damage.getDamageValue();
	}

	/**
	 * 添加伤害
	 * 
	 * @param damage
	 */
	protected void addHatred(Damage damage) {

	}

	/** 执行死亡时操作 */
	public boolean onDie(Living killer) {
		synchronized (dieLock) {
			if (this.livingState == DIE) {
				return false;
			}
			this.livingState = DIE;
		}
		clearWorkBuffer();
		dieTime = System.currentTimeMillis();
		return true;
	}

	public void addSkill(Skill skill) {
		this.drivingSkills.put(skill.getSkillId(), skill);
	}

	/**
	 * 获取场景对象的外观详细信息
	 * 
	 * @return
	 */
	public BattleLivingInfoMsg.Builder getBattlePlayerInfoMsg() {
		BattleLivingInfoMsg.Builder cachBattleInfoPacket = BattleLivingInfoMsg.newBuilder();
		cachBattleInfoPacket.setLivingId(getId());
		cachBattleInfoPacket.setPlayerId(getArmyId());
		cachBattleInfoPacket.setType(getType());
		if (simpleInfo != null) {
			cachBattleInfoPacket.setNickName(simpleInfo.getNickName());
			cachBattleInfoPacket.setLevel(simpleInfo.getLevel());
			cachBattleInfoPacket.setVipLevel(simpleInfo.getVipLevel());
			cachBattleInfoPacket.setFight(simpleInfo.getFight());
			cachBattleInfoPacket.setSkinId(simpleInfo.getSkinId());
			cachBattleInfoPacket.setFashionId(simpleInfo.getFashionId());
			cachBattleInfoPacket.setWeaponId(simpleInfo.getWeaponId());
			cachBattleInfoPacket.setMountId(simpleInfo.getMountId());
			cachBattleInfoPacket.setMagicWeaponId(simpleInfo.getMagicWeaponId());
			cachBattleInfoPacket.setWingId(simpleInfo.getWingId());
			cachBattleInfoPacket.setWeaponAwaken(simpleInfo.getWeaponAwaken());
			cachBattleInfoPacket.setStateLv(simpleInfo.getStateLv());
		} else {
			cachBattleInfoPacket.setSkinId(getSkin());
		}

		if (getPostion() != null) {
			cachBattleInfoPacket.setPostion(Vector3BuilderHelper.build(getPostion()));
		} else {
			cachBattleInfoPacket.setPostion(Vector3BuilderHelper.build(Vector3.Invalid));
		}
		cachBattleInfoPacket.setTarget(Vector3BuilderHelper.build(getTargetPostion()));
		cachBattleInfoPacket.setLiveState(livingState);
		cachBattleInfoPacket.setSoulState(isSoulState ? 1 : 0);
		// }

		List<Buffer> toalBuffer = new ArrayList<>();
		for (List<Buffer> pBuffers : permanentBuffer.values()) {
			toalBuffer.addAll(pBuffers);
		}
		for (List<Buffer> wBuffers : workBuffers.values()) {
			toalBuffer.addAll(wBuffers);
		}
		for (Buffer buffer : toalBuffer) {
			BufferMsg.Builder bufferMsg = BufferMsg.newBuilder();
			buffer.writeProto(bufferMsg);
			cachBattleInfoPacket.addBufferList(bufferMsg);
		}

		for (Skill skill : drivingSkills.values()) {
			cachBattleInfoPacket.addSkills(skill.getSkillId());
		}

		for (EnumAttr attr : propertys) {
			PropertyMsg.Builder pmsg = PropertyMsg.newBuilder();
			pmsg.setTotalPoint(this.getProperty(attr.getValue()));
			pmsg.setType(attr.getValue());
			cachBattleInfoPacket.addPropertis(pmsg);
		}
		return cachBattleInfoPacket;
	}

	/**
	 * 获取场景对象的快照信息
	 * 
	 * @return
	 */
	public PlayerAttSnapMsg.Builder getAttSnapMsg() {
		PlayerAttSnapMsg.Builder cacheAttSnapPacker = PlayerAttSnapMsg.newBuilder();
		cacheAttSnapPacker.setPlayerId(id);
		cacheAttSnapPacker.setType(getType());
		cacheAttSnapPacker.setSkinId(getSkin());
		cacheAttSnapPacker.setPostion(Vector3BuilderHelper.build(getPostion()));
		cacheAttSnapPacker.setTarget(Vector3BuilderHelper.build(getTargetPostion()));
		cacheAttSnapPacker.setOwnerId(getArmyId());
		if (this.getSimpleInfo() != null) {
			cacheAttSnapPacker.setGuildId(this.getSimpleInfo().getGuildId());
			if (this.getSimpleInfo().getGuildName() != null) {
				cacheAttSnapPacker.setGuildName(this.getSimpleInfo().getGuildName());
			}
			cacheAttSnapPacker.setGuildJob(this.getSimpleInfo().getGuildJob());
		}
		if (node != null) {
			cacheAttSnapPacker.setBornNodeId(node.getSpwanId());
		}
		return cacheAttSnapPacker;
	}

	/**
	 * 获取附近所有对象集合
	 * 
	 * @return
	 */
	public Set<Long> getNears(Selector selector) {
		Set<Long> ret = new HashSet<Long>();
		if (postion == null) {
			return ret;
		}
		if (field == null) {
			return ret;
		}
		Field temField = field;
		GridCoord coord = null;
		try {
			coord = temField.getGrid().getGridCrood(postion.getX(), postion.getZ());
		} catch (Exception e) {
			Log.error("position.x" + postion.getX() + " position.z" + postion.getZ(), e);
			return ret;
		}
		for (int i = 0; i < Grid.GRID9.length; i++) {
			int[] offset = Grid.GRID9[i];
			int tmpCoordX = coord.X + offset[0];
			int tmpCoordZ = coord.Z + offset[1];
			// if (tmpCoordX < 0 || tmpCoordZ < 0 || tmpCoordX >
			// field.getGrid().GridX || tmpCoordZ > field.getGrid().GridZ)
			// continue;
			GridItem gi = temField.getGrid().getGridItem(tmpCoordX, tmpCoordZ);
			if (gi == null)
				continue;
			Set<Entry<Integer, List<Long>>> set = gi.getLivings().entrySet();
			Iterator<Entry<Integer, List<Long>>> it = set.iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, List<Long>> entry = (Map.Entry<Integer, List<Long>>) it.next();
				if (selector != null) {
					// 选择器中排除类型
					if (!selector.selectorType(entry.getKey()))
						continue;
				}
				List<Long> entryList = entry.getValue();
				List<Long> tmp = new ArrayList<Long>();
				tmp.addAll(entryList);
				for (int j = 0; j < tmp.size(); j++) {
					if (tmp.get(j) == null)
						continue;
					long other = tmp.get(j);
					Living nearLiving = temField.getLiving(other);
					if (nearLiving == null) {
						continue;
					}
					if (selector != null) {
						// 选择器中排除id
						if (!selector.selectorid(other)) {
							continue;
						}
						if (!selector.canSee(nearLiving)) {
							continue;
						}

					}
					if (other == id) {
						continue;
					}

					if (selector != null) {
						if (!selector.selectorProtection(nearLiving.protection)) {
							continue;
						}
					}
					ret.add(other);
				}
			}
		}
		return ret;
	}

	public void destory() {
		this.livingState = DISTORY;
	}

	public void setProperty(EnumAttr attr, long value) {
		/* 不允许出现负属性 */
		if (value < 0) {
			value = 0;
		}
		if (attr.getValue() <= 29) {
			setInitData(attr, (int) value);
		}
		switch (attr) {
			case CUR_SOUL:
				if (value > this.getMaxSoul()) {
					value = this.getMaxSoul();
				}
				this.setCurSoul((int) value);
				break;
			case CUR_BLOOD:
				if (value > this.getMaxBlood()) {
					value = this.getMaxBlood();
				}
				this.setCurBlood((int) value);
				break;
			case MANA:
				this.setMana((int) value);
				break;
			case TEAM_ID:
				this.setTeamId((int) value);
				break;
			case PK_VAL:
				this.setPkVal((int) value);
				break;
			case BATTLE_MODE:
				this.setBattleMode((int) value);
				break;
			case Weapon:
				this.simpleInfo.setWeaponId((int) value);
				break;
			case SOUL_EXP:
				this.setSoulExp(value);
				break;
			default:
				break;
		}
	}

	public int getProperty(EnumAttr type) {
		return getProperty(type.getValue());
	}

	public int getProperty(int type) {
		EnumAttr attr = EnumAttr.getEnumAttrByValue(type);
		switch (attr) {
			case CUR_SOUL:
				return this.getCurSoul();
			case MAX_SOUL:
				return this.getMaxSoul();
			case SOUL:
				return this.getInitSoul();
			case BLOOD:
				return this.getInitBlood();
			case MAX_BLOOD:
				return this.getMaxBlood();
			case CUR_BLOOD:
				return this.getCurBlood();
			case MANA:
				return this.getMana();
			case ATTACK:
				return this.getAttack();
			case DEFENCE:
				return this.getDefence();
			case SOUL_ATTACK:
				return this.getSoulAttack();
			case SOUL_DEFENCE:
				return this.getSoulDefence();
			case ACCURATE:
				return this.getAccurate();
			case DODGE:
				return this.getDodge();
			case CRIT:
				return this.getCrit();
			case CRIT_DEFENCE:
				return this.getCritDefence();
			case CRIT_ADDTION:
				return this.getCritAddtion();
			case CRIT_CUT:
				return this.getCritCut();
			case ATTACK_ADDTION:
				return this.getAttackAddtion();
			case ATTACK_CUT:
				return this.getAttackCut();
			case SOUL_ATTACK_ADDTION:
				return this.getSoulAttackAddtion();
			case SOUL_ATTACK_CUT:
				return this.getSoulAttackCut();
			case REGAIN_SOUL:
				return this.getRegainSoul();
			case REGAIN_BLOOD:
				return this.getRegainBlood();
			case METAL:
				return this.getMetal();
			case WOOD:
				return this.getWood();
			case WATER:
				return this.getWater();
			case FIRE:
				return this.getFire();
			case EARTH:
				return this.getEarth();
			case METAL_DEFENCE:
				return this.getMetalDefence();
			case WOOD_DEFENCE:
				return this.getWoodDefence();
			case WATER_DEFENCE:
				return this.getWaterDefence();
			case FIRE_DEFENCE:
				return this.getFireDefence();
			case EARTH_DEFENCE:
				return this.getEarthDefence();
			case SPEED:
				return this.getSpeed();
			case TEAM_ID:
				return this.getTeamId();
			case PK_VAL:
				return this.getPkVal();
			case BATTLE_MODE:
				return this.getBattleMode();
			default:
				return 0;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSkin() {
		return skin;
	}

	public void setSkin(int skin) {
		this.skin = skin;
	}

	public SimplePlayerInfo getSimpleInfo() {
		return simpleInfo;
	}

	public void setSimpleInfo(SimplePlayerInfo simpleInfo) {
		this.skin = simpleInfo.getSkinId();
		this.simpleInfo = simpleInfo;
	}

	/* 切换战斗状态 */
	public synchronized void changeSoulState(boolean state) {
		isSoulState = state;
		sendChangeStatuMsg(FIGHT_STATU, state ? 1 : 0);
	}

	/* 闪名切换 */
	public void changeFlickerName(boolean state) {
		sendChangeStatuMsg(BATTLE_MODE, state ? 1 : 0);
	}

	public void setSoulState(boolean isSoulState) {
		this.isSoulState = isSoulState;
	}

	public boolean isSoulState() {
		return isSoulState;
	}

	public long getDieTime() {
		return dieTime;
	}

	public int lessBlood() {
		return getMaxBlood() - getCurBlood();
	}

	public int lessSoul() {
		return getMaxSoul() - getCurSoul();
	}

	public int getPkVal() {
		return pkVal;
	}

	public void setPkVal(int pkVal) {
		this.pkVal = pkVal;
	}

	public int getBattleMode() {
		return battleMode;
	}

	public void setBattleMode(int battleMode) {
		this.battleMode = battleMode;
	}

	public void exeWorkBuffer() {
		List<Buffer> wbuff = imageBuffs();
		if (wbuff != null && wbuff.size() != 0) {
			List<Damage> damages = new ArrayList<>();
			for (Buffer buff : wbuff) {
				Damage damage1 = new Damage(this, buff.getSource());
				Damage damage2 = new Damage(this, buff.getSource());
				if (buff.checkValid() && buff.execute(null, damage1, damage2, ExecWayType.TIME_LINE)) {
					damages.add(damage1);
					damages.add(damage2);
					takeDamage(damage1);
					takeDamage(damage2);
				}
			}
			if (damages.size() > 0) {
				DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
				damagesPb.setAttackId(-1);
				for (Damage damage : damages) {
					DamageMsg.Builder dmsg = DamageMsg.newBuilder();
					damage.writeProto(dmsg);
					damagesPb.addDamages(dmsg);
				}
				Set<Long> players = getNears(new PlayerSelectorHelper(this));
				// 添加自己
				players.add(getArmyId());

				BroadcastUtil.sendBroadcastPacket(players, Protocol.U_G_DAMAGE, damagesPb.build());
			}
		}
	}

	public void execWayBuffer(AttackOrder order, int execWay) {
		List<Buffer> buffers = getExeWayBuffers(execWay);
		if (buffers != null && buffers.size() != 0) {
			List<Damage> damages = new ArrayList<>();
			for (Buffer buff : buffers) {
				Damage damage1 = new Damage(this, buff.getSource());
				Damage damage2 = new Damage(this, buff.getSource());
				if (buff.checkValid() && buff.execute(order, damage1, damage2, execWay)) {
					damages.add(damage1);
					damages.add(damage2);
					takeDamage(damage1);
					takeDamage(damage2);
				}
			}
			if (damages.size() > 0) {
				DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
				damagesPb.setAttackId(-1);
				for (Damage damage : damages) {
					DamageMsg.Builder dmsg = DamageMsg.newBuilder();
					damage.writeProto(dmsg);
					damagesPb.addDamages(dmsg);
				}
				Set<Long> players = getNears(new PlayerSelectorHelper(this));
				// 添加自己
				players.add(getArmyId());

				BroadcastUtil.sendBroadcastPacket(players, Protocol.U_G_DAMAGE, damagesPb.build());
			}
		}
	}

	public void execBuffer(Buffer buff, int execWay) {
		List<Damage> damages = new ArrayList<>();
		Damage damage1 = new Damage(this, buff.getSource());
		Damage damage2 = new Damage(this, buff.getSource());
		boolean isValid = false;
		if (execWay == ExecWayType.REMOVE) {
			isValid = true;
		} else {
			isValid = buff.checkValid();
		}
		if (isValid && buff.execute(null, damage1, damage2, execWay)) {
			damages.add(damage1);
			damages.add(damage2);
			takeDamage(damage1);
			takeDamage(damage2);
		}

		if (damages.size() > 0) {
			DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
			damagesPb.setAttackId(-1);
			for (Damage d : damages) {
				DamageMsg.Builder dmsg = DamageMsg.newBuilder();
				d.writeProto(dmsg);
				damagesPb.addDamages(dmsg);
			}
			Set<Long> players = getNears(new PlayerSelectorHelper(this));
			// 添加自己
			players.add(getArmyId());
			BroadcastUtil.sendBroadcastPacket(players, Protocol.U_G_DAMAGE, damagesPb.build());
		}
	}

	/* 根据属性类型添加修改的变化，刷新对应属性 */
	public void refreshProperties(int type) {
		// 获取所有影响该属性的buffer
		List<Buffer> attrBuffers = getTypeBuffers(BufferType.ATTR_BODY);
		List<Buffer> truckBuffers = getTypeBuffers(BufferType.TRUCK_ATTR_BODY);
		List<AttributesBuffer> invock = new ArrayList<>();
		for (Buffer buffer : attrBuffers) {
			if (buffer.getBufferInfo().getValueType() == type) {
				invock.add((AttributesBuffer) buffer);
			}
		}
		for (Buffer buffer : truckBuffers) {
			if (buffer.getBufferInfo().getValueType() == type) {
				invock.add((AttributesBuffer) buffer);
			}
		}
		EnumAttr etype = EnumAttr.getEnumAttrByValue(type);
		if (etype == null) {
			Log.error("EnumAttr etype is null, type : " + type);
			return;
		}
		// 获取初始属性
		int lastValue = getInitValue(etype);
		// 计算变更值
		int addValue = 0;
		for (AttributesBuffer buffer : invock) {
			addValue = buffer.getAddValue(type, lastValue);
		}

		// 当玩家身上存在魂攻物攻转换buffer时，在计算完毕所有属性加成，再做转换。
		if (type == EnumAttr.ATTACK.getValue() || type == EnumAttr.SOUL_ATTACK.getValue()) {
			List<Buffer> convertBuffers = getTypeBuffers(BufferType.ATTACK_COVENT_SOULATTACK);
			for (Buffer as : convertBuffers) {
				AttackConvertSoulAttackBuffer A2S = (AttackConvertSoulAttackBuffer) as;
				addValue += A2S.getResult(type);
			}

			List<Buffer> convert2Buffers = getTypeBuffers(BufferType.SOULATTACK_COVENT_ATTACK);
			for (Buffer sa : convert2Buffers) {
				SoulAttackConvertAttackBuffer S2A = (SoulAttackConvertAttackBuffer) sa;
				addValue += S2A.getResult(type);
			}
		}
		setBufferProperty(etype, addValue);
		PlayerAttUpdateMsg.Builder notifyMsg = PlayerAttUpdateMsg.newBuilder();
		PropertyMsg.Builder speedMsg = PropertyMsg.newBuilder();
		speedMsg.setType(etype.getValue());
		speedMsg.setTotalPoint(getToalData(etype));
		notifyMsg.addAtt(speedMsg);
		notifyMsg.setPlayerId(getId());
		// 通知附近玩家
		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		if (this.armyId > 0) {
			nears.add(this.armyId);
		}
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_RESP_PLAYER_ATT_UPDATE, notifyMsg.build());
	}

	public void clearWorkBuffer() {
		List<Buffer> allbuffers = imageBuffs();
		for (Buffer buff : allbuffers) {
			buff.setState(BufferState.DEAD_REMOVE);
			buff.dispose();
		}
		workBuffers.clear();
		typeBuffers.clear();
		allBuffers.clear();
		livingStatus.clear();
		initState();
	}

	public boolean isFighting() {
		return fightState;
	}

	public void fight() {
		if (!fightState) {
			fightState = true;
		}
		lastFightTm = System.currentTimeMillis();
	}

	/** 脱离战斗 */
	public void leaveFight() {
		if (this.getType() == RoleType.player) {
			changeFlickerName(false);
			((Player) this).setFlashName(false);
		}

		if (!fightState) {
			return;
		}
		fightState = false;
		if (isSoulState() && getCurBlood() > 0) {
			changeSoulState(false);
			List<Damage> damages = new ArrayList<>();
			Damage curBlood = new Damage(this, this);
			curBlood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
			curBlood.setDamageValue(0 - getInitBlood() * 5 / 100);
			curBlood.setCalcType(DamageEffecterType.BLOOD);
			damages.add(curBlood);
			takeDamage(curBlood);

			if (damages.size() == 0) {
				return;
			}
			DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
			damagesPb.setAttackId(-1);
			for (Damage d : damages) {
				DamageMsg.Builder dmsg = DamageMsg.newBuilder();
				d.writeProto(dmsg);
				damagesPb.addDamages(dmsg);
			}
			Set<Long> players = getNears(new PlayerSelectorHelper(this));
			// 添加自己
			players.add(getArmyId());
			for (Long armyId : players) {
				ArmyProxy army = WorldMgr.getArmy(armyId);
				if (army != null) {
					PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, damagesPb.build());
					army.sendPbMessage(message);
				}
			}
		}
	}

	/**
	 * 获取颜色级别
	 * 
	 * @param val
	 * @return
	 */
	public int getColour(int val) {
		int minRed = SystemConfigTemplateMgr.getIntValue("pk.colour.minRed");
		int minYellow = SystemConfigTemplateMgr.getIntValue("pk.colour.minYellow");

		if (val >= minRed) {
			return BattleModeCode.red;
		} else if (val >= minYellow) {
			return BattleModeCode.yellow;
		}
		return BattleModeCode.white;
	}

	/**
	 * 通知
	 * 
	 * @param changeMap
	 * @param playerId
	 * @return
	 */
	public void notifyCenter(Map<Integer, Long> changeMap, long playerId) {
		PlayerAttUpdateMsg.Builder msg = PlayerAttUpdateMsg.newBuilder();
		msg.setPlayerId(playerId);
		for (int type : changeMap.keySet()) {
			PropertyMsg.Builder attMsg = PropertyMsg.newBuilder();
			attMsg.setType(type);
			attMsg.setTotalPoint(changeMap.get(type));
			msg.addAtt(attMsg);
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_PLAYER_UPDATA_PRO, msg);
		GatewayLinkedSet.send2Server(pkg);
	}

	public long getLastFightTM() {
		return lastFightTm;
	}

	public boolean canSee(long id) {
		return true;
	}

	public String toString() {
		return "LivingId :" + this.getId() + "  armyId:" + armyId + " type:" + this.type + this.simpleInfo;
	}

	public void sendChangeStatuMsg(int stateType, int stateValue) {
		LivingStateChangeMsg.Builder changeState = LivingStateChangeMsg.newBuilder();
		changeState.setLivingId(id);
		changeState.setStateType(stateType);
		changeState.setStateValue(stateValue);

		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(armyId);
		for (Long armyId : nears) {
			ArmyProxy army = WorldMgr.getArmy(armyId);
			PBMessage message = MessageUtil.buildMessage(Protocol.U_LIVING_STATE_CHANGE, changeState.build());
			if (army != null) {
				army.sendPbMessage(message);
			}
		}
	}

	public List<Buffer> imageBuffs() {
		List<Buffer> wbuff = new ArrayList<>();
		wbuff.addAll(allBuffers.values());
		return wbuff;
	}

	/**
	 * 获取攻击技能
	 * 
	 * @return
	 */
	public Skill getAttackSkill() {
		Skill skill = null;
		if (this.drivingSkills.isEmpty()) {
			return null;
		}
		List<Map.Entry<Integer, Skill>> skills = new ArrayList<Map.Entry<Integer, Skill>>(this.drivingSkills.entrySet());

		Collections.sort(skills, new Comparator<Map.Entry<Integer, Skill>>() {
			public int compare(Map.Entry<Integer, Skill> o1, Map.Entry<Integer, Skill> o2) {
				return (o2.getValue().getTemplateInfo().getCooldown() - o1.getValue().getTemplateInfo().getCooldown());
			}
		});

		for (int i = 0; i < skills.size(); i++) {
			if (!isCooldowning(CoolDownTypes.SKILL, skills.get(i).getValue().getActionId() + "")) {
				skill = skills.get(i).getValue();
				break;
			}
		}
		return skill;
	}

	/** buffer替换 */
	public void overlay(Buffer older, Buffer newer) {
		if (older.getState() == BufferState.INVALID) {
			removeBuffer(older);
			simpleAdd(newer);
			return;
		}
		if ((older.getOverlayWay() == BufferOverlayType.REPLACE || older.getOverlayWay() == BufferOverlayType.SUPERIMPOSED_EFFECT)
				&& newer.getBufferInfo().getLevel() > older.getBufferInfo().getLevel()) {
			removeBuffer(older);
			simpleAdd(newer);
		}

		if (older.getOverlayWay() == BufferOverlayType.REPLACE_TIME) {
			if (newer.getAliveTime() > older.getAliveTime()) {
				removeBuffer(older);
				simpleAdd(newer);
			}
		}

		if (older.getOverlayWay() == BufferOverlayType.REPLACE_COUNT) {
			older.setState(BufferState.VALID);
			older.setLeftCount(newer.getBufferInfo().getExeCount());
			older.setAliveTime(newer.getAliveTime());
			upBuffer(older);
		}

		if (older.getOverlayWay() == BufferOverlayType.SUPERIMPOSED) {
			older.setState(BufferState.VALID);
			older.setLeftCount(older.getLeftCount() + newer.getBufferInfo().getExeCount());
			older.setAliveTime(newer.getAliveTime());
			upBuffer(older);
		}

		if (older.getOverlayWay() == BufferOverlayType.SUPERIMPOSED_EFFECT) {
			older.setState(BufferState.VALID);
			older.setLeftCount(newer.getBufferInfo().getExeCount());
			older.setAliveTime(newer.getAliveTime());
			older.addPressedNum(1);
			upBuffer(older);
		}
	}

	/** 检查对应状态，false 不可行，true 可行 */
	public boolean checkStatus(LivingState state) {
		AtomicInteger value = livingStatus.get(state);
		if (value != null) {
			return value.get() <= 0;
		}
		return true;
	}

	public void addLivingState(int stateId) {
		LivingStatusTemplateInfo temp = BattleTempMgr.getLSInfo(stateId);
		// 模板不存在
		if (temp == null) {
			return;
		}
		// 免疫控制
		if (temp.getType() == LivingStatusTemplateInfo.CONTROL && !checkStatus(LivingState.BE_CONTROL)) {
			return;
		}
		List<LivingState> affectedStates = temp.getAffected();
		for (LivingState state : affectedStates) {
			AtomicInteger value = livingStatus.get(state);
			if (value == null) {
				Log.error("the state is not exists  " + value);
				continue;
			}
			value.incrementAndGet();
			notityState(state);
		}
	}

	protected void removeLivingState(int stateId) {
		LivingStatusTemplateInfo temp = BattleTempMgr.getLSInfo(stateId);
		// 模板不存在
		if (temp == null) {
			return;
		}
		List<LivingState> affectedStates = temp.getAffected();
		for (LivingState state : affectedStates) {
			AtomicInteger value = livingStatus.get(state);
			if (value != null) {
				value.decrementAndGet();
			}
		}
	}

	/**
	 * 添加冷却
	 *
	 * @param roleId
	 *            玩家Id
	 * @param type
	 *            类型
	 * @param key
	 *            关键字
	 * @param delay
	 *            冷却时间
	 */
	public void addCooldown(CoolDownTypes type, String key, long delay) {
		// 初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}
		if (cooldowns.containsKey(cooldownKey)) {
			CoolDown cooldown = cooldowns.get(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);
		} else {
			// 初始化冷却信息
			CoolDown cooldown = new CoolDown();
			cooldown.setType(type.getValue());
			cooldown.setKey(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);

			// 添加冷却
			cooldowns.put(cooldownKey, cooldown);
		}
	}

	/**
	 * 是否在冷却中
	 *
	 * @param monsterId
	 *            玩家Id
	 * @param type
	 *            冷却类型
	 * @param key
	 *            关键字
	 * @return
	 */
	public boolean isCooldowning(CoolDownTypes type, String key) {
		// 初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}

		// 查看冷却
		if (cooldowns.containsKey(cooldownKey)) {
			CoolDown cooldown = cooldowns.get(cooldownKey);
			if (System.currentTimeMillis() > cooldown.getStart() + cooldown.getDelay()) {
				return false;
			} else {
				return true;
			}
		}

		return false;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	/** 自杀 */
	public void suicide() {
		List<Damage> damages = new ArrayList<>();

		Damage soul = new Damage(this, this);
		soul.setDamageType(EnumAttr.CUR_SOUL.getValue());
		soul.setDamageValue(Integer.MAX_VALUE);
		soul.setSource(this);
		damages.add(soul);
		takeDamage(soul);

		Damage blood = new Damage(this, this);
		blood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
		blood.setDamageValue(Integer.MAX_VALUE);
		blood.setSource(this);
		damages.add(blood);
		takeDamage(blood);

		DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
		damagesPb.setAttackId(-1);
		for (Damage d : damages) {
			DamageMsg.Builder dmsg = DamageMsg.newBuilder();
			d.writeProto(dmsg);
			damagesPb.addDamages(dmsg);
		}
		Set<Long> players = getNears(new PlayerSelectorHelper(this));
		// 添加自己
		players.add(getArmyId());
		for (Long armyId : players) {
			ArmyProxy army = WorldMgr.getArmy(armyId);
			PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, damagesPb.build());
			if (army != null) {
				army.sendPbMessage(message);
			}
		}
	}

	/** 满血 */
	public void recovery() {
		addCurBlood(lessBlood(), DamageEffecterType.BLOOD, 0, 0);
		addCurSoul(lessSoul(), DamageEffecterType.SOUL, 0, 0);
	}

	public void addCurBlood(int addValue, int type, int fromType, long fromId) {
		List<Damage> damages = new ArrayList<>();

		Damage blood = new Damage(this, this);
		blood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
		blood.setDamageValue(-addValue);
		blood.setSource(this);
		blood.setCalcType(type);
		blood.setFromType(fromType);
		blood.setFromId(fromId);
		damages.add(blood);
		takeDamage(blood);

		DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
		damagesPb.setAttackId(-1);
		for (Damage d : damages) {
			DamageMsg.Builder dmsg = DamageMsg.newBuilder();
			d.writeProto(dmsg);
			damagesPb.addDamages(dmsg);
		}
		Set<Long> players = getNears(new PlayerSelectorHelper(this));
		// 添加自己
		players.add(getArmyId());

		for (Long armyId : players) {
			ArmyProxy army = WorldMgr.getArmy(armyId);
			PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, damagesPb.build());
			if (army != null) {
				army.sendPbMessage(message);
			}
		}
	}

	public void addCurSoul(int addValue, int type, int fromType, long fromId) {
		List<Damage> damages = new ArrayList<>();

		Damage soul = new Damage(this, this);
		soul.setDamageType(EnumAttr.CUR_SOUL.getValue());
		soul.setDamageValue(-addValue);
		soul.setCalcType(type);
		soul.setSource(this);
		soul.setFromType(fromType);
		soul.setFromId(fromId);
		damages.add(soul);
		takeDamage(soul);

		DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
		damagesPb.setAttackId(-1);
		for (Damage d : damages) {
			DamageMsg.Builder dmsg = DamageMsg.newBuilder();
			d.writeProto(dmsg);
			damagesPb.addDamages(dmsg);
		}
		Set<Long> players = getNears(new PlayerSelectorHelper(this));
		// 添加自己
		players.add(getArmyId());
		for (Long armyId : players) {
			ArmyProxy army = WorldMgr.getArmy(armyId);
			PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, damagesPb.build());
			if (army != null) {
				army.sendPbMessage(message);
			}
		}
	}

	public int getInitValue(EnumAttr type) {
		if (type.getValue() < 0 || type.getValue() > 29) {
			return 0;
		}
		return getInitData(type);
	}

	public void setBufferProperty(EnumAttr attr, long value) {
		/* 不允许出现负属性 */
		if (value < 0) {
			value = 0;
		}

		setBufferData(attr, (int) value);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAttack() {
		return getToalData(EnumAttr.ATTACK);
	}

	public int getDefence() {
		return getToalData(EnumAttr.DEFENCE);
	}

	public int getSoulAttack() {
		return getToalData(EnumAttr.SOUL_ATTACK);
	}

	public int getSoulDefence() {
		return getToalData(EnumAttr.SOUL_DEFENCE);
	}

	public int getAccurate() {
		return getToalData(EnumAttr.ACCURATE);
	}

	public int getDodge() {
		return getToalData(EnumAttr.DODGE);
	}

	public int getCrit() {
		return getToalData(EnumAttr.CRIT);
	}

	public int getCritDefence() {
		return getToalData(EnumAttr.CRIT_DEFENCE);
	}

	public int getCritAddtion() {
		return getToalData(EnumAttr.CRIT_ADDTION);
	}

	public int getCritCut() {
		return getToalData(EnumAttr.CRIT_CUT);
	}

	public int getAttackAddtion() {
		return getToalData(EnumAttr.ATTACK_ADDTION);
	}

	public int getAttackCut() {
		return getToalData(EnumAttr.ATTACK_CUT);
	}

	public int getSoulAttackAddtion() {
		return getToalData(EnumAttr.SOUL_ATTACK_ADDTION);
	}

	public int getSoulAttackCut() {
		return getToalData(EnumAttr.SOUL_ATTACK_CUT);
	}

	public int getRegainSoul() {
		return getToalData(EnumAttr.REGAIN_SOUL);
	}

	public int getRegainBlood() {
		return getToalData(EnumAttr.REGAIN_BLOOD);
	}

	public int getMetal() {
		return getToalData(EnumAttr.METAL);
	}

	public int getWood() {
		return getToalData(EnumAttr.WOOD);
	}

	public int getWater() {
		return getToalData(EnumAttr.WATER);
	}

	public int getFire() {
		return getToalData(EnumAttr.FIRE);
	}

	public int getEarth() {
		return getToalData(EnumAttr.EARTH);
	}

	public int getMetalDefence() {
		return getToalData(EnumAttr.METAL_DEFENCE);
	}

	public int getWoodDefence() {
		return getToalData(EnumAttr.WOOD_DEFENCE);
	}

	public int getWaterDefence() {
		return getToalData(EnumAttr.WATER_DEFENCE);
	}

	public int getFireDefence() {
		return getToalData(EnumAttr.FIRE_DEFENCE);
	}

	public int getEarthDefence() {
		return getToalData(EnumAttr.EARTH_DEFENCE);
	}

	public int getSpeed() {
		return getToalData(EnumAttr.SPEED);
	}

	public void setField(Field field) {
		this.field = field;
	}

	public int getInitSoul() {
		return getInitData(EnumAttr.SOUL);
	}

	public int getMaxSoul() {
		return getToalData(EnumAttr.SOUL);
	}

	public int getCurSoul() {
		return curSoul;
	}

	public void setCurSoul(int curSoul) {
		this.curSoul = curSoul;
	}

	public int getInitBlood() {
		return getInitData(EnumAttr.BLOOD);
	}

	public int getMaxBlood() {
		return getToalData(EnumAttr.BLOOD);
	}

	public int getCurBlood() {
		return curBlood;
	}

	public void setCurBlood(int curBlood) {
		this.curBlood = curBlood;
		if (curBlood <= 0 && isSoulState() == false) {
			changeSoulState(true);
		}

		if (getMaxBlood() > 0 && curBlood >= getMaxBlood() && isSoulState() == true) {
			changeSoulState(false);
		}
	}

	public Map<Integer, Skill> getDrivingSkills() {
		return drivingSkills;
	}

	public void setDrivingSkills(Map<Integer, Skill> drivingSkills) {
		this.drivingSkills = drivingSkills;
	}

	public boolean isPlayer() {
		return this.type == RoleConstants.RoleType.player;
	}

	public boolean isProtection() {
		return protection;
	}

	public void setProtection(boolean protection) {
		this.protection = protection;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public long getArmyId() {
		return armyId;
	}

	public void setArmyId(long armyId) {
		this.armyId = armyId;
	}

	public int getLivingState() {
		return livingState;
	}

	public void setLivingState(int livingState) {
		if (this.livingState == DISTORY) {
			Log.error("-----------重要调试日志，发现请通知---------------" + " livingId :" + this.getId(), new Exception());
			return;
		}
		this.livingState = livingState;
	}

	public long getRestoreTime() {
		return restoreTime;
	}

	public void setRestoreTime(long restoreTime) {
		this.restoreTime = restoreTime;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public boolean isClear() {
		return false;
	}

	public boolean costMana(int count) {
		if (this.mana >= count) {
			this.mana = this.mana - count;

			ArmyProxy army = WorldMgr.getArmy(this.getArmyId());
			if (army != null) {
				army.notifyMana();
			}
			return true;
		} else {
			return false;
		}
	}

	public void clearData() {
		if (field != null) {
			field.leaveField(this);
		}
		field = null;
		node = null;
		drivingSkills.clear();
		// mapSkill.clear();
		permanentBuffer.clear();
		workBuffers.clear();
		allBuffers.clear();
		livingStatus.clear();
		cooldowns.clear();
		setLivingState(DISTORY);
	}

	public long getSoulExp() {
		return soulExp;
	}

	public void setSoulExp(long soulExp) {
		this.soulExp = soulExp;
	}

	protected void notityState(LivingState state) {

	}

}
