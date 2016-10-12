package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.event.PlayerPropertyUpdateEvent;

/**
 * 技有修炼等级变更
 * @author laofan
 *
 */
public class SkillStageStateCondition extends BaseStateCondition {

	public SkillStageStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				PlayerPropertyUpdateEvent e = (PlayerPropertyUpdateEvent) event;
				Map<Integer, Long> map = e.getChangeMap();
				if(map!=null){
					for(Map.Entry<Integer, Long> entry:map.entrySet()){
						if(entry.getKey() == EnumAttr.SkillStage.getValue()){
							long skillStage = entry.getValue();
							info.setProcess((int)skillStage);
							doNotifyUpdate();
						}
					}
				}
			}
		};
		player.getBasePlayer().addListener(listener, EventNameType.UPDATE_PLAYER_PROPERTY);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.getBasePlayer().removeListener(listener, EventNameType.UPDATE_PLAYER_PROPERTY);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		this.info.setProcess(player.getBasePlayer().getPlayerInfo().getSkillStage());
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		if(this.info.getProcess()>=this.config.getTargetNum()){
			return true;
		}
		return false;
	}

	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}

}
