package com.chuangyou.xianni.script.manager;

import java.util.List;

import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.gather.CreatePrivateMonsterInnerProto.CreatePrivateMonsterInnerMsg;
import com.chuangyou.common.protobuf.pb.npcDialog.HintRespProto.HintRespMsg;
import com.chuangyou.common.util.ChangeCharsetUtil;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.npcDialog.NpcCommand;
import com.chuangyou.xianni.npcDialog.manager.NpcDialogManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.task.TaskTriggerInfo;
import com.chuangyou.xianni.word.WorldMgr;

public class ScriptInterfaceManager {

	/**
	 * 发送NPC命令列表给客户端
	 * @param playerId
	 * @param list
	 * @param content
	 */
	public static void sendNpcCmdListToClient(long playerId, List<NpcCommand> list, String content){
		NpcDialogManager.sendResultToClient(playerId, list, content);
	}
	
	/**
	 * 发送提示消息给客户端
	 * @param playerId
	 * @param content
	 */
	public static void sendHintToClient(long playerId, String content){
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if(player==null)return;
		HintRespMsg.Builder resp = HintRespMsg.newBuilder();
		resp.setContent(ChangeCharsetUtil.toUTF_8(content));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SENDHINT, resp);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 主角切换地图
	 * @param playerId
	 * @param mapId
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void changeMap(long playerId, int mapId, int x, int y, int z){
		MapProxyManager.changeMap(playerId, mapId, x, y, z);
	}
	
	/**
	 * 查询人物身上是否有某个任务
	 * @param playerId
	 * @param taskId
	 * @return
	 */
	public static boolean isHasTask(long playerId,int taskId){
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if(player==null)return false;
		TaskTriggerInfo info =   player.getTaskInventory().getTaskInfos().get(taskId);
		if(info == null){
			return false;
		}else{
			if(info.getInfo().getState() == TaskInfo.ACCEPT){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 添加物品接口
	 * @param playerId
	 * @param templateId
	 * @param num
	 * @param isBind
	 * @return
	 */
	public static boolean addItem(long playerId,int templateId,int count,boolean isBind){
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if(player==null)return false;
		return player.getBagInventory().addItem(templateId, count, ItemAddType.SCRIPT_ADD, isBind);
	}
	
	/**
	 * 刷私有怪物
	 * @param playerId
	 * @param monsterId
	 */
	public static void createPrivateMonster(long playerId,int monsterId,int leaveTime,int mapId){
		CreatePrivateMonsterInnerMsg.Builder msg = CreatePrivateMonsterInnerMsg.newBuilder();
		msg.setMonsterId(monsterId);
		msg.setPlayerId(playerId);
		msg.setLeaveTime(leaveTime);
		msg.setMapId(mapId);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_CREATE_PIRVATE_MONSTER,msg);
		GatewayLinkedSet.send2Server(pkg);
	}
	
	
	/**
	 * 刷私有怪物
	 * @param playerId
	 * @param monsterId
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void createPrivateMonster(long playerId,int monsterId,int x,int y,int z,int leaveTime,int mapId){
		CreatePrivateMonsterInnerMsg.Builder msg = CreatePrivateMonsterInnerMsg.newBuilder();
		msg.setMonsterId(monsterId);
		msg.setPlayerId(playerId);
		msg.setLeaveTime(leaveTime);
		msg.setMapId(mapId);
		PBVector3.Builder pos = PBVector3.newBuilder();
		pos.setX(x);
		pos.setY(y);
		pos.setZ(z);
		msg.setPos(pos);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_CREATE_PIRVATE_MONSTER,msg);
		GatewayLinkedSet.send2Server(pkg);
	}
	
	
	
	
}
