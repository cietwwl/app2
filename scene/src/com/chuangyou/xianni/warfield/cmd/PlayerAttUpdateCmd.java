package com.chuangyou.xianni.warfield.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.xianni.constant.DamageEffecterType;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.NotifyType;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code = Protocol.S_ATTRIBUTE_UPDATE, desc = "玩家属性变更需要广播给附近玩家")
public class PlayerAttUpdateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PlayerAttUpdateMsg req = PlayerAttUpdateMsg.parseFrom(packet.getBytes());

		// 改变属性的玩家
		ArmyProxy pArmy = WorldMgr.getArmy(req.getPlayerId());
		if (pArmy == null)
			return;

		// 修改玩家属性
		List<PropertyMsg> attList = req.getAttList();

		boolean hasWeaponUpdate = false;
		for (PropertyMsg property : attList) {
			// 升级直接满血
			if (property.getType() == EnumAttr.Level.getValue()) {
				pArmy.getPlayer().addCurBlood(Integer.MAX_VALUE, DamageEffecterType.BLOOD, 0, 0);
				pArmy.getPlayer().addCurSoul(Integer.MAX_VALUE, DamageEffecterType.SOUL, 0, 0);
				break;
			}
			if (property.getType() == EnumAttr.Weapon.getValue() || property.getType() == EnumAttr.WEAPON_AWAKEN.getValue()) {
				hasWeaponUpdate = true;
			}
		}

		// 读取属性
		pArmy.getPlayer().getSimpleInfo().readProperty(attList);
		pArmy.getPlayer().readProperty(attList);

		// 武器技能更新
		if (hasWeaponUpdate == true) {
			pArmy.getPlayer().updateWeaponBuff();
		}

		// 创建通知玩家自己的消息
		PlayerAttUpdateMsg.Builder resp = PlayerAttUpdateMsg.newBuilder();
		resp.setPlayerId(req.getPlayerId());
		resp.addAllAtt(req.getAttList());

		//需要同步给附近玩家的属性列表
		List<PropertyMsg> notifyNearsList = new ArrayList<>();
		for (PropertyMsg property : attList) {
			EnumAttr attr = EnumAttr.getEnumAttrByValue(property.getType());
			if(attr.getNotifyType() == NotifyType.NOTIFY_NEARS){
				notifyNearsList.add(property);
			}
			// 如果是更换坐骑，同时也要更新速度
			if (property.getType() == EnumAttr.Mount.getValue()) {
				PropertyMsg.Builder speedMsg = PropertyMsg.newBuilder();
				speedMsg.setType(EnumAttr.SPEED.getValue());
				speedMsg.setTotalPoint(pArmy.getPlayer().getProperty(EnumAttr.SPEED.getValue()));
				resp.addAtt(speedMsg);
				
				notifyNearsList.add(speedMsg.build());
				break;
			}
		}

		// 通知自己
		PBMessage selfPkg = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, resp);
		pArmy.sendPbMessage(selfPkg);

		//如果有属性需要通知附近玩家
		if(notifyNearsList.size() > 0){
			PlayerAttUpdateMsg.Builder notifyMsg = PlayerAttUpdateMsg.newBuilder();
			notifyMsg.setPlayerId(req.getPlayerId());
			notifyMsg.addAllAtt(notifyNearsList);
			
			// 通知附近玩家
			Set<Long> nears = pArmy.getPlayer().getNears(new PlayerSelectorHelper(pArmy.getPlayer()));
			
			NotifyNearHelper.notifyAttrChange(nears, notifyMsg.build());
		}
	}

}
