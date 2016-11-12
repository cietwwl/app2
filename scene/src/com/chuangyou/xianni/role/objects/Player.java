package com.chuangyou.xianni.role.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg.Builder;
import com.chuangyou.common.protobuf.pb.battle.DamageListMsgProtocol.DamageListMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanGetInfoRespProto.MagicwpBanGetInfoRespMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanInfoBeanProto.MagicwpBanInfoBeanMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterListProto.PlayerKillMonsterListMsg;
import com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg;
import com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.battle.action.HeroPollingAction;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.magicwpban.MagicwpCompanent;
import com.chuangyou.xianni.battle.magicwpban.MagicwpCompanentFactory;
import com.chuangyou.xianni.battle.mgr.AvatarTempManager;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.FuseSkillVo;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.campaign.ArenaBattleCampaign;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.PvP1v1Campaign;
import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.campaign.node.CampaignNodeDecorator;
import com.chuangyou.xianni.campaign.task.CTBaseCondition;
import com.chuangyou.xianni.constant.AvatarConstant;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.constant.BattleSettlementConstant;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.constant.SkillConstant.SkillSonType;
import com.chuangyou.xianni.entity.avatar.AvatarCorrespondTemplateInfo;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.equip.EquipAwakenCfg;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.entity.soul.SoulFuseSkillConfig;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.mount.MountTempleteMgr;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.action.RevivalPlayerAction;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.FieldConstants.FieldAttackRule;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.warfield.spawn.BeadMonsterSpawnNode;
import com.chuangyou.xianni.warfield.spawn.PerareState;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class Player extends ActiveLiving {
	/** 是否复活中 */
	private volatile boolean							revivaling				= false;
	/**
	 * 玩家坐骑状态 0未乘骑 1乘骑坐骑
	 */
	private int											mountState				= 1;
	private List<Integer>								monsterRefreshIdList	= new ArrayList<Integer>();
	private boolean										flashName				= false;

	/** 副本buffer */
	private List<Buffer>								campaignBuffers			= new ArrayList<>();

	/** 魂幡携带buffer */
	private Map<Integer, FuseSkillVo>					fuseSkillVos			= new HashMap<>();

	/** 魂幡buffers */
	private Buffer[]									fuseSkillBuffers		= new Buffer[4];

	/** 武器携带buffer */
	private Buffer										weaponBuffer;

	/** 武器技能(觉醒获得) */
	private int											weaponBuffId			= 0;

	/** 法宝禁制 */
	private Map<MagicwpBanConstant, MagicwpCompanent>	magicwps				= new HashMap<>();

	/** 宠物激活的玩家技能 */
	protected Skill										petSkill				= null;
	/** 宠物自己使用的技能 */
	protected Map<Integer, Skill>						petUseSkills			= new HashMap<>();

	/**
	 * 魂幡等级
	 */
	private int											soulLv					= 0;
	/** 解除变身时间 */
	private long										unTransfigurationTime	= 0;
	// 变身状态 0 未合体 1 合体
	protected int										correspondStatu			= 0;
	// 变身ID
	protected int										avatarTempId			= 0;
	// 变身相关属性
	private static EnumAttr[]							avatarAttrs;
	// 变身技能ID
	public static final int								TRANS_SKILL_ID			= 70001001;
	// 取消变身技能ID
	public static final int								UN_TRANS_SKILL_ID		= 70001002;
	// 变身后技能
	protected Map<Integer, Skill>						avatarSkills			= new HashMap<>();
	protected int										avatarEnergy;										// 仙力

	/** pk 值计算时间 */
	private long										pkValCalTime			= 0;
	static {
		avatarAttrs = new EnumAttr[] { EnumAttr.BLOOD, EnumAttr.SOUL, EnumAttr.ATTACK, EnumAttr.DEFENCE, EnumAttr.ACCURATE, EnumAttr.DODGE, EnumAttr.CRIT, EnumAttr.CRIT_DEFENCE };
	}

	public Player(long playerId) {
		super(playerId, playerId);
		// 每个人物身上有自调度，人物退出时候清理。(注意：添加此调度，必须有对应的销毁对象后清理)
		setType(RoleType.player);
		HeroPollingAction heroAction = new HeroPollingAction(this);
		this.enDelayQueue(heroAction);
	}

	public void readHeroInfo(HeroInfoMsg hero) {
		readProperty(hero.getPropertis());
		readSkillInfo(hero);
		this.setSoulLv(hero.getSoulLv());
	}

	public void updateHeroInfo(HeroInfoMsg hero) {
		readProperty(hero.getPropertis());
		readSkillInfo(hero);

		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_G_BATTLEPLAYERINFO, getBattlePlayerInfoMsg().build());
	}

	public void updateWeaponBuff() {
		if (simpleInfo.getWeaponId() == 0 || simpleInfo.getWeaponAwaken() == 0) {
			this.setWeaponBuffId(0);
		} else {
			EquipAwakenCfg cfg = EquipTemplateMgr.getAwakenMap().get(simpleInfo.getWeaponId() * 10000l + simpleInfo.getWeaponAwaken() * 100l + 0);
			if (cfg != null) {
				this.setWeaponBuffId(cfg.getSkillId());
			} else {
				this.setWeaponBuffId(0);
			}

		}
		addWeaponBuffer(this.getWeaponBuffId());
	}

	@Override
	public boolean onDie(Living source) {
		if (super.onDie(source)) {
			DieAction die = new DieAction(this, source, 1000);
			die.getActionQueue().enDelayQueue(die);
			getTruckHelper().relationOnDie();
			if (field != null && field.getCampaignId() > 0) {
				Campaign campaign = CampaignMgr.getCampagin(field.getCampaignId());
				if (campaign != null) {
					campaign.notifyTaskEvent(CTBaseCondition.LESS_DEAD_COUNT, 1);
					if (campaign instanceof ArenaBattleCampaign) {
						((ArenaBattleCampaign) campaign).playerFail();
					}
					if (campaign instanceof PvP1v1Campaign) {
						((PvP1v1Campaign) campaign).die(this.getArmyId());
					}
					if (campaign instanceof StateCampaign) {
						((StateCampaign) campaign).failOver();
					}

					if (source.getArmyId() > 0) {
						campaign.onPlayerDie(this, source);
					}
				}
			}
			if (isCorrespondStatu()) {
				unTransfiguration();
			}
		}

		return true;
	}

	/* 满血复活 */
	public synchronized boolean renascence() {
		if (getLivingState() == ALIVE) {
			return false;
		}
		if (getLivingState() == DISTORY) {
			return false;
		}
		setLivingState(ALIVE);
		sendChangeStatuMsg(LIVING, getLivingState());
		List<Damage> damages = new ArrayList<>();
		Damage curSoul = new Damage(this, this);
		curSoul.setDamageType(EnumAttr.CUR_SOUL.getValue());
		curSoul.setDamageValue(0 - getMaxSoul());
		damages.add(curSoul);
		takeDamage(curSoul);

		Damage curBlood = new Damage(this, this);
		curBlood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
		curBlood.setDamageValue(0 - getMaxBlood());
		damages.add(curBlood);
		takeDamage(curBlood);

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
			for (Long armyId : players) {
				ArmyProxy army = WorldMgr.getArmy(armyId);
				PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, damagesPb.build());
				if (army != null) {
					army.sendPbMessage(message);
				}
			}
		}
		this.isSoulState = false;
		this.revivaling = false;
		this.dieTime = 0;
		HeroPollingAction heroAction = new HeroPollingAction(this);
		this.enDelayQueue(heroAction);
		return true;
	}

	public List<Buffer> getExeWayBuffers(int exeWay) {
		List<Buffer> buffers = super.getExeWayBuffers(exeWay);
		if (weaponBuffer != null && weaponBuffer.getExeWay() == exeWay) {
			buffers.add(weaponBuffer);
		}
		return buffers;
	}

	public List<Buffer> getTypeBuffers(int type) {
		List<Buffer> toal = super.getTypeBuffers(type);
		if (weaponBuffer != null && weaponBuffer.getType() == type) {
			toal.add(weaponBuffer);
		}
		return toal;
	}

	/**
	 * 初始化技能
	 */
	public void readSkillInfo(HeroInfoMsg hero) {
		List<Integer> toalSkillInfos = new ArrayList<>();
		toalSkillInfos.addAll(hero.getSkillInfosList());
		// 清理旧技能
		this.drivingSkills.clear();
		this.permanentBuffer.clear();
		if (toalSkillInfos != null && toalSkillInfos.size() != 0) {
			// 添加变身技能
			toalSkillInfos.add(TRANS_SKILL_ID);
			toalSkillInfos.add(UN_TRANS_SKILL_ID);
			for (Integer tempId : toalSkillInfos) {
				SkillTempateInfo skillInfo = BattleTempMgr.getBSkillInfo(tempId);
				if (skillInfo == null) {
					continue;
				}
				// 添加可执行技能
				SkillActionTemplateInfo actionInfo = BattleTempMgr.getActionInfo(skillInfo.getActionId());
				if (actionInfo != null) {
					Skill skill = new Skill(actionInfo);
					skill.setSkillTempateInfo(skillInfo);
					addSkill(skill);
				}
				// 添加技能携带常驻buffer
				if (skillInfo.getFightBufferIds() != null && !skillInfo.getFightBufferIds().equals("")) {
					// 给英雄添加skillInfo自带的buffers，作为常驻buffer
					String[] bufferIds = skillInfo.getFightBufferIds().split(",");
					for (String bufferId : bufferIds) {
						SkillBufferTemplateInfo sbinfo = BattleTempMgr.getBufferInfo(Integer.valueOf(bufferId));
						if (sbinfo == null) {
							continue;
						}
						Buffer buff = BufferFactory.createBuffer(this, this, sbinfo);
						buff.setPermanent(true);
						addPermanentBuffer(buff);
					}
				}
			}
		}

		List<FuseSkillMsg> fuseSkills = hero.getFuseSkillsList();
		this.setFuseSkill(null, 0);
		this.setFuseSkill(null, 1);
		this.setFuseSkill(null, 2);
		this.setFuseSkill(null, 3);
		if (fuseSkills != null && fuseSkills.size() != 0) {
			for (FuseSkillMsg fuseSkillMsg : fuseSkills) {
				if (fuseSkillMsg.getIndex() > 4) {
					Log.error("------------index is error------------" + fuseSkillMsg.getIndex());
					continue;
				}
				this.setFuseSkill(new FuseSkillVo(fuseSkillMsg.getFuseSkillId(), fuseSkillMsg.getColor()), fuseSkillMsg.getIndex() - 1);
			}
		}
		// 添加变身技能

	}

	/** 武器buffer */
	public void addFuseBuffer(int bufferId, int index) {
		Buffer older = this.fuseSkillBuffers[index];
		if (bufferId == 0) {
			this.fuseSkillBuffers[index] = null;
			return;
		}
		SkillBufferTemplateInfo sbinfo = BattleTempMgr.getBufferInfo(bufferId);
		if (sbinfo == null) {
			Log.error("cannot find buffer temp ,tempId : " + bufferId);
			this.fuseSkillBuffers[index] = null;
			return;
		}

		Buffer buff = BufferFactory.createBuffer(this, this, sbinfo);
		buff.setPermanent(true);
		this.fuseSkillBuffers[index] = buff;
		if (older != null) {
			older.setPermanent(false);
			older.dispose();
		}
	}

	/** 获取魂幡buff */
	public Buffer getFuseBuffer(int index) {
		return fuseSkillBuffers[index];
	}

	/** 武器buffer */
	public void addWeaponBuffer(int weaponBufId) {
		Buffer older = this.getWeaponBuffer();
		if (weaponBufId == 0) {
			this.setWeaponBuffer(null);
			return;
		}
		SkillBufferTemplateInfo sbinfo = BattleTempMgr.getBufferInfo(weaponBufId);
		if (sbinfo == null) {
			Log.error("cannot find buffer temp ,tempId : " + weaponBufId);
			this.setWeaponBuffer(null);
			return;
		}

		Buffer buff = BufferFactory.createBuffer(this, this, sbinfo);
		buff.setPermanent(true);
		this.setWeaponBuffer(buff);

		if (older != null) {
			older.setPermanent(false);
			older.dispose();
		}
	}

	@Override
	public Builder getBattlePlayerInfoMsg() {
		BattleLivingInfoMsg.Builder temp = super.getBattlePlayerInfoMsg();
		temp.setMountState(getMountState());
		temp.setAvatarState(correspondStatu);
		temp.setAvatarTempId(avatarTempId);
		// 如果是变身状态，则清理以前技能,补进合体后技能
		if (isCorrespondStatu()) {
			temp.clearSkills();
			for (Skill skill : avatarSkills.values()) {
				temp.addSkills(skill.getSkillId());
			}
		}
		// 宠物技能
		if (petSkill != null && petSkill.getSkillId() > 0) {
			temp.addSkills(petSkill.getSkillId());
		}
		if (field != null && field.getCampaignId() > 0) {
			Campaign campaign = CampaignMgr.getCampagin(field.getCampaignId());
			if (campaign != null) {
				// 获取场景玩家详情时，如果在副本中，插入副本临时属性
				campaign.insertCampaignAtt(this.getArmyId(), temp);
			}
		}
		return temp;
	}

	// 获取技能
	public Skill getSkill(int skillId) {
		if (petSkill != null && petSkill.getSkillId() == skillId) {
			return petSkill;
		}
		if (petUseSkills.containsKey(skillId)) {
			return petUseSkills.get(skillId);
		}
		if (!isCorrespondStatu() || skillId == TRANS_SKILL_ID || skillId == UN_TRANS_SKILL_ID) {
			return drivingSkills.get(skillId);
		}
		return avatarSkills.get(skillId);

	}

	public void addCampaignBuff(Buffer buff) {
		campaignBuffers.add(buff);
		this.addBuffer(buff);
	}

	public void removeCampaignBuffer() {
		for (Buffer buffer : campaignBuffers) {
			removeBuffer(buffer);
		}
		campaignBuffers.clear();
	}

	private void calPKValue(Living source, Living deather) {
		// 攻击源处理
		if (source.getBattleMode() == BattleModeCode.warBattleMode && getBattleMode() == BattleModeCode.peaceBattleMode) {// 增加pk值
			int colour = getColour(source.getPkVal());
			source.setPkVal(source.getPkVal() + 1000);
			// 通知
			Map<Integer, Long> changeMap = new HashMap<Integer, Long>();
			changeMap.put(EnumAttr.PK_VAL.getValue(), (long) source.getPkVal());
			notifyCenter(changeMap, source.getArmyId());

			List<PropertyMsg> properties = new ArrayList<>();
			PropertyMsg.Builder p = PropertyMsg.newBuilder();
			p.setBasePoint((long) source.getPkVal());
			p.setTotalPoint((long) source.getPkVal());
			p.setType(EnumAttr.PK_VAL.getValue());
			properties.add(p.build());
			// updateProperty(source, properties);
			if (colour != getColour(source.getPkVal())) {
				updateProperty(source, properties);
			} else {
				upProperty(source, properties);
			}
		}

		// 自己
		List<PropertyMsg> properties = new ArrayList<>();
		Map<Integer, Long> changeMap = new HashMap<Integer, Long>();

		int changePkVal = 0;// 减少pk值
		if (getColour(getPkVal()) == BattleModeCode.yellow) {
			changePkVal = MathUtils.randomClamp(10, 20);
			notifyCenter(source.getArmyId(), getArmyId());
		} else if (getColour(getPkVal()) == BattleModeCode.red) {
			changePkVal = MathUtils.randomClamp(40, 80);
			notifyCenter(source.getArmyId(), getArmyId());
		}

		if (changePkVal > 0) {
			changePkVal = getPkVal() - changePkVal < 0 ? 0 : getPkVal() - changePkVal;
			int colour = getColour(this.getPkVal());
			setPkVal(changePkVal);
			PropertyMsg.Builder p = PropertyMsg.newBuilder();
			p.setBasePoint(changePkVal);
			p.setTotalPoint(changePkVal);
			p.setType(EnumAttr.PK_VAL.getValue());
			properties.add(p.build());
			changeMap.put(EnumAttr.PK_VAL.getValue(), (long) changePkVal);
			notifyCenter(changeMap, getArmyId());
			// updateProperty(deather, properties);
			if (colour != getColour(deather.getPkVal())) {
				updateProperty(deather, properties);
			} else {
				upProperty(deather, properties);
			}
		}
	}

	public boolean isRevivaling() {
		return revivaling;
	}

	public void setRevivaling(boolean revivaling) {
		this.revivaling = revivaling;
	}

	public int getMountState() {
		return mountState;
	}

	public void setMountState(int mountState) {
		this.mountState = mountState;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		int speed = super.getSpeed();
		if (mountState > 0) {
			if (this.simpleInfo != null && this.simpleInfo.getMountId() > 0) {
				MountGradeCfg mountCfg = MountTempleteMgr.getMountTemps().get(this.simpleInfo.getMountId());
				if (mountCfg != null) {
					speed += speed * mountCfg.getSpeed(EnumAttr.SPEED.getValue()) / 10000;
				}
			}
		}
		return speed;
	}

	public List<Integer> getMonsterRefreshIdList() {
		return monsterRefreshIdList;
	}

	public void reSetMonsterRefreshIdList(List<Integer> monsterRefreshIdList, int curCampaign) {
		ArrayList<Integer> date = new ArrayList<Integer>();
		date.addAll(monsterRefreshIdList);

		date.removeAll(this.monsterRefreshIdList);// 新增加的
		int maxId = 0; // 副本中最大刷怪点
		Campaign campaign = CampaignMgr.getCampagin(curCampaign);
		if (campaign != null) {
			Field f = campaign.getEnterField(0);
			Map<Integer, SpawnInfo> spawnInfos = SpawnTemplateMgr.getFieldSpawnInfos(f.getMapKey());
			if (date.size() > 0) {
				for (Entry<Integer, SpwanNode> entry : campaign.getSpwanNodes().entrySet()) {
					int tagId = entry.getValue().getSpawnInfo().getTagId();
					date.remove(Integer.valueOf(tagId));
					if (tagId > maxId)
						maxId = tagId;
				}
			}
			int endId = 0;
			for (int i = 0; i < date.size(); i++) {
				Integer tagId = date.get(i);
				if (tagId <= maxId)
					continue;

				int spwanId = SpawnTemplateMgr.getSpwanId(tagId);
				SpawnInfo sf = spawnInfos.get(spwanId);
				if (sf == null)
					continue;
				if (i == date.size() - 1) {
					endId = sf.getTagId();
					sf.setCampaignFeatures(Campaign.TERMINATOR);
				}
				SpwanNode node = new BeadMonsterSpawnNode(sf, f);
				f.addSpawnNode(node);
				node.build();
				node.stateTransition(new PerareState(node));
				campaign.getSpwanNodes().put(node.getSpwanId(), node);
			}
			for (Entry<Integer, SpwanNode> entry : campaign.getSpwanNodes().entrySet()) {
				if (endId > 0 && entry.getValue().getSpawnInfo().getTagId() != endId) {
					entry.getValue().setDecorator(new CampaignNodeDecorator());
				}
			}
		}

		this.monsterRefreshIdList.clear();
		this.monsterRefreshIdList.addAll(monsterRefreshIdList);
	}

	public boolean isFlashName() {
		return flashName;
	}

	public void setFlashName(boolean flashName) {
		this.flashName = flashName;
	}

	public FuseSkillVo getFuseSkill(int bufferId) {
		return fuseSkillVos.get(bufferId);
	}

	public void setFuseSkill(FuseSkillVo fuseSkill, int index) {
		if (fuseSkill != null) {
			SoulFuseSkillConfig config = BattleTempMgr.getFuseSkillTempById(fuseSkill.getSkillId());
			if (config == null) {
				Log.error("the skill can not find temp,the skillId : " + fuseSkill.getSkillId());
				addFuseBuffer(0, index);
				return;
			}
			addFuseBuffer(config.getBuff(), index);
			this.fuseSkillVos.put(fuseSkill.getBufferId(), fuseSkill);
		} else {
			addFuseBuffer(0, index);
		}
	}

	public int getWeaponBuffId() {
		return weaponBuffId;
	}

	public void setWeaponBuffId(int weaponBuffId) {
		this.weaponBuffId = weaponBuffId;
	}

	public Buffer getWeaponBuffer() {
		return weaponBuffer;
	}

	public void setWeaponBuffer(Buffer weaponBuffer) {
		this.weaponBuffer = weaponBuffer;
	}

	public int getSoulLv() {
		return soulLv;
	}

	public void setSoulLv(int soulLv) {
		this.soulLv = soulLv;
	}

	/**
	 * 设置宠物激活的玩家技能
	 * 
	 * @param petSkillId
	 * @param isNotify
	 */
	public void setPetSkillId(int petSkillId, boolean isNotify) {
		if (this.petSkill != null && this.petSkill.getSkillTempateInfo().getTemplateId() == petSkillId) {
			return;
		}
		petSkill = null;
		if (petSkillId > 0) {
			SkillTempateInfo skillTempateInfo = BattleTempMgr.getBSkillInfo(petSkillId);
			if (skillTempateInfo == null) {
				return;
			}
			SkillActionTemplateInfo actionTemp = BattleTempMgr.getActionInfo(skillTempateInfo.getActionId());
			if (actionTemp == null) {
				return;
			}
			petSkill = new Skill(actionTemp);
			petSkill.setSkillTempateInfo(skillTempateInfo);
		}
		if (isNotify == true) {
			notifyLivingSelf();
		}
	}

	/**
	 * 更新宠物自己用的技能
	 * 
	 * @param petTemp
	 */
	public void updatePetUseSkills(PetInfoCfg petTemp) {
		if (this.petUseSkills == null) {
			petUseSkills = new HashMap<>();
		}
		this.petUseSkills.clear();
		if (petTemp != null) {
			Set<Integer> skills = petTemp.getSkillSet();
			for (int skillId : skills) {
				SkillTempateInfo skillTempateInfo = BattleTempMgr.getBSkillInfo(skillId);
				if (skillTempateInfo == null) {
					return;
				}
				SkillActionTemplateInfo actionTemp = BattleTempMgr.getActionInfo(skillTempateInfo.getActionId());
				if (actionTemp == null) {
					return;
				}
				Skill skill = new Skill(actionTemp);
				skill.setSkillTempateInfo(skillTempateInfo);
				this.petUseSkills.put(skill.getSkillId(), skill);
			}
		}
	}

	public void notifyLivingSelf() {
		ArmyProxy army = WorldMgr.getArmy(getId());
		if (army != null) {
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_G_BATTLEPLAYERINFO, getBattlePlayerInfoMsg().build());
			army.sendPbMessage(pkg);
		}
	}

	/**
	 * 通知
	 * 
	 * @param playerId
	 * @param beKillerId
	 *            被杀者
	 */
	public void notifyCenter(long playerId, long beKillerId) {
		PlayerKillMonsterListMsg.Builder builder = PlayerKillMonsterListMsg.newBuilder();

		PlayerKillMonsterMsg.Builder msg = PlayerKillMonsterMsg.newBuilder();
		msg.setPlayerId(playerId);
		msg.setBeKillId(beKillerId);
		msg.setType(RoleType.player);
		msg.setJoinType(BattleSettlementConstant.KILLER);
		builder.addKillInfos(msg);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_PLAYER_KILL_MONSTER, msg);
		GatewayLinkedSet.send2Server(pkg);
	}

	/** 变身 */
	public void transfiguration() {
		// 第一步 ：查找出部队信息
		ArmyProxy army = WorldMgr.getArmy(getArmyId());
		if (army == null) {
			return;
		}

		// 第二步 是否存在分身或已经是分身状态
		List<Avatar> avatars = army.getAvatars();
		if (avatars == null || avatars.size() == 0) {
			return;
		}
		if (correspondStatu == 1) {
			return;
		}
		// 第三步 判断合体时间是否CD(5分钟CD时间)
		if (System.currentTimeMillis() - unTransfigurationTime < 0) { //
			return;
		}
		// 第四步 判断灵气是否足够变身
		if (getAvatarEnergy() < AvatarConstant.TRANSFIGURATION_COST) {
			return;
		}
		costAvatarEnergy(AvatarConstant.TRANSFIGURATION_COST);
		// 第五步 统计分身属性并从地图中移除所有分身
		BaseProperty baseProperty = new BaseProperty();
		for (Avatar avatar : avatars) {
			avatar.writeBaseProperty(baseProperty);
		}

		// 第六步 ： 人物添加分身属性
		addAvatarProperty(baseProperty);
		// 第七步：设置解除变身时间
		int durationTime = 30;// 保底30秒
		for (Avatar avatar : avatars) {
			AvatarCorrespondTemplateInfo at = AvatarTempManager.getAvatarCorrespondTemplateInfo(avatar.getSkin(), avatar.getCorrespond());
			durationTime += at.getFitTime();

			Log.error("durationTime  : " + durationTime + "  at" + at.getFitTime());
		}

		unTransfigurationTime = System.currentTimeMillis() + durationTime * 1000;
		// ---- 第八步：随机某个分身作为主体并补充其技能-----
		Avatar chosened = avatars.get(ThreadSafeRandom.getInstance().next(avatars.size()));
		correspondStatu = 1;
		mountState = 0;
		avatarTempId = chosened.getSimpleInfo().getSkinId();

		Skill un_trans_skill = new Skill(BattleTempMgr.getUN_TRANS_SKILL_TEMP());
		avatarSkills.put(un_trans_skill.getSkillId(), un_trans_skill);
		// 被选中者补入其所有技能
		for (Skill skill : chosened.getDrivingSkills().values()) {
			Skill newSkill = new Skill(skill.getTemplateInfo());
			avatarSkills.put(newSkill.getSkillId(), newSkill);
		}

		// 其他补入非配普攻技能
		for (Avatar avatar : avatars) {
			if (avatar == chosened) {
				continue;
			}
			if (avatar.getSimpleInfo().getSkinId() == chosened.getSimpleInfo().getSkinId()) {
				continue;
			}
			for (Skill skill : avatar.getDrivingSkills().values()) {
				// 分身技能 子类型为8
				if (skill.getSkillTempateInfo().getSonType() != SkillSonType.AVATAR_SKILL) {
					continue;
				}
				Skill newSkill = new Skill(skill.getTemplateInfo());
				avatarSkills.put(newSkill.getSkillId(), newSkill);
			}
		}
		// ----- 第九步：人物变更状态------
		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(getArmyId());
		for (long id : nears) {
			ArmyProxy others = WorldMgr.getArmy(id);
			if (others != null) {
				PBMessage message = MessageUtil.buildMessage(Protocol.U_G_BATTLEPLAYERINFO, getBattlePlayerInfoMsg());
				others.sendPbMessage(message);
			}
		}
		// BroadcastUtil.sendBroadcastPacket(nears,
		// Protocol.U_G_BATTLEPLAYERINFO, getBattlePlayerInfoMsg().build());
		for (Avatar avatar : avatars) {
			avatar.disappear();
		}
	}

	/** 解除变身 */
	public void unTransfiguration() {
		// 第一步 ：查找出部队信息
		ArmyProxy army = WorldMgr.getArmy(getArmyId());
		if (army == null) {
			return;
		}
		// 第二步 是否存在分身
		// List<Avatar> avatars = army.getAvatars();
		// if (avatars == null || avatars.size() == 0) {
		// return;
		// }
		// 第三步 移除合体属性
		clearAvatarProperty();
		// 第四步：人物变更状态并且通知客户端
		correspondStatu = 0;
		avatarTempId = 0;
		avatarSkills.clear();
		unTransfigurationTime = System.currentTimeMillis();
		// 第五步 返回出战的分身
		army.instillAvatar(getField(), getPostion());
		// ----- 第六步：人物变更状态------
		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_G_BATTLEPLAYERINFO, getBattlePlayerInfoMsg().build());
	}

	/** 添加合体属性 */
	private void addAvatarProperty(BaseProperty baseProperty) {
		// 判断是否有禁制-契
		MagicwpCompanent companent = getMagicwpCompanent(MagicwpBanConstant.AVATAR_CONTRACT);
		PlayerAttUpdateMsg.Builder notifyMsg = PlayerAttUpdateMsg.newBuilder();
		for (EnumAttr enumAttr : avatarAttrs) {
			Property pro = properties.get(enumAttr);
			int addValue = baseProperty.getProperty(enumAttr);
			if (companent != null && companent.isEffect()) {
				addValue += addValue * companent.getEffectValue() / 100;
			}
			pro.setAvatarData(addValue);
			PropertyMsg.Builder pmsg = PropertyMsg.newBuilder();
			pmsg.setType(pro.getType());
			pmsg.setTotalPoint(pro.getTotalJoin());
			notifyMsg.addAtt(pmsg);
		}
		notifyMsg.setPlayerId(getId());
		// 通知附近玩家
		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		if (this.armyId > 0) {
			nears.add(this.armyId);
		}
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_RESP_PLAYER_ATT_UPDATE, notifyMsg.build());
	}

	/** 移除合体属性 */
	private void clearAvatarProperty() {
		PlayerAttUpdateMsg.Builder notifyMsg = PlayerAttUpdateMsg.newBuilder();
		for (EnumAttr enumAttr : avatarAttrs) {
			Property pro = properties.get(enumAttr);
			pro.clearAvatarData();
			PropertyMsg.Builder pmsg = PropertyMsg.newBuilder();
			pmsg.setType(pro.getType());
			pmsg.setTotalPoint(pro.getTotalJoin());
			notifyMsg.addAtt(pmsg);
		}
		notifyMsg.setPlayerId(getId());
		// 通知附近玩家
		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		if (this.armyId > 0) {
			nears.add(this.armyId);
		}
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_RESP_PLAYER_ATT_UPDATE, notifyMsg.build());
	}

	public int getAvatarEnergy() {
		return avatarEnergy;
	}

	public void setAvatarEnergy(int avatarEnergy) {
		if (this.avatarEnergy != avatarEnergy) {
			this.avatarEnergy = avatarEnergy;
			ArmyProxy army = WorldMgr.getArmy(this.getArmyId());
			if (army != null) {
				army.notifyAvatarEnergy(0);
			}
		}
	}

	public boolean costAvatarEnergy(int count) {
		if (this.avatarEnergy >= count) {
			this.avatarEnergy = this.avatarEnergy - count;
			ArmyProxy army = WorldMgr.getArmy(this.getArmyId());
			if (army != null) {
				army.notifyAvatarEnergy(count);
			}
			return true;
		}
		return false;
	}

	public long getUnTransfigurationTime() {
		return unTransfigurationTime;
	}

	public boolean isCorrespondStatu() {
		return correspondStatu == 1;
	}

	public void setProperty(EnumAttr attr, long value) {
		super.setProperty(attr, value);
		if (attr == EnumAttr.AVATAR_ENERGY) {
			setAvatarEnergy((int) value);
		}
	}

	public long getPkValCalTime() {
		return pkValCalTime;
	}

	public void calPkVal() {
		if (this.getField().getAttackRule(null, null) == FieldAttackRule.USEPLAYERMODE && this.getPkVal() > 0) {
			this.pkValCalTime = System.currentTimeMillis();
			int changePkVal = MathUtils.randomClamp(1, 5);
			int colour = getColour(this.getPkVal());
			changePkVal = this.getPkVal() - changePkVal < 0 ? 0 : this.getPkVal() - changePkVal;
			this.setPkVal(changePkVal);
			Map<Integer, Long> changeMap = new HashMap<Integer, Long>();
			changeMap.put(EnumAttr.PK_VAL.getValue(), (long) this.getPkVal());
			notifyCenter(changeMap, this.getArmyId());

			List<PropertyMsg> properties = new ArrayList<>();
			PropertyMsg.Builder p = PropertyMsg.newBuilder();
			// p.setBasePoint(this.getPkVal());
			p.setTotalPoint(this.getPkVal());
			p.setType(EnumAttr.PK_VAL.getValue());
			properties.add(p.build());
			if (colour != getColour(this.getPkVal())) {
				updateProperty(this, properties);
			} else {
				upProperty(this, properties);
			}
		}
	}

	public void loadMagicwpData(MagicwpBanGetInfoRespMsg dataMsg) {
		magicwps.clear();
		for (MagicwpBanInfoBeanMsg bean : dataMsg.getBansList()) {
			MagicwpBanConstant banCon = MagicwpBanConstant.getCode(bean.getBanId());
			if (banCon == null) {
				Log.error("MagicwpBanConstant not find ,banId:" + bean.getBanId() + ",armyId " + this.id);
				continue;
			}
			MagicwpCompanent companent = MagicwpCompanentFactory.buildMagicwpCompanent(this, banCon, bean.getLevel());
			if (companent != null) {
				magicwps.put(banCon, companent);
			} else {
				Log.error("MagicwpBanConstant not find Decorator ,banId:" + bean.getBanId() + ",armyId " + this.id);
			}
		}
	}

	public MagicwpCompanent getMagicwpCompanent(MagicwpBanConstant banCon) {
		return magicwps.get(banCon);
	}

	public void setCurBlood(int curBlood) {
		this.curBlood = curBlood;
		if (curBlood <= 0 && isSoulState() == false) {
			MagicwpCompanent companent = getMagicwpCompanent(MagicwpBanConstant.RESIST_DEATH);
			if (companent == null || !companent.isEffect()) {
				changeSoulState(true);
			} else {
				companent.exe();
			}
		}
		if (curBlood >= getMaxBlood() && isSoulState() == true) {
			changeSoulState(false);
		}
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		super.clearData();

		monsterRefreshIdList.clear();
		campaignBuffers.clear();
		fuseSkillVos.clear();
		fuseSkillBuffers = new Buffer[4];
		weaponBuffer = null;
		magicwps.clear();
		petSkill = null;
		petUseSkills.clear();
		avatarSkills.clear();
	}

	/** 判断是否死亡 */
	public synchronized boolean isDie() {
		boolean isDie = super.isDie();
		if (isDie) {
			MagicwpCompanent companent = getMagicwpCompanent(MagicwpBanConstant.RESURRECTION);
			if (companent != null && companent.isEffect()) {
				companent.exe();
				return false;
			}
		}
		return isDie;
	}

	class DieAction extends DelayAction {
		Living	deather;
		Living	source;

		public DieAction(Living deather, Living source, int delay) {
			super(source, delay);
			this.deather = deather;
			this.source = source;
		}

		@Override
		public void execute() {
			ArmyProxy army = WorldMgr.getArmy(getArmyId());
			if (army == null) {
				Log.error("not find army when player die,playerId :" + getArmyId());
				return;
			}
			if (revivaling == false) {
				RevivalPlayerAction revival = new RevivalPlayerAction(army);
				ThreadManager.actionExecutor.enDelayQueue(revival);
				revivaling = true;
			}
			calPKValue(source, deather);
		}
	}

}
