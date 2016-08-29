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
		ITEM_TEMPLATE(1001, "物品模板表"), SYSTEM_CONFIG(1002, "公共字典配置表"), LV_UP_TEMP(1003, "升级配置表"), MONT_LEVEL(1004, "坐骑等级"), MOUNT_GRADE(1005, "坐骑进阶"), MOUNT_EQUIP(1006, "坐骑装备"), MOUNT_WEANPON(1007,
				"坐骑武器"),;
	private int		code;
	private String	des;

	private TemplateDataArr(int code, String des) {
		this.code = code;
		this.des = des;
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
