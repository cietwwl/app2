package com.chuangyou.xianni.player.cmd;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.army.ArmyInfoReloadMsgProto.ArmyInfoReloadMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.xianni.army.ArmyInventory;
import com.chuangyou.xianni.army.Hero;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PLAYER_RELOAD_SCENCE_DATA, desc = "scence服务器回写数据")
public class ReloadData4SceneCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ArmyInfoReloadMsg msg = ArmyInfoReloadMsg.parseFrom(packet.getBytes());
		// 回写位置
		if (msg.getPostion() != null) {
			PostionMsg postion = msg.getPostion();
			if (postion == null || postion.getMapKey() == 0) {
				return;
			}
			PlayerPositionInfo pinfo = player.getBasePlayer().getPlayerPositionInfo();
			FieldInfo mapTemp = MapProxyManager.getFieldTempInfo(pinfo.getMapTempId());
			if (mapTemp != null && mapTemp.getType() == 1) {
				pinfo.setPreMapId(pinfo.getMapId());
				pinfo.setPreMapTempId(pinfo.getMapTempId());
				pinfo.setPreX(pinfo.getX());
				pinfo.setPreY(pinfo.getY());
				pinfo.setPreZ(pinfo.getZ());
			}
			PBVector3 v3 = postion.getPostion();
			if (postion.getMapId() != 0 && postion.getMapKey() != 0 && (v3.getX() != 0 || v3.getY() != 0 || v3.getZ() != 0)) {
				pinfo.setMapId(postion.getMapId());
				pinfo.setMapTempId(postion.getMapKey());
				pinfo.setX(postion.getPostion().getX());
				pinfo.setY(postion.getPostion().getY());
				pinfo.setZ(postion.getPostion().getZ());
				pinfo.resetUpdate();
				player.setCurMapId(pinfo.getMapId());
			}
		}
		PlayerJoinInfo join = player.getBasePlayer().getPlayerJoinInfo();
		if (msg.getPropertysList() != null) {
			for (PropertyMsg property : msg.getPropertysList()) {
				if (property.getType() == EnumAttr.CUR_SOUL.getValue()) {
					join.setCurSoul((int) property.getTotalPoint());
				}
				if (property.getType() == EnumAttr.CUR_BLOOD.getValue()) {
					join.setCurBlood((int) property.getTotalPoint());
				}
				if (property.getType() == EnumAttr.MANA.getValue()) {
					join.setMana((int) property.getTotalPoint());
				}

			}
		}
		
		ArmyInventory armyInventory = player.getArmyInventory();
		Hero hero = armyInventory.getHero();
		hero.setCurBlood(join.getCurBlood());
		hero.setCurSoul(join.getCurSoul());
	}

}
