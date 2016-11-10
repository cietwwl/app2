package com.chuangyou.xianni.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.map.cmd.ChangeMapCmd;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.FieldInfoDao;
import com.chuangyou.xianni.word.WorldMgr;

public class MapProxyManager {
	private static Map<Integer, MapProxy>	maps			= new ConcurrentHashMap<Integer, MapProxy>();
	private static Map<Integer, FieldInfo>	fieldInfoMaps	= new HashMap<Integer, FieldInfo>();

	public static boolean init() {
		return reload();
	}

	public static boolean reload() {
		// 初始当前静态地图
		FieldInfoDao dao = DBManager.getFieldInfoDao();
		List<FieldInfo> info = dao.getAll();
		for (FieldInfo f : info) {
			fieldInfoMaps.put(f.getMapKey(), f);
			if (f.getType() == 1) {
				MapProxy proxy = new MapProxy(f.getMapKey(), f.getMapKey());
				maps.put(proxy.getMapId(), proxy);
			}
		}
		return true;
	}

	/**
	 * 创建一个地图代理
	 * 
	 * @param mapId
	 * @param mapKey
	 * @return
	 */
	public static boolean createMapProxy(int mapId, int mapKey) {
		// 地图是否存在数据
		if (!fieldInfoMaps.containsKey(mapKey)) {
			Log.error("create mapProxy,but FieldInfo is exists,mapId:" + mapId + "  mapKey:" + mapKey);
			return false;
		}
		// 已经存在ID重复，不再创建，否则创建 TODO 此处待考虑，是否直接创建
		if (maps.containsKey(mapId)) {
			Log.error("create mapProxy,but is exists,mapId:" + mapId + "  mapKey:" + mapKey);
		} else {
			MapProxy proxy = new MapProxy(mapId, mapKey);
			maps.put(proxy.getMapId(), proxy);
		}
		return true;
	}

	/**
	 * 获取地图模板信息
	 */
	public static FieldInfo getFieldTempInfo(int mapKey) {
		return fieldInfoMaps.get(mapKey);
	}

	/** 是否存在该地图 */
	public static boolean isExists(int mapId) {
		MapProxy proxy = maps.get(mapId);
		if (proxy == null) {
			return false;
		}
		return proxy.isOpen();
	}

	/**
	 * 将主角传送到指定地图的指定位置
	 * 
	 * @param mapId
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void changeMap(long playerId, int mapId, int x, int y, int z, int angle) {
		FieldInfo info = getFieldTempInfo(mapId);
		if (info == null) {
			ErrorMsgUtil.sendErrorMsg(playerId, ErrorCode.UNKNOW_ERROR, (short) -100, "地图不存在:" + mapId);
			return;
		}
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null)
			return;

		ReqChangeMapMsg.Builder req = ReqChangeMapMsg.newBuilder();
		PBVector3.Builder pos = PBVector3.newBuilder();
		pos.setX(x);
		pos.setY(y);
		pos.setZ(z);
		pos.setAngle(angle);
		
		PostionMsg.Builder postion = PostionMsg.newBuilder();
		postion.setPostion(pos);
		PBMessage pkg;

		if (info.getType() == 1) { // 公共地图
			postion.setMapId(mapId);
			postion.setMapKey(mapId);
			req.setPostionMsg(postion.build());
			pkg = MessageUtil.buildMessage(Protocol.C_CHANGE_MAP, playerId, req);
			pkg.setBytes(pkg.getMessage().toByteArray());
			player.enqueue(new CmdTask(new ChangeMapCmd(), null, pkg, player.getCmdTaskQueue()));
		} else if (info.getType() == 2) { // 副本地图(单人。暂未处理多人副本情况)
			postion.setMapId(-1);
			postion.setMapKey(mapId);
			req.setPostionMsg(postion.build());
			pkg = MessageUtil.buildMessage(Protocol.C_CHANGE_MAP, playerId, req);
			pkg.setBytes(pkg.getMessage().toByteArray());
			player.enqueue(new CmdTask(new ChangeMapCmd(), null, pkg, player.getCmdTaskQueue()));
		}
	}

	/** 通过NPC请求创建副本 */
	public static void createCampaignByNpc(long playerId, int campaignId, long npcId) {

	}

}
