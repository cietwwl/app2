package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanFragmentUseReqProto.MagicwpBanFragmentUseReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanFragmentUseRespProto.MagicwpBanFragmentUseRespMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanLevelUpRespProto.MagicwpBanLevelUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.magicwp.manager.MagicwpBanLevelManager;
import com.chuangyou.xianni.magicwp.manager.MagicwpManager;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_BAN_FRAGMENT_USE, desc = "激活/使用碎片")
public class MagicwpBanFragmentUseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpBanFragmentUseReqMsg req = MagicwpBanFragmentUseReqMsg.parseFrom(packet.getBytes());
		MagicwpBanInfo ban = player.getMagicwpInventory().getBan(req.getBanId());
		
		//如果碎片已经全部激活，则吃碎片涨经验
		if(ban.getLevel() >= 1){
			if(!MagicwpBanLevelManager.useFragmentByIndex(player, ban, req.getFragmentIndex(), packet.getCode())){
				return;
			}
			
			MagicwpBanLevelUpRespMsg.Builder msg = MagicwpBanLevelUpRespMsg.newBuilder();
			msg.setBanId(ban.getBanId());
			msg.setLevel(ban.getLevel());
			msg.setExp(ban.getExp());
			PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_LEVEL_UP, msg);
			player.sendPbMessage(p);
			return;
		}
		
		//已激活索引串
		String indexStr = ban.getFragmentStr();
		String[] indexArr = indexStr.split("_");
		
		//当前位置已激活则不处理
		for(String str:indexArr){
			if(str.equals("")) continue;
			if(Integer.parseInt(str) == req.getFragmentIndex()){
				return;
			}
		}
		
		MagicwpBanCfg banCfg = MagicwpTemplateMgr.getBanTemps().get(req.getBanId());
		int needItem = 0;
		switch(req.getFragmentIndex()){
			case 1:
				needItem = banCfg.getActiveItem1();
				break;
			case 2:
				needItem = banCfg.getActiveItem2();
				break;
			case 3:
				needItem = banCfg.getActiveItem3();
				break;
			case 4:
				needItem = banCfg.getActiveItem4();
				break;
			default:
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_FragmentIndex_Invalid, packet.getCode());
				return;
		}
		
		if(player.getBagInventory().getPlayerBagItemCount(needItem) < 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(needItem, 1, BindType.ALL)) return;
		
		
		//激活成功，重新生成字符串
		if(indexStr.equals("")){
			indexStr = "" + req.getFragmentIndex();
		}else{
			indexStr = indexStr + "_" + req.getFragmentIndex();
		}
		ban.setFragmentStr(indexStr);
		
		//判断激活最后一个位置，禁制激活
		indexArr = indexStr.split("_");
		if(ban.getLevel() == 0 && indexArr.length >= 4){
			ban.setLevel(1);
			
			MagicwpBanLevelUpRespMsg.Builder levelMsg = MagicwpBanLevelUpRespMsg.newBuilder();
			levelMsg.setBanId(ban.getBanId());
			levelMsg.setLevel(ban.getLevel());
			levelMsg.setExp(ban.getExp());
			PBMessage pLevel = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_LEVEL_UP, levelMsg);
			player.sendPbMessage(pLevel);
		}
		player.getMagicwpInventory().updateBan(ban);
		
		MagicwpBanFragmentUseRespMsg.Builder msg = MagicwpBanFragmentUseRespMsg.newBuilder();
		msg.setBanId(ban.getBanId());
		msg.setFragmentIndex(req.getFragmentIndex());
		msg.setFragmentStr(ban.getFragmentStr());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_FRAGMENT_USE, msg);
		player.sendPbMessage(p);
		
		//法宝总属性改变
//		MagicwpManager.changeMagicwpAtt(roleId);
		//影响人物属性变更
		player.getMagicwpInventory().updataProperty();
	}

}
