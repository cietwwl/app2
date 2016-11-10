package com.chuangyou.xianni.gather.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.gather.CreatePrivateMonsterInnerProto.CreatePrivateMonsterInnerMsg;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.PrivateMonsterMgr;
import com.chuangyou.xianni.role.action.CreatePrivateMonsterAction;
import com.chuangyou.xianni.role.objects.PrivateMonster;
import com.chuangyou.xianni.role.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code=Protocol.S_CREATE_PIRVATE_MONSTER,desc="创建私有怪物")
public class CreatePrivateMonsterCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		CreatePrivateMonsterInnerMsg msg = CreatePrivateMonsterInnerMsg.parseFrom(packet.getBytes());
		long playerId = msg.getPlayerId();
		int monsterId = msg.getMonsterId();
		int leaveTime = msg.getLeaveTime();
		int mapId     = msg.getMapId();
		
		//分压地图
		ArmyProxy army = WorldMgr.getArmy(playerId);
		if(mapId==1007){
			mapId = army.getPlayer().getField().id;
		}
		
		//检测一下此种怪物其它的时间 是否到了.因为检测细度问题.可能会有一些误差.这个地方做一个特殊处理
		List<PrivateMonster> list = PrivateMonsterMgr.get(playerId);
		if(list.size()>0){
			for (PrivateMonster privateMonster : list) {
				if(privateMonster.getSkin()==monsterId){
					if (privateMonster.expired()) {
						privateMonster.destory();
						if (privateMonster.getField() != null) {
							privateMonster.getField().leaveField(privateMonster);
						}
					}				
				}
			}
		}
		
		MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get(monsterId);
		if(army!=null && monsterInfo!=null){	
			CreatePrivateMonsterAction action= null;
			if(msg.hasPos()){
				PBVector3 pos = msg.getPos();
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				Vector3 vPos = new Vector3(x / Vector3.Accuracy, y / Vector3.Accuracy, z / Vector3.Accuracy);	
				if(FieldMgr.getIns().getField(mapId)==null)return;
				action = new CreatePrivateMonsterAction(army, monsterInfo,vPos,leaveTime,mapId);
			}else{
				action = new CreatePrivateMonsterAction(army, monsterInfo,leaveTime);
			}
			army.enqueue(action);
		}
		
		
	}

	

}
