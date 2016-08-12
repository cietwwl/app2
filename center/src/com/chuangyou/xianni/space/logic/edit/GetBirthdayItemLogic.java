package com.chuangyou.xianni.space.logic.edit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chuangyou.common.protobuf.pb.space.EditSpaceInfoReqProto.EditSpaceInfoReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 领取生日礼物
 * @author laofan
 *
 */
public class GetBirthdayItemLogic extends EditFaceLogic {

	@SuppressWarnings("deprecation")
	@Override
	public void doProcess(GamePlayer player, EditSpaceInfoReqMsg req) {
		// TODO Auto-generated method stub
		
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(player.getSpaceInventory().getSpaceInfo().getIsEditBirthday()==1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_ENDIT_INFO,"生日礼物已经领过");
			return;
		}
		try {
			Date birthday = sdf.parse(player.getSpaceInventory().getSpaceInfo().getBirthday());
			Date now = new Date();
			
			if(birthday.getDate()== now.getDate() && birthday.getMonth()==now.getMonth()){
				player.getSpaceInventory().getSpaceInfo().setIsEditBirthday(1);
				int itemType = SystemConfigTemplateMgr.getBirthdayItem();
				if(player.getBagInventory().addItem(itemType, 1, ItemAddType.SPACE_ADD, false)==false){
					Log.error("添加物品失败："+itemType);
					return;
				}					
			}else{
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_ENDIT_INFO,"不是生日当天");		
				return;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		sendMsg(player, req);
	}

	
}
