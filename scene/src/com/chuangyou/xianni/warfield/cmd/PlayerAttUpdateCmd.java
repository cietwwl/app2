package com.chuangyou.xianni.warfield.cmd;

import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code = Protocol.S_ATTRIBUTE_SCENE_UPDATE, desc = "玩家属性变更需要广播给附近玩家")
public class PlayerAttUpdateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PlayerAttUpdateMsg req = PlayerAttUpdateMsg.parseFrom(packet.getBytes());

		// 改变属性的玩家
		ArmyProxy pArmy = WorldMgr.getArmy(req.getPlayerId());
		
		// 修改玩家属性
		List<PropertyMsg> attList = req.getAttList();
		pArmy.getPlayer().getSimpleInfo().readProperty(attList);
		
		// 通知自己
		PBMessage selfPkg = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, req);
		army.sendPbMessage(selfPkg);

		// 通知附近玩家
		Field f = FieldMgr.getIns().getField(pArmy.getFieldId());
		Living living = f.getLiving(req.getPlayerId());
		Set<Long> nears = living.getNears(new PlayerSelectorHelper(living));

		NotifyNearHelper.notifyAttrChange(pArmy, nears, req);
	}

}
