package com.chuangyou.xianni.script.manager;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.campaign.action.CampaignCreateAction;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.warfield.cmd.EnterFieldCmd;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class ScriptInterfaceScenceManager {

	public static void createCampaign(long playerId, int campaignId) {
		ArmyProxy army = WorldMgr.getArmy(playerId);
		if (army == null) {
			Log.error("army:" + playerId + " touchPoint,but is null,campaignId :" + campaignId);
			return;
		}
		CampaignCreateAction createAction = new CampaignCreateAction(army, campaignId, true);
		army.enqueue(createAction);
	}

	public static void changeMap(long playerId, int mapKey, int x, int y, int z, int angle) {

		ArmyProxy army = WorldMgr.getArmy(playerId);

		ReqChangeMapMsg.Builder msg = ReqChangeMapMsg.newBuilder();

		PostionMsg.Builder posMsg = PostionMsg.newBuilder();

		FieldInfo fieldTemp = FieldTemplateMgr.getFieldTemp(mapKey);
		if (fieldTemp.getType() == 2) {
			posMsg.setMapId(-1);
		} else {
			posMsg.setMapId(mapKey);
		}
		posMsg.setMapKey(mapKey);
		PBVector3.Builder targetPos = PBVector3.newBuilder();
		targetPos.setX(x);
		targetPos.setY(y);
		targetPos.setZ(z);
		targetPos.setAngle(angle);

		posMsg.setPostion(targetPos);

		msg.setPostionMsg(posMsg);

		PBMessage p = MessageUtil.buildMessage(Protocol.S_ENTERSCENE, army.getPlayerId(), msg);
		p.setBytes(p.getMessage().toByteArray());
		army.enqueue(new CmdTask(new EnterFieldCmd(), null, p, army.getCmdTaskQueue()));
	}

	public static void onDie(long playerId, int monsterId) {
		ArmyProxy army = WorldMgr.getArmy(playerId);
		MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get(monsterId);

	}
}
