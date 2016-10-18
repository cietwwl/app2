package com.chuangyou.xianni.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerSimpleInfoMsgProto.PlayerSimpleInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.army.Hero;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.GuildConstant;
import com.chuangyou.xianni.constant.RobotConstant;
import com.chuangyou.xianni.entity.avatar.AvatarInfo;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.entity.role.RoleConfig;
import com.chuangyou.xianni.login.template.RoleConfigMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class RobotManager {
	public static Map<Integer, RobotTemplate>						robots				= new HashMap<>();

	public static Map<Integer, List<RobotTemplate>>					fightMappingRobots	= new HashMap<>();

	/** 系统帮派机器人 */
	public static Map<Integer, Map<Integer, List<RobotTemplate>>>	guildRobots			= new HashMap<>();

	public static boolean init() {
		return reload();
	}

	public static Set<RobotTemplate> getRandomRobots(int maxFloat, int minFloat, int size, Set<Integer> existsed) {
		Set<Integer> keys = fightMappingRobots.keySet();
		Set<Integer> choose = new HashSet<>();
		if (keys != null && keys.size() != 0) {
			for (Integer key : keys) {
				if (key <= maxFloat && key >= minFloat) {
					choose.add(key);
				}
			}
		}
		List<RobotTemplate> chooseRobots = new ArrayList<>();
		for (Integer key : choose) {
			chooseRobots.addAll(fightMappingRobots.get(key));
		}
		Set<RobotTemplate> result = new HashSet<>();
		if (chooseRobots.size() <= size) {
			result.addAll(chooseRobots);
			existsed.addAll(choose);
			return result;
		}
		Random random = new Random();
		int count = chooseRobots.size();
		for (int i = 0; i < count && size > 0; i++) {
			RobotTemplate r = chooseRobots.get(random.nextInt(chooseRobots.size()));
			if (existsed.contains(r.getId())) {
				continue;
			}
			result.add(r);
			existsed.add(r.getId());
			chooseRobots.remove(r);
			size--;
		}
		return result;
	}

	public static boolean reload() {
		List<RobotTemplate> robosList = DBManager.getRobotTemplateDao().getAll();
		for (RobotTemplate robot : robosList) {
			robots.put(robot.getId(), robot);

			if (robot.getType() == RobotConstant.Type.PLAYER_ROBOT) {
				List<RobotTemplate> teamFight = fightMappingRobots.get(robot.getFluctuate());
				if (teamFight == null) {
					teamFight = new ArrayList<>();
					fightMappingRobots.put(robot.getFluctuate(), teamFight);
				}
				teamFight.add(robot);
			}

			if (robot.getType() == RobotConstant.Type.STATIC_ROBOT && robot.getGuildId() > 0 && robot.getGuildJob() > 0) {
				Map<Integer, List<RobotTemplate>> guildJobRobots = guildRobots.get(robot.getGuildId());
				if (guildJobRobots == null) {
					guildJobRobots = new HashMap<>();
					guildRobots.put(robot.getGuildId(), guildJobRobots);
				}
				List<RobotTemplate> jobRobotList = guildJobRobots.get(robot.getGuildJob());
				if (jobRobotList == null) {
					jobRobotList = new ArrayList<>();
					guildJobRobots.put(robot.getGuildJob(), jobRobotList);
				}
				jobRobotList.add(robot);
			}
		}
		return true;
	}

	public static RobotTemplate getRobotTemp(int tempId) {
		int id = Integer.valueOf(tempId);
		return robots.get(id);
	}

	/**
	 * 获取帮派机器人
	 * 
	 * @param guildId
	 * @param guildJob
	 * @return
	 */
	public static RobotTemplate getGuildLeaderTemp(int guildId) {
		Map<Integer, List<RobotTemplate>> jobRobotMap = guildRobots.get(guildId);
		if (jobRobotMap == null) {
			return null;
		}
		List<RobotTemplate> jobRobotList = jobRobotMap.get(GuildConstant.GuildJob.LEADER);
		if (jobRobotList == null || jobRobotList.size() <= 0) {
			return null;
		}
		return jobRobotList.get(0);
	}

	/**
	 * 获取职业列表
	 * 
	 * @param guildId
	 * @param job
	 * @return
	 */
	public static List<RobotTemplate> getGuildJobRobots(int guildId, int job) {
		Map<Integer, List<RobotTemplate>> jobRobotMap = guildRobots.get(guildId);
		if (jobRobotMap == null) {
			return null;
		}
		List<RobotTemplate> jobRobotList = jobRobotMap.get(GuildConstant.GuildJob.LEADER);
		if (jobRobotList == null || jobRobotList.size() <= 0) {
			return null;
		}
		return jobRobotList;
	}

	/**
	 * 获取指定帮派的机器人
	 * 
	 * @param guildId
	 * @return
	 */
	public static Map<Integer, List<RobotTemplate>> getGuildRobots(int guildId) {
		return guildRobots.get(guildId);
	}

	public static boolean isRobot(long robotId) {
		return robotId >= 10001 && robotId <= 20001;
	}

	public static void writeSimpInfo(PlayerSimpleInfoMsg.Builder simpleInfo, long robotId, int fight) {
		RobotTemplate temp = getRobotTemp((int) robotId);
		if (temp == null) {
			return;
		}
		RoleConfig roleConfig = RoleConfigMgr.getRoleConfig(temp.getJob());
		if (roleConfig == null) {
			Log.error("the robot job is erro,that not find the roleConfig" + temp.getId() + " --- the job is " + temp.getJob());
			return;
		}
		simpleInfo.setPlayerId(robotId);
		simpleInfo.setFight(fight + fight * temp.getFluctuate() / 100);
		simpleInfo.setJob(temp.getJob());
		simpleInfo.setNickName(temp.getNickName());
		simpleInfo.setLevel(temp.getLevel());
		simpleInfo.setSkinId(roleConfig.getModelId());
		simpleInfo.setMagicWeaponId(temp.getMagicWeaponId());
		simpleInfo.setFashionId(temp.getFashionId());
		simpleInfo.setWeaponId(temp.getWeaponId());
		simpleInfo.setWingId(temp.getWingId());
	}

	public static void writeRobotData(GamePlayer player, long robotId, RobotInfoMsg.Builder robotInfo) {
		RobotTemplate temp = getRobotTemp((int) robotId);
		if (temp == null) {
			return;
		}
		if (player.getArmyInventory() == null) {
			return;
		}
		if (temp.getType() == RobotConstant.Type.PLAYER_ROBOT) {
			Hero baseTemp = player.getArmyInventory().getHero();

			RoleConfig roleConfig = RoleConfigMgr.getRoleConfig(temp.getJob());
			if (roleConfig == null) {
				Log.error("the robot job is erro,that not find the roleConfig" + temp.getId() + " --- the job is " + temp.getJob());
				return;
			}

			PlayerInfoMsg.Builder simpleInfo = PlayerInfoMsg.newBuilder();
			simpleInfo.setPlayerId(temp.getId());
			simpleInfo.setJob(temp.getJob());
			simpleInfo.setNickName(temp.getNickName());
			simpleInfo.setLevel(temp.getLevel());
			simpleInfo.setSkinId(roleConfig.getModelId());
			simpleInfo.setMagicWeaponId(temp.getMagicWeaponId());
			simpleInfo.setFashionId(temp.getFashionId());
			simpleInfo.setWeaponId(temp.getWeaponId());
			simpleInfo.setWingId(temp.getWingId());
			robotInfo.setSimpInfo(simpleInfo);

			PropertyListMsg.Builder propertyMsgs = PropertyListMsg.newBuilder();
			writePropertyProto(baseTemp, propertyMsgs, temp);
			robotInfo.setPropertis(propertyMsgs);

			String skills = roleConfig.getSkill();
			writeSkillProto(skills, robotInfo);
		} else if (temp.getType() == RobotConstant.Type.STATIC_ROBOT) {

			PlayerInfoMsg.Builder simpleInfo = PlayerInfoMsg.newBuilder();
			simpleInfo.setPlayerId(temp.getId());
			simpleInfo.setNickName(temp.getNickName());
			simpleInfo.setLevel(temp.getLevel());
			simpleInfo.setSkinId(temp.getModeId());
			simpleInfo.setMagicWeaponId(temp.getMagicWeaponId());
			simpleInfo.setFashionId(temp.getFashionId());
			simpleInfo.setWeaponId(temp.getWeaponId());
			simpleInfo.setWingId(temp.getWingId());
			robotInfo.setSimpInfo(simpleInfo);

			PropertyListMsg.Builder protpertyMsgs = PropertyListMsg.newBuilder();
			writePropertyProto(null, protpertyMsgs, temp);
			robotInfo.setPropertis(protpertyMsgs);

			String skills = temp.getSkillIds();
			writeSkillProto(skills, robotInfo);
		}
		// 所有机器人，都需要搭配分身，分身为对应挑战玩家分身
		if (player.getAvatarInventory() != null) {
			List<AvatarInfo> avatars = player.getAvatarInventory().getFinghtingInfos();
			for (AvatarInfo avatar : avatars) {
				robotInfo.addAvatarInfos(player.getAvatarInventory().writeProto(avatar));
			}
		}
	}

	private static void writePropertyProto(Hero hero, PropertyListMsg.Builder propertyMsgs, RobotTemplate temp) {
		EnumAttr[] attrs = { EnumAttr.BLOOD, EnumAttr.SOUL, EnumAttr.ATTACK, EnumAttr.DEFENCE, EnumAttr.SOUL_ATTACK, EnumAttr.SOUL_DEFENCE, EnumAttr.ACCURATE, EnumAttr.DODGE, EnumAttr.CRIT,
				EnumAttr.CRIT_DEFENCE, EnumAttr.SPEED };

		int type = temp.getType();
		for (EnumAttr attr : attrs) {
			long totalPoint = 0;
			if (type == RobotConstant.Type.PLAYER_ROBOT) {
				int value = hero.getTotalProperty(attr.getValue() - 1);
				totalPoint = value + value * temp.getValue(attr) / 100;
			} else if (type == RobotConstant.Type.STATIC_ROBOT) {
				totalPoint = temp.getValue(attr);
			}

			PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
			proMsg.setType(attr.getValue());
			proMsg.setTotalPoint(totalPoint);
			propertyMsgs.addPropertys(proMsg);

			if (attr == EnumAttr.BLOOD) {
				PropertyMsg.Builder cur_blood = PropertyMsg.newBuilder();
				cur_blood.setType(EnumAttr.CUR_BLOOD.getValue());
				cur_blood.setTotalPoint(totalPoint);
				propertyMsgs.addPropertys(cur_blood);
			}

			if (attr == EnumAttr.SOUL) {
				PropertyMsg.Builder cur_soul = PropertyMsg.newBuilder();
				cur_soul.setType(EnumAttr.CUR_SOUL.getValue());
				cur_soul.setTotalPoint(totalPoint);
				propertyMsgs.addPropertys(cur_soul);
			}
		}
	}

	private static void writeSkillProto(String skills, RobotInfoMsg.Builder robotInfo) {
		if (skills == null || skills.equals("0"))
			return;
		String[] skillAttr = skills.split(",");
		for (String strId : skillAttr) {
			if (strId == null || strId.equals("0"))
				continue;
			robotInfo.addBattleSkills(Integer.valueOf(strId));
		}
	}
}
