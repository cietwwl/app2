package com.chuangyou.xianni.role.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.chuangyou.common.protobuf.pb.PlayerAttSnapProto.PlayerAttSnapMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageListMsgProtocol.DamageListMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.ai2.proxy.AvatarAI;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.mgr.AvatarTempManager;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.entity.avatar.AvatarTemplateInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.HeartbeatWorldMgr;
import com.chuangyou.xianni.world.SimplePlayerInfo;
import com.chuangyou.xianni.world.WorldMgr;

/** 死亡时间 */
public class Avatar extends Robot {
	private int					dieTime			= 15;		// 死亡时间
	private int					correspond;					// 默契等级
	protected boolean			correspondStatu	= false;	// 变身状态 0 未合体 1 合体
	private int					campaignId;					// 所在副本ID
	private volatile boolean	revivaling		= false;
	private AvatarAI			ai;

	public Avatar() {
		super(IDMakerHelper.nextID());
		setType(RoleType.avatar);
		this.ai = new AvatarAI(this);
		HeartbeatWorldMgr.addAI(ai);
	}

	public PlayerAttSnapMsg.Builder getAttSnapMsg() {
		PlayerAttSnapMsg.Builder builder = super.getAttSnapMsg();
		return builder;
	}

	/** 判断是否死亡 */
	public synchronized boolean isDie() {
		if (otherDamageCalWay()) {
			return curBlood <= 0 || getLivingState() == DIE || getLivingState() == DISTORY;
		}
		return curSoul <= 0 || getLivingState() == DIE || getLivingState() == DISTORY;
	}

	/** 死亡规则：玩家元婴期以前，不计算魂血，魂伤扣气血 */
	public boolean otherDamageCalWay() {
		return super.otherDamageCalWay();
	}

	public BattleLivingInfoMsg.Builder getBattlePlayerInfoMsg() {
		BattleLivingInfoMsg.Builder builder = super.getBattlePlayerInfoMsg();
		return builder;
	}

	// 设置成合体状态并且踢出地图
	public void disappear() {
		correspondStatu = true;
		Field field = getField();
		if (field != null) {
			field.leaveField(this);
		}
	}

	// 离开副本
	public void leaveCampaign() {
		Field field = getField();
		if (field != null) {
			field.leaveField(this);
		}
	}

	// 回归
	public void reback() {
		correspondStatu = false;
		ArmyProxy army = WorldMgr.getArmy(getArmyId());
		if (army == null) {
			Log.error("find avatar not has army");
			return;
		}
		Field field = army.getPlayer().getField();

		if (field != null && field.getCampaignId() > 0) {
			this.setPostion(army.getPlayer().getPostion());
			field.enterField(this);
		}
	}

	// 写入属性
	public void writeBaseProperty(BaseProperty baseProperty) {
		AvatarTemplateInfo temp = AvatarTempManager.getAvatarTemplateInfo(getSkin());
		if (temp == null) {
			return;
		}
		int parcent = temp.getAddtionPercent();
		baseProperty.addBlood(getProperty(EnumAttr.BLOOD) * parcent / 100);
		baseProperty.addSoul(getProperty(EnumAttr.SOUL) * parcent / 100);
		baseProperty.addAttack(getProperty(EnumAttr.ATTACK) * parcent / 100);
		baseProperty.addDefence(getProperty(EnumAttr.DEFENCE) * parcent / 100);
		baseProperty.addAccurate(getProperty(EnumAttr.ACCURATE) * parcent / 100);
		baseProperty.addDodge(getProperty(EnumAttr.DODGE) * parcent / 100);
		baseProperty.addCrit(getProperty(EnumAttr.CRIT) * parcent / 100);
		baseProperty.addCritDefence(getProperty(EnumAttr.CRIT_DEFENCE) * parcent / 100);
	}

	public boolean onDie(Living killer) {
		if (super.onDie(killer)) {
			if (killer.getArmyId() != this.getArmyId()) {
				dieTime = dieTime + 5 > 30 ? 30 : dieTime + 5;
			}
			if (revivaling == false) {
				RevivalAvatarAction die = new RevivalAvatarAction(this, dieTime * 1000);
				die.getActionQueue().enDelayQueue(die);
				revivaling = true;
			}
		}
		return true;
	}

	public void readProperty(PropertyListMsg pmsg) {
		List<PropertyMsg> properties = new ArrayList<>(pmsg.getPropertysList());
		super.readProperty(properties);

		for (PropertyMsg property : properties) {
			if (property.getType() == EnumAttr.AVTAR_CORRESPOND.getValue()) {
				this.correspond = (int) property.getTotalPoint();
				break;
			}
		}
	}

	/** 浸染 */
	public void instill(RobotInfoMsg data) {
		simpleInfo = new SimplePlayerInfo();
		simpleInfo.readProto(data.getSimpInfo());
		setArmyId(data.getSimpInfo().getPlayerId());
		setSkin(simpleInfo.getSkinId());
		// 速度
		ArmyProxy army = WorldMgr.getArmy(armyId);
		if (army != null) {
			setProperty(EnumAttr.SPEED, army.getPlayer().getSpeed());
		} else {
			setProperty(EnumAttr.SPEED, 600);
		}
		// 注入属性
		readProperty(data.getPropertis());
		if (getCurBlood() <= 0) {
			setSoulState(true);
		}
		List<Integer> skillIds = new ArrayList<>(data.getBattleSkillsList());

		// 加入普攻（分身普攻固定技能）
		AvatarTemplateInfo atemp = AvatarTempManager.getAvatarTemplateInfo(simpleInfo.getSkinId());
		if (atemp != null) {
			skillIds.add(atemp.getCommonSkillId1());
			skillIds.add(atemp.getCommonSkillId2());
			skillIds.add(atemp.getCommonSkillId3());
			skillIds.add(atemp.getCommonSkillId4());
		}

		if (skillIds != null) {
			for (Integer skillId : skillIds) {
				SkillTempateInfo skillTempateInfo = BattleTempMgr.getBSkillInfo(Integer.valueOf(skillId));
				if (skillTempateInfo == null) {
					continue;
				}
				SkillActionTemplateInfo actionTemp = BattleTempMgr.getActionInfo(skillTempateInfo.getActionId());
				if (actionTemp == null) {
					continue;
				}
				Skill skill = new Skill(actionTemp);
				skill.setSkillTempateInfo(skillTempateInfo);
				addSkill(skill);
			}
		}
	}

	/* 满血复活 */
	public boolean renascence() {
		if (getLivingState() == DISTORY) {
			return false;
		}
		if (getLivingState() != ALIVE) {
			setLivingState(ALIVE);
			sendChangeStatuMsg(LIVING, getLivingState());
		}
		List<Damage> damages = new ArrayList<>();
		Damage curSoul = new Damage(this, this);
		curSoul.setDamageType(EnumAttr.CUR_SOUL.getValue());
		curSoul.setDamageValue(0 - getInitSoul());
		damages.add(curSoul);
		takeDamage(curSoul);

		Damage curBlood = new Damage(this, this);
		curBlood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
		curBlood.setDamageValue(0 - getInitBlood());
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
				// 宠物重生位置，在自己人物身边
				if (getArmyId() == armyId) {
					this.setPostion(army.getPlayer().getPostion());
				}

				PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, damagesPb.build());
				if (army != null) {
					army.sendPbMessage(message);
				}
			}
		}
		this.isSoulState = false;
		this.revivaling = false;
		return true;
	}

	class RevivalAvatarAction extends DelayAction {
		private Avatar avatar;

		public RevivalAvatarAction(Avatar avatar, int delay) {
			super(avatar, delay);
			this.avatar = avatar;
		}

		@Override
		public void execute() {
			avatar.renascence();
		}

	}

	public int getCorrespond() {
		return correspond;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
}
