package com.chuangyou.xianni.role.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg.Builder;
import com.chuangyou.common.protobuf.pb.battle.BufferMsgProto.BufferMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageListMsgProtocol.DamageListMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.action.HeroPollingAction;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.action.RevivalPlayerAction;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class Player extends ActiveLiving {
	/** 是否复活中 */
	private volatile boolean revivaling = false;
	/**
	 * 玩家坐骑状态 0未乘骑 1乘骑坐骑
	 */
	private int mountState = 1;

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
	}

	public void updateHeroInfo(HeroInfoMsg hero) {
		readProperty(hero.getPropertis());
		readSkillInfo(hero);

		Set<Long> nears = getNears(new PlayerSelectorHelper(this));
		nears.add(getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_G_BATTLEPLAYERINFO, getBattlePlayerInfoMsg().build());
	}

	public void onDie(Living source) {
		if (this.livingState == DIE) {
			return;
		}
		this.livingState = DIE;// 死亡状态不推，客户端自己判断
		clearWorkBuffer();
		dieTime = System.currentTimeMillis();
		System.err.println("living :" + this.armyId + " is die");
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
	}

	/* 满血复活 */
	public boolean renascence() {
		if (this.livingState == ALIVE) {
			return false;
		}

		this.livingState = ALIVE;
		sendChangeStatuMsg(LIVING, livingState);
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
				PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, damagesPb.build());
				if (army != null) {
					army.sendPbMessage(message);
				}
			}
			// BroadcastUtil.sendBroadcastPacket(players, Protocol.U_G_DAMAGE,
			// damagesPb.build());
		}

		this.isSoulState = false;
		this.dieTime = 0;
		HeroPollingAction heroAction = new HeroPollingAction(this);
		this.enDelayQueue(heroAction);
		return true;
	}

	/**
	 * 初始化技能
	 */
	public void readSkillInfo(HeroInfoMsg hero) {
		List<Integer> toalSkillInfos = hero.getSkillInfosList();

		if (toalSkillInfos != null && toalSkillInfos.size() != 0) {
			for (Integer tempId : toalSkillInfos) {
				SkillTempateInfo skillInfo = BattleTempMgr.getBSkillInfo(tempId);
				if (skillInfo != null) {
					// TODO 给英雄添加skillInfo自带的buffers，作为常驻buffer
				}
			}
		}

		List<Integer> battleSkilList = hero.getBattleSkillsList();

		if (battleSkilList != null && battleSkilList.size() != 0) {
			for (Integer tempId : toalSkillInfos) {
				// System.out.println("tempId: "+tempId);
				SkillTempateInfo skillInfo = BattleTempMgr.getBSkillInfo(tempId);
				if (skillInfo != null) {
					SkillActionTemplateInfo actionInfo = BattleTempMgr.getActionInfo(skillInfo.getActionId());
					if (actionInfo != null) {
						Skill skill = new Skill(actionInfo);
						skill.setSkillTempateInfo(skillInfo);
						addSkill(skill);
					}
					// SkillActionTemplateInfo actionInfo1 =
					// BattleTempMgr.getActionInfo(1002);
					// if (actionInfo1 != null) {
					// Skill skill1 = new Skill(actionInfo1);
					// addSkill(skill1);
					// }
					//
					// SkillActionTemplateInfo actionInfo2 =
					// BattleTempMgr.getActionInfo(1003);
					// if (actionInfo2 != null) {
					// Skill skill2 = new Skill(actionInfo2);
					// addSkill(skill2);
					// }
					//
					// SkillActionTemplateInfo actionInfo3 =
					// BattleTempMgr.getActionInfo(1004);
					// if (actionInfo3 != null) {
					// Skill skill3 = new Skill(actionInfo3);
					// addSkill(skill3);
					// }
				}
			}
		}

	}

	@Override
	public Builder getBattlePlayerInfoMsg() {
		// TODO Auto-generated method stub
		this.cachBattleInfoPacket = BattleLivingInfoMsg.newBuilder();
		cachBattleInfoPacket.setMountState(getMountState());
		return super.getBattlePlayerInfoMsg();
	}

	public void clearWorkBuffer() {
		List<Buffer> allbuffer = new ArrayList<>();
		synchronized (workBuffers) {
			for (Entry<Integer, List<Buffer>> entry : workBuffers.entrySet()) {
				List<Buffer> wayBufs = entry.getValue();
				allbuffer.addAll(wayBufs);
				wayBufs.clear();
			}
			workBuffers.clear();
		}

		for (Buffer buff : allbuffer) {
			buff.stop();
			BufferMsg.Builder bmsg = BufferMsg.newBuilder();
			bmsg.setBufferId(buff.getBufferId());
			bmsg.setOption(4);// 4 删除
			bmsg.setSourceId(buff.getSource().getId());
			bmsg.setTargetId(buff.getTarget().getId());
			sendBufferChange(bmsg.build());
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

}
