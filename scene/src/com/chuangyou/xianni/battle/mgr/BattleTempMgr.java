package com.chuangyou.xianni.battle.mgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.buffer.LivingStatusTemplateInfo;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.skill.SkillActionMoveTempleteInfo;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.LivingStatusTemplateInfoDao;

public class BattleTempMgr {
	private static Map<Integer, SkillActionTemplateInfo>		skillActionTemps	= new HashMap<Integer, SkillActionTemplateInfo>();

	private static Map<Integer, SkillActionMoveTempleteInfo>	skillActionMoveTemps;

	private static Map<Integer, SkillBufferTemplateInfo>		skillBufferTemps	= new HashMap<Integer, SkillBufferTemplateInfo>();

	private static Map<Integer, SkillTempateInfo>				skillTemps			= new HashMap<Integer, SkillTempateInfo>();

	private static Map<Integer, LivingStatusTemplateInfo>		livingStatusTemps	= new HashMap<Integer, LivingStatusTemplateInfo>();

	public static boolean init() {
		reloadPb();
		return true;
	}

	public static boolean reloadPb() {
		List<SkillActionTemplateInfo> actions = DBManager.getSkillActionTemplateInfoDao().load();
		if (actions != null && actions.size() > 0) {
			for (SkillActionTemplateInfo temp : actions) {
				skillActionTemps.put(temp.getTemplateId(), temp);
			}
		}

		skillActionMoveTemps = DBManager.getSkillActionMoveTemplateInfoDao().load();

		List<SkillBufferTemplateInfo> skillBuffers = DBManager.getSkillBufferTemplateInfoDao().load();
		if (skillBuffers != null && skillBuffers.size() > 0) {
			for (SkillBufferTemplateInfo btemp : skillBuffers) {
				skillBufferTemps.put(btemp.getTemplateId(), btemp);
			}
		}
		// 加载基础技能
		List<SkillTempateInfo> skillTempInfos = DBManager.getSkillTempateInfoDao().load();
		if (skillTempInfos != null && skillTempInfos.size() > 0) {
			for (SkillTempateInfo stemp : skillTempInfos) {
				skillTemps.put(stemp.getTemplateId(), stemp);
			}
		}
		List<LivingStatusTemplateInfo> lstemps = DBManager.getLivingStatusTemplateInfoDao().getAll();
		if (lstemps != null && lstemps.size() > 0) {
			for (LivingStatusTemplateInfo lsinfo : lstemps) {
				livingStatusTemps.put(lsinfo.getId(), lsinfo);
			}
		}
		return true;
	}

	public static SkillActionTemplateInfo getActionInfo(int templateId) {
		if (skillActionTemps.containsKey(templateId)) {
			return skillActionTemps.get(templateId);
		}
		return null;
	}

	public static SkillActionMoveTempleteInfo getActionMoveInfo(int templateId) {
		if (skillActionMoveTemps.containsKey(templateId)) {
			return skillActionMoveTemps.get(templateId);
		}
		return null;
	}

	public static SkillBufferTemplateInfo getBufferInfo(int templateId) {
		if (skillBufferTemps.containsKey(templateId)) {
			return skillBufferTemps.get(templateId);
		}
		return null;
	}

	public static SkillTempateInfo getBSkillInfo(int templateId) {
		if (skillTemps.containsKey(templateId)) {
			return skillTemps.get(templateId);
		}
		return null;
	}

	public static LivingStatusTemplateInfo getLSInfo(int templateId) {
		if (livingStatusTemps.containsKey(templateId)) {
			return livingStatusTemps.get(templateId);
		}
		return null;
	}

}
