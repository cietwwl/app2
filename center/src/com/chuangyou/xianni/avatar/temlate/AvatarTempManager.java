package com.chuangyou.xianni.avatar.temlate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.avatar.AvatarCorrespondTemplateInfo;
import com.chuangyou.xianni.entity.avatar.AvatarStarTemplate;
import com.chuangyou.xianni.entity.avatar.AvatarTemplateInfo;
import com.chuangyou.xianni.entity.avatar.AvatarUpGradeTemplate;
import com.chuangyou.xianni.sql.dao.DBManager;

public class AvatarTempManager {
	/** 分身模板 */
	public static Map<Integer, AvatarTemplateInfo>							avatarTemps				= new HashMap<>();
	/** 分身升星模板 */
	public static Map<Integer, Map<Integer, AvatarStarTemplate>>			avatarStarTemps			= new HashMap<>();
	/** 分身升级模板 */
	public static Map<Integer, Map<Integer, AvatarUpGradeTemplate>>			avatarUpGradeTemps		= new HashMap<>();
	/** 分身默契模板 */
	public static Map<Integer, Map<Integer, AvatarCorrespondTemplateInfo>>	avatarCorrespondTemps	= new HashMap<>();

	public static boolean init() {
		// 加载模板数据
		List<AvatarTemplateInfo> at_all = DBManager.getAvatarTemplateInfoDao().getAll();
		for (AvatarTemplateInfo at : at_all) {
			avatarTemps.put(at.getId(), at);
		}
		// 加载升星模板
		List<AvatarStarTemplate> ats_all = DBManager.getAvatarStarTemplateDao().getAll();
		for (AvatarStarTemplate at : ats_all) {
			Map<Integer, AvatarStarTemplate> sigleStarTemps = avatarStarTemps.get(at.getAvatarTempId());
			if (sigleStarTemps == null) {
				sigleStarTemps = new HashMap<>();
				avatarStarTemps.put(at.getAvatarTempId(), sigleStarTemps);
			}
			sigleStarTemps.put(at.getLevel(), at);
		}
		// 加载升级模板
		List<AvatarUpGradeTemplate> aupg_all = DBManager.getAvatarUpGradeTemplateDao().getAll();
		for (AvatarUpGradeTemplate aupg : aupg_all) {
			Map<Integer, AvatarUpGradeTemplate> singleUpgTemps = avatarUpGradeTemps.get(aupg.getAvatarTempId());
			if (singleUpgTemps == null) {
				singleUpgTemps = new HashMap<>();
				avatarUpGradeTemps.put(aupg.getAvatarTempId(), singleUpgTemps);
			}
			singleUpgTemps.put(aupg.getLevel(), aupg);
		}
		// 分身默契模板
		List<AvatarCorrespondTemplateInfo> act_all = DBManager.getAvatarCorrespondTemplateInfoDao().getAll();
		for (AvatarCorrespondTemplateInfo act : act_all) {
			Map<Integer, AvatarCorrespondTemplateInfo> actTemps = avatarCorrespondTemps.get(act.getAvatarTempId());
			if (actTemps == null) {
				actTemps = new HashMap<>();
				avatarCorrespondTemps.put(act.getAvatarTempId(), actTemps);
			}
			actTemps.put(act.getLevel(), act);
		}
		return true;
	}

	public static AvatarTemplateInfo getAvatarTemplateInfo(int tempId) {
		return avatarTemps.get(tempId);
	}

	public static AvatarStarTemplate getAvatarStarTemplate(int tempId, int starLv) {
		Map<Integer, AvatarStarTemplate> starTemps = avatarStarTemps.get(tempId);
		if (starTemps != null) {
			return starTemps.get(starLv);
		}
		return null;
	}

	public static AvatarUpGradeTemplate getAvatarUpGradeTemplate(int tempId, int grade) {
		Map<Integer, AvatarUpGradeTemplate> gradeTemps = avatarUpGradeTemps.get(tempId);
		if (gradeTemps != null) {
			return gradeTemps.get(grade);
		}
		return null;
	}

	public static AvatarCorrespondTemplateInfo getAvatarCorrespondTemplateInfo(int tempId, int correspondLv) {
		Map<Integer, AvatarCorrespondTemplateInfo> correspondTemps = avatarCorrespondTemps.get(tempId);
		if (correspondTemps != null) {
			return correspondTemps.get(correspondLv);
		}
		return null;
	}

}
