package com.chuangyou.xianni.http.service;

import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.FilterWordSet;
import com.chuangyou.xianni.BaseServer;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.campaign.CampaignTaskTempMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.common.template.PropertyFightingTemplateMgr;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.fashion.template.FashionTemplateMgr;
import com.chuangyou.xianni.inverseBead.template.InverseBeadTemMgr;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.npcDialog.NpcInfoTemplateMgr;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;
import com.chuangyou.xianni.team.TeamTargetTempMgr;
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
		}

		if (!BaseServer.initComponent(NpcInfoTemplateMgr.init(), "初始化NPC模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(TaskTemplateMgr.init(), "初始化任务模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(FashionTemplateMgr.init(), "初始化时装模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(CampaignTaskTempMgr.init(), "初始化副本任务模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(CampaignTempMgr.init(), "初始化副本模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(SkillTempMgr.init(), "初始化技能模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(InverseBeadTemMgr.init(), "初始化天逆珠模板数据")) {
			return false;
		}
		if (!BaseServer.initComponent(MonsterInfoTemplateMgr.init(), "初始化怪物模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(PropertyFightingTemplateMgr.init(), "初始化属性战斗力配置")) {
			return false;
		}
		if (!BaseServer.initComponent(TeamTargetTempMgr.init(), "初始化组队目标表")) {
			return false;
		}

		if (!BaseServer.initComponent(EquipTemplateMgr.init(), "进化装备模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(FilterWordSet.loadFilterWord(Config.getValue("filter_word")), "初始化敏感字")) {
			return false;
		}

		if (!BaseServer.initComponent(SoulTemplateMgr.init(), "初始化魂幡模板数据")) {
			return false;
		}

		if (!BaseServer.initComponent(VipTemplateMgr.init(), "初始化vip模板数据")) {
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
		FIELD_INFO ( 1024 , "tb_z_map_info" , "地图表" ),;
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
