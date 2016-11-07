package com.chuangyou.xianni.retask.trigger;

import java.util.Map;

import com.chuangyou.xianni.entity.avatar.AvatarInfo;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.event.AvatarEvent;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.skill.template.SkillTempMgr;

/**
 * 分身任务
 * 
 * @author laofan
 *
 */
public class AvatarTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public AvatarTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {

			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				AvatarEvent e = (AvatarEvent) event;
				if (e != null) {
					if (e.getTargetId() == 1) {
						if (e.getTargetId1() == getTask().getTaskCfg().getTargetId1()) {
							getTask().updateProcess(e.getTargetNum());
						}
						getTask().updateProcess(getCountLv(player.getAvatarInventory().getAvatarInfos(), getTask().getTaskCfg().getTargetId1()));
					}
					if (e.getTargetId() == 3) {
						if (e.getTargetId1() == getTask().getTaskCfg().getTargetId1()) {
							getTask().updateProcess(e.getTargetNum());
						}
						getTask().updateProcess(getCountSkillLv(player.getAvatarInventory().getAvatarInfos(), getTask().getTaskCfg().getTargetId1()));
					}
				}
			}
		};
		player.addListener(listener, EventNameType.AVATAR_UPDATE);
	}

	/**
	 * 统计达到等级的数量
	 * 
	 * @param map
	 * @param lv
	 * @return
	 */
	private int getCountLv(Map<Integer, AvatarInfo> map, int lv) {
		int count = 0;
		for (AvatarInfo avatar : map.values()) {
			if (avatar.getGrade() >= lv) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 统计达到等级的数量
	 * 
	 * @param map
	 * @param lv
	 * @return
	 */
	private int getCountSkillLv(Map<Integer, AvatarInfo> map, int lv) {
		int count = 0;
		for (AvatarInfo avatar : map.values()) {
			SkillTempateInfo skill = SkillTempMgr.getSkillTemp(avatar.getSkillId());
			if (skill.getLevel() >= lv) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.AVATAR_UPDATE);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		if (getTask().getTaskCfg().getTargetId() == 1) {
			AvatarInfo avatar = player.getAvatarInventory().getAvatarInfos().get(getTask().getTaskCfg().getTargetId1());
			if(avatar!=null){
				getTask().getTaskInfo().setProcess(avatar.getGrade());
			}
		} else if (getTask().getTaskCfg().getTargetId() == 2) {
			getTask().getTaskInfo().setProcess(getCountLv(player.getAvatarInventory().getAvatarInfos(), getTask().getTaskCfg().getTargetId1()));
		} else if (getTask().getTaskCfg().getTargetId() == 3) {
			AvatarInfo avatar = player.getAvatarInventory().getAvatarInfos().get(getTask().getTaskCfg().getTargetId1());
			if(avatar!=null){
				SkillTempateInfo skill = SkillTempMgr.getSkillTemp(avatar.getSkillId());
				getTask().getTaskInfo().setProcess(skill.getLevel());
			}
		} else if (getTask().getTaskCfg().getTargetId() == 4) {
			getTask().getTaskInfo().setProcess(getCountSkillLv(player.getAvatarInventory().getAvatarInfos(), getTask().getTaskCfg().getTargetId1()));
		}

	}

}
