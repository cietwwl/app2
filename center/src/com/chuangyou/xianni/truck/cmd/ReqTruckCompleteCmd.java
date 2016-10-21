package com.chuangyou.xianni.truck.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete;
import com.chuangyou.common.protobuf.pb.truck.RespTruckRewardProto.RespTruckReward;
import com.chuangyou.common.protobuf.pb.truck.TruckRewardProto.TruckReward;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.email.vo.EmailItemVo;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.reward.RewardTemplate;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.reward.RewardManager;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckInventory;
import com.chuangyou.xianni.truck.TruckRewardType;
import com.chuangyou.xianni.truck.helper.LevelUpHelper;
import com.chuangyou.xianni.truck.helper.TruckBillHelper;

@Cmd(code = Protocol.C_TRUCK_REQTRUCKCOMPLETE, desc = "运镖结束")
public class ReqTruckCompleteCmd extends AbstractCommand {

	/** 成功完成 */
	private static final int STATE_SUC = 1;
	/** 超时 */
	private static final int STATE_TIMEOUT = 2;
	
	/** 镖头 */
	public static final int LEADER = 1;
	/** 镖师 */
	public static final int MEMBER = 2;
	/** 帮派成员 */
	public static final int GUILDMEMBER = 3;
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqTruckComplete truckCompleteMsg = InnerReqTruckComplete.parseFrom(packet.getBytes());
		switch (truckCompleteMsg.getTruckertype()) {
		case LEADER:
			truckerLeader(player, truckCompleteMsg);
			break;
		case MEMBER:
			truckerMember(player, truckCompleteMsg);
			break;
		case GUILDMEMBER:
			guildMember(player, truckCompleteMsg);
			break;
		}
	}
	
	/**
	 * 镖头结算
	 */
	private void truckerLeader(GamePlayer player, InnerReqTruckComplete truckCompleteMsg)
	{
		int addExp = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.GuardExp");	//运镖者获取的经验
		int leaveMaterials = truckCompleteMsg.getMat();
		Map<Integer, List<TruckSkillConfig>> skills = player.getTruckInventory().getSkillInfos();
		//每次运镖可额外获得镖师经验
		int ext_exp = TruckBillHelper.fixedAdd(skills.get(TruckBillHelper.TRUCK_EXT_EXP));
		addExp += ext_exp;
		//个人额外的奖励表
		List<Integer> myExReward = new ArrayList<Integer>();
		if(truckCompleteMsg.getState() == STATE_SUC)
		{
			if(truckCompleteMsg.getRobbed() == 1)	//被劫镖
			{
				//被劫镖获得经验补偿
				int offsetExp = TruckBillHelper.fixedAdd(skills.get(TruckBillHelper.TRUCK_BROKEN_OFFSET));
				addExp += offsetExp;
			}
			else
			{
				//未被成功劫镖，结算时获得外经验
				int extExp = TruckBillHelper.fixedAdd(skills.get(TruckBillHelper.TRUCK_UNBROKEN_EXP));
				addExp += extExp;
				//未被成功劫镖，结算时获得外奖励
				myExReward = TruckBillHelper.getValueCollection(skills.get(TruckBillHelper.TRUCK_UNBROKEN_REWARD));
			}
			//完成后额外获得的经验奖励
			addExp += TruckBillHelper.fixedAdd(skills.get(TruckBillHelper.TRUCK_UNBROKEN_EXP));
		}
		else if(truckCompleteMsg.getState() == STATE_TIMEOUT)
		{
			//超时折扣
			float failRate = 1- (SystemConfigTemplateMgr.getIntValue("EscortSupplies.FailRatio") * 0.01f); 	//失败打折比例
			addExp *= failRate;
			leaveMaterials *= failRate;
		}
		//镖头发奖
		rewardHandler(player,  truckCompleteMsg.getTrucktype(), truckCompleteMsg.getState(), leaveMaterials, addExp, Arrays.asList(TruckRewardType.REWARD_P));
		//镖头发放额外的奖励
		extRewardHandler(player,  truckCompleteMsg.getTrucktype(), truckCompleteMsg.getState(), leaveMaterials, myExReward);
	}
	
	/**
	 * 镖师结算
	 */
	private void truckerMember(GamePlayer player, InnerReqTruckComplete truckCompleteMsg)
	{
		int addExp = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.GuardExp2");	//护镖者获取的经验
		int leaveMaterials = truckCompleteMsg.getMat();
		if(truckCompleteMsg.getState() == STATE_TIMEOUT)
		{
			float failRate = 1- (SystemConfigTemplateMgr.getIntValue("EscortSupplies.FailRatio") * 0.01f); 	//失败打折比例
			addExp *= failRate;
			leaveMaterials *= failRate;
		}
		Map<Integer, List<TruckSkillConfig>> skills = player.getTruckInventory().getSkillInfos();
		//判断领奖次数
		int protectCount = player.getBasePlayer().getPlayerTimeInfo().getPersonalTruckerProtCount();
		if(protectCount >= SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.GuardExp2Num")) return;
		protectCount++;
		player.getBasePlayer().getPlayerTimeInfo().setChallengeCampCount(protectCount);
		//----------------------------------------------------------------------
		Map<Integer, List<TruckSkillConfig>> protectorSkills = player.getTruckInventory().getSkillInfos();
		//为他人护镖获得额外经验 - 每天只限制一次
		if(player.getBasePlayer().getPlayerTimeInfo().getPresonalTruckerExtExp() == 0)
		{
			addExp += TruckBillHelper.fixedAdd(skills.get(TruckBillHelper.TRUCK_PROTECTOR_EXT_EXP));
			player.getBasePlayer().getPlayerTimeInfo().setPresonalTruckerExtExp(player.getBasePlayer().getPlayerTimeInfo().getPresonalTruckerExtExp()+1);
		}
		//镖师奖励
		rewardHandler(player,  truckCompleteMsg.getTrucktype(), truckCompleteMsg.getState(), leaveMaterials, addExp, Arrays.asList(TruckRewardType.REWARD_PROTECED));
		//发放额外的奖励 - 每天只限制一次
		if(player.getBasePlayer().getPlayerTimeInfo().getPresonalTruckerExtReward() == 0)
		{
			extRewardHandler(player,  truckCompleteMsg.getTrucktype(), truckCompleteMsg.getState(), leaveMaterials, TruckBillHelper.getValueCollection(protectorSkills.get(TruckBillHelper.TRUCK_PROTECTOR_EXT_REWARD)));
			player.getBasePlayer().getPlayerTimeInfo().setPresonalTruckerExtReward(player.getBasePlayer().getPlayerTimeInfo().getPresonalTruckerExtReward()+1);
		}
	}
	
	/**
	 * 帮派镖师结算
	 */
	private void guildMember(GamePlayer player, InnerReqTruckComplete truckCompleteMsg)
	{
		int addExp = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.GuardExp");	//运镖者获取的经验
		int leaveMaterials = truckCompleteMsg.getMat();
		if(truckCompleteMsg.getState() == STATE_TIMEOUT)
		{
			//超时折扣
			float failRate = 1- (SystemConfigTemplateMgr.getIntValue("EscortSupplies.FailRatio") * 0.01f); 	//失败打折比例
			addExp *= failRate;
			leaveMaterials *= failRate;
		}
		//镖师发奖
		rewardHandler(player,  truckCompleteMsg.getTrucktype(), truckCompleteMsg.getState(), leaveMaterials, addExp, Arrays.asList(TruckRewardType.REWARD_G_P, TruckRewardType.REWARD_G_G));
	}

	/**
	 * 
	 * @param player		领奖人
	 * @param trucktype		镖车类型
	 * @param state			结算状态 成功 / 失败
	 * @param leaveMat 		剩余物资
	 * @param addExp		增加的经验
	 * @param rewardTypes	奖励类型列表
	 */
	private void rewardHandler(GamePlayer player, int trucktype, int state, int leaveMat, int addExp, List<Integer> rewardTypes)
	{
		RespTruckReward.Builder builder = RespTruckReward.newBuilder();
		builder.setState(state);
		builder.setTrucktype(trucktype);
		Map<Integer, Reward> rewardMaps = new HashMap<Integer, ReqTruckCompleteCmd.Reward>();
		for(int i = 0; i<rewardTypes.size(); i++)
		{
			List<Reward> rewards = getReward(rewardTypes.get(i), leaveMat, state);
			for(int j = 0; j<rewards.size(); j++)
			{
				if(!rewardMaps.containsKey(rewards.get(j).itemtype))
				{
					rewardMaps.put(rewards.get(j).itemtype, new Reward(rewards.get(j).itemtype, rewards.get(j).count, leaveMat, state));
				}
				else
				{
					rewardMaps.get(rewards.get(j).itemtype).count += rewards.get(j).count;
				}
			}
		}
		//经验
		if(trucktype == TruckInventory.TYPE_P)
		{
			Reward truckExp = new Reward(SystemConfigTemplateMgr.getIntValue("EscortSupplies.Exp.EscortCar.Individual"), addExp, leaveMat, state);
			builder.addRewards(truckExp.createReward(player));
			Reward truckerExp = new Reward(SystemConfigTemplateMgr.getIntValue("EscortSupplies.Exp.BiaoShi"), addExp, leaveMat, state);
			builder.addRewards(truckerExp.createReward(player));
		}
		else if(trucktype == TruckInventory.TYPE_G)
		{
			Reward guildTruckExp = new Reward(SystemConfigTemplateMgr.getIntValue("EscortSupplies.Exp.EscortCar.Faction"), addExp, leaveMat, state);
			builder.addRewards(guildTruckExp.createReward(player));
		}
		for(Reward reward : rewardMaps.values())
		{
			builder.addRewards(reward.createReward(player));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_REWARD, builder);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 额外的奖励
	 * @param player		领奖人
	 * @param trucktype		镖车类型
	 * @param state			结算状态 成功 / 失败
	 * @param leaveMat		剩余物资
	 * @param rewardTypes	奖励类型列表
	 */
	private void extRewardHandler(GamePlayer player, int trucktype, int state, int leaveMat, List<Integer> rewardTypes)
	{
		Map<Integer, Reward> rewardMaps = new HashMap<Integer, ReqTruckCompleteCmd.Reward>();
		for(int i = 0; i<rewardTypes.size(); i++)
		{
			List<Reward> rewards = getReward(rewardTypes.get(i), leaveMat, state);
			for(int j = 0; j<rewards.size(); j++)
			{
				if(!rewardMaps.containsKey(rewards.get(j).itemtype))
				{
					rewardMaps.put(rewards.get(j).itemtype, new Reward(rewards.get(j).itemtype, rewards.get(j).count, leaveMat, state));
				}
				else
				{
					rewardMaps.get(rewards.get(j).itemtype).count += rewards.get(j).count;
				}
			}
		}
		if(rewardMaps.size() > 0)
		{
			List<EmailItemVo> items = new ArrayList<EmailItemVo>();
			for(Reward reward : rewardMaps.values())
			{
				EmailItemVo item = new EmailItemVo(reward.itemtype, reward.count, BindType.BIND);
				items.add(item);
			}
			EmailManager.insertEmail(player.getPlayerId(), "护镖奖励", "护镖额外奖励", items);
		}
	}
	
	private List<Reward> getReward(int rewardType, int leaveMat, int state)
	{
		List<Reward> rewards = new ArrayList<Reward>();
		RewardTemplate rewardTemp = RewardManager.rewardTemps.get(rewardType).get(0);
		if(rewardTemp.getItemTempId1() != 0)
			rewards.add(new Reward(rewardTemp.getItemTempId1(), rewardTemp.getCount1(), leaveMat, state));
		if(rewardTemp.getItemTempId2() != 0)
			rewards.add(new Reward(rewardTemp.getItemTempId2(), rewardTemp.getCount2(), leaveMat, state));
		if(rewardTemp.getItemTempId3() != 0)
			rewards.add(new Reward(rewardTemp.getItemTempId3(), rewardTemp.getCount3(), leaveMat, state));
		if(rewardTemp.getItemTempId4() != 0)
			rewards.add(new Reward(rewardTemp.getItemTempId4(), rewardTemp.getCount4(), leaveMat, state));
		if(rewardTemp.getItemTempId5() != 0)
			rewards.add(new Reward(rewardTemp.getItemTempId5(), rewardTemp.getCount5(), leaveMat, state));
		return rewards;
	}
	
	/**
	 * 奖励
	 *
	 */
	class Reward
	{
		/** 物品类型 */
		public int itemtype = 0;
		/** 数量 */
		public int count = 0;
		/** 剩余物资 */
		public int leaveMat = 0;
		/** 结算状态 */
		public int state = 0;
		
		public Reward(int itemtype, int count, int leaveMat, int state)
		{
			this.itemtype = itemtype;
			this.count = count;
			this.leaveMat = leaveMat;
			this.state = state;
		}
		
		public TruckReward.Builder createReward(GamePlayer player)
		{
			if(itemtype == SystemConfigTemplateMgr.getIntValue("EscortSupplies.Exp.BiaoShi"))
			{
				LevelUpHelper.levelUp(player, TruckInfo.TRUCKER, count, state, LevelUpHelper.RESULT);
			}
			else if(itemtype == SystemConfigTemplateMgr.getIntValue("EscortSupplies.Exp.EscortCar.Individual"))
			{
				LevelUpHelper.levelUp(player, TruckInfo.PERSONAL_TRUCK, count, state, LevelUpHelper.RESULT);
			}
			else if(itemtype == SystemConfigTemplateMgr.getIntValue("EscortSupplies.Exp.EscortCar.Faction"))
			{
				LevelUpHelper.levelUp(player, TruckInfo.GUILD_TRUCK, count, state, LevelUpHelper.RESULT);
			}
			else
			{
				player.getBagInventory().addItem(itemtype, count * leaveMat, ItemAddType.TRUCK_REWARD, true);
			}
			TruckReward.Builder builder = TruckReward.newBuilder();
			builder.setItemtype(itemtype);
			builder.setCount(count * leaveMat);
			return builder;
		}
	}
	
	
}
