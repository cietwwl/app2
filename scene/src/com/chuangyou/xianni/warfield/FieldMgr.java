package com.chuangyou.xianni.warfield;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.FileOperate;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Rect;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.constant.SpwanInfoType;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.touchPoint.TouchPointSpwanNode;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.FieldConstants;
import com.chuangyou.xianni.warfield.helper.ParseMapDataHelper;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeeker;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeekerStatuCode;
import com.chuangyou.xianni.warfield.spawn.ActiveSpwanNode;
import com.chuangyou.xianni.warfield.spawn.GatherSpawnNode;
import com.chuangyou.xianni.warfield.spawn.MonsterSpawnNode;
import com.chuangyou.xianni.warfield.spawn.NpcSpawnNode;
import com.chuangyou.xianni.warfield.spawn.PerareState;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.spawn.TriggerPointSpwanNode;
import com.chuangyou.xianni.warfield.spawn.WorkingState;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;

public class FieldMgr {

	private static FieldMgr ins = new FieldMgr();

	public static FieldMgr getIns() {
		return ins;
	}

	/**
	 * 寻路模板数据
	 */
	private static Map<String, NavmeshSeeker>	_seekersTemp	= new HashMap<String, NavmeshSeeker>();

	/**
	 * 地图边界配置
	 */
	private static Map<String, Rect>			_mapBounds		= new HashMap<String, Rect>();

	/**
	 * 获取一个地图的查看器
	 * 
	 * @param fieldName
	 * @return
	 */
	public NavmeshSeeker GetSeekerTemp(String fieldName) {
		if (_seekersTemp.containsKey(fieldName)) {
			return _seekersTemp.get(fieldName);
		}
		return null;
	}

	/**
	 * 获取一个地图的边界
	 * 
	 * @param fieldName
	 * @return
	 */
	public Rect GetBound(String fieldName) {
		if (_mapBounds.containsKey(fieldName)) {
			return _mapBounds.get(fieldName);
		}
		return null;
	}

	/**
	 * 创建数据对象
	 */
	public boolean initilize() {
		// 初始化地图
		if (!initField()) {
			return false;
		}
		createStateField();
		return true;
	}

	/**
	 * 场景静态地图
	 */
	private boolean initField() {
		for (Map.Entry<Integer, FieldInfo> entry : FieldTemplateMgr.fieldTemps.entrySet()) {
			if (!_seekersTemp.containsKey(entry.getValue().getResName())) {
				String ROOT = Config.getValue("mapdata");
				File f = new File(ROOT);
				File configFile = new File(ROOT + entry.getValue().getResName() + ".txt");
				if (configFile.exists()) {
					String name = configFile.getName();
					String configName = name.split("[.]")[0];
					try {
						byte[] data = FileOperate.read2Bytes(configFile.getAbsolutePath());
						NavmeshSeeker seeker = ParseMapDataHelper.parse2Seeker(data);
						Rect rect = seeker.getRect().clone();
						_mapBounds.put(configName, rect);
						_seekersTemp.put(configName, seeker);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.error("初始化场景数据错误", e);
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 创建静态地图
	 */
	private void createStateField() {
		for (Map.Entry<Integer, FieldInfo> entry : FieldTemplateMgr.fieldTemps.entrySet()) {
			if (entry.getValue().getType() == 1) // 公共/静态地图
			{
				initCreateField(entry.getValue().getMapKey(), entry.getValue().getType());
			}
		}
	}

	// /所有地图的集合
	private ConcurrentHashMap<Integer, Field> fields = new ConcurrentHashMap<Integer, Field>();

	public Field getField(int fid) {
		if (fields.containsKey(fid))
			return fields.get(fid);
		return null;
	}

	/**
	 * 获取指定mapkey的地图列表(副本地图相同key的地图可能会创建多个)
	 * 
	 * @param mapKey
	 * @return
	 */
	public List<Field> getFieldsByMapKey(int mapKey) {
		List<Field> resultList = new ArrayList<>();

		Iterator<Entry<Integer, Field>> it = fields.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Field> entry = it.next();
			if (entry.getValue().getMapKey() == mapKey) {
				resultList.add(entry.getValue());
			}
		}

		return resultList;
	}

	/**
	 * 创建一个地图
	 * 
	 * @param mapkey
	 * @return
	 */
	public Field createCampaignField(int mapkey, byte type, int campaignId) {
		Field f = new Field();
		f.setCampaignId(campaignId);
		if (type == 1) { // 静态地图id直接由mapkey
			f.id = mapkey;
		} else {
			f.id = IDMakerHelper.nextFieldId();// 动态地图由100000+ 生成 //
			// //IdUtilFacotry.getIdUtil(mapkey).nextId();
			notice2Center(f.id, mapkey);
			// 创建/销毁一个动态地图，需要通知center服务器创建一个镜像
		}
		f.setMapKey(mapkey);
		fields.put(f.id, f);
		spwanInit(f);
		Log.error("初始化地图，mapKey = " + mapkey);
		return f;
	}

	/** 服务器启动时，创建初始化地图 */
	public Field initCreateField(int mapkey, byte type) {
		Field f = new Field();
		f.setMapKey(mapkey);
		f.id = mapkey;
		fields.put(f.id, f);
		spwanInit(f);
		Log.error("初始化地图，mapKey = " + mapkey);
		return f;
	}

	private void spwanInit(Field f) {
		Map<Integer, SpawnInfo> spawnInfos = SpawnTemplateMgr.getFieldSpawnInfos(f.getMapKey());
		if (spawnInfos == null || spawnInfos.size() == 0) {
			Log.error("map has not anly spawnInfo ,the mapKey is:" + f.getMapKey());
			return;
		}
		for (SpawnInfo sf : spawnInfos.values()) {
			// if (sf.getTimerType() !=
			// FieldConstants.MonsterReflushTimerType.none) {
			// continue;
			// }
			SpwanNode node = null;

			switch (sf.getEntityType()) {
				case SpwanInfoType.MONSTER:
					node = new MonsterSpawnNode(sf, f);
					break;
				case SpwanInfoType.NPC:
					node = new NpcSpawnNode(sf, f);
					break;
				case SpwanInfoType.TRANSPOINT:
					node = new TouchPointSpwanNode(sf, f);
					break;
				case SpwanInfoType.GATHER_POINT:
					node = new GatherSpawnNode(sf, f);
					break;
				case SpwanInfoType.TASK_TRIGGER:
					node = new TriggerPointSpwanNode(sf, f);
					break;
				case SpwanInfoType.COMMON_TRIGGER:
					node = new ActiveSpwanNode(sf, f);
					break;
				default:
					node = new SpwanNode(sf, f);
			}
			f.addSpawnNode(node);
			node.build();
			if (node.getSpawnInfo().getInitStatu() == 1) {
				node.stateTransition(new WorkingState(node));
			} else {
				node.stateTransition(new PerareState(node));
			}
		}
	}

	public boolean clear(int id) {
		synchronized (fields) {
			fields.remove(id);
		}
		return true;
	}

	/// 生成怪物
	// private void spawnMonster(Field f) {
	// List<Integer> indexes =
	// SpawnTemplateMgr.spawnMonsterIndexes.get(f.getMapKey());
	// if (indexes == null)
	// return;
	// for (Integer index : indexes) {
	// SpawnInfo spawn =
	// SpawnTemplateMgr.spawnMonster.get(f.getMapKey()).get(index);
	// if (spawn.getTimerType() == FieldConstants.MonsterReflushTimerType.none)
	// {
	// ThreadManager.actionExecutor.enDelayQueue(new SpawnMonsterAction(spawn,
	// f));
	// }
	// }
	// }
	//
	// /// 生成NPC
	// private void spawnNPC(Field f) {
	// List<Integer> indexes =
	// SpawnTemplateMgr.spawnNpcIndexes.get(f.getMapKey());
	// if (indexes == null)
	// return;
	// for (Integer index : indexes) {
	// SpawnInfo spawn =
	// SpawnTemplateMgr.spawnNpc.get(f.getMapKey()).get(index);
	// if (spawn.getTimerType() == FieldConstants.MonsterReflushTimerType.none)
	// {
	// ThreadManager.actionExecutor.enDelayQueue(new SpawnNpcAction(spawn, f));
	// }
	// }
	// }
	//
	// /**
	// * 生成地图节点
	// *
	// * @param f
	// */
	// private void spawnPoint(Field f) {
	// List<Integer> indexes =
	// SpawnTemplateMgr.spawnPointIndexes.get(f.getMapKey());
	// if (indexes == null) {
	// return;
	// }
	// for (Integer index : indexes) {
	// SpawnInfo spawn =
	// SpawnTemplateMgr.spawnPoint.get(f.getMapKey()).get(index);
	//
	// TouchPoint tp = new TouchPoint();
	// tp.setPointId(spawn.getEntityId());
	// tp.setSpawnId(spawn.getId());
	// tp.setMapKey(f.getMapKey());
	// f.addTouchPoint(tp);
	//
	// if (spawn.getTimerType() == FieldConstants.MonsterReflushTimerType.none)
	// {
	// ThreadManager.actionExecutor.enDelayQueue(new SpawnTuchPointAction(spawn,
	// f));
	// }
	//
	// }
	// }

	// 地图创建成功，通知center服务器创建代理
	private void notice2Center(int id, int mapKey) {
		PostionMsg.Builder builder = PostionMsg.newBuilder();
		builder.setMapId(id);
		builder.setMapKey(mapKey);

		PBMessage message = MessageUtil.buildMessage(Protocol.C_SCENE_CREATE_MAP, builder.build());
		GatewayLinkedSet.send2Server(message);

	}
}
