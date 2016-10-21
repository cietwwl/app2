package com.chuangyou.xianni.gather.cmd;

import com.chuangyou.common.protobuf.pb.gather.GatherReqProto.GatherReqMsg;
import com.chuangyou.common.protobuf.pb.gather.GatherRespProto.GatherRespMsg;
import com.chuangyou.common.protobuf.pb.gather.TriggerReqProto.TriggerReqMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Gather;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.template.NpcInfoTemplateMgr;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code=Protocol.S_REQ_GATHER,desc="采集")
public class GatherCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GatherReqMsg msg = GatherReqMsg.parseFrom(packet.getBytes());
		int type = msg.getType();
		Field field = FieldMgr.getIns().getField(msg.getMapId());
		if(field==null)return;
		Living g = field.getLiving(msg.getPointId());
		if(g==null || !(g instanceof Gather))return;
		Gather gather  = (Gather) g;
		if(type == 1){
			gather.addCdTime(army.getPlayerId(), System.currentTimeMillis());
			sendResp(msg,army);
		}else{
			long time = gather.getTime(army.getPlayerId());
			if(time == 0)return;
			if(System.currentTimeMillis() - time < NpcInfoTemplateMgr.npcInfoTemps.get(g.getSkin()).getIntParam1()*1000){
				ErrorMsgUtil.sendErrorMsg(army, ErrorCode.UNKNOW_ERROR, Protocol.S_REQ_GATHER, "进度条未走完");
				return;
			}
			gather.removeCdTime(army.getPlayerId());
			
			gather.trigger(army);
			sendResp(msg,army);
			
			//发送脚本触发
			TriggerReqMsg.Builder req = TriggerReqMsg.newBuilder();
			req.setId(gather.getSkin());
			req.setPlayerId(army.getPlayerId());
			
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_REQ_TRIGGER,req);
			GatewayLinkedSet.send2Server(pkg);
				
		}
		
	}
	
	/**
	 * 发送回应消息给客户端
	 * @param msg
	 * @param army
	 */
	private void sendResp(GatherReqMsg msg,ArmyProxy army){
		GatherRespMsg.Builder resp = GatherRespMsg.newBuilder();
		resp.setMapId(msg.getMapId());
		resp.setPointId(msg.getPointId());
		resp.setType(msg.getType());
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GATHER,resp);
		army.sendPbMessage(pkg);
	}

}
