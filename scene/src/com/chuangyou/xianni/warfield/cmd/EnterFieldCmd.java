package com.chuangyou.xianni.warfield.cmd;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.action.EnterFieldAction;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_ENTERSCENE, desc = "进入场景")
public class EnterFieldCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {

		Log.error("玩家请求进入场景：playerId : +" + army.getPlayerId());
		ReqChangeMapMsg msg = ReqChangeMapMsg.parseFrom(packet.getBytes());
		PostionMsg pos = msg.getPostionMsg();
		EnterFieldAction action = new EnterFieldAction(army, pos.getMapId(), pos.getMapKey(), Vector3BuilderHelper.get(pos.getPostion()));
		army.enqueue(action);
		// PostionMsg posMsg = msg.getPostionMsg();
		// int mapId = posMsg.getMapId(); // 如果属于副本地图，且mapId = -1，则为请求创建
		// int mapKey = posMsg.getMapKey(); // 副本地图ID
		//
		// FieldInfo fieldTemp = FieldTemplateMgr.getFieldTemp(mapKey);
		// if (fieldTemp == null) {
		// Log.error("请求进入一个不存在的地图,playerId :" + army.getPlayerId() + "mapKey:"
		// + mapKey);
		// ChangeMapResultMsg.Builder cmbuilder =
		// ChangeMapResultMsg.newBuilder();
		// cmbuilder.setResult(EnterMapResult.TEMP_ERROR);// 地图模板不存在
		// army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT,
		// cmbuilder));
		// return;
		// }
		//
		// // 副本地图,需要创建
		// Field field = null;
		// boolean isPubMap = fieldTemp.getType() == 1;
		// // 副本地图，走进副本流程
		// if (!isPubMap) {
		// field = FieldMgr.getIns().getField(mapId);
		// } else {
		// field = FieldMgr.getIns().getField(isPubMap ? mapKey : mapId);
		// }
		//
		// // 当前进入地图为空（销毁了）
		// if (field == null) {
		// ChangeMapResultMsg.Builder cmbuilder =
		// ChangeMapResultMsg.newBuilder();
		// cmbuilder.setResult(EnterMapResult.CLEAR);// 地图已经销毁
		// army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT,
		// cmbuilder));
		// return;
		// }
		//
		// int angle = posMsg.getPostion().getAngle();
		// // 若无初始位置,则设置进入时占无效位置
		// Vector3 postion = Vector3BuilderHelper.get(posMsg.getPostion());
		// if (postion.getX() <= 0 && postion.getY() <= 0 && postion.getZ() <=
		// 0) {
		// postion = new Vector3(fieldTemp.getPosition().getX(),
		// fieldTemp.getPosition().getY(), fieldTemp.getPosition().getZ(),
		// fieldTemp.getPosition().getAngle());
		// angle = fieldTemp.getPosition().getAngle();
		// }
		//
		// if (isPubMap) {
		// army.changeField(field, postion);
		// ChangeMapResultMsg.Builder cmbuilder =
		// ChangeMapResultMsg.newBuilder();
		// cmbuilder.setResult(EnterMapResult.SUCCESS);// 进入成功
		// PostionMsg.Builder postionMsg = PostionMsg.newBuilder();
		// postionMsg.setMapId(field.id);
		// postionMsg.setMapKey(field.getMapKey());
		//
		// PBVector3.Builder v3b = Vector3BuilderHelper.build(postion);
		// v3b.setAngle(angle);
		// postionMsg.setPostion(v3b);
		// cmbuilder.setPostion(postionMsg);
		// army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT,
		// cmbuilder));
		// } else {
		// int result = EnterMapResult.CAMPAIGN_DESTORY;
		// Campaign campaign = CampaignMgr.getCampagin(field.getCampaignId());
		// if (campaign != null) {
		// campaign.onPlayerEnter(army, mapId, postion);
		// } else {
		// ChangeMapResultMsg.Builder cmbuilder =
		// ChangeMapResultMsg.newBuilder();
		// cmbuilder.setResult(result);
		// army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT,
		// cmbuilder));
		// }
		// }
	}

}
