package com.chuangyou.xianni.friend.cmd;

import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsRespProto.GetRecommendFriendsRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.friend.manager.FriendManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_GETRECOMMENDFRIENDS,desc="获取推荐好友")
public class GetRecommendFriendsCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		
		Friend friend = player.getFriendInventory().getFriend();
		if(System.currentTimeMillis()-player.getFriendInventory().lastQueryFriendTime<3*1000){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CD_IS_NOT_ENOUGTH, Protocol.C_REQ_GETRECOMMENDFRIENDS);
			return;
		}
		player.getFriendInventory().lastQueryFriendTime = System.currentTimeMillis();
//		String myCity = ActiveSpaceInfo.getInstance().get(roleId).getCity();
//		
//		List<SceneGameRole> list =  ActiveGameRole.getInstance().getList();
//		int type = 0;
//		if(message.getIsMan() == 1 && message.getIsWoman()!=1){
//			type  = 1;  //只找男的
//		}
//		if(message.getIsMan() !=1 && message.getIsWoman() == 1){
//			type =  2; //只找女的
//		}
//		int len = list.size();
//		List<GameRole> tempList = new ArrayList<>();
//		for(int i=0;i<len;i++){
//			SceneGameRole role = list.get(i);
//			//1：男  2：女
//			if(role.getRoleId() == roleId)continue;
////			int sex = DataDictionaryManager.getIns().getJobInfo((byte) role.getJobId()).getSex();
//			int sex = 1;
//			if(friend.isFriend(role.getRoleId()))continue;
//			if(message.getIsCity()==1 && !myCity.equals(ActiveSpaceInfo.getInstance().get(role.getRoleId()).getCity()))continue;
//			if(type == 2 && sex==1)continue;
//			if(type ==1 && sex ==2)continue;
//			tempList.add(role);
//		}
//		
//		OuterRespGetRecommendFriendsMsg msg = new OuterRespGetRecommendFriendsMsg();
//		len = tempList.size();
//		if(len<=8){
//			msg.setList(tempList);
//		}else{
//			List<GameRole> tempListR = new ArrayList<>();
//			for(int i=0;i<8;i++){
//				int r = new Random().nextInt(len);
//				tempListR.add(tempList.get(r));
//				tempList.remove(r);
//				len = tempList.size();
//			}
//			msg.setList(tempListR);
//		}
		
//		MessageUtil.sendMsg(channel, msg);
//		FriendActive.getInstance().setCdTime(roleId);
		GetRecommendFriendsRespMsg.Builder resp = GetRecommendFriendsRespMsg.newBuilder();
		List<GamePlayer> list =   WorldMgr.getOnLinePlayers();
		for (GamePlayer gamePlayer : list) {
			resp.addRoles(FriendManager.change(gamePlayer));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_REQ_GETRECOMMENDFRIENDS, resp);
		player.sendPbMessage(pkg);
		
		
		
	}

}
