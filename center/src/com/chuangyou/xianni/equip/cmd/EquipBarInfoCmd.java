package com.chuangyou.xianni.equip.cmd;

import com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipBarInfoReqProto.EquipBarInfoReqMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.entity.equip.EquipBarGradeCfg;
import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.level.LevelUp;
import com.chuangyou.xianni.equip.EquipOperateAction;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_EQUIPBAR_INFO, desc = "装备栏信息操作请求")
public class EquipBarInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		EquipBarInfoReqMsg req = EquipBarInfoReqMsg.parseFrom(packet.getBytes());
		
		switch(req.getAction()){
		case EquipOperateAction.EquipBar.ALL_INFOS://获取所有装备栏位信息
			getAllInfo(player, req);
			break;
		case EquipOperateAction.EquipBar.LEVEL_UP://栏位升级
			levelUp(player, req, packet.getCode());
			break;
		case EquipOperateAction.EquipBar.GRADE_UP://栏位加持
			gradeUp(player, req, packet.getCode());
			break;
		case EquipOperateAction.EquipBar.LEVEL_UP_ONEKEY://栏位一键升级
			levelUpOnekey(player, req, packet.getCode());;
			break;
		}
	}
	
	/**
	 * 返回所有装备栏位信息
	 * @param player
	 * @param req
	 */
	private void getAllInfo(GamePlayer player, EquipBarInfoReqMsg req){
		player.getEquipInventory().updateAllInfo();
	}
	
	/**
	 * 装备栏升级
	 * @param player
	 * @param req
	 * @param protocol
	 */
	private void levelUp(GamePlayer player, EquipBarInfoReqMsg req, short protocol){
		EquipBarInfo info = player.getEquipInventory().getEquipBarInfoMap().get((short)req.getPosition());
		if(info == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Get_EquipBar_Postion_Error, protocol, "装备栏索引错误");
			return;
		}
		if(info.getLevel() >= player.getLevel()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.RoleLevel_UnEnough, protocol, "装备栏位等级不能超过人物等级");
			return;
		}
		
		if(info.getLevel() >= LevelUpTempleteMgr.getEquipBarMaxLevel((short)req.getPosition())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_BAR_IS_MAX_LV, protocol, "装备栏等级已达到上限");
			return;
		}
		LevelUp levelUpCfg = LevelUpTempleteMgr.getEquipBarLevel((short)req.getPosition(), info.getLevel());
		if(player.getBasePlayer().getPlayerInfo().getEquipExp() < levelUpCfg.getExp() - info.getExp()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_UNENOUGH_EXP, protocol, "装备栏升级经验不足");
			return;
		}
		player.getBasePlayer().consumeEquipExp(levelUpCfg.getExp() - info.getExp());
		info.setLevel(info.getLevel() + 1);
		info.setExp(0);
		player.notifyListeners(new ObjectEvent(this, info, EventNameType.EQUIP));
		
		
		EquipBarInfoRespMsg.Builder msg = EquipBarInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.EquipBar.LEVEL_UP);
		EquipBarInfoMsg.Builder infoMsg = EquipBarInfoMsg.newBuilder();
		info.writeProto(infoMsg);
		msg.addEquipBar(infoMsg);
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIPBAR_INFO, msg);
		player.sendPbMessage(p);
		
		player.getBagInventory().updateHeroProperties(player.getPlayerId());
	}
	
	private void levelUpOnekey(GamePlayer player, EquipBarInfoReqMsg req, short protocol){
		EquipBarInfo info = player.getEquipInventory().getEquipBarByPos((short)req.getPosition());
		if(info == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Get_EquipBar_Postion_Error, protocol, "装备栏索引错误");
			return;
		}
		if(info.getLevel() >= player.getLevel()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.RoleLevel_UnEnough, protocol, "装备栏位等级不能超过人物等级");
			return;
		}
		
		if(info.getLevel() >= LevelUpTempleteMgr.getEquipBarMaxLevel((short)req.getPosition())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_BAR_IS_MAX_LV, protocol, "装备栏等级已达到上限");
			return;
		}
		LevelUp levelUpCfg = LevelUpTempleteMgr.getEquipBarLevel((short)req.getPosition(), info.getLevel());
		if(player.getBasePlayer().getPlayerInfo().getEquipExp() < levelUpCfg.getExp() - info.getExp()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIP_UNENOUGH_EXP, protocol, "装备栏升级经验不足");
			return;
		}
		
		
		while(info.getLevel() < player.getLevel() && info.getLevel() < LevelUpTempleteMgr.getEquipBarMaxLevel((short)req.getPosition()) && player.getBasePlayer().getPlayerInfo().getEquipExp() >= levelUpCfg.getExp() - info.getExp()){
			
			player.getBasePlayer().consumeEquipExp(levelUpCfg.getExp() - info.getExp());
			info.setLevel(info.getLevel() + 1);
			info.setExp(0);
			
			levelUpCfg = LevelUpTempleteMgr.getEquipBarLevel((short)req.getPosition(), info.getLevel());
		}
		
		EquipBarInfoRespMsg.Builder msg = EquipBarInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.EquipBar.LEVEL_UP_ONEKEY);
		EquipBarInfoMsg.Builder infoMsg = EquipBarInfoMsg.newBuilder();
		info.writeProto(infoMsg);
		msg.addEquipBar(infoMsg);
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIPBAR_INFO, msg);
		player.sendPbMessage(p);
		
		player.getBagInventory().updateHeroProperties(player.getPlayerId());
	}
	
	/**
	 * 装备栏加持
	 * @param player
	 * @param req
	 * @param protocol
	 */
	private void gradeUp(GamePlayer player, EquipBarInfoReqMsg req, short protocol){
		EquipBarInfo info = player.getEquipInventory().getEquipBarByPos((short)req.getPosition());
		if(info == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Get_EquipBar_Postion_Error, protocol, "装备栏索引错误");
			return;
		}
		EquipBarGradeCfg cfg = EquipTemplateMgr.getBarGradeCfg((short)req.getPosition(), info.getGrade());
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "装备栏加持配置错误");
			return;
		}
		if(EquipTemplateMgr.getBarGradeCfg((short)req.getPosition(), info.getGrade() + 1) == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIPBAR_GRADE_MAX, protocol, "装备栏加持等级已达到上限");
			return;
		}
		boolean isEnough = true;
		for(int itemTempId: cfg.getNeedItems().keySet()){
			int itemNum = player.getBagInventory().getItemCount(itemTempId);
			if(itemNum < cfg.getNeedItems().get(itemTempId)){
				isEnough = false;
				break;
			}
		}
		if(isEnough == false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.EQUIPBAR_GRADEUP_ITEM_NOTENOUGH, protocol, "装备栏加持道具不足");
			return;
		}
		for(int itemTempId: cfg.getNeedItems().keySet()){
			int itemNum = cfg.getNeedItems().get(itemTempId);
			if(player.getBagInventory().removeItemFromPlayerBag(itemTempId, itemNum, ItemRemoveType.EQUIPBAR_GRADEUP) == false){
				Log.error("playerId : " + player.getPlayerId() + " remove item :" + itemTempId + " count: " + itemNum + "  error");
			}
		}
		
		ThreadSafeRandom random = new ThreadSafeRandom();
		//升阶
		boolean isSuccess = false;
		if(info.getBless() >= cfg.getBlessValve()){
			if(info.getBless() >= cfg.getBlessMax()){
				isSuccess = true;
			}else{
				int rate = cfg.getRate();
				isSuccess = random.isSuccessful(rate, 10000);
			}
		}
		
		if(isSuccess){
			info.setGrade(info.getGrade() + 1);
			info.setBless(0);
		}else{
			int addBless = random.next(cfg.getFailBlessMin(), cfg.getFailBlessMax());
			info.setBless(info.getBless() + addBless);
			if(info.getBless() >= cfg.getBlessMax()){
				info.setGrade(info.getGrade() + 1);
				info.setBless(0);
				isSuccess = true;
			}
		}
		
		player.notifyListeners(new ObjectEvent(this, info, EventNameType.EQUIP));
		
		EquipBarInfoRespMsg.Builder msg = EquipBarInfoRespMsg.newBuilder();
		msg.setAction(EquipOperateAction.EquipBar.GRADE_UP);
		EquipBarInfoMsg.Builder infoMsg = EquipBarInfoMsg.newBuilder();
		info.writeProto(infoMsg);
		msg.addEquipBar(infoMsg);
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIPBAR_INFO, msg);
		player.sendPbMessage(p);
		
		if(isSuccess){
			player.getBagInventory().updateHeroProperties(player.getPlayerId());
			player.notifyListeners(new ObjectEvent(this, info, EventNameType.EQUIP));
		}
	}

}
