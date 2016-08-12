package com.chuangyou.xianni.entity.player;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

/**
 * 玩家详细信息
 *
 */
public class PlayerInfo extends DataObject {

	/** 角色ID */
	private long playerId;

	/** 用户ID */
	private long userId;

	/** 职业 */
	private int job;

	/** 角色名字 */
	private String nickName;

	/** 等级 */
	private int level;

	/** 当前经验 */
	private long exp;

	/** 总经验 */
	private long totalExp;

	/** 金币(灵石) */
	private long money;

	/** 绑定元宝(绑定仙玉) */
	private int bindCash;

	/** 元宝(仙玉) */
	private int cash;

	/** VIP等级 */
	private short vipLevel;

	/** 战斗力 */
	private int fight;

	/** 皮肤 */
	private int skinId;

	/** 角色背包数量 */
	private int pBagCount;

	/** 出战坐骑ID */
	private int mountId;

	/** 出战法宝ID */
	private int magicWeaponId;
	/** 技能达到的阶段 **/
	private int skillStage;
	/** 修为 **/
	private int repair;
	/** 战斗模式 1和平模式2对战模式3门派模式 */
	private int battleMode;
	/** pk 值 **/
	private int pkVal;
	/** 战斗模式更新时间 **/
	private long changeBattleModeTime;

	/** 时装ID */
	private int fashionId = 0;

	/** 武器ID */
	private int weaponId = 0;

	/** 翅膀ID */
	private int wingId = 0;
	/**
	 * 积分
	 **/
	private int points = 0;
	/**
	 * vip 到期时间
	 */
	private Date vipTimeLimit;
	/**
	 * 临时vip 到期时间
	 */
	private Date vipInterimTimeLimit;
	/**
	 * vip 经验
	 */
	private int vipExp;
	/**
	 * vip 领取记录
	 */
	private String vipReceiveRecording;
	
	/**
	 * 装备经验
	 */
	private long		equipExp	= 0;

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
		setOp(Option.Update);

	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		if (this.exp != exp) {
			setOp(Option.Update);
		}
		this.exp = exp;

	}

	public long getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(long totalExp) {
		if (this.totalExp != totalExp) {
			setOp(Option.Update);
		}
		this.totalExp = totalExp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if (this.level != level) {
			setOp(Option.Update);
		}
		this.level = level;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		if (this.money != money) {
			setOp(Option.Update);
		}
		this.money = money;
	}

	public int getBindCash() {
		return bindCash;
	}

	public void setBindCash(int bindCash) {
		if (this.bindCash != bindCash) {
			setOp(Option.Update);
		}
		this.bindCash = bindCash;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		if (this.cash != cash) {
			setOp(Option.Update);
		}
		this.cash = cash;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(short vipLevel) {
		if (this.vipLevel != vipLevel) {
			setOp(Option.Update);
		}
		this.vipLevel = vipLevel;
	}

	public int getFight() {
		return fight;
	}

	public void setFight(int fight) {
		if (this.fight != fight) {
			setOp(Option.Update);
		}
		this.fight = fight;
	}

	public int getSkinId() {
		return skinId;
	}

	public void setSkinId(int skinId) {
		if (this.skinId != skinId) {
			setOp(Option.Update);
		}
		this.skinId = skinId;
	}

	public int getpBagCount() {
		return pBagCount;
	}

	public void setpBagCount(int pBagCount) {
		if (this.pBagCount != pBagCount) {
			setOp(Option.Update);
		}
		this.pBagCount = pBagCount;
	}

	public int getMountId() {
		return mountId;
	}

	public void setMountId(int mountId) {
		if (this.mountId != mountId) {
			setOp(Option.Update);
		}
		this.mountId = mountId;
	}

	public int getMagicWeaponId() {
		return magicWeaponId;
	}

	public void setMagicWeaponId(int magicWeaponId) {
		if (this.magicWeaponId != magicWeaponId) {
			setOp(Option.Update);
		}
		this.magicWeaponId = magicWeaponId;
	}

	public int getSkillStage() {
		return skillStage;
	}

	public void setSkillStage(int skillStage) {
		if (this.skillStage != skillStage) {
			setOp(Option.Update);
		}
		this.skillStage = skillStage;
	}

	public int getRepair() {
		return repair;
	}

	public void setRepair(int repair) {
		if (this.repair != repair) {
			setOp(Option.Update);
		}
		this.repair = repair;
	}

	public int getBattleMode() {
		return battleMode;
	}

	public void setBattleMode(int battleMode) {
		if (this.battleMode != battleMode) {
			setOp(Option.Update);
		}
		this.battleMode = battleMode;
	}

	public int getPkVal() {
		return pkVal;
	}

	public void setPkVal(int pkVal) {
		if (this.pkVal != pkVal) {
			setOp(Option.Update);
		}
		this.pkVal = pkVal;
	}

	public long getChangeBattleModeTime() {
		return changeBattleModeTime;
	}

	public void setChangeBattleModeTime(long changeBattleModeTime) {
		this.changeBattleModeTime = changeBattleModeTime;
	}

	public int getFashionId() {
		return fashionId;
	}

	public void setFashionId(int fashionId) {
		if (this.fashionId != fashionId) {
			setOp(Option.Update);
		}
		this.fashionId = fashionId;
	}

	public int getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(int weaponId) {
		if (this.weaponId != weaponId) {
			setOp(Option.Update);
		}
		this.weaponId = weaponId;
	}

	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		if (this.wingId != wingId) {
			setOp(Option.Update);
		}
		this.wingId = wingId;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		if (this.points != points) {
			setOp(Option.Update);
			this.points = points;
		}
	}


	public Date getVipTimeLimit() {
		return vipTimeLimit;
	}

	public void setVipTimeLimit(Date vipTimeLimit) {
		setOp(Option.Update);
		this.vipTimeLimit = vipTimeLimit;
	}

	public Date getVipInterimTimeLimit() {
		return vipInterimTimeLimit;
	}

	public void setVipInterimTimeLimit(Date vipInterimTimeLimit) {
		setOp(Option.Update);
		this.vipInterimTimeLimit = vipInterimTimeLimit;
	}

	public int getVipExp() {
		return vipExp;
	}

	public void setVipExp(int vipExp) {
		setOp(Option.Update);
		this.vipExp = vipExp;
	}
	
	public String getVipReceiveRecording() {
		return vipReceiveRecording;
	}

	public void setVipReceiveRecording(String vipReceiveRecording) {
		setOp(Option.Update);
		this.vipReceiveRecording = vipReceiveRecording;
	}


	public long getEquipExp() {
		return equipExp;
	}

	public void setEquipExp(long equipExp) {
		if(this.equipExp != equipExp){
			setOp(Option.Update);
			this.equipExp = equipExp;
		}
	}


	/**
	 * 写玩家属性消息包
	 * 
	 * @param proto
	 * @param bagInitCount 背包初始大小配置
	 */
	public void writeProto(PlayerInfoMsg.Builder proto, int bagInitCount) {
		proto.setPlayerId(this.getPlayerId());
		proto.setUserId(this.getUserId());
		proto.setJob(this.getJob());
		proto.setNickName(this.getNickName());
		proto.setLevel(this.getLevel());
		proto.setExp(this.getExp());
		proto.setToalExp(this.getTotalExp());
		proto.setMoney(this.getMoney());
		proto.setBindCash(this.getBindCash());
		proto.setCash(this.getCash());
		proto.setVipLevel(this.getVipLevel());
		proto.setFight(this.getFight());
		proto.setSkinId(this.getSkinId());
		proto.setMountId(this.getMountId());
		proto.setMagicWeaponId(this.getMagicWeaponId());
		proto.setRepair(this.getRepair());
		proto.setFashionId(this.getFashionId());
		proto.setWeaponId(this.getWeaponId());
		proto.setWingId(this.getWingId());
		proto.setPoints(this.getPoints());
		proto.setEquipExp(this.getEquipExp());
		proto.setPBagCount(bagInitCount + this.getpBagCount());
	}

}
