package com.chuangyou.xianni.world;

import java.util.List;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerGuildInfoProto.PlayerGuildInfoMsg;
import com.chuangyou.xianni.constant.EnumAttr;

/**
 * 玩家信息
 */
public class SimplePlayerInfo {
	private long	playerId;		// 角色ID
	private long	userId;			// 用户ID
	private String	nickName;		// 用户昵称
	private int		level;			// 等级
	private long	exp;			// 当前经验
	private long	toalExp;		// 总经验g
	private long	money;			// 金币
	private int		bindCash;		// 绑定仙玉
	private int		vipLevel;		// VIP等级
	private int		fight;			// 战斗力
	private int		skinId;			// 皮肤
	private int		fashionId;		// 时装ID
	private int		weaponId;		// 武器ID
	private int		mountId;		// 坐骑ID
	private int		magicWeaponId;	// 法宝ID
	private int		wingId;			// 翅膀ID
	// private int battleMode; // 战斗模式
	// private int pkVal; // pk值
	private int		weaponAwaken;	// 武器觉醒等级
	private int		stateLv;		// 境界值

	private int		guildId;		// 所在帮派ID
	private String	guildName;		// 所在帮派名字
	private int		guildJob;		// 在帮派里的职位

	public void writeProto(PlayerInfoMsg.Builder proto) {
		proto.setPlayerId(this.getPlayerId());
		proto.setUserId(this.getUserId());
		proto.setNickName(this.getNickName());
		proto.setLevel(this.getLevel());
		proto.setExp(this.getExp());
		proto.setToalExp(this.getToalExp());
		proto.setMoney(this.getMoney());
		proto.setBindCash(this.getBindCash());
		proto.setVipLevel(this.getVipLevel());
		proto.setFight(this.getFight());
		proto.setSkinId(this.getSkinId());
		proto.setFashionId(this.getFashionId());
		proto.setWeaponId(this.getWeaponId());
		proto.setMountId(this.getMountId());
		proto.setMagicWeaponId(this.getMagicWeaponId());
		proto.setWingId(this.getWingId());
		proto.setWeaponAwaken(this.getWeaponAwaken());
		proto.setGuildId(this.getGuildId());
		proto.setGuildName(this.getGuildName());
		proto.setGuildJob(this.getGuildJob());
		proto.setStateLv(this.getStateLv());

	}

	public void readProto(PlayerInfoMsg proto) {
		this.setPlayerId(proto.getPlayerId());
		this.setUserId(proto.getUserId());
		this.setNickName(proto.getNickName());
		this.setLevel(proto.getLevel());
		this.setExp(proto.getExp());
		this.setToalExp(proto.getToalExp());
		this.setMoney(proto.getMoney());
		this.setBindCash(proto.getBindCash());
		this.setVipLevel(proto.getVipLevel());
		this.setFight(proto.getFight());
		this.setSkinId(proto.getSkinId());
		this.setFashionId(proto.getFashionId());
		this.setWeaponId(proto.getWeaponId());
		this.setMountId(proto.getMountId());
		this.setMagicWeaponId(proto.getMagicWeaponId());
		this.setWingId(proto.getWingId());
		this.setWeaponAwaken(proto.getWeaponAwaken());
		this.setGuildId(proto.getGuildId());
		this.setGuildName(proto.getGuildName());
		this.setGuildJob(proto.getGuildJob());
		this.setStateLv(proto.getStateLv());
	}

	public void writeGuildProto(PlayerGuildInfoMsg.Builder proto) {
		proto.setPlayerId(this.getPlayerId());
		proto.setGuildId(this.getGuildId());
		proto.setGuildName(this.getGuildName());
		proto.setGuildJob(this.getGuildJob());
	}

	public void readGuildProto(PlayerGuildInfoMsg proto) {
		this.setGuildId(proto.getGuildId());
		this.setGuildName(proto.getGuildName());
		this.setGuildJob(proto.getGuildJob());
	}

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
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public long getToalExp() {
		return toalExp;
	}

	public void setToalExp(long toalExp) {
		this.toalExp = toalExp;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public int getBindCash() {
		return bindCash;
	}

	public void setBindCash(int bindCash) {
		this.bindCash = bindCash;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	public int getFight() {
		return fight;
	}

	public void setFight(int fight) {
		this.fight = fight;
	}

	public int getSkinId() {
		return skinId;
	}

	public void setSkinId(int skinId) {
		this.skinId = skinId;
	}

	public int getFashionId() {
		return fashionId;
	}

	public void setFashionId(int fashionId) {
		this.fashionId = fashionId;
	}

	public int getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}

	public int getMountId() {
		return mountId;
	}

	public void setMountId(int mountId) {
		this.mountId = mountId;
	}

	public int getMagicWeaponId() {
		return magicWeaponId;
	}

	public void setMagicWeaponId(int magicWeaponId) {
		this.magicWeaponId = magicWeaponId;
	}

	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		this.wingId = wingId;
	}

	public int getWeaponAwaken() {
		return weaponAwaken;
	}

	public void setWeaponAwaken(int weaponAwaken) {
		this.weaponAwaken = weaponAwaken;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public int getGuildJob() {
		return guildJob;
	}

	public void setGuildJob(int guildJob) {
		this.guildJob = guildJob;
	}

	public void readProperty(List<PropertyMsg> properties) {
		for (PropertyMsg p : properties) {
			EnumAttr attr = EnumAttr.getEnumAttrByValue(p.getType());
			setProperty(attr, p.getTotalPoint());
		}
	}

	public void setProperty(EnumAttr attr, long value) {
		if (value < 0) {
			value = 0;
		}
		switch (attr) {
			case Level:
				this.setLevel((int) value);
				break;
			case VipLevel:
				this.setVipLevel((int) value);
				break;
			case FightValue:
				this.setFight((int) value);
				break;
			case Clothes:
				this.setFashionId((int) value);
				break;
			case Weapon:
				this.setWeaponId((int) value);
				break;
			case Mount:
				this.setMountId((int) value);
				break;
			case FaBao:
				this.setMagicWeaponId((int) value);
				break;
			case BeiShi:
				this.setWingId((int) value);
				break;
			case WEAPON_AWAKEN:
				this.setWeaponAwaken((int) value);
				break;
			case State:
				this.setStateLv((int) value);
				break;
			default:
				break;
		}
	}

	public int getStateLv() {
		return stateLv;
	}

	public void setStateLv(int stateLv) {
		this.stateLv = stateLv;
	}

}
