package com.chuangyou.xianni.entity.player;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.xianni.entity.DataObject;

/**
 * 玩家详细信息
 *
 */
public class PlayerInfo extends DataObject {

	/** 角色ID */
	private long playerId;

	/** 用户ID */
	private long userId;

	/** 角色名字 */
	private String nickName;

	/** 等级 */
	private int level;

	/** 当前经验 */
	private long exp;

	/** 总经验 */
	private long totalExp;

	/** 金币(灵石) */
	private int money;

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

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public long getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(long totalExp) {
		this.totalExp = totalExp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getBindCash() {
		return bindCash;
	}

	public void setBindCash(int bindCash) {
		this.bindCash = bindCash;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(short vipLevel) {
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

	public int getpBagCount() {
		return pBagCount;
	}

	public void setpBagCount(int pBagCount) {
		this.pBagCount = pBagCount;
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

	public int getSkillStage() {
		return skillStage;
	}

	public void setSkillStage(int skillStage) {
		this.skillStage = skillStage;
	}

	public int getRepair() {
		return repair;
	}

	public void setRepair(int repair) {
		this.repair = repair;
	}

	/**
	 * 写玩家属性消息包
	 * 
	 * @param proto
	 * @param bagInitCount
	 *            背包初始大小配置
	 */
	public void writeProto(PlayerInfoMsg.Builder proto, int bagInitCount) {
		proto.setPlayerId(this.getPlayerId());
		proto.setUserId(this.getUserId());
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
		proto.setRepair(this.repair);
		proto.setFashionId(0);
		proto.setWeaponId(2110001);
		proto.setWingId(0);
		proto.setPBagCount(bagInitCount + this.getpBagCount());
	}
}
