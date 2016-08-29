package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineGradeUpReqProto.MagicwpRefineGradeUpReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineGradeUpRespProto.MagicwpRefineGradeUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpGradeCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_REFINE_GRADE_UP, desc = "法宝洗炼进阶")
public class MagicwpRefineGradeUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpRefineGradeUpReqMsg req = MagicwpRefineGradeUpReqMsg.parseFrom(packet.getBytes());
		MagicwpCfg magicwpCfg = MagicwpTemplateMgr.getMagicwpTemps().get(req.getMagicwpId());
		if(magicwpCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "法宝配置错误");
			return;
		}
		if(magicwpCfg.getIsSpecial() == 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Special_NotOpera, packet.getCode());
			return;
		}
		
		MagicwpInfo magicwp = player.getMagicwpInventory().getMagicwpInfo(req.getMagicwpId());
		
		if(magicwp == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UnGet, packet.getCode());
			return;
		}
		MagicwpGradeCfg targetCfg = MagicwpTemplateMgr.getGradeTemps().get(magicwp.getMagicwpId() * 1000 + magicwp.getGrade() + 1);
		
		if(targetCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Grade_Max, packet.getCode());
			return;
		}
		
		MagicwpGradeCfg gradeCfg = MagicwpTemplateMgr.getGradeTemps().get(magicwp.getMagicwpId() * 1000 + magicwp.getGrade());
		if(magicwp.getLevel() < gradeCfg.getNeedLv()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Level_UnEnough, packet.getCode());
			return;
		}
		
		//消耗道具
		if(player.getBagInventory().getPlayerBagItemCount(gradeCfg.getJinjieItem()) < gradeCfg.getItemNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(gradeCfg.getJinjieItem(), gradeCfg.getItemNum(), ItemRemoveType.MAGICWP_GRADEUP)) return;
		
		if(magicwp.getGrade() == 0){
			String attStr = "";
			attStr += (1000000 * (int)(targetCfg.getAtt1()/1000000)) + "";
			attStr += "_" + (1000000 * (int)(targetCfg.getAtt2()/1000000));
			attStr += "_" + (1000000 * (int)(targetCfg.getAtt3()/1000000));
			attStr += "_" + (1000000 * (int)(targetCfg.getAtt4()/1000000));
			attStr += "_" + (1000000 * (int)(targetCfg.getAtt5()/1000000));
			attStr += "_" + (1000000 * (int)(targetCfg.getAtt6()/1000000));
			attStr += "_" + (1000000 * (int)(targetCfg.getAtt7()/1000000));
			attStr += "_" + (1000000 * (int)(targetCfg.getAtt8()/1000000));
			magicwp.setRefineAtts(attStr);
		}
		magicwp.setGrade(magicwp.getGrade() + 1);
		player.getMagicwpInventory().updateMagicwpInfo(magicwp);
		
		MagicwpRefineGradeUpRespMsg.Builder msg = MagicwpRefineGradeUpRespMsg.newBuilder();
		msg.setMagicwpId(magicwp.getMagicwpId());
		msg.setGrade(magicwp.getGrade());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_REFINE_GRADE_UP, msg);
		player.sendPbMessage(p);
		
		//法宝总属性改变
//		MagicwpManager.changeMagicwpAtt(roleId);
		//影响人物属性变更
//		player.getArmyInventory().getArmy().getHero().addMagicwp(MagicwpManager.computeMagicwpAtt(player));
//		player.getArmyInventory().updateProperty();
	}

}
