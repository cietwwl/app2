package com.chuangyou.xianni.truck.helper;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.truck.RespTruckDataProto.RespTruckData;
import com.chuangyou.common.protobuf.pb.truck.TruckDataProto.TruckData;
import com.chuangyou.common.protobuf.pb.truck.TruckSkillProto.TruckSkill;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.truck.TruckInventory;

public class TruckDataHelper {

	/**
	 * 退出帮派
	 * @param player
	 */
	public static void quitGuildHelper(GamePlayer player)
	{
		if(player == null) return;
		//判断是否在线
		if(player.getPlayerState() == PlayerState.OFFLINE) return;
		player.getTruckInventory().destoryGuildTruckAbout();
		sendTruckData(player);
	}
	
	/**
	 * 加入帮派
	 */
	public static void addGuildHelper(GamePlayer player)
	{
		//先创建镖车
		player.getTruckInventory().createGuildTrucker(GuildManager.getIns().getPlayerGuild(player.getPlayerId()).getGuildInfo().getGuildId());
		//同步镖车数据
		sendTruckData(player);
	}
	
	/**
	 * 同步运镖相关数据
	 * @param player
	 */
	public static void sendTruckData(GamePlayer player)
	{
		RespTruckData.Builder builder = RespTruckData.newBuilder();
		TruckData.Builder pTruckBuilder = TruckDataHelper.getTruckData(player.getTruckInventory().getMyTruckInfo());
		TruckData.Builder truckerBuilder = TruckDataHelper.getTruckData(player.getTruckInventory().getMyTruckInfo());
		//帮派镖车
		TruckInfo guildTruckInfo = player.getTruckInventory().getGuildTruckInfo();
		if(guildTruckInfo == null)
		{
			builder.setHasGuildTruck(0);//不存在帮派
		}
		else
		{
			builder.setHasGuildTruck(1);
		}
		TruckData.Builder gTruckBuilder = TruckDataHelper.getTruckData(guildTruckInfo);
		Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
		for(TruckSkillInfo skillInfo : player.getTruckInventory().getSkills().values())
		{
			TruckSkill.Builder skillBuilder = TruckSkill.newBuilder();
			skillBuilder.setId(skillInfo.getSkillId());
			skillBuilder.setLevel(skillInfo.getLevel());
			skillBuilder.setType(skillInfo.getSkillType());
			if(skillInfo.getType() == TruckSkillInfo.SKILL_TRUCK)
			{
				if(skillInfo.getTrucktype() == TruckInventory.TYPE_P)	//个人镖车技能
				{
					pTruckBuilder.addSkills(skillBuilder);
				}
				else
				{
					gTruckBuilder.addSkills(skillBuilder);
				}
			}
			else if(skillInfo.getType() == TruckSkillInfo.SKILL_TRUCKER)
			{
				//帮派镖车技能
				//未发现帮派忽略掉
				if(skillInfo.getTrucktype() == TruckInventory.TYPE_G && guild == null) continue;
				truckerBuilder.addSkills(skillBuilder);
			}
			else if(skillInfo.getType() == TruckSkillInfo.SKILL_GIFT)
			{
				truckerBuilder.addGifts(skillBuilder);
			}
		}
		builder.setTruckData(pTruckBuilder);
		builder.setTruckerData(truckerBuilder);
		builder.setGuildTruckData(gTruckBuilder);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_DATAS, builder);
		player.sendPbMessage(pkg);
	}
	
	public static TruckData.Builder getTruckData(TruckInfo truckInfo)
	{
		TruckData.Builder truckDataBuilder = TruckData.newBuilder();
		if(truckInfo == null)
		{
			truckDataBuilder.setTruckExp(0);
			truckDataBuilder.setTruckLevel(0);
			truckDataBuilder.setTruckRemainPoint(0);
		}
		else
		{
			truckDataBuilder.setTruckExp(truckInfo.getExp());
			truckDataBuilder.setTruckLevel(truckInfo.getLevel());
			truckDataBuilder.setTruckRemainPoint(truckInfo.getSkillPoint());
		}
		return truckDataBuilder;
	}
	
}
