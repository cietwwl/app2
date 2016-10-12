package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillAddPointProto.ReqTruckSkillAddPoint;
import com.chuangyou.common.protobuf.pb.truck.RespTruckSkillAddPointProto.RespTruckSkillAddPoint;
import com.chuangyou.common.protobuf.pb.truck.TruckSkillProto.TruckSkill;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.truck.TruckInventory;
import com.chuangyou.xianni.truck.TruckTempMgr;

@Cmd(code = Protocol.C_TRUCK_REQTRUCKSKILLADDPOINT, desc = "技能升级")
public class ReqTruckSkillAddPointCmd extends AbstractCommand {
	
	private static final int SUC = 1;		//成功
	private static final int UNKONWN = 2;		//未知错误
	private static final int POINTUNENOUGH = 3;	//技能点不够
	private static final int GUILDNOTFOUND = 4;	//未找到帮派
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqTruckSkillAddPoint skillAddPoint = ReqTruckSkillAddPoint.parseFrom(packet.getBytes());
		int remain = 0;
		TruckSkillConfig skillConfig = TruckTempMgr.getAllSkillConfig().get(skillAddPoint.getSkillId());
		if(skillConfig == null)
		{
			Log.error("SkillConfig is Null - " + skillAddPoint.getSkillId());
			respSkillAddPoint(player, UNKONWN, skillAddPoint.getSkilltype(), remain, null, null);
			return;
		}
		TruckSkillConfig nextSkillConfig = TruckTempMgr.getAllSkillConfig().get(skillConfig.getNextSkillId());
		switch (skillAddPoint.getSkilltype()) {
		case TruckSkillConfig.PERSONAL:	//个人镖车
			TruckInfo myTruck = player.getTruckInventory().getMyTruckInfo();
			remain = myTruck.getSkillPoint();
			if(myTruck.getSkillPoint() >= skillConfig.getNeedPoints())
			{
				remain = myTruck.getSkillPoint() - skillConfig.getNeedPoints();
				respSkillAddPoint(player, SUC, TruckSkillConfig.PERSONAL, remain, skillConfig, nextSkillConfig);
				myTruck.setSkillPoint(remain);
				myTruck.setOp(Option.Update);
				//更新技能
				updateSkillInfo(player, skillConfig, nextSkillConfig, TruckSkillInfo.SKILL_TRUCK, TruckInventory.TYPE_P);
			}
			else
			{
				Log.error("SkillPoint not enough");
				respSkillAddPoint(player, POINTUNENOUGH, TruckSkillConfig.PERSONAL, remain, skillConfig, null);
			}
			break;
		case TruckSkillConfig.GUILD:	//帮派镖车
			TruckInfo guildTruckInfo = player.getTruckInventory().getGuildTruckInfo();
			if(guildTruckInfo != null)
			{
				remain = guildTruckInfo.getSkillPoint();
				if(guildTruckInfo.getSkillPoint() >= skillConfig.getNeedPoints())
				{
					remain = guildTruckInfo.getSkillPoint() - skillConfig.getNeedPoints();
					respSkillAddPoint(player, SUC, TruckSkillConfig.GUILD, remain, skillConfig, nextSkillConfig);
					guildTruckInfo.setSkillPoint(remain);
					guildTruckInfo.setOp(Option.Update);
					//更新技能
					updateSkillInfo(player, skillConfig, nextSkillConfig, TruckSkillInfo.SKILL_TRUCK, TruckInventory.TYPE_G);
				}
				else
				{
					Log.error("SkillPoint not enough");
					respSkillAddPoint(player, POINTUNENOUGH, TruckSkillConfig.GUILD, remain, skillConfig, null);
				}
			}
			else
			{
				Log.error("guildTruckInfo is null");
				respSkillAddPoint(player, GUILDNOTFOUND, TruckSkillConfig.GUILD, remain, skillConfig, null);
			}
			break;
		case TruckSkillConfig.TRUCKER:	//镖师
			TruckInfo trucker = player.getTruckInventory().getTruckerInfo();
			remain = trucker.getSkillPoint();
			if(trucker.getSkillPoint() >= skillConfig.getNeedPoints())
			{
				remain = trucker.getSkillPoint() - skillConfig.getNeedPoints();
				respSkillAddPoint(player, SUC, TruckSkillConfig.TRUCKER, remain, skillConfig, nextSkillConfig);
				trucker.setSkillPoint(remain);
				trucker.setOp(Option.Update);
				//更新技能
				updateSkillInfo(player, skillConfig, nextSkillConfig, TruckSkillInfo.SKILL_TRUCKER, TruckInventory.TYPE_P);
			}
			else
			{
				Log.error("SkillPoint not enough");
				respSkillAddPoint(player, POINTUNENOUGH, TruckSkillConfig.TRUCKER, remain, skillConfig, null);
			}
			break;
		}
	}

	/**
	 * 回应技能升级
	 */
	private void respSkillAddPoint(GamePlayer player, int isSuc, int type, int remain, TruckSkillConfig oldSkill, TruckSkillConfig newSkill)
	{
		RespTruckSkillAddPoint.Builder resp = RespTruckSkillAddPoint.newBuilder();
		resp.setAddPointState(isSuc);
		resp.setSkilltype(type);
		resp.setRemainPoint(remain);
		if(oldSkill == null)
			resp.setOldskillId(0);
		else
			resp.setOldskillId(oldSkill.getId());
		if(newSkill == null)
			resp.setNewskillId(0);
		else
			resp.setNewskillId(newSkill.getId());
		resp.setType(oldSkill.getSkillType());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TRUCK_SKILLADDPOINT, resp);
		player.sendPbMessage(pkg);
	}

	/**
	 * 更新技能
	 * @param player
	 * @param skillConfig
	 * @param nextSkillConfig
	 */
	private void updateSkillInfo(GamePlayer player, TruckSkillConfig skillConfig, TruckSkillConfig nextSkillConfig, int type, int trucktype)
	{
		TruckSkillInfo skillInfo = player.getTruckInventory().getSkills().get(skillConfig.getSkillType());
		if(skillInfo == null)
		{
			if(trucktype == TruckInventory.TYPE_P)		//个人镖车
			{
				skillInfo = getSkillInfo(player.getPlayerId(), skillConfig.getLevel()+1, skillConfig.getSkillType(), type, nextSkillConfig.getId(), trucktype);
			}
			else					//帮派镖车
			{
				Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
				if(guild != null)
				{
					skillInfo = getSkillInfo(guild.getGuildInfo().getGuildId(), skillConfig.getLevel()+1, skillConfig.getSkillType(), type, nextSkillConfig.getId(), trucktype);
				}
			}
		}
		else
		{
			skillInfo.setSkillId(nextSkillConfig.getId());
			skillInfo.setLevel(nextSkillConfig.getLevel());
		}
		player.getTruckInventory().addSkill(skillInfo);
	}
	
	/**
	 * 创建一个镖车技能
	 * @param ownerId
	 * @param level
	 * @param skillType
	 * @param type
	 * @return
	 */
	private TruckSkillInfo getSkillInfo(long ownerId, int level, int skillType, int type, int skillId, int trucktype)
	{
		TruckSkillInfo skillInfo = new TruckSkillInfo();
		skillInfo.setLevel(level);
		skillInfo.setOwnerId(ownerId);
		skillInfo.setTrucktype(trucktype);
		skillInfo.setSkillType(skillType);
		skillInfo.setType(type);
		skillInfo.setSkillId(skillId);
		return skillInfo;
	}
}
