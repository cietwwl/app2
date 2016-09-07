package com.chuangyou.xianni.soul.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.soul.SoulInfoAddExpReqProto.SoulInfoAddExpReqMsg;
import com.chuangyou.common.protobuf.pb.soul.SoulInfoAddExpRespProto.SoulInfoAddExpRespMsg;
import com.chuangyou.common.protobuf.pb.soul.SyncSoulProto.SyncSoulLv;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_SOUL_ADD_EXP,desc="加经验")
public class SoulInfoAddExpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, Integer> map = SystemConfigTemplateMgr.getSoulItemExp();
		if(map==null){
			Log.error("配置表出错：SoulInfoAddExpCmd");
			return;
		}
		
		SoulInfoAddExpReqMsg req = SoulInfoAddExpReqMsg.parseFrom(packet.getBytes());
		boolean flag = check(req.getItem1(), req.getItemCount1(), player) &
				check(req.getItem2(), req.getItemCount2(), player) &
				check(req.getItem3(), req.getItemCount3(), player);
		if(flag==false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Use_Error, Protocol.C_REQ_SOUL_ADD_EXP," 数量不够：");		
			return;
		}
		
		
		SoulInfo soulInfo = player.getSoulInventory().getSoulInfo();
		
		int lv = LevelUpTempleteMgr.getSoulLevel(soulInfo.getExp());
		addExp(req.getItem1(),req.getItemCount1(),map,soulInfo,player);
		addExp(req.getItem2(),req.getItemCount2(),map,soulInfo,player);
		addExp(req.getItem3(),req.getItemCount3(),map,soulInfo,player);
		soulInfo.setOp(Option.Update);
		if(lv!=LevelUpTempleteMgr.getSoulLevel(soulInfo.getExp())){
			player.getSoulInventory().updateProperty();
		}
		SoulInfoAddExpRespMsg.Builder resp = SoulInfoAddExpRespMsg.newBuilder();
		resp.setExp(soulInfo.getExp());
		resp.addAllAtts(player.getSoulInventory().getList());
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SOUL_ADD_EXP,resp);
		player.sendPbMessage(pkg);
		
		//经验值同步到scene服务器
		lv = LevelUpTempleteMgr.getSoulLevel(soulInfo.getExp());
		SyncSoulLv.Builder sync = SyncSoulLv.newBuilder();
		sync.setSoulLv(lv);
		pkg =  MessageUtil.buildMessage(Protocol.S_REQ_SOUL_EXP,sync);
		player.sendPbMessage(pkg);
		
	}
	
	private boolean check(int item,int count,GamePlayer player){
		if(item>0 && count>0){
			if(player.getBagInventory().getItemCount(item)<count){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
		
	}
	
	private void addExp(int item,int count,Map<Integer, Integer> map,SoulInfo soulInfo,GamePlayer player){
		if(item>0 && count>0){
			if(!map.containsKey(item)){
				Log.error("配置表出错1：SoulInfoAddExpCmd");
				return;
			}
			if(player.getBagInventory().removeItem(item, count, ItemRemoveType.SOUL)){				
				int addExp = map.get(item);
				soulInfo.setExp(soulInfo.getExp()+addExp*count);
			}
		}
	}

}
