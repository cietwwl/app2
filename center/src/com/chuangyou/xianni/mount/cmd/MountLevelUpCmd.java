package com.chuangyou.xianni.mount.cmd;

import java.util.Date;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.mount.MountAttUpdateRespProto.MountAttUpdateRespMsg;
import com.chuangyou.common.protobuf.pb.mount.MountLevelUpRespProto.MountLevelUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.entity.mount.MountLevelCfg;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MOUNT_LEVEL_UP, desc = "坐骑升级")
public class MountLevelUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MountInfo mount = player.getMountInventory().getMount();
		//判断是否冷却中
		if(mount.getUpLevCd() > (new Date()).getTime()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CD_IS_NOT_ENOUGTH, packet.getCode());
			return;
		}
		
		//坐骑升级配置表
		Map<Integer, MountLevelCfg> mountLevCfg = MountTemplateMgr.getLevelTemps();
		
		//判断目标等级是否有配置
		MountLevelCfg targetCfg = mountLevCfg.get(mount.getLevel() + 1);
		if(targetCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Level_Max, packet.getCode());
			return;
		}
		
		MountLevelCfg levelCfg = mountLevCfg.get(mount.getLevel());
		if(player.getBasePlayer().getPlayerInfo().getMoney() < levelCfg.getNeedGold()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode());
			return;
		}
		//判断金币
		if(!player.getBasePlayer().consumeMoney(levelCfg.getNeedGold())) return;
		
		//升级成功
		mount.setLevel(mount.getLevel() + 1);
		mount.setUpLevCd((new Date()).getTime() + targetCfg.getUpLevCd()*1000);
		player.getMountInventory().updateMount(mount);
		
		//返回消息
		MountLevelUpRespMsg.Builder msg = MountLevelUpRespMsg.newBuilder();
		msg.setLevel(mount.getLevel());
		long leftCd = mount.getUpLevCd() - (new Date()).getTime();
		if(leftCd < 0){
			leftCd = 0;
		}
		msg.setUpLevelCd(leftCd);
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_LEVEL_UP, msg);
		player.sendPbMessage(p);
		
		//属性变更
		MountAttUpdateRespMsg.Builder attMsg = MountAttUpdateRespMsg.newBuilder();
		Map<Integer, Integer> attMap = MountManager.computeMountAtt(player);
		for(Integer attType:attMap.keySet()){
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			bean.setAttType(attType);
			bean.setAttValue(attMap.get(attType));
			attMsg.addAtts(bean);
		}
		PBMessage pAtt = MessageUtil.buildMessage(Protocol.U_MOUNT_ATT_UPDATE, attMsg);
		player.sendPbMessage(pAtt);
		
		//影响人物属性变更
		player.getMountInventory().updataProperty();
	}

}
