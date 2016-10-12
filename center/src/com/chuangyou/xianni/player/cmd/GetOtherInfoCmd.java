package com.chuangyou.xianni.player.cmd;

import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.item.ItemListProto.ItemListMsg;
import com.chuangyou.common.protobuf.pb.player.GetOtherInfosMsgProto.GetOtherInfosMsg;
import com.chuangyou.common.protobuf.pb.player.OtherEquipmentInfoProto.OtherEquipmentInfoMsg;
import com.chuangyou.common.protobuf.pb.player.OtherMagicwpMsgProto.OtherMagicwpMsg;
import com.chuangyou.common.protobuf.pb.player.OtherMountProto.OtherMountMsg;
import com.chuangyou.common.protobuf.pb.player.OtherPetProto.OtherPetMsg;
import com.chuangyou.common.protobuf.pb.player.OtherSoulgProto.OtherSoulMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerSimpleInfoMsgProto.PlayerSimpleInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.bag.BaseBag;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_GET_OTHER_INFO, desc = "获取其他用户详细信息")
public class GetOtherInfoCmd implements Command {
	static final int	EQUIPMENT	= 1;	// 人物信息以及装备
	static final int	MAGICWP		= 2;	// 法宝详细信息
	static final int	MOUNT		= 3;	// 坐骑详细信息
	static final int	PET			= 4;	// 宠物详细信息
	static final int	SOUT		= 5;	// 魂幡详细信息

	public void execute(Channel channel, PBMessage packet) throws Exception {
		GetOtherInfosMsg msg = GetOtherInfosMsg.parseFrom(packet.getBytes());
		GamePlayer other = WorldMgr.getPlayer(msg.getPlayerId());
		if (other == null) {
			Log.error("GetOtherInfoCmd but other is null ,otherId:" + msg.getPlayerId());
			return;
		}

		if (msg.getReqType() == EQUIPMENT) {
			sendEquipmentInfo(channel, packet.getPlayerId(), other);
		}
		if (msg.getReqType() == MAGICWP) {
			sendMagicwp(channel, packet.getPlayerId(), other);
		}
		if (msg.getReqType() == MOUNT) {
			sendMount(channel, packet.getPlayerId(), other);
		}
		if (msg.getReqType() == PET) {
			sendPet(channel, packet.getPlayerId(), other);
		}
		if (msg.getReqType() == SOUT) {
			sendSoul(channel, packet.getPlayerId(), other);
		}
	}

	public void sendSoul(Channel channel, long reqer, GamePlayer other) {
		OtherSoulMsg.Builder otherSoulMsg = OtherSoulMsg.newBuilder();
		if (other.getSoulInventory() != null) {
			other.getSoulInventory().writeInSimpOtherSnap(otherSoulMsg);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_OTHER_SOUL_IFNO, reqer, otherSoulMsg.build());
		channel.writeAndFlush(message);
	}

	public void sendPet(Channel channel, long reqer, GamePlayer other) {
		OtherPetMsg.Builder otherPetMsg = OtherPetMsg.newBuilder();
		if (other.getPetInventory() != null) {
			other.getPetInventory().writeInSimpOtherSnap(otherPetMsg);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_OTHER_PET_IFNO, reqer, otherPetMsg.build());
		channel.writeAndFlush(message);
	}

	public void sendMount(Channel channel, long reqer, GamePlayer other) {
		OtherMountMsg.Builder otherMountMsg = OtherMountMsg.newBuilder();
		if (other.getMountInventory() != null) {
			other.getMountInventory().writeInSimpOtherSnap(otherMountMsg);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_OTHER_MOUNT_IFNO, reqer, otherMountMsg.build());
		channel.writeAndFlush(message);
	}

	public void sendMagicwp(Channel channel, long reqer, GamePlayer other) {
		OtherMagicwpMsg.Builder magicwpInfos = OtherMagicwpMsg.newBuilder();
		if (other.getMagicwpInventory() != null) {
			other.getMagicwpInventory().writeInSimpOtherSnap(magicwpInfos);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_OTHER_MAGICWP_IFNO, reqer, magicwpInfos.build());
		channel.writeAndFlush(message);
	}

	public void sendEquipmentInfo(Channel channel, long reqer, GamePlayer other) {
		OtherEquipmentInfoMsg.Builder peinfos = OtherEquipmentInfoMsg.newBuilder();

		PlayerSimpleInfoMsg.Builder simpInfo = PlayerSimpleInfoMsg.newBuilder();
		if (other.getBasePlayer().getPlayerInfo() != null) {
			other.getBasePlayer().getPlayerInfo().writeSimpInfo(simpInfo);
			peinfos.setSimpleInfoMsg(simpInfo);
		}

		PropertyListMsg.Builder propertyList = PropertyListMsg.newBuilder();
		if (other.getBasePlayer().getPlayerJoinInfo() != null) {
			other.getBasePlayer().getPlayerJoinInfo().writeProto(propertyList);
			peinfos.setPropertitys(propertyList);
		}

		ItemListMsg.Builder equipemntMsg = ItemListMsg.newBuilder();
		if (other.getBagInventory() != null) {
			BaseBag equBag = other.getBagInventory().getBag(BagType.HeroEquipment);
			equBag.writeAllItems(equipemntMsg);
			peinfos.setItemListMsg(equipemntMsg);
		}
		if(other.getEquipInventory() != null){
			other.getEquipInventory().writeInSimpOtherSnap(peinfos);
		}
		
		PBMessage message = MessageUtil.buildMessage(Protocol.U_OTHER_EQUIPMENT_IFNO, reqer, peinfos.build());
		channel.writeAndFlush(message);
	}

}
