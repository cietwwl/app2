package com.chuangyou.xianni.warfield.cmd;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerManaUpdateProto.PlayerManaUpdateMsg;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_PLAYER_MANA_UPDATE, desc = "玩家灵力更新")
public class PlayerManaUpdateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PlayerManaUpdateMsg req = PlayerManaUpdateMsg.parseFrom(packet.getBytes());
		
		int mana = army.getPlayer().getMana() + req.getMana();
		army.getPlayer().setMana(mana);
		
		//总值回写给center
		PlayerManaUpdateMsg.Builder msg = PlayerManaUpdateMsg.newBuilder();
		msg.setMana(army.getPlayer().getMana());
		PBMessage p = MessageUtil.buildMessage(Protocol.C_PLAYER_MANA_WRITEBACK, msg);
		army.sendPbMessage(p);
		
		PlayerAttUpdateMsg.Builder resp = PlayerAttUpdateMsg.newBuilder();
		resp.setPlayerId(army.getPlayerId());
		PropertyMsg.Builder propertyMsg = PropertyMsg.newBuilder();
		propertyMsg.setType(EnumAttr.MANA.getValue());
		propertyMsg.setTotalPoint(mana);
		resp.addAtt(propertyMsg);
		
		//通知自己
		PBMessage selfPkg = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, resp);
		army.sendPbMessage(selfPkg);
		
		// 通知附近玩家
		Set<Long> nears = army.getPlayer().getNears(new PlayerSelectorHelper(army.getPlayer()));
		NotifyNearHelper.notifyAttrChange(army, nears, resp.build());
	}

}
