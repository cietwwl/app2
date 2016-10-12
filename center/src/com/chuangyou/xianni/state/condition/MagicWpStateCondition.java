package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;


/**
 * 法宝
 * @author laofan
 *
 */
public class MagicWpStateCondition extends BaseStateCondition {

	public MagicWpStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				MagicwpInfo tempInfo = (MagicwpInfo) event.getObject();
				int targetId = config.getTargetId();
				if(targetId == 1){
					if(tempInfo.getMagicwpId() == config.getTargetNum()){
						info.setProcess(player.getMagicwpInventory().getMagicwpInfo(config.getTargetNum()).getLevel());
					}
				}else if(targetId == 2){
					info.setProcess(getCountLv(player.getMagicwpInventory().getMagicwpInfoMap(),config.getTargetId1()));
				}else if(targetId == 3){
					if(tempInfo.getMagicwpId() == config.getTargetNum()){
						info.setProcess(player.getMagicwpInventory().getMagicwpInfo(config.getTargetNum()).getGrade());
					}
				}else if(targetId == 4){
					info.setProcess(getCountGrade(player.getMagicwpInventory().getMagicwpInfoMap(),config.getTargetId1()));
				}
			}
		};
		player.addListener(listener, EventNameType.MAGICWP);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.MAGICWP);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId = config.getTargetId();
		if(targetId == 1){
			if(player.getMagicwpInventory().getMagicwpInfo(config.getTargetNum())!=null){
				info.setProcess(player.getMagicwpInventory().getMagicwpInfo(config.getTargetNum()).getLevel());
			}
		}else if(targetId == 2){
			info.setProcess(getCountLv(player.getMagicwpInventory().getMagicwpInfoMap(),config.getTargetId1()));
		}else if(targetId == 3){
			if(player.getMagicwpInventory().getMagicwpInfo(config.getTargetNum())!=null){
				info.setProcess(player.getMagicwpInventory().getMagicwpInfo(config.getTargetNum()).getGrade());
			}
		}else if(targetId == 4){
			info.setProcess(getCountGrade(player.getMagicwpInventory().getMagicwpInfoMap(),config.getTargetId1()));
		}
	}
	
	protected int getCountLv(Map<Integer, MagicwpInfo> map,int num){
		int count=0;
		for (MagicwpInfo mountEquip : map.values()) {
			if(mountEquip.getLevel()>=num){
				count ++;
			}
		}
		return count;
	}
	
	protected int getCountGrade(Map<Integer, MagicwpInfo> map,int num){
		int count=0;
		for (MagicwpInfo mountEquip : map.values()) {
			if(mountEquip.getGrade()>=num){
				count ++;
			}
		}
		return count;
	}


	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}

}
