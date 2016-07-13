package com.chuangyou.xianni.role.action;

import com.chuangyou.common.protobuf.pb.ChangeMapResultMsgProto.ChangeMapResultMsg;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.constant.EnterMapResult;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;

/** 人物复活 */
public class RevivalPlayerAction extends DelayAction {
	static final int			DEFAULT_MAP	= 1005;
	/* 死亡CD */
	private static final int	DeathCD		= 12 * 1000;

	private ArmyProxy			army;

	public RevivalPlayerAction(ArmyProxy army) {
		super(army, DeathCD);
		this.army = army;
	}

	@Override
	public void execute() {
		// 人物复活
		Player player = army.getPlayer();

		// 如果已经复活，返回
		if (player.isDie() == false) {
			return;
		}
		player.setRevivaling(false);
		// 先复活人物
		player.renascence();

		Vector3 vector3 = Vector3.Invalid;

		// 进入的目标地图
		Field field = FieldMgr.getIns().getField(army.getFieldId());
		Campaign campaign = null;
		// 野外死亡，直接回主城
		if (field == null || field.getCampaignId() == 0 || (campaign = CampaignMgr.getCampagin(field.getCampaignId())) == null) {
			field = FieldMgr.getIns().getField(DEFAULT_MAP);
			FieldInfo fieldTemp = FieldTemplateMgr.getFieldTemp(DEFAULT_MAP);
			vector3 = fieldTemp.getPosition();
		} else {
			// 副本中死亡
			SpwanNode revivalNode = campaign.getRevivalNode();
			// 副本中死亡有复活点到复活点复活
			if (revivalNode != null && revivalNode.getField() != null) {
				field = revivalNode.getField();
				vector3 = revivalNode.getSpawnInfo().getPosition();
			} else {
				// 没有复活点，到副本起始地图复活
				field = campaign.getStarField();
				FieldInfo fieldTemp = FieldTemplateMgr.getFieldTemp(field.getMapKey());
				vector3 = fieldTemp.getPosition();
			}
		}

		army.changeField(field, vector3);

		ChangeMapResultMsg.Builder cmbuilder = ChangeMapResultMsg.newBuilder();
		cmbuilder.setResult(EnterMapResult.SUCCESS);// 进入成功
		PostionMsg.Builder postionMsg = PostionMsg.newBuilder();
		postionMsg.setMapId(field.id);
		postionMsg.setMapKey(field.getMapKey());
		postionMsg.setPostion(Vector3BuilderHelper.build(vector3));
		cmbuilder.setPostion(postionMsg);
		army.sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT, cmbuilder));

	}

}
