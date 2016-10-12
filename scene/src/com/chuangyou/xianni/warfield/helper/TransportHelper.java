package com.chuangyou.xianni.warfield.helper;

import com.chuangyou.common.protobuf.pb.ChangeMapResultMsgProto.ChangeMapResultMsg;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.army.ArmyInfoReloadMsgProto.ArmyInfoReloadMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.constant.EnterMapResult;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.CenterProtocol;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 转场处理
 * @author wkghost
 *
 */
public class TransportHelper {

	public static void truckTransport(Truck truck, Vector3 nextVector3, int nextFieldId)
	{
		Field nextField = FieldMgr.getIns().getField(nextFieldId);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		if(nextField == null)
		{
			Log.error("nextFieldId = " + nextFieldId + " 未找出场景");
			return;
		}
		//先镖车转场
		truck.getField().leaveField(truck);
		truck.setPostion(nextVector3);
		nextField.enterField(truck);
	}
	
	/**
	 * 转场
	 */
	public static void transport(ArmyProxy army, Vector3 position, int fieldId)
	{
		Field nextField = FieldMgr.getIns().getField(fieldId);
		if(nextField == null)
		{
			Log.error("fieldId = " + fieldId + " 未找出场景");
			return;
		}
		// 进入前，向center服务器同步一次位置
		reloadPos(army);
		// 进入地图
		int angle = 0;
		army.changeField(nextField, position);
		// 地图变更信息
		ChangeMapResultMsg.Builder cmbuilder = ChangeMapResultMsg.newBuilder();
		cmbuilder.setResult(EnterMapResult.SUCCESS);// 进入成功
		PostionMsg.Builder postionMsg = PostionMsg.newBuilder();
		postionMsg.setMapId(nextField.id);
		postionMsg.setMapKey(nextField.getMapKey());
		PBVector3.Builder builder = Vector3BuilderHelper.build(position);
		builder.setAngle(angle);//
		postionMsg.setPostion(builder);
		cmbuilder.setPostion(postionMsg);
		army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT, cmbuilder));
	}
	
	private static void reloadPos(ArmyProxy army) {

		ArmyInfoReloadMsg.Builder armyReload = ArmyInfoReloadMsg.newBuilder();
		Player player = army.getPlayer();
		Field field = FieldMgr.getIns().getField(army.getFieldId());
		if (field != null && player.getPostion() != null) {
			PostionMsg.Builder postion = PostionMsg.newBuilder();
			postion.setMapId(field.id);
			postion.setMapKey(field.getMapKey());

			Vector3 curPos = player.getPostion();
			PBVector3.Builder pbPos = Vector3BuilderHelper.build(curPos);
			postion.setPostion(pbPos.build());
			armyReload.setPostion(postion.build());
		}
		PBMessage redata = MessageUtil.buildMessage(CenterProtocol.C_PLAYER_RELOAD_SCENCE_DATA, armyReload);
		army.sendPbMessage(redata);

	}
}
