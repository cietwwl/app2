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
import com.chuangyou.common.protobuf.pb.battle.BufferMsgProto.BufferMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageListMsgProtocol.DamageListMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.xianni.battle.action.HeroPollingAction;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.mount.MountTempleteMgr;
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

	@Override
	public boolean onDie(Living source) {
		if (super.onDie(source)) {
			ArmyProxy army = WorldMgr.getArmy(getArmyId());
			if (army == null) {
				Log.error("not find army when player die,playerId :" + getArmyId());
				return true;
			}
			if (revivaling == false) {
				RevivalPlayerAction revival = new RevivalPlayerAction(army);
				ThreadManager.actionExecutor.enDelayQueue(revival);
				revivaling = true;
			}
		}
		

		if (source.getBattleMode() == BattleModeCode.warBattleMode && this.getBattleMode() == BattleModeCode.peaceBattleMode) {// 增加pk值
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
			updateProperty(source.getArmyId(), properties);
		}

		List<PropertyMsg> properties = new ArrayList<>();
		Map<Integer, Long> changeMap = new HashMap<Integer, Long>();

		int changePkVal = 0;// 减少pk值
		long exp = 0;// 损失经验
		if (getColour(this.getPkVal()) == BattleModeCode.yellow) {
			changePkVal = MathUtils.randomClamp(10, 20);
			exp = getSimpleInfo().getExp() * (getPkVal() / 500000);
		} else if (getColour(this.getPkVal()) == BattleModeCode.red) {
			changePkVal = MathUtils.randomClamp(40, 80);
			exp = getSimpleInfo().getExp() * (getPkVal() / 100000);
		}

		if (changePkVal > 0) {
			changePkVal = this.getPkVal() - changePkVal < 0 ? 0 : this.getPkVal() - changePkVal;
			this.setPkVal(changePkVal);
			PropertyMsg.Builder p = PropertyMsg.newBuilder();
			p.setBasePoint(changePkVal);
			p.setTotalPoint(changePkVal);
			p.setType(EnumAttr.PK_VAL.getValue());
			properties.add(p.build());
			changeMap.put(EnumAttr.PK_VAL.getValue(), (long) this.getPkVal());
		}
		if (exp > 0) {
			long nowExp = getSimpleInfo().getExp() - exp < 0 ? 0 : getSimpleInfo().getExp() - exp;
			PropertyMsg.Builder p = PropertyMsg.newBuilder();
			p.setBasePoint(nowExp);
			p.setTotalPoint(nowExp);
			p.setType(EnumAttr.Exp.getValue());
			properties.add(p.build());
			changeMap.put(EnumAttr.Exp.getValue(), (long) (exp * -1));
		}
		if (changeMap.size() > 0)
			notifyCenter(changeMap, this.getArmyId());
		if (properties.size() > 0)
			updateProperty(this.getArmyId(), properties);
		
		
		return true;
	}

	/**
	 * 获取颜色级别
	 * 
	 * @param val
	 * @return
	 */
	public int getColour(int val) {
		if (val >= 1000) {
			return BattleModeCode.red;
		} else if (val > 0) {
			return BattleModeCode.yellow;
		}
		return BattleModeCode.white;
	}

	// /**
	// * 通知
	// *
	// * @param changeMap
	// * @param playerId
	// * @return
	// */
	// public void notifyCenter(Map<Integer, Long> changeMap, long playerId) {
	// PlayerAttUpdateMsg.Builder msg = PlayerAttUpdateMsg.newBuilder();
	// msg.setPlayerId(playerId);
	// for (int type : changeMap.keySet()) {
	// PropertyMsg.Builder attMsg = PropertyMsg.newBuilder();
	// attMsg.setType(type);
	// attMsg.setTotalPoint(changeMap.get(type));
	// msg.addAtt(attMsg);
	// }
	// PBMessage pkg = MessageUtil.buildMessage(Protocol.C_PLAYER_UPDATA_PRO, msg);
	// GatewayLinkedSet.send2Server(pkg);
	// }

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

	// @Override
	// public PlayerAttSnapMsg.Builder getAttSnapMsg() {
	// // TODO Auto-generated method stub
	// if (cacheAttSnapPacker == null) {
	// cacheAttSnapPacker = PlayerAttSnapMsg.newBuilder();
	// cacheAttSnapPacker.setPlayerId(id);
	// }
	// cacheAttSnapPacker.setType(getType());
	// cacheAttSnapPacker.setSkinId(getSkin());
	// cacheAttSnapPacker.setPostion(Vector3BuilderHelper.build(getPostion()));
	// cacheAttSnapPacker.setTarget(Vector3BuilderHelper.build(getTargetPostion()));
	// if(simpleInfo != null){
	// cacheAttSnapPacker.setMountId(simpleInfo.getMountId());
	// }
	// cacheAttSnapPacker.setMountState(getMountState());
	// return cacheAttSnapPacker;
	// }

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

}
