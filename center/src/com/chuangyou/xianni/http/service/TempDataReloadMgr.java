package com.chuangyou.xianni.http.service;

import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.FilterWordSet;
import com.chuangyou.xianni.BaseServer;
import com.chuangyou.xianni.activity.template.ActivityTemplateMgr;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.artifact.template.ArtifactTemplateMgr;
import com.chuangyou.xianni.avatar.temlate.AvatarTempManager;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.campaign.CampaignTaskTempMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.common.template.PropertyFightingTemplateMgr;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.fashion.template.FashionTemplateMgr;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.inverseBead.template.InverseBeadTemMgr;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.npcDialog.NpcInfoTemplateMgr;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.reward.RewardManager;
import com.chuangyou.xianni.robot.RobotManager;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;
import com.chuangyou.xianni.team.TeamTargetTempMgr;
import com.chuangyou.xianni.truck.TruckTempMgr;
import com.chuangyou.xianni.vip.templete.VipTemplateMgr;

public class TempDataReloadMgr {

	public static boolean reloadData() {
		TemplateDataArr type = null;
		switch (type) {
			case ITEM_TEMPLATE:
				return ItemManager.init();
			case SYSTEM_CONFIG:
				return SystemConfigTemplateMgr.init();
			case LV_UP_TEMP:
				return LevelUpTempleteMgr.init();
			case MONT_LEVEL:
				return MountTemplateMgr.reloadMontLevelCfg();
			case MOUNT_GRADE:
				return MountTemplateMgr.reloadMountGradeCfg();
			case MOUNT_EQUIP:
				return MountTemplateMgr.reloadMountEquipCfg();
			case MOUNT_WEANPON:
				return MountTemplateMgr.reloadMountWeaponCfg();
			case MAGICWP:
				return MagicwpTemplateMgr.reloadMagicwpCfg();
			case MAGICWP_LEVEL:
				return MagicwpTemplateMgr.reloadMagicwpLevelCfg();
			case MAGICWP_GRADE:
				return MagicwpTemplateMgr.reloadMagicwpGradeCfg();
			case MAGICWP_REFINE:
				return MagicwpTemplateMgr.reloadMagicwpRefineCfg();
			case MAGICWP_BAN:
				return MagicwpTemplateMgr.reloadMagicwpBanCfg();
			case MAGICWP_BAN_LEVEL:
				return MagicwpTemplateMgr.reloadMagicwpBanLevelCfg();
			case PET_INFO:
				return PetTemplateMgr.reloadPetInfoCfg();
			case PET_GRADE:
				return PetTemplateMgr.reloadPetGradeCfg();
			case PET_LEVEL:
				return PetTemplateMgr.reloadPetLevelCfg();
			case PET_PHYSIQUE:
				return PetTemplateMgr.reloadPetPhysiqueCfg();
			case PET_QUALITY:
				return PetTemplateMgr.reloadPetQualityCfg();
			case PET_SOUL:
				return PetTemplateMgr.reloadPetSoulCfg();
			case PET_SKILL:
				return PetTemplateMgr.reloadPetSkillInfoCfg();
			case PET_SKILL_LEVEL:
				return PetTemplateMgr.reloadSkillLevelCfg();
			case PET_SKILL_SLOT:
				return PetTemplateMgr.reloadPetSkillSlotCfg();
			case SHOP:
				return ShopTemplateMgr.reloadTemplateData();
			case FIELD_INFO:
				return MapProxyManager.reload();
			case NPC_INFO:
				return NpcInfoTemplateMgr.reloadNpcInfoTemp();
			case TASK_INFO:
				return TaskTemplateMgr.reloadTemplate();
			case FASHION:
				return FashionTemplateMgr.reloadFashionTemps();
			case FASHION_LEVEL:
				return FashionTemplateMgr.reloadLevel();
			case FASHION_QUALITY:
				return FashionTemplateMgr.reloadQualityTemps();
			case CAMPAIGN_TASK:
				return CampaignTaskTempMgr.init();
			case CAMPAIGN_INFO:
				return CampaignTempMgr.reload();
			case SKILL_INFO:
				return SkillTempMgr.reloadPb();
			case SKILL_STAGE:
				return SkillTempMgr.reloadSkillStage();
			case INVERSE_BEAD:
				return InverseBeadTemMgr.init();
			case MONSTER_INFO:
				return MonsterInfoTemplateMgr.reloadMonsterInfoTemp();
			case PROPERTY_FIGHTING:
				return PropertyFightingTemplateMgr.init();
			case TEAM_TARGET:
				return TeamTargetTempMgr.init();
			case EQUIP_GRADE:
				return EquipTemplateMgr.reloadGrade();
			case EQUIP_AWAKEN:
				return EquipTemplateMgr.reloadAwaken();
			case EQUIP_SUIT:
				return EquipTemplateMgr.reloadSuit();
			case ARTIFACT:
				return ArtifactTemplateMgr.reloadArtifactMap();
			case ARTIFACT_LEVELUP:
				return ArtifactTemplateMgr.reloadLevelUpMap();
			case ARTIFACT_GRADEUP:
				return ArtifactTemplateMgr.reloadGradeUpMap();
			case ARTIFACT_SUIT:
				return ArtifactTemplateMgr.reloadSuitList();
			case ARTIFACT_JEWEL_LEVELUP:
				return ArtifactTemplateMgr.reloadJewelLevelupListMap();
			case ARTIFACT_JEWEL_SUIT:
				return ArtifactTemplateMgr.reloadJewelSuitList();
			case STATE_CONDITION:
				return StateTemplateMgr.reloadConditions();
			case STATE_CONFIG:
				return StateTemplateMgr.reloadStates();
			case CONSUMP_SYSTEM:
				return StateTemplateMgr.reloadConsums();
			case FILTER_WORD:
				return FilterWordSet.reloadFilterWord();
			case SOUL_TEMPLATE:
				return SoulTemplateMgr.reloadMap();
			case SOUL_CARDLEVEL:
				return SoulTemplateMgr.reloadLvMap();
			case SOUL_STAR:
				return SoulTemplateMgr.reloadStarMap();
			case SOUL_SKILL:
				return SoulTemplateMgr.reloadSkillMap();
			case SOUL_PROFICIENCY:
				return SoulTemplateMgr.reloadMakeMap();
			case SOUL_FUSESKILL:
				return SoulTemplateMgr.reloadFuseSkillMap();
			case SOUL_FUSEITEM:
				return SoulTemplateMgr.reloadFuseItemConfigMap();
			case SOUL_COMBO:
				return SoulTemplateMgr.reloadCardComboMap();
			case VIP:
				return VipTemplateMgr.reloadVipTemplate();
			case VIP_LEVEL:
				return VipTemplateMgr.reloadVipLevelTemp();
			case VIP_GAG:
				return VipTemplateMgr.reloadPb();
			case ACTIVITY_CONFIG:
				return ActivityTemplateMgr.reloadActivityTemp();
			case GUILD_JOB:
				return GuildTemplateMgr.reloadJobMap();
			case GUILD_JOB_POWER:
				return GuildTemplateMgr.reloadPowerMap();
			case GUILD_DONATE:
				return GuildTemplateMgr.reloadDonateMap();
			case GUILD_WAREHOUSE:
				return GuildTemplateMgr.reloadWarehouseMap();
			case GUILD_SKILL_LEVEL:
				return GuildTemplateMgr.reloadSkillMap();
			case GUILD_SYSTEM:
				return GuildTemplateMgr.reloadGuildList();
			case ROBOT:
				return RobotManager.reload();
			case REWARD:
				return RewardManager.reload();
			case AVATAR_TEMPLATE_INFO:
				return AvatarTempManager.reloadAvatarTemps();
			case AVATAR_STAR_TEMPLATE:
				return AvatarTempManager.reloadAvatarStarTemps();
			case AVATAR_UPGRADE_TEMPLATE:
				return AvatarTempManager.reloadAvatarUpGradeTemps();
			case AVATAR_CORRSPOND_TEMPLATE:
				return AvatarTempManager.reloadAvatarCorrespondTemps();
			case TRUCK_LEVEL:
				return TruckTempMgr.reloadLevelConfig();
			case TRUCK_SKILL:
				return TruckTempMgr.reloadSkillConfig();
			case TRUCK_FUNC:
				return TruckTempMgr.reloadSkillFunc();
		}


		if (!BaseServer.initComponent(ItemManager.init(), "物品模板初始化")) {
			return false;
		}

		if (!BaseServer.initComponent(SystemConfigTemplateMgr.init(), "初始化公共字典配置表")) {
			return false;
		}

		if (!BaseServer.initComponent(LevelUpTempleteMgr.init(), "初始华升级配置表")) {
			return false;
		}

		if (!BaseServer.initComponent(MountTemplateMgr.init(), "初始化玩家坐骑模板数据")) {
			return false;
		}
 
		if (!BaseServer.initComponent(MagicwpTemplateMgr.init(), "初始化法宝模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(PetTemplateMgr.init(), "初始化宠物模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(ShopTemplateMgr.init(), "初始化商店数据")) {
			return false;
		}
		if (!BaseServer.initComponent(MapProxyManager.init(), "初始化地图")) {
			return false;
		}
		if (!BaseServer.initComponent(NpcInfoTemplateMgr.init(), "1111初始化NPC模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(TaskTemplateMgr.init(), "11111初始化任务模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(FashionTemplateMgr.init(), "1111初始化时装模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(CampaignTaskTempMgr.init(), "1111初始化副本任务模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(CampaignTempMgr.init(), "1111初始化副本模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(SkillTempMgr.init(), "1111初始化技能模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(InverseBeadTemMgr.init(), "1111初始化天逆珠模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(MonsterInfoTemplateMgr.init(), "1111初始化怪物模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(PropertyFightingTemplateMgr.init(), "1111初始化属性战斗力配置")) {
			return false;
		}
		if (!BaseServer.initComponent(TeamTargetTempMgr.init(), "1111初始化组队目标表")) {
			return false;
		}

		if (!BaseServer.initComponent(EquipTemplateMgr.init(), "1111寝化装备模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(ArtifactTemplateMgr.init(), "1111神器模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(StateTemplateMgr.init(), "1111境界模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(FilterWordSet.loadFilterWord(Config.getValue("filter_word")), "111初始化敏感字")) {
			return false;
		}

		if (!BaseServer.initComponent(SoulTemplateMgr.init(), "1111初始化魂幡模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(VipTemplateMgr.init(), "1111初始化vip模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(ActivityTemplateMgr.init(), "1111初始化日常活动模板数据")) {
			return false;
		}

		if(!BaseServer.initComponent(GuildTemplateMgr.init(), "1111帮派模板数据")){
			return false;
		}
		
		if (!BaseServer.initComponent(RobotManager.init(), "1111初始化机器模块")) {
			return false;
		}
		if (!BaseServer.initComponent(RewardManager.init(), "1111奖励模块")) {
			return false;
		}
		if (!BaseServer.initComponent(AvatarTempManager.init(), "1111加载分身模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(TruckTempMgr.init(), "初始化镖车模块")) {
			return false;
		}
		if(!BaseServer.initComponent(GuildManager.getIns().init(), "初始化帮派模块")){
			return false;
		}
		
		
		
		
		
		
		return true;
	}

	public static enum TemplateDataArr {
		ITEM_TEMPLATE ( 1001 , "tb_z_item_template" , "物品模板表" ) ,
		SYSTEM_CONFIG ( 1002 , "tb_z_system" , "公共字典配置表" ) ,
		LV_UP_TEMP ( 1003 , "tb_z_level_up" , "升级配置表" ) ,
		MONT_LEVEL ( 1004 , "tb_z_mount_level" , "坐骑等级" ) ,
		MOUNT_GRADE ( 1005 , "tb_z_mount_grade" , "坐骑进阶" ) ,
		MOUNT_EQUIP ( 1006 , "tb_z_mount_grade" , "坐骑装备" ) ,
		MOUNT_WEANPON ( 1007 , "tb_z_mount_weapon" , "坐骑武器" ) ,
		MAGICWP ( 1008 , "tb_z_magicwp" , "法宝" ) ,
		MAGICWP_LEVEL ( 1009 , "tb_z_magicwp_level" , "法宝等级" ) ,
		MAGICWP_GRADE ( 1010 , "tb_z_magicwp_grade" , "法宝进阶" ) ,
		MAGICWP_REFINE ( 1011 , "tb_z_magicwp_refine" , "法宝洗练" ) ,
		MAGICWP_BAN ( 1012 , "tb_z_magicwp_ban" , "法宝禁制" ) ,
		MAGICWP_BAN_LEVEL ( 1013 , "tb_z_magicwp_ban_level" , "法宝禁制等级" ) ,
		PET_INFO ( 1014 , "tb_z_pet_info" , "宠物信息" ) ,
		PET_GRADE ( 1015 , "tb_z_pet_grade" , "宠物阶级" ) ,
		PET_LEVEL ( 1016 , "tb_z_pet_level" , "宠物等级" ) ,
		PET_PHYSIQUE ( 1017 , "tb_z_pet_physique" , "宠物炼体" ) ,
		PET_QUALITY ( 1018 , "tb_z_pet_quality" , "宠物炼体等级" ) ,
		PET_SOUL ( 1019 , "tb_z_pet_soul" , "宠物炼魂" ) ,
		PET_SKILL ( 1020 , "tb_z_pet_skill" , "宠物技能" ) ,
		PET_SKILL_LEVEL ( 1021 , "tb_z_pet_skill_level" , "宠物技能等级" ) ,
		PET_SKILL_SLOT ( 1022 , "tb_z_pet_skill_slot" , "宠物技能格" ) ,
		SHOP ( 1023 , "tb_z_shop_info" , "商店" ) ,
		FIELD_INFO ( 1024 , "tb_z_map_info" , "地图表" ),
		//-----------------------------------------------
		NPC_INFO(1025,"tb_z_npc_info","初始化NPC模板数据"),
		TASK_INFO(1026,"tb_z_task_info","初始化任务模板数据"),
		FASHION(1027,"tb_z_fashion","初始化时装模板数据"),
		FASHION_LEVEL(1028,"tb_z_fashion_level","初始化时装模板数据"),
		FASHION_QUALITY(1029,"tb_z_fashion_quality","初始化时装模板数据"),
		CAMPAIGN_TASK(1030,"tb_z_campaign_task","初始化副本任务模板数据"),
		CAMPAIGN_INFO(1031,"tb_z_campaign_info","初始化副本模板数据"),
		SKILL_INFO(1032,"tb_z_skillinfo","初始化技能模板数据"),
		SKILL_STAGE(1033,"tb_z_skill_stage","初始化技能模板数据"),
		INVERSE_BEAD(1034,"tb_z_inverse_bead","初始化天逆珠模板数据"),
		MONSTER_INFO(1035,"tb_z_monster_info","初始化怪物模板数据"),
		PROPERTY_FIGHTING(1036,"tb_z_property_fighting","初始化属性战斗力配置"),
		TEAM_TARGET(1037,"tb_z_team_target","初始化组队目标表"),
		EQUIP_GRADE(1038,"tb_z_equipbar_grade","寝化装备模板数据"),
		EQUIP_AWAKEN(1039,"tb_z_equip_awaken","寝化装备模板数据"),
		EQUIP_SUIT(1040,"tb_z_equip_suit","寝化装备模板数据"),
		ARTIFACT(1041,"tb_z_artifact","神器模板数据"),
		ARTIFACT_LEVELUP(1042,"tb_z_artifact_levelup","神器模板数据"),
		ARTIFACT_GRADEUP(1043,"tb_z_artifact_gradeup","神器模板数据"),
		ARTIFACT_SUIT(1044,"tb_z_artifact_suit","神器模板数据"),
		ARTIFACT_JEWEL_LEVELUP(1045,"tb_z_artifact_jewel_levelup","神器模板数据"),
		ARTIFACT_JEWEL_SUIT(1046,"tb_z_artifact_jewel_suit","神器模板数据"),
		
		STATE_CONDITION(1047,"tb_z_state_condition","境界模板数据"),
		STATE_CONFIG(1048,"tb_z_state_config","境界模板数据"),
		CONSUMP_SYSTEM(1049,"tb_z_consump_system","境界模板数据"),
		FILTER_WORD(1050,"filter_word","初始化敏感字"),
		
		SOUL_TEMPLATE(1051,"tb_z_soul_template","初始化魂幡模板数据"),
		SOUL_CARDLEVEL(1052,"tb_z_soul_cardLevel","初始化魂幡模板数据"),
		SOUL_STAR(1053,"tb_z_soul_star","初始化魂幡模板数据"),
		SOUL_SKILL(1054,"tb_z_soul_skill","初始化魂幡模板数据"),
		SOUL_PROFICIENCY(1055,"tb_z_soul_proficiency","初始化魂幡模板数据"),
		SOUL_FUSESKILL(1056,"tb_z_soul_fuseSkill","初始化魂幡模板数据"),
		SOUL_FUSEITEM(1057,"tb_z_soul_fuseItem","初始化魂幡模板数据"),
		SOUL_COMBO(1058,"tb_z_soul_combo","初始化魂幡模板数据"),
		VIP(1059,"tb_z_vip","初始化vip模板数据"),
		VIP_LEVEL(1060,"tb_z_vip_level","初始化vip模板数据"),
		VIP_GAG(1061,"tb_z_vip_bag","初始化vip模板数据"),
		ACTIVITY_CONFIG(1062,"tb_z_activity_config","初始化日常活动模板数据"),
		GUILD_JOB(1063,"tb_z_guild_job","帮派模板数据"),
		GUILD_JOB_POWER(1064,"tb_z_guild_job_power","帮派模板数据"),
		GUILD_DONATE(1065,"tb_z_guild_donate","帮派模板数据"),
		GUILD_WAREHOUSE(1066,"tb_z_guild_warehouse","帮派模板数据"),
		GUILD_SKILL_LEVEL(1067,"tb_z_guild_skill_level","帮派模板数据"),
		GUILD_SYSTEM(1068,"tb_z_guild_system","帮派模板数据"),
		ROBOT(1069,"tb_z_robot","初始化机器模块"),
		REWARD(1070,"tb_z_reward","初始化机器模块"),
		AVATAR_TEMPLATE_INFO(1071,"tb_z_avatar_template_info","加载分身模板数据"),
		AVATAR_STAR_TEMPLATE(1072,"tb_z_avatar_star_template","加载分身模板数据"),
		AVATAR_UPGRADE_TEMPLATE(1073,"tb_z_avatar_upgrade_template","加载分身模板数据"),
		AVATAR_CORRSPOND_TEMPLATE(1074,"tb_z_avatar_correspond_template","加载分身模板数据"),
		TRUCK_LEVEL(1075,"tb_z_truck_level","初始化镖车模块"),
		TRUCK_SKILL(1076,"tb_z_truck_skill","初始化镖车模块"),
		TRUCK_FUNC(1077,"tb_z_truck_func","初始化镖车模块"),
		
		
		;
		
		
		
	private int		code;
	private String	tableName;
	private String	des;

	private TemplateDataArr(int code, String tableName, String des) {
		this.code = code;
		this.des = des;
		this.tableName = tableName;
	}

	public static TemplateDataArr get(int code) {
		TemplateDataArr[] enums = TemplateDataArr.values();
		for (TemplateDataArr td : enums) {
			if (td.code == code) {
				return td;
			}
		}
		return null;
	}
	}
}
