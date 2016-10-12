package com.chuangyou.xianni.truck.cmd;

import java.util.HashSet;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg;
import com.chuangyou.common.protobuf.pb.truck.ReqTruckStopProto.ReqTruckStop;
import com.chuangyou.common.protobuf.pb.truck.ReqTruckSyncPosProto.ReqTruckSyncPos;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.ActiveLiving;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.ExcludePetSelector;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_STOP, desc = "镖车停止移动")
public class TruckReqStopCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		Field f = FieldMgr.getIns().getField(army.getFieldId());
		if(f == null) return;
		ReqTruckStop stopmsg = ReqTruckStop.parseFrom(packet.getBytes());
		Truck truck = (Truck) f.getLiving(stopmsg.getTruckid());
		if(truck == null) return;
		ExcludePetSelector selector = new ExcludePetSelector(truck);
		
		Vector3 current = Vector3BuilderHelper.get(stopmsg.getCur());
		
		//获取目前周围的玩家
		Set<Long> oldNears = truck.getNears(selector);
		//设置位置
		truck.setPostion(current);
		//再获取更新后的周围玩家
		Set<Long> newNears = truck.getNears(selector);
		//交集
		Set<Long> intersection = new HashSet<Long>(oldNears);
		intersection.retainAll(newNears);
		
		//交集的玩家通知停止移动
		if(intersection.size() > 0) 
		{
			NotifyNearHelper.notifyStop(army, intersection);
		}
		
		// 离开场景通知 老玩家集合 同移动集合的 差集
		oldNears.removeAll(intersection);
		
		// 通知离开
        if (oldNears.size() > 0)
        {
        	//System.err.println("stopcmd 有玩家离开视野");
    		NotifyNearHelper.notifyLeaveGrid(truck, oldNears);
        }
        
        // 进入新场景通知 新玩家集合同移动集合的差集
        newNears.removeAll(intersection);
		
        // 通知进入
        if(newNears.size() > 0)
        {
        	//System.err.println("stopcmd 有玩家进入视野");
        	NotifyNearHelper.notifyAttSnap(truck, newNears);
        }
	}

}
