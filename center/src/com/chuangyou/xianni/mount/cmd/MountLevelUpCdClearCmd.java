package com.chuangyou.xianni.mount.cmd;

import java.util.Date;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.mount.MountLevelUpCdClearRespProto.MountLevelUpCdClearRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.entity.mount.MountLevelCfg;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MOUNT_LEVELUPCD_CLEAR, desc = "清除坐骑升级CD")
public class MountLevelUpCdClearCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		//玩家坐骑信息
		MountInfo mount = player.getMountInventory().getMount();
		//不在冷却中，不需要清除
		if(mount.getUpLevCd() <= (new Date()).getTime()){
			return;
		}
		
		//坐骑升级配置表
		Map<Integer, MountLevelCfg> mountLevCfg = MountTemplateMgr.getLevelTemps();
		MountLevelCfg mountLevel = mountLevCfg.get(mount.getLevel());
		
		//判断元宝
		if(player.getBasePlayer().getPlayerInfo().getCash() < mountLevel.getClearCdCash()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode());
			return;
		}
		if(!player.getBasePlayer().consumeCash(mountLevel.getClearCdCash())) return;
		
		//清除成功
		mount.setUpLevCd(0);
		player.getMountInventory().updateMount(mount);
		
		//返回消息
		MountLevelUpCdClearRespMsg.Builder msg = MountLevelUpCdClearRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_LEVELUPCD_CLEAR, msg);
		player.sendPbMessage(p);
	}

}
