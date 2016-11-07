package com.chuangyou.xianni.warfield.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.ChangeMapResultMsgProto.ChangeMapResultMsg;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.constant.EnterMapResult;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_NEARMONSTER, desc = "靠近某种怪物")
public class NearMonsterCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {

		System.out.println("玩家请求移动到怪物附近 : +" + army.getPlayerId());
		ReqChangeMapMsg msg = ReqChangeMapMsg.parseFrom(packet.getBytes());

		PostionMsg posMsg = msg.getPostionMsg();
		int mapKey = posMsg.getMapKey(); // 副本地图ID

		FieldInfo fieldTemp = FieldTemplateMgr.getFieldTemp(mapKey);
		if (fieldTemp == null) {
			Log.error("请求进入一个不存在的地图,playerId :" + army.getPlayerId() + "mapKey:" + mapKey);
			ChangeMapResultMsg.Builder cmbuilder = ChangeMapResultMsg.newBuilder();
			cmbuilder.setResult(EnterMapResult.TEMP_ERROR);// 地图模板不存在
			army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT, cmbuilder));
			return;
		}
		Field field = FieldMgr.getIns().getField(army.getFieldId());

		// 当前进入地图为空（销毁了）
		if (field == null) {
			ChangeMapResultMsg.Builder cmbuilder = ChangeMapResultMsg.newBuilder();
			cmbuilder.setResult(EnterMapResult.CLEAR);// 地图已经销毁
			army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT, cmbuilder));
			return;
		}
		List<Living> monster = field.getMonsters();

		Vector3 v = null;
		for (Living living : monster) {
			v = living.getPostion();
		}
		if (v == null) {
			return;
		}
		// EnterFieldAction action = new EnterFieldAction(army, pos.getMapId(),
		// pos.getMapKey(), v);
		// army.enqueue(action);
		// if (true) {
		// return;
		// }

		army.changeField(field, v);
	}

}
