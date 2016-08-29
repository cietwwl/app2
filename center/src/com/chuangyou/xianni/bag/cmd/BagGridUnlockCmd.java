package com.chuangyou.xianni.bag.cmd;

import com.chuangyou.common.protobuf.pb.item.ItemBagGridUnlockReqProto.ItemBagGridUnlockReqMsg;
import com.chuangyou.common.protobuf.pb.item.ItemBagGridUnlockRespProto.ItemBagGridUnlockRespMsg;
import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_BAG_GRID_UNLOCK, desc = "背包格子解锁")
public class BagGridUnlockCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ItemBagGridUnlockReqMsg req = ItemBagGridUnlockReqMsg.parseFrom(packet.getBytes());
		int bagSize = SystemConfigTemplateMgr.getIntValue("bag.initGridNum") + player.getBasePlayer().getPlayerInfo().getpBagCount();
		int maxBagSize = SystemConfigTemplateMgr.getIntValue("bag.maxGridNum");
		
		if(req.getUnlockNum() <= 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "解锁数量异常");
			return;
		}
		
		if(bagSize + req.getUnlockNum() > maxBagSize){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Bag_Grid_Unlock_Max, packet.getCode(), "解锁格子数已超过上限");
			return;
		}
		
		//扣道具
		int itemId = SystemConfigTemplateMgr.getIntValue("bag.unlock.item");
		int hasNum = player.getBagInventory().getItemCount(itemId);
		if(req.getUnlockNum() > hasNum){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode(), "物品不足");
			return;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(itemId, req.getUnlockNum(), ItemRemoveType.BAG_GRID_UNLOCK)) return;
		
		BagInventory bagIn = player.getBagInventory();
		bagIn.getBag(BagType.Play).addCapability((short)req.getUnlockNum());
		
		int pBagCount = player.getBasePlayer().getPlayerInfo().getpBagCount();
		player.getBasePlayer().getPlayerInfo().setpBagCount(pBagCount + req.getUnlockNum());
		
		ItemBagGridUnlockRespMsg.Builder msg = ItemBagGridUnlockRespMsg.newBuilder();
		msg.setBagTotalSize(SystemConfigTemplateMgr.getIntValue("bag.initGridNum") + player.getBasePlayer().getPlayerInfo().getpBagCount());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_BAG_GRID_UNLOCK, msg);
		player.sendPbMessage(p);
	}

}
