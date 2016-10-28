package com.chuangyou.xianni.role.objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.PlayerAttSnapProto.PlayerAttSnapMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg.Builder;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.entity.truck.TruckCheckPointConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.truck.action.GuildTruckDelayCount;
import com.chuangyou.xianni.truck.action.TruckDelayAutoDestory;
import com.chuangyou.xianni.truck.helper.RobHelper;
import com.chuangyou.xianni.truck.helper.TruckBillHelper;
import com.chuangyou.xianni.truck.objects.CheckPointMaterial;

/**
 * 镖车
 * @author wkghost
 *
 */
public class Truck extends ActiveLiving {

	/**
	 * 个人
	 */
	public static final int TRUCK_P = 1;
	/**
	 * 帮派
	 */
	public static final int TRUCK_G = 2;
	
	//镖车状态，等待
	public static final int TRUCK_STATE_WAIT = 1;
	//镖车状态，运镖中
	public static final int TRUCK_STATE_RUN = 2;
	//镖车状态，到达终点
	public static final int TRUCK_STATE_ARRIALFINAL = 3;
	//镖车状态，镖车超时
	public static final int TRUCK_STATE_TIMEOUT	= 5;
	
	/** 镖车技能 */
	private Map<Integer, List<TruckSkillInfo>> skills;
	/** 技能次数 */
	private Map<Integer, Integer> truckSkillCount;
	/** 护镖人的时长 */
	private Map<Long, Integer> protectorTimer;
	
	/**
	 * 护镖人
	 */
	private Set<Long> protectors;
	public Set<Long> getProtectors() {
		return protectors;
	}

	public Set<Long> getRobbers() {
		return robbers;
	}

	/**
	 * 劫镖者
	 */
	private Set<Long> robbers;
	/**
	 * 镖车类型
	 */
	private int trucktype;
	public int getTrucktype() {
		return trucktype;
	}

	/**
	 * 目标路点的ID
	 */
	private int targetCheckPoint;
	/**
	 * 镖车状态
	 */
	private int truckState;
	/**
	 * 检查点的物质
	 */
	private Map<Integer, CheckPointMaterial> checkPointMaterials;
	/**
	 * 被结果镖
	 */
	private boolean robbed;
	/**
	 * 暗标时间戳
	 */
	private long hideTimestamp = 0;
	/**
	 * 暗标市场
	 */
	private int hideTimer = 0;
	
	public int getTruckState() {
		return truckState;
	}

	public int getTargetCheckPoint() {
		return targetCheckPoint;
	}

	public void setNextCheckPoint(int nextCheckPoint) {
		this.targetCheckPoint = nextCheckPoint;
	}

	public Truck(long playerId, long id, int trucktype) {
		super(playerId, id);
		truckState = TRUCK_STATE_WAIT;
		robbed = false;
		this.trucktype = trucktype;
		if(this.trucktype == TRUCK_P)
		{
			this.enDelayQueue(new TruckDelayAutoDestory(this, 60 * 60 * 1000));	//帮派1小时超时
			//this.enDelayQueue(new TruckDelayAutoDestory(this, 10 * 1000));	//帮派10s超时
		}
		else
		{
			this.enDelayQueue(new TruckDelayAutoDestory(this, 2 * 60 * 60 * 1000)); //帮派2小时超时
			this.enDelayQueue(new GuildTruckDelayCount(this, 1 * 1000));
		}
		protectors = new HashSet<Long>();
		robbers = new HashSet<Long>();
		protectorTimer = new HashMap<Long, Integer>();
		setType(RoleType.truck);
		TruckRelationshipMgr.setRelation(playerId, id);	//设置关系
		TruckRelationshipMgr.setOwnership(playerId, id);//设置从属
		TruckMgr.addTruck(this);
	}
	
	public void createCheckPoint()
	{
		Iterator<Entry<Integer, TruckCheckPointConfig>> it = TruckTempMgr.getCheckPoints().entrySet().iterator();
		checkPointMaterials = new HashMap<Integer, CheckPointMaterial>();
		while(it.hasNext())
		{
			Entry<Integer, TruckCheckPointConfig> entry = it.next();
			if(entry.getValue().getPointType() == TruckTempMgr.FRIST || entry.getValue().getPointType() == TruckTempMgr.LAST) continue;
			CheckPointMaterial mat = new CheckPointMaterial();
			mat.setId(entry.getValue().getId());
			int baseRemian = 0;
			int basePrice = 0;
			if(this.trucktype == 1)
			{
				baseRemian = entry.getValue().getIndividualNum();
				basePrice = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.Price");
			}
			else
			{
				baseRemian = entry.getValue().getFactionNum();
				basePrice = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.Price");
			}
			mat.setRemain(baseRemian + TruckBillHelper.getAddMatPlus(this, baseRemian));
			mat.setPrice(TruckBillHelper.getMatPrice(this, basePrice));
			checkPointMaterials.put(mat.getId(), mat);
		}
	}
	
	/**
	 * 获取场景对象的快照信息
	 * 
	 * @return
	 */
	@Override
	public PlayerAttSnapMsg.Builder getAttSnapMsg() {
		if (cacheAttSnapPacker == null)
			cacheAttSnapPacker = PlayerAttSnapMsg.newBuilder();
		cacheAttSnapPacker.setPlayerId(id);
		cacheAttSnapPacker.setType(getType());
		cacheAttSnapPacker.setSkinId(getTrucktype());
		cacheAttSnapPacker.setPostion(Vector3BuilderHelper.build(getPostion()));
		cacheAttSnapPacker.setTarget(Vector3BuilderHelper.build(getTargetPostion()));
		cacheAttSnapPacker.setOwnerId(getArmyId());
		return cacheAttSnapPacker;
	}

	/**
	 * 获取详情
	 */
	@Override
	public Builder getBattlePlayerInfoMsg() {
		cachBattleInfoPacket = BattleLivingInfoMsg.newBuilder();
		cachBattleInfoPacket.setLivingId(getId());
		cachBattleInfoPacket.setPlayerId(getArmyId());
		cachBattleInfoPacket.setNickName(simpleInfo.getNickName());
		cachBattleInfoPacket.setType(getType());
		cachBattleInfoPacket.setSkinId(getTrucktype());	//镖车类型
		cachBattleInfoPacket.setLiveState(getTruckState());	//镖车状态
		cachBattleInfoPacket.setLevel(simpleInfo.getLevel());//等级
		cachBattleInfoPacket.setVipLevel(getTruckState());//当前状态
		//当前检查点
		PropertyMsg.Builder pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.Exp.getValue());
		pmsg.setTotalPoint(simpleInfo.getExp());
		cachBattleInfoPacket.addPropertis(pmsg);
		//劫镖进度
		pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.WOOD.getValue());
		pmsg.setTotalPoint(getProperty(EnumAttr.WOOD.getValue()));
		cachBattleInfoPacket.addPropertis(pmsg);
		//物资
		pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.METAL.getValue());
		pmsg.setTotalPoint(getProperty(EnumAttr.METAL.getValue()));
		cachBattleInfoPacket.addPropertis(pmsg);
		
		pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.WATER.getValue());
		pmsg.setTotalPoint(getProperty(EnumAttr.WATER.getValue()));
		cachBattleInfoPacket.addPropertis(pmsg);
		
		pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.SPEED.getValue());
		pmsg.setTotalPoint(getProperty(EnumAttr.SPEED.getValue()));
		cachBattleInfoPacket.addPropertis(pmsg);
		
		if (getPostion() != null) {
			cachBattleInfoPacket.setPostion(Vector3BuilderHelper.build(getPostion()));
		} else {
			cachBattleInfoPacket.setPostion(Vector3BuilderHelper.build(Vector3.Invalid));
		}
		return cachBattleInfoPacket;
	}
	
	/**
	 * 添加一个护镖者
	 * @param id
	 */
	public void addProtector(long id)
	{
		synchronized (protectors) {
			protectors.add(id);
		}
	}
	
	/**
	 * 清楚护镖者
	 */
	public void clearProtector()
	{
		synchronized (protectors) {
			protectors.clear();
		}
	}
	
	/**
	 * 移除一个护镖者
	 * @param id
	 */
	public void removeProtector(long id)
	{
		synchronized (protectors) {
			protectors.remove(id);
		}
	}
	
	/**
	 * 添加一个劫镖者
	 * @param id
	 */
	public void addRobber(long id)
	{
		synchronized (robbers) {
			robbers.add(id);
		}
	}
	
	/**
	 * 清除劫镖者
	 */
	public void clearRobber()
	{
		synchronized (robbers) {
			robbers.clear();
		}
	}
	
	/**
	 * 移除一个劫镖者
	 * @param id
	 */
	public void removeRobber(long id)
	{
		synchronized (robbers) {
			robbers.remove(id);
		}
	}
	
	/**
	 *  开始起运
	 * 	每个检查点开始起运
	 */
	public void startRun(int next)
	{
		truckState = TRUCK_STATE_RUN;
		targetCheckPoint = next;
		simpleInfo.setExp(targetCheckPoint);
	}
	
	/**
	 * 到底检查点
	 */
	public void arrialCheckPoint()
	{
		truckState = TRUCK_STATE_WAIT;
	}
	
	/**
	 * 镖车送到目的地
	 */
	public void arrialGoal()
	{
		truckState = TRUCK_STATE_ARRIALFINAL;
	}
	
	/**
	 * 是否在破坏保护中
	 * @return
	 */
	public boolean isBrokenProtect()
	{
		return getProperty(EnumAttr.METAL.getValue()) < SystemConfigTemplateMgr.getIntValue("EscortSupplies.ProtectedMaterial");
	}
	
	/**
	 * 获取当前目标检查点的物质剩余数
	 * @return
	 */
	public int getTargetCheckPointMatRemain()
	{
		if(checkPointMaterials.containsKey(targetCheckPoint))
			return checkPointMaterials.get(targetCheckPoint).getRemain();
		return 0;
	}
	
	/**
	 * 获取当前目标检查点的物质的单价
	 * @return
	 */
	public int getTargetCheckPointMatPrice()
	{
		if(checkPointMaterials.containsKey(targetCheckPoint))
			return checkPointMaterials.get(targetCheckPoint).getPrice();
		return 0;
	}
	
	/**
	 * 扣除检查点的可购买的物质
	 * @param checkpointID
	 * @param cut
	 */
	public void cutCheckPointMatRemain(int checkpointID, int cut)
	{
		if(checkPointMaterials.containsKey(targetCheckPoint))
		{
			CheckPointMaterial mat = checkPointMaterials.get(checkpointID);
			mat.setRemain(mat.getRemain() - cut);
		}
	}
	
	/**
	 * 更新暗标时间戳
	 */
	public void updateHideTimestramp(long timestramp, int hideTimer)
	{
		this.hideTimestamp = timestramp;
		this.hideTimer = hideTimer;
	}
	
	/**
	 * 在暗标中
	 */
	public boolean hasHiding()
	{
		return (System.currentTimeMillis() - this.hideTimestamp) < this.hideTimer;
	}
	
	/**
	 * 掉落物质
	 */
	public void dropMatrials(long relationId)
	{
		if(isBrokenProtect()) return;
		int cutRob = 0;
		if(relationId == getId())
		{
			if(getTrucktype() == TRUCK_P)
			{
				cutRob = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.LeaderKill");
			}
			else
			{
				cutRob = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.LeaderKill");
			}
		}
		else
		{
			//死亡人是护镖者
			if(protectors.contains(relationId))
			{
				if(getTrucktype() == TRUCK_P)
				{
					cutRob = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.GuardKill");
				}
				else
				{
					cutRob = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.GuardKill");
				}
			}
		}
		RobHelper.robByKill(this, cutRob);
	}
	
	/**
	 * 更新护镖者的护镖时长
	 */
	public void updateProtectorTimer(long id, int timer)
	{
		if(!protectorTimer.containsKey(id))
		{
			protectorTimer.put(id, 0);
		}
		protectorTimer.put(id, protectorTimer.get(id) + timer);
	}
	
	@Override
	public void destory() {
		// TODO Auto-generated method stub
		protectors.clear();
		protectors = null;
		robbers.clear();
		robbers = null;
		super.destory();
	}

	public Map<Integer, List<TruckSkillInfo>> getSkills() {
		return skills;
	}

	public void setSkills(Map<Integer, List<TruckSkillInfo>> skills) {
		this.skills = skills;
	}

	public Map<Integer, Integer> getTruckSkillCount() {
		return truckSkillCount;
	}

	public void setTruckSkillCount(Map<Integer, Integer> truckSkillCount) {
		this.truckSkillCount = truckSkillCount;
	}

	public boolean isRobbed() {
		return robbed;
	}

	public void setRobbed(boolean robbed) {
		this.robbed = robbed;
	}

	public Map<Long, Integer> getProtectorTimer() {
		return protectorTimer;
	}

	
}
