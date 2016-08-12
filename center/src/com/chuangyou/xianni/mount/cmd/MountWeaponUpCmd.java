package com.chuangyou.xianni.mount.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.mount.MountAttUpdateRespProto.MountAttUpdateRespMsg;
import com.chuangyou.common.protobuf.pb.mount.MountWeaponUpRespProto.MountWeaponUpRespMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.entity.mount.MountWeaponCfg;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
@Cmd(code = Protocol.C_MOUNT_WEAPON_UP, desc = "坐骑神兵进阶")
public class MountWeaponUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MountInfo mount = player.getMountInventory().getMount();
		
		Map<Integer, MountWeaponCfg> weaponMap = MountTemplateMgr.getWeaponTemps();
		//判断目标阶数
		MountWeaponCfg targetCfg = weaponMap.get(mount.getWeaponGrade() + 1);
		if(targetCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Weapon_Grade_Max, packet.getCode());
			return;
		}
		
		MountWeaponCfg gradeCfg = weaponMap.get(mount.getWeaponGrade());
		//判断道具
		if(player.getBagInventory().getPlayerBagItemCount(gradeCfg.getUpgradeItem()) < gradeCfg.getUpgradeItemNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(gradeCfg.getUpgradeItem(), gradeCfg.getUpgradeItemNum(), ItemRemoveType.USE)) return;
		
		ThreadSafeRandom random = new ThreadSafeRandom();
		//骑兵升阶
		boolean isSuccess = false;
		if(mount.getWeaponBless() >= gradeCfg.getBlessValve()){
			if(mount.getWeaponBless() >= gradeCfg.getBlessMax()){
				isSuccess = true;
			}else{
				int rate = gradeCfg.getRate();
				
				isSuccess = random.isSuccessful(rate, 10000);
			}
		}
		if(isSuccess){
			mount.setWeaponGrade(mount.getWeaponGrade() + 1);
			mount.setWeaponBless(0);
		}else{
			int addBless = random.next(gradeCfg.getFailBlessMin(), gradeCfg.getFailBlessMax());
			mount.setWeaponBless(mount.getWeaponBless() + addBless);
		}
		player.getMountInventory().updateMount(mount);
		
		MountWeaponUpRespMsg.Builder msg = MountWeaponUpRespMsg.newBuilder();
		msg.setWeaponGrade(mount.getWeaponGrade());
		msg.setWeaponBless(mount.getWeaponBless());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_WEAPON_UP, msg);
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
