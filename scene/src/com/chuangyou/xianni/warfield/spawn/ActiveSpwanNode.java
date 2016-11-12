package com.chuangyou.xianni.warfield.spawn;

import com.chuangyou.common.protobuf.pb.gather.TriggerReqProto.TriggerReqMsg;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 接触点
 * 
 * 召唤阵
 * 
 * 
 * 
 */
public class ActiveSpwanNode extends SpwanNode {
	protected int blood; // 节点血量（适用于需要循环开闭的节点，如传送阵）

	public ActiveSpwanNode(SpawnInfo spwanInfo, Field field) {
		super(spwanInfo, field);
	}

	public void start() {
		super.start();
	}

	public void active(ArmyProxy army) {
		TriggerReqMsg.Builder req = TriggerReqMsg.newBuilder();
		req.setId(spwanInfo.getEntityId());
		req.setPlayerId(army.getPlayerId());

		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_REQ_TRIGGER, req);
		GatewayLinkedSet.send2Server(pkg);
		super.active(army);
	}

	public void over() {
		super.over();
	}

}
