package com.chuangyou.xianni.relation.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg;
import com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsRespProto.GetRecommendFriendsRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.manager.RelationProtoUtil;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code = Protocol.C_REQ_GETRECOMMENDFRIENDS, desc = "获取推荐好友")
public class GetRecommendFriendsCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		if (player.getRelationInventory() == null)
			return;
//		if (System.currentTimeMillis() - player.getRelationInventory().lastQueryTime < 3 * 1000) {
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CD_IS_NOT_ENOUGTH, Protocol.C_REQ_GETRECOMMENDFRIENDS);
//			return;
//		}
//		player.getRelationInventory().lastQueryTime = System.currentTimeMillis();

		GetRecommendFriendsReqMsg req = GetRecommendFriendsReqMsg.parseFrom(packet.getBytes());
		
		GetRecommendFriendsRespMsg.Builder resp = GetRecommendFriendsRespMsg.newBuilder();
		List<GamePlayer> list = WorldMgr.getPlayerLevelRank();
		
		if(list.size() > 1){
			int playerIndex = list.indexOf(player);
			
			//小于自己等级的玩家计数
			int beforeCount = 0;
			
			if(playerIndex - 1 >= 0){
				for (int i = playerIndex - 1; i >=0; i--) {
					GamePlayer gamePlayer = list.get(i);
					if(checkCanAddFriend(player, gamePlayer, req) == false) continue;
					
					//加入列表并添加计数
					resp.addRoles(RelationProtoUtil.change(gamePlayer));
					beforeCount ++;
					if(beforeCount >= 15) break;
				}
			}
			
			//一共推荐30条，还需要的剩余数量
			int needCount = 30 - beforeCount;
			
			int afterCount = 0;
			if(playerIndex + 1 < list.size()){
				for(int i = playerIndex + 1; i < list.size(); i++){
					GamePlayer gamePlayer = list.get(i);
					if(checkCanAddFriend(player, gamePlayer, req) == false) continue;
					
					//加入列表并添加计数
					resp.addRoles(RelationProtoUtil.change(gamePlayer));
					afterCount ++;
					if(afterCount >= needCount) break;
				}
			}
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_REQ_GETRECOMMENDFRIENDS, resp);
		player.sendPbMessage(pkg);

	}
	
	private boolean checkCanAddFriend(GamePlayer selfPlayer, GamePlayer targetPlayer, GetRecommendFriendsReqMsg req){
		if(targetPlayer == null){
			return false;
		}
		//排除自己
		if(targetPlayer.getPlayerId() == selfPlayer.getPlayerId()){
			return false;
		}
		//排除已经是自己好友的玩家
		if(selfPlayer.getRelationInventory().isRelationTypeTargetToSelf(targetPlayer.getPlayerId(), PlayerRelationConstant.FRIEND)){
			return false;
		}
		//如果选择同城，排除非同城玩家
		if(req.getIsCity() > 0){
			if(selfPlayer.getSpaceInventory() == null || targetPlayer.getSpaceInventory() == null){
				return false;
			}
			if(selfPlayer.getSpaceInventory().getSpaceInfo() == null || targetPlayer.getSpaceInventory().getSpaceInfo() == null){
				return false;
			}
			String selfCity = selfPlayer.getSpaceInventory().getSpaceInfo().getCity();
			String targetCity = targetPlayer.getSpaceInventory().getSpaceInfo().getCity();
			if(selfCity.equals(targetCity) == false) {
				return false;
			}
		}
		//判断男女筛选，男女同时勾选的时候则不用筛选
		if(!(req.getIsMan() > 0 && req.getIsWoman() > 0)){
			if(req.getIsMan() > 0){
				if(targetPlayer.getBasePlayer().getPlayerInfo().getJob() != 2){
					return false;
				}
			}
			if(req.getIsWoman() > 0){
				if(targetPlayer.getBasePlayer().getPlayerInfo().getJob() == 2){
					return false;
				}
			}
		}
		
		return true;
	}

}
