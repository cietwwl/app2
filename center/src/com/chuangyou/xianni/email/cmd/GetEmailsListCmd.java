package com.chuangyou.xianni.email.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.email.GetEmailNumRespProto.GetEmailNumRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;


@Cmd(code = Protocol.C_REQ_GETEMAILS_NUM, desc = "请求玩家邮件列表数量")
public class GetEmailsListCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		List<Email> list = player.getEmailInventory().getEmails();
		GetEmailNumRespMsg.Builder msg = GetEmailNumRespMsg.newBuilder();
		
		
		if(list==null || list.size() == 0){
			msg.setEmailNum(0);
			msg.setUnReadEmailNum(0);
		}else{
			msg.setEmailNum(list.size());
			short count =0;
			for (Email email : list) {
				if(email.getStatus() == 0){
					count ++;
				}
			}
			msg.setUnReadEmailNum(count);		
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETEMAILS_NUM, msg);
		player.sendPbMessage(pkg);
		
	}

}
