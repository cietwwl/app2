package com.chuangyou.xianni.equip;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.equip.EquipBarGradeCfg;
import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.DBManager;

public class EquipInventory extends AbstractEvent implements IInventory {
	
	private GamePlayer player;
	
	private Map<Short, EquipBarInfo> equipBarInfoMap = new HashMap<>();
	
	public EquipInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	public EquipBarInfo getEquipBarByPos(short pos){
		return this.getEquipBarInfoMap().get(pos);
	}
	
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		equipBarInfoMap = DBManager.getEquipBarInfoDao().getAll(this.player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		player = null;
		
		if(equipBarInfoMap != null){
			equipBarInfoMap.clear();
		}
		equipBarInfoMap = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(equipBarInfoMap != null &&equipBarInfoMap.size() > 0){
			for(EquipBarInfo info: equipBarInfoMap.values()){
				short option = info.getOp();
				if(option == Option.Insert){
					DBManager.getEquipBarInfoDao().add(info);
				}else if(option == Option.Update){
					DBManager.getEquipBarInfoDao().update(info);
				}
			}
		}
		return false;
	}
	public Map<Short, EquipBarInfo> getEquipBarInfoMap() {
		if(equipBarInfoMap == null){
			equipBarInfoMap = new HashMap<>();
		}
		if(equipBarInfoMap.size() <= 0){
			Map<Short, Map<Integer, EquipBarGradeCfg>> equipBarGradeMap = EquipTemplateMgr.getBarGradeMap();
			
			for(short position:equipBarGradeMap.keySet()){
				EquipBarInfo info = new EquipBarInfo(player.getPlayerId(), position);
				info.setOp(Option.Insert);
				equipBarInfoMap.put(position, info);
			}
		}
		return equipBarInfoMap;
	}
	
	public void updateAllInfo(){
		Map<Short, EquipBarInfo> equipBarInfoMap = getEquipBarInfoMap();
		EquipBarInfoRespMsg.Builder msg = EquipBarInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.EquipBar.ALL_INFOS);
		
		for(EquipBarInfo info: equipBarInfoMap.values()){
			EquipBarInfoMsg.Builder infoMsg = EquipBarInfoMsg.newBuilder();
			info.writeProto(infoMsg);
			msg.addEquipBar(infoMsg);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIPBAR_INFO, msg);
		player.sendPbMessage(p);
	}

}
